import java.io.Serializable;

public class HuffmanNode implements Serializable {
    private char ch;
    private int freq;
    private HuffmanNode left;
    private HuffmanNode right;

    public HuffmanNode(char ch, int freq, HuffmanNode left, HuffmanNode right) {
        this.ch = ch;
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

    public char getCh() {
        return ch;
    }

    public void setCh(char ch) {
        this.ch = ch;
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
