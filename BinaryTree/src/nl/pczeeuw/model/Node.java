/**
 * Node class.
 */
package nl.pczeeuw.model;

public class Node {
    
    private int value;
    private Node left, right;
    private static int COUNT;
    
    public Node (int value) {
	COUNT++;
	this.value = value;
    }
    
    /**
     * Recursive search function.
     * @param int -> value that needs to be searched.
     * @return Node -> ultimatly return either null (value not in tree) or the node containing the searcvh value.
     */
    Node findNode (int val) {
	if (this.value == val)
	    return this;
	else if (val > this.value && right != null)
	    return right.findNode(val);
	else if (val < this.value && left != null)
	    return left.findNode(val);
	else return null;
    }
    
    
    /**
     * Recursive fucntion for adding a node. If node has the same value as current node: Do not add the node and decrease the
     * counter by 1. The function keeps on going until it finds an empty child node to at the param node.
     * @param node to be added
     */
    void addNode (Node node) {
	
	if (node.value == value) {
	    COUNT--;
	} else if (node.value > value) {	    
	    if (right != null) 
		right.addNode(node);
	    else
		right = node;    
	} else if (node.value < value) {
	    if (left != null) 
		left.addNode(node);
	    else 
		left = node;
	}	
    }

    /**
     * Recursive funtion to print all the nodes.
     */
    void printNode () {
	System.out.println("This node value = " + value);
	if (left != null) {
	    System.out.print("Left - ");
	    left.printNode();
	}
	if (right != null) {
	    System.out.print("Right - ");
	    right.printNode();
	}
    }
    
    /**
     * 
     * @return the value of the current node
     */
    int getValue () {
	return value;
    }
    
    /**
     * 
     * @return the amount of nodes in the tree.
     */
    int getCOUNT () {
	return COUNT;
    }
    
    public Node getLeft () {
	return left;
    }
    
    public Node getRight () {
	return right;
    }
    
    public void setLeft (Node n) {
	left =n;
    }
    
    public void setRight (Node n) {
	right = n;
    }

    
}
