import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrafikClass extends JPanel {
    BufferedImage imageGrafikSicaklik;
    BufferedImage imageGrafikBasinc;
    BufferedImage imageGrafikHiz;
    BufferedImage imageGrafikYukseklik;
    //Graphics cizici;
    public GrafikClass(){
        setBackground(new java.awt.Color(136, 27, 8));
        try {
            imageGrafikSicaklik = ImageIO.read(new FileImageInputStream(new File("Sıcaklık.png")));
            imageGrafikBasinc = ImageIO.read(new FileImageInputStream(new File("Basınç.png")));
            imageGrafikHiz = ImageIO.read(new FileImageInputStream(new File("Hız.png")));
            imageGrafikYukseklik = ImageIO.read(new FileImageInputStream(new File("Yükseklik.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.WHITE);
        g.drawImage(imageGrafikSicaklik, 36, 16,imageGrafikSicaklik.getWidth(),imageGrafikSicaklik.getHeight(), this);
        g.drawImage(imageGrafikBasinc, 736, 16,imageGrafikBasinc.getWidth(),imageGrafikBasinc.getHeight(), this);
        g.drawImage(imageGrafikHiz, 36, 398, imageGrafikHiz.getWidth(),imageGrafikHiz.getHeight(), this);
        g.drawImage(imageGrafikYukseklik, 740, 398, imageGrafikYukseklik.getWidth(), (imageGrafikYukseklik.getHeight()*1009)/1000, this);
        //Arayuz.ggg = Arayuz.grafikPaneli.getGraphics();
    }
    public void grafikCizimSicaklik(JPanel grafikpaneli, int startX, double instantHeat){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));
        cizici.drawLine(startX,(int) (355 - (instantHeat / 3) * 20), startX + 3, (int) (355 - (instantHeat / 3) * 20));
    }
    public void grafikCizimBasinc(JPanel grafikpaneli, int startX, double pressure){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));
        cizici.drawLine(startX, 255 - (int) (((pressure*1000) - 90000) / 100) * 2, startX + 3, 255 - (int) (((pressure*1000) - 90000) / 100) * 2);
    }
    public void grafikCizimHiz(JPanel grafikpaneli, int startX, double speed){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));
        cizici.drawLine(startX, (int)(680 - ((speed / 2) * 20)), startX + 3, (int)(680 - ((speed / 2) * 20)));
    }
    public void grafikCizimYukseklik(JPanel grafikpaneli, int startX, double instantHeight){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));
        cizici.drawLine(startX - 7 , (int) (instantHeight/2)-50, startX - 4, (int) (instantHeight/2)-50);
    }

}
