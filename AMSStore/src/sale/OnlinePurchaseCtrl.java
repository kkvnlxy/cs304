package sale;

import java.sql.SQLException;
import java.util.GregorianCalendar;

import register.AuthenException;
import register.Customer;

public class OnlinePurchaseCtrl extends TransactionCtrl 
{
//	public OnlinePurchaseCtrl(String cid)
//	{
//		this.cid = cid;
//	}
	
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
	 * This method will authenticate the current purchase transaction to a
	 * specific customer. It requires register.AuthenCtrl object to verify if
	 * the password match the cust_id provided
	 * @param cust_id the customer id
	 * @param password the password
	 * @post this.cur_cust will be set
	 * @throw AuthenException if the password does not match cust_id
	 */
	private void authen(String cust_id, String password)
			throws AuthenException
	{
		
	}
	
	private Customer cur_cust;
//	private String cid;
}
