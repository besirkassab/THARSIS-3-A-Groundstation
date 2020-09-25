import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class VideoAktarimPaneli extends JPanel {
    private JLabel videoYuklemeAlani = new JLabel("Video Yükleme Alanı");
    private JTextField dosyaYolu = new JTextField();
    private JButton gozat = new JButton("Gözat");
    private JLabel gonderimHizi = new JLabel("Gönderim Hızı");
    private JProgressBar videoYuklenmeYuzdesi = new JProgressBar();
    private JButton videoYuklemeBaslat = new JButton("Başlat");
    private JButton videoYuklemeDurdur = new JButton("Durdur");
    private int progressCount = 0;

    public VideoAktarimPaneli(){
        setLayout(null);



        videoYuklemeAlani.setFont(new java.awt.Font("Tahoma", Font.BOLD,14));
        videoYuklemeAlani.setBounds(15,15,150,20);
        dosyaYolu.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        dosyaYolu.setBounds(15,55,350,25);
        gozat.setBounds(420,55,70,25);
        gozat.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        gonderimHizi.setBounds(430,115,70,25);
        gonderimHizi.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        videoYuklenmeYuzdesi.setStringPainted(true);
        videoYuklenmeYuzdesi.setBounds(15,140,470,25);
        videoYuklemeBaslat.setBounds(310,185,85,25);
        videoYuklemeBaslat.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        videoYuklemeDurdur.setBounds(405,185,85,25);
        videoYuklemeDurdur.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));


        add(videoYuklemeAlani);
        add(dosyaYolu);
        add(gozat);
        add(gonderimHizi);
        add(videoYuklenmeYuzdesi);
        add(videoYuklemeBaslat);
        add(videoYuklemeDurdur);

        videoYuklemeBaslat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                videoYuklemeBaslatActionPerformed(evt);
            }
        });

        gozat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gozatActionPerformed(evt);
            }
        });
    }

    private void videoYuklemeBaslatActionPerformed (java.awt.event.ActionEvent evt){
        Timer timer = new Timer(100,null);


        timer.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                progressCount++;
                gonderimHizi.setText((progressCount-videoYuklenmeYuzdesi.getValue())+" byte/second");
                videoYuklenmeYuzdesi.setValue(progressCount);
                if(videoYuklenmeYuzdesi.getValue() == 100){
                    timer.stop();
                    JOptionPane.showMessageDialog(VideoAktarimPaneli.this,"Video Görev Yükü'ne Başarıyla İletildi.");
                    videoYuklenmeYuzdesi.setValue(0);
                }
            }
        });

        timer.start();

    }

    private void gozatActionPerformed (java.awt.event.ActionEvent evt){
        JFileChooser chooseVideoFile = new JFileChooser();
        chooseVideoFile.showOpenDialog(null);
        dosyaYolu.setText(chooseVideoFile.getSelectedFile().getAbsolutePath());
    }
}
