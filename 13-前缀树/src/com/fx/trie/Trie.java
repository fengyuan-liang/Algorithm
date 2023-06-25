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
        Map<Character, Node<V>> children; // 存储分叉的子结点
        V value;
        boolean isEndWord; // 是否以单词的结尾（是否为一个完整的单词）

        public Map<Character, Node<V>> getChildren() {
            if (children == null) {
                children = new HashMap<>();
            }
            return children;
        }
    }

    private int size;
    private final Node<V> root = new Node<>();


    @Override
    public int size() {
        return 0;
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
    public V add(String str, V value) {
        return null;
    }

    @Override
    public V remove(String str) {
        return null;
    }

    @Override
    public boolean starsWith(String prefix) {
        return false;
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
        if (key == null || key.length() == 0) {
            throw new RuntimeException("key is empty");
        }
    }
}
