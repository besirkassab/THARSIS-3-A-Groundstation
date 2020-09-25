import javax.swing.*;

public class TabbedPane extends JTabbedPane {
    //GrafikClass grafikPaneli = new GrafikClass();
    TelemetriClass telemetriTable = new TelemetriClass();
    KameraClass kameraPaneli = new KameraClass();
    HaritaClass haritaPaneli = new HaritaClass();
    //UcBoyutClass ucBoyutPaneli = new UcBoyutClass();

    public TabbedPane(){
        setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(220, 239, 238), 2));



        //addTab("Grafikler",grafikPaneli);
        addTab("Telemetri",telemetriTable);
        addTab("Kamera",kameraPaneli);
        addTab("Harita",haritaPaneli);
        //addTab("3D Görüntü",ucBoyutPaneli);
    }
}
