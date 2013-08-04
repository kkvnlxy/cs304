package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCConnTester 
{

	/**
	 * @param args
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws SQLException 
	{
		Connection conn = null;
		try
		{
			conn = JDBCConnection.getConnection();
			
			String command = "INSERT INTO Customer VALUES('ang', '2234', 'Angela Wei', 'Richmond', '778-213-1245')";
			System.out.println(command);
			Statement stmt = conn.createStatement();
			stmt.execute(command);
			stmt.close();
		}
		catch(FileNotFoundException expt)
		{
			System.out.println("File not found.");
			System.out.println(System.getProperty("user.dir"));
			System.out.println(expt.getMessage());
		}
		catch(IOException expt)
		{
			System.out.println("File parsing error.");
			System.out.println(expt.getMessage());
		}
		catch(SQLException expt)
		{
			System.out.println("Connection fails.");
			System.out.println(expt.getMessage());
		}
		catch(ClassNotFoundException expt)
		{
			System.out.println("Driver not found.");
			System.out.println(expt.getMessage());
		}
		finally
		{
			conn.close();
			System.out.println("closed.");
		}
	}

}
