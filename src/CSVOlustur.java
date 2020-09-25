import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class CSVOlustur {
    boolean csvFile = false;
    FileWriter fw = new FileWriter("TelemetriVerileri.csv");
    PrintWriter output = new PrintWriter(fw);

    public CSVOlustur() throws IOException{}

    public void CreateCSVFile(int teamNum, int packageNumCount, String realTime, String pressure, int initialHeight, int speed, String initialHeat, int batteryVoltage, float latitude, float longitude
            , float altitude, String realStatus, double pitch, double roll, double yaw, int donmeSayisi, String videoTransfer) {
        if (!csvFile) {
            csvFile = true;

            output.println("Takim No" + ";" + "Paket Numarasi" + ";" + "Gonderme Saati" + ";" + "Basinc" + ";" + "Yukseklik" + ";" + "Inis Hizi" + ";" + "Sicaklik" + ";" + "Pil Gerilimi" + ";"
                    + "GPS Latitude" + ";" + "GPS Longitude" + ";" + "GPS Altitude" + ";" + "Uydu Statusu" + ";"
                    + "Pitch" + ";" + "Roll" + ";" + "Yaw" + ";" + "Donus Sayisi" + ";" + "Video Aktarim Bilgisi");
            output.println(teamNum + ";" + packageNumCount + ";" + realTime + ";" + pressure + ";" + initialHeight + ";" + speed + ";" + initialHeat + ";" + batteryVoltage + ";" + latitude + ";" + longitude + ";"
                    + altitude + ";" + realStatus + ";" + pitch + ";" + roll + ";" + yaw + ";" + donmeSayisi + ";" + videoTransfer);

        } else {
            output.println(teamNum + ";" + packageNumCount + ";" + realTime + ";" + pressure + ";" + initialHeight + ";" + speed + ";" + initialHeat + ";" + batteryVoltage + ";" + latitude + ";" + longitude + ";"
                    + altitude + ";" + realStatus + ";" + pitch + ";" + roll + ";" + yaw + ";" + donmeSayisi + ";" + videoTransfer);
        }
    }
}
