package sale;

public class Item 
{
	protected Item(	String upc, String title, ITEM_TYPE type, GENRE category,
					String comp, String year, int price)
	{
		this.upc = upc;
	}
	
	/*
	 *****************************************
	 * trivial getter methods:
	 *****************************************
	 */
	
	
	final private String upc;
	final private String title;
	final private ITEM_TYPE type;
	final private GENRE category;
	final private String company;
	final private String year; // or GregorianCalendar?
	final private int price_in_cent; // int vs double
	
	public static enum ITEM_TYPE
	{
		CD, DVD
	}
	public static enum GENRE
	{
		POP, ROCK, RAP, COUNTRY, CLASSICAL, NEW_AGE, INSTRUMENTAL
	}
}
