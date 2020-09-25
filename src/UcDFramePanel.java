import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UcDFramePanel extends JPanel {
    BufferedImage imageUcDGoruntu;
    public UcDFramePanel(){
        setLayout(null);
        setBackground(new java.awt.Color(136, 27, 8));

        try {
            imageUcDGoruntu = ImageIO.read(new FileImageInputStream(new File("UcBoyutUydu.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imageUcDGoruntu,0,0,42*imageUcDGoruntu.getWidth()/41,35*imageUcDGoruntu.getHeight()/34,this);
    }
}
