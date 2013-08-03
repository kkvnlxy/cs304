package register;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import util.JDBCConnection;

/**
 * This class is simply a string-comparison purpose "authenticator". It only 
 * goes to the database and see if the given password matches the given cid.
 * The reason I make such a class for a not-need feature is extendibility as 
 * well as "separate of concerns".
 * 
 * @author kevin
 *
 */
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
	 * 						database or connection fails
	 * @throws AuthenException if the given password does not match the password
	 * 							in database
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public Customer authenticate() 
			throws SQLException, AuthenException, ClassNotFoundException, 
				   IOException, FileNotFoundException
	{
		if(this.conn == null)
		//sanity check
			conn = JDBCConnection.getConnection(); 
		
		PreparedStatement stmt = conn.prepareStatement("SELECT * " +
													   "FROM Customer" +
													   "WHERE cid = " + CUST_ID);
		ResultSet result = stmt.executeQuery();
		if(result.next())
		//a tuple is found from database
		{
			String cid = result.getString(Customer.CID_IND);
			String pswd = result.getString(Customer.PSWD_IND);
			String name = result.getString(Customer.NAME_IND);
			String adr = result.getString(Customer.ADRRESS_IND);
			String phone = result.getString(Customer.PHONE_IND);
			
			if(this.PSWD.equals(pswd))
			//passwords matches
				return new Customer(cid, name, adr, phone);
			else
				throw new AuthenException();
		}
		else
			throw new SQLException("Customer ID cannot be found in the " +
								   "database");
	}
	
	final private String CUST_ID;
	final private String PSWD;
	private Connection conn;
}
