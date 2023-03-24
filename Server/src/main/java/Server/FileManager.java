package Server;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    private File selectedFile;
    private FileWriter writer;
    private Scanner reader;

    public FileManager() {
    }

    public void open(String name) {
        selectedFile = new File("files/"+name);

        if (!selectedFile.exists() && !createFile()) {
            System.err.println("There was a problem: that file does not exists and cannot be created");
        }

        try {
            writer = new FileWriter(selectedFile);
            reader = new Scanner(selectedFile);
        } catch (IOException e) {
            System.err.println("There was a problem: cannot read or write to that file");
        }

    }

    public void write(String data) {
        try {
            writer.write(data);
            writer.close();
        } catch (IOException e) {
            System.err.println("Could not write to " + selectedFile.getPath());
        } catch (NullPointerException e) {
            System.err.println("Tried to write before opening a file");
        }
    }

    private Boolean createFile() {
        try {
            return selectedFile.createNewFile();
        } catch (IOException e) {
            return false;
        }
    }

    public static void main(String[] args) {
        FileManager fm = new FileManager();
        fm.open("test.txt");
        fm.write("Hello, again, world!");
    }
}
