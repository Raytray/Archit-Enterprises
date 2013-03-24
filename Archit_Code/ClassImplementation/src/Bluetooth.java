public class Bluetooth {
    public int Connection;
    public int Ports;
    private UltrasonicSensor UInterface;
    private LightSensor LInterface;
    private MicrophoneSensor MInterface;
    private TouchSensor TInterface;
    
    public boolean send(String command){
    	return true;
    }
    public boolean ValidateConnection(){
    	return true;
    }
    public boolean CheckConnection(){
    	return true;
    }
    public boolean UpdateSensors(){
    	return true;
    }
    public boolean CheckPort(int x){
    	return true;
    }
}
