package management;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import sale.Item.GENRE;
import sale.Item.ITEM_TYPE;

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
	 */
	public void createItem(	String upc, String title, ITEM_TYPE type, 
							GENRE category, String company, String year, 
							int price_incent, int initStk)
			throws SQLException
	{
		
	}
	
	/**
	 * This method updates (increments) the stock attribute in the Item table
	 * @param upc
	 * @param qty quantity to INCREMENT
	 * @throws SQLException if there is no tuple with the given upc is found in
	 * 						the database
	 */
	public void stockItem(String upc, int qty) throws SQLException
	{
		
	}
	
	/**
	 * This method increments the stock attribute in the Item table as well as
	 * modify the price attribute
	 * @param upc
	 * @param qty quantity to be increment
	 * @param price_incent price to be overwrite
	 * @throws SQLException if no tuple with the given upc can be found in the
	 * 						database
	 */
	public void stockItemPrice(String upc, int qty, int price_incent)
			throws SQLException
	{
		
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
