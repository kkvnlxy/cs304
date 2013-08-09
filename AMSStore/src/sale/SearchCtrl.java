package sale;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import sale.Item.GENRE;
import sale.Item.ITEM_TYPE;
import util.JDBCConnection;



public class SearchCtrl 
{
	public SearchCtrl() {}

	public ArrayList<Item> searchByCategory(String category, int qty)
			throws ClassNotFoundException, IOException, SQLException 
	{
		if(conn == null)
			conn = JDBCConnection.getConnection();
		
		String sql = "SELECT * " +
					 "FROM Item " +
					 "WHERE category = ?";
		PreparedStatement stmt = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, category.toUpperCase());
			
			ResultSet result = stmt.executeQuery();
			ArrayList<Item> items = new ArrayList<Item>();
			while(result.next())
			{
				String upc = result.getString(Item.UPC_IND);
				String title = result.getString(Item.TITLE_IND);
				ITEM_TYPE type = Item.translateType(result.getString(
																Item.TYPE_IND));
				GENRE genre = Item.translateGenre(result.getString(
															Item.CATEGORY_IND));
				String comp = result.getString(Item.COMPANY_IND);
				String year = result.getString(Item.YEAR_IND);
				int price = (int)result.getDouble(Item.PRICE_IND) * 100;
				int stk = result.getInt(Item.STOCK_IND);
				
				if(qty >= stk)
					items.add(new Item(upc, title, type, genre, comp, year, 
									   price, stk));
			}
			return items;
		}
		finally
		{
			stmt.close();
		}
	}
	
	public ArrayList<Item> searchByTitle (String tlt, int qty) 
			throws ClassNotFoundException, IOException, SQLException
	{
		if(conn == null)
			conn = JDBCConnection.getConnection();
		
		String sql = "SELECT * " +
					 "FROM Item " +
					 "WHERE title = ?";
		PreparedStatement stmt = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, tlt);
			
			ResultSet result = stmt.executeQuery();
			ArrayList<Item> items = new ArrayList<Item>();
			while(result.next())
			{
				String upc = result.getString(Item.UPC_IND);
				String title = result.getString(Item.TITLE_IND);
				ITEM_TYPE type = Item.translateType(result.getString(
																Item.TYPE_IND));
				GENRE genre = Item.translateGenre(result.getString(
															Item.CATEGORY_IND));
				String comp = result.getString(Item.COMPANY_IND);
				String year = result.getString(Item.YEAR_IND);
				int price = (int)result.getDouble(Item.PRICE_IND) * 100;
				int stk = result.getInt(Item.STOCK_IND);
				
				if(qty >= stk)
					items.add(new Item(upc, title, type, genre, comp, year, 
									   price, stk));
			}
			return items;
		}
		finally
		{
			stmt.close();
		}
	}
	
	public ArrayList<Item> searchBySinger (String singer, int qty) 
			throws SQLException, ClassNotFoundException, IOException
	{
		if(conn == null)
			conn = JDBCConnection.getConnection();
		
		String sql = "SELECT i.upc, i.title, i.type, i.category, i.company, " +
							"i.year, i.price, i.stock " +
					 "FROM  Item i, LeadSinger l " +
					 "WHERE i.upc = l.upc AND l.name = ?";
		PreparedStatement stmt = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, singer);
			
			ResultSet result = stmt.executeQuery();
			ArrayList<Item> items = new ArrayList<Item>();
			while(result.next())
			{
				String upc = result.getString(Item.UPC_IND);
				String title = result.getString(Item.TITLE_IND);
				ITEM_TYPE type = Item.translateType(result.getString(
																Item.TYPE_IND));
				GENRE genre = Item.translateGenre(result.getString(
															Item.CATEGORY_IND));
				String comp = result.getString(Item.COMPANY_IND);
				String year = result.getString(Item.YEAR_IND);
				int price = (int)result.getDouble(Item.PRICE_IND) * 100;
				int stk = result.getInt(Item.STOCK_IND);
				
				if(qty >= stk)
					items.add(new Item(upc, title, type, genre, comp, year, 
									   price, stk));
			}
			return items;
		}
		finally
		{
			stmt.close();
		}
	}
	
	public ArrayList<Item> searchAll (String title, String category, 
									  String singer, int qty) 
				throws ClassNotFoundException, IOException, SQLException 
	{
		if(conn == null)
			conn = JDBCConnection.getConnection();
		
		String sql = "SELECT i.upc, i.title, i.type, i.category, i.company, " +
							"i.year, i.price, i.stock " +
					 "FROM  Item i, LeadSinger l " +
					 "WHERE i.upc = l.upc AND " +
					 	   "l.name = ? AND " +
					 	   "i.title = ? AND " +
					 	   "i.category = ?";
		PreparedStatement stmt = null;
		try
		{
			stmt = conn.prepareStatement(sql);
			stmt.setString(1, singer);
			stmt.setString(2, title);
			stmt.setString(3, category);
			
			ResultSet result = stmt.executeQuery();
			ArrayList<Item> items = new ArrayList<Item>();
			while(result.next())
			{
				String upc = result.getString(Item.UPC_IND);
				String ttl = result.getString(Item.TITLE_IND);
				ITEM_TYPE type = Item.translateType(result.getString(
																Item.TYPE_IND));
				GENRE genre = Item.translateGenre(result.getString(
															Item.CATEGORY_IND));
				String comp = result.getString(Item.COMPANY_IND);
				String year = result.getString(Item.YEAR_IND);
				int price = (int)result.getDouble(Item.PRICE_IND) * 100;
				int stk = result.getInt(Item.STOCK_IND);
				
				if(qty >= stk)
					items.add(new Item(upc, title, type, genre, comp, year, 
									   price, stk));
			}
			return items;
		}
		finally
		{
			stmt.close();
		}
	}
			
	private Connection conn;
	
}
	