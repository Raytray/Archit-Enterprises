import java.awt.Color;
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


public class GUI {

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
	private JTextField txtMovementControls_1;
	private JTextField textField_8;
	private JTextField textField_9;
	private JTextField txtNo;
	private JTextField textField_11;
	private JTextField txtUltrasonic_1;
	private JTextField txtTouch_1;
	private JTextField txtLight_1;
	private JTextField txtMicrophone_1;
	private JTextField textField_12;
	private JTextField textField_13;
	private JTextField textField_14;
	private JTextField textField_15;
	private JTextField txtType;
	private JTextField txtCurrent;
	private JTextField txtNew;
	private JTextField txtOn;
	private JTextField txtOff;

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
	private void initialize() {
		frame = new JFrame("Control Station");
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 376, 285, 74);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
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
		txtW.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == 'w')
				{
					txtW.setBackground(Color.orange);
					txtW.setCaretColor(Color.blue);
					//station.moveForward();
				}
				else if(e.getKeyChar() == 'd')
				{
					txtD.setBackground(Color.orange);
					txtD.setCaretColor(Color.blue);
					//station.turnRight();
				}
				else if(e.getKeyChar() == 'a')
				{
					txtA.setBackground(Color.orange);
					txtA.setCaretColor(Color.blue);
					//station.turnLeft();
				}
				else if(e.getKeyChar() == 's')
				{
					txtS.setBackground(Color.orange);
					txtS.setCaretColor(Color.blue);
					//station.moveBackward();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 'w')
				{
					txtW.setBackground(Color.blue);
					txtW.setCaretColor(Color.orange);
					//station.stop();
				}
				else if(e.getKeyChar() == 'a')
				{
					txtA.setBackground(Color.blue);
					txtA.setCaretColor(Color.orange);
					//station.stop();
				}
				else if(e.getKeyChar() == 's')
				{
					txtS.setBackground(Color.blue);
					txtS.setCaretColor(Color.orange);
					//station.stop();
				}
				else if(e.getKeyChar() == 'd')
				{
					txtD.setBackground(Color.blue);
					txtD.setCaretColor(Color.orange);
					//station.stop();
				}
			}
		});
		txtW.setHorizontalAlignment(SwingConstants.CENTER);
		txtW.setEditable(false);
		txtW.setText("W");
		txtW.setColumns(10);
		
		txtMovementControls_1 = new JTextField();
		txtMovementControls_1.setBackground(Color.ORANGE);
		txtMovementControls_1.setForeground(Color.BLUE);
		txtMovementControls_1.setBounds(62, 0, 155, 20);
		panel.add(txtMovementControls_1);
		txtMovementControls_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtMovementControls_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		txtMovementControls_1.setEditable(false);
		txtMovementControls_1.setText("Movement Controls");
		txtMovementControls_1.setColumns(10);
		
		textField_8 = new JTextField();
		textField_8.setText("45");
		textField_8.setEditable(false);
		textField_8.setBounds(388, 87, 43, 20);
		frame.getContentPane().add(textField_8);
		textField_8.setColumns(10);
		
		textField_9 = new JTextField();
		textField_9.setEditable(false);
		textField_9.setText("50");
		textField_9.setBounds(388, 118, 43, 20);
		frame.getContentPane().add(textField_9);
		textField_9.setColumns(10);
		
		txtNo = new JTextField();
		txtNo.setText("NO");
		txtNo.setEditable(false);
		txtNo.setBounds(388, 149, 43, 20);
		frame.getContentPane().add(txtNo);
		txtNo.setColumns(10);
		
		textField_11 = new JTextField();
		textField_11.setText("35");
		textField_11.setEditable(false);
		textField_11.setBounds(388, 180, 43, 20);
		frame.getContentPane().add(textField_11);
		textField_11.setColumns(10);
		
		txtUltrasonic_1 = new JTextField();
		txtUltrasonic_1.setForeground(Color.BLUE);
		txtUltrasonic_1.setBackground(Color.ORANGE);
		txtUltrasonic_1.setEditable(false);
		txtUltrasonic_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtUltrasonic_1.setText("Ultrasonic");
		txtUltrasonic_1.setBounds(292, 180, 86, 20);
		frame.getContentPane().add(txtUltrasonic_1);
		txtUltrasonic_1.setColumns(10);
		
		txtTouch_1 = new JTextField();
		txtTouch_1.setForeground(Color.BLUE);
		txtTouch_1.setBackground(Color.ORANGE);
		txtTouch_1.setEditable(false);
		txtTouch_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtTouch_1.setText("Touch");
		txtTouch_1.setBounds(292, 149, 86, 20);
		frame.getContentPane().add(txtTouch_1);
		txtTouch_1.setColumns(10);
		
		txtLight_1 = new JTextField();
		txtLight_1.setForeground(Color.BLUE);
		txtLight_1.setBackground(Color.ORANGE);
		txtLight_1.setEditable(false);
		txtLight_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtLight_1.setText("Light");
		txtLight_1.setBounds(292, 118, 86, 20);
		frame.getContentPane().add(txtLight_1);
		txtLight_1.setColumns(10);
		
		txtMicrophone_1 = new JTextField();
		txtMicrophone_1.setForeground(Color.BLUE);
		txtMicrophone_1.setBackground(Color.ORANGE);
		txtMicrophone_1.setEditable(false);
		txtMicrophone_1.setHorizontalAlignment(SwingConstants.CENTER);
		txtMicrophone_1.setText("Microphone");
		txtMicrophone_1.setBounds(292, 87, 86, 20);
		frame.getContentPane().add(txtMicrophone_1);
		txtMicrophone_1.setColumns(10);
		
		textField_12 = new JTextField();
		textField_12.setBounds(431, 87, 43, 20);
		frame.getContentPane().add(textField_12);
		textField_12.setColumns(10);
		
		textField_13 = new JTextField();
		textField_13.setBounds(431, 118, 43, 20);
		frame.getContentPane().add(textField_13);
		textField_13.setColumns(10);
		
		textField_14 = new JTextField();
		textField_14.setBounds(431, 149, 43, 20);
		frame.getContentPane().add(textField_14);
		textField_14.setColumns(10);
		
		textField_15 = new JTextField();
		textField_15.setBounds(431, 180, 43, 20);
		frame.getContentPane().add(textField_15);
		textField_15.setColumns(10);
		
		txtType = new JTextField();
		txtType.setHorizontalAlignment(SwingConstants.CENTER);
		txtType.setForeground(Color.BLUE);
		txtType.setBackground(Color.ORANGE);
		txtType.setEditable(false);
		txtType.setText("Sensor Type");
		txtType.setBounds(292, 56, 86, 20);
		frame.getContentPane().add(txtType);
		txtType.setColumns(10);
		
		txtCurrent = new JTextField();
		txtCurrent.setForeground(Color.ORANGE);
		txtCurrent.setBackground(Color.BLUE);
		txtCurrent.setEditable(false);
		txtCurrent.setFont(new Font("Tahoma", Font.PLAIN, 10));
		txtCurrent.setText("Current");
		txtCurrent.setBounds(388, 56, 43, 20);
		frame.getContentPane().add(txtCurrent);
		txtCurrent.setColumns(10);
		
		txtNew = new JTextField();
		txtNew.setBackground(Color.BLUE);
		txtNew.setForeground(Color.ORANGE);
		txtNew.setEditable(false);
		txtNew.setText("New");
		txtNew.setBounds(431, 56, 43, 20);
		frame.getContentPane().add(txtNew);
		txtNew.setColumns(10);
		
		JButton btnNewButton = new JButton("Initiate Connection");
		btnNewButton.setBounds(292, 252, 182, 23);
		frame.getContentPane().add(btnNewButton);
		
		txtOn = new JTextField();
		txtOn.setEditable(false);
		txtOn.setHorizontalAlignment(SwingConstants.CENTER);
		txtOn.setText("On");
		txtOn.setBounds(292, 292, 86, 20);
		frame.getContentPane().add(txtOn);
		txtOn.setColumns(10);
		
		txtOff = new JTextField();
		txtOff.setHorizontalAlignment(SwingConstants.CENTER);
		txtOff.setText("Off");
		txtOff.setEditable(false);
		txtOff.setBounds(388, 292, 86, 20);
		frame.getContentPane().add(txtOff);
		txtOff.setColumns(10);
	}
}
