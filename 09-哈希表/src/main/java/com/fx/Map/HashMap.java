package com.fx.Map;

import com.fx.IMap.Map;
import com.fx.printer.BinaryTreeInfo;
import com.fx.printer.BinaryTrees;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.BiConsumer;

/**
 * <p>
 * hashmap源码学习
 * </p>
 *
 * @since: 2022/7/15 14:57
 * @author: 梁峰源
 */
@SuppressWarnings("unchecked")
public class HashMap<K, V> implements Map<K, V> {
    private static final boolean RED = false;
    private static final boolean BLACK = true;
    /**
     * size表示现在有多少个桶里面有结点了，这里要和数组长度区分开来
     */
    private int size;
    /**
     * 数组默认的大小，必须是2的幂，1 << 4可以更方便看出幂关系
     */
    private static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
    /**
     * 装填因子，等于节点总数量 / 哈希表桶数组长度
     */
    private static final float DEFAULT_LOAD_FACTOR = 0.75f;

    /**
     * 基于红黑树根结点的数组，每一个桶的位置都是一个红黑树根结点
     */
    private Node<K, V>[] table;

    /**
     * 初始化
     */
    public HashMap() {
        table = new Node[DEFAULT_INITIAL_CAPACITY];
    }

    /**
     * 红黑树结点
     *
     * @param <K>
     * @param <V>
     */
    protected static class Node<K, V> {
        K key;
        V value;
        int hashCode;
        boolean color = RED;
        public Node<K, V> left;//左结点
        public Node<K, V> right;//右结点
        public Node<K, V> parent;//父结点

        public Node(K key, V value, Node<K, V> parent) {
            this.key = key;
            this.value = value;
            this.parent = parent;
            this.hashCode = key == null ? 0 : key.hashCode();
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

        @Override
        public String toString() {
            return "Node{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
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
        if (table != null && size > 0) {
            size = 0;
            // 遍历每个桶，将头结点置空
            for (int i = 0; i < table.length; ++i)
                table[i] = null;
        }
    }

    /**
     * 计算Key的索引
     */
    private int index(K key) {
        return hash(key) & (table.length - 1);
    }

    /**
     * 对key进行扰动计算
     */
    private int hash(K key) {
        // HashMap运行key为空，为空我们将其放到数组下标为0的位置
        if (key == null) return 0;
        // 计算hashCode
        int hashCode = key.hashCode();
        // 拿高16位和低16位进行混淆运算，让hash值更加离散，减少hash冲突
        hashCode = hashCode ^ (hashCode >>> 16);
        return hashCode;
    }

    private int index(Node<K, V> node) {
        return node.hashCode & (table.length - 1);
    }

    @Override
    public V put(K key, V value) {
        // 检测是否需要扩容
        resize();
        // 拿到索引
        int index = index(key);
        // 取出index位置上的红黑树结点
        Node<K, V> rootNode = table[index];
        if (rootNode == null) {
            // 如果根结点为空，初始化结点
            rootNode = createNode(key, value, null);
            // 放到对应桶里面
            table[index] = rootNode;
            size++;
            // 新增加了一个结点后一定要修复红黑树性质
            fixAfterPut(rootNode);
            return value;
        }
        // 桶上面已经有结点了，即发生了hash冲突
        Node<K, V> parent;
        Node<K, V> node = rootNode;
        int cmp = 0;
        // 计算添加结点key的hash值
        int h1 = hash(key);
        // 定义一个中间变量
        Node<K, V> result = null;
        // 用来标记是否已经扫描过整棵树了
        boolean searched = false;
        do {
            parent = node;
            K k2 = node.key;
            int h2 = node.hashCode;
            // 比较hashCode
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (Objects.equals(key, k2)) { // 通过equals方法比较
                cmp = 0;
            } else if (key != null && k2 != null // 比较类名或者自身的compareTo方法
                    && key.getClass() == k2.getClass()
                    && key instanceof Comparable
                    && (cmp = ((Comparable) key).compareTo(k2)) != 0) {
                // nothing to do
            } else if (searched) {
                // 之前已经扫描过了，发现没有该结点，直接比较内存地址即可
                cmp = System.identityHashCode(key) - System.identityHashCode(k2);
            } else {
                // 扫描整棵红黑树，看该结点是否已经出现过了
                if ((node.left != null && (result = node(node.left, key)) != null)
                        || (node.right != null && (result = node(node.right, key)) != null)) {
                    // 表示已经存在这个key
                    node = result;
                    cmp = 0;
                } else {
                    cmp = System.identityHashCode(key) - System.identityHashCode(k2);
                    // 标记已经整棵树扫描过了
                    searched = true;
                }
            }
            /*----------------------------下面是红黑树结点的摆放---------------------------------*/
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
        } while (node != null);
        //添加元素
        Node<K, V> newNode = new Node<>(key, value, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }

        size++;

        //判断是否需要平衡这棵二叉树
        fixAfterPut(newNode);
        return null;
    }

    /**
     * 创建一个结点
     */
    protected Node<K, V> createNode(
            K k,
            V v,
            Node<K, V> parent) {
        return new Node<>(k, v, parent);
    }

    /**
     * 删除结点后的操作，注意这里不是修复红黑树的性质
     */
    protected void afterRemove(Node<K, V> removedNode) {
    }

    /**
     * 判断是否需要对数组容量进行扩容
     */
    private void resize() {
        // 装填因子小于等于0.75
        if (size / table.length <= DEFAULT_LOAD_FACTOR) return;
        System.out.println("map进行了扩容,原容量为：" + table.length + "，新容量为：" + (table.length << 1));
        // 先保留一下旧的数组
        Node<K, V>[] oldTable = table;
        // 扩容两倍
        table = new Node[oldTable.length << 1];
        // 移动所有的结点到新的桶上面
        // 这里手动遍历所有的桶
        // 准备一个栈
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (Node<K, V> kvNode : oldTable) {
            if (kvNode == null) continue;
            // 将桶上根结点入队
            queue.offer(kvNode);
            while (!queue.isEmpty()) {
                // 出栈
                Node<K, V> popNode = queue.poll();
                if (popNode.left != null) queue.offer(popNode.left);
                if (popNode.right != null) queue.offer(popNode.right);
                // 挪动结点，需要写在入队代码之后
                moveNode(kvNode);
            }
        }
    }

    private void moveNode(Node<K, V> newNode) {
        // 重置该结点的所有引用
        newNode.parent = null;
        newNode.left = null;
        newNode.right = null;
        // 树结点默认应该为red
        red(newNode);
        // 拿到索引
        int index = index(newNode);
        // 取出index位置上的红黑树结点
        Node<K, V> rootNode = table[index];
        if (rootNode == null) {
            // 如果根结点为空，初始化结点
            rootNode = newNode;
            // 放到对应桶里面
            table[index] = rootNode;
            // 新增加了一个结点后一定要修复红黑树性质
            fixAfterPut(rootNode);
            return;
        }
        // 桶上面已经有结点了，即发生了hash冲突
        Node<K, V> parent;
        Node<K, V> node = rootNode;
        int cmp;
        K key = newNode.key;
        // 计算添加结点key的hash值
        int h1 = newNode.hashCode;
        do {
            K k2 = node.key;
            int h2 = node.hashCode;
            // 挪动的时候只需要考虑是往左走还是往右走，不需要equals
            if (h1 > h2) {
                cmp = 1;
            } else if (h1 < h2) {
                cmp = -1;
            } else if (key != null && k2 != null // 比较类名或者自身的compareTo方法
                    && key.getClass() == k2.getClass()
                    && key instanceof Comparable
                    && (cmp = ((Comparable) key).compareTo(k2)) != 0) {
                // nothing to do
            } else {
                cmp = System.identityHashCode(key) - System.identityHashCode(k2);
            }
            /*----------------------------下面是红黑树结点的摆放---------------------------------*/
            //保存当前结点的父结点
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            }
        } while (node != null);
        // 因为是移动结点，不可能出现相等的情况
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        // 设置移动结点的父结点
        newNode.parent = parent;
        //判断是否需要平衡这棵二叉树
        fixAfterPut(newNode);
    }


