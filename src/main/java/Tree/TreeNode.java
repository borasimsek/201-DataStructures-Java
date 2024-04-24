package Tree;

import List.LinkedList;
import List.Node;

public class TreeNode {

    protected TreeNode left;
    protected TreeNode right;
    protected int data;

    public TreeNode(int data){
        this.data = data;
        this.left = null;
        this.right = null;
    }

    public TreeNode getLeft(){
        return left;
    }

    public TreeNode getRight(){
        return right;
    }

    public int getData(){
        return data;
    }

    public void setLeft(TreeNode left){
        this.left = left;
    }

    public void setRight(TreeNode right){
        this.right = right;
    }

    public TreeNode recursiveSearch(int value){
        if (data == value){
            return this;
        }
        if (value < data){
            if (left != null){
                return left.recursiveSearch(value);
            } else {
                return null;
            }
        } else {
            if (right != null){
                return right.recursiveSearch(value);
            } else {
                return null;
            }
        }
    }

    public TreeNode recursiveMinSearch(){
        if (left == null){
            return this;
        }
        return left.recursiveMinSearch();
    }

    public TreeNode recursiveMaxSearch(){
        if (right == null){
            return this;
        }
        return right.recursiveMaxSearch();
    }

    public void preorder(){
        System.out.println(data);
        if (left != null){
            left.preorder();
        }
        if (right != null){
            right.preorder();
        }
    }

    public void inorder(){
        if (left != null){
            left.inorder();
        }
        System.out.println(data);
        if (right != null){
            right.inorder();
        }
    }

    public void postorder(){
        if (left != null){
            left.postorder();
        }
        if (right != null){
            right.postorder();
        }
        System.out.println(data);
    }

    public void prettyPrint(int level){
        for (int i = 0; i < level; i++){
            System.out.print("\t");
        }
        System.out.println(data);
        if (left != null){
            left.prettyPrint(level + 1);
        }
        if (right != null){
            right.prettyPrint(level + 1);
        }
    }

    public void recursiveInsert(TreeNode node){
        if (node.getData() < data){
            if (left != null){
                left.recursiveInsert(node);
            } else {
                left = node;
            }
        } else {
            if (right != null){
                right.recursiveInsert(node);
            } else {
                right = node;
            }
        }
    }
    /*
     write me int average() method which returns the number of nodes in the tree that satisfy the following condition:
     the data of the node is equal to the average of the values of its children. Write it in a recursive way.
      So calculate the childern's average and compare it with the data of the node. If they are equal, return 1 +
      the number of nodes in the left subtree that satisfy the condition + the number of nodes in the right subtree that satisfy the condition.
      If they are not equal, return 0
        */
    public int average(){
        if (left == null && right == null){
            return 0;
        }
        int leftSum = 0;
        int rightSum = 0;
        if (left != null){
            leftSum = left.average();
        }
        if (right != null){
            rightSum = right.average();
        }
        if (leftSum == 0 && rightSum == 0){
            return 0;
        }
        if (leftSum == 0){
            if (right.getData() == rightSum){
                return 1 + rightSum;
            } else {
                return 0;
            }
        }
        if (rightSum == 0){
            if (left.getData() == leftSum){
                return 1 + leftSum;
            } else {
                return 0;
            }
        }
        if (left.getData() == leftSum && right.getData() == rightSum){
            return 1 + leftSum + rightSum;
        }
        return leftSum + rightSum;
    }
    /*
    Write a recursive method in TreeNode class that computes the sum
     of all keys that are less than X in a binary search tree. You are not
     allowed to use any tree methods, just attributes, constructors, getters
     and setters.
     int sumOfTree(int X)
     */
    public int sumOfTree(int X) {
        int sum = 0;
        if (data < X) {
            sum += data;
        }
        if (left != null) {
            sum += left.sumOfTree(X);
        }
        if (right != null) {
            sum += right.sumOfTree(X);
        }
        return sum;
    }
    /*
    Write a recursive method
     void pathList(LinkedList l)
     in the TreeNode class, which returns the keys on the path in the linked
     list l, where the path is defined by the current parent as follows: If the
     parent is odd, go left; otherwise go right. Assume that the function is
     called with an empty linked list for the root node.
     */
    public void pathList(LinkedList l) {
        l.insertFirst(new Node(data));
        if (data % 2 == 0) {
            if (right != null) {
                right.pathList(l);
            }
        } else {
            if (left != null) {
                left.pathList(l);
            }
        }
    }
    /*
    Write a recursive method in TreeNode class that finds the number of
     duplicate keys in a binary search tree. Assume that if a key is duplicate,
     it occurs at most twice. Hint: The duplicate of a key is either the
     maximum number on its left subtree or the minimum number on its
     right subtree.
     int numberOfDuplicates()
     */
    public int numberOfDuplicates() {
        int count = 0;
        if (left != null) {
            if (left.getData() == data) {
                count++;
            }
            count += left.numberOfDuplicates();
        }
        if (right != null) {
            if (right.getData() == data) {
                count++;
            }
            count += right.numberOfDuplicates();
        }
        return count;
    }

    /*
    Write a recursive method , which returns the number of nodes in the
     binary search tree which have value larger than X. Your method should
     run in O(logN +K) time, where N is total number of nodes and K is
     the number of nodes which have value larger than X in the tree. Do
     not use any class or external methods.
     int higherThanX(int X)
     */
    public int higherThanX(int X) {
        int count = 0;
        if (data > X) {
            count++;
        }
        if (left != null) {
            count += left.higherThanX(X);
        }
        if (right != null) {
            count += right.higherThanX(X);
        }
        return count;
    }
    /*
     Write a method that computes the products of all keys in a binary
     search tree.
     int productOfTree()
     */
    public int productOfTree() {
        int product = data;
        if (left != null) {
            product *= left.productOfTree();
        }
        if (right != null) {
            product *= right.productOfTree();
        }
        return product;
    }

    public static void main(String[] args) {
        // write me a TDM for average() method
        TreeNode root = new TreeNode(8);
        TreeNode node1 = new TreeNode(5);
        TreeNode node2 = new TreeNode(12);
        TreeNode node3 = new TreeNode(2);
        TreeNode node4 = new TreeNode(6);
        TreeNode node5 = new TreeNode(10);
        TreeNode node6 = new TreeNode(14);
        TreeNode node7 = new TreeNode(1);
        TreeNode node8 = new TreeNode(3);
        TreeNode node9 = new TreeNode(4);
        TreeNode node10 = new TreeNode(7);
        TreeNode node11 = new TreeNode(9);
        TreeNode node12 = new TreeNode(11);
        TreeNode node13 = new TreeNode(13);
        TreeNode node14 = new TreeNode(15);
        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        node3.setLeft(node7);
        node3.setRight(node8);
        node4.setLeft(node9);
        node4.setRight(node10);
        node5.setLeft(node11);
        node5.setRight(node12);
        node6.setLeft(node13);
        node6.setRight(node14);
        //System.out.println(root.average());
        // create me a TDD for pathList() method, create new tree too
        LinkedList l = new LinkedList();
        root.pathList(l);
        System.out.print(l.toString());

    }


    }




