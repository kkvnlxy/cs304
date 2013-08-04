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
		this.CID = c_id;
		this.NAME = name;
		this.ADDRESS = adr;
		this.PHONE = phone;
	}
	
	/*
	 *******************************************
	 * trivial getter methods:
	 *******************************************
	 */
	final public String getCustomerID()
	{
		return this.CID;
	}
	final public String getName()
	{
		return this.NAME;
	}
	final public String getAddress()
	{
		return this.ADDRESS;
	}
	final public String getPhone()
	{
		return this.PHONE;
	}
	
	final private String CID;
	//pswd is not present because we don't need it
	final private String NAME;
	final private String ADDRESS;
	final private String PHONE;
	
	//static field for ResultSet indexing of Customer tuple.
	//USE THESE INDEXES ONLY WHEN "SELECT *"
	final static protected int CID_IND = 1;
	final static protected int PSWD_IND = 2;
	final static protected int NAME_IND = 2;
	final static protected int ADRRESS_IND = 2;
	final static protected int PHONE_IND = 2;
	//or instead using these:
	final static protected String CID_LAB = "cid";
	final static protected String PSWD_LAB = "pswd";
	final static protected String NAME_LAB = "name";
	final static protected String ADDRESS_LAB = "address";
	final static protected String PHONE_LAB = "phone";
}

