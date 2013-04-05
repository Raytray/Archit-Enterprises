import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;
import java.awt.Font;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

// click W key, robot moves. On release, it stops.

public class GUI 
{

	private JFrame frame;
	private JTextField txtA;
	private JTextField txtD;
	private JTextField txtS;
	private JTextField txtW;
	private BaseStation station; 
	private JTextField txtMovementControls;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JTextField textField_3;
	private JTextField txtMicrophone;
	private JTextField txtTouch;
	private JTextField txtUltrasonic;
	private JTextField txtLight;
	private JTextField textField_4;
	private JTextField textField_5;
	private JTextField textField_6;
	private JTextField textField_7;
	private BufferedImage logo;
	private JLabel lblNewLabel_1;
	private JTextField textHeaderMovementControls;
	private JTextField textFieldMicrophone;
	private JTextField textFieldLight;
	private JTextField textFieldTouch;
	private JTextField textFieldUltrasonic;
	private JTextField txtUltrasonic_1;
	private JTextField txtTouch_1;
	private JTextField txtLight_1;
	private JTextField txtMicrophone_1;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField textHeaderType;
	private JTextField textHeaderCurrent;
	private JTextField textConnectionOn, textConnectionOff;
	private JButton btn;
	private JTextField txtT;
	private JTextField inputFieldSpeed;
	private JTextField txtSpeed;
	private String temp;
	private boolean wIsPressed, aIsPressed, sIsPressed, dIsPressed, tIsPressed, 
	upIsPressed, downIsPressed, stopped, valid;
	private int speed, previousSpeed;
	
	private final int MAX_SPEED = 720;
	private final int MIN_SPEED = 0;
	private final int SPEED_CHANGE_INCREMENT = 10;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
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
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		station = new BaseStation();
		temp = "";
		speed = 100;
		valid = true;
		frame = new JFrame("Control Station");
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setFocusable(true);
		makeNewControlListener(frame);

		JPanel panel = new JPanel();
		panel.setBounds(10, 376, 464, 74);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		makeNewControlListener(panel);


		txtA = new JTextField();
		txtA.setForeground(Color.ORANGE);
		txtA.setBounds(0, 54, 86, 20);
		panel.add(txtA);
		txtA.setBackground(Color.BLUE);
		txtA.setHorizontalAlignment(SwingConstants.CENTER);
		txtA.setEditable(false);
		txtA.setText("A");
		txtA.setColumns(10);

		txtD = new JTextField();
		txtD.setForeground(Color.ORANGE);
		txtD.setBackground(Color.BLUE);
		txtD.setBounds(199, 54, 86, 20);
		panel.add(txtD);
		txtD.setHorizontalAlignment(SwingConstants.CENTER);
		txtD.setEditable(false);
		txtD.setText("D");
		txtD.setColumns(10);

		txtS = new JTextField();
		txtS.setForeground(Color.ORANGE);
		txtS.setBackground(Color.BLUE);
		txtS.setBounds(96, 54, 86, 20);
		panel.add(txtS);
		txtS.setHorizontalAlignment(SwingConstants.CENTER);
		txtS.setEditable(false);
		txtS.setText("S");
		txtS.setColumns(10);

		txtW = new JTextField();
		txtW.setForeground(Color.ORANGE);
		txtW.setBackground(Color.BLUE);
		txtW.setBounds(96, 23, 86, 20);
		panel.add(txtW);

		txtW.setHorizontalAlignment(SwingConstants.CENTER);
		txtW.setEditable(false);
		txtW.setText("W");
		txtW.setColumns(10);

		txtT = new JTextField();
		txtT.setText("T");
		txtT.setHorizontalAlignment(SwingConstants.CENTER);
		txtT.setForeground(Color.ORANGE);
		txtT.setEditable(false);
		txtT.setColumns(10);
		txtT.setBackground(Color.BLUE);
		txtT.setBounds(321, 16, 40, 40);
		panel.add(txtT);



		textFieldMicrophone = new JTextField();
		textFieldMicrophone.setText("45");
		textFieldMicrophone.setEditable(false);
		textFieldMicrophone.setBounds(408, 87, 43, 20);
		frame.getContentPane().add(textFieldMicrophone);
		textFieldMicrophone.setColumns(10);

		textFieldLight = new JTextField();
		textFieldLight.setEditable(false);
		textFieldLight.setText("50");
		textFieldLight.setBounds(408, 118, 43, 20);
		frame.getContentPane().add(textFieldLight);
		textFieldLight.setColumns(10);

		textFieldTouch = new JTextField();
		textFieldTouch.setText("FALSE");
		textFieldTouch.setEditable(false);
		textFieldTouch.setBounds(408, 149, 43, 20);
		frame.getContentPane().add(textFieldTouch);
		textFieldTouch.setColumns(10);

