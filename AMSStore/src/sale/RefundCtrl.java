package sale;

import java.sql.SQLException;
import java.util.GregorianCalendar;

public class RefundCtrl extends TransactionCtrl 
{
	@Override
	public Item addItem(String upc)
			throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Receipt process(String card_num, GregorianCalendar exp_date)
			throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancel() 
	{
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * This method will go to the Purchase table and look at the purchase date,
	 * if the purchase date is within 15 days from today, this refund is 
	 * allowed
	 * @param pid the receipt id of the purchase
	 * @post purc and status will be updated
	 * @return the instance field status
	 */
	public boolean verify(String rid)
	{
		
	}

	private boolean status; //within 15 days?
	private Purchase purc; //the purchase the current refund is refering to
}
