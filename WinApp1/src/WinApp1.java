import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

public class WinApp1 {

	private JFrame frame;
	private JLabel lblClock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinApp1 window = new WinApp1();
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
	public WinApp1() {
		initialize();
		lblClock.setText("CLOCK");
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		lblClock = new JLabel("New label");
		lblClock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblClock.setBounds(76, 65, 130, 40);
		frame.getContentPane().add(lblClock);
	}
}
