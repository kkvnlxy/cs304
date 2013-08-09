import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.Canvas;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


public class ClerkReturn extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField rId;
	private JTextField pDate;
	private JTextField upc;
	private JTextField qty;
	private ClerkReturn cr = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClerkReturn dialog = new ClerkReturn();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClerkReturn() {
		getContentPane().setBackground(Color.PINK);
		setBounds(100, 100, 450, 301);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 450, 1);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblReceiptId = new JLabel("Receipt ID");
		lblReceiptId.setBounds(52, 40, 83, 16);
		contentPanel.add(lblReceiptId);
		
		JLabel label = new JLabel("Receipt ID");
		label.setBounds(52, 66, 83, 16);
		contentPanel.add(label);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 224));
			buttonPane.setBounds(0, 239, 450, 39);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("OK");
				okButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
					
					}
				});
				okButton.setForeground(new Color(0, 0, 0));
				okButton.setBackground(Color.ORANGE);
				okButton.setActionCommand("OK");
				buttonPane.add(okButton);
				getRootPane().setDefaultButton(okButton);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
					}
				});
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						cr.setVisible(false);
					}
				});
				cancelButton.setForeground(new Color(0, 0, 0));
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
		
		JLabel lblClerkProcessReturns = DefaultComponentFactory.getInstance().createTitle("Clerk: Process Returns");
		lblClerkProcessReturns.setForeground(new Color(0, 0, 0));
		lblClerkProcessReturns.setBounds(49, 28, 262, 16);
		getContentPane().add(lblClerkProcessReturns);
		
		JLabel lblReceiptId_1 = DefaultComponentFactory.getInstance().createLabel("Receipt ID:");
		lblReceiptId_1.setForeground(new Color(0, 0, 0));
		lblReceiptId_1.setBounds(101, 78, 68, 16);
		getContentPane().add(lblReceiptId_1);
		
		rId = new JTextField();
		rId.setBounds(187, 72, 182, 28);
		getContentPane().add(rId);
		rId.setColumns(10);
		
		JLabel lblDate = DefaultComponentFactory.getInstance().createLabel("Date:");
		lblDate.setForeground(new Color(0, 0, 0));
		lblDate.setBounds(101, 107, 59, 16);
		getContentPane().add(lblDate);
		
		pDate = new JTextField();
		pDate.setBounds(187, 101, 182, 28);
		getContentPane().add(pDate);
		pDate.setColumns(10);
		
		JLabel lblUpc = DefaultComponentFactory.getInstance().createLabel("UPC:");
		lblUpc.setForeground(new Color(0, 0, 0));
		lblUpc.setBounds(101, 135, 120, 16);
		getContentPane().add(lblUpc);
		
		JLabel lblQuantity = DefaultComponentFactory.getInstance().createLabel("Quantity:");
		lblQuantity.setForeground(new Color(0, 0, 0));
		lblQuantity.setBounds(101, 163, 120, 16);
		getContentPane().add(lblQuantity);
		
		upc = new JTextField();
		upc.setBounds(187, 129, 182, 28);
		getContentPane().add(upc);
		upc.setColumns(10);
		
		qty = new JTextField();
		qty.setBounds(187, 157, 182, 28);
		getContentPane().add(qty);
		qty.setColumns(10);
	}
}
