package sale;

import java.util.GregorianCalendar;

/**
 * Entity class representing a Purchase tuple from the database.
 * @author kevin
 *
 */
public class Purchase extends Receipt
{
	/**
	 * This constructor fully constructs, but I don't think there will be many
	 * occasions that this constructor will be called, since not all parameters
	 * may be known.
	 * If the calling method cannot provide sufficient parameters, you may think
	 * of using other constructors instead.
	 * @param r_id receipt id retrieved from the database
	 * @param pur_date should always be set to today
	 * @param c_id customer id
	 * @param card_num credit card number
	 * @param expr_date expirary date of the credit card
	 * @param del_date deliver date
	 */
	protected Purchase(	String r_id, GregorianCalendar pur_date, String c_id, 
						String card_num, GregorianCalendar expr_date, 
						GregorianCalendar expt_date, GregorianCalendar del_date)
	{
		super(r_id, pur_date);
		this.CUST_ID = c_id;
		this.CARD_NUM = card_num;
		this.EXPR_DATE = expr_date;
		this.EXPT_DATE = expt_date;
		this.DEL_DATE = del_date;
	}
	/**
	 * This constructor is for in store purchase with cash. The calling method
	 * may find using the first constructor easier. It's up to your choice.
	 * @param r_id
	 * @param pur_date
	 */
	protected Purchase(String r_id, GregorianCalendar pur_date)
	{
		super(r_id, pur_date);
		this.CUST_ID = null;
		this.CARD_NUM = null;
		this.EXPR_DATE = null;
		this.EXPT_DATE = null;
		this.DEL_DATE = null;
	}
	/**
	 * This constructor is for in store purchase with credit card. The calling 
	 * method may find using the first constructor easier. It's up to your 
	 * choice.
	 * @param r_id
	 * @param pur_date
	 * @param card_num
	 * @param expr_date
	 */
	protected Purchase(String r_id, GregorianCalendar pur_date, String card_num,
					   GregorianCalendar expr_date)
	{
		super(r_id, pur_date);
		this.CUST_ID = null;
		this.CARD_NUM = card_num;
		this.EXPR_DATE = expr_date;
		this.EXPT_DATE = null;
		this.DEL_DATE = null;
	}
	/**
	 * This constructor is for online purchase. The calling method may find 
	 * using the first constructor easier. It's up to your choice.
	 * @param r_id
	 * @param pur_date
	 * @param card_num
	 * @param expr_date
	 */
	protected Purchase(	String r_id, GregorianCalendar pur_date, String cust_id,
						String card_num, GregorianCalendar expr_date, 
						GregorianCalendar expt_date)
	{
		super(r_id, pur_date);
		this.CUST_ID = cust_id;
		this.CARD_NUM = card_num;
		this.EXPR_DATE = expr_date;
		this.EXPT_DATE = expt_date;
		this.DEL_DATE = null;
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
	final public GregorianCalendar getExprDate()
	{
		return this.EXPR_DATE;
	}
	final public String getExprDateString()
	{
		return this.EXPR_DATE.toString();
	}
	final public GregorianCalendar getExptDate()
	{
		return this.EXPT_DATE;
	}
	final public String getExptDateString()
	{
		return this.EXPT_DATE.toString();
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
	private final GregorianCalendar EXPR_DATE;
	private final GregorianCalendar EXPT_DATE;
	private final GregorianCalendar DEL_DATE;
	
	public static final int RID_IND = 1;
	public static final int PDATE_IND = 2;
	public static final int CID_IND = 3;
	public static final int CARDNUM_IND = 4;
	public static final int EXPRDATE_IND = 5;
	public static final int EXPTDATE_IND = 6;
	public static final int DELDATE_IND = 7;
}
