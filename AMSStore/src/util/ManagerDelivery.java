import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import com.jgoodies.forms.factories.DefaultComponentFactory;
import javax.swing.JTextField;


import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;


public class ManagerDelivery extends JDialog {
// DONE - ANG :D
	
	private final JPanel contentPanel = new JPanel();
	private JTextField r_Id;
	private JTextField dDate;
	private ManagerDelivery md = this;
	private Connection conn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		try {
			ManagerDelivery dialog = new ManagerDelivery();
			dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
			dialog.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Create the dialog.
	 */
	public ManagerDelivery() {
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(null);
		contentPanel.setBounds(0, 0, 434, 228);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Receipt ID");
			lblNewJgoodiesLabel.setBounds(77, 92, 92, 14);
			contentPanel.add(lblNewJgoodiesLabel);
		}

		JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Delivered Date");
		lblNewJgoodiesLabel_1.setBounds(77, 117, 92, 14);
		contentPanel.add(lblNewJgoodiesLabel_1);

		r_Id = new JTextField();
		r_Id.setBounds(179, 89, 133, 20);
		contentPanel.add(r_Id);
		r_Id.setColumns(10);

		dDate = new JTextField();
		dDate.setBounds(179, 114, 133, 20);
		contentPanel.add(dDate);
		dDate.setColumns(10);

		JLabel lblManagerUpdate = DefaultComponentFactory.getInstance().createTitle("Manager : Update Delivered Date");
		lblManagerUpdate.setBounds(23, 20, 224, 32);
		contentPanel.add(lblManagerUpdate);
		{
			JPanel buttonPane = new JPanel();
			buttonPane.setBounds(0, 228, 434, 33);
			buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
			getContentPane().add(buttonPane);
			{
				JButton okButton = new JButton("Submit");
				okButton.addMouseListener(new MouseAdapter() {
					@Override
					public void mouseClicked(MouseEvent arg0) {
						String rid_in = r_Id.getText();
						String date_in = dDate.getText();

						try{
							if(processDelivery(rid_in, date_in)){
								System.out.println("DeliveredDate updated");
							}else{
								System.out.println("Error occured, please try again");
							}
						}catch (ClassNotFoundException | IOException | SQLException e1) {
							e1.printStackTrace();
						}
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
						md.setVisible(false);
					}
				});
				cancelButton.setActionCommand("Cancel");
				buttonPane.add(cancelButton);
			}
		}
	}

	public boolean processDelivery(String r_id, String date)
			throws SQLException, ClassNotFoundException, IOException
			{
		if(this.conn == null)
			this.conn = JDBCConnection.getConnection();
		PreparedStatement stmt = conn.prepareStatement(
				"UPDATE Purchase " +
				"SET deliveredDate = to_date(' ? ' , 'dd-mm-yyyy')" +
				"WHERE receiptId = ? AND cid IS NOT NULL");

		stmt.setString(1, date);
		stmt.setString(2, r_id);
		try
		{
			int update = stmt.executeUpdate();
			if(update == 1)
				return true;
			else if(update == 0)
				//if none is update
				throw new SQLException("Invalid ReceiptId");
			else
				//Fatal error: more than 1 tuple is updated -> duplicate receiptId
				throw new SQLException("Fatal Error: Duplicate receiptId");
		}
		finally
		{
			stmt.close();
		}
			}
}
