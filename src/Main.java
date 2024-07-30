import java.io.IOException;

public class Main {

    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ArgumentChecker.check(args);

        HuffmanService huffmanService = new HuffmanService();

        if (args[1].equals("-e")) {
            huffmanService.encode(args[0], args[2]);
        }
        if (args[1].equals("-d")) {
            huffmanService.decode(args[0], args[2]);
        }

//        FileUtils.areTheSameFiles("files/file.txt", "files/file-decoded.txt");
    }
}