import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import javax.bluetooth.RemoteDevice;
import javax.microedition.io.StreamConnection;

public class IncomingMessagesLoggingRunnable implements Runnable {

    private StreamConnection connection;
	public boolean receiveFlag = false;
	int incomingData;
	byte buffer[] = new byte[1];

    public IncomingMessagesLoggingRunnable(StreamConnection connection) {
		this.connection = connection;
	}

    @Override
    public void run() {
        InputStream input = null;
		RemoteDevice device = null;
		try {
			input = new BufferedInputStream(connection.openInputStream());
			device = RemoteDevice.getRemoteDevice(connection);
		} catch (IOException e) {
			System.err.println("Listening service failed. Incoming messages won't be displayed.");
			e.printStackTrace();
			return;
		}

		while (true) {
			try {
				input.read(buffer);	
				incomingData = Byte.toUnsignedInt(buffer[0]);
				receiveFlag = true;
			} catch (IOException e) {
				System.err.println("Error while reading the incoming message.");
				e.printStackTrace();
			}
		}
    }
    
}
