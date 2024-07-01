import java.io.IOException;
import java.util.*;

public class HuffmanService {

    private FileUtils fileUtils;

    public HuffmanService() {
        this.fileUtils = new FileUtils();
    }

    public void encode(String sourceFilePath, String destinationFilePath) throws IOException {
        String originalString = fileUtils.readFileToEncode(sourceFilePath);
        Map<Character, Integer> freqMap = createFreqMap(originalString);
        Queue<HuffmanNode> minHeap = createMinHeap(freqMap);
        HuffmanNode root = createTree(minHeap);
        Map<Character, String> encodeMap = createEncodeMap(root);
        String encodedString = createEncodedString(originalString, encodeMap);
        BitSet bitSet = createBitSet(encodedString);
        FileHeader fileHeader = new FileHeader(encodedString.length(), root);
        writeEncodedToFile(bitSet, fileHeader, destinationFilePath);
        fileUtils.printFileSize(sourceFilePath);
        fileUtils.printFileSize(destinationFilePath);
    }

    public void decode(String sourceFilePath, String destinationFilePath) throws IOException, ClassNotFoundException {
        List<Object> list = fileUtils.readFileToDecode(sourceFilePath);
        BitSet set = (BitSet) list.get(0);
        FileHeader fileHeader = (FileHeader) list.get(1);
        String decodedString = createDecodedString(set, fileHeader);
        fileUtils.writeDecodedToFile(destinationFilePath, decodedString);
        fileUtils.printFileSize(destinationFilePath);
    }

    private String createDecodedString(BitSet set, FileHeader fileHeader) {
        HuffmanNode curr = fileHeader.getRoot();
        int len = fileHeader.getEncodedStringLength();
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
        return sb.toString();
    }

    private String createEncodedString(String originalString, Map<Character, String> encodeMap) {
        StringBuilder sb = new StringBuilder();
        for(char ch : originalString.toCharArray()) {
            sb.append(encodeMap.get(ch));
        }
        return sb.toString();
    }

    private BitSet createBitSet(String encodedString) {
        BitSet bitSet = new BitSet(encodedString.length());
        for(int i = 0; i < encodedString.length(); i++) {
            if(encodedString.charAt(i) == '1') {
                bitSet.set(i);
            }
        }
        return bitSet;
    }

    private Map<Character, String> createEncodeMap(HuffmanNode root) {
        HuffmanNode curr = root;
        StringBuilder sb = new StringBuilder();
        Map<Character, String> encodeMap = new HashMap<>();
        inOrderTraverse(curr, sb, encodeMap);
        return encodeMap;
    }

    private void inOrderTraverse(HuffmanNode curr, StringBuilder sb, Map<Character, String> encodeMap) {
        if (isLeaf(curr)) {
            encodeMap.put(curr.getCh(), sb.toString());
            return;
        }
        inOrderTraverse(curr.getLeft(), sb.append('0'), encodeMap);
        sb.deleteCharAt(sb.length() - 1);
        inOrderTraverse(curr.getRight(), sb.append('1'), encodeMap);
        sb.deleteCharAt(sb.length() - 1);
    }


    private HuffmanNode createTree(Queue<HuffmanNode> minHeap) {
        while (minHeap.size() > 1) {
            HuffmanNode node1 = minHeap.poll();
            HuffmanNode node2 = minHeap.poll();
            HuffmanNode newNode = new HuffmanNode();
            newNode.setFreq(node1.getFreq() + node2.getFreq());
            newNode.setLeft(node1);
            newNode.setRight(node2);
            minHeap.offer(newNode);
        }
        return minHeap.poll();
    }

    private Queue<HuffmanNode> createMinHeap(Map<Character, Integer> freqMap) {
        Queue<HuffmanNode> minHeap = new PriorityQueue<>(new Comparator<HuffmanNode>() {
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
        return minHeap;
    }

    private Map<Character, Integer> createFreqMap(String originalString) {
        Map<Character, Integer> freqMap = new HashMap<>();
        for (char ch : originalString.toCharArray()) {
            freqMap.put(ch, freqMap.getOrDefault(ch, 0) + 1);
        }
        return freqMap;
    }

    private boolean isLeaf(HuffmanNode node) {
        return node.getLeft() == null && node.getRight() == null;
    }

    private void writeEncodedToFile(BitSet bitSet, FileHeader fileHeader, String destinationFilePath) throws IOException {
        fileUtils.writeEncodedToFile(bitSet, fileHeader, destinationFilePath);
    }

}
