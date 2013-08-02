package sale;

import java.sql.SQLException;
import java.util.GregorianCalendar;

public class StorePurchaseCtrl extends TransactionCtrl
{
	public Item addItem(String upc) 
			throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public Receipt process(String card_num, GregorianCalendar exp_date) 
			throws SQLException
	{
		// TODO Auto-generated method stub
		return null;
	}

	public void cancel() 
	{
		// TODO Auto-generated method stub

	}
	
	/**
	 * Probe the DB for the last not used receiptId in the Purchase table
	 * May not be needed
	 * @post set this.cur_rcpt_id
	 */
	private void getLastestRcptId()
	{
		
	}

	
	private String cur_rcpt_id;	//the receipt id currently is processing, 
								//may not needed
}
