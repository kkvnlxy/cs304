import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;


public class ManagerDailyReport extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField textField;
	private ManagerDailyReport md = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ManagerDailyReport dialog = new ManagerDailyReport();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ManagerDailyReport() {
		setBounds(100, 100, 455, 215);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBounds(0, 0, 455, 161);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblMangerDaily = DefaultComponentFactory.getInstance().createTitle("Manager: Daily Report");
		lblMangerDaily.setBounds(22, 11, 156, 14);
		contentPanel.add(lblMangerDaily);
		
		JLabel lblDate = DefaultComponentFactory.getInstance().createLabel("Date");
		lblDate.setBounds(101, 76, 88, 14);
		contentPanel.add(lblDate);
		
		textField = new JTextField();
		textField.setBounds(171, 73, 193, 20);
		contentPanel.add(textField);
		textField.setColumns(10);
		{
			{
				MouseAdapter ma = new MouseAdapter() {
					
					@Override
					public void mouseClicked(MouseEvent e) {
						   md.setVisible(false);
					}
				};
			}
		}
		JPanel buttonPane = new JPanel();
		buttonPane.setBackground(new Color(255, 255, 224));
		buttonPane.setBounds(0, 160, 455, 33);
		getContentPane().add(buttonPane);
		buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
		{
			JButton okButton = new JButton("OK");
			okButton.addMouseListener(new MouseAdapter() {
				@Override
				public void mouseClicked(MouseEvent e) {
					DailySalesReport dsr = new DailySalesReport();
					dsr.setVisible(true);
				}
			});
			okButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent arg0) {
							}
			});
			okButton.setActionCommand("OK");
			buttonPane.add(okButton);
			getRootPane().setDefaultButton(okButton);
		}
		JButton cancelButton = new JButton("Cancel");
		cancelButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				md.setVisible(false);
			}
		});
		cancelButton.setActionCommand("Cancel");
		buttonPane.add(cancelButton);
	}
}
