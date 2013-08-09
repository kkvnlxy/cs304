package management;

import java.util.ArrayList;

public class DailyReport
{	
	private ArrayList<Triple<String, ArrayList<ReportItem>, Double>> report;
	
	protected DailyReport(
				ArrayList<Triple<String, ArrayList<ReportItem>, Double>> report)
	{
		this.report = report;
	}
	
	public int getNumOfCat()
	{
		return this.report.size();
	}
	
	public String getCategoryName(int row)
	{
		return this.report.get(row).getS();
	}
	
	public double getCategoryTotal(int row)
	{
		return this.report.get(row).getD();
	}
	
	public ArrayList<ReportItem> getCategoryItemList(int row)
	{
		return this.report.get(row).getL();
	}
	
	public boolean isEmpty()
	{
		return report.isEmpty();
	}
}
