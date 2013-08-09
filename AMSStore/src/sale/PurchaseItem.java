package sale;

public class PurchaseItem extends Item
{
	protected PurchaseItem(String r_id, String upc, int qty, String title,
						   ITEM_TYPE type, GENRE category, String comp, 
						   String year, int price_incents, int stock)
	{
		super(upc, title, type, category, comp, year, price_incents, stock);
		this.RCPT_ID = r_id;
		this.QUANTITY = qty;
	}
	
	/*
	 *****************
	 * trivial getters
	 *****************
	 */
	final public String getReceiptID()
	{
		return this.RCPT_ID;
	}
	final public int getQuantity()
	{
		return this.QUANTITY;
	}
	
	final private String RCPT_ID;
	final private int QUANTITY;
}
