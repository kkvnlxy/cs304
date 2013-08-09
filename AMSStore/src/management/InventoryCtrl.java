package management;



import java.io.IOException;

import java.sql.Connection;

import java.sql.Date;

import java.sql.PreparedStatement;

import java.sql.ResultSet;

import java.sql.SQLException;

import java.util.ArrayList;
import java.util.Calendar;

import java.util.GregorianCalendar;




import sale.Item;

import sale.Item.GENRE;

import sale.Item.ITEM_TYPE;

import util.JDBCConnection;



/**

 * Control object class for management package

 * @creator kevin

 *

 */

public class InventoryCtrl 

{

	/**

	 * This method insert a new Item tuple into the Item table

	 * @param upc

	 * @param title

	 * @param type

	 * @param category

	 * @param company

	 * @param year

	 * @param price_incent

	 * @param initStk

	 * @throws SQLException if there is already a tuple with the same upc exists

	 * @throws IOException 

	 * @throws ClassNotFoundException 

	 */

	public void createItem(	String upc, String title, ITEM_TYPE type, 

			GENRE category, String company, String year, 

			int price_incent, int initStk)

					throws SQLException, ClassNotFoundException, IOException

					{

		if(this.conn == null)

			this.conn = JDBCConnection.getConnection();








		PreparedStatement stmt = conn.prepareStatement(

				"INSERT INTO Item " +

				"VALUES(?, ?, ?, ?, ?, ?, ?, ?)");

		stmt.setString(1, upc);

		stmt.setString(2, title);

		stmt.setString(3, Item.translateType(type));

		stmt.setString(4, Item.translateGenre(category));

		stmt.setString(5, company);

		stmt.setString(6, year);

		stmt.setDouble(7, (double)price_incent / 100.0);//TODO setDouble/setFloat?

		stmt.setInt(8, initStk);



		try

		{

			int count = stmt.executeUpdate();

		}

		finally

		{

			stmt.close();

		}

					}



	/**

	 * This method updates (increments) the stock attribute in the Item table

	 * @param upc

	 * @param qty quantity to INCREMENT

	 * @throws SQLException if there is no tuple with the given upc is found in

	 * 						the database

	 * @throws IOException 

	 * @throws ClassNotFoundException 

	 */

	public void stockItem(String upc, int qty) 

			throws SQLException, ClassNotFoundException, IOException

			{

		if(qty < 0 )

			//sanity check

			throw new IOException("Quantity input cannot be less than 0.");



		if(this.conn == null)

			this.conn = JDBCConnection.getConnection();



		PreparedStatement stmt = conn.prepareStatement(

				"UPDATE Item " +

						"SET stock = stock + ? " +

				"WHERE upc = ? ");

		stmt.setInt(1, qty);

		stmt.setString(2, upc);

		try

		{

			int update = stmt.executeUpdate();

			if(update == 1)

				return;

			else if(update == 0)

				//if none is update

				throw new SQLException("The Item with upc " + upc + " does " +

						"not exist.");

			else

				//Fatal error: more than 1 tuple is updated -> duplicate upc!!!

				throw new SQLException("Fatal Error: Duplicate UPC!");

		}

		finally

		{

			stmt.close();

		}

			}



	/**

	 * This method increments the stock attribute in the Item table as well as

	 * modify the price attribute

	 * @param upc

	 * @param qty quantity to be increment

	 * @param price_incent price to be overwrite

	 * @throws SQLException if no tuple with the given upc can be found in the

	 * 						database

	 * @throws IOException 

	 * @throws ClassNotFoundException 

	 */

	public void stockItemPrice(String upc, int qty, int price_incent)

			throws SQLException, ClassNotFoundException, IOException

			{

		if(qty < 0)

			//sanity check

			throw new IOException("Quantity input cannot be less than 0.");

		if(price_incent <= 0)

			//santiy check

			throw new IOException("Price input must be larger than 0.");



		if(this.conn == null)

			this.conn = JDBCConnection.getConnection();



		PreparedStatement stmt = conn.prepareStatement(

				"UPDATE Item " +

						"SET stock = stock + ?, price = ? " +

				"WHERE upc = ?");

		stmt.setInt(1, qty);

		stmt.setDouble(2, (double)price_incent / 100.0);

		stmt.setString(3, upc);

		try

		{

			int update = stmt.executeUpdate();

			if(update == 1)

				return;

			else if(update == 0)

				//if none is update

				throw new SQLException("The Item with upc " + upc + " does " +

						"not exist.");

			else

				//Fatal error: more than 1 tuple is updated -> duplicate upc!!!

				throw new SQLException("Fatal Error: Duplicate UPC!");

		}

		finally

		{

			stmt.close();

		}

			}



