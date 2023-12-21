import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import java.util.Calendar;
import java.util.GregorianCalendar;

public class WinApp2 {

	private JFrame frame;
	private JLabel lblClock;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinApp2 window = new WinApp2();
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
	public WinApp2() {
		initialize();
		//lblClock.setText("CLOCK");
		clock();
	}
	
	public void clock() {
	    Calendar cal = new GregorianCalendar();
	    int year = cal.get(Calendar.YEAR);
	    int month = cal.get(Calendar.MONTH) + 1;
	    int day = cal.get(Calendar.DAY_OF_MONTH);
	    int hour = cal.get(Calendar.HOUR_OF_DAY);
	    int minute = cal.get(Calendar.MINUTE);
	    int second = cal.get(Calendar.SECOND);
	    lblClock.setText(year + "-" + month + "-" + day + " " + hour + ":" + minute + ":" + second);
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
