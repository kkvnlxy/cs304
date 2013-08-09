package management;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import management.TopNReport;
import management.TopNReport.TopNReportItem;

public class TopNReportTest 
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
		int year = Integer.parseInt(args[0]);
		int month = Integer.parseInt(args[1]);
		int day = Integer.parseInt(args[2]);
		int n = Integer.parseInt(args[3]);
		
		InventoryCtrl ctrl = new InventoryCtrl();
		
		TopNReport report = ctrl.genTopNReport(
									new GregorianCalendar(year, month, day), n);
		
		ArrayList<TopNReportItem> rep_lst = report.getList();
		
		if(rep_lst.size() == 0)
		{
			System.out.println("empty report");
			return;
		}
		
		for(int row = 0; row < rep_lst.size(); row++)
		{
			TopNReportItem cur_item = rep_lst.get(row);
			System.out.println(	cur_item.getUPC() + "\t" + 
								cur_item.getTITLE() + "\t" +
								cur_item.getCOMPANY() + "\t" +
								cur_item.getSTOCK() + "\t" +
								cur_item.getQtySold());
		}
	}

}
