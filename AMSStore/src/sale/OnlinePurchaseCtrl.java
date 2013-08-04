package sale;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import register.AuthenCtrl;
import register.AuthenException;
import register.Customer;

public class OnlinePurchaseCtrl extends TransactionCtrl 
{
//	public OnlinePurchaseCtrl(String cid)
//	{
//		this.cid = cid;
//	}
	
	/*
	 **********************************************
	 * inheriting abstract methods:
	 **********************************************
	 */
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
		this.cur_cust = null;
		this.items.clear();
	}
	
	/**
	 * This method will authenticate the current purchase transaction to a
	 * specific customer. It requires register.AuthenCtrl object to verify if
	 * the password match the cust_id provided
	 * @param cust_id the customer id
	 * @param password the password
	 * @throws IOException 
	 * @throws FileNotFoundException 
	 * @throws ClassNotFoundException 
	 * @post this.cur_cust will be set
	 * @throw AuthenException if the password does not match cust_id
	 * @throw SQLException if given cid is not found in the database
	 */
	public void authen(String cid, String pswd)
			throws SQLException, AuthenException, ClassNotFoundException, 
					FileNotFoundException, IOException
	{
		cur_cust = new AuthenCtrl(cid, pswd).authenticate();
		//TODO need further operation??
	}
	
	private Customer cur_cust;
//	private String cid;
}
