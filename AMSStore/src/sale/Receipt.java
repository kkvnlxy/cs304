package sale;
import java.util.GregorianCalendar;

/**
 * Superclass for Purchase and Return.
 * Getter methods are final which not allow subclass to override.
 * Constructor of all subclass should be protected too since only the control 
 * objects of this package should be allowed to create these object
 * @author kevin
 *
 */
public class Receipt 
{
	protected Receipt(String r_id, GregorianCalendar date)
	{
		this.rcpt_id = r_id;
		this.trans_date = date;
	}
	
	/*
	 ***************************************************
	 * Trivial getter methods:
	 ***************************************************
	 */
	final public String getRcptId()
	{
		return this.rcpt_id;
	}
	final public String getDateString()
	{
		return trans_date.toString();
	}
	final public GregorianCalendar getDate()
	{
		return this.trans_date;
	}
	
	private final String rcpt_id;
	private final GregorianCalendar trans_date;
}
