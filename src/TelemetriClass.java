import javax.swing.*;

public class TelemetriClass extends JScrollPane {
    JTable telemetriEkrani = new JTable();

    public TelemetriClass(){

        telemetriEkrani.setModel(new javax.swing.table.DefaultTableModel(
                        new Object [][] {

                        },
                        new String [] {
                                "Takım No", "Paket Numarasi", "Gönderme Saati", "Basınç", "Yükseklik", "İniş Hızı", "Sıcaklık", "Pil Gerilimi", "GPS Latitude", "GPS Longitude", "GPS Altitude", "Uydu Statüsü", "Pitch", "Roll", "Yaw", "Dönüş Sayısı", "Video Aktarımı"
                        }
                ) {
                    boolean[] canEdit = new boolean [] {
                            false, true, true, true, true, true, true, true, true, true, true, true, false, true, true, true, false
                    };

                    public boolean isCellEditable(int rowIndex, int columnIndex) {
                        return canEdit [columnIndex];
                    }
                });
        setViewportView(telemetriEkrani);
        if (telemetriEkrani.getColumnModel().getColumnCount() > 0) {
            telemetriEkrani.getColumnModel().getColumn(0).setResizable(false);
        }
    }
}
