package com.fx.Map;

import com.fx.IMap.Map;
import com.fx.Tree.Comparator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * <p>
 * TreeMap
 * </p>
 *
 * @since: 2022/7/12 20:57
 * @author: 梁峰源
 */
@SuppressWarnings("unchecked")
public class TreeMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    private int size;//当前树结点个数
    private Node<K, V> root;//根结点
    protected Comparator<K> comparator;//比较器

    public TreeMap() {
        this(null);
    }

    public TreeMap(Comparator<K> comparator) {
        this.comparator = comparator;
    }

    // 基于红黑树实现
    private static class Node<K, V> {
        K key;
        V value;
        boolean color = RED;
        public Node<K, V> left;//左结点
        public Node<K, V> right;//右结点
        public Node<K, V> parent;//父结点

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }

        public boolean isLeftChild() {
            return parent != null && this == parent.left;
        }

        public boolean isRightChild() {
            return parent != null && this == parent.right;
        }

        // 返回当前结点的兄弟结点
        public Node<K, V> sibling() {
            if (isLeftChild()) {
                return parent.right;
            }
            if (isRightChild()) {
                return parent.left;
            }
            //没有兄弟结点
            return null;
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public V put(K key, V value) {
        //非空检测
        KeyNotNullCheck(key);
        //先判断是否为根结点，如果根结点为空则添加根结点
        if (root == null) {
            root = new Node<>(key, value, null);
            size++;
            return null;
        }
        //非根结点，非递归进行添加
        Node<K, V> node = root;//用来标记移动的结点
        Node<K, V> parent = root;//保存当前结点的父结点，默认根结点就是父结点
        //根据比较规则找到待添加元素的位置
        int cmp = 0;
        while (node != null) {
            //比较值
            cmp = compare(key, node.key);
            //保存当前结点的父结点
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                // 相等的话覆盖
                V oldValue = node.value;
                node.key = key;
                node.value = value;
                return oldValue;
            }
        }
        //添加元素
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
        //判断是否需要平衡这棵二叉树
        afterPut(newNode);
        return null;
    }

    /**
     * 修复红黑树性质
     */
    private void afterPut(Node<K, V> node) {
        // 先取出父结点
        Node<K, V> parent = node.parent;
        // 添加的是根结点(将其染成黑色并返回)或者上溢到根结点
        if (parent == null) {
            black(node);
            return;
        }
        // 如果是前四种情况，即父结点为黑色结点，不用处理
        if (isBlack(parent)) return;
        // 取出uncle结点
        // 取出祖父结点
        Node<K, V> grand = parent.parent;
        Node<K, V> uncle = parent.sibling();
        // 如果叔父结点是红色【B树结点上溢，只需要染色】
        if (isRed(uncle)) {
            black(parent);
            black(uncle);
            // 把祖父结点当做是新添加的结点
            // 递归调用
            afterPut(red(grand));
            return;
        }
        /*
         * 叔父结点不是红色，有四种情况
         * LL/RR: parent染成BLACK，grand染成RED - grand进行单旋操作
         * LR/RL: 自己染成black，grand染成red，再双旋
         */
        if (parent.isLeftChild()) { // L
            red(grand);
            if (node.isLeftChild()) { // LL
                black(parent);
            } else { // LR
                black(node);
                rotateLeft(parent);
            }
            rotateRight(grand);
        } else { //R
            red(grand);
            if (node.isLeftChild()) { // RL
                black(node);
                rotateRight(parent);
            } else { // RR
                black(parent);
            }
            rotateLeft(grand);
        }
    }

    @Override
    public V get(K key) {
        Node<K, V> node = node(key);
        return node == null ? null : node.value;
    }


    /**
     * 对外暴露的删除方法
     */
    @Override
    public V remove(K key) {
        return remove(node(key));
    }


    @Override
    public boolean containsKey(K key) {
        return node(key) == null;
    }

    @Override
    public boolean containsValue(V value) {
        if (root == null) return false;
        Queue<Node<K, V>> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            Node<K, V> node = queue.poll();
            if (ValEquals(value, node.value)) return true;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
        return false;
    }

    private boolean ValEquals(V v1, V v2) {
        return v1 == null ? v2 == null : v1.equals(v2);
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        traversal(root, visitor);
    }

    /**
     * 中序遍历
     */
    private void traversal(Node<K, V> node, Visitor<K, V> visitor) {
        if (node == null || visitor.stop) return;
        traversal(node.left, visitor);
        if (visitor.stop) return;
        visitor.visit(node.key, node.value);
        traversal(node.right, visitor);
    }

    /**
     * 根据结点删除该结点
     */
    private V remove(Node<K, V> node) {
        if (node == null) return null;
        V oldValue = node.value;
        //优先处理度为2的结点
        if (node.hasTwoChildren()) {
            //找到其后继结点
            Node<K, V> successor = successor(node);
            //用后继结点的值覆盖度为2的结点的值
            node.key = successor.key;
            node.value = successor.value;
            //因为度为2的结点的后继或者前驱结点一定是度为1或0，所以将删除结点交给后面的代码来做
            node = successor;
        }
        //删除度为1或者度为0的结点
        Node<K, V> replaceNode = node.left != null ? node.left : node.right;
        /*
         * 这里有三种情况，需要分类讨论
         *  1. node是度为1的结点
         *  2. node是叶子结点并且是根结点
         *  3. node是叶子结点
         */
        if (replaceNode != null) {
            //先修改node.parent的指向
            replaceNode.parent = node.parent;
            //修改parent的left、right指向
            if (node.parent == null) { //node是度为1的结点且是根结点
                root = replaceNode;
            } else if (node == node.parent.left) {
                node.parent.left = replaceNode;
            } else {
                node.parent.right = replaceNode;
            }
            //删除结点之后的处理
            afterRemove(replaceNode);
        } else if (node.parent == null) {
            //node是叶子结点并且是根结点,直接让该结点为null
            root = null;
            //删除结点之后的处理，这里不需要替代
            afterRemove(node);
        } else {
            //叶子结点
            //父结点的左子树
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                //父结点右子树
                node.parent.right = null;
            }
            //删除结点之后的处理，这里也不需要替代
            afterRemove(node);
        }
        size--;
        return oldValue;
    }

    protected void afterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;
        // 删除的是根节点
        if (parent == null) return;

        // 删除的是黑色叶子节点【下溢】
        // 判断被删除的node是左还是右
        boolean left = parent.left == null || node.isLeftChild();
        Node<K, V> sibling = left ? parent.right : parent.left;
        if (left) { // 被删除的节点在左边，兄弟节点在右边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateLeft(parent);
                // 更换兄弟
                sibling = parent.right;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.right)) {
                    rotateRight(sibling);
                    sibling = parent.right;
                }

                color(sibling, colorOf(parent));
                black(sibling.right);
                black(parent);
                rotateLeft(parent);
            }
        } else { // 被删除的节点在右边，兄弟节点在左边
            if (isRed(sibling)) { // 兄弟节点是红色
                black(sibling);
                red(parent);
                rotateRight(parent);
                // 更换兄弟
                sibling = parent.left;
            }

            // 兄弟节点必然是黑色
            if (isBlack(sibling.left) && isBlack(sibling.right)) {
                // 兄弟节点没有1个红色子节点，父节点要向下跟兄弟节点合并
                boolean parentBlack = isBlack(parent);
                black(parent);
                red(sibling);
                if (parentBlack) {
                    afterRemove(parent);
                }
            } else { // 兄弟节点至少有1个红色子节点，向兄弟节点借元素
                // 兄弟节点的左边是黑色，兄弟要先旋转
                if (isBlack(sibling.left)) {
                    rotateLeft(sibling);
                    sibling = parent.left;
                }

                color(sibling, colorOf(parent));
                black(sibling.left);
                black(parent);
                rotateRight(parent);
            }
        }
    }

    /**
     * 根据传入的元素找到结点
     */
    private Node<K, V> node(K key) {
        Node<K, V> node = root;
        while (node != null) {
            int cmp = compare(key, node.key);
            if (cmp == 0) return node;
            if (cmp > 0) {
                node = node.right;
            } else {
                node = node.left;
            }
        }
        //没找到
        return null;
    }


    /**
     * 规定传入对象的比较规则
     *
     * @param k1 第一个对象
     * @param k2 第二个对象
     * @return 0表示相等，大于0表示 e1 > e2,小于0表示 e2 < e1
     */
    private int compare(K k1, K k2) {
        if (comparator != null) {
            return comparator.compare(k1, k2);
        }
        return ((Comparable<K>) k1).compareTo(k2);
    }

    /**
     * 判断元素是否为空
     */
    private void KeyNotNullCheck(K k) {
        if (k == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 对该元素进行左旋转
     *
     * @param grand 待旋转的结点
     */
    protected void rotateLeft(Node<K, V> grand) {
        if (null == grand) return;
        //获得parent结点
        Node<K, V> parent = grand.right;
        //将parent的左子结点取出
        Node<K, V> leftChild = parent.left;
        //左旋
        grand.right = leftChild;
        parent.left = grand;
        //旋转之后让parent结点成为根结点并更新grand、parent、child结点的高度
        afterRotate(grand, parent, leftChild);
    }


    /**
     * 对该元素进行右旋转
     *
     * @param grand 待旋转的结点
     */
    protected void rotateRight(Node<K, V> grand) {
        //获得parent结点,即grand结点的左结点
        Node<K, V> parent = grand.left;
        //获得parent结点的右子结点，方便后面更新高度
        Node<K, V> rightChild = parent.right;
        //右旋
        grand.left = rightChild;
        parent.right = grand;
        //旋转之后让parent结点成为根结点并更新grand、parent、child结点的高度
        afterRotate(grand, parent, rightChild);
    }

    /**
     * 旋转之后让parent结点成为根结点并更新grand、parent、child结点的高度
     */
    protected void afterRotate(Node<K, V> grand, Node<K, V> parent, Node<K, V> child) {
        /*
         * 让parent结点成为当前子树的根结点
         * 这里有两步：
         *  1. 让parent的父结点指向grand的父结点
         *  2. 让grand父结点本来指向grand的指针指向parent,这里顺便更新了parent结点的父结点
         **/
        parent.parent = grand.parent;
        if (grand.isLeftChild()) {
            grand.parent.left = parent;
        } else if (grand.isRightChild()) {
            grand.parent.right = parent;
        } else {
            //当前结点没有父结点，即grand结点就是root结点
            root = parent;
        }
        /*
         * 一共需要更新三个结点的parent，grand、parent和leftChild结点
         * grand结点在上面第二步中已经更新了，所以这里我们还需要更新parent结点和leftChild结点的parent结点
         **/
        if (child != null) {
            child.parent = grand;
        }
        //更新grand的parent结点
        grand.parent = parent;

    }

    /**
     * 找到当前结点的前驱结点
     */
    protected Node<K, V> predecessor(Node<K, V> node) {
        if (node == null) throw new IllegalArgumentException("node不能为空");
        //前驱结点在左子树当中(left.right.right.......)
        Node<K, V> p = node.left;
        if (p != null) {
            while (p.right != null) {
                p = p.right;
            }
            return p;
        }
        //从祖父结点里面找
        while (node.parent != null && node == node.parent.left) {
            node = node.parent;
        }
        /*
         * 这里有两种情况
         *  1. node.parent == null
         *  2. node = node.parent.right;
         */
        return node.parent;
    }

    /**
     * 找到其后继结点
     */
    protected Node<K, V> successor(Node<K, V> node) {
        if (node == null) throw new IllegalArgumentException("node不能为空");
        Node<K, V> p = node.right;
        //第一种情况，其后继结点为node.right.left.left...
        if (p != null) {
            while (p.left != null) {
                p = p.left;
            }
            return p;
        }
        //从祖父结点里面找
        while (node.parent != null && node == node.parent.right) {
            node = node.parent;
        }
        /*
         * 来到这里有两种情况
         *  1. node.right = null
         *  2. node = node.parent.left;
         */
        return node.parent;
    }

    //染成红色
    private Node<K, V> red(Node<K, V> node) {
        return color(node, RED);
    }

    //染成黑色
    private Node<K, V> black(Node<K, V> node) {
        return color(node, BLACK);
    }

    /**
     * 将元素染色
     *
     * @param node  带染色的结点
     * @param color 需要染的颜色
     * @return 将染色的结点返回
     */
    private Node<K, V> color(Node<K, V> node, boolean color) {
        if (node == null) return node;
        node.color = color;
        return node;
    }

    //查看当前结点颜色
    private boolean colorOf(Node<K, V> node) {
        return node == null ? BLACK : node.color;
    }

    private boolean isBlack(Node<K, V> node) {
        return colorOf(node) == BLACK;
    }

    private boolean isRed(Node<K, V> node) {
        return colorOf(node) == RED;
    }
}
