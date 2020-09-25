import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class HaritaClass extends JPanel {
    BufferedImage imageHarita;

    public HaritaClass(){
        setBackground(new java.awt.Color(136, 27, 8));
        try{
            imageHarita = ImageIO.read(new FileImageInputStream(new File("Harita.png")));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imageHarita,170,60,5* imageHarita.getWidth()/3,4* imageHarita.getHeight()/3,this);

    }
}
