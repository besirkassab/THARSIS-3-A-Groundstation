import javax.swing.*;
import java.awt.*;

public class GeciciSagYukseklik extends JPanel {
    JLabel digitalLabel ;
    public GeciciSagYukseklik(){
        setBackground(new java.awt.Color(153,153,153));
        setBounds(217,113,44,30);
        setLayout(null);
        digitalLabel = new JLabel("YUKSEKLIK");
        digitalLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD,9));
        digitalLabel.setBounds(0,0,40,30);
        add(digitalLabel);
    }
}
