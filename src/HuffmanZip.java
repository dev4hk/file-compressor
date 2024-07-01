import java.io.IOException;
import java.util.*;

public class HuffmanZip {

    public static Map<Character, Integer> freqMap;
    public static Queue<HuffmanNode> minHeap;
    public static HuffmanNode root;
    public static Map<Character, String> encodeMap;
    public static String originalString;
    public static String encodedString;
    public static String decodedString;
    public static BitSet bitSet;
    public static int len;

    public static void encode() throws IOException {
        originalString = FileUtils.readFileToEncode();
        createFreqMap();
        createMinHeap();
        createTree();
        createEncodeMap();
        createEncodedString();
        createBitSet();
        writeEncodedToFile();
    }

    public static void decode() throws IOException, ClassNotFoundException {
        List<Object> list = FileUtils.readFileToDecode();
        BitSet set = (BitSet) list.get(0);
        FileHeader fileHeader = (FileHeader) list.get(1);
        createDecodedString(set, fileHeader);
        FileUtils.writeDecodedToFile();
    }

    private static void createDecodedString(BitSet set, FileHeader fileHeader) {
        HuffmanNode curr = fileHeader.getRoot();
        int len = fileHeader.getOriginalStringLength();
        StringBuilder sb = new StringBuilder();
        int currIdx = 0;
        while(currIdx < len) {
            while(!isLeaf(curr)) {
                if(set.get(currIdx)) {
                    curr = curr.getRight();
                }
                else {
                    curr = curr.getLeft();
                }
                currIdx++;
            }
            sb.append(curr.getCh());
            curr = fileHeader.getRoot();
        }
        decodedString = sb.toString();
    }

    private static void createEncodedString() {
        encodedString = null;
        StringBuilder sb = new StringBuilder();
        for(char ch : originalString.toCharArray()) {
            sb.append(encodeMap.get(ch));
        }
        encodedString = sb.toString();
        len = encodedString.length();
    }

    private static void createBitSet() {
        bitSet = new BitSet(len);
        for(int i = 0; i < encodedString.length(); i++) {
            if(encodedString.charAt(i) == '1') {
                bitSet.set(i);
            }
        }
    }

    private static void createEncodeMap() {
        HuffmanNode curr = root;
        StringBuilder sb = new StringBuilder();
        encodeMap = new HashMap<>();
        inOrderTraverse(curr, sb);
    }

    private static void inOrderTraverse(HuffmanNode curr, StringBuilder sb) {
        if (isLeaf(curr)) {
            encodeMap.put(curr.getCh(), sb.toString());
            return;
        }
        inOrderTraverse(curr.getLeft(), sb.append('0'));
        sb.deleteCharAt(sb.length() - 1);
        inOrderTraverse(curr.getRight(), sb.append('1'));
        sb.deleteCharAt(sb.length() - 1);
    }


    private static void createTree() {
        root = null;
        while (minHeap.size() > 1) {
            HuffmanNode node1 = minHeap.poll();
            HuffmanNode node2 = minHeap.poll();
            HuffmanNode newNode = new HuffmanNode();
            newNode.setFreq(node1.getFreq() + node2.getFreq());
            newNode.setLeft(node1);
            newNode.setRight(node2);
            minHeap.offer(newNode);
        }
        root = minHeap.poll();
    }

    private static void createMinHeap() {
        minHeap = new PriorityQueue<>(new Comparator<HuffmanNode>() {
            @Override
            public int compare(HuffmanNode o1, HuffmanNode o2) {
                if (o1.getFreq() != o2.getFreq()) {
                    return Integer.compare(o1.getFreq(), o2.getFreq());
                }
                return Character.compare(o1.getCh(), o2.getCh());
            }
        });
        for (char ch : freqMap.keySet()) {
            HuffmanNode node = new HuffmanNode(ch, freqMap.get(ch), null, null);
            minHeap.offer(node);
        }
    }

    private static void createFreqMap() {
        freqMap = new HashMap<>();
        for (char ch : originalString.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
    }

    private static boolean isLeaf(HuffmanNode node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    private static void writeEncodedToFile() throws IOException {
        FileHeader fileHeader = new FileHeader(len, root);
        FileUtils.writeEncodedToFile(fileHeader);
    }

}
