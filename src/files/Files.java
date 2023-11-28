package files;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Files {
    public static String createFile(String directoryPath, String data) {
        Date currentDate = new Date();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss");
        String formattedDateTime = dateFormat.format(currentDate);
        String filename = directoryPath + "file_" + formattedDateTime + ".txt";
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

    public static ArrayList<String> getStringNamesRecordFiles(String directoryPath) {
        ArrayList<String> stringNames = new ArrayList<>();
        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    stringNames.add(file.getName());
                }
            }
        }
        return stringNames;
    }

    public static boolean deleteFile(String filename) {
        try {
            File fileToDelete = new File(filename);
            if (fileToDelete.delete()) {
                return true;
            }
        } catch (Exception e) { }
        return false;
    }

    public static boolean deleteAllFiles(String directoryPath) {
        ArrayList<String> stringNamesRecordFiles = getStringNamesRecordFiles(directoryPath);
        int countFiles = stringNamesRecordFiles.size();
        for (String name : stringNamesRecordFiles) {
            deleteFile(directoryPath + name);
            countFiles--;
        }
        return countFiles == 0;
    }
}
