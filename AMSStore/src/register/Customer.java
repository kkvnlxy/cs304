package register;

public class Customer 
{
	/**
	 * Protected constructor restricts the creation of such entity object
	 * @param c_id
	 * @param name
	 * @param adr
	 * @param phone
	 */
	protected Customer(String c_id, String name, String adr, String phone)
	{
		this.CUST_ID = c_id;
		this.NAME = name;
		this.ADR = adr;
		this.PHONE = phone;
	}
	
	/*
	 *******************************************
	 * trivial getter methods:
	 *******************************************
	 */
	
	final private String CUST_ID;
	final private String NAME;
	final private String ADR;
	final private String PHONE;
	// pswd is not present because we don't need it
}
