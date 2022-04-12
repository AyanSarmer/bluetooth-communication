import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Frame implements ActionListener {

    JLabel luminosityLabel;
    JProgressBar progressBar;
    Color customColor = new Color(215, 215, 215);
    Color currentColor;
    MouseListener ml;
    boolean scanningButtonFlag = false;
    boolean connectButtonFlag = false;
    boolean sendFlag = false;
    JFrame frame;
    JComboBox<String> comboBox;
    JButton scanningButton, connectButton, colorButton;
    Label[] label = new Label[105];
    int ledNum;
    int[] X = new int[]{
        30, 53, 76, 99, 122, 145, 168, 191, 214, 237, 260, 283, 306, 329, 352, 375, 398, 421, 444, 467, 490,        
        490, 467, 444, 421, 398, 375, 352, 329, 306, 283, 260, 237, 214, 191, 168, 145, 122, 99, 76, 53, 30,
        30, 53, 76, 99, 122, 145, 168, 191, 214, 237, 260, 283, 306, 329, 352, 375, 398, 421, 444, 467, 490,
        490, 467, 444, 421, 398, 375, 352, 329, 306, 283, 260, 237, 214, 191, 168, 145, 122, 99, 76, 53, 30,
        30, 53, 76, 99, 122, 145, 168, 191, 214, 237, 260, 283, 306, 329, 352, 375, 398, 421, 444, 467, 490
    };
    int[] Y = new int[]{
        170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170, 170,
        193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193, 193,
        216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216, 216,
        239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239, 239,
        262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262, 262
    };

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == scanningButton) {
            scanningButtonFlag = true;
        }
        else if(e.getSource() == connectButton) {
            connectButtonFlag = true;
        }
        else if(e.getSource() == colorButton) {
            currentColor = JColorChooser.showDialog(colorButton,"Choose",Color.WHITE); 
        }
    }

    Frame() {
        ml = new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                for(int i = 0; i < 105; i++) {
                    if(e.getSource() == label[i]) {
                        label[i].setBackground(currentColor);
                        ledNum = i;

                        sendFlag = true;
                    }
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

            @Override
            public void mouseExited(MouseEvent e) {
                // TODO Auto-generated method stub
                
            }

        };

        frame = new JFrame("Bluetooth communication");
        frame.setSize(555, 560);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        scanningButton = new JButton("Scanning");
        scanningButton.setBounds(125, 20, 110, 30);
        scanningButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        scanningButton.setFocusable(false);
        scanningButton.addActionListener(this);
        frame.add(scanningButton);

        connectButton = new JButton("Connect");
        connectButton.setBounds(125, 60, 110, 30);
        connectButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        connectButton.setFocusable(false);
        connectButton.addActionListener(this);
        connectButton.setEnabled(false);
        frame.add(connectButton);

        comboBox = new JComboBox<>();
        comboBox.setBounds(275, 20, 130, 30);
        frame.add(comboBox);

        luminosityLabel = new JLabel("Luminosity");
        luminosityLabel.setBounds(230, 410, 150, 30);
        luminosityLabel.setForeground(Color.BLUE);
        luminosityLabel.setFont(new Font("Ink Free", Font.BOLD, 18));
        frame.add(luminosityLabel);

        progressBar = new JProgressBar(0, 0, 100);
        progressBar.setBounds(212, 440, 120, 20);
        progressBar.setForeground(Color.BLUE);
        frame.add(progressBar);

        colorButton = new JButton("Color");
        colorButton.setBounds(230, 300, 80, 30);
        colorButton.setFont(new Font("Ink Free", Font.BOLD, 20));
        colorButton.setFocusable(false);
        colorButton.setEnabled(false);
        colorButton.addActionListener(this);
        frame.add(colorButton);

        for(int i = 0; i < 105; i++) {
            label[i] = new Label();
            label[i].setBounds(X[i], Y[i], 20, 20);
            label[i].setFocusable(true);
            label[i].setBackground(customColor);
            frame.add(label[i]);
        }

        for(int i = 0; i < 105; i++) {
            label[i].addMouseListener(ml);
        }

        frame.setVisible(true);
    }

    void showMessageDialog(String message) {
        JOptionPane.showMessageDialog(frame, message);
    }
    
}
