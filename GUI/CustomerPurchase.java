import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JPasswordField;
import java.awt.Color;
import javax.swing.JScrollPane;


public class CustomerPurchase extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField itemYear;
	private Connection conn;
	private JPasswordField passwordField;
	private JTextField tfTitle;
	private JTextField textField;
	private JTextField fieldCreditCard;
	private JTextField fieldMM;
	private JTextField textField_1;
	
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
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBounds(0, 0, 450, 278);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 450, 275);
			contentPanel.add(tabbedPane);
			{
				JPanel Login = new JPanel();
				Login.setBackground(Color.PINK);
				tabbedPane.addTab("Login", null, Login, null);
				Login.setLayout(null);

				JLabel lblCustomerID = DefaultComponentFactory.getInstance().createLabel("Customer ID");
				lblCustomerID.setBounds(83, 80, 92, 14);
				Login.add(lblCustomerID);

				JLabel lblPassword = DefaultComponentFactory.getInstance().createLabel("Password");
				lblPassword.setBounds(83, 112, 92, 14);
				Login.add(lblPassword);

				itemYear = new JTextField();
				itemYear.setBounds(177, 80, 177, 20);
				Login.add(itemYear);
				itemYear.setColumns(10);
				
				passwordField = new JPasswordField();
				passwordField.setBounds(177, 105, 177, 28);
				Login.add(passwordField);
				
				JLabel lblCustomerLogin = DefaultComponentFactory.getInstance().createTitle("Customer: Login");
				lblCustomerLogin.setBounds(48, 32, 122, 16);
				Login.add(lblCustomerLogin);
				
				JButton btnLogin = new JButton("Login");
				btnLogin.setBounds(237, 159, 117, 29);
				Login.add(btnLogin);
			}
			{
				JPanel Shop = new JPanel();
				Shop.setBackground(Color.PINK);
				tabbedPane.addTab("Shop", null, Shop, null);
				Shop.setLayout(null);
				
				JPanel panel = new JPanel();
				panel.setBackground(new Color(255, 255, 224));
				panel.setBounds(228, 46, 181, 164);
				Shop.add(panel);
				
				JLabel lblItemSearch = DefaultComponentFactory.getInstance().createTitle("Item Search:");
				lblItemSearch.setBounds(30, 18, 122, 16);
				Shop.add(lblItemSearch);
				
				JLabel lblCart = DefaultComponentFactory.getInstance().createTitle("Cart:");
				lblCart.setBounds(228, 18, 122, 16);
				Shop.add(lblCart);
				
				JLabel lblCategory = DefaultComponentFactory.getInstance().createLabel("Category");
				lblCategory.setBounds(30, 51, 120, 16);
				Shop.add(lblCategory);
				
				tfTitle = new JTextField();
				tfTitle.setBounds(30, 107, 156, 28);
				Shop.add(tfTitle);
				tfTitle.setColumns(10);
				
				JLabel lblTitle = DefaultComponentFactory.getInstance().createLabel("Title");
				lblTitle.setBounds(30, 94, 120, 16);
				Shop.add(lblTitle);
				
				JComboBox cbCategory = new JComboBox();
				cbCategory.setModel(new DefaultComboBoxModel(new String[] {"ROCK", "POP", "RAP", "COUNTRY", "CLASSICAL", "NEW_AGE", "INTRUMENTAL"}));
				cbCategory.setBounds(30, 67, 156, 27);
				Shop.add(cbCategory);
				
				JLabel lblLeadingSinger = DefaultComponentFactory.getInstance().createLabel("Leading Singer");
				lblLeadingSinger.setBounds(30, 138, 120, 16);
				Shop.add(lblLeadingSinger);
				
				textField = new JTextField();
				textField.setBounds(30, 152, 156, 28);
				Shop.add(textField);
				textField.setColumns(10);
				
				JButton btnSearch = new JButton("Search");
				btnSearch.setBounds(62, 181, 90, 29);
				Shop.add(btnSearch);
			}
			
			JPanel CheckOut = new JPanel();
			CheckOut.setBackground(Color.PINK);
			tabbedPane.addTab("Check Out", null, CheckOut, null);
			CheckOut.setLayout(null);
			
			JScrollPane scrollPane = new JScrollPane();
			scrollPane.setBounds(17, 43, 180, 169);
			CheckOut.add(scrollPane);
			
			JLabel lblBill = DefaultComponentFactory.getInstance().createTitle("Bill:");
			lblBill.setBounds(17, 17, 122, 16);
			CheckOut.add(lblBill);
			
			JLabel lblPayment = DefaultComponentFactory.getInstance().createTitle("Payment:");
			lblPayment.setBounds(215, 17, 122, 16);
			CheckOut.add(lblPayment);
			
			JLabel lblCreditCard = DefaultComponentFactory.getInstance().createLabel("Credit Card");
			lblCreditCard.setBounds(209, 61, 122, 16);
			CheckOut.add(lblCreditCard);
			
			fieldCreditCard = new JTextField();
			fieldCreditCard.setBounds(219, 79, 180, 28);
			CheckOut.add(fieldCreditCard);
			fieldCreditCard.setColumns(10);
			
			JLabel lblExpirationDate = DefaultComponentFactory.getInstance().createLabel("Expiration Date");
			lblExpirationDate.setBounds(209, 119, 120, 16);
			CheckOut.add(lblExpirationDate);
			
			fieldMM = new JTextField();
			fieldMM.setBounds(260, 136, 54, 28);
			CheckOut.add(fieldMM);
			fieldMM.setColumns(10);
			
			JLabel lblMM = new JLabel("MM");
			lblMM.setBounds(229, 142, 36, 16);
			CheckOut.add(lblMM);
			
			JLabel lblYY = new JLabel("YY");
			lblYY.setBounds(326, 142, 36, 16);
			CheckOut.add(lblYY);
			
			textField_1 = new JTextField();
			textField_1.setColumns(10);
			textField_1.setBounds(351, 136, 54, 28);
			CheckOut.add(textField_1);
			
			JButton btnConfirmPurchase = new JButton("Confirm Purchase");
			btnConfirmPurchase.setBounds(250, 183, 149, 29);
			CheckOut.add(btnConfirmPurchase);
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
	public void createItem(	String upc, String title, EntityClasses.Item.ITEM_TYPE type, 
			EntityClasses.Item.GENRE category, String company, String year, 
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
		stmt.setString(3, EntityClasses.Item.translateType(type));
		stmt.setString(4, EntityClasses.Item.translateGenre(category));
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
}
