import java.util.*;

public class HuffmanZip {

    public static Map<Character, Integer> freqMap;
    public static Queue<HuffmanNode> minHeap;
    public static HuffmanNode root;
    public static Map<Character, String> encodeMap;

    public static String encode(String source) {
        createFreqMap(source);
        createMinHeap();
        createTree();
        createCodeMap();
        return createEncodedString(source);
    }

    private static String createEncodedString(String source) {
        StringBuilder sb = new StringBuilder();
        for (char ch : source.toCharArray()) {
            sb.append(encodeMap.get(ch));
        }
        return sb.toString();
    }

    private static void createCodeMap() {
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

    private static void createFreqMap(String source) {
        freqMap = new HashMap<>();
        for (char ch : source.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
    }

    public static String decode(String encoded) {
        StringBuilder sb = new StringBuilder();
        HuffmanNode curr = root;
        int idx = 0;
        while(idx < encoded.length()) {
            while(!isLeaf(curr)) {
                char ch = encoded.charAt(idx);
                if(ch == '1') {
                    curr = curr.getRight();
                } else if(ch == '0') {
                    curr = curr.getLeft();
                }
                idx++;
            }
            sb.append(curr.getCh());
            curr = root;
        }
        return sb.toString();
    }

    private static boolean isLeaf(HuffmanNode node) {
        return node.getLeft() == null && node.getRight() == null;
    }

}
