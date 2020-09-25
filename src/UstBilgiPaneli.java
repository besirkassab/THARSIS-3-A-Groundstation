import javax.swing.*;
import java.awt.*;

public class UstBilgiPaneli extends JPanel {
    JLabel uyduStatusu = new JLabel("STANDBY");

    public UstBilgiPaneli(){
        setBackground(new java.awt.Color(153,153,153));
        setBounds(50,0,150,40);
        setLayout(null);
        JLabel uyduDurumu = new JLabel("UYDU DURUMU");
        JLabel seperating = new JLabel("------------------------------");
        uyduDurumu.setFont(new java.awt.Font("Tahoma", Font.BOLD,11));
        seperating.setFont(new java.awt.Font("Tahoma",Font.BOLD,11));
        uyduStatusu.setFont(new java.awt.Font("Tahoma",Font.BOLD,16));
        uyduStatusu.setForeground(new java.awt.Color(1,198,248));
        uyduDurumu.setBounds(33,3,100,11);
        seperating.setBounds(0,10,150,11);
        uyduStatusu.setBounds(34,19,125,20);
        add(uyduDurumu);
        add(seperating);
        add(uyduStatusu);
    }


    public void changeStatus(String status){
        uyduStatusu.setText(status);
    }
}