    /**
     * 通过key找到node结点
     */
    private Node<K, V> node(K key) {
        // 先计算hash值
        Node<K, V> root = table[index(key)];
        return root == null ? null : node(root, key);
    }

    /**
     * 通过key找到node结点
     */
    private Node<K, V> node(Node<K, V> node, K k1) {
        // 先计算hash值，k1需要经过扰动计算
        int h1 = hash(k1);
        // 存放查找的结果
        Node<K, V> result;
        int cmp;
        while (node != null) {
            K k2 = node.key;
            // h2是经过了扰动计算的
            int h2 = node.hashCode;
            // 先比较hash值
            if (h1 > h2) {
                node = node.right;
            } else if (h1 < h2) {
                node = node.left;
            } else if (Objects.equals(k1, k2)) {
                return node;
            } else if (k1 != null && k2 != null
                    && k1.getClass() == k2.getClass()
                    && k1 instanceof Comparable
                    && (cmp = ((Comparable) k1).compareTo(k2)) != 0) {
                node = cmp > 0 ? node.right : node.left;
            } else if (node.right != null && (result = node(node.right, k1)) != null) {
                // 遍历右子树
                return result;
            } else {
                // 只能往左边走
                node = node.left;
            }
        }
        return null;
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

    /**
     * 根据结点删除该结点
     */
    protected V remove(Node<K, V> node) {
        if (node == null) return null;
        // 计算桶的位置
        int index = index(node.key);
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
                // 这里要替换成对应桶的位置
                table[index] = replaceNode;
            } else if (node == node.parent.left) {
                node.parent.left = replaceNode;
            } else {
                node.parent.right = replaceNode;
            }
            //删除结点之后的处理
            fixAfterRemove(replaceNode);
        } else if (node.parent == null) {
            //node是叶子结点并且是根结点,直接让该结点为null
            table[index] = null;
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
            fixAfterRemove(node);
        }
        size--;
        // 删除结点后的操作，交给子类去实现
        afterRemove(node);
        return oldValue;
    }

