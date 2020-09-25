import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GrafikFramePanel extends JPanel {
    BufferedImage imageGrafikSicaklik;
    BufferedImage imageGrafikBasinc;
    BufferedImage imageGrafikHiz;
    BufferedImage imageGrafikYukseklik;
    GrafikBellek[] grafikBellekListesiSicaklik = new GrafikBellek[146];
    GrafikBellek[] grafikBellekListesiBasinc = new GrafikBellek[146];
    GrafikBellek[] grafikBellekListesiHiz = new GrafikBellek[146];
    GrafikBellek[] grafikBellekListesiYukseklik = new GrafikBellek[146];
    int countSicakliik = 0;
    int countBasinc = 0;
    int countHiz = 0;
    int countYukseklik = 0;
    int kontrolSicaklik = 1;
    int kontrolBasinc = 1;
    int kontrolHiz = 1;
    int kontrolYukseklik = 1;
    boolean checkSicaklik = false;
    boolean checkBasinc = false;
    boolean checkHiz = false;
    boolean checkYukseklik = false;
    public GrafikFramePanel(){


        setLayout(null);
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
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.WHITE);

        g.drawImage(imageGrafikSicaklik, 36, 16, imageGrafikSicaklik.getWidth(), imageGrafikSicaklik.getHeight(), this);
        g.drawImage(imageGrafikBasinc, 736, 16, imageGrafikBasinc.getWidth(), imageGrafikBasinc.getHeight(), this);
        g.drawImage(imageGrafikHiz, 36, 398, imageGrafikHiz.getWidth(), imageGrafikHiz.getHeight(), this);
        g.drawImage(imageGrafikYukseklik, 740, 398, imageGrafikYukseklik.getWidth(), (imageGrafikYukseklik.getHeight() * 1009) / 1000, this);
    }

    public void grafikCizimSicaklik(JPanel grafikpaneli, int startX, double instantHeat){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));

        if(startX >= 510){
            grafikpaneli.paint(grafikpaneli.getGraphics());

            grafikBellekListesiSicaklik[countSicakliik] = new GrafikBellek(startX,(int) (272 - (instantHeat / 45) * 140), startX + 3, (int) (272 - (instantHeat / 45) * 140));

            for (int j = 0 ; j < grafikBellekListesiSicaklik.length-2; j++ ){

                grafikBellekListesiSicaklik[j].x1 = grafikBellekListesiSicaklik[j+1].x1 ;
                grafikBellekListesiSicaklik[j].y1 = grafikBellekListesiSicaklik[j+1].y1 ;
                grafikBellekListesiSicaklik[j].x2 = grafikBellekListesiSicaklik[j+1].x2 ;
                grafikBellekListesiSicaklik[j].y2 = grafikBellekListesiSicaklik[j+1].y2 ;
                cizici.drawLine(grafikBellekListesiSicaklik[j].x1-3,grafikBellekListesiSicaklik[j].y1,grafikBellekListesiSicaklik[j].x2-3,grafikBellekListesiSicaklik[j].y2);
                grafikBellekListesiSicaklik[j].x1 -= 3;
                grafikBellekListesiSicaklik[j].x2 -= 3;

            }
            kontrolSicaklik++;

        }else{
            grafikBellekListesiSicaklik[countSicakliik] = new GrafikBellek(startX,(int) (272 - (instantHeat / 45) * 140), startX + 3, (int) (272 - (instantHeat / 45) * 140));
            countSicakliik++;
        }
        cizici.drawLine(startX,(int) (272 - (instantHeat / 45) * 140), startX + 3, (int) (272 - (instantHeat / 45) * 140));
    }

    public void grafikCizimBasinc(JPanel grafikpaneli, int startX, double pressure){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));

        if(startX >= 1240){
            grafikBellekListesiBasinc[countBasinc] = new GrafikBellek(startX, 245 - (int) (15*(((pressure*1000) - 90000) / 540)) , startX + 3, 245 - (int) (15*(((pressure*1000) - 90000) / 540)) );

            for (int j = 0 ; j < grafikBellekListesiBasinc.length-1; j++ ){

                grafikBellekListesiBasinc[j].x1 = grafikBellekListesiBasinc[j+1].x1 ;
                grafikBellekListesiBasinc[j].y1 = grafikBellekListesiBasinc[j+1].y1 ;
                grafikBellekListesiBasinc[j].x2 = grafikBellekListesiBasinc[j+1].x2 ;
                grafikBellekListesiBasinc[j].y2 = grafikBellekListesiBasinc[j+1].y2 ;
                cizici.drawLine(grafikBellekListesiBasinc[j].x1-3,grafikBellekListesiBasinc[j].y1,grafikBellekListesiBasinc[j].x2-3,grafikBellekListesiBasinc[j].y2);
                grafikBellekListesiBasinc[j].x1 -= 3;
                grafikBellekListesiBasinc[j].x2 -= 3;

            }
            kontrolBasinc++;

        }else{
            grafikBellekListesiBasinc[countBasinc] = new GrafikBellek(startX, 245 - (int) (15*(((pressure*1000) - 90000) / 540)), startX + 3, 245 - (int) (15*(((pressure*1000) - 90000) / 540)));
            countBasinc++;
        }
        cizici.drawLine(startX, 245 - (int) (15*(((pressure*1000) - 90000) / 540)), startX + 3, 245 - (int) (15*(((pressure*1000) - 90000) / 540)));

    }

    public void grafikCizimHiz(JPanel grafikpaneli, int startX, double speed){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));

        if(startX >= 510){

            if (speed != 0) {
                grafikBellekListesiHiz[countHiz] = new GrafikBellek(startX, (int)(680 - ((speed / 2) * 20)), startX + 3, (int)(680 - ((speed / 2) * 20)));
            } else {
                grafikBellekListesiHiz[countHiz] = new GrafikBellek(startX, 675, startX + 3, 675);
            }
            for (int j = 0 ; j < grafikBellekListesiHiz.length-2; j++ ){
                grafikBellekListesiHiz[j].x1 = grafikBellekListesiHiz[j+1].x1 ;
                grafikBellekListesiHiz[j].y1 = grafikBellekListesiHiz[j+1].y1 ;
                grafikBellekListesiHiz[j].x2 = grafikBellekListesiHiz[j+1].x2 ;
                grafikBellekListesiHiz[j].y2 = grafikBellekListesiHiz[j+1].y2 ;
                cizici.drawLine(grafikBellekListesiHiz[j].x1-3,grafikBellekListesiHiz[j].y1,grafikBellekListesiHiz[j].x2-3,grafikBellekListesiHiz[j].y2);
                grafikBellekListesiHiz[j].x1 -= 3;
                grafikBellekListesiHiz[j].x2 -= 3;

            }
            kontrolHiz++;
        }else {
            if (speed != 0) {
                grafikBellekListesiHiz[countHiz] = new GrafikBellek(startX, (int)(680 - ((speed / 2) * 20)), startX + 3, (int)(680 - ((speed / 2) * 20)));
                countHiz++;
            } else {
                grafikBellekListesiHiz[countHiz] = new GrafikBellek(startX, 676, startX + 3, 676);
                countHiz++;
            }
        }if (speed != 0) {
            cizici.drawLine(startX, (int)(680 - ((speed / 2) * 20)), startX + 3, (int)(680 - ((speed / 2) * 20)));
        } else {
            cizici.drawLine(startX, 676, startX + 3, 676);
        }
    }
    public void grafikCizimYukseklik(JPanel grafikpaneli, int startX, double instantHeight){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));

        if(startX >= 1240){
            grafikBellekListesiYukseklik[countYukseklik] = new GrafikBellek(startX - 7 , 737 - (int) (2*instantHeight/6)+100, startX - 4, 737 - (int) (2*instantHeight/6)+100);

            for (int j = 0 ; j < grafikBellekListesiYukseklik.length-2; j++ ){

                grafikBellekListesiYukseklik[j].x1 = grafikBellekListesiYukseklik[j+1].x1 ;
                grafikBellekListesiYukseklik[j].y1 = grafikBellekListesiYukseklik[j+1].y1 ;
                grafikBellekListesiYukseklik[j].x2 = grafikBellekListesiYukseklik[j+1].x2 ;
                grafikBellekListesiYukseklik[j].y2 = grafikBellekListesiYukseklik[j+1].y2 ;
                cizici.drawLine(grafikBellekListesiYukseklik[j].x1-3,grafikBellekListesiYukseklik[j].y1,grafikBellekListesiYukseklik[j].x2-3,grafikBellekListesiYukseklik[j].y2);
                grafikBellekListesiYukseklik[j].x1 -= 3;
                grafikBellekListesiYukseklik[j].x2 -= 3;

            }
            kontrolYukseklik++;

        }else{
            grafikBellekListesiYukseklik[countYukseklik] = new GrafikBellek(startX - 7 , 737 - (int) (2*instantHeight/6)+100, startX - 4, 737 - (int) (2*instantHeight/6)+100);
            countYukseklik++;
        }
        cizici.drawLine(startX - 7 , 737 - (int) (2*instantHeight/6)+100, startX - 4, 737 - (int) (2*instantHeight/6)+100);
    }

    public void grafikCizimSicaklikPrototip(JPanel grafikpaneli, int startX, double instantHeat){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));

        if(startX >= 510){
            grafikpaneli.paint(grafikpaneli.getGraphics());

            grafikBellekListesiSicaklik[countSicakliik] = new GrafikBellek(startX,(int) (345 - (instantHeat / 3) * 20), startX + 3, (int) (345 - (instantHeat / 3) * 20));
            if(countSicakliik < 146){
                countSicakliik++;
            }
            for (int j = 0 ; j < grafikBellekListesiSicaklik.length-1; j++ ){
                if (kontrolSicaklik > 1){
                    checkSicaklik = true;
                }
                if (!checkSicaklik){
                    if (j == 145){
                        break;
                    }
                }

                grafikBellekListesiSicaklik[j].x1 = grafikBellekListesiSicaklik[j+1].x1 ;
                grafikBellekListesiSicaklik[j].y1 = grafikBellekListesiSicaklik[j+1].y1 ;
                grafikBellekListesiSicaklik[j].x2 = grafikBellekListesiSicaklik[j+1].x2 ;
                grafikBellekListesiSicaklik[j].y2 = grafikBellekListesiSicaklik[j+1].y2 ;
                cizici.drawLine(grafikBellekListesiSicaklik[j].x1-3,grafikBellekListesiSicaklik[j].y1,grafikBellekListesiSicaklik[j].x2-3,grafikBellekListesiSicaklik[j].y2);
                grafikBellekListesiSicaklik[j].x1 -= 3;
                grafikBellekListesiSicaklik[j].x2 -= 3;

            }
            kontrolSicaklik++;

        }else{
            grafikBellekListesiSicaklik[countSicakliik] = new GrafikBellek(startX,(int) (345 - (instantHeat / 3) * 20), startX + 3, (int) (345 - (instantHeat / 3) * 20));
            countSicakliik++;
        }
        cizici.drawLine(startX,(int) (345 - (instantHeat / 3) * 20), startX + 3, (int) (345 - (instantHeat / 3) * 20));

    }
    public void grafikCizimBasincPrototip(JPanel grafikpaneli, int startX, double pressure){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));
        if(startX >= 1240){
            grafikBellekListesiBasinc[countBasinc] = new GrafikBellek(startX, 245 - (int) ((pressure - 90000) / 100) * 2, startX + 3, 245 - (int) ((pressure - 90000) / 100) * 2);
            if(countBasinc < 146){
                countBasinc++;
            }
            for (int j = 0 ; j < grafikBellekListesiBasinc.length-1; j++ ){
                if (kontrolBasinc > 1){
                    checkBasinc = true;
                }
                if (!checkBasinc){
                    if (j == 145){
                        break;
                    }
                }

                grafikBellekListesiBasinc[j].x1 = grafikBellekListesiBasinc[j+1].x1 ;
                grafikBellekListesiBasinc[j].y1 = grafikBellekListesiBasinc[j+1].y1 ;
                grafikBellekListesiBasinc[j].x2 = grafikBellekListesiBasinc[j+1].x2 ;
                grafikBellekListesiBasinc[j].y2 = grafikBellekListesiBasinc[j+1].y2 ;
                cizici.drawLine(grafikBellekListesiBasinc[j].x1-3,grafikBellekListesiBasinc[j].y1,grafikBellekListesiBasinc[j].x2-3,grafikBellekListesiBasinc[j].y2);
                grafikBellekListesiBasinc[j].x1 -= 3;
                grafikBellekListesiBasinc[j].x2 -= 3;

            }
            kontrolBasinc++;

        }else{
            grafikBellekListesiBasinc[countBasinc] = new GrafikBellek(startX, 245 - (int) ((pressure - 90000) / 100) * 2, startX + 3, 245 - (int) ((pressure - 90000) / 100) * 2);
            countBasinc++;
        }
        cizici.drawLine(startX, 245 - (int) ((pressure - 90000) / 100) * 2, startX + 3, 245 - (int) ((pressure - 90000) / 100) * 2);
    }
    public void grafikCizimHizPrototip(JPanel grafikpaneli, int startX, double speed){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));
        if(startX >= 510){

            if (speed != 0) {
                grafikBellekListesiHiz[countHiz] = new GrafikBellek(startX, (int) (680 - ((speed / 2) * 20)), startX + 3, (int) (680 - ((speed / 2) * 20)));
            } else {
                grafikBellekListesiHiz[countHiz] = new GrafikBellek(startX, 675, startX + 3, 675);
            }
            if(countHiz < 146){
                countHiz++;
            }
            for (int j = 0 ; j < grafikBellekListesiHiz.length-1; j++ ){
                if (kontrolHiz > 1){
                    checkHiz = true;
                }
                if (!checkHiz){
                    if (j == 145){
                        break;
                    }
                }
                grafikBellekListesiHiz[j].x1 = grafikBellekListesiHiz[j+1].x1 ;
                grafikBellekListesiHiz[j].y1 = grafikBellekListesiHiz[j+1].y1 ;
                grafikBellekListesiHiz[j].x2 = grafikBellekListesiHiz[j+1].x2 ;
                grafikBellekListesiHiz[j].y2 = grafikBellekListesiHiz[j+1].y2 ;
                cizici.drawLine(grafikBellekListesiHiz[j].x1-3,grafikBellekListesiHiz[j].y1,grafikBellekListesiHiz[j].x2-3,grafikBellekListesiHiz[j].y2);
                grafikBellekListesiHiz[j].x1 -= 3;
                grafikBellekListesiHiz[j].x2 -= 3;

            }
            kontrolHiz++;
        }else {
            if (speed != 0) {
                grafikBellekListesiHiz[countHiz] = new GrafikBellek(startX, (int) (680 - ((speed / 2) * 20)), startX + 3, (int) (680 - ((speed / 2) * 20)));
                countHiz++;
            } else {
                grafikBellekListesiHiz[countHiz] = new GrafikBellek(startX, 675, startX + 3, 675);
                countHiz++;
            }
        }if (speed != 0) {
            cizici.drawLine(startX, (int) (680 - ((speed / 2) * 20)), startX + 3, (int) (680 - ((speed / 2) * 20)));
        } else {
            cizici.drawLine(startX, 675, startX + 3, 675);
        }
    }

    public void grafikCizimYukseklikPrototip(JPanel grafikpaneli, int startX, double instantHeight){
        Graphics cizici = grafikpaneli.getGraphics();
        cizici.setColor(new java.awt.Color(255,255,255));
        if(startX >= 1240){
            grafikBellekListesiYukseklik[countYukseklik] = new GrafikBellek(startX  , (int) instantHeight+120, startX +3, (int) instantHeight+120);
            if(countYukseklik < 146){
                countYukseklik++;
            }
            for (int j = 0 ; j < grafikBellekListesiYukseklik.length-1; j++ ){
                if (kontrolYukseklik > 1){
                    checkYukseklik = true;
                }
                if (!checkYukseklik){
                    if (j == 145){
                        break;
                    }
                }
                grafikBellekListesiYukseklik[j].x1 = grafikBellekListesiYukseklik[j+1].x1 ;
                grafikBellekListesiYukseklik[j].y1 = grafikBellekListesiYukseklik[j+1].y1 ;
                grafikBellekListesiYukseklik[j].x2 = grafikBellekListesiYukseklik[j+1].x2 ;
                grafikBellekListesiYukseklik[j].y2 = grafikBellekListesiYukseklik[j+1].y2 ;
                cizici.drawLine(grafikBellekListesiYukseklik[j].x1-3,grafikBellekListesiYukseklik[j].y1,grafikBellekListesiYukseklik[j].x2-3,grafikBellekListesiYukseklik[j].y2);
                grafikBellekListesiYukseklik[j].x1 -= 3;
                grafikBellekListesiYukseklik[j].x2 -= 3;

            }
            kontrolYukseklik++;

        }else{
            grafikBellekListesiYukseklik[countYukseklik] = new GrafikBellek(startX  , (int) instantHeight+120, startX +3, (int) instantHeight+120);
            countYukseklik++;
        }
        cizici.drawLine(startX  , (int) instantHeight+120, startX +3, (int) instantHeight+120);
    }
}