import java.io.Serializable;

public class HuffmanNode implements Serializable {
    private byte bt;
    private int freq;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(byte bt, int freq, HuffmanNode left, HuffmanNode right) {
        this.bt = bt;
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode(int freq, HuffmanNode left, HuffmanNode right) {
        this.freq = freq;
        this.left = left;
        this.right = right;
    }

    public HuffmanNode() {
    }

    public byte getBt() {
        return bt;
    }

    public void setBt(byte bt) {
        this.bt = bt;
    }

    public int getFreq() {
        return freq;
    }

    public void setFreq(int freq) {
        this.freq = freq;
    }

    public HuffmanNode getLeft() {
        return left;
    }

    public void setLeft(HuffmanNode left) {
        this.left = left;
    }

    public HuffmanNode getRight() {
        return right;
    }

    public void setRight(HuffmanNode right) {
        this.right = right;
    }
}
