package com.fx.linkList;

/**
 * 在链表头结点前面添加了虚拟头结点，使代码更加严谨且简洁
 */
public class LinkList2<E> extends AbstractList<E> {
    private Node<E> first;
    private Node<E> last;

    public LinkList2() {
        //创建一个虚拟头结点
        first = new Node<>(null, null);
    }

    private static class Node<E> {
        E element;
        Node<E> next;

        public Node(E element, Node<E> next) {
            this.element = element;
            this.next = next;
        }

        @Override
        public String toString() {
            StringBuilder sb = new StringBuilder();
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
        Node<E> prev = index == 0 ? first : node(index - 1);//首先获取上一个结点
        prev.next = new Node<>(element, prev.next);
        size++;
    }

    @Override
    public void add(E element) {
        add(size, element);
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        Node<E> prev = index == 0 ? first : node(index - 1);//首先获取上一个结点
        Node<E> node = prev.next;
        prev.next = node.next;
        size--;
        return node.element;
    }

    @Override
    public void clear() {
        size = 0;
        first = null;
    }

    @Override
    public boolean contains(E element) {
        return indexOf(element) != ELEMENT_NOT_FOUND;
    }

    @Override
    public int indexOf(E element) {
        Node<E> node = first;
        if (element == null) {
            for (int i = 0; i < size; i++) {
                if (node.element == null) {
                    return i;
                } else {
                    node = node.next;
                }
            }
        } else {
            for (int i = 0; i < size; i++) {
                //让使用者指定E的比较方法,如未指定，即比较内存地址
                if (element.equals(node.element)) {
                    return i;
                }
                node = node.next;
            }
        }
        return ELEMENT_NOT_FOUND;
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldElement = node.element;
        node.element = element;
        return oldElement;
    }

    @Override
    public E get(int index) {
        return node(index).element;
    }

    @Override
    public boolean isEmpty() {
        return first == null;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 返回指定索引值上的node对象
     */
    private Node<E> node(int index) {
        rangeCheck(index);
        Node<E> node = first.next;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    @Override
    public String toString() {
        StringBuilder sbf = new StringBuilder();
        sbf.append("size=").append(size).append(",[");
        Node<E> node = first;
        for (int i = 0; i < size; i++) {
            if (i != 0) {
                sbf.append(", ");
            }
            sbf.append(node.element);
            node = node.next;
        }
        sbf.append("]");
        return sbf.toString();
    }

}
