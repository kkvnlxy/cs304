package register;

import java.io.IOException;
import java.sql.SQLException;

public class RegistrationTest
{

	/**
	 * @param args
	 * @throws SQLException 
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) 
			throws ClassNotFoundException, IOException, SQLException 
	{
		RegistrationCtrl register = new RegistrationCtrl();
		
		for(int row = 0; row < args.length; row++)
			System.out.println(args[row]);
		
		if(register.register(args[0], args[1], args[2], args[3], args[4]))
			System.out.println("register ok.");
		else
			System.out.println("register unseccesful");
	}

}
