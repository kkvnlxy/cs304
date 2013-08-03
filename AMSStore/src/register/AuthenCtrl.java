package register;

import java.sql.SQLException;

import util.JDBCConnection;

public class AuthenCtrl
{
	public AuthenCtrl(String cid, String pswd)
	{
		this.CUST_ID = cid;
		this.PSWD = pswd;
	}
	
	/**
	 * Method that authen a given customer id and password, needs to access the
	 * Customer table
	 * @param CUST_ID the given customer id wanted to be authenticated
	 * @param PSWD the given password associate with the customer id
	 * @return a Customer entity object will be returned on success 
	 * @throws SQLException if the given customer id is not present in the 
	 * 						database
	 * @throws AuthenException if the given password does not match the password
	 * 							in database
	 */
	public Customer authen() 
			throws SQLException, AuthenException
	{
		// TODO auto-gen stub
		return null;
	}
	
	final private String CUST_ID;
	final private String PSWD;
	private JDBCConnection conn;
}
