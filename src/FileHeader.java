import java.io.Serializable;
import java.util.Objects;

public class FileHeader implements Serializable {
    private int encodedStringLength;
    private HuffmanNode root;

    public FileHeader(int encodedStringLength, HuffmanNode root) {
        this.encodedStringLength = encodedStringLength;
        this.root = root;
    }

    public int getEncodedStringLength() {
        return encodedStringLength;
    }

    public void setEncodedStringLength(int encodedStringLength) {
        this.encodedStringLength = encodedStringLength;
    }

    public HuffmanNode getRoot() {
        return root;
    }

    public void setRoot(HuffmanNode root) {
        this.root = root;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FileHeader that = (FileHeader) o;
        return encodedStringLength == that.encodedStringLength && Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(encodedStringLength, root);
    }
}
