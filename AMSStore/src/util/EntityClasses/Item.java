package EntityClasses;

import java.lang.EnumConstantNotPresentException;

/**
 * This is the entity class represents the Item in the database. Please note 
 * that in this implementation, we don't have a field for the stock attribute,
 * as well as the songs and singers.
 * @author kevin
 *
 */
public class Item 
{
	private Item(	String upc, String title, ITEM_TYPE type, GENRE category,
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
	final public String getUPC()
	{
		return this.UPC;
	}
	final public String getTitle()
	{
		return this.TITLE;
	}
	final public ITEM_TYPE getType()
	{
		return this.TYPE;
	}
	final public GENRE getCategory()
	{
		return this.CATEGORY;
	}
	final public String getCompany()
	{
		return this.COMPANY;
	}
	final public String getYear()
	{
		return this.YEAR;
	}
	/**
	 * This method returns the price in cents, to obtain the price in dollar,
	 * call getPrice() method instead.
	 * @return the price in cents
	 */
	final public int getPriceInCents()
	{
		return this.PRICE_IN_CENT;
	}
	/**
	 * This method returns the price in dollar, to obtain the price in cents,
	 * call getPriceInCents() instead. 
	 * @return the price in decimal form
	 */
	final public double getPrice()
	{
		return (double)this.PRICE_IN_CENT / (double)100;
	}

	/*
	 * Two helper methods to translate enum into strings 
	 */
	final public static String translateType(ITEM_TYPE type)
	{
		if(type == ITEM_TYPE.CD)
			return "CD";
		else
			return "DVD";
	}
	final public static ITEM_TYPE translateType(String type)
	{
		if(type.equals(TYPE_CD))
			return ITEM_TYPE.CD;
		else if(type.equals(TYPE_DVD))
			return ITEM_TYPE.DVD;
		else
			throw new EnumConstantNotPresentException(ITEM_TYPE.class, type);
	}
	final public static String translateGenre(GENRE category)
	{
		if(category == GENRE.ROCK)
			return "ROCK";
		else if(category == GENRE.CLASSICAL)
			return "CLASSICAL";
		else if(category == GENRE.COUNTRY)
			return "COUNTRY";
		else if(category == GENRE.INSTRUMENTAL)
			return "INSTRUMENTAL";
		else if(category == GENRE.NEW_AGE)
			return "NEW_AGE";
		else if(category == GENRE.POP)
			return "POP";
		else
			return "RAP";
	}
	final public static GENRE translateGenre(String category)
	{
		if(category.equals(Item.GENRE_CLASSICAL))
			return GENRE.CLASSICAL;
		else if(category.equals(Item.GENRE_COUNTRY))
			return GENRE.COUNTRY;
		else if(category.equals(Item.GENRE_INST))
			return GENRE.INSTRUMENTAL;
		else if(category.equals(Item.GENRE_NEW_AGE))
			return GENRE.NEW_AGE;
		else if(category.equals(Item.GENRE_POP))
			return GENRE.POP;
		else if(category.equals(Item.GENRE_RAP))
			return GENRE.RAP;
		else if(category.equals(Item.GENRE_ROCK))
			return GENRE.ROCK;
		else
			throw new EnumConstantNotPresentException(GENRE.class, category);
	}
	
	//enumerators:
	public static enum ITEM_TYPE
	{
		CD, DVD
	}
	public static enum GENRE
	{
		POP, ROCK, RAP, COUNTRY, CLASSICAL, NEW_AGE, INSTRUMENTAL
	}
	
	final private String UPC;
	final private String TITLE;
	final private ITEM_TYPE TYPE;
	final private GENRE CATEGORY;
	final private String COMPANY;
	final private String YEAR; // or GregorianCalendar?
	final private int PRICE_IN_CENT; // int vs double?
	
	//static constant for attribute indexing.
	//ONLY USE THESE WHEN YOU ARE ISSUING "SELECT * FROM Item" SQL
	static public final int UPC_IND = 1;
	static public final int TITLE_IND = 2;
	static public final int TYPE_IND = 3;
	static public final int CATEGORY_IND = 4;
	static public final int COMPANY_IND = 5;
	static public final int YEAR_IND = 6;
	static public final int PRICE_IND = 7;
	static public final int STOCK_IND = 8;
	
	//the following is only for enum translation between app code and database
	static public final String TYPE_CD = "CD";
	static public final String TYPE_DVD = "DVD";
	static public final String GENRE_ROCK = "ROCK";
	static public final String GENRE_POP = "POP";
	static public final String GENRE_RAP = "RAP";
	static public final String GENRE_COUNTRY = "COUNTRY";
	static public final String GENRE_CLASSICAL = "CLASSICAL";
	static public final String GENRE_NEW_AGE = "NEW_AGE";
	static public final String GENRE_INST = "INSTRUMENTAL";
}
