/**
 * <h1>Binary Tree Class</h1>
 * BinaryTree implements a Binary Tree Data Structure using Nodes
 * <p></p>
 * This Tree can:
 * <ul>
 * <li>Print 3 traversals: pre-, in-, and post-order</li>
 * <li>Insert/Delete individual values</li>
 * <li>Find and display predecessor and successor of a given value</li>
 * </ul>
 * <p></p>
 * <b>Note:</b> All traversals, add, and delete were implemented using recursion.
 *
 * @author John Berkley
 *         CPP Class: CS 241
 *         Date Created: Apr 23, 2017
 */
public class BinaryTree {
    /**Root node of the Binary Tree*/
    private Node root;

    /**
     * Default Constructor. Assigns the root node to null.
     */
    public BinaryTree() {
        root = null;
    }

    /**
     * Prints Tree in pre-order style starting at the root node.
     */
    public void displayPreOrder() {
        displayPreOrder(root);
    }

    /**
     * Prints Tree in pre-order style starting at a given node.
     *
     * @param node local root node that the pre-order traversal begins at
     */
    public void displayPreOrder(Node node) {
        if (node != null) {
            System.out.print(node.data + " ");
            displayPreOrder(node.leftChild);
            displayPreOrder(node.rightChild);
        }
    }

    /**
     * Prints Tree in in-order style starting at the root node.
     */
    public void displayInOrder() {
        displayInOrder(root);
    }

    /**
     * Prints Tree in in-order style starting at a given node.
     *
     * @param node local root node that the in-order traversal begins at
     */
    public void displayInOrder(Node node) {
        if (node != null) {
            displayInOrder(node.leftChild);
            System.out.print(node.data + " ");
            displayInOrder(node.rightChild);
        }
    }

    /**
     * Prints Tree in post-order style starting at the root node.
     */
    public void displayPostOrder() {
        displayPostOrder(root);
    }

    /**
     * Prints Tree in post-order style starting at a given node.
     *
     * @param node local root node that the post-order traversal begins at
     */
    public void displayPostOrder(Node node) {
        if (node != null) {
            displayPostOrder(node.leftChild);
            displayPostOrder(node.rightChild);
            System.out.print(node.data + " ");
        }
    }

    /**
     * Add given integer to the specified Binary Tree, will not add duplicate values.
     *
     * @param data is inserted into the binary tree as a leaf node
     */
    public void insert(int data) {
        Node newNode = new Node();
        newNode.data = data;

        if (root == null) {
            root = newNode;
        } else {
            Node current = root;
            Node parent;
            while (true) {
                parent = current;
                if (data < current.data) {
                    current = current.leftChild;
                    if (current == null) {
                        parent.leftChild = newNode;
                        return;
                    }
                } else if (current.data < data){
                    current = current.rightChild;
                    if (current == null) {
                        parent.rightChild = newNode;
                        return;
                    }
                } else {
                    return;
                }
            }
        }
    }

    /**
     * Attempts to remove given integer value from the specified Binary Tree.
     *
     * @param key value to be deleted
     * @return true if key was deleted successfully, false if key could not be found
     */
    public boolean delete(int key) {
        Node current = root;
        Node parent = root;
        boolean isLeftChild = true;

        while (current.data != key) {
            parent = current;
            if (key < current.data) {
                isLeftChild = true;
                current = current.leftChild;
            } else {
                isLeftChild = false;
                current = current.rightChild;
            }
            if (current == null) {
                return false;
            }
        }

        if (current.leftChild == null && current.rightChild == null) {
            if (current == root) {
                root = null;
            } else if (isLeftChild) {
                parent.leftChild = null;
            } else {
                parent.rightChild = null;
            }
        } else if (current.leftChild == null) {
            if (current == root) {
                root = current.rightChild;
            } else if (isLeftChild) {
                parent.leftChild = current.rightChild;
            } else {
                parent.rightChild = current.rightChild;
            }
        } else if (current.rightChild == null) {
            if (current == root) {
                root = current.leftChild;
            } else if (isLeftChild) {
                parent.leftChild = current.leftChild;
            } else {
                parent.rightChild = current.leftChild;
            }
        } else {
            Node successor = getSuccessor(current);

            if (current == root) {
                root = successor;
            } else if (isLeftChild) {
                parent.leftChild = successor;
            } else {
                parent.rightChild = successor;
            }

            successor.leftChild = current.leftChild;
        }

        return true;
    }

    /**
     * Returns next highest value node after the given argument node.
     * <strong>For use in delete().</strong>
     *
     * @param node predecessor of needed in-order successor
     * @return in-order successor of the given node
     */
    private Node getSuccessor(Node node) {
        Node parent = node;
        Node successor = node;
        Node current = node.rightChild;
        while (current != null) {
            parent = successor;
            successor = current;
            current = current.leftChild;
        }

        if (successor != node.rightChild) {
            parent.leftChild = successor.rightChild;
            successor.rightChild = node.rightChild;
        }
        return successor;
    }

    /**
     * Returns node preceding node holding key value using in-order traversal.
     *
     * @param key value to find predecessor of
     * @return node preceding key value
     */
    public Node getPredecessor(int key) {
        Node keyNode = null;
        Node current = root;
        Node predecessor = null;

        while (current != null) {
            if (current.data == key) {
                keyNode = current;
                break;
            } else if (key < current.data) {
                current = current.leftChild;
            } else {
                predecessor = current;
                current = current.rightChild;
            }
        }
        if (keyNode == null) {
            return null;
        }
        if (keyNode.leftChild != null) {
            predecessor = current.leftChild;
            while (predecessor.rightChild != null) {
                predecessor = predecessor.rightChild;
            }
        }
        if (predecessor == null) {
            return null;
        }

        return predecessor;
    }

    /**
     * Returns node succeeding node holding key value using in-order traversal.
     *
     * @param key value to find successor of
     * @return node succeeding key value
     */
    public Node getSuccessor(int key) {
        Node keyNode = null;
        Node current = root;
        Node successor = null;

        while (current != null) {
            if (current.data == key) {
                keyNode = current;
                break;
            } else if (key < current.data) {
                current = current.leftChild;
                successor = current;
            } else {
                current = current.rightChild;
            }
        }
        if (keyNode == null) {
            return null;
        }
        if (keyNode.rightChild != null) {
            successor = current.rightChild;
            while (successor.leftChild != null) {
                successor = successor.leftChild;
            }
        }
        if (successor == null) {
            return null;
        }

        return successor;
    }
}
