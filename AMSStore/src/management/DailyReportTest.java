package management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import util.JDBCConnection;

public class DailyReportTest
{

	/**
	 * @param args
	 * @throws IOException 
	 * @throws SQLException 
	 * @throws ClassNotFoundException 
	 */
	public static void main(String[] args) 
			throws ClassNotFoundException, SQLException, IOException 
	{
		InventoryCtrl ctrl = new InventoryCtrl();
		
		DailyReport report = ctrl.genDailyReport(
								new GregorianCalendar(Integer.parseInt(args[0]),
													  Integer.parseInt(args[1]),
													  Integer.parseInt(args[2])));
		
		for(int row = 0; row < report.getNumOfCat(); row++)
		{
			ArrayList<ReportItem> cat_lst = report.getCategoryItemList(row);
			
			for(int col = 0; col < cat_lst.size(); col++)
			{
				ReportItem item = cat_lst.get(col);
				System.out.println(item.getUPC() + "\t" + 
								   item.getCategory() + "\t" + 
								   item.getUnitPrices() + "\t" + 
								   item.getUnits() + "\t" + 
								   item.getTotalSale());
			}
			
			System.out.println(report.getCategoryName(row) + "\t" + 
							   report.getCategoryTotal(row));
		}
	}
}
