import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {

    public static byte[] readFileToEncode() throws IOException {
        FileInputStream fis = new FileInputStream("files/test.txt");
        byte[] bytes = new byte[fis.available()];
        fis.read(bytes);
        fis.close();
        return bytes;
    }

    public static void writeCompressedBytesToFile() throws IOException {
        OutputStream outputStream = new FileOutputStream("files/test-encoded.txt");
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
        objectOutputStream.writeObject(HuffmanZip.encodedBytes);
        objectOutputStream.writeObject(HuffmanZip.root);
        objectOutputStream.close();
        outputStream.close();
    }

//    public static List<Object> readFileToDecode() throws IOException, ClassNotFoundException {
//        FileInputStream fis = new FileInputStream("files/test-encoded.txt");
//        ObjectInputStream ois = new ObjectInputStream(fis);
//        Object encodedBytes = ois.readObject();
//        Object root = ois.readObject();
//        List<Object> list = new ArrayList<>();
//        list.add(encodedBytes);
//        list.add(root);
//        fis.close();
//        ois.close();
//        return list;
//    }
}
