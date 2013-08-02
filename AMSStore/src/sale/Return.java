package sale;

import java.util.GregorianCalendar;


public class Return extends Receipt 
{
	protected Return(String rcpt_id, GregorianCalendar ref_date, String ref_id)
	{
		super(rcpt_id, ref_date);
		this.ref_id = ref_id;
	}
	
	/*
	 ******************************************
	 * trivial getter methods:
	 ****************************************** 
	 */
	
	final private String ref_id;
}
