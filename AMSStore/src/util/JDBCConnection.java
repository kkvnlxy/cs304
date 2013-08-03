package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JDBCConnection 
{
	/**
	 * Initialization for this singleton class
	 * This constructor is going to throw 3 kinds of exceptions; however, one 
	 * of them inherits another. Therefore, to give meaningful error message, 
	 * please catch exceptions carefully.
	 * @throws FileNotFoundException if the config file is missing
	 * @throws IOException if config file failed to be parsed
	 * @throws SQLException if fails to connect to the database
	 * @throws ClassNotFoundException database driver is not found in the 
	 * 								  system
	 */
	private static void init()
			throws IOException, SQLException, ClassNotFoundException
	{
		FileInputStream file = new FileInputStream(PATH);
		Properties prpt = new Properties();
		prpt.load(file);
		
		//retrieve the properties
		String driver = prpt.getProperty("jdbc.driver");
		if(driver == null)
		//santiy check
		{
			throw new IOException("Driver is not specified.");
		}
		String url = prpt.getProperty("jdbc.url");		
		if(url == null)
		{
			throw new IOException("URL is not specified in the configuration " +
								  "file.");
		}
		String user = prpt.getProperty("jdbc.username");
		if(user == null)
		{
			user = "";
		}
		String pswd = prpt.getProperty("jdbc.password");
		if(pswd == null)
		{
			pswd = "";
		}
		
		Class.forName(driver);
		connection = DriverManager.getConnection(url, user, pswd);
	}
	
	public static Connection getConnection() 
			throws IOException, SQLException, ClassNotFoundException
	{
		if(connection == null)
			init();
		return connection;
	}
	
	private static Connection connection = null;
	private final static String PATH = "config"; 
}
