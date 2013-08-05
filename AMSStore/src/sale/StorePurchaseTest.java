package sale;

import java.io.IOException;
import java.sql.SQLException;

public class StorePurchaseTest 
{
	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args)
			throws NumberFormatException, ClassNotFoundException, SQLException, IOException 
	{
		StorePurchaseCtrl ctrl = new StorePurchaseCtrl();
		
		for(int row = 0; row < args.length; row += 2)
		{
			System.out.println("ADDING: "+ args[row] + " with " + args[row + 1]);
			ctrl.addItem(args[row], Integer.parseInt(args[row + 1]));
		}
		
		Receipt receipt = ctrl.process("", null);
		System.out.println(receipt.getRcptId());
		ctrl.cancel();
	}

}
