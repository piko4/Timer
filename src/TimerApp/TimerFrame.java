package TimerApp;

import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.SoftBevelBorder;
import javax.swing.UIManager;
import javax.swing.border.MatteBorder;
import java.awt.Color;

public class TimerFrame extends JFrame
{

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JLabel timerDisplay;
	private JTextField hours;
	private JTextField minutes;
	private JTextField seconds;
	JButton start;
	Timer timer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{

		SwingUtilities.invokeLater(new Runnable()
		{
			@Override
			public void run()
			{
				try
				{
					TimerFrame frame = new TimerFrame();
					frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public TimerFrame()
	{
		setTitle("Timer");
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 460, 338);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.scrollbar);
		contentPane.setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		// ------------------pause------------------------------
		JButton pause = new JButton("Pause");
		pause.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		pause.setFont(new Font("Tahoma", Font.PLAIN, 12));

		pause.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{

				if (pause.getText().equals("Resume"))
				{
					timer.resume();
					pause.setText("Pause");
				} else
				{
					pause.setText("Resume");
					timer.pause();
				}

			}
		});
		pause.setBounds(180, 226, 85, 32);
		contentPane.add(pause);

		// ----------------------reset-----------------------
		JButton reset = new JButton("Reset");
		reset.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		reset.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				hours.setText("0");
				minutes.setText("0");
				seconds.setText("0");
				timer.reset();
				timerDisplay.setText("00:00:00");

				if (!start.isEnabled())
					start.setEnabled(true);
			}
		});
		reset.setFont(new Font("Tahoma", Font.PLAIN, 12));
		reset.setBounds(329, 226, 85, 32);
		contentPane.add(reset);

		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(39, 32, 375, 14);
		contentPane.add(progressBar);

		// ----------------------------* start *-----------------
		start = new JButton("Start");
		start.setBorder(new SoftBevelBorder(BevelBorder.RAISED, null, null, null, null));
		start.addActionListener(new ActionListener()
		{
			@Override
			public void actionPerformed(ActionEvent e)
			{
				int hrs = Integer.parseInt(hours.getText());
				int min = Integer.parseInt(minutes.getText());
				int sec = Integer.parseInt(seconds.getText());

				timer = new Timer(hrs, min, sec, timerDisplay); // -------constructor--------------

				timer.start();

				hours.setText("0");
				minutes.setText("0");
				seconds.setText("0");
				start.setEnabled(false);
			}
		});

		start.setFont(new Font("Tahoma", Font.PLAIN, 12));
		start.setBounds(39, 226, 85, 32);
		contentPane.add(start);

		// --------------------Display label--------------------------
		timerDisplay = new JLabel("00:00:00");
		timerDisplay.setBorder(new BevelBorder(BevelBorder.LOWERED, null, null, null, null));
		timerDisplay.setFont(new Font("Tahoma", Font.PLAIN, 70));
		timerDisplay.setHorizontalAlignment(SwingConstants.CENTER);
		timerDisplay.setBounds(39, 56, 375, 81);
		contentPane.add(timerDisplay);


		hours = new JTextField();
		hours.setText("0");
		hours.setBounds(136, 148, 44, 27);
		contentPane.add(hours);
		hours.setColumns(10);

		JLabel lblNewLabel_1 = new JLabel("HH");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1.setBounds(136, 173, 44, 32);
		contentPane.add(lblNewLabel_1);

		JLabel lblNewLabel_1_1 = new JLabel(":");
		lblNewLabel_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1.setBounds(180, 148, 25, 27);
		contentPane.add(lblNewLabel_1_1);

		minutes = new JTextField();
		minutes.setText("0");
		minutes.setColumns(10);
		minutes.setBounds(202, 148, 44, 27);
		contentPane.add(minutes);

		seconds = new JTextField();
		seconds.setText("0");
		seconds.setColumns(10);
		seconds.setBounds(267, 148, 44, 27);
		contentPane.add(seconds);

		JLabel lblNewLabel_1_1_1 = new JLabel(":");
		lblNewLabel_1_1_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_1_1.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_1_1.setBounds(243, 147, 25, 25);
		contentPane.add(lblNewLabel_1_1_1);

		JLabel lblNewLabel_1_2 = new JLabel("mm");
		lblNewLabel_1_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_2.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_2.setBounds(202, 173, 44, 32);
		contentPane.add(lblNewLabel_1_2);

		JLabel lblNewLabel_1_3 = new JLabel("ss");
		lblNewLabel_1_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1_3.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblNewLabel_1_3.setBounds(268, 173, 44, 32);
		contentPane.add(lblNewLabel_1_3);
	}
}
