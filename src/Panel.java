import javax.swing.*;
import java.awt.*;

public class Panel extends JPanel {
    GeciciSolHız geciciSolHız;
    GeciciSagYukseklik geciciSagYukseklik;
    UstBilgiPaneli ustBilgiPaneli;
    GoruntuPaneli goruntuPaneli;
    JLabel yukseklik = new JLabel("altitude");
    JLabel hiz = new JLabel("speed");



    public Panel() {
        setBounds(240,212,265,240);
        setBackground(new java.awt.Color(0,0,0));
        setFocusable(true);
        setLayout(null);
        yukseklik.setBounds(217,95,44,13);
        yukseklik.setFont(new java.awt.Font("Tahoma",Font.BOLD,9));
        yukseklik.setForeground(new java.awt.Color(1,198,248));
        hiz.setBounds(0,95,44,13);
        hiz.setFont(new java.awt.Font("Tahoma",Font.BOLD,11));
        hiz.setForeground(new java.awt.Color(1,198,248));
        geciciSolHız = new GeciciSolHız();
        geciciSagYukseklik = new GeciciSagYukseklik();
        goruntuPaneli = new GoruntuPaneli();
        ustBilgiPaneli = new UstBilgiPaneli();
        add(yukseklik);
        add(hiz);
        add(geciciSolHız);
        add(geciciSagYukseklik);
        add(ustBilgiPaneli);
        add(goruntuPaneli);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        g2d.setColor(new Color(255, 255, 51));
        g2d.drawLine(36,130,47,123);
        g2d.drawLine(36,130,47,137);
        g2d.drawLine(47,123,47,137);
        g2d.drawLine(202,123,215,130);
        g2d.drawLine(202,137,215,130);
        g2d.drawLine(202,137,202,123);

    }
}
