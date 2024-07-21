package TimerApp;

import java.awt.Toolkit;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * TimerApp
 * 
 * @author:Piyush
 */
public class Timer
{

	private static boolean isPaused = false;
	private JLabel label;

	private long timeleft;
	private final Object lock = new Object();
	ScheduledExecutorService scheduler;

	// public void setlabel(JLabel label){ this.label=label;}

	// -------constructor--------------
	public Timer(int hrs, int min, int sec, JLabel display)
	{

		this.label = display;

		this.timeleft = hrs * 3600 + min * 60 + sec;

		this.scheduler = Executors.newScheduledThreadPool(1);
	}

//----------------------------start-----------------
	public void start()
	{
		synchronized (lock)
		{
			if (scheduler.isShutdown())
			{
				scheduler = Executors.newScheduledThreadPool(1);
			}
			isPaused = false;
		}
		Runnable task = new Runnable()
		{

			@Override
			public void run()
			{
				
				if (!isPaused && timeleft >= 0)
				{
					LocalTime time = LocalTime.ofSecondOfDay(timeleft);
					// System.out.println(time.format(DateTimeFormatter.ofPattern("HH:mm:ss")));

					String labelstr = time.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

					label.setText(labelstr); // ------*** label Update ***

					timeleft--;
				} else if (timeleft < 0)
				{
					notification();
					scheduler.shutdown();
				}
			}
		};
		scheduler.scheduleAtFixedRate(task, 0, 1, TimeUnit.SECONDS);

	}
	// -------------------Notification------------------------
	public void notification() 
	{
		Toolkit.getDefaultToolkit().beep();
		JOptionPane.showMessageDialog(null, "Times up!!!", "Notification", JOptionPane.INFORMATION_MESSAGE);

	}

	// --------pause-------------
	public void pause()
	{
		synchronized (lock)
		{
			isPaused = true;
		}
	}

	// --------resume---------
	public void resume()
	{
		synchronized (lock)
		{
			isPaused = false;
		}
	}

	// --------stop---------
	public void stop()
	{
		synchronized (lock)
		{
			scheduler.shutdown();
//			System.out.println("Timer stopped.");
		}
	}

	// -----Reset----------
	public void reset()
	{
		synchronized (lock)
		{
			scheduler.shutdown();
			stop();
			timeleft = 0;
		}
	}
}