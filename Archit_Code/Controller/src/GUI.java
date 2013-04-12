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
import java.io.IOException;
import java.util.*;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JPanel;

import lejos.pc.comm.NXTCommException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

// click W key, robot moves. On release, it stops.

public class GUI 
{

	//<<<<<<< HEAD

	private JFrame frame;
	private JTextField txtA;
	private JTextField txtD;
	private JTextField txtS;
	private JTextField txtW;
	private BaseStation station; 
	private BufferedImage logo;
	private JTextField textHeaderMovementControls;
	private JTextField textFieldMicrophone;
	private JTextField textFieldLight;
	private JTextField textFieldTouch;
	private JTextField textFieldUltrasonic;
	private JTextField txtUltrasonic_1;
	private JTextField txtTouch_1;
	private JTextField txtLight_1;
	private JTextField txtMicrophone_1;
	private JTextField textHeaderType;
	private JTextField textHeaderCurrent;
	private JTextField textConnectionOn, textConnectionOff;
	private JTextField txtT;
	private JTextField inputFieldSpeed;
	private JTextField txtSpeed;
	private boolean wIsPressed, aIsPressed, sIsPressed, dIsPressed, tIsPressed, 
	stopped, valid, valueHolder, isSent;
	private int speed, previousSpeed;
	private String temp;
	private final int MAX_SPEED = 720;
	private final int MIN_SPEED = 0;
	private final int SPEED_CHANGE_INCREMENT = 10;
	public JTextField textField_8;
	public JTextField textField_9;
	public JTextField txtNo;
	public JTextField textField_11;
	private JTextField textConnectionButtonOn;
	private JTextField textConnectionButtonOff;
	private static GUI window;

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
		station = new BaseStation();
		frame = new JFrame("Control Station");
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setFocusable(true);
		makeNewControlListener(frame);

		JPanel panel = new JPanel();
		panel.setBounds(10, 358, 464, 92);
		frame.getContentPane().add(panel);
		panel.setLayout(null);

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


		textHeaderMovementControls = new JTextField();
		textHeaderMovementControls.setBackground(Color.ORANGE);
		textHeaderMovementControls.setForeground(Color.BLUE);
		textHeaderMovementControls.setBounds(0, 0, 464, 30);
		panel.add(textHeaderMovementControls);
		textHeaderMovementControls
		.setHorizontalAlignment(SwingConstants.CENTER);
		textHeaderMovementControls.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textHeaderMovementControls.setEditable(false);
		textHeaderMovementControls.setText("Click for Movement Controls");
		// textHeaderMovementControls.setSize(200, 40);
		textHeaderMovementControls.setColumns(10);
		makeNewControlListener(textHeaderMovementControls);
		
		txtT = new JTextField();
		txtT.setBounds(328, 41, 40, 40);
		panel.add(txtT);
		txtT.setForeground(Color.ORANGE);
		txtT.setBackground(Color.BLUE);
		txtT.setHorizontalAlignment(SwingConstants.CENTER);
		txtT.setEditable(false);
		txtT.setText("T");
		txtT.setColumns(10);

