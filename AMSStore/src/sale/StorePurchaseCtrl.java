package sale;

import java.sql.SQLException;
import java.util.GregorianCalendar;

public class StorePurchaseCtrl extends TransactionCtrl
{
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
	/**
	 * @author kevin
	 */
	@Override
	public void cancel() 
	{
		this.cur_rcpt_id = null;
		this.items.clear();
	}
	
	/**
	 * Probe the DB for the last not used receiptId in the Purchase table
	 * May not be needed
	 * @post set this.cur_rcpt_id
	 */
//	private void getLastestRcptId()
//	{
//		
//	}

	
	private String cur_rcpt_id;	//the receipt id currently is processing, 
								//may not needed
}
