package sale;

import java.util.GregorianCalendar;


/**
 * Entity class representing a Purchase tuple from the database.
 * @author kevin
 *
 */
public class Purchase extends Receipt
{
	protected Purchase(	String r_id, GregorianCalendar pur_date, String c_id, 
						String card_num, GregorianCalendar exp_date, 
						GregorianCalendar del_date)
	{
		super(r_id, pur_date);
		this.CUST_ID = c_id;
		this.CARD_NUM = card_num;
		this.EXP_DATE = exp_date;
		this.DEL_DATE = del_date;
	}
	
	/*
	 ***************************************************
	 * Trivial getter methods:
	 ***************************************************
	 */
	final public String getCustId()
	{
		return this.CUST_ID;
	}
	final public String getCardNum()
	{
		return this.CARD_NUM;
	}
	final public GregorianCalendar getExpDate()
	{
		return this.EXP_DATE;
	}
	final public String getExpDateString()
	{
		return this.EXP_DATE.toString();
	}
	final public GregorianCalendar getDelDate()
	{
		return this.DEL_DATE;
	}
	final public String getDelDateString()
	{
		return this.DEL_DATE.toString();
	}

	private final String CUST_ID;
	private final String CARD_NUM;
	private final GregorianCalendar EXP_DATE;
	private final GregorianCalendar DEL_DATE;
}
