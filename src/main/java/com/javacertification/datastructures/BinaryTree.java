package com.javacertification.datastructures;

/**
 * A binary tree is a data structure which is build from nodes that have at most two children.
 * In practice, each node stores a reference to two nodes: left and right node. Only one node,
 * called root node, is at the top of the tree. Leaf nodes which are at the bottom
 * of a tree contain null lef and right references.
 *
 * @param <T> the generic reference type of the class
 */
public class BinaryTree<T> {

    private Node root;

    /**
     * Constructor that converts a vararg into a {@link BinaryTree} object and initializes the root node.
     *
     * @param array the vararg
     */
    public BinaryTree(T... array) {
        root = convert(array, 0, array.length - 1);
    }

    public Node getRoot() {
        return root;
    }

    /**
     * Class representing a leaf composed of a right and left references.
     */
    public class Node {
        public final T value;
        Node right;

        Node left;
        private Node(T value, Node right, Node left) {
            this.value = value;
            this.right = right;
            this.left = left;
        }

    }

    /**
     * Receive the array's range of elements to be passed to the new {@link BinaryTree} object.
     *
     * @param array an array containing the elements to copy
     * @param start the initial index from which to start the copy
     * @param end the final index of the array's element to be copied
     *
     * @return a new {@link Node} object that represents the {@link BinaryTree}'s root
     */
    public Node convert(T[] array, int start, int end) {
        if (start > end) {
            return null;
        }
        int middle = (start + end) / 2;
        Node node = new Node(array[middle], null, null);
        node.left = convert(array, start, middle - 1);
        node.right = convert(array, middle + 1, end);
        return node;
    }
}
