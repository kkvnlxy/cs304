package management;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;



public class TopNReport
{
	protected ArrayList<TopNReportItem> row;
		
	protected TopNReport()
	{
		this.row = new ArrayList<TopNReportItem>();
	}

	protected void insertRow(String upc, String title, String company, 
							 int stock, int totalUnits)
	{
		TopNReportItem rp = new TopNReportItem (upc, title, company, stock, 
												totalUnits);
		row.add(rp);
	}
	
	public ArrayList<TopNReportItem> getList()
	{
		return this.row;
	}
	

	protected class TopNReportItem 
	{
		final private String UPC;
		final private String TITLE;
		final private String COMPANY;
		final private int STOCK;
		final private int SOLD;

		private TopNReportItem(String upc, String title, String company, int stock,
						   int sold)
		{
			this.UPC = upc;
			this.TITLE = title; 
			this.COMPANY = company;
			this.STOCK = stock;
			this.SOLD = sold;
		}
		
		public String getUPC() 
		{
			return UPC;
		}

		public String getTITLE() 
		{
			return TITLE;
		}

		public String getCOMPANY() 
		{
			return COMPANY;
		}

		public int getSTOCK() 
		{
			return STOCK;

		}
		
		public int getQtySold()
		{
			return this.SOLD;
		}
	}

}