package sale;

import java.util.GregorianCalendar;

import entity.Item;

public abstract class TransactionCtrl 
{
	public abstract Item addItem(String upc);
	public abstract Receipt process(String card_num, GregorianCalendar exp_date);
	public abstract void cancel();
	
	private HashtMap<Item, int> items;
	private JDBCConnection conn;
}
