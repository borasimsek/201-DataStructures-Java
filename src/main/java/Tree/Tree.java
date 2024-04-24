package Tree;

import List.LinkedList;

public class Tree {

    final static int LEFT = 0;
    final static int RIGHT = 1;

    protected TreeNode root;

    public Tree(){
        root = null;
    }

    public TreeNode getRoot(){
        return root;
    }

    public void setRoot(TreeNode root){
        this.root = root;
    }

    public TreeNode recursiveSearch(int value){
        if (root != null){
            return root.recursiveSearch(value);
        } else {
            return null;
        }
    }

    public TreeNode iterativeSearch(int value){
        TreeNode tmp = root;
        while (tmp != null){
            if (value < tmp.getData()){
                tmp = tmp.getLeft();
            } else {
                if (value > tmp.getData()){
                    tmp = tmp.getRight();
                } else {
                    return tmp;
                }
            }
        }
        return null;
    }

    public TreeNode iterativeMinSearch(){
        TreeNode tmp = root;
        TreeNode parent = null;
        while (tmp != null) {
            parent = tmp;
            tmp = tmp.getLeft();
        }
        return parent;
    }

    public TreeNode iterativeMaxSearch(){
        TreeNode tmp = root;
        while (tmp != null) {
            if (tmp.getRight() == null){
                return tmp;
            }
            tmp = tmp.getRight();
        }
        return null;
    }

    public TreeNode recursiveMinSearch(){
        if (root != null){
            return root.recursiveMinSearch();
        }
        return null;
    }

    public TreeNode recursiveMaxSearch(){
        if (root != null){
            return root.recursiveMaxSearch();
        }
        return null;
    }

    protected TreeNode getParent(TreeNode node){
        TreeNode x = root, parent = null;
        while (x != node){
            parent = x;
            if (x.data > node.data){
                x = x.left;
            } else {
                x = x.right;
            }
        }
        return parent;
    }

    public void delete(int value){
        TreeNode y, x = root, parent;
        while (x.data != value){
            if (x.data > value){
                x = x.left;
            } else {
                x = x.right;
            }
        }
        parent = getParent(x);
        while (true){
            if (x.left != null){
                y = x.left.recursiveMaxSearch();
                parent = getParent(y);
            } else {
                if (x.right != null){
                    y = x.right.recursiveMinSearch();
                    parent = getParent(y);
                } else {
                    if (parent == null){
                        root = null;
                    } else {
                        if (parent.left == x){
                            parent.left = null;
                        } else {
                            parent.right = null;
                        }
                    }
                    break;
                }
            }
            x.data = y.data;
            x = y;
        }
    }

    public void inorder(){
        if (root != null){
            root.inorder();
        }
    }

    public void preorder(){
        if (root != null){
            root.preorder();
        }
    }

    public void postorder(){
        if (root != null){
            root.postorder();
        }
    }

    protected void insertChild(TreeNode parent, TreeNode child){
        if (parent == null) {
            root = child;
        } else {
            if (child.data < parent.data) {
                parent.left = child;
            } else {
                parent.right = child;
            }
        }
    }

    public void iterativeInsert(TreeNode node){
        TreeNode parent = null;
        TreeNode tmp = root;
        while (tmp != null) {
            parent = tmp;
            if (node.getData() < tmp.getData()){
                tmp = tmp.getLeft();
            } else {
                tmp = tmp.getRight();
            }
        }
        insertChild(parent, node);
    }

    public void recursiveInsert(TreeNode node){
        if (root == null){
            root = node;
        } else {
            root.recursiveInsert(node);
        }
    }

    public void prettyPrint(){
        if (root != null){
            root.prettyPrint(0);
        }
    }

    public int nodeCountWithStack(){
        TreeNode tmp;
        int count = 0;
        Stack c = new Stack(100);
        if (root != null){
            c.push(new Element(root));
        }
        while (!c.isEmpty()){
            Element e = c.pop();
            count++;
            tmp = e.getData();
            if (tmp.getLeft() != null){
                c.push(new Element(tmp.getLeft()));
            }
            if (tmp.getRight() != null){
                c.push(new Element(tmp.getRight()));
            }
        }
        return count;
    }

    public int nodeCountWithQueue(){
        TreeNode tmp;
        int count = 0;
        Queue c = new Queue(100);
        if (root != null){
            c.enqueue(new Element(root));
        }
        while (!c.isEmpty()){
            Element e = c.dequeue();
            count++;
            tmp = e.getData();
            if (tmp.getLeft() != null){
                c.enqueue(new Element(tmp.getLeft()));
            }
            if (tmp.getRight() != null){
                c.enqueue(new Element(tmp.getRight()));
            }
        }
        return count;
    }
    /*
    Write a non-recursive method int sumofPath(String path) which returns the sum of the values of the nodes in the path.
    path consists of 0 and 1. 0 means left and 1 means right. The path starts from the root. Use charAt method of String class.
     */
    public int sumOfPath(String path) {
        TreeNode tmp = root;
        int sum = tmp.getData();
        for (int i = 0; i < path.length(); i++) {
            if (path.charAt(i) == '0') {
                tmp = tmp.getLeft();
            } else {
                tmp = tmp.getRight();
            }
            sum += tmp.getData();
        }
        return sum;
    }
    /*
    Write a non-recursive method in Tree class that returns the depth of
    the node containing a given data X in a binary search tree. You are not
    allowed to use any tree methods, just attributes, constructors, getters
    and setters.
     */
    public int depth(int x) {
        TreeNode tmp = root;
        int depth = 0;
        while (tmp != null) {
            if (tmp.getData() == x) {
                return depth;
            }
            if (tmp.getData() > x) {
                tmp = tmp.getLeft();
            } else {
                tmp = tmp.getRight();
            }
            depth++;
        }
        return -1;
    }


}
