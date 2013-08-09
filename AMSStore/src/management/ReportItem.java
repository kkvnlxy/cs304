package management;

public class ReportItem 
{
	final private String UPC;
	final private String CATEGORY;
	final private double UNIT_PRICE;
	final private int UNITS;
	final private double TOTAL_SALE;
	
	protected ReportItem(String upc, String cat, double price, int qty, 
					   double total)
	{
		this.UPC = upc;
		this.CATEGORY = cat;
		this.UNIT_PRICE = price;
		this.UNITS = qty;
		this.TOTAL_SALE = total;
	}
	
	final public String getUPC()
	{
		return this.UPC;
	}
	final public String getCategory()
	{
		return this.CATEGORY;
	}
	final public double getUnitPrices()
	{
		return this.UNIT_PRICE;
	}
	final public int getUnits()
	{
		return this.UNITS;
	}
	final public double getTotalSale()
	{
		return this.TOTAL_SALE;
	}
}