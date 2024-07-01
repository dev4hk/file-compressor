import java.io.IOException;
import java.util.*;

public class HuffmanZip {

    public static Map<Byte, Integer> freqMap;
    public static Queue<HuffmanNode> minHeap;
    public static HuffmanNode root;
    public static Map<Byte, String> encodeMap;
    public static byte[] originalBytes;
    public static byte[] encodedBytes;

    public static void encode() throws IOException {
        originalBytes = FileUtils.readFileToEncode();
        createFreqMap();
        createMinHeap();
        createTree();
        createCodeMap();
        createEncodedBytes();
        FileUtils.writeCompressedBytesToFile();
    }

//    public static void decode() throws IOException, ClassNotFoundException {
//        List<Object> list = FileUtils.readFileToDecode();
//        byte[] encodedBytes = (byte[]) list.get(0);
//        HuffmanNode root = (HuffmanNode) list.get(1);
//        byte[] decodedBytes = traverseTreeToDecode(encodedBytes, root);
//    }

//    private static byte[] traverseTreeToDecode(byte[] encodedBytes, HuffmanNode root) {
//        HuffmanNode curr = root;
//        return null;
//    }

    private static void createCodeMap() {
        HuffmanNode curr = root;
        StringBuilder sb = new StringBuilder();
        encodeMap = new HashMap<>();
        inOrderTraverse(curr, sb);
    }

    private static void inOrderTraverse(HuffmanNode curr, StringBuilder sb) {
        if (isLeaf(curr)) {
            encodeMap.put(curr.getBt(), sb.toString());
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
                return Byte.compare(o1.getBt(), o2.getBt());
            }
        });
        for (byte bt : freqMap.keySet()) {
            HuffmanNode node = new HuffmanNode(bt, freqMap.get(bt), null, null);
            minHeap.offer(node);
        }
    }

    private static void createFreqMap() {
        freqMap = new HashMap<>();
        for (byte bt : originalBytes) {
            freqMap.put(bt, freqMap.getOrDefault(bt, 0) + 1);
        }
    }

    private static boolean isLeaf(HuffmanNode node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    private static void createEncodedBytes() {
        encodedBytes = null;
        StringBuilder sb = new StringBuilder();
        for(byte bt : originalBytes) {
            sb.append(encodeMap.get(bt));
        }
        int byteArrLen = (sb.length() + 7) / 8;
        byte[] bytes = new byte[byteArrLen];
        int currentIdx = 0;
        for(int i = 0; i < sb.length(); i += 8) {
            String byteStr;
            if(i + 8 > sb.length()) {
                byteStr = sb.substring(i);
            } else {
                byteStr = sb.substring(i, i + 8);
            }
            bytes[currentIdx++] = (byte) Integer.parseInt(byteStr, 2);
        }
        encodedBytes = bytes;
    }

}
