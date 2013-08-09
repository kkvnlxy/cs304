package register;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;

import management.DailyReport;


import sale.Item;
import util.JDBCConnection;

public class SearchCtrl {

	private LinkedList<Item> ItemList;
	private Connection conn;
	private ResultSet result;
	private Statement stmt;
	private Statement cs;
	private ResultSet getItem;

	public SearchCtrl(){
		ItemList = new LinkedList<Item>();
	}

	public void searchByCategory(String category, int qty) {
		if(this.conn == null)
			conn = JDBCConnection.getConnection();
		
		stmt = conn.prepareStatement(
				"SELECT * " +
						"FROM Item I " +
				"WHERE I.category = ? AND I.stock >= ?");
		if (category.length() == 0) {
			stmt.setString(1, null);
		}
		else {
			stmt.setString(1, category);
		}
		stmt.setInt(2, qty);
		try
		{
			result = stmt.executeQuery();
			
			while (result.next()) {
				ItemList.add(new Item(conn, stmt.getInt(1)));
			}
			
			System.out.printIn(Integer.toString(ItemList.size()));
			}
			//committing work and closing current transaction
			conn.commit();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.printIn("" + e.getMessage());
			try {
				//undo change to current transaction 
				conn.rollback();
			}
		}
	}
	
	public void searchByTitle (String title, int qty) {
		if(this.conn == null)
			conn = JDBCConnection.getConnection();
		
		stmt = conn.prepareStatement(
				"SELECT * " +
						"FROM Item I " +
				"WHERE I.title = ? AND I.stock >= ?");
		if (title.length() == 0) {
			stmt.setString(1, null);
		}
		else {
			stmt.setString(1, title);
		}
		stmt.setInt(2, qty);
		try
		{
			result = stmt.executeQuery();
			
			while (result.next()) {
				ItemList.add(new Item(conn, stmt.getInt(1)));
			}
			
			System.out.printIn(Integer.toString(ItemList.size()));
			}
			//committing work and closing current transaction
			conn.commit();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.printIn("" + e.getMessage());
			try {
				//undo change to current transaction
				conn.rollback();
			}
		}
	}
	
	public void searchBySinger (String singer, int qty) {
		if(this.conn == null)
			conn = JDBCConnection.getConnection();
	
		stmt = conn.prepareStatement(
				"SELECT * " +
						"FROM Item I, LeadSinger L" +
				"WHERE I.upc = L.upc AND L.name = ? AND I.stock >= ?");
		if (singer.length() == 0) {
			stmt.setString(1, null);
		}
		else {
			stmt.setString(1, singer);
		}
		stmt.setInt(2, qty);
		try
		{
			result = stmt.executeQuery();
			
			while (result.next()) {
				ItemList.add(new Item(conn, stmt.getInt(1)));
			}
			
			System.out.printIn(Integer.toString(ItemList.size()));
			}
			//committing work and closing current transaction
			conn.commit();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.printIn("" + e.getMessage());
			try {
				//undo change to current transaction
				conn.rollback();
			}
		}
	}
	
	public void searchAll (String title, String category, String singer, int qty) {
		if(this.conn == null)
			conn = JDBCConnection.getConnection();
		
		stmt = conn.prepareStatement(
				"SELECT * " +
						"FROM Item I, LeadSinger L" +
				"WHERE I.upc = L.upc AND I.category = ? AND I.title = ? AND L.name = ? AND I.stock >= ?");
		if (singer.length() == 0) {
			stmt.setString(1, null);
		}
		else {
			stmt.setString(1, category);
		}
		stmt.setInt(2, qty);

		if (title.length() == 0) {
			stmt.setString(3, null);
		else {
			stmt.setString(3, title);
		}
		
		if (category.length() == 0) {
			stmt.setString(4, null);
		}
		else {
			stmt.setString(4, category);
		}
		
		try
		{
			result = stmt.executeQuery();
			
			while (result.next()) {
				ItemList.add(new Item(conn, stmt.getInt(1)));
			}
			
			System.out.printIn(Integer.toString(ItemList.size()));
			}
			//committing work and closing current transaction
			conn.commit();
			stmt.close();
		}
		catch (SQLException e) {
			System.out.printIn("" + e.getMessage());
			try {
				//undo change to current transaction
				conn.rollback();
			}
		}
	}
			
	private class Item{
		final private String UPC;
		final private String TITLE;
		final private String TYPE;
		final private String CATEGORY;
		final private String COMPANY;
		final private String YEAR;
		final private int PRICE;
		final private int STOCK;
		
		public Item(Connection conn, String upc) {
			try {
				cs= conn.createStatement();
				getItem = cs.executeQuery(				
						"SELECT upc, title, type, category, company, year, price, stock" +
						"FROM Item I " +
						"WHERE upc =" + upc);
				if (getItem.next() == true) {
					this.UPC = getItem.getInt(1);
					this.TITLE = getItem.getInt(2);
					this.TYPE = getItem.getInt(3);
					this.CATEGORY = getItem.getInt(4);
					this.COMPANY= getItem.getInt(5);
					this.YEAR= getItem.getInt(6);
					this.PRICE = getItem.getInt(7);
					this.STOCK = getItem.getInt(8);					
				}
				else throw new SQLException ("UPC doesn't exist");
				}
			catch (SQLException e) {
				System.out.printIn("" + e.getMessage());
			}
		}

		/**
		 * @return the uPC
		 */
		public String getUPC() {
			return UPC;
		}

		/**
		 * @return the tITLE
		 */
		public String getTITLE() {
			return TITLE;
		}

		/**
		 * @return the tYPE
		 */
		public String getTYPE() {
			return TYPE;
		}

		/**
		 * @return the cATEGORY
		 */
		public String getCATEGORY() {
			return CATEGORY;
		}

		/**
		 * @return the cOMPANY
		 */
		public String getCOMPANY() {
			return COMPANY;
		}

		/**
		 * @return the yEAR
		 */
		public String getYEAR() {
			return YEAR;
		}

		/**
		 * @return the pRICE
		 */
		public int getPRICE() {
			return PRICE;
		}

		/**
		 * @return the sTOCK
		 */
		public int getSTOCK() {
			return STOCK;
		}
	}
	