import java.io.PrintStream;

public class BinarySearchTree <T extends Comparable<T>> implements SortedCollection<T> {

    protected BSTNode<T> root; // ptr to the root of the BST
    private int size;

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
                this.size++;
            }
            else {
                insertHelper(newNode, subtree.getLeft());
            }
        }
        else {
            if (subtree.getRight() == null) {
                subtree.setRight(newNode);
                this.size++;
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
        return this.size;
    }

    public boolean isEmpty() {
        return this.size == 0;
    }

    /**
     * Removes all nodes from the tree
     */
    public void clear() {
        this.root = null;
        this.size = 0;
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
            this.size--;
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
}
