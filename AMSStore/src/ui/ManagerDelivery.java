package ui;
import java.awt.BorderLayout;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import com.jgoodies.forms.factories.DefaultComponentFactory;

import javax.swing.JTextField;

import util.JDBCConnection;



import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;


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
		getContentPane().setBackground(Color.PINK);
		setBounds(100, 100, 600, 400);
		getContentPane().setLayout(null);
		contentPanel.setBackground(Color.PINK);
		contentPanel.setBounds(0, 0, 600, 378);
		contentPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		getContentPane().add(contentPanel);
		contentPanel.setLayout(null);
		{
			JLabel lblNewJgoodiesLabel = DefaultComponentFactory.getInstance().createLabel("Receipt ID");
			lblNewJgoodiesLabel.setBounds(119, 145, 92, 14);
			contentPanel.add(lblNewJgoodiesLabel);
		}
		
				JLabel lblNewJgoodiesLabel_1 = DefaultComponentFactory.getInstance().createLabel("Delivered Date");
				lblNewJgoodiesLabel_1.setBounds(119, 189, 92, 14);
				contentPanel.add(lblNewJgoodiesLabel_1);
				
						r_Id = new JTextField();
						r_Id.setBounds(252, 142, 236, 20);
						contentPanel.add(r_Id);
						r_Id.setColumns(10);
						
								dDate = new JTextField();
								dDate.setBounds(252, 186, 236, 20);
								contentPanel.add(dDate);
								dDate.setColumns(10);
								
										JLabel lblManagerUpdate = DefaultComponentFactory.getInstance().createTitle("Manager : Update Delivered Date");
										lblManagerUpdate.setBounds(46, 37, 224, 32);
										contentPanel.add(lblManagerUpdate);
										{
											JPanel buttonPane = new JPanel();
											buttonPane.setBounds(0, 339, 600, 39);
											contentPanel.add(buttonPane);
											buttonPane.setBackground(new Color(255, 255, 224));
											buttonPane.setLayout(new FlowLayout(FlowLayout.RIGHT));
											{
												JButton okButton = new JButton("Update");
												okButton.addActionListener(new ActionListener() {
													public void actionPerformed(ActionEvent arg0) {
													}
												});
												okButton.addMouseListener(new MouseAdapter() {
													@Override
													public void mouseClicked(MouseEvent arg0) {
														String rid_in = r_Id.getText();
														String date_in = dDate.getText();

														try{
															if(processDelivery(rid_in, date_in)){
																JOptionPane.showMessageDialog(null, "Successful: Delivered Date Updated!");	
															}else{
																JOptionPane.showMessageDialog(null, "Error occurred: Please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
															}
														}catch (ClassNotFoundException | IOException | SQLException e1) {
															e1.printStackTrace();
															JOptionPane.showMessageDialog(null, "Error: Please try again", "ERROR", JOptionPane.ERROR_MESSAGE);
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

		String[] buffer = date.split("-");
		if(buffer.length != 3)
		//sanity check
			JOptionPane.showMessageDialog(null, "Wrong date input format", "ERROR", JOptionPane.ERROR_MESSAGE);
		String sql = "UPDATE Purchase " +
			 	 	 "SET deliveredDate = to_date('" + buffer[0] + "-" + buffer[1] + "-" + buffer[2] + "', 'dd-mm-yyyy') " +
			 	 	 "WHERE receiptId = '" + r_id + "' AND cid IS NOT NULL";

		PreparedStatement stmt = conn.prepareStatement(sql);

		try
		{
			int update = stmt.executeUpdate();
			conn.commit();
			if(update == 1)
				return true;
			else if(update == 0){
				//if none is update
				JOptionPane.showMessageDialog(null, "Error occurred: Invalid Receipt ID", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			else{
				//Fatal error: more than 1 tuple is updated -> duplicate receiptId
				JOptionPane.showMessageDialog(null, "Fatal Error occurred: Duplicate ReceiptID", "ERROR", JOptionPane.ERROR_MESSAGE);
				return false;
			}
		}
		finally
		{
			stmt.close();
		}
			}
}
