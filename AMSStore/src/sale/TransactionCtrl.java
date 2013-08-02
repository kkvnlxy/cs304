package sale;

import java.util.GregorianCalendar;
import java.util.HashMap;

public abstract class TransactionCtrl 
{
	public abstract Item addItem(String upc);
	public abstract Receipt process(String card_num, GregorianCalendar exp_date);
	public abstract void cancel();
	
	private HashMap<Item, Integer> items;
	private JDBCConnection conn;
}
