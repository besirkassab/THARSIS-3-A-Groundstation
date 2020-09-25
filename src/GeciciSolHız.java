import javax.swing.*;
import java.awt.*;

public class GeciciSolHız extends JPanel {
    JLabel digitalLabel;

    public GeciciSolHız(){
        setBackground(new java.awt.Color(153,153,153));
        setBounds(0,113,35,30);
        setLayout(null);
        digitalLabel = new JLabel("HIZ");
        digitalLabel.setFont(new java.awt.Font("Tahoma", Font.BOLD,14));
        digitalLabel.setBounds(0,0,35,30);
        add(digitalLabel);
    }
}
