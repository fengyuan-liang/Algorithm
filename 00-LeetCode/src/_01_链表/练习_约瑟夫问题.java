//package _01_链表;
//
//
//import utils.AbstractList;
//
///**
// * @author: 梁峰源
// * @date: 2022/1/9 15:25
// * Description:https://www.luogu.com.cn/problem/P1996
// * 本题采用双向循环链表解决
// */
//public class 练习_约瑟夫问题<E> extends AbstractList<E> {
//    private Node<E> first;
//    private Node<E> last;
//    private Node<E> current;//用于指向结点
//    private static class Node<E> {
//        E element;
//        Node<E> prev;
//        Node<E> next;
//        public Node(Node<E> prev, E element, Node<E> next) {
//            this.prev = prev;
//            this.element = element;
//            this.next = next;
//        }
//
//        @Override
//        public String toString() {
//            StringBuilder sb = new StringBuilder();
//
//            if (prev != null) {
//                sb.append(prev.element);
//            } else {
//                sb.append("null");
//            }
//
//            sb.append("_").append(element).append("_");
//
//            if (next != null) {
//                sb.append(next.element);
//            } else {
//                sb.append("null");
//            }
//
//            return sb.toString();
//        }
//    }
//
//    public static void main(String[] args) {
//        练习_约瑟夫问题<Integer> list=new 练习_约瑟夫问题<>();
//        for (int i = 1; i < 8; i++) {
//            list.add(i);
//        }
//        //从1开始数，每3个数取出一个
//        //指向头结点
//        list.reset();
//        while (!list.isEmpty()){
//            list.currentNext();
//            list.currentNext();
//            System.out.println(list.currentRemove());;
//        }
//    }
//
//    //添加的三个方法
//    //回到1的位置
//    public void reset(){
//        current=first;
//    }
//    //让current移向下一个位置
//    public E currentNext(){
//        if(current==null) return null;
//        current=current.next;
//        return current.element;
//    }
//    //删除current指向的元素，删除后让其移向下一个元素
//    public E currentRemove(){
//        Node<E> next=current.next;
//        E removeElement = remove(indexOf(current.element));
//        current = size == 0 ? null : next;
//        return removeElement;
//    }
//
//    @Override
//    public void add(int index, E element) {
//        rangeCheckForAdd(index);
//        if(index == size){
//            //往最后面添加元素
//            Node<E> oldLast = last;
//            last = new Node<>(oldLast,element,first);
//            if(oldLast==null){//链表添加的第一个元素
//                first=last;
//                first.next=first;
//                first.prev=first;
//            }else {
//                oldLast.next=last;
//                first.prev=last;
//            }
//        }else {
//            //往最后一个元素之前添加元素
//            Node<E> next = node(index);
//            Node<E> prev = next.prev;
//            Node<E> newNode = new Node<>(prev, element, next);
//            next.prev=newNode;
//            prev.next=newNode;
//            if(index == 0){//第一个元素添加结点
//                first = newNode;
//            }
//        }
//        size++;
//    }
//
//    @Override
//    public void add(E element) {
//        add(size,element);
//    }
//
//    @Override
//    public E remove(int index) {
//        rangeCheck(index);
//        Node<E> node=node(index);
//        if(index == 0){
//            //移除的是第一个元素
//            first=node.next;
//            node.next.prev=node.prev;
//        }else if(index == size - 1){
//            //最后一个元素为空
//            last=node.prev;
//            node.prev.next=first;
//        }else {
//            node.prev.next=node.next;
//            node.next.prev=node.prev;
//        }
//        size--;
//        return node.element;
//    }
//
//    @Override
//    public void clear() {
//        size=0;
//        first=null;
//        last=null;
//    }
//
//    @Override
//    public boolean contains(Object element) {
//        return indexOf(element) != ELEMENT_NOT_FOUND;
//    }
//
//    @Override
//    public int indexOf(E element) {
//        Node<E> node=first;
//        if(element==null){
//            for (int i = 0; i < size; i++) {
//                if (node.element==null){
//                    return i;
//                }else {
//                    node=node.next;
//                }
//            }
//        }else {
//            for (int i = 0; i < size; i++) {
//                //让使用者指定E的比较方法,如未指定，即比较内存地址
//                if (element.equals(node.element)) {
//                    return i;
//                }
//                node=node.next;
//            }
//        }
//        return ELEMENT_NOT_FOUND;
//    }
//
//    @Override
//    public E set(int index, E element) {
//        Node<E> node = node(index);
//        E oldElement=node.element;
//        node.element=element;
//        return oldElement;
//    }
//
//    @Override
//    public E get(int index) {
//        return node(index).element;
//    }
//
//    @Override
//    public boolean isEmpty() {
//        return first==null;
//    }
//
//    @Override
//    public int size() {
//        return size;
//    }
//
//    /**
//     * 返回指定索引值上的node对象
//     */
//    private Node<E> node(int index){
//        rangeCheck(index);
//        Node<E> node=null;
//        if(index < (size) >> 1){
//            //从左往右找
//            node=first;
//            for (int i = 0; i < index; i++) {
//                node=node.next;
//            }
//        }else {
//            //从右往左找
//            node=last;
//            for (int i = size - 1; i > index; i--) {
//                node=node.prev;
//            }
//        }
//        return node;
//    }
//    @Override
//    public String toString() {
//        StringBuilder sbf = new StringBuilder();
//        sbf.append("size=").append(size).append(",[");
//        Node<E> node=first;
//        for (int i = 0; i < size; i++) {
//            if (i != 0) {
//                sbf.append(", ");
//            }
//            sbf.append(node.element);
//            node=node.next;
//        }
//        sbf.append("]");
//        return sbf.toString();
//    }
//}
