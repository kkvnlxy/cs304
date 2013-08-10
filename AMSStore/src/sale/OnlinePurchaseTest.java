package sale;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;

public class OnlinePurchaseTest 
{

	/**
	 * @param args
	 * @throws Exception 
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) throws NumberFormatException, ClassNotFoundException, SQLException, IOException, Exception 
	{
		final String cust = "kkvnlxy";
		final String pswd = "1234";
		OnlinePurchaseCtrl ctrl = new OnlinePurchaseCtrl();
		
		ctrl.authen(cust, pswd);
		
		for(int row = 0; row < args.length; row += 2)
			ctrl.addItem(args[row], Integer.parseInt(args[row + 1]));
		
		Receipt recpt = ctrl.process("1234567890123456", 
									 new GregorianCalendar(2018, 7, 01));
		System.out.println(recpt.getRcptId());
		ctrl.cancel();
	}
}
