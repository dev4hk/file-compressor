import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;

public class FileUtils {

    public String readFileToEncode(String sourceFilePath) throws IOException {
        return Files.readString(Path.of(sourceFilePath));
    }

    public void writeEncodedToFile(BitSet bitSet, FileHeader fileHeader, String destinationFilePath) throws IOException {
        OutputStream outputStream = new FileOutputStream(destinationFilePath);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(bitSet);
        objectOutputStream.writeObject(fileHeader);
        objectOutputStream.close();
        outputStream.close();
    }

    public List<Object> readFileToDecode(String sourceFilePath) throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream(sourceFilePath);
        ObjectInputStream ois = new ObjectInputStream(fis);
        Object bitSet = ois.readObject();
        Object fileHeader = ois.readObject();
        List<Object> list = new ArrayList<>();
        list.add(bitSet);
        list.add(fileHeader);
        fis.close();
        ois.close();
        return list;
    }

    public void writeDecodedToFile(String destinationFilePath, String decodedString) throws IOException {
        Files.writeString(Path.of(destinationFilePath), decodedString);
    }

    public void printFileSize(String filePath) throws IOException {
        long bytes = Files.size(Path.of(filePath));
        System.out.println("Path: " + filePath);
        System.out.printf("Size: %,d bytes%n", bytes);
    }

    public void areTheSameFiles(String filePath1, String filePath2) {

    }
}
