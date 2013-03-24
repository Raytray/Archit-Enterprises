public class Bluetooth {
    public int Connection;
    public int Ports;
    public sensorInfo sensors;
    
    public boolean send(String command);
    public boolean ValidateConnection();
    public boolean CheckConnection();
    public boolean UpdateSensors();
    public boolean CheckPort(int x);
}
