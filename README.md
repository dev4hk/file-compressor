# Huffman File Compressor

This project is to compress & decompress text files using Huffman Coding Greedy Algorithm

## Features

- This application decode & encode text contents using Huffman Coding
- The Java console displays sizes of original, compressed, decompressed files, compression rate, and checks if the contents of the original file & the decompressed file are the same
- The compressed file contains encoded contents of the original file & some metadata (i.e. Huffman Tree, length of encoded string) for decompression process
- This application reduces ~47% size of the original text file


## Tech Used

![Static Badge](https://img.shields.io/badge/Java-blue)
![Static Badge](https://img.shields.io/badge/Huffman_Coding-blue)
![Static Badge](https://img.shields.io/badge/Greedy_Algorithm-blue)
![Static Badge](https://img.shields.io/badge/Recursion-blue)
![Static Badge](https://img.shields.io/badge/Binary_Tree-blue)
![Static Badge](https://img.shields.io/badge/Tree_Traverse-blue)
![Static Badge](https://img.shields.io/badge/Hash_Map-blue)
![Static Badge](https://img.shields.io/badge/Min_Heap-blue)
![Static Badge](https://img.shields.io/badge/Bit_Set-blue)
![Static Badge](https://img.shields.io/badge/JAVA_IO-blue)

## Screenshots

- Application Run
  ![form](screenshot/img.png)

## Challenges

- In this project, Huffman Encoding generates binary String, which was actually larger than the content of original file
- For example, when we get a Huffman code table during the encoding process as below: 
  - Character | Huffman Code
    ------------- | -------------
    A  | 100
    B  | 101
    C  | 11
    D  | 0
- Then String ABBCCCDDDD is encoded to:
  - 1001011011111110000
- And this result is larger than the original string ABBCCCDDDD
- Even though this looks like a binary number, but it is still a string where each character is 2 bytes(= 16 bits)
- To solve this issue, this application uses BitSet, which is a collection of boolean where each boolean is 1 bit, 
- And it writes the BitSet object into a file using Java IO's ObjectOutputStream, which results in reduced size of the encoded contents of the original file 
  
