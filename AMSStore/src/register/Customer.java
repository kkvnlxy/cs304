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
}

