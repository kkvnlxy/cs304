import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.GridLayout;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.BoxLayout;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.JTable;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import java.awt.Component;


public class CustomerRegistration extends JDialog {
	//DONE - Ang.
	
	private JTextField cid;
	private JTextField name;
	private JTextField addr;
	private JTextField phone;
	private Connection conn;
	private CustomerRegistration cr = this;
	private JPasswordField pwd;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			CustomerRegistration dialog = new CustomerRegistration();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public CustomerRegistration() {
		getContentPane().setLayout(null);
		getContentPane().setLayout(null);

		JLabel lblId = new JLabel("User ID");
		lblId.setBounds(77, 73, 76, 14);
		getContentPane().add(lblId);

		cid = new JTextField();
		cid.setBounds(181, 70, 176, 20);
		getContentPane().add(cid);
		cid.setColumns(10);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setBounds(77, 98, 76, 14);
		getContentPane().add(lblPassword);

		JLabel lblName = new JLabel("Name");
		lblName.setBounds(77, 128, 76, 14);
		getContentPane().add(lblName);

		JLabel lblAddress = new JLabel("Address");
		lblAddress.setBounds(77, 157, 89, 14);
		getContentPane().add(lblAddress);

		JLabel lblPhoneNumber = new JLabel("Phone Number");
		lblPhoneNumber.setBounds(77, 182, 97, 14);
		getContentPane().add(lblPhoneNumber);

		name = new JTextField();
		name.setBounds(181, 126, 176, 20);
		getContentPane().add(name);
		name.setColumns(10);

		addr = new JTextField();
		addr.setBounds(181, 154, 176, 20);
		getContentPane().add(addr);
		addr.setColumns(10);

		phone = new JTextField();
		phone.setBounds(181, 182, 176, 20);
		getContentPane().add(phone);
		phone.setColumns(10);
		
		getContentPane().setLayout(null);
		setBounds(100, 100, 453, 299);
		getContentPane().setLayout(null);
		getContentPane().add(lblId);
		getContentPane().add(cid);
		getContentPane().add(lblPassword);
		getContentPane().add(lblName);
		getContentPane().add(lblAddress);
		getContentPane().add(lblPhoneNumber);
		getContentPane().add(name);
		getContentPane().add(addr);
		getContentPane().add(phone);

		JLabel lblRegistration = DefaultComponentFactory.getInstance().createTitle("Customer: Registration");
		lblRegistration.setBounds(26, 11, 148, 38);
		getContentPane().add(lblRegistration);

		JButton btnCancel = new JButton("Cancel");
		btnCancel.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				cr.setVisible(false);
			}
		});
		btnCancel.setBounds(338, 226, 89, 23);
		getContentPane().add(btnCancel);

		JButton btnOk = new JButton("Ok");
		btnOk.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});
		btnOk.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseExited(MouseEvent e) {
				String cid_in = cid.getText();
				String pswd_in = pwd.getPassword().toString();
				String name_in = name.getText();
				String adr_in = addr.getText();
				String phone_in = phone.getText();
				
				try {
					if(register(cid_in, pswd_in, name_in, adr_in, phone_in)){
						//TODO:
						System.out.println("You have you been registered");
					}else{
						//TODO
						System.out.println("Error occured, please try again");
					}
				} catch (ClassNotFoundException | IOException | SQLException e1) {
					e1.printStackTrace();
				}
			}
		});
		btnOk.setBounds(237, 226, 89, 23);
		getContentPane().add(btnOk);
		
		pwd = new JPasswordField();
		pwd.setBounds(181, 95, 176, 20);
		getContentPane().add(pwd);
		pwd.setColumns(10);
	}

	public boolean register(String cid, String pswd, String name, String adr,
			String phone) 
					throws ClassNotFoundException, IOException, SQLException
					{
		if(conn == null){
			conn = JDBCConnection.getConnection();
		}
		PreparedStatement stmt = conn.prepareStatement(
				"INSERT INTO Customer " +
				"VALUES(?, ?, ?, ?, ?)");
		stmt.setString(1, cid);
		stmt.setString(2, pswd);
		stmt.setString(3, name);
		stmt.setString(4, adr);
		stmt.setString(5, phone);

		try
		{
			if(stmt.executeUpdate() == 1){
				//sanity check, usually will return 1
				return true;
			}
			else return false;
		}
		catch(SQLException expt)
		//insert unseccessful due to database problem or a customer with same
		//id exist
		{
			System.out.println("SQLException" + expt.getMessage());
			return false;
		}
		finally
		{
			stmt.close();
		}
					}
}
