package sale;

import java.io.IOException;
import java.sql.SQLException;
import java.util.GregorianCalendar;

import sale.Receipt.PAYMENT_METHOD;

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
		String credit = args[1].equals("null") ? "" : args[1];
		String month = credit.equals("") ? "" : args[2];
		String year = credit.equals("") ? "" : args[3];
		System.out.println("pid = " + pid + "\n" +
						   "credit = " + credit + "\n" +
						   "month = " + month + "\n" +
						   "year = " + year + "\n");

		RefundCtrl ctrl = new RefundCtrl(pid);
		if(ctrl.getStatus())
		{
			for(int row = 4; row < args.length; row += 2)
				//returned object not caught
				ctrl.addItem(args[row], Integer.parseInt(args[row + 1]));
			
			Receipt rcpt = ctrl.process(credit, 
										new GregorianCalendar(Integer.parseInt(year), 
															  Integer.parseInt(month), 
															  1));
			
			if(rcpt != null)
				System.out.println(rcpt.getRcptId());
			else
				System.out.println("fail");
		}
		else
			System.out.println("Status = " + ctrl.getStatus());
	}

}
