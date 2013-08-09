	package management;



	import java.util.HashMap;

	import java.util.Map;



	public class TopNReport

	{

		protected HashMap<ReportItem, Integer> row;

		

		protected TopNReport(){

			Map<ReportItem, Integer> row = new HashMap<ReportItem, Integer>();

		}

		

		protected void insertRow(String upc, String title, String company, int stock, int totalUnits){

			ReportItem rp = new ReportItem (upc, title, company, stock);

			row.put(rp, totalUnits);

		}

		

		private class ReportItem {

			final private String UPC;

			final private String TITLE;

			final private String COMPANY;

			final private int STOCK;

			

			private ReportItem(String upc, String title, String company, int stock){

				this.UPC = upc;

				this.TITLE = title; 

				this.COMPANY = company;

				this.STOCK = stock;

			}



			private String getUPC() {

				return UPC;

			}



			private String getTITLE() {

				return TITLE;

			}



			private String getCOMPANY() {

				return COMPANY;

			}



			private int getSTOCK() {

				return STOCK;

			}

		}

	}