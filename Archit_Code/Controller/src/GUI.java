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
	private JTextField textHeaderMovementControls;
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
	private JTextField textHeaderType;
	private JTextField textHeaderCurrent;
	private JTextField textHeaderNew;
	private JTextField textConnectionButtonOn;
	private JTextField textConnectionButtonOff;

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
	 * 
	 * @throws NXTCommException
	 * @throws IOException
	 */
	public GUI() throws NXTCommException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * 
	 * @throws NXTCommException
	 * @throws IOException
	 */
	private void initialize() throws NXTCommException, IOException {
		station = new BaseStation();
		frame = new JFrame("Control Station");
		frame.setBounds(100, 100, 450, 300);
		frame.setSize(500, 500);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setFocusable(true);

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

		txtW.setHorizontalAlignment(SwingConstants.CENTER);
		txtW.setEditable(false);
		txtW.setText("W");
		txtW.setColumns(10);

		textHeaderMovementControls = new JTextField();
		textHeaderMovementControls.setBackground(Color.ORANGE);
		textHeaderMovementControls.setForeground(Color.BLUE);
		textHeaderMovementControls.setBounds(0, 0, 285, 17);
		panel.add(textHeaderMovementControls);
		textHeaderMovementControls
				.setHorizontalAlignment(SwingConstants.CENTER);
		textHeaderMovementControls.setFont(new Font("Tahoma", Font.PLAIN, 14));
		textHeaderMovementControls.setEditable(false);
		textHeaderMovementControls.setText("Click for Movement Controls");
		// textHeaderMovementControls.setSize(200, 40);
		textHeaderMovementControls.setColumns(10);
		textHeaderMovementControls.addKeyListener(new KeyAdapter() {
			boolean isSent = false;

			public void keyTyped(KeyEvent e) {
				if (!isSent) {
					if (e.getKeyChar() == 'w') {
						txtW.setBackground(Color.orange);
						txtW.setCaretColor(Color.blue);
						try {
							station.moveForward();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (e.getKeyChar() == 'd') {
						txtD.setBackground(Color.orange);
						txtD.setCaretColor(Color.blue);
						try {
							station.turnRight(0);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (e.getKeyChar() == 'a') {
						txtA.setBackground(Color.orange);
						txtA.setCaretColor(Color.blue);
						try {
							station.turnLeft(0);
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (e.getKeyChar() == 's') {
						txtS.setBackground(Color.orange);
						txtS.setCaretColor(Color.blue);
						try {
							station.moveBackward();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
				isSent = true;
			}

			@Override
			public void keyReleased(KeyEvent e) {
				isSent = false;
				if (!isSent) {
					if (e.getKeyChar() == 'w') {
						txtW.setBackground(Color.blue);
						txtW.setCaretColor(Color.orange);
						try {
							station.stop();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (e.getKeyChar() == 'a') {
						txtA.setBackground(Color.blue);
						txtA.setCaretColor(Color.orange);
						try {
							station.stop();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (e.getKeyChar() == 's') {
						txtS.setBackground(Color.blue);
						txtS.setCaretColor(Color.orange);
						try {
							station.stop();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					} else if (e.getKeyChar() == 'd') {
						txtD.setBackground(Color.blue);
						txtD.setCaretColor(Color.orange);
						try {
							station.stop();
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
					}
				}
			}
		});

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
		txtNo.setText("FALSE");
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

		textHeaderType = new JTextField();
		textHeaderType.setHorizontalAlignment(SwingConstants.CENTER);
		textHeaderType.setForeground(Color.BLUE);
		textHeaderType.setBackground(Color.ORANGE);
		textHeaderType.setEditable(false);
		textHeaderType.setText("Sensor Type");
		textHeaderType.setBounds(292, 56, 86, 20);
		frame.getContentPane().add(textHeaderType);
		textHeaderType.setColumns(10);

		textHeaderCurrent = new JTextField();
		textHeaderCurrent.setForeground(Color.ORANGE);
		textHeaderCurrent.setBackground(Color.BLUE);
		textHeaderCurrent.setEditable(false);
		textHeaderCurrent.setFont(new Font("Tahoma", Font.PLAIN, 10));
		textHeaderCurrent.setText("Current");
		textHeaderCurrent.setBounds(388, 56, 43, 20);
		frame.getContentPane().add(textHeaderCurrent);
		textHeaderCurrent.setColumns(10);

		textHeaderNew = new JTextField();
		textHeaderNew.setBackground(Color.BLUE);
		textHeaderNew.setForeground(Color.ORANGE);
		textHeaderNew.setEditable(false);
		textHeaderNew.setText("New");
		textHeaderNew.setBounds(431, 56, 43, 20);
		frame.getContentPane().add(textHeaderNew);
		textHeaderNew.setColumns(10);

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
		btnNewButton.setBounds(292, 252, 182, 23);
		frame.getContentPane().add(btnNewButton);

		textConnectionButtonOn = new JTextField();
		textConnectionButtonOn.setEditable(false);
		textConnectionButtonOn.setHorizontalAlignment(SwingConstants.CENTER);
		textConnectionButtonOn.setText("On");
		textConnectionButtonOn.setBounds(292, 292, 86, 20);
		frame.getContentPane().add(textConnectionButtonOn);
		textConnectionButtonOn.setColumns(10);

		textConnectionButtonOff = new JTextField();
		textConnectionButtonOff.setHorizontalAlignment(SwingConstants.CENTER);
		textConnectionButtonOff.setText("Off");
		textConnectionButtonOff.setEditable(false);
		textConnectionButtonOff.setBounds(388, 292, 86, 20);
		frame.getContentPane().add(textConnectionButtonOff);
		textConnectionButtonOff.setColumns(10);
		textConnectionButtonOff.setBackground(Color.red);
	}
}
