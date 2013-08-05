package sale;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map.Entry;

import sale.Receipt.PAYMENT_METHOD;
import util.JDBCConnection;

public class RefundCtrl extends TransactionCtrl 
{
	/**
	 * The constructor will go through the database and gather the information
	 * about the purchase to be refunded.
	 * @param pur_id the purchase id to be refund
	 * @throws SQLException database connection failure or the purchase with 
	 * 						the given purchase id is not found
	 * @throws IOException configuration file parsing error or cannot be found
	 * @throws ClassNotFoundException database drive cannot be found in the 
	 * 								  system
	 * @author kevin
	 */
	public RefundCtrl(String pur_id) 
			throws ClassNotFoundException, IOException, SQLException
	{
		if(conn == null)
			conn = JDBCConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(
									"SELECT * " +
									"FROM Purchase" +
									"WHERE receiptId = " + pur_id);
		try
		{
			ResultSet result = stmt.executeQuery();
			
			if(result.next())
			//expecting one tuple
			{
				String rcpt_id = result.getString(Purchase.RID_IND);
				
				GregorianCalendar pur_date = convert(result.getDate(
			   										   	   Purchase.PDATE_IND));
				
				String cid = result.getString(Purchase.CID_IND);
				
				String card_num = result.getString(Purchase.CARDNUM_IND);
				
				GregorianCalendar expr_date = convert(result.getDate(
														Purchase.EXPRDATE_IND));
			
				GregorianCalendar expt_date = convert(result.getDate(
														Purchase.EXPTDATE_IND));
			
				GregorianCalendar del_date = convert(result.getDate(
														 Purchase.DELDATE_IND));
			
				//keep track of the purchase in the instance fields:
				this.purc = new Purchase(rcpt_id, pur_date, cid, card_num, 
									 	 expr_date, expt_date, del_date);
				this.verify();
			}
			else
			//purchase cannot be found
			{
				this.purc = null;
				this.status = false;
				throw new SQLException("Receipt ID cannot be found.");
			}
		}
		finally
		{
			stmt.close();
		}
	}
	
	/*
	 **********************************************
	 * inheriting abstract methods:
	 **********************************************
	 */
	/**
	 * This method will test if this refund is allowed. A null value Item object
	 * will be returned if the refund is not allowed.
	 * @pre instance field status must be set properly before invoking this 
	 * 		method
	 * @author kevin
	 */
	@Override
	public Item addItem(String upc, int qty)
			throws SQLException, ClassNotFoundException, IOException
	{
		if(status)
			return super.addItem(upc, qty);
		else
			return null;
	}
	/**
	 * @return a Retun object if refund succeed, a null value is returned for
	 * 		   un-qualified refund
	 */
	@Override
	public Receipt process(String card_num, GregorianCalendar exp_date)
			throws SQLException, ClassNotFoundException, IOException
	{
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();
		
		if(status)
		{
			//0. Get the corresponding payment method and check whether the 
			//supplied parameter is correct
			PAYMENT_METHOD method = this.getPaymentMethod();
			//check if the supplied parameters are same as the one in database
			if((method == PAYMENT_METHOD.CREDIT_CARD) && //short circuit
			   (!purc.getCardNum().equals(card_num) ||
				purc.getExprDate().DATE != exp_date.DATE))//TODO: testing needed
				return null;
			//else if it is cash, don't care what the credit card is supplied

			PreparedStatement stmt = null;
			try
			{
				//1. Inserting an entry into Refund table and retrieve the 
				//auto-gen retid
				stmt = conn.prepareStatement(
								"INSERT INTO Refund(rDate, receiptId) " +
								"VALUES (?, ?)", 
								Statement.RETURN_GENERATED_KEYS);
				stmt.setDate(1, new Date(Calendar.getInstance().DATE));
				stmt.setString(2, purc.getRcptId());
				int count = stmt.executeUpdate();
				if(count != 1)
				//sanity check
					throw new SQLException("Fail to create a Refund.");
				ResultSet result = stmt.getGeneratedKeys();
				if(!result.next())
				//sanity check
					throw new SQLException("No Refund ID can be generated.");
				String ret_id = result.getString(1);//retrieve the receiptId
				//stmt.close();//TODO: need?
				
				//2. With the retid, inserting entries into RefundItem table,
				//3. Update the stock
				this.processItems(ret_id);
				
				//4. Construct and return a Refund entity object:
				return new Return(purc.getRcptId(), new GregorianCalendar(),
								  ret_id);
			}
			finally
			{
				stmt.close();
			}
		}
		else
			return null;
	}
	/**
	 * @author kevin
	 */
	@Override
	public void cancel() 
	{
		this.status = false;
		this.purc = null;
		this.items.clear();
	}
	
	/**
	 * This method returns true if the purchase is within 15 days from today
	 */
	public boolean getStatus()
	{
		return this.status;
	}
	/**
	 * This method returns the payment method of the current purchase
	 * @return
	 */
	public PAYMENT_METHOD getPaymentMethod()
	{
		return this.purc.getPaymentMethod();
	}
	
	/**
	 * This is a helper method to check whether the purc.purdate is 15 within
	 * from today.
	 * @author kevin
	 */
	private void verify()
	{
		Calendar deadline = this.purc.getPDate();
		deadline.add(Calendar.DATE, MAX_RETURN_DAYS);
		
		this.status = deadline.compareTo(Calendar.getInstance()) >= 0;
	}
	/**
	 * This is a helper method. You may get a lot of warning from this method,
	 * because the getter methods from sql.Date which inherits java.util.Date's
	 * are all obsolete.
	 * @param date
	 * @return a equivalent object in GregorianCalendar
	 */
	private GregorianCalendar convert(Date date)
	{
		return new GregorianCalendar(date.getYear(), date.getMonth(),
									 date.getDay(), date.getHours(), 
									 date.getMinutes(), date.getSeconds());
	}
	/**
	 * process the RefundItem table:
	 * insert new entry for each item in the items instance field
	 * +
	 * process the Item table:
	 * update (increment) the stock attribute with each item in items instance 
	 * field
	 * @author kevin
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void processItems(String ret_id) 
			throws SQLException, ClassNotFoundException, IOException
	{
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();
		PreparedStatement stmt = null;
		try
		{
			for(Entry<Item, Integer> each : this.items.entrySet())
			{
				int count = stmt.executeUpdate(
								"INSERT INTO RefundItem " +
								"VALUES(" + ret_id + ", " + 
										each.getKey().getUPC() + ", " + 
										each.getValue().intValue() + ")" );
				if(count != 1)
					//sanity check
						throw new SQLException("This item has already been " +
										   	   "associated with the purchase.");
				
				//updating stock in Item table:
				count = stmt.executeUpdate(
							"UPDATE Item " +
							"SET stock = stock + " + each.getValue().intValue() +
							"WHERE upc = " + each.getKey().getUPC());
				if(count != 1)
				//sanity check
					throw new SQLException("Fatal error: Duplicate UPC.");
			}
		}
		finally
		{
			stmt.close();
		}
	}
	
	private boolean status; //within 15 days?
	private Purchase purc; //the purchase the current refund is refering to

	public static final int MAX_RETURN_DAYS = 15;
}
