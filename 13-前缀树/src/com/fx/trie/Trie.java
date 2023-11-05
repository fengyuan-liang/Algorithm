package com.fx.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *
 * </p>
 *
 * @author 梁峰源 <fengyuan-liang@foxmail.com>
 * @since 2023/6/24 14:25
 */
public class Trie<V> implements ITrie<V> {

    private static class Node<V> {
        Map<Character, Node<V>> children; // 存储分叉的子结点，k为当前节点存储的字符
        V value;
        boolean isEndWord; // 是否以单词的结尾（是否为一个完整的单词）

        public Map<Character, Node<V>> getChildren() {
            return children == null ? (children = new HashMap<>()) : children;
        }
    }

    private int size;
    private final Node<V> root = new Node<>();


    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public void clear() {
        size = 0;
        root.children.clear();
    }

    @Override
    public boolean contains(String str) {
        return false;
    }

    @Override
    public V get(String key) {
        Node<V> node = node(key);
        return node == null ? null : node.value;
    }

    @Override
    public V add(String k, V value) {
        keyCheck(k);
        Node<V> node = root;
        for (char c : k.toCharArray()) {
            Node<V> childNode = node.getChildren().get(c);
            if (childNode == null) {
                childNode = new Node<>();
                node.getChildren().put(c, childNode);
            }
            node = childNode;
        }
        // 之前没有单词
        if (!node.isEndWord) {
            node.isEndWord = true; node.value = value; size++;
            return null;
        }
        // 存在则覆盖
        V oldValue = node.value;
        node.value = value;
        return oldValue;
    }

    @Override
    public V remove(String str) {
        Node<V> node = node(str);
        if (node == null) {
            return null;
        }
        node.isEndWord = false;
        V oldValue = node.value;
        node.value = null;
        return oldValue;
    }

    @Override
    public boolean starsWith(String prefix) {
       keyCheck(prefix);

       Node<V> node = root;
        for (char c : prefix.toCharArray()) {
            node = node.getChildren().get(c);
            if (node == null) {
                return false;
            }
        }
        return true;
    }

    private Node<V> node(String key) {
        keyCheck(key);
        Node<V> node = this.root;
        int len = key.length();
        for (int i = 0; i < len; i++) {
            char c = key.charAt(i); // d o g
            node = node.getChildren().get(c);
            if (node == null) {
                return null;
            }
        }
        return node.isEndWord ? node : null;
    }

    private void keyCheck(String key) {
        if (key == null || key.isEmpty()) {
            throw new IllegalArgumentException("key is empty");
        }
    }
}
