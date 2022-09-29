package com.fx.Map;

import java.util.function.BiConsumer;

/**
 * <p>
 * LinkedHashMap,红黑树上面的所有结点都用线连接起来了
 * </p>
 *
 * @author 梁峰源
 * @since 2022/7/21 9:35
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
    protected void afterRemove(Node<K, V> willNode, Node<K, V> removedNode) {
        LinkedNode<K, V> linkedWillNode = (LinkedNode<K, V>) willNode;
        LinkedNode<K, V> linkedRemoveNode = (LinkedNode<K, V>) removedNode;
        // 如果想要删除的实际要删除的结点不一样，表示该结点为红黑树中度为2的结点，需要交换链表的指向
        if (linkedRemoveNode != linkedWillNode) {
            // 设置中间结点,处理prev
            LinkedNode<K, V> temp = linkedWillNode.prev;
            linkedWillNode.prev = linkedRemoveNode.prev;
            linkedRemoveNode.prev = temp;
            if (linkedWillNode.prev == null) {
                first = linkedWillNode;
            } else {
                linkedWillNode.prev.next = linkedWillNode;
            }
            if (linkedRemoveNode.prev == null) {
                first = linkedRemoveNode;
            } else {
                linkedRemoveNode.prev.next = linkedRemoveNode;
            }
            // 处理next
            temp = linkedWillNode.next;
            linkedWillNode.next = linkedRemoveNode.next;
            linkedRemoveNode.next = temp;
            if (linkedWillNode.next == null) {
                last = linkedWillNode;
            } else {
                linkedWillNode.next.prev = linkedWillNode;
            }
            if (linkedRemoveNode.next == null) {
                last = linkedRemoveNode;
            } else {
                linkedRemoveNode.next.prev = linkedRemoveNode;
            }
        }
        LinkedNode<K, V> prev = linkedRemoveNode.prev;
        LinkedNode<K, V> next = linkedRemoveNode.next;
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
