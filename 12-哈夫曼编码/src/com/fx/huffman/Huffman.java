package com.fx.huffman;

import java.util.Comparator;
import java.util.HashMap;
import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 哈夫曼编码
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/6/23 13:29
 */

class HuffmanNode {
    int data;
    char c;
    HuffmanNode left;
    HuffmanNode right;
}

class MyComparator implements Comparator<HuffmanNode> {
    public int compare(HuffmanNode x, HuffmanNode y) {
        return x.data - y.data;
    }
}

public class Huffman {

    public static void printCode(HuffmanNode root, String s) {
        if (root.left == null && root.right == null && Character.isLetter(root.c)) {
            System.out.println(root.c + ":" + s);
            return;
        }
        assert root.left != null;
        printCode(root.left, s + "0");
        printCode(root.right, s + "1");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the input string: ");
        String inputString = sc.nextLine();

        HashMap<Character, Integer> freqMap = new HashMap<>();

        for (int i = 0; i < inputString.length(); i++) {
            char c = inputString.charAt(i);
            if (Character.isLetter(c)) {
                freqMap.put(c, freqMap.getOrDefault(c, 0) + 1);
            }
        }

        PriorityQueue<HuffmanNode> pq
                = new PriorityQueue<>(new MyComparator());

        for (char c : freqMap.keySet()) {
            HuffmanNode hn = new HuffmanNode();
            hn.c = c;
            hn.data = freqMap.get(c);
            hn.left = null;
            hn.right = null;
            pq.add(hn);
        }

        while (pq.size() > 1) {

            HuffmanNode x = pq.peek();
            pq.poll();

            HuffmanNode y = pq.peek();
            pq.poll();

            HuffmanNode f = new HuffmanNode();
            f.data = x.data + y.data;
            f.c = '-';
            f.left = x;
            f.right = y;
            pq.add(f);
        }

        HuffmanNode root = pq.peek();

        printCode(root, "");
    }
}