		textFieldUltrasonic = new JTextField();
		textFieldUltrasonic.setText("35");
		textFieldUltrasonic.setEditable(false);
		textFieldUltrasonic.setBounds(408, 180, 43, 20);
		frame.getContentPane().add(textFieldUltrasonic);
		textFieldUltrasonic.setColumns(10);

		txtUltrasonic_1 = new JTextField();
		txtUltrasonic_1.setForeground(Color.BLUE);
		txtUltrasonic_1.setBackground(Color.ORANGE);
		txtUltrasonic_1.setEditable(false);
		txtUltrasonic_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtUltrasonic_1.setText("Ultrasonic");
		txtUltrasonic_1.setBounds(312, 180, 86, 20);
		frame.getContentPane().add(txtUltrasonic_1);
		txtUltrasonic_1.setColumns(10);

		txtTouch_1 = new JTextField();
		txtTouch_1.setForeground(Color.BLUE);
		txtTouch_1.setBackground(Color.ORANGE);
		txtTouch_1.setEditable(false);
		txtTouch_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtTouch_1.setText("Touch");
		txtTouch_1.setBounds(312, 149, 86, 20);
		frame.getContentPane().add(txtTouch_1);
		txtTouch_1.setColumns(10);

		txtLight_1 = new JTextField();
		txtLight_1.setForeground(Color.BLUE);
		txtLight_1.setBackground(Color.ORANGE);
		txtLight_1.setEditable(false);
		txtLight_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtLight_1.setText("Light");
		txtLight_1.setBounds(312, 118, 86, 20);
		frame.getContentPane().add(txtLight_1);
		txtLight_1.setColumns(10);

		txtMicrophone_1 = new JTextField();
		txtMicrophone_1.setForeground(Color.BLUE);
		txtMicrophone_1.setBackground(Color.ORANGE);
		txtMicrophone_1.setEditable(false);
		txtMicrophone_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtMicrophone_1.setText("Microphone");
		txtMicrophone_1.setBounds(312, 87, 86, 20);
		frame.getContentPane().add(txtMicrophone_1);
		txtMicrophone_1.setColumns(10);

		textHeaderType = new JTextField();
		textHeaderType.setHorizontalAlignment(SwingConstants.CENTER);
		textHeaderType.setForeground(Color.BLUE);
		textHeaderType.setBackground(Color.ORANGE);
		textHeaderType.setEditable(false);
		textHeaderType.setText("Sensor Type");
		textHeaderType.setBounds(312, 56, 86, 20);
		frame.getContentPane().add(textHeaderType);
		textHeaderType.setColumns(10);

		textHeaderCurrent = new JTextField();
		textHeaderCurrent.setForeground(Color.ORANGE);
		textHeaderCurrent.setBackground(Color.BLUE);
		textHeaderCurrent.setEditable(false);
		textHeaderCurrent.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textHeaderCurrent.setText("Current");
		textHeaderCurrent.setBounds(408, 56, 43, 20);
		frame.getContentPane().add(textHeaderCurrent);
		textHeaderCurrent.setColumns(10);

		JButton btnInitiateConnection = new JButton("Initiate Connection");
		btnInitiateConnection.setBounds(292, 252, 182, 23);
		btnInitiateConnection.setToolTipText("Click to attempt a connection with the NXT brick.");
		frame.getContentPane().add(btnInitiateConnection);

		textConnectionOn = new JTextField();
		textConnectionOn.setEditable(false);
		textConnectionOn.setHorizontalAlignment(SwingConstants.CENTER);
		textConnectionOn.setText("On");
		textConnectionOn.setBounds(292, 292, 86, 20);
		frame.getContentPane().add(textConnectionOn);
		textConnectionOn.setColumns(10);

		textConnectionOff = new JTextField();
		textConnectionOff.setHorizontalAlignment(SwingConstants.CENTER);
		textConnectionOff.setText("Off");
		textConnectionOff.setEditable(false);
		textConnectionOff.setBounds(388, 292, 86, 20);
		frame.getContentPane().add(textConnectionOff);
		textConnectionOff.setColumns(10);

		textHeaderMovementControls = new JTextField();
		textHeaderMovementControls.setBounds(10, 328, 464, 46);
		frame.getContentPane().add(textHeaderMovementControls);
		textHeaderMovementControls.setBackground(Color.ORANGE);
		textHeaderMovementControls.setForeground(Color.BLUE);
		textHeaderMovementControls.setHorizontalAlignment(SwingConstants.CENTER);
		textHeaderMovementControls.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textHeaderMovementControls.setEditable(false);
		textHeaderMovementControls.setText("Click Here for Keyboard Control");
		textHeaderMovementControls.setColumns(10);
		makeNewControlListener(textHeaderMovementControls);

		inputFieldSpeed = new JTextField();
		inputFieldSpeed.setText("100");
		inputFieldSpeed.setBounds(216, 180, 86, 20);
		frame.getContentPane().add(inputFieldSpeed);
		inputFieldSpeed.setColumns(10);

