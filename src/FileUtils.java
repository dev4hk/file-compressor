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
        OutputStream os = new FileOutputStream(destinationFilePath);
        ObjectOutputStream oos = new ObjectOutputStream(os);
        oos.writeObject(bitSet);
        oos.writeObject(fileHeader);
        oos.close();
        os.close();
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

    public void printFileSize(String filePath, FileType fileType) throws IOException {

        long bytes = Files.size(Path.of(filePath));

        System.out.println("========================================");
        System.out.println(fileType.getFileType());
        System.out.println("Path: " + filePath);
        System.out.printf("Size: %,d bytes%n", bytes);
        System.out.println("========================================");
    }

    public static void areTheSameFiles(String filePath1, String filePath2) throws IOException {

        long misMatch = Files.mismatch(Path.of(filePath1), Path.of(filePath2));

        if (misMatch == -1) {
            System.out.println("========================================");
            System.out.println("Comparing Two Files: ");
            System.out.println("File 1: " + filePath1);
            System.out.println("File 2: " + filePath2);
            System.out.println("Result: No Mismatch Found");
            System.out.println("========================================");
        } else {
            System.out.println("========================================");
            System.out.println("Comparing Two Files: ");
            System.out.println("File 1: " + filePath1);
            System.out.println("File 2: " + filePath2);
            System.out.println("Result: Mismatch Found");
            System.out.println("========================================");
        }

    }

    public void getCompressionRate(String originalFilePath, String encodedFilePath) throws IOException {
        long bytesOriginal = Files.size(Path.of(originalFilePath));
        long bytesEncoded = Files.size(Path.of(encodedFilePath));
        double compressionRate = ((bytesOriginal - bytesEncoded) / (double) bytesOriginal) * 100;
        System.out.println("========================================");
        System.out.println("Compression Rate: " + String.format("%.2f", compressionRate) + "% reduced");
        System.out.println("========================================");
    }
}
