import javax.bluetooth.BluetoothStateException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;

public class BluetoothClient {
    
    DeviceDiscoveredLoggingCallback DDLC = new DeviceDiscoveredLoggingCallback();

    public void startDiscovery() throws BluetoothStateException, InterruptedException {
        DiscoveryAgent agent = LocalDevice.getLocalDevice().getDiscoveryAgent();
		agent.startInquiry(DiscoveryAgent.GIAC, DDLC);

		synchronized (BluetoothClient.class) {
			BluetoothClient.class.wait();
		}
    }
}
