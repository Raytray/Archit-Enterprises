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
    public boolean readFlag = true;
    public InputStream is; 
    public DataOutputStream oHandle;
    public DataInputStream iHandle;
    public String command; 
    private int touchValue = 0; 
    private GUI theGUI;
	
    public BaseStation(GUI g) throws NXTCommException, IOException
    {
    	theGUI = g;
    }
	
    public void establishConnection() throws NXTCommException, IOException {
	connection = NXTCommFactory.createNXTComm(NXTCommFactory.BLUETOOTH);
	info = new NXTInfo(NXTCommFactory.BLUETOOTH, "TROGDOR", "00:16:53:13:E6:74"); // your robot's name must be NXT and the code is 123

	// open connections and data streams
	connection.open(info);
	os = connection.getOutputStream();
	is = connection.getInputStream();
	iHandle = new DataInputStream(is);
	oHandle = new DataOutputStream(os);
		
	Thread PCreceiver = new Thread() { 
		public void run() {
		    while (readFlag) {
			try {
			    byte[] buffer = new byte[256];
			    int count = iHandle.read(buffer); // might want to check ack later
			    if (count > 0) {
				String ret = (new String(buffer)).trim();
				if(verifyChecksum(ret)){
				    System.out.printf("NXJ: %s [%dms]\n", ret);
				    if(ret.substring(0, 3).equals("SDT")){
				    	touchValue = Integer.parseInt(ret.substring(9,10));
				    	if(getTouchValue())
				    		theGUI.txtNo.setText("TRUE");
				    	else
				    		theGUI.txtNo.setText("FALSE");
				    }
				    else if(ret.substring(0,3).equals("SDU"))
				    {
				    	int uValue = Integer.parseInt(ret.substring(3,10));
				    	theGUI.textField_11.setText(Integer.toString(uValue));
				    }
				    else if(ret.substring(0,3).equals("SDM"))
				    {
				    	int mValue = Integer.parseInt(ret.substring(3,10));
				    	theGUI.textField_8.setText(Integer.toString(mValue));
				    }
				    else if(ret.substring(0,3).equals("SDL"))
				    {
				    	int lValue = Integer.parseInt(ret.substring(3,10));
				    	theGUI.textField_9.setText(Integer.toString(lValue));
				    }
				   }
			    }
			    Thread.sleep(10);
			} catch (IOException e) {
			    System.out.println("Fail to read from iHandle bc "
					       + e.toString());
			    return;
			} catch (InterruptedException e) {


			}
		    }
		}
	    };
		
		
	PCreceiver.start();
		
	/*oHandle.close();
	  iHandle.close();
	  os.close();
	  is.close();
	  connection.close(); 
	  readFlag = false;*/ 
    }
	
    public void sendMessage(String message) throws IOException
    {
	oHandle.write(message.getBytes());
	oHandle.flush();
    }
	
    public void moveForward() throws IOException
    {
	//Encrypt a move forward command and send using bluetooth
	command = "MSF0000000";
	command = command + getChecksum(command);
	sendMessage(command); 
    }
	
    public void moveBackward() throws IOException
    {
	//Encrypt a move backward command and send using bluetooth
	command = "MSB0000000";
	command = command + getChecksum(command);
	sendMessage(command);
    }
	
    public void turnLeft(int degrees) throws IOException
    {
	//encrypt a turn left command and send using bluetooth
	if(degrees == 0)
	    {
		command = "TNL0000000";
	    }
	else
	    {
		command = "TNL0000090";
	    }
	command = command + getChecksum(command);
	sendMessage(command); 
    }
	
    public void turnRight(int degrees) throws IOException
    {
	//encrypt a turn right command and send using bluetooth
	if(degrees == 0)
	    {
		command = "TNR0000000";
	    }
	else 
	    {
		command = "TNR0000090";
	    }
	command = command + getChecksum(command);
	sendMessage(command); 
    }
	
    public void stop() throws IOException
    {
	//encrypt a stop command and send using bluetooth
	command = "ST00000000";
	command = command + getChecksum(command);
	sendMessage(command); 
    }

    public void getTouchSensor() throws IOException
    {
	command ="RST0000000";
	command = command + getChecksum(command);
	sendMessage(command);
    }
    
    public boolean getTouchValue(){
    	if(touchValue == 1){
    		return true;
    	}else{
    		return false;
    	}
    }
    
    //verify the checksum that is held at the 11th byte.
    public boolean verifyChecksum(String message) {
	if(message.length() == 11) {
            if(getChecksum(message.substring(0, 10)).equals(message.substring(10))){
                return true;
            }
        }
        return false;
    }

    //Gets the checksum to be sent as the byte.
    private String getChecksum(String message) {
	int sum = 0;
        String ret;
        byte[] buffer = message.getBytes();
        for (int i = 0; i < buffer.length; i++) {
            sum += (int) buffer[i];
        }
        sum = sum % 256;
        byte[] checksum = new byte[1];
        checksum[0] = (byte) sum;
        ret = new String(checksum);
        return ret;
    }

	public void getMicrophoneSensor() throws IOException {
		command ="RSM0000000";
		command = command + getChecksum(command);
		sendMessage(command);		
	}

	public void getLightSensor() throws IOException {
		command ="RSL0000000";
		command = command + getChecksum(command);
		sendMessage(command);
	}

	public void getUltraSensor() throws IOException {
		command ="RSU0000000";
		command = command + getChecksum(command);
		sendMessage(command);
	}
}
