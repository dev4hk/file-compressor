import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        HuffmanService huffmanService = new HuffmanService();
        huffmanService.encode("files/test.txt", "files/test-encoded.txt");
        huffmanService.decode("files/test-encoded.txt", "files/test-decoded.txt");
    }
}