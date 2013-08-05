package sale;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map.Entry;

import register.AuthenCtrl;
import register.AuthenException;
import register.Customer;
import util.JDBCConnection;

public class OnlinePurchaseCtrl extends TransactionCtrl 
{
	/*
	 **********************************************
	 * inheriting abstract methods:
	 **********************************************
	 */
	/**
	 * @pre the cur_cust field must be authenticated before this method is
	 * 		called
	 * @author kevin
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	@Override
	public Receipt process(String card_num, GregorianCalendar exp_date)
			throws SQLException, ClassNotFoundException, IOException
	{
		if(conn == null)
			conn = JDBCConnection.getConnection();

		/*
		 * Process the Purchase table:
		 * insert a new entry
		 */
		//First determine the expected delivery date:
		GregorianCalendar expt_date = expectDelivery();

		PreparedStatement stmt = null;
		try
		{
			stmt = conn.prepareStatement(
					"INSERT INTO Purchase (pDate, " +
										  "cid, " +
										  "cardNum, " +
										  "expiryDate, " +
										  "expectedDate)" +
					"VALUES (?, ?, ?, ?, ?)");
			stmt.setDate(1, new Date(Calendar.getInstance().DATE));
			stmt.setString(2, cur_cust.getCustomerID());
			stmt.setString(3, card_num);
			stmt.setDate(4, new Date(exp_date.DATE));
			stmt.setDate(5, new Date(expt_date.DATE));
			
			int count = stmt.executeUpdate();
			if(count != 1)
			//sanity check
				throw new SQLException("Fail to create a Purchase.");
			ResultSet result = stmt.getGeneratedKeys();
			if(!result.next())
			//sanity check
				throw new SQLException("No Receipt ID can be generated.");
			String r_id = result.getString(1);//retrieve the receiptId
			
//			stmt.close();//TODO: need?
			processItems(r_id);
			
			return new Purchase(r_id, new GregorianCalendar(), 
								cur_cust.getCustomerID(),
								//need to display only the last five digit 
								//of the card
								"XXXXXXXXXXX" + card_num.substring(11), 
								exp_date, expt_date);
		}
		finally
		{
			stmt.close();
		}
	}
	@Override
	public void cancel() 
	{
		this.cur_cust = null;
		this.items.clear();
	}
	
	/**
	 * This method will authenticate the current purchase transaction to a
	 * specific customer. It requires register.AuthenCtrl object to verify if
	 * the password match the cust_id provided
	 * In simple word, it construct cur_cust field if the passwords match
	 * @param cust_id the customer id
	 * @param password the password
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @post this.cur_cust will be set
	 * @throw AuthenException if the password does not match cust_id
	 * @throw SQLException if given cid is not found in the database
	 */
	public void authen(String cid, String pswd)
			throws SQLException, AuthenException, ClassNotFoundException, 
					FileNotFoundException, IOException
	{
		cur_cust = new AuthenCtrl(cid, pswd).authenticate();
		//TODO need further operation??
	}
	
	private GregorianCalendar expectDelivery()
	{
		//TODO not yet implemented
		return null;
	}
	/**
	 * process the PurchaseItem table:
	 * insert new entry for each item in the items instance field
	 * +
	 * process the Item table:
	 * update the stock attribute with each item in items instance 
	 * field
	 * @author kevin
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	private void processItems(String r_id) 
			throws SQLException, ClassNotFoundException, IOException
	{
		if(conn == null)
			conn = JDBCConnection.getConnection();
		PreparedStatement stmt = null;
		try
		{
			for(Entry<Item, Integer> each : items.entrySet())
			{
				//adding entry to PurchaseItem table:
				int count = stmt.executeUpdate(
								"INSERT INTO PurchaseItem " +
										"VALUES(" + r_id +", " + 
										each.getKey().getUPC() + ", " +
										each.getValue().intValue() + ")");
				if(count != 1)
				//sanity check
					throw new SQLException("This item has already been " +
									   	   "associated with the purchase.");

				//updating stock in Item table:
				count = stmt.executeUpdate(
							"UPDATE Item " +
							"SET stock = stock - " + each.getValue().intValue() +
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
	
	private Customer cur_cust;
}
