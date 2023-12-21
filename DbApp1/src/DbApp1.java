import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.sql.*;

public class DbApp1 {

	private JFrame frame;
	private JTextField txtDbName;
	private JTextField txtCsvFile;
	private JComboBox<String> comboBoxList;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					DbApp1 window = new DbApp1();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public DbApp1() {
		initialize();
	}
	
	public void updateComboBoxList() {
		comboBoxList.removeAllItems();
		String[] list = SqliteDb.getList();
		for (String s : list) comboBoxList.addItem(s);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 580, 335);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblDbName = new JLabel("SQLite DB Name");
		lblDbName.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblDbName.setBounds(129, 11, 110, 24);
		frame.getContentPane().add(lblDbName);
		
		JLabel lblCsvFile = new JLabel("CSV Data File");
		lblCsvFile.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblCsvFile.setBounds(129, 42, 110, 24);
		frame.getContentPane().add(lblCsvFile);
		
		txtDbName = new JTextField();
		txtDbName.setText("sqlite1.db");
		txtDbName.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtDbName.setBounds(243, 15, 300, 24);
		frame.getContentPane().add(txtDbName);
		txtDbName.setColumns(10);
		
		txtCsvFile = new JTextField();
		txtCsvFile.setText("../Lab1_files/tennis_players.csv");
		txtCsvFile.setFont(new Font("Consolas", Font.PLAIN, 14));
		txtCsvFile.setBounds(243, 46, 300, 24);
		frame.getContentPane().add(txtCsvFile);
		txtCsvFile.setColumns(10);
		
		JButton btnInit = new JButton("Init");
		btnInit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SqliteDb.setDb(txtDbName.getText());
				SqliteDb.initialize(txtCsvFile.getText());
				updateComboBoxList();
			}
		});
		btnInit.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnInit.setBounds(33, 42, 70, 24);
		frame.getContentPane().add(btnInit);
		
		JButton btnLoad = new JButton("Load");
		btnLoad.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SqliteDb.setDb(txtDbName.getText());
				updateComboBoxList();
			}
		});
		btnLoad.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnLoad.setBounds(33, 11, 70, 24);
		frame.getContentPane().add(btnLoad);
		
		comboBoxList = new JComboBox<String>();
		comboBoxList.setFont(new Font("Tahoma", Font.PLAIN, 14));
		comboBoxList.setBounds(33, 77, 178, 24);
		frame.getContentPane().add(comboBoxList);
	}
}
