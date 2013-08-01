package sale;

import java.util.GregorianCalendar;


/**
 * Entity class representing a Purchase tuple from the database.
 * @author kevin
 *
 */
public class Purchase extends Receipt
{
	protected Purchase(String r_id, GregorianCalendar pur_date, String c_id, 
					String card_num, GregorianCalendar exp_date, 
					GregorianCalendar del_date)
	{
		super(r_id, pur_date);
		this.cust_id = c_id;
		this.card_num = card_num;
		this.exp_date = exp_date;
		this.del_date = del_date;
	}
	
	/*
	 ***************************************************
	 * Trivial getter methods:
	 ***************************************************
	 */
	final public String getCustId()
	{
		return this.cust_id;
	}
	final public String getCardNum()
	{
		return this.card_num;
	}
	final public GregorianCalendar getExpDate()
	{
		return this.exp_date;
	}
	final public String getExpDateString()
	{
		return this.exp_date.toString();
	}
	final public GregorianCalendar getDelDate()
	{
		return this.del_date;
	}
	final public String getDelDateString()
	{
		return this.del_date.toString();
	}
	
	private final String cust_id;
	private final String card_num;
	private final GregorianCalendar exp_date;
	private final GregorianCalendar del_date;
}
