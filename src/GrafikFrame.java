import javax.swing.*;

public class GrafikFrame extends JFrame {
    GrafikFramePanel grafikFramePanel = new GrafikFramePanel();

    public GrafikFrame(String title){
        super(title);
        setFocusable(false);
        setResizable(false);
        setBounds(0,0,1400,790);


        add(grafikFramePanel);
    }
}

