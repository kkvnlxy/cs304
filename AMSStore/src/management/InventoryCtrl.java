package management;

import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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


		System.out.println("price in cent: " + price_incent);//testing
		System.out.println("price in doulb: " + (double)price_incent / 100.0);//testing

		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO Item " +
				"VALUES(?, ?, ?, ?, ?, ?, ?, ?)");
		stmt.setString(1, upc);
		stmt.setString(2, title);
		stmt.setString(3, Item.translateType(type));
		stmt.setString(4, Item.translateGenre(category));
		stmt.setString(5, company);
		stmt.setString(6, year);
		stmt.setDouble(7, (double)price_incent / 100.0);
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
	 * @return entity object DailyReport will be return
	 * @throws SQLException
	 * @throws IOException 
	 * @throws ClassNotFoundException 
	 */
	public DailyReport genDailyReport(GregorianCalendar date) 
			throws SQLException, ClassNotFoundException, IOException
			{
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(
				"COLUMN UPC FORMAT A20 " + 
						"BREAK ON CATEGORY ON REPORT " +
						"COMPUTE SUM LABEL Total OF \"Total Value\" ON CATEGORY" +
						"COMPUTE SUM LABEL \"Total Daily Sales\" OF \"Total Value\" ON REPORT" +
						"SELECT I.upc, I.category, I.price AS \"Unit Price\", PI.quantity AS \"Units\", I.price * PI.quantity AS \"Total Value\"" +
						"FROM Purchase P, PurchaseItem PI, Item I" +
						"WHERE P.receiptId = PI.receiptId AND PI.upc = I.upc AND P.pDate = to_date( ? , 'dd-mm-yyyy')" +
				"ORDER BY I.category");

		stmt.setDate(1, new Date(date.DATE));

		try
		{
			ResultSet result = stmt.executeQuery();
			if(!result.next())
				throw new SQLException("Could Not Produce Report");
			//TODO:
			else return new DailyReport();
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
		PreparedStatement stmt = conn.prepareStatement(
				"SELECT *" +
						"FROM( " +
						"SELECT I.UPC, I.title, I.company, I.stock, SUM(PI.quantity) AS \"Total Units\"" +
						"FROM Purchase P, PurchaseItem PI, Item I" +
						"WHERE P.pDate = to_date( ? , 'dd-mm-yyyy') AND P.receiptId = PI.receiptId AND PI.quantity > 0 AND I.UPC = PI.UPC" +
						"GROUP BY I.UPC, I.title, I.company, I.stock" +
						"ORDER BY \"Total Units\" DESC)" +
						"WHERE ROWNUM <= ? " +
				"ORDER BY ROWNUM");

		stmt.setDate(1, new Date(date.DATE));
		stmt.setInt(2, top_n);

		try
		{
			ResultSet result = stmt.executeQuery();
			if(!result.next())
				throw new SQLException("Could Not Produce Report");
			//TODO:
			else return new TopNReport();
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
