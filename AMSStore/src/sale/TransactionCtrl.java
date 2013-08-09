package sale;

import java.io.FileNotFoundException;
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
	protected TransactionCtrl()
	{
		this.items = new HashMap<Item, Integer>();
		this.upc_item_map = new HashMap<String, Item>();
		this.grandtotal = 0;
	}
	
	/**
	 * Add an Item object to the items, which require to go to the Item table
	 * @param upc the upc going to be added
	 * @param qty the number of the same item to be added
	 * @return the "entity" object Item represented by the upc
	 * @throws SQLException there is no tuple in the Item table with that upc
	 * @throws IOException if configuration file parsing error
	 * @throws ClassNotFoundException database driver cannot be found
	 * @throws Exception if not enough stock for the given item
	 */
	public Item addItem(String upc, int qty) 
			throws SQLException, ClassNotFoundException, IOException, Exception
	{
		if(qty <= 0)
		//sanity check
			throw new IOException("Quantity cannot be less than or equal to " +
								  "0.");
		
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(
										"SELECT * " +
										"FROM Item " +
										"WHERE upc = ?");
		stmt.setString(1, upc);
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
				
				int price = (int)(result.getDouble(Item.PRICE_IND) * 100.0);
				
				int stk = result.getInt(Item.STOCK_IND);
				if(stk < qty)
				//sanity check
					//TODO cannot think of what is the appropriate exception,
					//		use the most general one for the moment
					throw new Exception("Not enough stock for " + title + 
										"(" + upc + ")");
				
				//adding this item item to the cart and returns it
				Item item = new Item(the_upc, title, type, category, company, 
									 year, price, stk);
				items.put(item, new Integer(qty));
				upc_item_map.put(upc, item);
				this.grandtotal += price * qty;
				return item;
			}
			else
			//item with the specified upc is not found
				throw new SQLException("Item with upc " + upc + 
										" cannot be found.");
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
	 * @throws FileNotFoundException if the configuration file cannot be found
	 * @throws IOException error when parsing configuration file
	 * @throws ClassNotFoundException database driver not found in the system
	 */
	public abstract Receipt process(String card_num, GregorianCalendar exp_date)
			throws SQLException, ClassNotFoundException, FileNotFoundException, 
				   IOException;
	
	/**
	 * Set all the current state of this object to null, prepare for the next
	 * transaction
	 */
	public abstract void cancel();
	
	final public int getGrandTotal()
	{
		return this.grandtotal;
	}
	
	/**
	 * EXTREAM CAUTION: NO ERROR CHECKING!!!
	 * @param upc
	 * @return
	 */
	public int getSubTotal(String upc)
	{
		Item target = upc_item_map.get(upc);
		
		int unit_price = target.getPriceInCents();
		int qty = items.get(target).intValue();
		
		return unit_price * qty;
	}
	
	protected HashMap<String, Item> upc_item_map;
	protected HashMap<Item, Integer> items;
	protected Connection conn;
	protected int grandtotal;
}
