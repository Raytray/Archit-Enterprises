import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JButton;
import javax.swing.JLabel;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.io.IOException;

import javax.swing.JPanel;
import lejos.pc.comm.NXTCommException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JScrollPane;

public class GUI 
{
	private BaseStation station;
	private JFrame frame;
	private JTextField txtA, txtD, txtS, txtW, txtT, txtHeaderMovementControls,
	txtUltrasonic, txtTouch, txtLight, txtMicrophone, inputFieldSpeed,
	txtSpeed, txtConnectionOn, txtConnectionOff;
	private JButton btnTerminateConnection, refreshSpeed;
	private boolean wIsPressed, aIsPressed, sIsPressed, dIsPressed, tIsPressed,
	stopped, valid, valueHolder, qIsPressed;
	private JTextPane errorPane;
	private String errors;
	private int speed, previousSpeed;
	private static GUI window;
	private final int MAX_SPEED = 720;
	private final int MIN_SPEED = 0;
	private final int SPEED_CHANGE_INCREMENT = 10;
	public JTextField txtFieldMicrophone, txtFieldLight, txtFieldTouch,
	txtFieldUltrasonic;
	private static JLabel architEnterprisesLogo;
	private JScrollPane scrollPane;
	private JTextField txtQ;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) 
	{
		EventQueue.invokeLater(new Runnable() 
		{
			public void run() 
			{
				try 
				{
					window = new GUI();
					window.frame.setVisible(true);
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * 
	 * @throws NXTCommException
	 * @throws IOException
	 */
	public GUI() throws NXTCommException, IOException 
	{
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws NXTCommException
	 * @throws IOException
	 */
	private void initialize() throws NXTCommException, IOException 
	{
		wIsPressed = false;
		dIsPressed = false;
		sIsPressed = false;
		aIsPressed = false;
		tIsPressed = false;
		qIsPressed = false;
		speed = 100;
		previousSpeed = 100;
		station = new BaseStation();
		errors = "Error pane initialized";
		frame = new JFrame("Control Station");
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setFocusable(true);
		makeControlListener(frame);

		JPanel panel = new JPanel();
		panel.setBounds(10, 358, 464, 92);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

		architEnterprisesLogo = new JLabel();
		architEnterprisesLogo.setBounds(35, 0, 400, 50);
		frame.getContentPane().add(architEnterprisesLogo);
		architEnterprisesLogo.setIcon(new javax.swing.ImageIcon("src/Archit_Logo.png"));
		txtA = new JTextField();
		txtA.setForeground(Color.ORANGE);
		txtA.setBounds(0, 67, 86, 20);
		panel.add(txtA);
		txtA.setBackground(Color.BLUE);
		txtA.setHorizontalAlignment(SwingConstants.CENTER);
		txtA.setEditable(false);
		txtA.setText("A");
		txtA.setColumns(10);

		txtD = new JTextField();
		txtD.setForeground(Color.ORANGE);
		txtD.setBackground(Color.BLUE);
		txtD.setBounds(192, 67, 86, 20);
		panel.add(txtD);
		txtD.setHorizontalAlignment(SwingConstants.CENTER);
		txtD.setEditable(false);
		txtD.setText("D");
		txtD.setColumns(10);

		txtS = new JTextField();
		txtS.setForeground(Color.ORANGE);
		txtS.setBackground(Color.BLUE);
		txtS.setBounds(96, 67, 86, 20);
		panel.add(txtS);
		txtS.setHorizontalAlignment(SwingConstants.CENTER);
		txtS.setEditable(false);
		txtS.setText("S");
		txtS.setColumns(10);

		txtW = new JTextField();
		txtW.setForeground(Color.ORANGE);
		txtW.setBackground(Color.BLUE);
		txtW.setBounds(96, 36, 86, 20);
		panel.add(txtW);
		txtW.setHorizontalAlignment(SwingConstants.CENTER);
		txtW.setEditable(false);
		txtW.setText("W");
		txtW.setColumns(10);

		txtHeaderMovementControls = new JTextField();
		txtHeaderMovementControls.setBackground(Color.ORANGE);
		txtHeaderMovementControls.setForeground(Color.BLUE);
		txtHeaderMovementControls.setBounds(0, 0, 464, 30);
		panel.add(txtHeaderMovementControls);
		txtHeaderMovementControls.setHorizontalAlignment(SwingConstants.CENTER);
		txtHeaderMovementControls.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtHeaderMovementControls.setEditable(false);
		txtHeaderMovementControls.setText("Click for Movement Controls");
		txtHeaderMovementControls.setColumns(10);
		makeControlListener(txtHeaderMovementControls);

		txtT = new JTextField();
		txtT.setBounds(328, 41, 40, 40);
		panel.add(txtT);
		txtT.setForeground(Color.ORANGE);
		txtT.setBackground(Color.BLUE);
		txtT.setHorizontalAlignment(SwingConstants.CENTER);
		txtT.setEditable(false);
		txtT.setText("T");
		txtT.setColumns(10);

		txtQ = new JTextField();
		txtQ.setText("Q");
		txtQ.setHorizontalAlignment(SwingConstants.CENTER);
		txtQ.setForeground(Color.ORANGE);
		txtQ.setEditable(false);
		txtQ.setColumns(10);
		txtQ.setBackground(Color.BLUE);
		txtQ.setBounds(375, 41, 40, 40);
		panel.add(txtQ);

		txtFieldMicrophone = new JTextField();
		txtFieldMicrophone.setText("45");
		txtFieldMicrophone.setEditable(false);
		txtFieldMicrophone.setBackground(Color.green);
		txtFieldMicrophone.setBounds(356, 87, 43, 20);
		frame.getContentPane().add(txtFieldMicrophone);
		txtFieldMicrophone.setColumns(10);

		txtFieldLight = new JTextField();
		txtFieldLight.setEditable(false);
		txtFieldLight.setText("50");
		txtFieldLight.setBackground(Color.green);
		txtFieldLight.setBounds(356, 118, 43, 20);
		frame.getContentPane().add(txtFieldLight);
		txtFieldLight.setColumns(10);

		txtFieldTouch = new JTextField();
		txtFieldTouch.setText("FALSE");
		txtFieldTouch.setEditable(false);
		txtFieldTouch.setBackground(Color.green);
		txtFieldTouch.setBounds(356, 149, 43, 20);
		frame.getContentPane().add(txtFieldTouch);
		txtFieldTouch.setColumns(10);

		txtFieldUltrasonic = new JTextField();
		txtFieldUltrasonic.setText("35");
		txtFieldUltrasonic.setEditable(false);
		txtFieldUltrasonic.setBackground(Color.green);
		txtFieldUltrasonic.setBounds(356, 180, 43, 20);
		frame.getContentPane().add(txtFieldUltrasonic);
		txtFieldUltrasonic.setColumns(10);

		txtUltrasonic = new JTextField();
		txtUltrasonic.setForeground(Color.BLUE);
		txtUltrasonic.setBackground(Color.ORANGE);
		txtUltrasonic.setEditable(false);
		txtUltrasonic.setHorizontalAlignment(SwingConstants.CENTER);
		txtUltrasonic.setText("Ultrasonic");
		txtUltrasonic.setBounds(261, 180, 86, 20);
		frame.getContentPane().add(txtUltrasonic);
		txtUltrasonic.setColumns(10);

		txtTouch = new JTextField();
		txtTouch.setForeground(Color.BLUE);
		txtTouch.setBackground(Color.ORANGE);
		txtTouch.setEditable(false);
		txtTouch.setHorizontalAlignment(SwingConstants.CENTER);
		txtTouch.setText("Touch");
		txtTouch.setBounds(261, 149, 86, 20);
		frame.getContentPane().add(txtTouch);
		txtTouch.setColumns(10);

		txtLight = new JTextField();
		txtLight.setForeground(Color.BLUE);
		txtLight.setBackground(Color.ORANGE);
		txtLight.setEditable(false);
		txtLight.setHorizontalAlignment(SwingConstants.CENTER);
		txtLight.setText("Light");
		txtLight.setBounds(261, 118, 86, 20);
		frame.getContentPane().add(txtLight);
		txtLight.setColumns(10);

		txtMicrophone = new JTextField();
		txtMicrophone.setForeground(Color.BLUE);
		txtMicrophone.setBackground(Color.ORANGE);
		txtMicrophone.setEditable(false);
		txtMicrophone.setHorizontalAlignment(SwingConstants.CENTER);
		txtMicrophone.setText("Microphone");
		txtMicrophone.setBounds(261, 87, 86, 20);
		frame.getContentPane().add(txtMicrophone);
		txtMicrophone.setColumns(10);

		inputFieldSpeed = new JTextField();
		inputFieldSpeed.setText("100");
		inputFieldSpeed.setBounds(356, 209, 43, 20);
		frame.getContentPane().add(inputFieldSpeed);
		inputFieldSpeed.setColumns(10);

		txtSpeed = new JTextField();
		txtSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		txtSpeed.setText("Speed");
		txtSpeed.setForeground(Color.BLUE);
		txtSpeed.setEditable(false);
		txtSpeed.setColumns(10);
		txtSpeed.setBackground(Color.ORANGE);
		txtSpeed.setBounds(261, 209, 86, 20);
		frame.getContentPane().add(txtSpeed);

		txtConnectionOn = new JTextField();
		txtConnectionOn.setEditable(false);
		txtConnectionOn.setHorizontalAlignment(SwingConstants.CENTER);
		txtConnectionOn.setText("On");
		txtConnectionOn.setBounds(274, 292, 86, 20);
		frame.getContentPane().add(txtConnectionOn);
		txtConnectionOn.setColumns(10);

		txtConnectionOff = new JTextField();
		txtConnectionOff.setHorizontalAlignment(SwingConstants.CENTER);
		txtConnectionOff.setText("Off");
		txtConnectionOff.setEditable(false);
		txtConnectionOff.setBounds(370, 292, 86, 20);
		frame.getContentPane().add(txtConnectionOff);
		txtConnectionOff.setColumns(10);
		txtConnectionOff.setBackground(Color.red);

		makeControlListener(txtFieldMicrophone);
		makeControlListener(txtFieldLight);
		makeControlListener(txtFieldUltrasonic);
		makeControlListener(txtFieldTouch);
		makeControlListener(txtA);
		makeControlListener(txtS);
		makeControlListener(txtD);
		makeControlListener(txtW);
		makeControlListener(txtT);
		makeControlListener(txtQ);
		makeControlListener(txtMicrophone);
		makeControlListener(txtSpeed);
		makeControlListener(txtUltrasonic);
		makeControlListener(txtTouch);
		makeControlListener(txtLight);

		JButton btnConnection = new JButton("Initiate Connection");
		btnConnection.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					station.establishConnection();
					txtConnectionOn.setBackground(Color.green);
					txtConnectionOff.setBackground(Color.lightGray);
				} 
				catch (NXTCommException | IOException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnConnection.setBounds(274, 258, 182, 23);
		frame.getContentPane().add(btnConnection);
		makeControlListener(btnConnection);

		JButton refreshMicro = new JButton("Refresh");
		refreshMicro.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					station.getMicrophoneSensor();
					txtFieldMicrophone.setText(Integer.toString(station
							.getMicrophoneValue()));
					if (station.getMicrophoneValue() > 50) 
					{
						txtFieldMicrophone.setBackground(Color.red);
					} 
					else 
					{
						txtFieldMicrophone.setBackground(Color.green);
					}
				} 
				catch (IOException | InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		refreshMicro.setFont(new Font("Tahoma", Font.PLAIN, 9));
		refreshMicro.setBounds(405, 86, 69, 23);
		frame.getContentPane().add(refreshMicro);

		JButton refreshLight = new JButton("Refresh");
		refreshLight.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					station.getLightSensor();
					txtFieldLight.setText(Integer.toString(station
							.getLightValue()));
					if (station.getLightValue() < 50) 
					{
						txtFieldLight.setBackground(Color.red);
					} 
					else 
					{
						txtFieldLight.setBackground(Color.green);
					}
				} 
				catch (IOException | InterruptedException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshLight.setFont(new Font("Tahoma", Font.PLAIN, 9));
		refreshLight.setBounds(405, 117, 69, 23);
		frame.getContentPane().add(refreshLight);

		JButton refreshTouch = new JButton("Refresh");
		refreshTouch.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					station.getTouchSensor();
					Thread.sleep(250);
					valueHolder = station.getTouchValue();
					if (valueHolder) 
					{
						txtFieldTouch.setText("TRUE");
						txtFieldTouch.setBackground(Color.red);
					} 
					else 
					{
						txtFieldTouch.setText("FALSE");
						txtFieldTouch.setBackground(Color.green);
					}
				} 
				catch (IOException | InterruptedException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshTouch.setFont(new Font("Tahoma", Font.PLAIN, 9));
		refreshTouch.setBounds(405, 148, 69, 23);
		frame.getContentPane().add(refreshTouch);

		JButton refreshUltra = new JButton("Refresh");
		refreshUltra.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					station.getUltraSensor();
					txtFieldUltrasonic.setText(Integer.toString(station
							.getUltrasonicValue()));
					if (station.getUltrasonicValue() < 25) 
					{
						txtFieldUltrasonic.setBackground(Color.red);
					} 
					else 
					{
						txtFieldUltrasonic.setBackground(Color.green);
					}
				} 
				catch (IOException | InterruptedException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		refreshUltra.setFont(new Font("Tahoma", Font.PLAIN, 9));
		refreshUltra.setBounds(405, 179, 69, 23);
		frame.getContentPane().add(refreshUltra);

		JButton refreshAll = new JButton("Refresh All Sensors");
		refreshAll.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					station.readSensors();
					Thread.sleep(250);
					txtFieldUltrasonic.setText(Integer.toString(station
							.getUltrasonicValue()));
					if (station.getUltrasonicValue() < 25) 
					{
						txtFieldUltrasonic.setBackground(Color.red);
					} 
					else 
					{
						txtFieldUltrasonic.setBackground(Color.green);
					}
					valueHolder = station.getTouchValue();
					if (valueHolder) 
					{
						txtFieldTouch.setText("TRUE");
						txtFieldTouch.setBackground(Color.red);
					} 
					else 
					{
						txtFieldTouch.setText("FALSE");
						txtFieldTouch.setBackground(Color.green);
					}
					txtFieldLight.setText(Integer.toString(station
							.getLightValue()));
					if (station.getLightValue() < 50) 
					{
						txtFieldLight.setBackground(Color.red);
					} 
					else 
					{
						txtFieldLight.setBackground(Color.green);
					}
					txtFieldMicrophone.setText(Integer.toString(station
							.getMicrophoneValue()));
					if (station.getMicrophoneValue() > 50) 
					{
						txtFieldMicrophone.setBackground(Color.red);
					} 
					else 
					{
						txtFieldMicrophone.setBackground(Color.green);
					}
				} 
				catch (IOException | InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		refreshAll.setBounds(281, 59, 172, 23);
		frame.getContentPane().add(refreshAll);

		btnTerminateConnection = new JButton("Terminate Connection");
		btnTerminateConnection.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent arg0) 
			{
				try 
				{
					station.exitRobot();
					System.exit(0);
				} 
				catch (IOException | InterruptedException e) 
				{
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnTerminateConnection.setBounds(274, 323, 182, 23);
		frame.getContentPane().add(btnTerminateConnection);
		makeControlListener(btnTerminateConnection);

		refreshSpeed = new JButton("Refresh");
		refreshSpeed.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					station.setSpeed(Integer.parseInt(inputFieldSpeed.getText()));
				} 
				catch (NumberFormatException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} 
				catch (InterruptedException e2) 
				{
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		});
		refreshSpeed.setFont(new Font("Tahoma", Font.PLAIN, 9));
		refreshSpeed.setBounds(405, 208, 69, 23);
		frame.getContentPane().add(refreshSpeed);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 180, 241, 167);
		frame.getContentPane().add(scrollPane);

		errorPane = new JTextPane();
		scrollPane.setViewportView(errorPane);
		errorPane.setForeground(Color.RED);
		errorPane.setEditable(false);
		errorPane.setText(errors.toString());
		makeControlListener(errorPane);

		JButton btnRefreshErrors = new JButton("Refresh Errors ");
		btnRefreshErrors.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				errors = station.getErrors();
				station.clearErrors();
				printError(errors);
			}
		});
		btnRefreshErrors.setBounds(50, 148, 157, 23);
		frame.getContentPane().add(btnRefreshErrors);
		makeControlListener(btnRefreshErrors);
		makeControlListener(refreshAll);
		makeControlListener(refreshMicro);
		makeControlListener(refreshUltra);
		makeControlListener(refreshSpeed);
		makeControlListener(refreshTouch);
		makeControlListener(refreshLight);
		makeControlListener(txtConnectionOff);
		makeControlListener(txtConnectionOn);
	}

	private void makeControlListener(Component c) 
	{
		c.addKeyListener(new KeyAdapter() 
		{
			public void keyPressed(KeyEvent e) 
			{
				try 
				{
					controlPress(e.getKeyChar());
				} 
				catch (IOException | InterruptedException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			public void keyReleased(KeyEvent e) 
			{
				try 
				{
					controlRelease(e.getKeyChar());
				} 
				catch (IOException | InterruptedException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void controlPress(char key) throws IOException, InterruptedException 
	{
		previousSpeed = speed;
		if (key != ',' && key != '.') 
		{
			try 
			{
				speed = Integer.parseInt(inputFieldSpeed.getText());
				if (speed < MIN_SPEED) 
				{
					speed = previousSpeed;
					inputFieldSpeed.setText(String.valueOf(speed));
					throw new IllegalArgumentException();
				}
				if (speed > MAX_SPEED) 
				{
					speed = MAX_SPEED;
					inputFieldSpeed.setText(String.valueOf(speed));
					throw new IllegalArgumentException();
				}
				valid = true;
			} 
			catch (IllegalArgumentException e) 
			{
				printError("Speed must be between " + MIN_SPEED + " and "
						+ MAX_SPEED + " inclusive! Speed has been reset.");
				valid = false;
				speed = previousSpeed;
				inputFieldSpeed.setText(Integer.toString(speed));
			} 
			catch (Exception e) 
			{
				printError("Invalid speed! Speed has been reset.");
				valid = false;
				speed = previousSpeed;
				inputFieldSpeed.setText(Integer.toString(speed));
			}
		}
		if (valid) 
		{
			if (key == ',' && speed > MIN_SPEED) 
			{
				if (speed >= MIN_SPEED + SPEED_CHANGE_INCREMENT)
					speed -= SPEED_CHANGE_INCREMENT;
				else
					speed = MIN_SPEED;
			}
			if (key == '.' && speed < MAX_SPEED) 
			{
				if (speed <= MAX_SPEED - SPEED_CHANGE_INCREMENT)
					speed += SPEED_CHANGE_INCREMENT;
				else
					speed = MAX_SPEED;

			}
			if (key == 'q' && !qIsPressed) 
			{
				txtQ.setBackground(Color.orange);
				qIsPressed = true;
				station.swing();
			}
			if (key == 't' && !tIsPressed) 
			{
				txtT.setBackground(Color.orange);
				tIsPressed = true;
				station.turnRight(180);
			}
			if (key == 'w' && !wIsPressed && !sIsPressed) 
			{
				txtW.setBackground(Color.orange);
				wIsPressed = true;
				if (aIsPressed) 
				{
					station.moveForwardLeft();
				} 
				else if (dIsPressed) 
				{
					station.moveForwardRight();
				} 
				else 
				{
					station.moveForward();
				}

			}
			if (key == 'd' && !dIsPressed && !aIsPressed) 
			{
				txtD.setBackground(Color.orange);
				dIsPressed = true;
				if (wIsPressed) 
				{
					station.moveForwardRight();
				} 
				else if (sIsPressed) 
				{
					station.moveBackwardRight();
				}
				else 
				{
					station.turnRight(0);
				}
			}
			if (key == 'a' && !aIsPressed && !dIsPressed) 
			{
				txtA.setBackground(Color.orange);
				aIsPressed = true;
				if (wIsPressed) 
				{
					station.moveForwardLeft();
				} 
				else if (sIsPressed) 
				{
					station.moveBackwardLeft();
				} 
				else 
				{
					station.turnLeft(0);
				}
			}
			if (key == 's' && !sIsPressed && !wIsPressed) 
			{
				txtS.setBackground(Color.orange);
				sIsPressed = true;
				if (aIsPressed) 
				{
					station.moveBackwardLeft();
				} 
				else if (dIsPressed) 
				{
					station.moveBackwardRight();
				} 
				else 
				{
					station.moveBackward();
				}
			}
			stopped = !(wIsPressed || aIsPressed || sIsPressed || dIsPressed || tIsPressed
					|| qIsPressed);
			inputFieldSpeed.setText(String.valueOf(speed));
		}
	}

	private void controlRelease(char key) throws IOException,
	InterruptedException 
	{
		if (valid) 
		{
			switch (key) 
			{// TODO: Break on A and D?
			case 't':
				txtT.setBackground(Color.blue);
				tIsPressed = false;
				break;
			case 'q':
				txtQ.setBackground(Color.blue);
				qIsPressed = false;
				break;
			case 'w':
				txtW.setBackground(Color.blue);
				wIsPressed = false;
				break;
			case 'a':
				txtA.setBackground(Color.blue);
				aIsPressed = false;
				break;
			case 's':
				txtS.setBackground(Color.blue);
				sIsPressed = false;
				break;
			case 'd':
				txtD.setBackground(Color.blue);
				dIsPressed = false;
				break;
			}
			if (!stopped && key != 't' && key != 'q') 
			{
				try 
				{
					station.stop();
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
			}
			stopped = !(wIsPressed || aIsPressed || sIsPressed || dIsPressed
					|| tIsPressed || qIsPressed);
		}
	}

	private void printError(String error) 
	{
		errorPane.setText(error);
	}
}
