package sale;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;

import sale.Item.GENRE;
import sale.Item.ITEM_TYPE;
import util.JDBCConnection;

/**
 * Abstract class that all control object in this package should inherit.
 * @author kevin
 *
 */
public abstract class TransactionCtrl 
{
	/**
	 * Add an Item object to the items, which require to go to the Item table
	 * @param upc the upc going to be added
	 * @param qty the number of the same item to be added
	 * @return the "entity" object Item represented by the upc
	 * @throws SQLException there is no tuple in the Item table with that upc
	 * @throws IOException if configuration file parsing error
	 * @throws ClassNotFoundException database driver cannot be found
	 */
	public Item addItem(String upc, int qty) 
			throws SQLException, ClassNotFoundException, IOException
	{
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(
										"SELECT * " +
										"FROM Item" +
										"WHERE upc = " + upc);
		try
		{
			ResultSet result = stmt.executeQuery(); 
			if(result.next())
			//expecting only 1 tuple is returned
			{
				String the_upc = result.getString(Item.UPC_IND);
				
				String title = result.getString(Item.TITLE_IND);
				
				ITEM_TYPE type = Item.translateType(result.getString(
																Item.TYPE_IND));
				
				GENRE category = Item.translateGenre(result.getString(
															Item.CATEGORY_IND));

				String company = result.getString(Item.COMPANY_IND);
				
				String year = result.getString(Item.YEAR_IND);
				
				int price = (int)result.getDouble(Item.PRICE_IND) * 100;
				
				//adding this item item to the cart and returns it
				Item item = new Item(the_upc, title, type, category, company, 
									 year, price);
				items.put(item, new Integer(qty));
				return item;
			}
			else
			//item with the specified upc is not found
				throw new SQLException("Item with upc " + upc + 
										"cannot be found.");
		}
		finally
		{
			stmt.close();
		}
	}
	
	/**
	 * Methods that tells the database the update/insert relevant tables
	 * @param card_num credit card number, null if pay by cash
	 * @param exp_date expiry date, null if pay by cash
	 * @return Purchase or Return for relevant operation
	 * @throws SQLException any exception possible
	 */
	public abstract Receipt process(String card_num, GregorianCalendar exp_date)
			throws SQLException;
	
	/**
	 * Set all the current state of this object to null, prepare for the next
	 * transaction
	 */
	public abstract void cancel();
	
	protected HashMap<Item, Integer> items;
	protected Connection conn;
}
