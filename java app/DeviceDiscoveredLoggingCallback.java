import javax.bluetooth.DeviceClass;
import javax.bluetooth.DiscoveryListener;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.RemoteDevice;
import javax.bluetooth.ServiceRecord;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.Timer;
import java.util.TimerTask;

public class DeviceDiscoveredLoggingCallback implements DiscoveryListener {

    boolean DDLCflag = false;
    String[] name = new String[5];
    String[] address = new String[5];
    int counter = 0;
	StreamConnection connection;
	OutputStream outputStream;
	InputStreamReader inputStream;
	IncomingMessagesLoggingRunnable IMLR;
	public boolean dataReceivedFlag = false;
	byte[] toSendBytes = new byte[11];
	int receivedData;

    public void openConnection(String address) throws IOException {
		connection = (StreamConnection) Connector.open(address);
		
		if (connection == null) {
			System.err.println("Could not open connection to address: " + address);
			System.exit(1);
		}

		outputStream = connection.openOutputStream();

		ExecutorService service = Executors.newSingleThreadExecutor();
		IMLR = new IncomingMessagesLoggingRunnable(connection);
		service.submit(IMLR);
		checkReceivedData();
		toSendBytes[0] = 2;
		sendData();
	}

    public void checkReceivedData() {
		TimerTask timerTask = new TimerTask() {
            public void run() {
				if(IMLR.receiveFlag == true) {
					IMLR.receiveFlag = false;
					receivedData = IMLR.incomingData;					
					dataReceivedFlag = true;					
				}
			}
		};
		Timer timer = new Timer();
        timer.scheduleAtFixedRate(timerTask, 0, 200);
	}

    public void sendData() {
		try {
			outputStream.write(toSendBytes);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

    @Override
    public void deviceDiscovered(RemoteDevice btDevice, DeviceClass code) {
        address[counter] = btDevice.getBluetoothAddress();
		try {
			name[counter] = btDevice.getFriendlyName(false);
            counter++;
		} catch (IOException e) {
			System.err.println("Error while retrieving name for device [" + address + "]");
			e.printStackTrace();
		}
    }

    @Override
    public void inquiryCompleted(int arg0) {
        DDLCflag = true;
		synchronized (BluetoothClient.class) {
			BluetoothClient.class.notify();
		}
    }

    @Override
    public void serviceSearchCompleted(int arg0, int arg1) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void servicesDiscovered(int arg0, ServiceRecord[] arg1) {
        // TODO Auto-generated method stub
        
    }
    
}
