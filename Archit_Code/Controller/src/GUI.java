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
		
		txtA = new JTextField();
		txtA.setBackground(UIManager.getColor("Button.background"));
		txtA.setHorizontalAlignment(SwingConstants.CENTER);
		txtA.setEditable(false);
		txtA.setText("A");
		txtA.setBounds(10, 430, 86, 20);
		frame.getContentPane().add(txtA);
		txtA.setColumns(10);
		
		txtD = new JTextField();
		txtD.setHorizontalAlignment(SwingConstants.CENTER);
		txtD.setEditable(false);
		txtD.setText("D");
		txtD.setBounds(209, 430, 86, 20);
		frame.getContentPane().add(txtD);
		txtD.setColumns(10);
		
		txtS = new JTextField();
		txtS.setHorizontalAlignment(SwingConstants.CENTER);
		txtS.setEditable(false);
		txtS.setText("S");
		txtS.setBounds(106, 430, 86, 20);
		frame.getContentPane().add(txtS);
		txtS.setColumns(10);
		
		txtW = new JTextField();
		txtW.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent e) {
				if(e.getKeyChar() == 'w')
				{
					txtW.setBackground(Color.red);
					station.moveForward();
				}
				else if(e.getKeyChar() == 'd')
				{
					txtD.setBackground(Color.red);
					station.turnRight();
				}
				else if(e.getKeyChar() == 'a')
				{
					txtA.setBackground(Color.red);
					station.turnLeft();
				}
				else if(e.getKeyChar() == 's')
				{
					txtS.setBackground(Color.red);
					station.moveBackward();
				}
			}
			@Override
			public void keyReleased(KeyEvent e) {
				if(e.getKeyChar() == 'w')
				{
					txtW.setBackground(frame.getBackground());
					station.stop();
				}
				else if(e.getKeyChar() == 'a')
				{
					txtA.setBackground(frame.getBackground());
					station.stop();
				}
				else if(e.getKeyChar() == 's')
				{
					txtS.setBackground(frame.getBackground());
					station.stop();
				}
				else if(e.getKeyChar() == 'd')
				{
					txtD.setBackground(frame.getBackground());
					station.stop();
				}
			}
		});
		txtW.setHorizontalAlignment(SwingConstants.CENTER);
		txtW.setEditable(false);
		txtW.setText("W");
		txtW.setBounds(106, 399, 86, 20);
		frame.getContentPane().add(txtW);
		txtW.setColumns(10);
	}
}
