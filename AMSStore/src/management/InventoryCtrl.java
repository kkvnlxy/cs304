package management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import sale.Item;
import sale.Item.GENRE;
import sale.Item.ITEM_TYPE;
import util.JDBCConnection;

/**
 * Control object class for management package
 * @creator kevin
 *
 */
public class InventoryCtrl 
{
	/**
	 * This method insert a new Item tuple into the Item table
	 * @param upc
	 * @param title
	 * @param type
	 * @param category
	 * @param company
	 * @param year
	 * @param price_incent
	 * @param initStk
	 * @throws SQLException if there is already a tuple with the same upc exists
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void createItem(	String upc, String title, ITEM_TYPE type, 
							GENRE category, String company, String year, 
							int price_incent, int initStk)
			throws SQLException, ClassNotFoundException, IOException
	{
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();
	

		System.out.println("price in cent: " + price_incent);//testing
		System.out.println("price in doulb: " + (double)price_incent / 100.0);//testing
		
		PreparedStatement stmt = conn.prepareStatement(
										"INSERT INTO Item " +
										"VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		stmt.setString(1, upc);
		stmt.setString(2, title);
		stmt.setString(3, Item.translateType(type));
		stmt.setString(4, Item.translateGenre(category));
		stmt.setString(5, company);
		stmt.setString(6, year);
		stmt.setDouble(7, (double)price_incent / 100.0);//TODO setDouble/setFloat?
		stmt.setInt(8, initStk);
		
		try
		{
			int count = stmt.executeUpdate();
		}
		finally
		{
			stmt.close();
		}
	}
	
	/**
	 * This method updates (increments) the stock attribute in the Item table
	 * @param upc
	 * @param qty quantity to INCREMENT
	 * @throws SQLException if there is no tuple with the given upc is found in
	 * 						the database
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void stockItem(String upc, int qty) 
			throws SQLException, ClassNotFoundException, IOException
	{
		if(qty < 0 )
		//sanity check
			throw new IOException("Quantity input cannot be less than 0.");
		
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(
										"UPDATE Item " +
										"SET stock = stock + ? " +
										"WHERE upc = ? ");
		stmt.setInt(1, qty);
		stmt.setString(2, upc);
		try
		{
			int update = stmt.executeUpdate();
			if(update == 1)
				return;
			else if(update == 0)
			//if none is update
				throw new SQLException("The Item with upc " + upc + " does " +
									   "not exist.");
			else
			//Fatal error: more than 1 tuple is updated -> duplicate upc!!!
				throw new SQLException("Fatal Error: Duplicate UPC!");
		}
		finally
		{
			stmt.close();
		}
	}
	
	/**
	 * This method increments the stock attribute in the Item table as well as
	 * modify the price attribute
	 * @param upc
	 * @param qty quantity to be increment
	 * @param price_incent price to be overwrite
	 * @throws SQLException if no tuple with the given upc can be found in the
	 * 						database
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public void stockItemPrice(String upc, int qty, int price_incent)
			throws SQLException, ClassNotFoundException, IOException
	{
		if(qty < 0)
		//sanity check
			throw new IOException("Quantity input cannot be less than 0.");
		if(price_incent <= 0)
		//santiy check
			throw new IOException("Price input must be larger than 0.");
		
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(
										"UPDATE Item " +
										"SET stock = stock + ?, price = ? " +
										"WHERE upc = ?");
		stmt.setInt(1, qty);
		stmt.setDouble(2, (double)price_incent / 100.0);
		stmt.setString(3, upc);
		try
		{
			int update = stmt.executeUpdate();
			if(update == 1)
				return;
			else if(update == 0)
			//if none is update
				throw new SQLException("The Item with upc " + upc + " does " +
									   "not exist.");
			else
			//Fatal error: more than 1 tuple is updated -> duplicate upc!!!
				throw new SQLException("Fatal Error: Duplicate UPC!");
		}
		finally
		{
			stmt.close();
		}
	}
	
	/**
	 * This method generate a sales report with a particular given day
	 * @param date
	 * @return entity object DailyReport will be return
	 * @throws SQLException
	 */
	public DailyReport genDailyReport(GregorianCalendar date) 
			throws SQLException
	{
		
	}
	
	/**
	 * This method gerneate a report about the n top selling items
	 * @param date
	 * @param top_n
	 * @return entity object TopNReport will be return
	 * @throws SQLException
	 */
	public TopNReport genTopNReport(GregorianCalendar date, int top_n)
			throws SQLException
	{
		
	}
	
	private Connection conn;
}
