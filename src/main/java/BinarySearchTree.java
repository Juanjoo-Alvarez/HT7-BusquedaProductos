import java.util.ArrayList;
import java.util.List;

/**
 * Generic Binary Search Tree implementation.
 * Based on the Binary Search Tree implementation from Java Structures, Chapter 12.
 * @param <E> Type of elements stored in the tree, must implement Comparable
 */
public class BinarySearchTree<E extends Comparable<E>> {

    // Inner Node class
    protected class Node {
        protected E data; // the value stored in this node
        protected Node left; // left child
        protected Node right; // right child

        public Node(E data) {
            this.data = data;
            this.left = null;
            this.right = null;
        }
    }

    protected Node root; // root of the tree
    protected int size; // number of elements in the tree

    /**
     * Constructs an empty binary search tree.
     */
    public BinarySearchTree() {
        root = null;
        size = 0;
    }

    /**
     * Inserts an element into the tree.
     * @param value The element to insert
     */
    public void insert(E value) {
        root = insert(root, value);
        size++;
    }

    /**
     * Helper method for inserting a value into the tree.
     * @param node The current node
     * @param value The value to insert
     * @return The updated node
     */
    protected Node insert(Node node, E value) {
        if (node == null) {
            return new Node(value);
        }

        int compareResult = value.compareTo(node.data);

        if (compareResult < 0) {
            node.left = insert(node.left, value);
        } else if (compareResult > 0) {
            node.right = insert(node.right, value);
        } else {
            // If the value already exists, we can either replace it or ignore it
            // Here we replace it
            node.data = value;
        }

        return node;
    }

    /**
     * Searches for a value in the tree.
     * @param value The value to search for
     * @return The value if found, null otherwise
     */
    public E search(E value) {
        Node result = search(root, value);
        return (result == null) ? null : result.data;
    }

    /**
     * Helper method for searching a value in the tree.
     * @param node The current node
     * @param value The value to search for
     * @return The node containing the value if found, null otherwise
     */
    protected Node search(Node node, E value) {
        if (node == null) {
            return null;
        }

        int compareResult = value.compareTo(node.data);

        if (compareResult < 0) {
            return search(node.left, value);
        } else if (compareResult > 0) {
            return search(node.right, value);
        } else {
            return node;
        }
    }

    /**
     * Returns the number of elements in the tree.
     * @return The number of elements
     */
    public int size() {
        return size;
    }

    /**
     * Checks if the tree is empty.
     * @return True if the tree is empty, false otherwise
     */
    public boolean isEmpty() {
        return size == 0;
    }

    /**
     * Returns a list of all elements in the tree using in-order traversal.
     * @return A list of all elements
     */
    public List<E> inOrder() {
        List<E> result = new ArrayList<>();
        inOrder(root, result);
        return result;
    }

    /**
     * Helper method for in-order traversal.
     * @param node The current node
     * @param result The list to store the results
     */
    protected void inOrder(Node node, List<E> result) {
        if (node != null) {
            inOrder(node.left, result);
            result.add(node.data);
            inOrder(node.right, result);
        }
    }
}