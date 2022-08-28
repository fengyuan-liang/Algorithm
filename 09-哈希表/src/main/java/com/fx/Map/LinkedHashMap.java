package com.fx.Map;

import java.util.function.BiConsumer;

/**
 * <p>
 * LinkedHashMap,红黑树上面的所有结点都用线连接起来了
 * </p>
 *
 * @since 2022/7/21 9:35
 * @author 梁峰源
 */
public class LinkedHashMap<K, V> extends HashMap<K, V> {

    /* 需要记录链表的头尾结点 */
    private LinkedNode<K, V> first;
    private LinkedNode<K, V> last;

    /**
     * LinkedHashMap结点
     */
    protected static class LinkedNode<K, V> extends Node<K, V> {
        // 指向上一个结点的指针和指向下一个结点的指针
        LinkedNode<K, V> prev;
        LinkedNode<K, V> next;

        public LinkedNode(K key, V value, Node<K, V> parent) {
            super(key, value, parent);
        }
    }

    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        // 从头结点开始遍历
        LinkedNode<K, V> node = first;
        while (node != null) {
            action.accept(node.key, node.value);
            node = node.next;
        }
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        if (visitor == null) return;
        LinkedNode<K, V> node = first;
        while (node != null) {
            if (visitor.visit(node.key, node.value)) return;
            node = node.next;
        }
    }

    /**
     * 我们需要在创建结点的时候用指针将其串起来
     */
    @Override
    protected Node<K, V> createNode(K k, V v, Node<K, V> parent) {
        LinkedNode<K, V> node = new LinkedNode<>(k, v, parent);
        // 添加头结点
        if (first == null) {
            first = last = node;
        } else {
            // 非头结点
            last.next = node;
            node.prev = last;
            last = node;
        }
        return node;
    }

    /**
     * 清空结点需要将first和last去掉，不然不会进行GC
     */
    @Override
    public void clear() {
        super.clear();
        first = null;
        last = null;
    }


    /**
     * 这里并不是删除结点后修复红黑树性质的代码，而是修复链表和红黑树结点指向关系的代码
     */
    @Override
    protected void afterRemove(Node<K, V> removedNode) {
        if (removedNode == null) return;
        LinkedNode<K, V> linkedNode = (LinkedNode<K, V>) removedNode;
        LinkedNode<K, V> prev = linkedNode.prev;
        LinkedNode<K, V> next = linkedNode.next;
        if (prev == null) {
            // 是头结点，那么下一个结点成为头结点
            first = next;
        } else {
            prev.next = next;
        }
        if (next == null) {
            // 删除的是尾结点
            last = prev;
        } else {
            next.prev = prev;
        }
    }
}
