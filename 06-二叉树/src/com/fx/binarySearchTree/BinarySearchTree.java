package com.fx.binarySearchTree;

import com.fx.printer.BinaryTreeInfo;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * @author: xxx
 * @date: 2022/3/14 20:12
 * Description: 二叉搜索树
 */
@SuppressWarnings("unchecked")
public class BinarySearchTree<E> implements BinaryTreeInfo {
    //结点以内部类的形式存在
    private static class Node<E> {
        E element;//当前结点保存的元素
        Node<E> left;//左结点
        Node<E> right;//右结点
        Node<E> parent;//父结点

        public Node(E element, Node<E> parent) {
            this.element = element;
            this.parent = parent;
        }

        public boolean isLeaf() {
            return left == null && right == null;
        }

        public boolean hasTwoChildren() {
            return left != null && right != null;
        }
    }

    private int size;//当前树结点个数
    private Node<E> root;//根结点
    private Comparator<E> comparator;//比较器

    public BinarySearchTree() {

    }

    public BinarySearchTree(Comparator<E> comparator) {
        this.comparator = comparator;
    }

    public void add(E element) {
        //非空检测
        elementNotNullCheck(element);
        //先判断是否为根结点
        if (root == null) {
            root = new Node<>(element, null);
        }
        //非根结点，非递归进行添加
        Node<E> node = root;//用来标记移动的结点
        Node<E> parent = root;//保存当前结点的父结点，默认根结点就是父结点
        //根据比较规则找到待添加元素的位置
        int cmp = 0;
        while (node != null) {
            //比较值
            cmp = compare(element, node.element);
            //保存当前结点的父结点
            parent = node;
            if (cmp > 0) {
                node = node.right;
            } else if (cmp < 0) {
                node = node.left;
            } else {
                return;
            }
        }
        //添加元素
        Node<E> newNode = new Node<>(element, parent);
        if (cmp > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
        size++;
    }

    /**
     * 对外暴露的删除方法
     */
    public void remove(E element) {
        remove(node(element));
    }

    /**
     * 根据结点删除该结点
     */
    private void remove(Node<E> node) {
        if (node == null) return;
        //优先处理度为2的结点
        if (node.hasTwoChildren()) {
            //找到其后继结点
            Node<E> successor = successor(node);
            //用后继结点的值覆盖度为2的结点的值
            node.element = successor.element;
            //因为度为2的结点的后继或者前驱结点一定是度为1或0，所以将删除结点交给后面的代码来做
            node = successor;
        }
        //删除度为1或者度为0的结点
        Node<E> replaceNode = node.left != null ? node.left : node.right;
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
        } else if (node.parent == null) {
            //node是叶子结点并且是根结点,直接让该结点为null
            root = null;
        } else {
            //叶子结点
            //父结点的左子树
            if (node == node.parent.left) {
                node.parent.left = null;
            } else {
                //父结点右子树
                node.parent.right = null;
            }
        }
        size--;
    }

    /**
     * 根据传入的元素找到结点
     */
    private Node<E> node(E element) {
        Node<E> node = root;
        while (node != null) {
            int cmp = compare(element, node.element);
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

    public boolean contains(E e) {
        return node(e) != null;
    }


    /**
     * 规定传入对象的比较规则
     *
     * @param e1 第一个对象
     * @param e2 第二个对象
     * @return 0表示相等，大于0表示 e1 > e2,小于0表示 e2 < e1
     */
    private int compare(E e1, E e2) {
        if (comparator != null) {
            return comparator.compare(e1, e2);
        }
        return ((java.lang.Comparable<E>) e1).compareTo(e2);
    }

    /**
     * 返回树结点个数
     */
    public int size() {
        return size;
    }

    /**
     * 判断树是否为空
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * 清空当前树
     */
    public void clear() {
        root = null;
        size = 0;
    }


    /**
     * 判断元素是否为空
     */
    private void elementNotNullCheck(E element) {
        if (element == null) {
            throw new IllegalArgumentException("element must not be null");
        }
    }

    /**
     * 递归输出树的高度
     *
     * @return 返回树的高度
     */
    public int height() {
        return height(root);
    }

    private int height(Node<E> node) {
        if (node == null) return 0;
        return 1 + Math.max(height(node.left), height(node.right));
    }

    /**
     * 非递归返回树的高度
     *
     * @return 树的高度
     */
    public int heightByLevelOrder() {
        Queue<Node<E>> queue = new LinkedList<>();
        //将根结点入队
        queue.offer(root);
        //记录树的高度
        int height = 0;
        //记录每一层的元素数量
        int levelSize = 1;
        while (!queue.isEmpty()) {
            //出队
            Node<E> node = queue.poll();
            //当前层元素减少
            levelSize--;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
            //访问下一层
            if (levelSize == 0) {
                levelSize = queue.size();
                height++;
            }
        }
        return height;
    }

    /**
     * 判断该树是否是一棵完全二叉树
     *
     * @return true是<br>false不是
     */
    public boolean isComplete() {
        if (root == null) return false;
        Queue<Node<E>> queue = new LinkedList<>();
        //将根结点入队
        queue.offer(root);
        //标记上一次访问的结点是否度为一且子树为左子树
        boolean flag = false;
        //遍历所有结点
        Node<E> node;
        while (!queue.isEmpty()) {
            //出队
            node = queue.poll();
            //上一次访问的结点左子树存在右子树不存在那么这次访问的结点必须是叶子结点
            if (flag && !node.isLeaf()) return false;
            if (node.left != null) {
                queue.offer(node.left);
            } else if (node.right != null) {
                return false;
            }
            if (node.right != null) {
                queue.offer(node.right);
            } else {
                flag = true;
            }
        }
        return true;
    }


    /**
     * 遍历接口
     *
     * @param <E> visit 返回true表示停止遍历<br>返回false表示不停止，继续遍历
     */
    public abstract static class Visitor<E> {
        /**
         * stop用来标记递归遍历中是否需要停止遍历
         */
        boolean stop = false;

        abstract boolean visit(E element);
    }

    /**
     * 前序遍历
     */
    public void preorderTraversal(Visitor<E> visitor) {
        if (visitor == null) throw new IllegalArgumentException("visitor不能为空");
        preorderTraversal(root, visitor);
    }

    private void preorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null) return;
        //回调
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        preorderTraversal(node.left, visitor);
        preorderTraversal(node.right, visitor);
    }

