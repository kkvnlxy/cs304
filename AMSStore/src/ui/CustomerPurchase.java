package ui;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import sale.Item;
import util.JDBCConnection;

import com.jgoodies.forms.factories.DefaultComponentFactory;


public class CustomerPurchase extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTabbedPane tabbedPane;

	private Connection conn;
	private CustomerPurchase cp = this;

	//Login
	private JTextField cid;
	private JPasswordField pwd;
	private register.Customer current_cust;//DON'T forget: when finished check out, erase the current_cust to null

	//SearchItem
	private JTextField SITitle;
	private JTextField SISinger;
	private JTextField SIQty;
	private JComboBox SICat;
	private ArrayList<Item> search_result;//when finish checkout, set to null

	//Results
	private JPanel Results;
	private JPanel Resultspanel;
	private ArrayList<onlineItemsGUI> resultItems;
	private int RVCounter = 15;

	//Cart
	private ArrayList<onlineItemsGUI> cartItems;
	private int CVCounter = 15;

	//Checkout
	private JTextField COMonth;
	private JTextField COCardNo;
	private JTextField COYear;
	
	private sale.OnlinePurchaseCtrl op_ctrl;


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
		//fields
		search_result = new ArrayList<Item>();

		//GUI
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
			tabbedPane = new JTabbedPane(JTabbedPane.TOP);
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

				cid = new JTextField();
				cid.setBounds(229, 105, 217, 20);
				Login.add(cid);
				cid.setColumns(10);

				pwd = new JPasswordField();
				pwd.setBounds(229, 150, 217, 20);
				Login.add(pwd);

				JLabel lblCustomerLogin = DefaultComponentFactory.getInstance().createTitle("Customer: Login");
				lblCustomerLogin.setBounds(50, 50, 122, 16);
				Login.add(lblCustomerLogin);

				JButton btnLogin = new JButton("Login");
				btnLogin.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				btnLogin.addMouseListener(new MouseAdapter() 
				{
					//kevin: 
					//DON'T forget: when finished check out, erase the 
					//current_cust to null
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						String c_id = cid.getText();
						String pswd = new String (pwd.getPassword());
						System.out.println(c_id);
						System.out.println(pswd);

						register.AuthenCtrl authenticator = new register.AuthenCtrl(c_id, pswd);
						try
						{
							current_cust = authenticator.authenticate();
							if(current_cust!= null){
								op_ctrl = new sale.OnlinePurchaseCtrl();
								op_ctrl.authen(c_id, pswd);
								tabbedPane.setSelectedIndex(1);
							}
						}
						catch(register.AuthenException expt)
						{
							// TODO Exception/Error pop-up window insert here
							System.out.println("error1");
						}
						catch(FileNotFoundException expt)
						{
							System.out.println("error2");
							// TODO Exception/Error pop-up window insert here
						}
						catch(IOException expt)
						{
							System.out.println("error3");
							// TODO Exception/Error pop-up window insert here
						}
						catch(SQLException expt)
						{
							System.out.println("error4");
							// TODO Exception/Error pop-up window insert here
						} 
						catch (ClassNotFoundException expt) 
						{
							System.out.println("error5");
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

				SICat = new JComboBox();
				SICat.setToolTipText("");
				SICat.setModel(new DefaultComboBoxModel(new String[] {"Choose a category", "ROCK", "POP", "RAP", "COUNTRY", "CLASSICAL", "NEW_AGE", "INTRUMENTAL"}));
				SICat.setBounds(220, 105, 270, 27);
				Search.add(SICat);

				JLabel lblLeadingSinger = DefaultComponentFactory.getInstance().createLabel("Leading Singer");
				lblLeadingSinger.setBounds(93, 183, 120, 16);
				Search.add(lblLeadingSinger);

				SISinger = new JTextField();
				SISinger.setBounds(220, 181, 270, 20);
				Search.add(SISinger);
				SISinger.setColumns(10);

				JLabel qty_lab = DefaultComponentFactory.getInstance().createLabel("Quantity");
				qty_lab.setBounds(93, 220, 120, 16);
				Search.add(qty_lab);
				SIQty = new JTextField();//qty
				SIQty.setColumns(4);
				SIQty.setBounds(220, 218, 270, 20);
				Search.add(SIQty);

				Results = new JPanel();
				Results.setBackground(Color.PINK);
				Results.setToolTipText("");
				tabbedPane.addTab("Results", null, Results, null);
				Results.setLayout(null);

				Resultspanel = new JPanel();
				Resultspanel.setLayout(null);
				Resultspanel.setBackground(Color.WHITE);
				Resultspanel.setBounds(10, 38, 558, 250);
				Results.add(Resultspanel);

				JButton btnSearch = new JButton("Search");
				btnSearch.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				btnSearch.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						sale.SearchCtrl searcher = new sale.SearchCtrl();
						String cate = (String) SICat.getSelectedItem();
						cate = cate.equals("Choose a category") ? "" : cate;
						String title = SISinger.getText() != null ? SISinger.getText() : "";
						String singer = SITitle.getText() != null ? SITitle.getText() : "";
						int qty = SIQty.getText() != null ? 
								Integer.parseInt(SIQty.getText()) : 1;
								System.out.println(cate + "\t" + title + "\t" + singer + "\t" + qty);//testing

								try
								{
									if(!cate.equals("") && !title.equals("") && 
											!singer.equals(""))
										search_result = searcher.searchAll(title, cate, 
												singer, qty);
									//the following control flow will always search for 
									//category if presented, then title if presented, 
									//last singer
									else if(!cate.equals("")){
										search_result = searcher.searchByCategory(cate, qty);
										System.out.println("" + search_result.size());
									}
									else if(!title.equals("")){
										search_result = searcher.searchByTitle(title, qty);
										System.out.println("" + search_result.size());
									}
									else if(!singer.equals("")){
										search_result = searcher.searchBySinger(singer, qty);
										System.out.println("" + search_result.size());
									}

									//if results is not empty, then changes to results tab
									if(search_result.isEmpty()){
										// TODO print msg : "Sorry no results found"
										System.out.println("Sorry no results found");
									} else {

										JLabel lblType = DefaultComponentFactory.getInstance().createTitle("Type");
										lblType.setBounds(215, 11, 56, 23);
										Resultspanel.add(lblType);

										JLabel lblUpc_1 = DefaultComponentFactory.getInstance().createTitle("UPC");
										lblUpc_1.setBounds(10, 11, 33, 23);
										Resultspanel.add(lblUpc_1);

										JLabel lblTitle_1 = DefaultComponentFactory.getInstance().createTitle("Title");
										lblTitle_1.setBounds(55, 11, 149, 23);
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

										//TODO: parsing out info for results page
										for(int i = 0; i < cp.search_result.size(); i++){
											RVCounter += 25;
											Item item_nxt = search_result.get(i);
											onlineItemsGUI current_item = new onlineItemsGUI(item_nxt, RVCounter);
											resultItems.add(current_item);
										}
										search_result.clear();
										tabbedPane.setSelectedIndex(2);
									}
								}
								catch(FileNotFoundException expt)
								{
									System.out.println("error1");
									// TODO Exception/Error pop-up window insert here
								}
								catch(IOException expt)
								{
									System.out.println("error2");
									// TODO Exception/Error pop-up window insert here
								}
								catch(SQLException expt)
								{
									System.out.println("error3");
									// TODO Exception/Error pop-up window insert here
								} 
								catch (ClassNotFoundException expt) 
								{
									System.out.println("error4");
									// TODO Exception/Error pop-up window insert here
								}
					}
				});
				btnSearch.setBounds(483, 299, 90, 29);
				Search.add(btnSearch);

				SITitle = new JTextField();
				SITitle.setColumns(10);
				SITitle.setBounds(220, 144, 270, 20);
				Search.add(SITitle);
			}

			JButton btnAddSelectedItems = new JButton("View Cart");
			btnAddSelectedItems.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					tabbedPane.setSelectedIndex(3);
				}
			});
			btnAddSelectedItems.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
				}
			});
			btnAddSelectedItems.setBounds(472, 299, 101, 29);
			Results.add(btnAddSelectedItems);

			JButton btnGotosearch = new JButton("New Search");
			btnGotosearch.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent arg0) {
					Resultspanel.removeAll();
					RVCounter = 15;
					tabbedPane.setSelectedIndex(1);

					JLabel lblType = DefaultComponentFactory.getInstance().createTitle("Type");
					lblType.setBounds(215, 11, 56, 23);
					Resultspanel.add(lblType);

					JLabel lblUpc_1 = DefaultComponentFactory.getInstance().createTitle("UPC");
					lblUpc_1.setBounds(10, 11, 33, 23);
					Resultspanel.add(lblUpc_1);

					JLabel lblTitle_1 = DefaultComponentFactory.getInstance().createTitle("Title");
					lblTitle_1.setBounds(55, 11, 149, 23);
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

				}
			});
			btnGotosearch.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
				}
			});
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

			JPanel CartPane = new JPanel();
			CartPane.setLayout(null);
			CartPane.setBackground(Color.WHITE);
			CartPane.setBounds(10, 38, 558, 250);
			Cart.add(CartPane);

			JButton RemoveFromCart = new JButton("Remove");
			RemoveFromCart.setBounds(463, 30, 89, 25);
			CartPane.add(RemoveFromCart);

			JLabel UPC1 = new JLabel("UPC1");
			UPC1.setBounds(10, 36, 47, 18);
			CartPane.add(UPC1);

			JLabel TITLE1 = new JLabel("TITLE1");
			TITLE1.setBounds(60, 36, 178, 18);
			CartPane.add(TITLE1);

			JLabel CUPC = DefaultComponentFactory.getInstance().createTitle("UPC");
			CUPC.setBounds(10, 16, 47, 16);
			CartPane.add(CUPC);

			JLabel CTITLE = DefaultComponentFactory.getInstance().createTitle("Title");
			CTITLE.setBounds(60, 16, 178, 16);
			CartPane.add(CTITLE);

			JLabel CTYPE = DefaultComponentFactory.getInstance().createTitle("Type");
			CTYPE.setBounds(242, 16, 48, 16);
			CartPane.add(CTYPE);

			JLabel CCAT = DefaultComponentFactory.getInstance().createTitle("Category");
			CCAT.setBounds(292, 16, 76, 16);
			CartPane.add(CCAT);

			JLabel CPRICE = DefaultComponentFactory.getInstance().createTitle("Price");
			CPRICE.setBounds(368, 16, 40, 16);
			CartPane.add(CPRICE);

			JLabel CQTY = DefaultComponentFactory.getInstance().createTitle("Qty");
			CQTY.setBounds(413, 16, 46, 16);
			CartPane.add(CQTY);

			JLabel TYPE1 = new JLabel("TYPE1");
			TYPE1.setBounds(242, 40, 48, 14);
			CartPane.add(TYPE1);

			JLabel CAT1 = new JLabel("CAT1");
			CAT1.setBounds(292, 40, 76, 14);
			CartPane.add(CAT1);

			JLabel PRICE1 = new JLabel("PRICE1");
			PRICE1.setBounds(368, 40, 40, 14);
			CartPane.add(PRICE1);

			JLabel QTY1 = new JLabel("QTY1");
			QTY1.setBounds(413, 40, 46, 14);
			CartPane.add(QTY1);

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
			tabbedPane.addTab("CheckOut", null, CheckOut, null);
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

			JPanel BillPanle = new JPanel();
			BillPanle.setBounds(17, 34, 299, 249);
			CheckOut.add(BillPanle);
			BillPanle.setLayout(null);

			JLabel COUPC1 = new JLabel("UPC1");
			COUPC1.setBounds(128, 39, 50, 14);
			BillPanle.add(COUPC1);

			JLabel COTITLE1 = new JLabel("TITLE1");
			COTITLE1.setBounds(16, 39, 102, 14);
			BillPanle.add(COTITLE1);

			JLabel COPRICE1 = new JLabel("PRICE1");
			COPRICE1.setBounds(226, 39, 65, 14);
			BillPanle.add(COPRICE1);

			JLabel lblTitle0 = DefaultComponentFactory.getInstance().createTitle("Title");
			lblTitle0.setBounds(16, 16, 102, 16);
			BillPanle.add(lblTitle0);

			JLabel lblUPC = DefaultComponentFactory.getInstance().createTitle("UPC");
			lblUPC.setBounds(128, 16, 50, 16);
			BillPanle.add(lblUPC);

			JLabel lblQty = DefaultComponentFactory.getInstance().createTitle("Qty");
			lblQty.setBounds(183, 16, 39, 16);
			BillPanle.add(lblQty);

			JLabel lblPrice = DefaultComponentFactory.getInstance().createTitle("Price");
			lblPrice.setBounds(226, 16, 65, 16);
			BillPanle.add(lblPrice);

			JLabel COQTY1 = new JLabel("QTY1");
			COQTY1.setBounds(183, 38, 39, 16);
			BillPanle.add(COQTY1);

			COMonth = new JTextField();
			COMonth.setColumns(10);
			COMonth.setBounds(378, 130, 59, 22);
			CheckOut.add(COMonth);

			COCardNo = new JTextField();
			COCardNo.setColumns(10);
			COCardNo.setBounds(367, 74, 182, 22);
			CheckOut.add(COCardNo);

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
			button.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent arg0) 
					{
						if(op_ctrl == null)
						//sanity check
							throw new RuntimeException("Ctrl not yet instantiate, forget to login?");
					
						String card_num = COCardNo.getText() == null ? "" : COCardNo.getText();
						int expr_yy = card_num.equals("") ? 0 : 2000 + Integer.parseInt(COYear.getText());
						int expr_mm = card_num.equals("") ? 0 : Integer.parseInt(COMonth.getText());
						
						try
						{
							sale.Purchase receipt = (sale.Purchase)op_ctrl.process(card_num, 
																					new GregorianCalendar(expr_yy, expr_mm, 1));

							op_ctrl.cancel();//housekeeping
							current_cust = null;//housekeeping
							
							//TODO further printing with the receipt adhere:
							
						}
						catch(FileNotFoundException expt)
						{
							// TODO Auto-generated catch block
							expt.printStackTrace();
						} 
						catch (IOException expt) 
						{
							// TODO Auto-generated catch block
							expt.printStackTrace();
						}
						catch (ClassNotFoundException expt) 
						{
							// TODO Auto-generated catch block
							expt.printStackTrace();
						} 
						catch (SQLException expt) 
						{
							// TODO Auto-generated catch block
							expt.printStackTrace();
						} 
					}
				});
			panel.add(button);

			JButton button_1 = new JButton("Cancel");
			button_1.setActionCommand("Cancel");
			//housekeeping button:
			button_1.addMouseListener(new MouseAdapter()
				{
					@Override
					public void mouseClicked(MouseEvent arg0)
					{
						if(op_ctrl != null)
							op_ctrl.cancel();
						current_cust = null;
					}
				});
			panel.add(button_1);

			COYear = new JTextField();
			COYear.setBounds(492, 130, 59, 22);
			CheckOut.add(COYear);
			COYear.setColumns(10);

			JLabel lblMm = DefaultComponentFactory.getInstance().createLabel("MM");
			lblMm.setBounds(346, 133, 41, 16);
			CheckOut.add(lblMm);

			JLabel lblYy = new JLabel("YY");
			lblYy.setBounds(460, 134, 25, 14);
			CheckOut.add(lblYy);
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
	
	//TODO
	
	public class onlineItemsGUI {
		protected Item GUIitem;
		protected int counterInit;
		
		//GUI elements
		private JTextField RQty_IN;
		private JLabel RTITLE;
		private JLabel RUPC;
		private JLabel RTYPE;
		private JLabel RCAT;
		private JLabel RPRICE;
		private JLabel RSTOCK;
		private JButton btnAddToCart;
		
		protected onlineItemsGUI(Item i, int counterStart){
			this.GUIitem = i; 
			this.counterInit = counterStart;
			
			RTITLE = new JLabel(GUIitem.getTitle());
			RTITLE.setBounds(55, counterInit, 149, 29);
			Resultspanel.add(RTITLE);

			RUPC = new JLabel(GUIitem.getUPC());
			RUPC.setBounds(10, counterInit, 33, 29);
			Resultspanel.add(RUPC);

			RTYPE = new JLabel("" + GUIitem.getType());
			RTYPE.setBounds(215, counterInit, 61, 29);
			Resultspanel.add(RTYPE);

			RCAT = new JLabel("" + GUIitem.getCategory());
			RCAT.setBounds(288, counterInit, 67, 29);
			Resultspanel.add(RCAT);

			RPRICE = DefaultComponentFactory.getInstance().createLabel("" + GUIitem.getPrice());
			RPRICE.setBounds(367, counterInit, 46, 29);
			Resultspanel.add(RPRICE);

			RSTOCK = new JLabel("" + GUIitem.getSTOCK());
			RSTOCK.setBounds(421, counterInit, 33, 29);
			Resultspanel.add(RSTOCK);

			RQty_IN = new JTextField();
			RQty_IN.setBounds(466, counterInit, 33, 29);
			Resultspanel.add(RQty_IN);
			RQty_IN.setColumns(10);
			
			
			}
		
		//search_result parsing out
		
			/*

			//TODO: 
			//check qty field if it's less than stock then allow to add to cart
			// if not ask customer to accept quantity of stock [popup]
											
			JButton btnAddToCart = new JButton("Add");
			btnAddToCart.addMouseListener(new MouseAdapter() {
				//TODO: hold the item that it's associated with as a local variable? 
				
				@Override
				public void mouseClicked(MouseEvent e) {
					int qty_in = Integer.parseInt(RQty_IN.getText());
					
					if (qty_in >= GUIitem.getSTOCK()){
						resultItems.add(new Item(GUIitem.getUPC(), GUIitem.getTitle(), GUIitem.getType(), GUIitem.getCategory(),
							GUIitem.getCompany(), GUIitem.getYear(), GUIitem.getPriceInCents(), GUIitem.getSTOCK()));
					System.out.println("no. of items in cart currently: " + resultItems.size());
					}else{
						System.out.println("asking for too much! </3");
					}
				}
			});
			btnAddToCart.setBounds(501, RVCounter, 56, 29);
			Resultspanel.add(btnAddToCart);
		}*/
	}
}