		textField_8 = new JTextField();
		textField_8.setText("45");
		textField_8.setEditable(false);
		textField_8.setBounds(357, 87, 43, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);

		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setText("50");
		textField_9.setBounds(357, 118, 43, 20);
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);

		txtNo = new JTextField();
		txtNo.setText("FALSE");
		txtNo.setEditable(false);
		txtNo.setBounds(357, 149, 43, 20);
		frame.getContentPane().add(txtNo);
		txtNo.setColumns(10);

		textField_11 = new JTextField();
		textField_11.setText("35");
		textField_11.setEditable(false);
		textField_11.setBounds(357, 180, 43, 20);
		frame.getContentPane().add(textField_11);
		textField_11.setColumns(10);

		txtUltrasonic_1 = new JTextField();
		txtUltrasonic_1.setForeground(Color.BLUE);
		txtUltrasonic_1.setBackground(Color.ORANGE);
		txtUltrasonic_1.setEditable(false);
		txtUltrasonic_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtUltrasonic_1.setText("Ultrasonic");
		txtUltrasonic_1.setBounds(261, 180, 86, 20);
		frame.getContentPane().add(txtUltrasonic_1);
		txtUltrasonic_1.setColumns(10);

		txtTouch_1 = new JTextField();
		txtTouch_1.setForeground(Color.BLUE);
		txtTouch_1.setBackground(Color.ORANGE);
		txtTouch_1.setEditable(false);
		txtTouch_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtTouch_1.setText("Touch");
		txtTouch_1.setBounds(261, 149, 86, 20);
		frame.getContentPane().add(txtTouch_1);
		txtTouch_1.setColumns(10);

		txtLight_1 = new JTextField();
		txtLight_1.setForeground(Color.BLUE);
		txtLight_1.setBackground(Color.ORANGE);
		txtLight_1.setEditable(false);
		txtLight_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtLight_1.setText("Light");
		txtLight_1.setBounds(261, 118, 86, 20);
		frame.getContentPane().add(txtLight_1);
		txtLight_1.setColumns(10);

		txtMicrophone_1 = new JTextField();
		txtMicrophone_1.setForeground(Color.BLUE);
		txtMicrophone_1.setBackground(Color.ORANGE);
		txtMicrophone_1.setEditable(false);
		txtMicrophone_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtMicrophone_1.setText("Microphone");
		txtMicrophone_1.setBounds(261, 87, 86, 20);
		frame.getContentPane().add(txtMicrophone_1);
		txtMicrophone_1.setColumns(10);
		
		inputFieldSpeed = new JTextField();
		inputFieldSpeed.setText("100");
		inputFieldSpeed.setBounds(167, 180, 86, 20);
		frame.getContentPane().add(inputFieldSpeed);
		inputFieldSpeed.setColumns(10);

		txtSpeed = new JTextField();
		txtSpeed.setHorizontalAlignment(SwingConstants.CENTER);
		txtSpeed.setText("Speed");
		txtSpeed.setForeground(Color.BLUE);
		txtSpeed.setEditable(true);
		txtSpeed.setColumns(10);
		txtSpeed.setBackground(Color.ORANGE);
		txtSpeed.setBounds(71, 180, 86, 20);
		frame.getContentPane().add(txtSpeed);


		JButton btnNewButton = new JButton("Initiate Connection");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					station.establishConnection();
					textConnectionButtonOn.setBackground(Color.green);
					textConnectionButtonOff.setBackground(Color.lightGray);
				} catch (NXTCommException | IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnNewButton.setBounds(274, 258, 182, 23);
		frame.getContentPane().add(btnNewButton);

		textConnectionButtonOn = new JTextField();
		textConnectionButtonOn.setEditable(false);
		textConnectionButtonOn.setHorizontalAlignment(SwingConstants.CENTER);
		textConnectionButtonOn.setText("On");
		textConnectionButtonOn.setBounds(274, 292, 86, 20);
		frame.getContentPane().add(textConnectionButtonOn);
		textConnectionButtonOn.setColumns(10);

		textConnectionButtonOff = new JTextField();
		textConnectionButtonOff.setHorizontalAlignment(SwingConstants.CENTER);
		textConnectionButtonOff.setText("Off");
		textConnectionButtonOff.setEditable(false);
		textConnectionButtonOff.setBounds(370, 292, 86, 20);
		frame.getContentPane().add(textConnectionButtonOff);
		textConnectionButtonOff.setColumns(10);
		textConnectionButtonOff.setBackground(Color.red);

		JButton btnNewButton_1 = new JButton("Refresh");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				try {
					station.getMicrophoneSensor();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		btnNewButton_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		btnNewButton_1.setBounds(405, 86, 69, 23);
		frame.getContentPane().add(btnNewButton_1);

		JButton button = new JButton("Refresh");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					station.getLightSensor();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button.setBounds(405, 117, 69, 23);
		frame.getContentPane().add(button);

		JButton button_1 = new JButton("Refresh");
		button_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					station.getTouchSensor();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button_1.setBounds(405, 148, 69, 23);
		frame.getContentPane().add(button_1);

		JButton button_2 = new JButton("Refresh");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					station.getUltraSensor();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.PLAIN, 9));
		button_2.setBounds(405, 179, 69, 23);
		frame.getContentPane().add(button_2);
	}

	private void makeNewControlListener(Component c)
	{
		c.addKeyListener(new KeyAdapter() 
		{
			@Override
			public void keyPressed(KeyEvent e) 
			{
				try {
					controlPress(e.getKeyChar());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			@Override
			public void keyReleased(KeyEvent e)
			{
				try {
					controlRelease(e.getKeyChar());
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
	}

	private void controlPress(char key) throws IOException
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
			//station.setSpeed(speed);
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
				station.turnRight(180);
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
					station.turnRight(0);
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
					station.turnLeft(0);
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


	private void controlRelease(char key) throws IOException
	{
		if(valid)
		{
			valueHolder = station.getTouchValue();
			if(valueHolder)
			{
				txtNo.setText("true");
			}
			else
			{
				txtNo.setText("false");
			}
			//			isSent = false;

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


	//=======

	public void keyReleased(KeyEvent e)
	{
		boolean valueHolder = station.getTouchValue();
		if(valueHolder)
		{
			txtNo.setText("true");
		}
		else
		{
			txtNo.setText("false");
		}
		//		isSent = false;
		if (isSent) 
		{
			if (e.getKeyChar() == 'w') 
			{
				txtW.setBackground(Color.blue);
				txtW.setCaretColor(Color.orange);
				try 
				{
					station.stop();
				} catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			else if (e.getKeyChar() == 'a') 
			{
				txtA.setBackground(Color.blue);
				txtA.setCaretColor(Color.orange);
				try 
				{
					station.stop();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			else if (e.getKeyChar() == 's') 
			{
				txtS.setBackground(Color.blue);
				txtS.setCaretColor(Color.orange);
				try 
				{
					station.stop();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			} 
			else if (e.getKeyChar() == 'd') 
			{
				txtD.setBackground(Color.blue);
				txtD.setCaretColor(Color.orange);
				try 
				{
					station.stop();
				} 
				catch (IOException e1) 
				{
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
	}
}
