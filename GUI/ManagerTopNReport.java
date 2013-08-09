import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Color;


public class ManagerTopNReport extends JDialog {

	private final JPanel contentPanel = new JPanel();
	private JTextField n;
	private JTextField date;
	private ManagerTopNReport tnp = this;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ManagerTopNReport dialog = new ManagerTopNReport();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ManagerTopNReport() {
		setBounds(100, 100, 434, 215);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBounds(0, 0, 434, 193);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		
		JLabel lblManagerTop = DefaultComponentFactory.getInstance().createTitle("Manager: Top N Report");
		lblManagerTop.setBounds(21, 25, 287, 14);
		contentPanel.add(lblManagerTop);
		
		JLabel lblDate = DefaultComponentFactory.getInstance().createLabel("Date");
		lblDate.setBounds(40, 106, 88, 14);
		contentPanel.add(lblDate);
		
		JLabel lblNumberOfItems = DefaultComponentFactory.getInstance().createLabel("Number of Items to Display");
		lblNumberOfItems.setBounds(40, 68, 190, 14);
		contentPanel.add(lblNumberOfItems);
		
		n = new JTextField();
		n.setBounds(240, 65, 151, 20);
		contentPanel.add(n);
		n.setColumns(10);
		
		date = new JTextField();
		date.setBounds(240, 103, 151, 20);
		contentPanel.add(date);
		date.setColumns(10);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBackground(new Color(255, 255, 224));
			buttonPane.setBounds(0, 160, 434, 33);
			contentPanel.add(buttonPane);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			{
				JButton okButton = new JButton("OK");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						TopNReport tnr = new TopNReport();
						tnr.setVisible(true);
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
			{
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent e) {
						tnp.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}
}
