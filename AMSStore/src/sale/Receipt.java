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
		this.RCPT_ID = r_id;
		this.TRANS_DATE = date;
	}
	
	/*
	 ***************************************************
	 * Trivial getter methods:
	 ***************************************************
	 */
	final public String getRcptId()
	{
		return this.RCPT_ID;
	}
	final public String getDateString()
	{
		return TRANS_DATE.toString();
	}
	final public GregorianCalendar getDate()
	{
		return this.TRANS_DATE;
	}
	
	private final String RCPT_ID;
	private final GregorianCalendar TRANS_DATE;
}
