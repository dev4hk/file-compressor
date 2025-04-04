# Text File Compressor Using Huffman Coding Algorithm

This project demonstrates how to compress and decompress text files using the **Huffman Coding** greedy algorithm.

## 🚀 Features

- Compresses and decompresses text content using Huffman Coding.
- Displays original, compressed, and decompressed file sizes along with the compression rate.
- Verifies that the decompressed file matches the original.
- Stores encoded content along with necessary metadata (e.g., Huffman Tree, encoded string length) for decompression.
- Achieves an average compression rate of approximately **47%**.

## 🛠️ Input Arguments

```bash
[source_text_file_path] [compression_option] [destination_text_file_path]
```

### 🧩 Compression Options

- `-e` : Encode (compress)
- `-d` : Decode (decompress)
- **Example**: `files/file.txt -e files/encoded_file.txt`


## 🧠 Data Structures, Algorithms, and Key Components

- **Hash Map**: Used to store character frequencies and map each character to its corresponding frequency.
- **Min Heap**: A priority queue used to build the Huffman Tree by always extracting the nodes with the lowest frequencies.
- **Bit Set**: Utilized for efficient storage and manipulation of binary data, reducing memory usage.
- **Binary Tree**: Forms the core structure of the Huffman Tree, where each node represents a character and its frequency.
- **Tree In-Order Traversal**: Used to generate the Huffman codes by traversing the tree from left to right.
- **Regular Expression**: Applied for parsing text content when necessary during the encoding/decoding processes.


## 🔧 Huffman Coding Algorithm Implementation

### 🔐 Encoding Process

1. **Read the input text file**: Open the text file to encode.
2. **Create a frequency map**: Build a `HashMap` to count the occurrences of each character in the file.
3. **Build tree nodes**: Instantiate a node for each character, including its frequency, left child, and right child, and add these nodes to a min heap.
4. **Process all characters**: Repeat step 3 for all characters present in the frequency map.
5. **Build the Huffman tree**:
    - Pop the two nodes with the lowest frequencies from the heap.
    - Create a new parent node with these two nodes as children.
    - Push the newly created parent node back into the heap.
6. **Repeat the process**: Continue popping nodes and creating parent nodes until there is only one node remaining, which becomes the root of the Huffman tree.
7. **Create encoding map**:
    - Build another `HashMap` where each character is mapped to its Huffman code (a binary string).
8. **Perform in-order traversal**:
    - Traverse the tree from the root. Assign a binary `0` when moving left and a `1` when moving right.
9. **Assign binary codes**: When reaching a leaf node, assign the corresponding binary string to the character.
10. **Generate the encoded string**: Convert the original file's text into a binary string using the Huffman codes.
11. **Store in BitSet**: Convert the binary string into a `BitSet` for efficient storage.
12. **Save to file**: Save the Huffman tree and the BitSet to a new file for future decompression.

### 🔓 Decoding Process

1. **Read the encoded file**: Open the file containing the Huffman tree and the BitSet.
2. **Process the BitSet**:
    - Loop through each bit in the BitSet.
    - Traverse the Huffman tree, moving left for `0` and right for `1`.
3. **Reconstruct the original text**:
    - When reaching a leaf node, add the character to a `StringBuilder`.
4. **Write the decompressed text**: Once all bits are processed, output the decoded text to a new file.

### 🌲 Example: Huffman Tree

Consider the string `ABBCCC`. The corresponding Huffman tree might look like this:

![](screenshot/Huffman_Diagram.png)


## 🧰 Technologies Used

- **Programming Language**: Java
- **Algorithm**: Huffman Coding
- **Algorithm Type**: Greedy Algorithm
- **Approach**: Recursion, Iteration
- **Data Structures**:
    - Binary Tree
    - Tree Traversal
    - Hash Map
    - Min Heap
    - Bit Set
- **Java I/O**: Java Input/Output (Java I/O)



## 🧪 Screenshots

### ▶️ Application Run
![Application Run Screenshot](screenshot/img.png)

## ⚠️ Challenges

- In this project, Huffman Encoding generates a binary string that can actually be larger than the content of the original file.

- For example, consider the following Huffman code table generated during the encoding process:

| Character | Huffman Code |
|-----------|--------------|
| A         | 100          |
| B         | 101          |
| C         | 11           |
| D         | 0            |

- If we encode the string `ABBCCCDDDD`, it results in the following binary string:
  - 1001011011111110000

- This result is actually larger than the original string `ABBCCCDDDD`.

- Even though the result looks like a binary number, each character is still represented as a 2-byte (16-bit) value in the string format.

### ✅ Solution

To solve this issue, this application uses `BitSet`, which is a collection of boolean values where each boolean represents a single bit. This reduces the size of the encoded contents. The `BitSet` object is then written to a file using Java IO's `ObjectOutputStream`, which helps efficiently store the compressed data.
