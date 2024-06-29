public class Main {
    public static void main(String[] args) {

        String originalString = "ABBCCCDDDD";
        String encodedString = HuffmanZip.encode(originalString);
        String decodedString = HuffmanZip.decode(encodedString);
        System.out.println(encodedString);
        System.out.println(decodedString);

    }
}