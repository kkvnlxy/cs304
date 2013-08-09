import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JSplitPane;
import javax.swing.JInternalFrame;
import javax.swing.JSeparator;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import javax.swing.SwingConstants;


public class ClerkPurchase extends JDialog {

	private JPanel contentPanel = new JPanel();
	private JTextField fieldUPC;
	private JTextField fieldQuantity;
	private JTextField fieldCreditCard;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ClerkPurchase dialog = new ClerkPurchase();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ClerkPurchase() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new BorderLayout());
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel, BorderLayout.CENTER);
		contentPanel.setLayout(null);
		{
			JLabel lblAddItemTo = DefaultComponentFactory.getInstance().createTitle("Clerk: Process Purchase");
			lblAddItemTo.setBounds(36, 20, 250, 16);
			contentPanel.add(lblAddItemTo);
		}
		{
			JButton button = new JButton("Add Item");
			button.setBounds(336, 105, 90, 29);
			contentPanel.add(button);
		}
		
		JLabel lblQuantity = DefaultComponentFactory.getInstance().createLabel("Quantity");
		lblQuantity.setBounds(296, 78, 82, 16);
		contentPanel.add(lblQuantity);
		
		JLabel lblUPC = DefaultComponentFactory.getInstance().createLabel("UPC");
		lblUPC.setBounds(325, 50, 30, 16);
		contentPanel.add(lblUPC);
		
		fieldUPC = new JTextField();
		fieldUPC.setBounds(356, 44, 70, 28);
		contentPanel.add(fieldUPC);
		fieldUPC.setColumns(10);
		
		fieldQuantity = new JTextField();
		fieldQuantity.setBounds(356, 72, 70, 28);
		contentPanel.add(fieldQuantity);
		fieldQuantity.setColumns(10);
		
		JPanel panel = new JPanel();
		panel.setBounds(36, 44, 159, 180);
		contentPanel.add(panel);
		
		JLabel lblCreditCardoptional = DefaultComponentFactory.getInstance().createLabel("Credit Card (Optional)");
		lblCreditCardoptional.setHorizontalAlignment(SwingConstants.RIGHT);
		lblCreditCardoptional.setBounds(238, 146, 188, 16);
		contentPanel.add(lblCreditCardoptional);
		
		fieldCreditCard = new JTextField();
		fieldCreditCard.setColumns(10);
		fieldCreditCard.setBounds(238, 165, 188, 28);
		contentPanel.add(fieldCreditCard);
		
		JButton btnAuthorize = new JButton("Authorize");
		btnAuthorize.setBounds(336, 195, 90, 29);
		contentPanel.add(btnAuthorize);
		
		JButton btnClear = new JButton("Clear");
		btnClear.setBounds(263, 195, 76, 29);
		contentPanel.add(btnClear);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 224));
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane, BorderLayout.SOUTH);
			{
				JButton btnCompletePurchase = new JButton("Complete Purchase");
				buttonPane.add(btnCompletePurchase);
				getRootPane().setDefaultButton(btnCompletePurchase);
			}
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
