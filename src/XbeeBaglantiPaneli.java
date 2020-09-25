import tr.edu.hacettepe.uav.tharsis3a.havi.Havi;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class XbeeBaglantiPaneli extends JPanel {

    JTextField portCombo;
    JTextField baudaCombo;
    private JButton connectXbee;
    boolean connectOrdisconnectXbee = true;
    CSVOlustur saveCSVFile;

    public XbeeBaglantiPaneli(){
        setLayout(null);

        JLabel baglantiAyarlari = new JLabel("Bağlantı Ayarları");
        JLabel port = new JLabel("Port:");
        JLabel baudaRate = new JLabel("Bauda Rate:");
        portCombo = new JTextField();
        baudaCombo = new JTextField();
        connectXbee = new JButton("Xbee'ye Bağlan");

        baglantiAyarlari.setFont(new java.awt.Font("Tahoma", Font.BOLD,14));
        baglantiAyarlari.setBounds(15,15,120,20);
        port.setFont(new java.awt.Font("Tahoma",Font.BOLD,14));
        port.setBounds(75,70,80,20);
        baudaRate.setFont(new java.awt.Font("Tahoma",Font.BOLD,14));
        baudaRate.setBounds(25,130,110,20);
        portCombo.setBounds(160,60,110,50);
        baudaCombo.setBounds(160,120,110,50);
        connectXbee.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        connectXbee.setBounds(320,160,150,30);

        add(baglantiAyarlari);
        add(port);
        add(baudaRate);
        add(portCombo);
        add(baudaCombo);
        add(connectXbee);

        connectXbee.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connectXbeeActionPerformed(evt);
            }
        });

    }

    private void connectXbeeActionPerformed(java.awt.event.ActionEvent evt){
        if (connectOrdisconnectXbee) {
            try {
                Havi.setPort(portCombo.getText(), Integer.parseInt(baudaCombo.getText()));
                connectXbee.setText("Bağlantıyı Kes");
                connectOrdisconnectXbee = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else{
            Havi.freePort();
            connectXbee.setText("Xbee'ye Bağlan");
            connectOrdisconnectXbee = true;
            saveCSVFile.output.flush();
            saveCSVFile.output.close();
            try {
                saveCSVFile.fw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
