import javax.swing.*;

public class UcDFrameTest extends JFrame {
    UcDFramePanel ucDFramePanel = new UcDFramePanel();
    public UcDFrameTest(String title){
        super(title);
        setFocusable(false);
        setResizable(false);
        setBounds(0,0,1400,790);


        add(ucDFramePanel);
    }
}
