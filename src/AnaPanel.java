import tr.edu.hacettepe.uav.tharsis3a.havi.Havi;
import tr.edu.hacettepe.uav.tharsis3a.havi.IHaviCallbacks;
import tr.edu.hacettepe.uav.tharsis3a.havi.Severity;
import tr.edu.hacettepe.uav.tharsis3a.havi.TelemetryPacket;
import javax.imageio.ImageIO;
import javax.imageio.stream.FileImageInputStream;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AnaPanel extends JPanel implements IHaviCallbacks {
    BufferedImage imageTharsis;
    Panel flightPanel ;
    XbeeBaglantiPaneli xbeeBaglantiPaneli = new XbeeBaglantiPaneli();
    VideoAktarimPaneli videoAktarimPaneli = new VideoAktarimPaneli();
    SecilenTelemetriBilgiPaneli secilenTelemetriBilgiPaneli = new SecilenTelemetriBilgiPaneli();
    GrafikFrame grafikFrameTest = new GrafikFrame("Grafikler");
    UcDFrameTest ucDFrameTest = new UcDFrameTest("3 Boyutlu Uydu Görüntüsü");
    TabbedPane tabbedPane = new TabbedPane();
    CSVOlustur csv = new CSVOlustur();
    JTextArea konsolAkisArea = new JTextArea();
    JScrollPane scrollPane;


    boolean grafikKontrolu = true;
    JTextField komutGirisAlani;

    private int teamNum = 50618;
    private int packageNumCount = 1;
    private double pressure = 93300;
    private double positivePressure = 110;
    private int initialHeight = 600;
    private double initialHeightDrawPoint = 457;
    private int negativeHeight = 10;
    private int speed = 10;
    private int stoppingCodon = 0;
    private double initialHeat = 22.0;
    private double positiveHeat = 0.05;
    private double latitude = 39.925533;
    private double longitude = 32.866287;
    private double positiveDonmeSayisi = 0.35;
    private int altitude = 850;
    static int pitch = 0;
    static int roll = 0;
    static int yaw = 0;
    private int donmeSayisi = 0;
    int startingXSicaklikHiz = 75;
    int startingXBasincYukseklik = 805;
    private String statusAscend = "ASCEND";
    private String statusStandby = "STANDBY";
    private String statusFreefall = "FREEFALL";
    private String statusDescent = "DESCENT";
    private String statusSlow = "SLOW";
    private String statusPost = "POST";
    private String realStatus = statusStandby;

    Timer timer = new Timer(1000, null);
    DecimalFormat df = new DecimalFormat("#.###");

    public AnaPanel() throws IOException {
        setLayout(null);
        setBackground(new java.awt.Color(136, 27, 8));
        initComponents();



        try{
            imageTharsis = ImageIO.read(new FileImageInputStream(new File("THARSIS-3A.png")));
        } catch (IOException ex) {
            Logger.getLogger(AnaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void paint(Graphics g) {
        super.paint(g);
        g.drawImage(imageTharsis, 1515, 3, 5*imageTharsis.getWidth()/18,(4*imageTharsis.getHeight())/13,this);
    }

    private void initComponents() {


        JTextField konsolField = new JTextField("                                                        Konsol");
        komutGirisAlani = new JTextField();
        scrollPane = new JScrollPane();
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        JButton baslat = new JButton("Başlat");
        JButton durdur = new JButton("Durdur");
        JButton ayrilmaKomutu = new JButton("Ayrılma Komutu");
        JButton komutGonder = new JButton("Komut Gönder");
        JButton baglantiSonlandir = new JButton("Bağlantı Sonlandır");
        JButton manuelTahrik = new JButton("Manuel Tahrik Komutu");
        JButton veriYukleme = new JButton("Veri Dosyasi Seç");
        JButton grafikler = new JButton("Grafikler");
        JButton ucDGoruntu = new JButton("3D Uydu");
        JLabel tharsis3a = new JLabel("THARSIS 3-A");
        JLabel uyeler = new JLabel("Üyelerimiz:");
        JLabel algi = new JLabel("Algı KÜÇÜKYAVUZ");
        JLabel nejla = new JLabel("Nejla CENAN");
        JLabel besir = new JLabel("Beşir KASSAB");
        JLabel nuri = new JLabel("Nuri ULUSOY");
        JLabel asrin = new JLabel("Asrın Baran ÇAVDAR");
        JLabel utku = new JLabel("Halit Utku MADEN");
        JLabel takimNo = new JLabel("50618");
        flightPanel = new Panel();


        xbeeBaglantiPaneli.setBounds(3,3,500,205);
        videoAktarimPaneli.setBounds(3,780,500,230);
        secilenTelemetriBilgiPaneli.setBounds(510,3,1000,205);
        tabbedPane.setBounds(510,212,1400,790);
        tharsis3a.setForeground(new java.awt.Color(255,255,255));
        tharsis3a.setBounds(70,212,100,25);
        tharsis3a.setFont(new java.awt.Font("Tahoma",Font.BOLD,14));
        takimNo.setForeground(new java.awt.Color(255,255,255));
        takimNo.setBounds(95,235,70,25);
        takimNo.setFont(new java.awt.Font("Tahoma",Font.BOLD,14));
        uyeler.setForeground(new java.awt.Color(255,255,255));
        uyeler.setBounds(15,262,70,25);
        uyeler.setFont(new java.awt.Font("Tahoma",Font.BOLD,13));
        algi.setForeground(new java.awt.Color(255,255,255));
        algi.setBounds(40,290,105,25);
        algi.setFont(new java.awt.Font("Tahoma",Font.BOLD,11));
        nejla.setForeground(new java.awt.Color(255,255,255));
        nejla.setBounds(40,318,70,25);
        nejla.setFont(new java.awt.Font("Tahoma",Font.BOLD,11));
        besir.setForeground(new java.awt.Color(255,255,255));
        besir.setBounds(40,346,90,25);
        besir.setFont(new java.awt.Font("Tahoma",Font.BOLD,11));
        nuri.setForeground(new java.awt.Color(255,255,255));
        nuri.setBounds(40,374,90,25);
        nuri.setFont(new java.awt.Font("Tahoma",Font.BOLD,11));
        asrin.setForeground(new java.awt.Color(255,255,255));
        asrin.setBounds(40,402,115,25);
        asrin.setFont(new java.awt.Font("Tahoma",Font.BOLD,11));
        utku.setForeground(new java.awt.Color(255,255,255));
        utku.setBounds(40,430,125,25);
        utku.setFont(new java.awt.Font("Tahoma",Font.BOLD,11));
        konsolField.setBounds(3,460,500,25);
        konsolField.setFont(new java.awt.Font("Tahoma", Font.BOLD,12));
        konsolField.setEditable(false);
        komutGirisAlani.setBounds(3,695,500,20);
        komutGirisAlani.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        konsolAkisArea.setBounds(3,490,500,200);
        konsolAkisArea.setEditable(false);
        scrollPane.setBounds(3,490,500,200);
        scrollPane.getViewport().setBackground(Color.WHITE);
        scrollPane.getViewport().add(konsolAkisArea);
        baslat.setBounds(3,720,65,25);
        baslat.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        durdur.setBounds(70,720,70,25);
        durdur.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        ayrilmaKomutu.setBounds(143,720,120,25);
        ayrilmaKomutu.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        komutGonder.setBounds(266,720,110,25);
        komutGonder.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        baglantiSonlandir.setBounds(379,720,121,25);
        baglantiSonlandir.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        manuelTahrik.setBounds(3,750,150,25);
        manuelTahrik.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        veriYukleme.setBounds(156,750,120,25);
        veriYukleme.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        grafikler.setBounds(280,750,74,25);
        grafikler.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));
        ucDGoruntu.setBounds(357,750,80,25);
        ucDGoruntu.setFont(new java.awt.Font("Tahoma",Font.PLAIN,11));

        add(xbeeBaglantiPaneli);
        add(videoAktarimPaneli);
        add(secilenTelemetriBilgiPaneli);
        add(tabbedPane);
        add(flightPanel);
        add(tharsis3a);
        add(uyeler);
        add(algi);
        add(nejla);
        add(besir);
        add(nuri);
        add(asrin);
        add(utku);
        add(takimNo);
        add(konsolField);
        add(komutGirisAlani);
        add(scrollPane);
        add(baslat);
        add(durdur);
        add(ayrilmaKomutu);
        add(komutGonder);
        add(baglantiSonlandir);
        add(manuelTahrik);
        add(veriYukleme);
        add(grafikler);
        add(ucDGoruntu);

        baslat.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                baslatActionPerformed(evt);
            }
        });

        durdur.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                durdurActionPerformed(evt);
            }
        });

        ayrilmaKomutu.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                ayrilmaKomutuActionPerformed(evt);
            }
        });

        grafikler.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent evt) {
                grafiklerActionPerformed(evt);
            }
        });

        ucDGoruntu.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent evt) {ucDGoruntuActionPerformed(evt);}
        });

        komutGonder.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent evt){ komutGonderActionPerformed(evt);}
        });

        veriYukleme.addActionListener(new java.awt.event.ActionListener(){
            public void actionPerformed(ActionEvent evt){ veriYuklemeActionPerformed(evt);}
        });

        tabbedPane.telemetriTable.telemetriEkrani.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                telemetriEkraniMouseClicked(evt);
            }
        });
    }

    private void baslatActionPerformed(java.awt.event.ActionEvent evt){
        if(grafikKontrolu) {
            DefaultTableModel model = (DefaultTableModel) tabbedPane.telemetriTable.telemetriEkrani.getModel();
            konsolAkisArea.setText(konsolAkisArea.getText() + "\nArayüz Başlatıldı.");
            grafikKontrolu = false;
            timer.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (initialHeight == 600) {
                        konsolAkisArea.setText(konsolAkisArea.getText() + "\nUydu İnişe Başladı.");

                        Object[] addRow = {teamNum, packageNumCount, realTime(), df.format(pressure), initialHeight, speed, df.format(initialHeat), teamNum,
                                df.format(latitude), df.format(longitude), altitude, realStatus , pitch, roll, yaw, donmeSayisi, "Hayır"};
                        model.addRow(addRow);


                        flightPanel.geciciSolHız.digitalLabel.setText(String.valueOf(speed));
                        flightPanel.geciciSagYukseklik.digitalLabel.setText(String.valueOf(initialHeight));

                        realStatus = statusFreefall;
                        flightPanel.ustBilgiPaneli.changeStatus(statusFreefall);
                        packageNumCount++;
                        pressure += positivePressure;
                        initialHeight -= negativeHeight;
                        initialHeat += positiveHeat;
                        donmeSayisi += positiveDonmeSayisi;


                        grafikFrameTest.grafikFramePanel.grafikCizimSicaklikPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,initialHeat);
                        grafikFrameTest.grafikFramePanel.grafikCizimBasincPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,pressure);
                        grafikFrameTest.grafikFramePanel.grafikCizimHizPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,speed);
                        grafikFrameTest.grafikFramePanel.grafikCizimYukseklikPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,initialHeightDrawPoint);

                        initialHeightDrawPoint += 1.6d;
                        if(startingXSicaklikHiz >= 512){

                        }else {
                            startingXSicaklikHiz += 3;
                        }
                        if(startingXBasincYukseklik >= 1270){

                        }else {
                            startingXBasincYukseklik += 3;
                        }

                    } else if (initialHeight <= 590 && initialHeight >= 410) {

                        Object[] addRow = {teamNum, packageNumCount, realTime(), df.format(pressure), initialHeight, speed, df.format(initialHeat), teamNum,
                                df.format(latitude), df.format(longitude), altitude, realStatus, pitch, roll, yaw, donmeSayisi, "Hayır"};
                        model.addRow(addRow);

                        flightPanel.geciciSolHız.digitalLabel.setText(String.valueOf(speed));
                        flightPanel.geciciSagYukseklik.digitalLabel.setText(String.valueOf(initialHeight));

                        packageNumCount++;
                        pressure += positivePressure;
                        initialHeight -= negativeHeight;
                        initialHeat += positiveHeat;
                        donmeSayisi += positiveDonmeSayisi;
                        roll += 5;
                        pitch += 5;

                        grafikFrameTest.grafikFramePanel.grafikCizimSicaklikPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,initialHeat);
                        grafikFrameTest.grafikFramePanel.grafikCizimBasincPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,pressure);
                        grafikFrameTest.grafikFramePanel.grafikCizimHizPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,speed);
                        grafikFrameTest.grafikFramePanel.grafikCizimYukseklikPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,initialHeightDrawPoint);

                        initialHeightDrawPoint += 1.6d;
                        if(startingXSicaklikHiz >= 512){

                        }else {
                            startingXSicaklikHiz += 3;
                        }
                        if(startingXBasincYukseklik >= 1270){

                        }else {
                            startingXBasincYukseklik += 3;
                        }

                    } else if (initialHeight >= 390 && initialHeight < 410) {
                        Object[] addRow = {teamNum, packageNumCount, realTime(), df.format(pressure), initialHeight, speed, df.format(initialHeat), teamNum,
                                df.format(latitude), df.format(longitude), altitude, realStatus, pitch, roll, yaw, donmeSayisi, "Hayır"};
                        model.addRow(addRow);

                        flightPanel.geciciSolHız.digitalLabel.setText(String.valueOf(speed));
                        flightPanel.geciciSagYukseklik.digitalLabel.setText(String.valueOf(initialHeight));

                        packageNumCount++;
                        pressure += positivePressure;
                        initialHeight -= negativeHeight;
                        initialHeat += positiveHeat;
                        roll += 5;
                        pitch += 5;

                        grafikFrameTest.grafikFramePanel.grafikCizimSicaklikPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,initialHeat);
                        grafikFrameTest.grafikFramePanel.grafikCizimBasincPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,pressure);
                        grafikFrameTest.grafikFramePanel.grafikCizimHizPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,speed);
                        grafikFrameTest.grafikFramePanel.grafikCizimYukseklikPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,initialHeightDrawPoint);

                        initialHeightDrawPoint += 1.6d;
                        if(startingXSicaklikHiz >= 512){

                        }else {
                            startingXSicaklikHiz += 3;
                        }
                        if(startingXBasincYukseklik >= 1270){

                        }else {
                            startingXBasincYukseklik += 3;
                        }

                    } else if (initialHeight < 390 && initialHeight > 0) {
                        realStatus = statusDescent;
                        flightPanel.ustBilgiPaneli.changeStatus(statusDescent);

                        Object[] addRow = {teamNum, packageNumCount, realTime(), df.format(pressure), initialHeight, speed, df.format(initialHeat), teamNum,
                                df.format(latitude), df.format(longitude), altitude, realStatus, pitch, roll, yaw, donmeSayisi, "Hayır"};
                        model.addRow(addRow);

                        flightPanel.geciciSolHız.digitalLabel.setText(String.valueOf(speed));
                        flightPanel.geciciSagYukseklik.digitalLabel.setText(String.valueOf(initialHeight));

                        packageNumCount++;
                        pressure += positivePressure;
                        initialHeight -= negativeHeight;
                        initialHeat += positiveHeat;
                        donmeSayisi += positiveDonmeSayisi;
                        roll -= 5;
                        pitch -= 5;


                        grafikFrameTest.grafikFramePanel.grafikCizimSicaklikPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,initialHeat);
                        grafikFrameTest.grafikFramePanel.grafikCizimBasincPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,pressure);
                        grafikFrameTest.grafikFramePanel.grafikCizimHizPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,speed);
                        grafikFrameTest.grafikFramePanel.grafikCizimYukseklikPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,initialHeightDrawPoint);

                        initialHeightDrawPoint += 1.6d;
                        if(startingXSicaklikHiz >= 512){

                        }else {
                            startingXSicaklikHiz += 3;
                        }
                        if(startingXBasincYukseklik >= 1270){

                        }else {
                            startingXBasincYukseklik += 3;
                        }

                    } else if (initialHeight == 0) {
                        speed = 0;
                        donmeSayisi = 0;
                        realStatus = statusPost;
                        flightPanel.ustBilgiPaneli.changeStatus(statusPost);

                        Object[] addRow = {teamNum, packageNumCount, realTime(), df.format(pressure), initialHeight, speed, df.format(initialHeat), teamNum,
                                df.format(latitude), df.format(longitude), altitude, realStatus, pitch, roll, yaw, donmeSayisi, "Hayır"};
                        model.addRow(addRow);

                        flightPanel.geciciSolHız.digitalLabel.setText(String.valueOf(speed));
                        flightPanel.geciciSagYukseklik.digitalLabel.setText(String.valueOf(initialHeight));

                        stoppingCodon++;
                        packageNumCount++;

                        grafikFrameTest.grafikFramePanel.grafikCizimSicaklikPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,initialHeat);
                        grafikFrameTest.grafikFramePanel.grafikCizimBasincPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,pressure);
                        grafikFrameTest.grafikFramePanel.grafikCizimHizPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,speed);
                        grafikFrameTest.grafikFramePanel.grafikCizimYukseklikPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,initialHeightDrawPoint);

                        if(startingXSicaklikHiz >= 510){

                        }else {
                            startingXSicaklikHiz += 3;
                        }
                        if(startingXBasincYukseklik >= 1270){

                        }else {
                            startingXBasincYukseklik += 3;
                        }
                        /*if (stoppingCodon == 60) {
                            timer.stop();
                            konsolAkisArea.setText(konsolAkisArea.getText() + "\nGörev Sonlandı.");
                        } else {
                            speed = 0;
                            donmeSayisi = 0;
                            realStatus = statusPost;
                            flightPanel.ustBilgiPaneli.changeStatus(statusPost);

                            Object[] addRow = {teamNum, packageNumCount, realTime(), df.format(pressure), initialHeight, speed, df.format(initialHeat), teamNum,
                                    df.format(latitude), df.format(longitude), altitude, realStatus, pitch, roll, yaw, donmeSayisi, "Hayır"};
                            model.addRow(addRow);

                            flightPanel.geciciSolHız.digitalLabel.setText(String.valueOf(speed));
                            flightPanel.geciciSagYukseklik.digitalLabel.setText(String.valueOf(initialHeight));

                            stoppingCodon++;
                            packageNumCount++;

                            grafikFrameTest.grafikFramePanel.grafikCizimSicaklikPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,initialHeat);
                            grafikFrameTest.grafikFramePanel.grafikCizimBasincPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,pressure);
                            grafikFrameTest.grafikFramePanel.grafikCizimHizPrototip(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,speed);
                            grafikFrameTest.grafikFramePanel.grafikCizimYukseklikPrototip(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,initialHeightDrawPoint);

                            startingXSicaklikHiz += 3;
                            startingXBasincYukseklik += 3;
                        }*/

                    }
                }
            });
            timer.start();
        }
    }

    private void durdurActionPerformed(java.awt.event.ActionEvent evt) {
        konsolAkisArea.setText(konsolAkisArea.getText() + "\nArayüz Durduruldu.");
        timer.stop();
    }
    private void ayrilmaKomutuActionPerformed(java.awt.event.ActionEvent evt){
        komutGirisAlani.setText("nichrome 1");
        doCommand();
    }

    private void grafiklerActionPerformed(java.awt.event.ActionEvent evt){

        grafikFrameTest.setVisible(true);

    }

    private void ucDGoruntuActionPerformed(java.awt.event.ActionEvent evt){

        ucDFrameTest.setVisible(true);

    }


    private void veriYuklemeActionPerformed(java.awt.event.ActionEvent evt) {
        JFileChooser veriDosyasi = new JFileChooser();
        veriDosyasi.showOpenDialog(null);
        veriDosyasi.getSelectedFile().getAbsolutePath();
        konsolAkisArea.setText(konsolAkisArea.getText() + "\n>>'" + veriDosyasi.getSelectedFile().getAbsolutePath() + "' \nDizinindeki Dosya Seçildi.");

    }

    private void telemetriEkraniMouseClicked(java.awt.event.MouseEvent evt){
        DefaultTableModel model = (DefaultTableModel) tabbedPane.telemetriTable.telemetriEkrani.getModel();
        int selectedRow = tabbedPane.telemetriTable.telemetriEkrani.getSelectedRow();

        secilenTelemetriBilgiPaneli.teamNoTextF.setText(model.getValueAt(selectedRow,0).toString());
        secilenTelemetriBilgiPaneli.packageNoTextF.setText(model.getValueAt(selectedRow,1).toString());
        secilenTelemetriBilgiPaneli.shipTimeTextF.setText(model.getValueAt(selectedRow,2).toString());
        secilenTelemetriBilgiPaneli.pressureTextF.setText(model.getValueAt(selectedRow,3).toString());
        secilenTelemetriBilgiPaneli.heightTextF.setText(model.getValueAt(selectedRow,4).toString());
        secilenTelemetriBilgiPaneli.speedTextF.setText(model.getValueAt(selectedRow,5).toString());
        secilenTelemetriBilgiPaneli.gpsLatitudeTextF.setText(model.getValueAt(selectedRow,8).toString());
        secilenTelemetriBilgiPaneli.pitchTextF.setText(model.getValueAt(selectedRow,12).toString());
        secilenTelemetriBilgiPaneli.statueTextF.setText(model.getValueAt(selectedRow,11).toString());
        secilenTelemetriBilgiPaneli.heatTextF.setText(model.getValueAt(selectedRow,6).toString());
        secilenTelemetriBilgiPaneli.voltageTextF.setText(model.getValueAt(selectedRow,7).toString());
        secilenTelemetriBilgiPaneli.gpsAltitudeTextF.setText(model.getValueAt(selectedRow,10).toString());
        secilenTelemetriBilgiPaneli.gpsLongitudeTextF.setText(model.getValueAt(selectedRow,9).toString());
        secilenTelemetriBilgiPaneli.yawTextF.setText(model.getValueAt(selectedRow,14).toString());
        secilenTelemetriBilgiPaneli.rollTextF.setText(model.getValueAt(selectedRow,13).toString());
        secilenTelemetriBilgiPaneli.turnsNoTextF.setText(model.getValueAt(selectedRow,15).toString());
        secilenTelemetriBilgiPaneli.videoTransmissionTextF.setText(model.getValueAt(selectedRow,16).toString());
    }

    private void komutGonderActionPerformed(java.awt.event.ActionEvent evt){
        if (komutGirisAlani.getText().equals("")){
            konsolAkisArea.setText(konsolAkisArea.getText() + "\nBoş Komut Gönderdiniz. Lütfen Geçerli Bir Komut Gönderiniz.");
        }
        else{
            doCommand();
        }
    }

    @Override
    public void telemetryReceived(TelemetryPacket telemetryPacket) {
        DefaultTableModel model = (DefaultTableModel) tabbedPane.telemetriTable.telemetriEkrani.getModel();

        Object[] addRow = {telemetryPacket.teamId, telemetryPacket.packetNumber, telemetryPacket.captureTime, df.format(telemetryPacket.pressure), telemetryPacket.altitude, telemetryPacket.verticalSpeed,
                df.format(telemetryPacket.temperature), telemetryPacket.voltage, df.format(telemetryPacket.latitude), df.format(telemetryPacket.longitude), df.format(telemetryPacket.gpsAltitude),
                telemetryPacket.status, telemetryPacket.pitch, telemetryPacket.roll, telemetryPacket.yaw, telemetryPacket.spin, telemetryPacket.videoState};
        model.addRow(addRow);

        flightPanel.ustBilgiPaneli.changeStatus(telemetryPacket.status);

        pitch = (int)telemetryPacket.pitch;
        roll = (int)telemetryPacket.roll;

        flightPanel.geciciSolHız.digitalLabel.setText(String.valueOf(telemetryPacket.verticalSpeed));
        flightPanel.geciciSagYukseklik.digitalLabel.setText(String.valueOf(telemetryPacket.altitude));

        grafikFrameTest.grafikFramePanel.grafikCizimSicaklik(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,telemetryPacket.temperature);
        grafikFrameTest.grafikFramePanel.grafikCizimYukseklik(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,telemetryPacket.altitude);
        grafikFrameTest.grafikFramePanel.grafikCizimBasinc(grafikFrameTest.grafikFramePanel,startingXBasincYukseklik,telemetryPacket.pressure);
        grafikFrameTest.grafikFramePanel.grafikCizimHiz(grafikFrameTest.grafikFramePanel,startingXSicaklikHiz,telemetryPacket.verticalSpeed);
        if(startingXSicaklikHiz < 510){
            startingXSicaklikHiz += 3;
        }
        if(startingXBasincYukseklik < 1240){
            startingXBasincYukseklik += 3;
        }

        csv.CreateCSVFile(telemetryPacket.teamId, telemetryPacket.packetNumber, telemetryPacket.captureTime.toString(), df.format(telemetryPacket.pressure), (int)telemetryPacket.altitude, (int)telemetryPacket.verticalSpeed,
                df.format(telemetryPacket.temperature), (int)telemetryPacket.voltage, telemetryPacket.latitude, telemetryPacket.longitude, telemetryPacket.gpsAltitude,
                telemetryPacket.status, telemetryPacket.pitch, telemetryPacket.roll, telemetryPacket.yaw, telemetryPacket.spin, telemetryPacket.videoState);
    }

    @Override
    public void messageReceived(Severity severity, String s) {
        konsolAkisArea.setText(konsolAkisArea.getText() +"\n"+ s);
    }

    public static void htp_thread(){
        Havi.init();

        for(;;){
            Havi.work();
        }
    }

    void doCommand()
    {
        String command = komutGirisAlani.getText();
        Havi.command(command);
        komutGirisAlani.setText("");
        konsolAkisArea.setText(konsolAkisArea.getText()+"\n>>"+command);
    }

    public String realTime() {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy, HH/mm/ss");
        LocalDateTime now = LocalDateTime.now();
        return dtf.format(now);
    }

    public static int getRoll() {
        return roll;
    }

    public static int getPitch() {
        return pitch;
    }
}



class GoruntuPaneli extends JPanel {
    BufferedImage imageFlightPanel;

    public GoruntuPaneli() {
        setLayout(null);
        try{
            imageFlightPanel = ImageIO.read(new FileImageInputStream(new File("flightpanel.jpeg")));
        }catch (IOException ex) {
            Logger.getLogger(AnaPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
        setBackground(new java.awt.Color(255,255,255));
        setBounds(50,55,150,145);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();

        g2d.setColor(Color.BLACK);
        int x = (getWidth() - imageFlightPanel.getWidth()) / 2;
        int y = (getHeight() - imageFlightPanel.getHeight()) / 2;
        AffineTransform at = new AffineTransform();
        for (int i = 0; i < 360;i++) {
            at.setToRotation(-(Math.toRadians(AnaPanel.getRoll())), x + (imageFlightPanel.getWidth() / 2), y + (imageFlightPanel.getHeight() / 2));


        }

        at.translate(x, y);
        g2d.setTransform(at);
        g2d.drawImage(imageFlightPanel, 0, AnaPanel.getPitch(), this);


        g2d.dispose();
        repaint();
    }


}