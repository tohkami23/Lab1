import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class WinApp3 {

	private JFrame frame;
	private JLabel lblClock;
	private boolean clockOn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					WinApp3 window = new WinApp3();
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
	public WinApp3() {
		initialize();
		//lblClock.setText("CLOCK");
		clock();
	}
	
	public class ClockThread extends Thread {
		public void run() {
			while (true) {
				clock();
				try {
					sleep(1000); // 1000 ms
					if (!clockOn) break;
				}
				catch (Exception e) {
					System.out.println("<<< " + e.getMessage());
					break;
				}
			}
        }
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
		
		JButton btnClock = new JButton("Clock On");
		btnClock.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (clockOn) {
		            btnClock.setText("Clock On");
		            clockOn = false;
		        }
		        else {
		            btnClock.setText("Clock Off");
		            clockOn = true;
		            ClockThread ct = new ClockThread();
		            ct.start();
		        }
			}
		});
		btnClock.setFont(new Font("Tahoma", Font.PLAIN, 14));
		btnClock.setBounds(76, 31, 138, 23);
		frame.getContentPane().add(btnClock);
	}
}
