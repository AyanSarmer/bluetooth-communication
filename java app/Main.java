import java.io.IOException;

public class Main {
    public static void main(String[] args) throws InterruptedException, IOException {
        Frame f = new Frame();
        BluetoothClient BC = new BluetoothClient();

        while(true) {
            if(f.scanningButtonFlag == true) {
                f.scanningButtonFlag = false;
                BC.startDiscovery();
            }

            if(BC.DDLC.DDLCflag == true) {
                BC.DDLC.DDLCflag = false;
                for(int i = 0; i < 5; i++) {
                    if(BC.DDLC.address[i] != null) {
                        f.comboBox.addItem(BC.DDLC.name[i]);
                    }
                }
                f.showMessageDialog("scanning completed");
                f.connectButton.setEnabled(true);
                f.scanningButton.setEnabled(false);
            }

            if(f.connectButtonFlag == true) {
                f.connectButtonFlag = false;
                f.connectButton.setEnabled(false);                
                String address = BC.DDLC.address[f.comboBox.getSelectedIndex()];
                BC.DDLC.openConnection("btspp://" + address + ":1");
                f.colorButton.setEnabled(true);
            }

            if(f.sendFlag == true) {
                f.sendFlag = false;
                int ColorValue;
                byte key = 1, hundreds, tens, units;

                BC.DDLC.toSendBytes[0] = key;
                BC.DDLC.toSendBytes[1] = (byte)(f.ledNum);

                ColorValue = f.currentColor.getRed();
                hundreds = (byte)(ColorValue / 100);
                tens = (byte)((ColorValue - hundreds * 100) / 10);
                units = (byte)(ColorValue - hundreds * 100 - tens * 10);
                BC.DDLC.toSendBytes[2] = hundreds;
                BC.DDLC.toSendBytes[3] = tens;
                BC.DDLC.toSendBytes[4] = units;

                ColorValue = f.currentColor.getGreen();
                hundreds = (byte)(ColorValue / 100);
                tens = (byte)((ColorValue - hundreds * 100) / 10);
                units = (byte)(ColorValue - hundreds * 100 - tens * 10);
                BC.DDLC.toSendBytes[5] = hundreds;
                BC.DDLC.toSendBytes[6] = tens;
                BC.DDLC.toSendBytes[7] = units;

                ColorValue = f.currentColor.getBlue();
                hundreds = (byte)(ColorValue / 100);
                tens = (byte)((ColorValue - hundreds * 100) / 10);
                units = (byte)(ColorValue - hundreds * 100 - tens * 10);
                BC.DDLC.toSendBytes[8] = hundreds;
                BC.DDLC.toSendBytes[9] = tens;
                BC.DDLC.toSendBytes[10] = units;
                BC.DDLC.sendData();
            }

            if(BC.DDLC.dataReceivedFlag == true) {
                BC.DDLC.dataReceivedFlag = false;
                f.progressBar.setValue(BC.DDLC.receivedData);               
            }

            Thread.sleep(20);
        }
    }    
}