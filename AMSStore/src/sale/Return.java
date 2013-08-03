package sale;

import java.util.GregorianCalendar;


public class Return extends Receipt 
{
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
	
	final private String REF_ID;
}
