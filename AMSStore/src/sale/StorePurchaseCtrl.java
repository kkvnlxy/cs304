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

import util.JDBCConnection;

public class StorePurchaseCtrl extends TransactionCtrl
{
	public StorePurchaseCtrl()
	{
		super();
	}
	
	/*
	 **********************************************
	 * inheriting abstract methods:
	 **********************************************
	 */
	/**
	 * @pre Integer value in the items have to be greater than 0
	 * @author kevin
	 */
	@Override
 	public Receipt process(String card_num, GregorianCalendar exp_date) 
			throws SQLException, ClassNotFoundException, IOException
	{
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();
		
		//true if it is cash purchase, false if credit card
		boolean cash_pur = 	(card_num == null && exp_date == null) ||
							(card_num.equals(""));
		
		PreparedStatement stmt = null;
		
		try
		{
			/*
			 * process the Purchase table:
			 * insert an new entry and retrieve the auto-generated receiptId
			 */
			//preparing the statement
			if(cash_pur)
			{
				stmt = conn.prepareStatement(
							//cash purchase: cardNum and expiryDate have to be 
							//null, not empty string (ie only setting pDate)
							"INSERT INTO Purchase (pDate) " +
							"VALUES (?)",
							//receiptId is auto-generated attribute
							new String[]{"receiptId"});
				stmt.setDate(1, new Date(Calendar.getInstance().DATE));
			}
			else
			//credit card purchase
			{
				stmt = conn.prepareStatement(
							"INSERT INTO Purchase (pDate, cardNum, expiryDate) " +
							"VALUES (?, ?, ?)",
							//receiptId is auto-generated attribute
							new String[]{"receiptId"});
				stmt.setDate(1, new Date(Calendar.getInstance().DATE));
				stmt.setString(2, card_num);
				stmt.setDate(3, new Date(exp_date.DATE));
			}
			//execute the statement and get the auto-generated receiptId
			int count = stmt.executeUpdate();
			if(count != 1)
			//sanity check
				throw new SQLException("Fail to create a Purchase.");
			ResultSet result = stmt.getGeneratedKeys();
			if(!result.next())
			//sanity check
				throw new SQLException("No Receipt ID can be generated.");
			String r_id = result.getString(1);
			
			processItems(r_id);
			
			//last, construct a representation of the Puchase entry
			if(cash_pur)
				return new Purchase(r_id, new GregorianCalendar());
			else
				return new Purchase(r_id, new GregorianCalendar(),
									//need to display only the last five digit 
									//of the card
									"XXXXXXXXXXX" + card_num.substring(11), 
									exp_date);
		}
		finally
		{
			stmt.close();
		}
	}
	/**
	 * @author kevin
	 */
	@Override
	public void cancel() 
	{
		this.cur_rcpt_id = null;
		this.items.clear();
	}
	
	/**
	 * process the PurchaseItem table:
	 * insert new entry for each item in the items instance field
	 * +
	 * process the Item table:
	 * update (decrement) the stock attribute with each item in items instance 
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
				String sql = "INSERT INTO PurchaseItem " +
							 "VALUES('" + r_id +"', " + 
							 		"'" + each.getKey().getUPC() + "', " +
							 		"'" + each.getValue().intValue() + "')";
				stmt = conn.prepareStatement(sql);
				int count = stmt.executeUpdate();
				if(count != 1)
				//sanity check
					throw new SQLException("This item has already been " +
									   	   "associated with the purchase.");

				//updating stock in Item table:
				sql = 	"UPDATE Item " +
						"SET stock = stock - " + each.getValue().intValue() + " " +
						"WHERE upc = '" + each.getKey().getUPC() + "'";
				count = stmt.executeUpdate(sql);
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
	
	private String cur_rcpt_id;	//the receipt id currently is processing, 
								//may not needed
}
