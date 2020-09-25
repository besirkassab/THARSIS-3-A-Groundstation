import tr.edu.hacettepe.uav.tharsis3a.havi.Havi;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Main extends JFrame {
    public Main(String title) throws HeadlessException {
        super(title);
        setFocusable(false);
        setSize(1980,1080);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public static void main(String[] args) throws IOException {
        Main arayuz = new Main("THARSIS 3-A GROUNDSTATION");
        AnaPanel anaPanel = new AnaPanel();

        Havi.setCallback(anaPanel);
        Thread htp_thread = new Thread(AnaPanel::htp_thread);
        htp_thread.start();

        arayuz.add(anaPanel);
        arayuz.setVisible(true);
    }
}