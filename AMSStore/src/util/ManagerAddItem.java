import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JTabbedPane;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.JLabel;

import EntityClasses.Item;

import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;


public class ManagerAddItem extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private ManagerAddItem mai = this;
	private JTextField upc;
	private JTextField stock;
	private JTextField price;
	private JTextField itemUPC;
	private JTextField itemTitle;
	private JTextField itemCompany;
	private JTextField itemYear;
	private JTextField itemPrice;
	private JTextField itemStock;
	private Connection conn;


	private JComboBox itemType;
	private JComboBox itemCategory;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ManagerAddItem dialog = new ManagerAddItem();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ManagerAddItem() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 250);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JTabbedPane tabbedPane = new JTabbedPane(JTabbedPane.TOP);
			tabbedPane.setBounds(0, 0, 434, 261);
			contentPanel.add(tabbedPane);
			{
				JPanel NewItem = new JPanel();
				tabbedPane.addTab("NewItem", null, NewItem, null);
				NewItem.setLayout(null);

				JLabel niUPC = DefaultComponentFactory.getInstance().createLabel("UPC");
				niUPC.setBounds(22, 11, 92, 14);
				NewItem.add(niUPC);

				JLabel niTitle = DefaultComponentFactory.getInstance().createLabel("Title");
				niTitle.setBounds(22, 31, 92, 14);
				NewItem.add(niTitle);

				JLabel niType = DefaultComponentFactory.getInstance().createLabel("Type");
				niType.setBounds(22, 51, 92, 14);
				NewItem.add(niType);

				JLabel niCategory = DefaultComponentFactory.getInstance().createLabel("Category");
				niCategory.setBounds(22, 76, 92, 14);
				NewItem.add(niCategory);

				JLabel niCompany = DefaultComponentFactory.getInstance().createLabel("Company");
				niCompany.setBounds(22, 101, 92, 14);
				NewItem.add(niCompany);

				JLabel niYear = DefaultComponentFactory.getInstance().createLabel("Year");
				niYear.setBounds(22, 126, 92, 14);
				NewItem.add(niYear);

				JLabel niPrice = DefaultComponentFactory.getInstance().createLabel("Price");
				niPrice.setBounds(22, 151, 92, 14);
				NewItem.add(niPrice);

				JLabel niStock = DefaultComponentFactory.getInstance().createLabel("Stock");
				niStock.setBounds(22, 176, 92, 14);
				NewItem.add(niStock);

				itemUPC = new JTextField();
				itemUPC.setBounds(97, 8, 177, 20);
				NewItem.add(itemUPC);
				itemUPC.setColumns(10);

				itemTitle = new JTextField();
				itemTitle.setBounds(97, 28, 177, 20);
				NewItem.add(itemTitle);
				itemTitle.setColumns(10);

				itemCompany = new JTextField();
				itemCompany.setBounds(97, 101, 177, 20);
				NewItem.add(itemCompany);
				itemCompany.setColumns(10);

				itemYear = new JTextField();
				itemYear.setBounds(97, 126, 177, 20);
				NewItem.add(itemYear);
				itemYear.setColumns(10);

				itemPrice = new JTextField();
				itemPrice.setBounds(97, 148, 177, 20);
				NewItem.add(itemPrice);
				itemPrice.setColumns(10);

				itemStock = new JTextField();
				itemStock.setBounds(97, 176, 177, 20);
				NewItem.add(itemStock);
				itemStock.setColumns(10);

				itemType = new JComboBox();
				itemType.setModel(new DefaultComboBoxModel(new String[] {"Choose a type", "CD", "DVD"}));
				itemType.setMaximumRowCount(3);
				itemType.setBounds(97, 48, 177, 20);
				NewItem.add(itemType);

				itemCategory = new JComboBox();
				itemCategory.setModel(new DefaultComboBoxModel(new String[] {"Choose a category", "ROCK", "POP", "RAP", "COUNTRY", "CLASSICAL", "NEW_AGE", "INSTRUMENTAL"}));
				itemCategory.setBounds(97, 73, 177, 20);
				NewItem.add(itemCategory);

				JButton niSubmit = new JButton("Submit");
				niSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				niSubmit.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						//TODO createItem(String upc, String title, EntityClasses.Item.ITEM_TYPE type, 
						//EntityClasses.Item.GENRE category, String company, String year, 
						//int price_incent, int initStk)
						String upc_in = itemUPC.getText();
						String title_in = itemTitle.getText();
						String company_in = itemCompany.getText();
						String year_in = itemYear.getText();
						String price_in = itemPrice.getText();
						String stock_in = itemStock.getText();
						String type_in = itemType.getName();
						String category_in = itemCategory.getName();
						System.out.println(type_in + "wtf" +  category_in);		
					}
				});
				niSubmit.setBounds(330, 199, 89, 23);
				NewItem.add(niSubmit);
			}
			{
				JPanel UpdateStock = new JPanel();
				tabbedPane.addTab("UpdateStock", null, UpdateStock, null);
				UpdateStock.setLayout(null);

				JLabel usUPC = DefaultComponentFactory.getInstance().createLabel("UPC");
				usUPC.setBounds(30, 53, 92, 14);
				UpdateStock.add(usUPC);

				JLabel usStock = DefaultComponentFactory.getInstance().createLabel("Stock");
				usStock.setBounds(30, 81, 92, 14);
				UpdateStock.add(usStock);

				JLabel usPrice = DefaultComponentFactory.getInstance().createLabel("Price");
				usPrice.setBounds(32, 112, 92, 14);
				UpdateStock.add(usPrice);

				upc = new JTextField();
				upc.setBounds(151, 47, 194, 20);
				UpdateStock.add(upc);
				upc.setColumns(10);

				stock = new JTextField();
				stock.setBounds(151, 78, 194, 20);
				UpdateStock.add(stock);
				stock.setColumns(10);

				price = new JTextField();
				price.setBounds(151, 112, 196, 20);
				UpdateStock.add(price);
				price.setColumns(10);

				JButton usSubmit = new JButton("Submit");
				usSubmit.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent arg0) {
					}
				});
				usSubmit.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String upc_in = upc.getText();
						String stock_in = stock.getText();
						String price_in = price.getText();

						if(price_in.equals("")){
							try {
								stockItem(upc_in, Integer.parseInt(stock_in));
							} catch (NumberFormatException
									| ClassNotFoundException | SQLException
									| IOException e) {
								e.printStackTrace();
							}
						}else{
							try {
								//****When entering price, don't include decimal point!!!!!!!!!!!****
								stockItemPrice(upc_in, Integer.parseInt(stock_in), Integer.parseInt(price_in));
							} catch (NumberFormatException
									| ClassNotFoundException | SQLException
									| IOException e) {
								e.printStackTrace();
							}
						}
					}
				});
				usSubmit.setBounds(330, 199, 89, 23);
				UpdateStock.add(usSubmit);
			}
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
