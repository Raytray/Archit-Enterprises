import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import lejos.pc.comm.NXTComm;
import lejos.pc.comm.NXTCommException;
import lejos.pc.comm.NXTCommFactory;
import lejos.pc.comm.NXTInfo;

public class BaseStation {
	public NXTComm connection;
	public NXTInfo info;
	public OutputStream os;
	public boolean readFlag;
	public InputStream is;
	public DataOutputStream oHandle;
	public DataInputStream iHandle;
	public String command;
	private Thread PCreceiver;
	private int touchValue, ultraSonicValue, lightValue, micValue;
	private String currentErrors;
	private final String[] errorMessages = { "Error with sensor in port 1.",
			"Error with sensor in port 2.", "Error with sensor in port 3.",
			"Error with sensor in port 4.", "Error with motor in port A.",
			"Error with motor in port B.", "Error with motor in port C.", };
	private final int UPDATE_TIME = 100;
	private final int BYTE_SIZE = 256;
	private final int MESSAGE_LENGTH = 10;
	private final int TENS = 10;
	private final int HUNDREDS = 100;
	private final int THOUSANDS = 1000;
	private final int OFFSET = 3;

	public BaseStation() throws NXTCommException, IOException {
		touchValue = 0;
		ultraSonicValue = 0;
		lightValue = 0;
		micValue = 0;
		currentErrors = "";
	}

	public void establishConnection() throws NXTCommException, IOException {
		connection = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
		info = new NXTInfo(NXTCommFactory.BLUETOOTH, "TROGDOR",
				"00:16:53:13:E6:74");
		// info = new NXTInfo(NXTCommFactory.BLUETOOTH, "NXT",
		// "00:16:53:13:D3:FC");

		connection.open(info);
		os = connection.getOutputStream();
		is = connection.getInputStream();
		iHandle = new DataInputStream(is);
		oHandle = new DataOutputStream(os);

		PCreceiver = new Thread() {
			public void run() {
				byte[] buffer;
				String message;
				int count;
				readFlag = true;
				while (readFlag) {
					try {
						buffer = new byte[BYTE_SIZE];
						count = iHandle.read(buffer);
						if (count > 0) {
							message = (new String(buffer)).trim();
							if (true) {
								switch (message.substring(0, 3)) {
								case "SDT":
									touchValue = Integer.parseInt(message
											.substring(9, MESSAGE_LENGTH));
									createACK();
									break;
								case "SDU":
									ultraSonicValue = Integer.parseInt(message
											.substring(3, MESSAGE_LENGTH));
									createACK();
									break;
								case "SDM":
									micValue = Integer.parseInt(message
											.substring(3, MESSAGE_LENGTH));
									createACK();
									break;
								case "SDL":
									lightValue = Integer.parseInt(message
											.substring(3, MESSAGE_LENGTH));
									createACK();
									break;
								case "ERS":
									currentErrors = currentErrors
											+ "\n"
											+ errorMessages[Integer
													.parseInt(message
															.substring(9,
																	MESSAGE_LENGTH))];
									createACK();
									break;
								case "ERM":
									currentErrors = currentErrors
											+ "\n"
											+ errorMessages[OFFSET
													+ Integer
															.parseInt(message
																	.substring(
																			9,
																			MESSAGE_LENGTH))];
									createACK();
									break;
								}
							}
						}
					} catch (IOException e) {
						System.out.println("Fail to read from iHandle bc "
								+ e.toString());
						return;
					} catch (InterruptedException e) {
						System.out.println("Failed to connect");
						return;
					}
				}
			}
		};
		PCreceiver.start();
	}

	public void sendMessage(String message) throws IOException,
			InterruptedException {
		oHandle.write(message.getBytes());
		oHandle.flush();
	}

	public void moveForward() throws IOException, InterruptedException {
		command = "MSF0000000";
		buildCommand();
		sendMessage(command);
	}

	public void moveBackward() throws IOException, InterruptedException {
		command = "MSB0000000";
		buildCommand();
		sendMessage(command);
	}

	public void turnLeft(int degrees) throws IOException, InterruptedException {
		if (degrees < 0) {
			turnRight(degrees * (-1));
		} else if (degrees == 0) {
			command = "TNL0000000";
			buildCommand();
			sendMessage(command);
		} else {
			command = "TNL0000000";
			buildCommand(180);
			sendMessage(command);
		}
	}

