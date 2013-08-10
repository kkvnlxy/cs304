package ui;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;

import java.awt.Color;

import sale.Item;
import util.JDBCConnection;

import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class CustomerPurchase extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField itemYear;
	private Connection conn;
	private JPasswordField passwordField;
	private JTextField textField;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField textField_1;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;//qty in search tap
	
	private register.Customer current_cust;//DON'T forget: when finished check 
										   //out, erase the current_cust to null
	private ArrayList<sale.Item> search_result;//same as above
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerPurchase dialog = new CustomerPurchase();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerPurchase() {
		setBackground(Color.PINK);
		getContentPane().setBackground(Color.PINK);
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBounds(0, 0, 600, 380);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 600, 380);
			contentPanel.add(tabbedPane);
			{
				JPanel Login = new JPanel();
				Login.setBackground(Color.PINK);
				tabbedPane.addTab("Login", null, Login, null);
				Login.setLayout(null);

				JLabel lblCustomerID = DefaultComponentFactory.getInstance().createLabel("Customer ID");
				lblCustomerID.setBounds(118, 108, 92, 14);
				Login.add(lblCustomerID);

				JLabel lblPassword = DefaultComponentFactory.getInstance().createLabel("Password");
				lblPassword.setBounds(118, 153, 92, 14);
				Login.add(lblPassword);

				itemYear = new JTextField();//kevin: customer id field?
				itemYear.setBounds(229, 105, 217, 20);
				Login.add(itemYear);
				itemYear.setColumns(10);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(229, 150, 217, 20);
				Login.add(passwordField);
				
				JLabel lblCustomerLogin = DefaultComponentFactory.getInstance().createTitle("Customer: Login");
				lblCustomerLogin.setBounds(50, 50, 122, 16);
				Login.add(lblCustomerLogin);
				
				JButton btnLogin = new JButton("Login");
				btnLogin.addMouseListener(new MouseAdapter() 
				{
					//kevin0: 
					//DON'T forget: when finished check out, erase the 
					//current_cust to null
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						String c_id = itemYear.getText();
						String pswd = passwordField.getText();
						
						register.AuthenCtrl authenticator = new register.AuthenCtrl(c_id, pswd);
						try
						{
							current_cust = authenticator.authenticate();
						}
						catch(register.AuthenException expt)
						{
							// TODO Exception/Error pop-up window insert here
						}
						catch(FileNotFoundException expt)
						{
							// TODO Exception/Error pop-up window insert here
						}
						catch(IOException expt)
						{
							// TODO Exception/Error pop-up window insert here
						}
						catch(SQLException expt)
						{
							// TODO Exception/Error pop-up window insert here
						} 
						catch (ClassNotFoundException expt) 
						{
							// TODO Exception/Error pop-up window insert here
						}
					}
				});
				btnLogin.setBounds(456, 299, 117, 29);
				Login.add(btnLogin);
			}
			{
				JPanel Search = new JPanel();
				Search.setBackground(Color.PINK);
				tabbedPane.addTab("SearchItem", null, Search, null);
				Search.setLayout(null);
				
				JLabel lblItemSearch = DefaultComponentFactory.getInstance().createTitle("Item Search:");
				lblItemSearch.setBounds(50, 50, 122, 16);
				Search.add(lblItemSearch);
				
				JLabel lblCategory = DefaultComponentFactory.getInstance().createLabel("Category");
				lblCategory.setBounds(93, 109, 120, 16);
				Search.add(lblCategory);
				
				JLabel lblTitle = DefaultComponentFactory.getInstance().createLabel("Title");
				lblTitle.setBounds(93, 146, 120, 16);
				Search.add(lblTitle);
				
				//kevin1:
				final JComboBox<String> cbCategory = new JComboBox<String>();//cross out <String>?
				cbCategory.setToolTipText("");
				cbCategory.setModel(new DefaultComboBoxModel<String>(new String[] {"Choose a category", "ROCK", "POP", "RAP", "COUNTRY", "CLASSICAL", "NEW_AGE", "INTRUMENTAL"}));
				cbCategory.setBounds(220, 105, 270, 27);
				Search.add(cbCategory);
				
				JLabel lblLeadingSinger = DefaultComponentFactory.getInstance().createLabel("Leading Singer");
				lblLeadingSinger.setBounds(93, 183, 120, 16);
				Search.add(lblLeadingSinger);
				
				textField = new JTextField();//singer
				textField.setBounds(220, 181, 270, 20);
				Search.add(textField);
				textField.setColumns(10);
				
				textField_3 = new JTextField();//title
				textField_3.setColumns(10);
				textField_3.setBounds(220, 144, 270, 20);
				Search.add(textField_3);
				
				JLabel qty_lab = DefaultComponentFactory.getInstance().createLabel("Quantity");
				qty_lab.setBounds(93, 220, 120, 16);
				Search.add(qty_lab);
				textField_6 = new JTextField();//qty
				textField_6.setColumns(4);
				textField_6.setBounds(220, 218, 270, 20);
				Search.add(textField_6);
				
				JButton btnSearch = new JButton("Search");
				btnSearch.setBounds(483, 299, 90, 29);
				Search.add(btnSearch);
				btnSearch.addMouseListener(new MouseAdapter(){
					public void mouseClicked(MouseEvent arg0) 
					{
						sale.SearchCtrl searcher = new sale.SearchCtrl();
						String cate = (String)(cbCategory.getSelectedItem());
						cate = cate.equals("Choose a category") ? "" : cate;
						String title = textField.getText() != null ? textField.getText() : "";
						String singer = textField_3.getText() != null ? textField_3.getText() : "";
						int qty = textField_6.getText() != null ? 
									Integer.parseInt(textField_6.getText()) : 1;
						System.out.println(cate + "\t" + title + "\t" + singer + "\t" + qty);//testing
						
						try
						{
							search_result = new ArrayList<sale.Item>();
							if(!cate.equals("") && !title.equals("") && 
							   !singer.equals(""))
								search_result = searcher.searchAll(title, cate, 
												 				   singer, qty);
							//the following control flow will always search for 
							//category if presented, then title if presented, 
							//last singer
							else if(!cate.equals(""))
								search_result = searcher.searchByCategory(cate, qty);
							else if(!title.equals(""))
								search_result = searcher.searchByTitle(title, qty);
							else if(!singer.equals(""))
								search_result = searcher.searchBySinger(singer, qty);
						}
						catch(FileNotFoundException expt)
						{
							// TODO Exception/Error pop-up window insert here
						}
						catch(IOException expt)
						{
							// TODO Exception/Error pop-up window insert here
						}
						catch(SQLException expt)
						{
							// TODO Exception/Error pop-up window insert here
						} 
						catch (ClassNotFoundException expt) 
						{
							// TODO Exception/Error pop-up window insert here
						}
					}
				});
				//end of kevin1
			}
			
			JPanel Results = new JPanel();
			Results.setBackground(Color.PINK);
			Results.setToolTipText("");
			tabbedPane.addTab("Results", null, Results, null);
			Results.setLayout(null);
			
			JButton btnAddSelectedItems = new JButton("View Cart");
			btnAddSelectedItems.setBounds(472, 299, 101, 29);
			Results.add(btnAddSelectedItems);
			
			JPanel Resultspanel = new JPanel();
			Resultspanel.setLayout(null);
			Resultspanel.setBackground(Color.WHITE);
			Resultspanel.setBounds(10, 38, 558, 250);
			Results.add(Resultspanel);
			
			textField_2 = new JTextField();
			textField_2.setBounds(466, 35, 33, 29);
			Resultspanel.add(textField_2);
			textField_2.setColumns(10);
			
			JLabel lblUPC1 = new JLabel("UPC1");
			lblUPC1.setBounds(10, 35, 33, 29);
			Resultspanel.add(lblUPC1);
			
			JButton btnAddToCart = new JButton("Add");
			btnAddToCart.setBounds(501, 35, 56, 29);
			Resultspanel.add(btnAddToCart);
			
			JLabel lblCategory_1 = new JLabel("Category1");
			lblCategory_1.setBounds(288, 35, 67, 29);
			Resultspanel.add(lblCategory_1);
			
			JLabel lblTitle = new JLabel("Title");
			lblTitle.setBounds(55, 35, 46, 29);
			Resultspanel.add(lblTitle);
			
			JLabel lblQty1 = new JLabel("Qty1");
			lblQty1.setBounds(421, 35, 33, 29);
			Resultspanel.add(lblQty1);
			
			JLabel lblType = DefaultComponentFactory.getInstance().createTitle("Type");
			lblType.setBounds(215, 11, 56, 23);
			Resultspanel.add(lblType);
			
			JLabel lblNewLabel = new JLabel("New Age");
			lblNewLabel.setBounds(215, 35, 61, 29);
			Resultspanel.add(lblNewLabel);
			
			JLabel lblUpc_1 = DefaultComponentFactory.getInstance().createTitle("UPC");
			lblUpc_1.setBounds(10, 11, 33, 23);
			Resultspanel.add(lblUpc_1);
			
			JLabel lblTitle_1 = DefaultComponentFactory.getInstance().createTitle("Title");
			lblTitle_1.setBounds(55, 11, 53, 23);
			Resultspanel.add(lblTitle_1);
			
			JLabel lblCategory_2 = DefaultComponentFactory.getInstance().createTitle("Category");
			lblCategory_2.setBounds(288, 11, 67, 23);
			Resultspanel.add(lblCategory_2);
			
			JLabel lblPrice_1 = DefaultComponentFactory.getInstance().createTitle("Price");
			lblPrice_1.setBounds(367, 11, 46, 23);
			Resultspanel.add(lblPrice_1);
			
			JLabel lblStock = DefaultComponentFactory.getInstance().createTitle("Stock");
			lblStock.setBounds(421, 11, 46, 23);
			Resultspanel.add(lblStock);
			
			JLabel lblQty_1 = DefaultComponentFactory.getInstance().createTitle("Qty");
			lblQty_1.setBounds(469, 11, 46, 23);
			Resultspanel.add(lblQty_1);
			
			JLabel label_1 = DefaultComponentFactory.getInstance().createLabel("$15.00");
			label_1.setBounds(367, 35, 46, 29);
			Resultspanel.add(label_1);
			
			JButton btnGotosearch = new JButton("New Search");
			btnGotosearch.setBounds(359, 299, 101, 29);
			Results.add(btnGotosearch);
			
			JLabel lblSearchResults = DefaultComponentFactory.getInstance().createTitle("Search Results:");
			lblSearchResults.setBounds(10, 15, 122, 16);
			Results.add(lblSearchResults);
			
			JPanel Cart = new JPanel();
			Cart.setBackground(Color.PINK);
			tabbedPane.addTab("Cart", null, Cart, null);
			Cart.setLayout(null);
			
			JButton btnRemoveSelectedItems = new JButton("CheckOut");
			btnRemoveSelectedItems.setBounds(454, 299, 114, 29);
			Cart.add(btnRemoveSelectedItems);
			
			JPanel panel_1 = new JPanel();
			panel_1.setLayout(null);
			panel_1.setBackground(Color.WHITE);
			panel_1.setBounds(10, 38, 558, 250);
			Cart.add(panel_1);
			
			JButton RemoveFromCart = new JButton("Remove");
			RemoveFromCart.setBounds(463, 30, 89, 25);
			panel_1.add(RemoveFromCart);
			
			JLabel lblUpc = new JLabel("UPC1");
			lblUpc.setBounds(10, 37, 56, 18);
			panel_1.add(lblUpc);
			
			JLabel lblTitle2 = new JLabel("Title");
			lblTitle2.setBounds(60, 37, 176, 18);
			panel_1.add(lblTitle2);
			
			JLabel lblUpc_2 = DefaultComponentFactory.getInstance().createTitle("UPC");
			lblUpc_2.setBounds(10, 16, 38, 16);
			panel_1.add(lblUpc_2);
			
			JLabel lblTitle_2 = DefaultComponentFactory.getInstance().createTitle("Title");
			lblTitle_2.setBounds(60, 16, 122, 16);
			panel_1.add(lblTitle_2);
			
			JLabel lblType_1 = DefaultComponentFactory.getInstance().createTitle("Type");
			lblType_1.setBounds(242, 16, 38, 16);
			panel_1.add(lblType_1);
			
			JLabel lblCategory_3 = DefaultComponentFactory.getInstance().createTitle("Category");
			lblCategory_3.setBounds(292, 16, 73, 16);
			panel_1.add(lblCategory_3);
			
			JLabel lblPrice_2 = DefaultComponentFactory.getInstance().createTitle("Price");
			lblPrice_2.setBounds(368, 16, 38, 16);
			panel_1.add(lblPrice_2);
			
			JLabel lblQty_2 = DefaultComponentFactory.getInstance().createTitle("Qty");
			lblQty_2.setBounds(413, 16, 38, 16);
			panel_1.add(lblQty_2);
			
			JButton btnSearchpage = new JButton("ResultsPage");
			btnSearchpage.setBounds(161, 207, 114, 29);
			Cart.add(btnSearchpage);
			
			JButton btnResultspage = new JButton("SearchPage");
			btnResultspage.setBounds(21, 207, 114, 29);
			Cart.add(btnResultspage);
			
			JLabel lblShoppingCart = DefaultComponentFactory.getInstance().createTitle("Shopping Cart:");
			lblShoppingCart.setBounds(50, 15, 122, 16);
			Cart.add(lblShoppingCart);
			
			JPanel CheckOut = new JPanel();
			CheckOut.setBackground(Color.PINK);
			tabbedPane.addTab("Check Out", null, CheckOut, null);
			CheckOut.setLayout(null);
			
			JLabel lblBill = DefaultComponentFactory.getInstance().createTitle("Bill:");
			lblBill.setBounds(18, 17, 122, 16);
			CheckOut.add(lblBill);
			
			JLabel lblPayment = DefaultComponentFactory.getInstance().createTitle("Payment:");
			lblPayment.setBounds(344, 17, 122, 16);
			CheckOut.add(lblPayment);
			
			JLabel lblCreditCard = DefaultComponentFactory.getInstance().createLabel("Credit Card Number:");
			lblCreditCard.setBounds(344, 58, 122, 16);
			CheckOut.add(lblCreditCard);
			
			JLabel lblExpirationDate = DefaultComponentFactory.getInstance().createLabel("Expiration Date:");
			lblExpirationDate.setBounds(344, 107, 120, 16);
			CheckOut.add(lblExpirationDate);
			
			JPanel panelBill = new JPanel();
			panelBill.setBounds(17, 34, 299, 249);
			CheckOut.add(panelBill);
			panelBill.setLayout(null);
			
			JLabel lblUPC3 = new JLabel("UPC");
			lblUPC3.setBounds(128, 39, 31, 14);
			panelBill.add(lblUPC3);
			
			JLabel lblTitle1 = new JLabel("Title");
			lblTitle1.setBounds(16, 39, 31, 14);
			panelBill.add(lblTitle1);
			
			JLabel lblPrice1 = new JLabel("$15.00");
			lblPrice1.setBounds(226, 39, 54, 14);
			panelBill.add(lblPrice1);
			
			JLabel lblTitle0 = DefaultComponentFactory.getInstance().createTitle("Title");
			lblTitle0.setBounds(16, 16, 40, 16);
			panelBill.add(lblTitle0);
			
			JLabel lblUPC = DefaultComponentFactory.getInstance().createTitle("UPC");
			lblUPC.setBounds(128, 16, 40, 16);
			panelBill.add(lblUPC);
			
			JLabel lblQty = DefaultComponentFactory.getInstance().createTitle("Qty");
			lblQty.setBounds(183, 16, 31, 16);
			panelBill.add(lblQty);
			
			JLabel lblPrice = DefaultComponentFactory.getInstance().createTitle("Price");
			lblPrice.setBounds(226, 16, 46, 16);
			panelBill.add(lblPrice);
			
			JLabel lblQty3 = new JLabel("1");
			lblQty3.setBounds(183, 38, 22, 16);
			panelBill.add(lblQty3);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(400, 123, 59, 22);
			CheckOut.add(textField_1);
			
			textField_4 = new JTextField();
			textField_4.setColumns(10);
			textField_4.setBounds(367, 74, 182, 22);
			CheckOut.add(textField_4);
			
			JButton btnClear = new JButton("Clear");
			btnClear.setBounds(389, 177, 73, 29);
			CheckOut.add(btnClear);
			
			JButton btnAuthorize = new JButton("Authorize");
			btnAuthorize.setBounds(456, 177, 93, 29);
			CheckOut.add(btnAuthorize);
			
			JPanel panel = new JPanel();
			panel.setBackground(new Color(255, 255, 224));
			panel.setBounds(0, 295, 579, 39);
			CheckOut.add(panel);
			panel.setLayout(new FlowLayout(FlowLayout.RIGHT));
			
			JButton button = new JButton("ConfirmPurchase");
			button.setActionCommand("ConfirmPurchase");
			panel.add(button);
			
			JButton button_1 = new JButton("Cancel");
			button_1.setActionCommand("Cancel");
			panel.add(button_1);
			
			textField_5 = new JTextField();
			textField_5.setBounds(490, 120, 59, 28);
			CheckOut.add(textField_5);
			textField_5.setColumns(10);
			
			JLabel lblMm = DefaultComponentFactory.getInstance().createLabel("MM");
			lblMm.setBounds(367, 126, 41, 16);
			CheckOut.add(lblMm);
		}
	}

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
	public void createItem(	String upc, String title, Item.ITEM_TYPE type, 
			Item.GENRE category, String company, String year, 
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
			JOptionPane.showMessageDialog(null, "Error occurred: Please try again", "ERROR", JOptionPane.ERROR_MESSAGE);

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
}
