package sale;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;

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
	 * @return the "entity" object Item represented by the upc
	 * @throws SQLException there is no tuple in the Item table with that upc
	 */
	public abstract Item addItem(String upc) 
			throws SQLException;
	
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
	
	private HashMap<Item, Integer> items;
	private Connection conn;
}
