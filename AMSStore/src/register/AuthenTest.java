package register;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;

public class AuthenTest 
{

	/**
	 * @param args
	 */
	public static void main(String[] args) 
	{
		String id = args[0];
		System.out.println("given id = " + id);
		String password = args[1];
		System.out.println("given pwd = " + password);

		AuthenCtrl authenticator = new AuthenCtrl(id, password);
		try 
		{
			Customer cust = authenticator.authenticate();
			System.out.println(cust.getCustomerID());
		} 
		catch (ClassNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (FileNotFoundException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (AuthenException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		catch (IOException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
