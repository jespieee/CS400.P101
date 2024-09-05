import java.io.PrintStream;

public class BinarySearchTree <T extends Comparable<T>> {

    protected BSTNode<T> root; // ptr to the root of the BST

    /**
     * Constructor that creates a new node with the value data. Both parent
     * and child references of the new node are initialized to null.
     * @param root the root node of the new tree
     */
    public BinarySearchTree(BSTNode<T> root) { this.root = null; }

    public void insert(T key) {
        return;
    }
    // add key to this BST; error if it is already there

    public void delete(T key) {
        return;
    }
    // remove the node containing key from this BST if it is there;
    // otherwise, do nothing

    public boolean lookup(T key) {
        return false;
    }
    // if key is in this BST, return true; otherwise, return false

    public void print(PrintStream p) {
        return;
    }
    // print the values in this BST in sorted order (to p)
}
