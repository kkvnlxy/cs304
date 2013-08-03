package sale;

public class Item 
{
	protected Item(	String upc, String title, ITEM_TYPE type, GENRE category,
					String comp, String year, int price)
	{
		this.UPC = upc;
		this.TITLE = title;
		this.TYPE = type;
		this.CATEGORY = category;
		this.COMPANY = comp;
		this.YEAR = year;
		this.PRICE_IN_CENT = price;
	}
	
	/*
	 *****************************************
	 * trivial getter methods:
	 *****************************************
	 */
	
	
	final private String UPC;
	final private String TITLE;
	final private ITEM_TYPE TYPE;
	final private GENRE CATEGORY;
	final private String COMPANY;
	final private String YEAR; // or GregorianCalendar?
	final private int PRICE_IN_CENT; // int vs double
	
	public static enum ITEM_TYPE
	{
		CD, DVD
	}
	public static enum GENRE
	{
		POP, ROCK, RAP, COUNTRY, CLASSICAL, NEW_AGE, INSTRUMENTAL
	}
}
