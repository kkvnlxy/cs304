import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import java.awt.Toolkit;


public class FrontPage extends JDialog {

	// Complete - Lucia
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrontPage dialog = new FrontPage();
					dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
					dialog.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the dialog.
	 */
	public FrontPage() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\john\\Desktop\\Cute-Strawberry.png"));
		//getContentPane().setFont(new Font("Arial Black", Font.PLAIN, 13));
		getContentPane().setBackground(Color.PINK);
		getContentPane().setForeground(Color.BLACK);
		setBounds(300, 100, 442, 233);
		
		// Customer Button
		JButton btnCustomer = new JButton("Customer");
		btnCustomer.setBounds(51, 55, 100, 100);
		btnCustomer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnCustomer.setIcon(null);
		btnCustomer.addMouseListener(new MouseAdapter() {
			
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open New Customer
				Customer customer = new Customer();
				customer.setVisible(true);
			}
		});
		btnCustomer.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnCustomer.setBackground(Color.PINK);
		
		// Clerk Button
		JButton btnClerk = new JButton("Clerk");
		btnClerk.setBounds(171, 55, 100, 100);
		btnClerk.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// Open New Clerk
				Clerk clerk = new Clerk();
				clerk.setVisible(true);
			}
		});
		btnClerk.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnClerk.setBackground(Color.PINK);
		
		// Manager Button
		JButton btnManager = new JButton("Manager");
		btnManager.setBounds(291, 55, 100, 100);
		btnManager.addMouseListener(new MouseAdapter() {

			@Override
			public void mouseClicked(MouseEvent e) {
				// Open New Manager
				Manager manager = new Manager();
				manager.setVisible(true);
			}
		});
		btnManager.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnManager.setBackground(Color.PINK);
		getContentPane().setLayout(null);
		getContentPane().add(btnCustomer);
		getContentPane().add(btnClerk);
		getContentPane().add(btnManager);

	}
}
