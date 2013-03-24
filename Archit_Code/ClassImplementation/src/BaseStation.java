public class BaseStation {
    private Bluetooth Comm;
    private UltrasonicSensor UInterface;
    private LightSensor LInterface;
    private MicrophoneSensor MInterface;
    private TouchSensor TInterface;
 
    public boolean EstablishBluetooth(){
    	return true;
    }
    public boolean MoveForward(float radians){
    	return true;
    }
    public boolean moveBackward(float radians){
    	return true;
    }
    public boolean Turn(float radians){
    	return true;
    }
    public int CheckSensor(int sensor){
    	return 0;
    }
}
