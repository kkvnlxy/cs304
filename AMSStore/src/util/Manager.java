import java.awt.EventQueue;

import javax.swing.JDialog;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;


public class Manager extends JDialog {
	
	// Complete - Lucia
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Manager dialog = new Manager();
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
	public Manager() {
		getContentPane().setFont(new Font("Arial Black", Font.PLAIN, 13));
		getContentPane().setBackground(Color.PINK);
		getContentPane().setForeground(Color.BLACK);
		setBounds(100, 100, 442, 233);
		getContentPane().setLayout(null);
		
		JButton btnManagerAddItem = new JButton("Add Items to the Store");
		btnManagerAddItem.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open AddItem
				ManagerAddItem mai = new ManagerAddItem();
				mai.setVisible(true);
			}
		});
		btnManagerAddItem.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnManagerAddItem.setBackground(Color.PINK);
		btnManagerAddItem.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnManagerAddItem.setBounds(96, 24, 250, 35);
		getContentPane().add(btnManagerAddItem);
		
		JButton btnManagerProcessOrder = new JButton("Process Order Delivery");
		btnManagerProcessOrder.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open AddItem
				ManagerDelivery md = new ManagerDelivery();
				md.setVisible(true);
			}
		});
		btnManagerProcessOrder.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnManagerProcessOrder.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnManagerProcessOrder.setBackground(Color.PINK);
		btnManagerProcessOrder.setBounds(96, 66, 250, 35);
		getContentPane().add(btnManagerProcessOrder);
		
		JButton btnDailySalesReport = new JButton("Daily Sales Report");
		btnDailySalesReport.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open DailyReport
				ManagerDailyReport mdr = new ManagerDailyReport();
				mdr.setVisible(true);
			}
		});
		btnDailySalesReport.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnDailySalesReport.setBackground(Color.PINK);
		btnDailySalesReport.setBounds(96, 108, 250, 35);
		getContentPane().add(btnDailySalesReport);
		
		JButton btnTopSellingItems = new JButton("Top Selling Items");
		btnTopSellingItems.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// Open TopNReport
				ManagerTopNReport mtnr = new ManagerTopNReport();
				mtnr.setVisible(true);
			}
		});
		btnTopSellingItems.setFont(new Font("Lucida Grande", Font.PLAIN, 13));
		btnTopSellingItems.setBackground(Color.PINK);
		btnTopSellingItems.setBounds(96, 150, 250, 35);
		getContentPane().add(btnTopSellingItems);

	}
}
