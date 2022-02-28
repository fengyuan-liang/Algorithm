package com.fx.circleLinkedList;

import com.fx.linkedList.AbstractList;

/**
 * 双向循环链表
 */
public class DoubleCircleLinkedList<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;
    private static class Node<E> {
        E element;
        Node<E> prev;
        Node<E> next;
        public Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();

            if (prev != null) {
                sb.append(prev.element);
            } else {
                sb.append("null");
            }

            sb.append("_").append(element).append("_");

            if (next != null) {
                sb.append(next.element);
            } else {
                sb.append("null");
            }

            return sb.toString();
        }
    }
    @Override
    public void add(int index, E element) {
        rangeCheckForAdd(index);
        if(index == size){
            //往最后面添加元素
            Node<E> oldLast = last;
            last = new Node<>(oldLast,element,first);
            if(oldLast==null){//链表添加的第一个元素
                first=last;
                first.next=first;
                first.prev=first;
            }else {
                oldLast.next=last;
                first.prev=last;
            }
        }else {
            //往最后一个元素之前添加元素
            Node<E> next = node(index);
            Node<E> prev = next.prev;
            Node<E> newNode = new Node<>(prev, element, next);
            next.prev=newNode;
            prev.next=newNode;
            if(index == 0){//第一个元素添加结点
                first = newNode;
            }
        }
        size++;
    }

    @Override
    public void add(E element) {
        add(size,element);
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> node=node(index);
        if(index == 0){
            //移除的是第一个元素
            first=node.next;
            node.next.prev=node.prev;
        }else if(index == size - 1){
            //最后一个元素为空
            last=node.prev;
            node.prev.next=first;
        }else {
            node.prev.next=node.next;
            node.next.prev=node.prev;
        }
        size--;
        return node.element;
    }

    @Override
    public void clear() {
        size=0;
        first=null;
        last=null;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node=first;
        if(element==null){
            for (int i = 0; i < size; i++) {
                if (node.element==null){
                    return i;
                }else {
                    node=node.next;
                }
            }
        }else {
            for (int i = 0; i < size; i++) {
                //让使用者指定E的比较方法,如未指定，即比较内存地址
                if (element.equals(node.element)) {
                    return i;
                }
                node=node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement=node.element;
        node.element=element;
        return oldElement;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public boolean isEmpty() {
        return first==null;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 返回指定索引值上的node对象
     */
    private Node<E> node(int index){
        rangeCheck(index);
        Node<E> node=null;
        if(index < (size) >> 1){
            //从左往右找
            node=first;
            for (int i = 0; i < index; i++) {
                node=node.next;
            }
        }else {
            //从右往左找
            node=last;
            for (int i = size - 1; i > index; i--) {
                node=node.prev;
            }
        }
        return node;
    }
    @Override
    public String toString() {
        StringBuilder sbf = new StringBuilder();
        sbf.append("size=").append(size).append(",[");
        Node<E> node=first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sbf.append(", ");
            }
            sbf.append(node.element);
            node=node.next;
        }
        sbf.append("]");
        return sbf.toString();
    }
}
