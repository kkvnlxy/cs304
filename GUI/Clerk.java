import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Clerk extends JDialog {

	// Complete - Lucia
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Clerk dialog = new Clerk();
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
	public Clerk() {
		getContentPane().setFont(new Font("Arial Black", Font.PLAIN, 13));
		getContentPane().setBackground(Color.PINK);
		getContentPane().setForeground(Color.BLACK);
		setBounds(100, 100, 442, 233);
		getContentPane().setLayout(null);
		
		JButton btnClerkPurchase = new JButton("Process Purchase");
		btnClerkPurchase.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open ClerkPurchase
				ClerkPurchase cp = new ClerkPurchase();
				cp.setVisible(true);
			}
		});
		btnClerkPurchase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnClerkPurchase.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnClerkPurchase.setBackground(Color.PINK);
		btnClerkPurchase.setBounds(96, 61, 250, 35);
		getContentPane().add(btnClerkPurchase);
		
		JButton btnClerkReturn = new JButton("Process Return");
		btnClerkReturn.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open ClerkReturn
				ClerkReturn cr = new ClerkReturn();
				cr.setVisible(true);
			}
		});
		btnClerkReturn.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnClerkReturn.setBackground(Color.PINK);
		btnClerkReturn.setBounds(96, 108, 250, 35);
		getContentPane().add(btnClerkReturn);

	}
}