		txtSpeed = new JTextField();
		txtSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		txtSpeed.setText("Speed");
		txtSpeed.setForeground(Color.BLUE);
		txtSpeed.setEditable(true);
		txtSpeed.setColumns(10);
		txtSpeed.setBackground(Color.ORANGE);
		txtSpeed.setBounds(120, 180, 86, 20);
		frame.getContentPane().add(txtSpeed);



	}

	private void makeNewControlListener(Component c)
	{
		c.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				controlPress(e.getKeyChar());
			}
			@Override
			public void keyReleased(KeyEvent e)
			{
				controlRelease(e.getKeyChar());
			}
		});
	}

	private void controlPress(char key)
	{
		//System.out.println("\t" + (int)key);
		previousSpeed = speed;
		if(key != ',' && key != '.')
		{
			try
			{
				speed = Integer.parseInt(inputFieldSpeed.getText());
				if(speed < MIN_SPEED)
				{
					speed = previousSpeed;
					inputFieldSpeed.setText(String.valueOf(speed));
					throw new IllegalArgumentException();
				}
				if(speed > MAX_SPEED)
				{
					speed = MAX_SPEED;
					inputFieldSpeed.setText(String.valueOf(speed));
					throw new IllegalArgumentException();
				}
				valid = true;
			}
			catch(IllegalArgumentException e)
			{
				System.err.println("Speed must be between " + MIN_SPEED + " and "
						+ MAX_SPEED + " inclusive!");
				valid = false;
			}
			catch(Exception e)
			{
				System.err.println("Invalid speed!");
				valid = false;
			}
		}
		if(valid)
		{
			station.setSpeed(speed);
			if(key == ',' && speed > MIN_SPEED)
			{
				if(speed >= MIN_SPEED + SPEED_CHANGE_INCREMENT)
					speed -= SPEED_CHANGE_INCREMENT;
				else
					speed = MIN_SPEED;
			}
			if(key == '.' && speed < MAX_SPEED)
			{
				if(speed <= MAX_SPEED - SPEED_CHANGE_INCREMENT)
					speed += SPEED_CHANGE_INCREMENT;
				else
					speed = MAX_SPEED;

			}
			if(key == 't' && !tIsPressed)
			{
				txtT.setBackground(Color.orange);
				tIsPressed = true;
				station.turn180();
			}
			if(key == 'w' && !wIsPressed && !sIsPressed)
			{
				txtW.setBackground(Color.orange);
				wIsPressed = true;
				if(aIsPressed)
				{
					station.moveForwardLeft();
				}
				else if(dIsPressed)
				{
					station.moveForwardRight();
				}
				else
				{
					station.moveForward();
				}

			}
			if(key == 'd' && !dIsPressed && !aIsPressed)
			{
				txtD.setBackground(Color.orange);
				dIsPressed = true;
				if(wIsPressed)
				{
					station.moveForwardRight();
				}
				else if(sIsPressed)
				{
					station.moveBackwardRight();
				}
				else
				{
					station.turnRight();
				}
			}
			if(key == 'a' && !aIsPressed && !dIsPressed)
			{
				txtA.setBackground(Color.orange);
				aIsPressed = true;
				if(wIsPressed)
				{
					station.moveForwardLeft();
				}
				else if(sIsPressed)
				{
					station.moveBackwardLeft();
				}
				else
				{
					station.turnLeft();
				}
			}
			if(key == 's' && !sIsPressed && !wIsPressed)
			{
				txtS.setBackground(Color.orange);
				sIsPressed = true;
				if(aIsPressed)
				{
					station.moveBackwardLeft();
				}
				else if(dIsPressed)
				{
					station.moveBackwardRight();
				}
				else
				{
					station.moveBackward();
				}
			}
			stopped = !(wIsPressed || aIsPressed || sIsPressed || dIsPressed || tIsPressed);
			inputFieldSpeed.setText(String.valueOf(speed));
		}
	}


	private void controlRelease(char key)
	{
		if(valid)
		{
			if(key == 't')
			{
				txtT.setBackground(Color.blue);
				tIsPressed = false;
			}
			else if(key == 'w')
			{
				txtW.setBackground(Color.blue);
				wIsPressed = false;
				if(!stopped)
					station.stop();
			}
			else if(key == 'a')
			{
				txtA.setBackground(Color.blue);
				aIsPressed = false;
				if(!stopped)
					station.stop();
			}
			else if(key == 's')
			{
				txtS.setBackground(Color.blue);
				sIsPressed = false;
				if(!stopped)
					station.stop();
			}
			else if(key == 'd')
			{
				txtD.setBackground(Color.blue);
				dIsPressed = false;
				if(!stopped)
					station.stop();
			}
			stopped = wIsPressed || aIsPressed || sIsPressed || dIsPressed || tIsPressed;
			//		System.out.println(temp);
		}
	}


}