    /**
     * 删除之后的补偿策略
     */
    protected void fixAfterRemove(Node<K, V> node) {
        // 如果删除的节点是红色
        // 或者 用以取代删除节点的子节点是红色
        if (isRed(node)) {
            black(node);
            return;
        }

        Node<K, V> parent = node.parent;


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
                    fixAfterRemove(parent);
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
                    fixAfterRemove(parent);
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

    @Override
    public boolean containsKey(K key) {
        return node(key) != null;
    }

    @Override
    public boolean containsValue(V value) {
        // 层序遍历每个桶上面的红黑树
        if (size == 0) return false;
        AtomicBoolean result = new AtomicBoolean(false);
        // 遍历集合
        forEach((k, v) -> {
            if (Objects.equals(v, value))
                result.set(true);
        });
        return result.get();
    }

    @Override
    public void traversal(Visitor<K, V> visitor) {
        // 准备一个栈
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (Node<K, V> kvNode : table) {
            if (kvNode == null) continue;
            if (visitor.stop) return;
            // 将桶上根结点入队
            queue.offer(kvNode);
            while (!queue.isEmpty() && !visitor.stop) {
                // 出栈
                Node<K, V> popNode = queue.poll();
                // 执行回调函数
                visitor.visit(popNode.key, popNode.value);
                if (popNode.left != null) queue.offer(popNode.left);
                if (popNode.right != null) queue.offer(popNode.right);
            }
        }
    }

    /**
     * 通过forEach遍历函数
     */
    @Override
    public void forEach(BiConsumer<? super K, ? super V> action) {
        if (action == null)
            throw new NullPointerException();
        // 这里手动遍历所有的桶
        // 准备一个栈
        Queue<Node<K, V>> queue = new LinkedList<>();
        for (Node<K, V> kvNode : table) {
            if (kvNode == null) continue;
            // 将桶上根结点入队
            queue.offer(kvNode);
            while (!queue.isEmpty()) {
                // 出栈
                Node<K, V> popNode = queue.poll();
                // 执行回调函数
                action.accept(popNode.key, popNode.value);
                if (popNode.left != null) queue.offer(popNode.left);
                if (popNode.right != null) queue.offer(popNode.right);
            }
        }
    }

    /**
     * 打印所有的红黑树
     */
    public void showTree() {
        for (int i = 0; i < table.length; i++) {
            Node<K, V> node = table[i];
            if (node == null) continue;
            System.out.println("-----------第【" + i + "】个结点------------");
            BinaryTrees.println(new BinaryTreeInfo() {
                @Override
                public Object root() {
                    return node;
                }

                @Override
                public Object left(Object node) {
                    return ((Node<K, V>) node).left;
                }

                @Override
                public Object right(Object node) {
                    return ((Node<K, V>) node).right;
                }

                @Override
                public Object string(Object node) {
                    return node;
                }
            });
            System.out.println("---------------------------------------");
        }
    }

    /*--------------HashMap中的key的比较--------------*/

    /**
     * 规定传入对象的比较规则
     *
     * @param k1 第一个对象
     * @param k2 第二个对象
     * @param h1 k1的hash值
     * @param h2 k2的hash值
     * @return 0表示相等，大于0表示 e1 > e2,小于0表示 e2 < e1
     */
    private int compare(K k1, K k2, int h1, int h2) {
        // 先比较hash值，如果hash值不同，返回hash值的差
        int result = h1 - h2;
        if (result != 0) return result;
        // hash值一样，需要比较equals
        if (Objects.equals(k1, k2)) return 0;
        // hash值相等，但不equals，我们比较类名
        if (k1 != null && k2 != null) {
            String k1ClassName = k1.getClass().getName();
            String k2ClassName = k2.getClass().getName();
            // 通过类名进行比较
            result = k1ClassName.compareTo(k2ClassName);
            if (result != 0) return result;
            // 类名也相等，继续比较，如果key实现了Comparable接口，直接进行比较
            if (k1 instanceof Comparable) return ((Comparable) k1).compareTo(k2);
        }
        /*
         * 同一种类型，但是不具备可比较性
         * 1. k1为null但k2不为null
         * 2. k2不为null但是k1为空
         * 注意：k1、k2都为空的情况已经被上面的Objects.equals方法拦截
         */
        // 到了这里，只能比较内存地址了
        return System.identityHashCode(k1) - System.identityHashCode(k2);
    }

    /*------------------修复红黑树性质---------------------*/

    /**
     * 修复红黑树性质
     */
    private void fixAfterPut(Node<K, V> node) {
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
            fixAfterPut(red(grand));
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
            table[index(grand.key)] = parent;
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
