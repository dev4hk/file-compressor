import java.io.Serializable;
import java.util.Objects;

public class FileHeader implements Serializable {
    private int originalStringLength;
    private HuffmanNode root;

    public FileHeader(int originalStringLength, HuffmanNode root) {
        this.originalStringLength = originalStringLength;
        this.root = root;
    }

    public FileHeader() {
    }

    public int getOriginalStringLength() {
        return originalStringLength;
    }

    public void setOriginalStringLength(int originalStringLength) {
        this.originalStringLength = originalStringLength;
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
        return originalStringLength == that.originalStringLength && Objects.equals(root, that.root);
    }

    @Override
    public int hashCode() {
        return Objects.hash(originalStringLength, root);
    }
}
