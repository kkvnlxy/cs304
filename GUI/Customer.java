import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;


public class Customer extends JDialog {

	// Complete - Lucia
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Customer dialog = new Customer();
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
	public Customer() {
		getContentPane().setFont(new Font("Arial Black", Font.PLAIN, 13));
		getContentPane().setBackground(Color.PINK);
		getContentPane().setForeground(Color.BLACK);
		setBounds(100, 100, 442, 233);
		getContentPane().setLayout(null);
		
		JButton btnRegister = new JButton("Register");
		btnRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open Register
				CustomerRegistration reg = new CustomerRegistration();
				reg.setVisible(true);
			}
		});
		btnRegister.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnRegister.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnRegister.setBackground(Color.PINK);
		btnRegister.setBounds(96, 61, 250, 35);
		getContentPane().add(btnRegister);
		
		JButton btnPlaceOrder = new JButton("Place Order");
		btnPlaceOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open CustomerPurchase
				CustomerPurchase purchase = new CustomerPurchase();
				purchase.setVisible(true);
			}
		});
		btnPlaceOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnPlaceOrder.setBackground(Color.PINK);
		btnPlaceOrder.setBounds(96, 108, 250, 35);
		getContentPane().add(btnPlaceOrder);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon("C:\\Users\\john\\Dropbox\\AMSStore\\src\\patrick.gif"));
		label.setBounds(44, 50, 46, 56);
		getContentPane().add(label);

	}
}
