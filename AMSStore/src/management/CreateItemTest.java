package management;

import java.io.IOException;
import java.sql.SQLException;

import sale.Item;

public class CreateItemTest 
{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 * @throws NumberFormatException 
	 */
	public static void main(String[] args) 
			throws NumberFormatException, ClassNotFoundException, SQLException, 
				   IOException 
	{
		for(int row = 0; row < args.length; row++)
			System.out.println(args[row]);

		InventoryCtrl invnt = new InventoryCtrl();
		invnt.createItem(args[0], 
						 args[1], 
						 Item.translateType(args[2]), 
						 Item.translateGenre(args[3]), 
						 args[4], 
						 args[5], 
						 Integer.parseInt(args[6]), 
						 Integer.parseInt(args[7]));
		System.out.println("added successful.");
	}

}
