package sale;

import java.sql.SQLException;
import java.util.GregorianCalendar;
import java.util.HashMap;

public abstract class TransactionCtrl 
{
	public abstract Item addItem(String upc) 
			throws SQLException;
	public abstract Receipt process(String card_num, GregorianCalendar exp_date)
			throws SQLException;
	public abstract void cancel();
	
	private HashMap<Item, Integer> items;
	private JDBCConnection conn;
}
