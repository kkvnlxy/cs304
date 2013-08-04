package management;

import java.io.IOException;
import java.sql.SQLException;

public class StockItemTest
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
		for(int row = 0; row < args.length; row++)
			System.out.println(args[row]);
		
		InventoryCtrl invt = new InventoryCtrl();
		
		if(args.length == 2)
			invt.stockItem(args[0], Integer.parseInt(args[1]));
		else if(args.length == 3)
			invt.stockItemPrice(args[0], Integer.parseInt(args[1]), 
								Integer.parseInt(args[2]));
	}

}
