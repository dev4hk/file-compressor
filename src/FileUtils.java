import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FileUtils {

    public static String readFileToEncode() throws IOException {
        return Files.readString(Path.of("files/test.txt"));
    }

    public static void writeEncodedToFile(FileHeader fileHeader) throws IOException {
        OutputStream outputStream = new FileOutputStream("files/test-encoded.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(HuffmanZip.bitSet);
        objectOutputStream.writeObject(fileHeader);
        objectOutputStream.close();
        outputStream.close();
    }

    public static List<Object> readFileToDecode() throws IOException, ClassNotFoundException {
        FileInputStream fis = new FileInputStream("files/test-encoded.txt");
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

    public static void writeDecodedToFile() throws IOException {
        Files.writeString(Path.of("files/test-decoded.txt"), HuffmanZip.decodedString);
        byte[] file1 = Files.readAllBytes(Path.of("files/test.txt"));
        byte[] file2 = Files.readAllBytes(Path.of("files/test-decoded.txt"));
        System.out.println(Arrays.equals(file1, file2));
    }
}
