import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class UcBoyutClass extends JPanel {
    BufferedImage image3DGoruntu ;

    public UcBoyutClass(){
        try {
            image3DGoruntu = ImageIO.read(new FileImageInputStream(new File("UcBoyutUydu.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(image3DGoruntu,0,0,42*image3DGoruntu.getWidth()/41,35*image3DGoruntu.getHeight()/34,this);

    }
}