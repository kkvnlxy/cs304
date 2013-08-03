package util;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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
			
			String command = "DROP TABLE Publishers";
			conn.createStatement().execute(command);
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