	/**

	 * This method generate a sales report with a particular given day

	 * @param date

	 * @return empty report is returned if report cannot be generated

	 * @throws SQLException

	 * @throws IOException 

	 * @throws ClassNotFoundException 

	 */
	public DailyReport genDailyReport(GregorianCalendar date) 
			throws SQLException, ClassNotFoundException, IOException
	{
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();

		String sql = "SELECT I.upc, \n" +
				 	 "       I.category, \n" +
				 	 "       I.price, \n" +
				 	 "       PI.quantity, \n" +
				 	 "       I.price * PI.quantity \n" +
				 	 "FROM Purchase P, PurchaseItem PI, Item I \n" +
				 	 "WHERE P.receiptId = PI.receiptId AND \n" +
				 	 "      PI.upc = I.upc AND \n" +
				 	 "      P.pDate = ? \n" +
				 	 "ORDER BY I.category \n";
		PreparedStatement stmt = conn.prepareStatement(sql);
		Date temp = new Date(date.get(Calendar.YEAR) - 1900, 
							 date.get(Calendar.MONTH) - 1, 
							 date.get(Calendar.DAY_OF_MONTH));
		stmt.setDate(1, temp);
		
		try
		{
			ResultSet result = stmt.executeQuery();

			ArrayList<Triple<String, ArrayList<ReportItem>, Double>>
				tuples = new ArrayList<Triple<String, ArrayList<ReportItem>, Double>>();
			ArrayList<ReportItem> lst = new ArrayList<ReportItem>();
			String current_cat = "";
			while(result.next())
			{
				String upc = result.getString(1);
				String cat = result.getString(2);
				double price_$ = result.getDouble(3);
				int qty_sold = result.getInt(4);
				double total_$ = result.getDouble(5);

				ReportItem item = new ReportItem(upc, cat, price_$, qty_sold, total_$);
				lst.add(item);
				
				if(!cat.equals(current_cat))
				//different category group
				{
					current_cat = new String(cat);
					
					double cat_total = 0;
					for(int row = 0; row < lst.size(); row++)
						cat_total += lst.get(row).getTotalSale();
					
					Triple<String, ArrayList<ReportItem>, Double>
						a_tuple = new Triple<String, ArrayList<ReportItem>, Double>
										(current_cat, lst, new Double(cat_total));
					tuples.add(a_tuple);
					
					lst = new ArrayList<ReportItem>();//clean up the current lst
				}
			}
			
			return new DailyReport(tuples);
		}
		finally
		{
			stmt.close();
		}
	}



	/**

	 * This method generate a report about the n top selling items

	 * @param date

	 * @param top_n

	 * @return entity object TopNReport will be return

	 * @throws SQLException

	 * @throws IOException 

	 * @throws ClassNotFoundException 

	 */
	public TopNReport genTopNReport(GregorianCalendar date, int top_n)
			throws SQLException, ClassNotFoundException, IOException
	{
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();

		String sql =	"SELECT * \n" +
						"FROM( \n" +
						"      SELECT I.UPC, \n" +
						"             I.title, \n" +
						"             I.company, \n" +
						"             I.stock, \n" +
						"             SUM(PI.quantity) \n" +
						"      FROM Purchase P, PurchaseItem PI, Item I \n" +
						"      WHERE P.pDate = ? AND \n" +
						"            P.receiptId = PI.receiptId AND \n" +
						"            PI.quantity > 0 AND \n" +
						"            I.UPC = PI.UPC \n" +
						"      GROUP BY I.UPC, I.title, I.company, I.stock \n" +
						"      ORDER BY SUM(PI.quantity) DESC) \n" +
						"WHERE ROWNUM <= ? \n" +
						"ORDER BY ROWNUM \n";
				
		PreparedStatement stmt = conn.prepareStatement(sql);

		stmt.setDate(1, new Date(date.get(Calendar.YEAR) - 1900, 
								 date.get(Calendar.MONTH) - 1, 
								 date.get(Calendar.DAY_OF_MONTH)));
		stmt.setInt(2, top_n);

		try

		{
			ResultSet result = stmt.executeQuery();
			TopNReport report = new TopNReport();

			while(result.next())
			{
				String upc = result.getString(1);
				String title = result.getString(2);
				String comp = result.getString(3);
				int stk = result.getInt(4);
				int unit_sold = result.getInt(5);
				
				report.insertRow(upc, title, comp, stk, new Integer(unit_sold));
			}
			
			return report;
		}
		finally
		{
			stmt.close();
		}
	}



	/*

	 * This method updates delivery date for specific online purchase

	 * @pram r_id receipt id needs to be updated

	 * @pram date delivered date

	 * @return returns boolean to show if update was successful

	 * @throws SQLException

	 * @throws ClassNotFoundException

	 * @throws IOException



	 */



	public boolean processDelivery(String r_id)

			throws SQLException, ClassNotFoundException, IOException

			{

		if(this.conn == null)

			this.conn = JDBCConnection.getConnection();

		PreparedStatement stmt = conn.prepareStatement(

				"UPDATE Purchase " +

						"SET deliveredDate = ? " +

				"WHERE receiptId = ? AND cid NOT null");

		stmt.setDate(1, new Date(Calendar.getInstance().DATE));

		stmt.setString(2, r_id);

		try

		{

			int update = stmt.executeUpdate();

			if(update == 1)

				return true;

			else if(update == 0)

				//if none is update

				throw new SQLException("Invalid ReceiptId");

			else

				//Fatal error: more than 1 tuple is updated -> duplicate receiptId

				throw new SQLException("Fatal Error: Duplicate receiptId");

		}

		finally

		{

			stmt.close();

		}

			}

	private Connection conn;

}