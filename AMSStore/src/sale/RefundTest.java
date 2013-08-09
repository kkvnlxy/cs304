package sale;

import java.io.IOException;
import java.sql.SQLException;

public class RefundTest 
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
		String pid = args[0];
		String credit = args[1];
		String month = args[2];
		String year = args[3];

		RefundCtrl ctrl = new RefundCtrl(pid);
		if(ctrl.getStatus())
		{
			
		}
		else
			System.out.println("Status = " + ctrl.getStatus());
	}

}
