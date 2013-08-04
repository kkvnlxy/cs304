package sale;

import java.util.GregorianCalendar;

/**
 * 
 * @author kevin
 *
 */
public class Return extends Receipt 
{
	/**
	 * Constructor for this class
	 * @param rcpt_id
	 * @param ref_date
	 * @param ref_id return id in the refund table
	 */
	protected Return(String rcpt_id, GregorianCalendar ref_date, String ref_id)
	{
		super(rcpt_id, ref_date);
		this.REF_ID = ref_id;
	}
	
	/*
	 ******************************************
	 * trivial getter methods:
	 ****************************************** 
	 */
	public String getRefundID()
	{
		return this.REF_ID;
	}
	
	final private String REF_ID;
}
