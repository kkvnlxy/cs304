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
		this.cust_id = c_id;
		this.name = name;
		this.adr = adr;
		this.phone = phone;
	}
	
	/*
	 *******************************************
	 * trivial getter methods:
	 *******************************************
	 */
	
	final private String cust_id;
	final private String name;
	final private String adr;
	final private String phone;
}
