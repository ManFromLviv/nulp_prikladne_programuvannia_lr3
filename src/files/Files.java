package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Files {
    public static String createFile(String data) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = dateFormat.format(currentDate);
        String filename = "src/history/file_" + formattedDateTime + ".txt";
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename));
            writer.write(data);
            writer.close();
        } catch (Exception e) {
            System.out.println("Помилка запису файлу! " + e.toString());
        }
        return filename;
    }

    public static String readFile(String filename) {
        StringBuilder data = new StringBuilder();
        try {
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            line = reader.readLine();
            while (true) {
                data.append(line);
                if ((line = reader.readLine()) != null) {
                    data.append("\n");
                } else {
                    break;
                }
            }
            reader.close();
        } catch (Exception e) {
            System.out.println("Помилка зчитування файлу! " + e.toString());
        }
        return data.toString();
    }
}