	public void turnRight(int degrees) throws IOException, InterruptedException {
		if (degrees < 0) {
			turnLeft(degrees * (-1));
		} else if (degrees == 0) {
			command = "TNR0000000";
			buildCommand();
			sendMessage(command);
		} else {
			command = "TNR0000000";
			buildCommand(180);
			sendMessage(command);
		}
	}

	public void swing() throws IOException, InterruptedException {
		command = "SW00000000";
		buildCommand();
		sendMessage(command);
	}

	public void moveForwardLeft() throws IOException, InterruptedException {
		command = "MAFL000000";
		buildCommand();
		sendMessage(command);
	}

	public void moveForwardRight() throws IOException, InterruptedException {
		command = "MAFR000000";
		buildCommand();
		sendMessage(command);
	}

	public void moveBackwardLeft() throws IOException, InterruptedException {
		command = "MABL000000";
		buildCommand();
		sendMessage(command);
	}

	public void moveBackwardRight() throws IOException, InterruptedException {
		command = "MABR000000";
		buildCommand();
		sendMessage(command);
	}

	public void stop() throws IOException, InterruptedException {
		command = "ST00000000";
		buildCommand();
		sendMessage(command);
	}

	public void getTouchSensor() throws IOException, InterruptedException {
		command = "RST0000000";
		buildCommand();
		sendMessage(command);
	}

	public boolean getTouchValue() {
		if (touchValue == 1) {
			return true;
		} else {
			return false;
		}
	}

	private String getChecksum(String message) {
		int sum = 0;
		byte[] checksum = new byte[1];
		String checksumString;
		byte[] buffer = message.getBytes();
		for (int i = 0; i < buffer.length; i++) {
			sum += (int) buffer[i];
		}
		sum = sum % BYTE_SIZE;
		checksum[0] = (byte) sum;
		checksumString = new String(checksum);
		return checksumString;
	}

	public boolean verifyChecksum(String message) {
		if (message.length() == 11) {
			if (getChecksum(message.substring(0, MESSAGE_LENGTH)).equals(
					message.substring(MESSAGE_LENGTH))) {
				return true;
			}
		}
		return false;
	}

	public void getMicrophoneSensor() throws IOException, InterruptedException {
		command = "RSM0000000";
		buildCommand();
		sendMessage(command);
	}

	public void getLightSensor() throws IOException, InterruptedException {
		command = "RSL0000000";
		buildCommand();
		sendMessage(command);
	}

	public void getUltraSensor() throws IOException, InterruptedException {
		command = "RSU0000000";
		buildCommand();
		sendMessage(command);
	}

	public void readSensors() throws IOException, InterruptedException {
		getTouchSensor();
		Thread.sleep(UPDATE_TIME);
		getMicrophoneSensor();
		Thread.sleep(UPDATE_TIME);
		getLightSensor();
		Thread.sleep(UPDATE_TIME);
		getUltraSensor();
		Thread.sleep(UPDATE_TIME);
	}

	public int getMicrophoneValue() {
		return micValue;
	}

	public int getLightValue() {
		return lightValue;
	}

	public int getUltrasonicValue() {
		return ultraSonicValue;
	}

	public String getErrors() {
		return currentErrors;
	}

	public void exitRobot() throws IOException, InterruptedException {
		command = "ECE0000000";
		buildCommand();
		sendMessage(command);
	}

	public void setSpeed(int newSpeed) throws IOException, InterruptedException {
		if (newSpeed < TENS) {
			command = "SSDT00000" + Integer.toString(newSpeed);
		} else if (newSpeed < HUNDREDS) {
			command = "SSDT0000" + Integer.toString(newSpeed);
		} else {
			command = "SSDT000" + Integer.toString(newSpeed);
		}
		command = command + getChecksum(command);
		sendMessage(command);
	}

	private void buildCommand() {
		buildCommand(0);
	}

	private void buildCommand(int value) { // TODO check for proper function
		if (value < TENS) {
			command = command.substring(0, MESSAGE_LENGTH - 1);
		} else if (value < HUNDREDS) {
			command = command.substring(0, MESSAGE_LENGTH - 2);
		} else if (value < THOUSANDS) {
			command = command.substring(0, MESSAGE_LENGTH - 3);
		}
		command = command + Integer.toString(value);
		command = command + getChecksum(command);
	}

	public void createACK() throws IOException, InterruptedException {
		command = "AK00000000";
		buildCommand();
		sendMessage(command);
	}

	public void clearErrors() {
		currentErrors = "";
	}
}