    /**
     * 栈实现前序遍历
     */
    private final Stack<Node<E>> stack = new Stack<>();

    public void preorderTraversalByStack(Visitor<E> visitor) {
        if (root == null) throw new IllegalArgumentException("Visitor不能为空");
        //将根元素入栈
        preorderTraversalByStack(root, visitor);
    }

    private void preorderTraversalByStack(Node<E> popNode, Visitor<E> visitor) {
        while (popNode != null) {
            //回调
            if (visitor.stop) return;
            visitor.stop = visitor.visit(popNode.element);
            //将其右结点入栈
            if (popNode.right != null) {
                stack.push(popNode.right);
            }
            //将其左结点入栈
            if (popNode.left != null) {
                stack.push(popNode.left);
            }
            //将栈顶元素出栈
            popNode = stack.isEmpty() ? null : stack.pop();
        }
    }

    /**
     * 中序遍历（递归实现）
     */
    public void inorderTraversal(Visitor<E> visitor) {
        if (visitor == null) throw new IllegalArgumentException("visitor不能为空");
        inorderTraversal(root, visitor);
    }

    private void inorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        inorderTraversal(node.left, visitor);
        //回调
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
        inorderTraversal(node.right, visitor);
    }

    /**
     * 中序栈实现
     */
    public void inorderTraversalByStack(Visitor<E> visitor) {
        if (root == null || visitor == null) throw new IllegalArgumentException("Visitor不能为空");
        inorderTraversalByStack(root, visitor);
    }

    private void inorderTraversalByStack(Node<E> popNode, Visitor<E> visitor) {
        //只要左结点存在就一直入栈
        while (true) {
            if (popNode != null) {
                stack.push(popNode);
                popNode = popNode.left;
            } else {
                if (stack.isEmpty()) return;
                popNode = stack.pop();
                //回调
                visitor.visit(popNode.element);
                popNode = popNode.right;
            }
        }
    }

    /**
     * 后序遍历
     */
    public void postorderTraversal(Visitor<E> visitor) {
        if (visitor == null) throw new IllegalArgumentException("visitor不能为空");
        postorderTraversal(root, visitor);
    }

    public void postorderTraversal(Node<E> node, Visitor<E> visitor) {
        if (node == null || visitor.stop) return;
        postorderTraversal(node.left, visitor);
        postorderTraversal(node.right, visitor);
        if (visitor.stop) return;
        visitor.stop = visitor.visit(node.element);
    }

    /**
     * 后序遍历非递归实现
     */
    public void postorderTraversalByStack() {
        postorderTraversalByStack(root);
    }

    public void postorderTraversalByStack(Node<E> root) {
        if (root == null) return;
        Stack<Node<E>> stack = new Stack<>();
        stack.push(root);
        Node<E> temp = null;
        while (!stack.isEmpty()) {
            temp = stack.pop();
            //业务逻辑
            System.out.println(temp);
            if (temp.left != null) {
                stack.push(temp.left);
            }
            if (temp.right != null) {
                stack.push(temp.right);
            }
        }
    }

    private boolean isLeafNode(Node<E> node) {
        System.out.println(node.element);
        return node.left == null && node.right == null;
    }

    private boolean isPeekStack(Node<E> node) {
        return stack.peek().left == node || stack.peek().right == node;
    }


    /**
     * 层序遍历接口暴露
     *
     * @param visitor 逻辑接口
     */
    public void leverOrderTraversal(Visitor<E> visitor) {
        if (visitor == null || root == null) throw new IllegalArgumentException("Visitor不能为空");
        Queue<Node<E>> queue = new LinkedList<>();
        //将根结点入队
        queue.offer(root);
        while (!queue.isEmpty()) {
            //出队
            Node<E> node = queue.poll();
            //回调，将处理遍历数据的业务交给调用者
            if (visitor.visit(node.element)) return;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }
    }

    /**
     * 找到当前结点的前驱结点
     */
    public Node<E> predecessor(Node<E> node) {
        if (node == null) throw new IllegalArgumentException("node不能为空");
        //前驱结点在左子树当中(left.right.right.......)
        Node<E> p = node.left;
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
    public Node<E> successor(Node<E> node) {
        if (node == null) throw new IllegalArgumentException("node不能为空");
        Node<E> p = node.right;
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

    @Override
    public Object root() {
        return root;
    }

    @Override
    public Object left(Object node) {
        return ((Node<E>) node).left;
    }

    @Override
    public Object right(Object node) {
        return ((Node<E>) node).right;
    }

    @Override
    public Object string(Object node) {
        return ((Node<E>) node).element;
    }
}