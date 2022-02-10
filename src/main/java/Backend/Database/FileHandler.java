package Backend.Database;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileHandler {
    private static FileHandler fileHandler;
    private File filepath;
    private String[] output;

    private FileHandler() throws IOException {
        filepath = new File("export.txt");
//        List<String> lines = Files.readAllLines(Paths.get(String.valueOf(filepath)));
//        output = new String[lines.size()];
//        for (int i = 0; i < lines.size(); i++) {
//            output[i] = lines.get(i);
//        }
    }

    public static FileHandler getInstance() throws IOException {
        if (fileHandler == null) {
            fileHandler = new FileHandler();

        }
        return fileHandler;
    }

    public FileHandler setFilepath(File file) {
            filepath = file;
        return this;
    }

    private void saveFile() {
        String input = "";
        for (String line : output) {
            input += line + "\n";
        }
        try {
            FileWriter fileWriter = new FileWriter((filepath));
            fileWriter.write(input);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save(String[] input) {
        output = input;
        saveFile();
    }

    public void saveString(String input){
        try {
            FileWriter fileWriter = new FileWriter((String.valueOf(filepath)));
            fileWriter.write(input);
            fileWriter.flush();
            fileWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String[] read() {
        return output;
    }
}
