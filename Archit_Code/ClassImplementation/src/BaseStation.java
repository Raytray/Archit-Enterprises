public class BaseStation {
    private Bluetooth Comm;
    private UltrasonicSensor UInterface;
    private LightSensor LInterface;
    private MicrophoneSensor MInterface;
    private TouchSensor TInterface;
 
    public boolean EstablishBluetooth();
    public boolean MoveForward(float radians);
    public boolean moveBackward(float radians);
    public boolean Turn(float radians);
    public int CheckSensor(int sensor);
}
