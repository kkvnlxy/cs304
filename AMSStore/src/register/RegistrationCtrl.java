package register;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import util.JDBCConnection;

public class RegistrationCtrl
{
	public boolean register(String cid, String pswd, String name, String adr,
							String phone) 
			throws ClassNotFoundException, IOException, SQLException
	{
		if(conn == null)
			conn = JDBCConnection.getConnection();
		
		PreparedStatement stmt = conn.prepareStatement(
										"INSERT INTO Customer " +
										"VALUES(?, ?, ?, ?, ?)");
		stmt.setString(1, cid);
		stmt.setString(2, pswd);
		stmt.setString(3, name);
		stmt.setString(4, adr);
		stmt.setString(5, phone);
		
		try
		{
			if(stmt.executeUpdate() == 1)
			//sanity check, usually will return 1
				return true;
			return false;
		}
		catch(SQLException expt)
		//insert unseccessful due to database problem or a customer with same
		//id exist
		{
			return false;
		}
		finally
		{
			stmt.close();
		}
	}
	
	private Connection conn;
}
