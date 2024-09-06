import java.io.PrintStream;

public class BinarySearchTree <T extends Comparable<T>> implements SortedCollection<T> {

    protected BSTNode<T> root; // ptr to the root of the BST

    /**
     * Adds a key to this BST; error if it is already there
     * @param key the new value being inserted
     */
    public void insert(T key) {
        if (this.root == null) {
            this.root = new BSTNode<>(key);
        }
        else {
            insertHelper(new BSTNode<T>(key), this.root);
        }
    }

    /**
     * Performs the native binary search tree insert algorithm to recursively
     * insert the provided newNode (which has already been initialized with a
     * data value) into the provided tree/subtree.  When the provided subtree
     * is null, this method does nothing.
     */
    protected void insertHelper(BSTNode<T> newNode, BSTNode<T> subtree) {
        if (subtree == null) {
            return;
        }
        if (newNode.getData().compareTo(subtree.getData()) <= 0) {
            if (subtree.getLeft() == null) {
                subtree.setLeft(newNode);
            }
            else {
                insertHelper(newNode, subtree.getLeft());
            }
        }
        else {
            if (subtree.getRight() == null) {
                subtree.setRight(newNode);
            }
            else {
                insertHelper(newNode, subtree.getRight());
            }
        }
    }

    /**
     * Checks if the tree contains a BSTNode with the data param as its own data
     * @param data the value to check for in the collection
     * @return boolean
     */
    public boolean contains(Comparable<T> data) {
        return contains(this.root, data);
    }

    private boolean contains(BSTNode<T> node, Comparable<T> data) {
        if (node == null) {
            return false;
        }
        if (node.getData().equals(data)) {
            return true;
        }
        if (data.compareTo(node.getData()) < 0) {
            return contains(node.getLeft(), data);
        }
        else {
            return contains(node.getRight(), data);
        }
    }

    public int size() {
        if (this.root == null) {
            return 0;
        }
        return this.size(this.root);
    }

    private int size(BSTNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1+ size(node.getLeft()) + size(node.getRight());
    }

    public boolean isEmpty() {
        return this.root == null;
    }

    /**
     * Removes all nodes from the tree
     */
    public void clear() {
        this.root = null;
    }

    /**
     * Removes the node containing key from this BST if it is there;
     * otherwise, do nothing
     * @param key the key to delete
     */
    public void delete(T key) {
        this.root = delete(this.root, key);
    }

    private BSTNode<T> delete(BSTNode<T> node, Comparable<T> key) {
        if (node == null) {
            return null;
        }
        if (key.equals(node.getData())) {
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            if (node.getLeft() == null) {
                return node.getRight();
            }
            if (node.getRight() == null) {
                return node.getLeft();
            }
            T small = smallest(node.getRight());
            node.setData(small);
            node.setRight(delete(node.getRight(), small));
            return node;
        }
        else if (key.compareTo(node.getData()) < 0) {
            node.setLeft(delete(node.getLeft(), key));
            return node;
        }
        else {
            node.setRight(delete(node.getRight(), key));
            return node;
        }
    }

    /**
     * Finds the furthest left node given a starting node
     * @param node the node to begin searching at
     * @return the data of the furthest left node
     */
    private T smallest(BSTNode<T> node) {
        if (node == null) {
            return null;
        }
        if (node.getLeft() == null) {
            return node.getData();
        }
        else {
            return smallest(node.getLeft());
        }
    }


    /**
     * If key is in this BST, returns true; otherwise, returns false
     * @param key the key to search for
     * @return boolean
     */
    public boolean lookup(T key) {
        return lookup(this.root, key);
    }

    private boolean lookup(BSTNode<T> node, T key) {
        if (node == null) {
            return false;
        }
        if (node.getData().equals(key)) {
            return true;
        }
        if (key.compareTo(node.getData()) < 0) {
            return lookup(node.getLeft(), key);
        }
        else {
            return lookup(node.getRight(), key);
        }
    }

    /**
     * Prints the values in this BST in sorted order (to p)
     * @param p the PrintStream to print to
     */
    public void print(PrintStream p) {
        p.print(this.root.toInOrderString());
    }

    /**
     * Inserting multiple values as both left and right children in different orders to create differently shaped trees.
     * Finding values that are both left and right leaves as well as values stored in the interior of a tree (including at the root position).
     * Ensuring that the size and clear methods are working through the building and clearing of a few different trees worth of data.
     * Each test should make use of differently shaped trees, and across your tests there should be at least one that holds Integers and one that holds Strings.
     * @return true or false depending on if the test passed
     */
    public boolean test1() {
        var bst = new BinarySearchTree<String>();
        bst.insert("a");
        if (bst.size() != 1) {
            return false;
        }
        bst.insert("b");
        bst.insert("c");
        bst.insert("d");
        if (bst.size() != 4) {
            return false;
        }
        bst.insert("e");

        bst.print(System.out);

        if (!bst.lookup("a") || !bst.lookup("b") || !bst.lookup("c") || !bst.lookup("d") || !bst.lookup("e")) {
            return false;
        }
        if (bst.size() != 5) {
            return false;
        }
        bst.clear();
        if (bst.size() != 0) {
            return false;
        }
        if (bst.lookup("a") || bst.lookup("b") || bst.lookup("c") || bst.lookup("d") || bst.lookup("e")) {
            return false;
        }
        return true;
    }

    public boolean test2() {
        var bst = new BinarySearchTree<Integer>();
        if (bst.size() != 0) {
            return false;
        }
        bst.insert(4);
        bst.insert(2);
        bst.insert(1);
        bst.insert(3);
        bst.insert(7);
        bst.insert(9);
        bst.insert(5);
        if (bst.size() != 7) {
            return false;
        }

        bst.print(System.out);

        if (!bst.lookup(1) || !bst.lookup(2) || !bst.lookup(3) || !bst.lookup(4)
                || !bst.lookup(5) || !bst.lookup(7) || !bst.lookup(9)) {
            return false;
        }

        bst.clear();
        if (bst.size() != 0) {
            return false;
        }
        if (bst.lookup(1) || bst.lookup(2) || bst.lookup(3) || bst.lookup(4)
                || bst.lookup(5) || bst.lookup(7) || bst.lookup(9)) {
            return false;
        }
        return true;
    }

    public boolean test3() {
        var bst = new BinarySearchTree<Integer>();
        if (bst.size() != 0) {
            return false;
        }
        bst.insert(13);
        bst.insert(9);
        bst.insert(5);
        bst.insert(12);
        bst.insert(16);
        bst.insert(19);
        bst.print(System.out);
        // tree structure is:
        //          13
        //      9       16
        //    5   12       19
        if (bst.size() != 6) {
            return false;
        }
        // leaf nodes using contains instead of lookup
        if (!bst.contains(5) || !bst.contains(12) || !bst.contains(19)) {
            return false;
        }
        // inner nodes using contains instead of lookup
        if (!bst.contains(9) || !bst.contains(13)) {
            return false;
        }
        bst.delete(5);
        // tree structure is:
        //          13
        //      9       16
        //       12       19
        bst.print(System.out);
        if (bst.contains(5)) {
            return false;
        }
        bst.delete(9);
        // tree structure is:
        //          13
        //      12      16
        //                 19
        bst.print(System.out);
        if (bst.contains(9)) {
            return false;
        }
        if (bst.size() != 4) {
            return false;
        }
        return true;
    }
}
