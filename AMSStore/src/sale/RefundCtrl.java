package sale;

import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;

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
			verify();
		}
		else
		//purchase cannot be found
		{
			this.purc = null;
			this.status = false;
			throw new SQLException("Receipt ID cannot be found.");
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
	@Override
	public Receipt process(String card_num, GregorianCalendar exp_date)
			throws SQLException
	{
		// TODO Auto-generated method stub
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
	
	private boolean status; //within 15 days?
	private Purchase purc; //the purchase the current refund is refering to

	public static final int MAX_RETURN_DAYS = 15;
}
