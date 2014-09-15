/**
 * Controller class. Set a root node in the constructor, and define some public functions to search/add/print the tree.
 */
package nl.pczeeuw.model;

public class NodeTree {
    private Node root;
    
    public NodeTree (Node root) {
	this.root = root;
    }
    
    /**
     * Add a node.
     * @param node
     */
    public void addNode (Node node) {
	root.addNode(node);    
    }
    
    public void addNodeIt (Node toAdd) {
	Node tmpNode = root;
	
	while (true) {    	
    	    if (toAdd.getValue() == tmpNode.getValue()) {
    		break;
    	    }else if (tmpNode.getValue() > toAdd.getValue()) {
    		if (tmpNode.getRight() == null) {
    		    tmpNode.setRight(toAdd);
    		} else {
    		    tmpNode = tmpNode.getRight();
    		    continue;
    		}
    	    } else if (tmpNode.getValue() < toAdd.getValue()) {
    		if (tmpNode.getLeft() == null) {
    		    tmpNode.setLeft(toAdd);
    		} else {
    		    tmpNode = tmpNode.getLeft();
    		    continue;
    		}
    	    }
	}
    }
    
    
    
    /**
     * Return number of nodes in tree.
     * @return
     */
    public int getCOUNT () {
	return root.getCOUNT();
    }
    
    /**
     * print the tree to the console
     */
    public void printTree () {
	root.printNode();
    }
    
    /**
     * 
     * @param val -> value to be searched
     * @return a string with the result (value found or value not found).
     */
    public String searchTree (int val) {
	Node result = root.findNode(val);
	if (result != null) {
	    return ("Found Node with value " + val);
	} else {
	    return ("There isn't a Node with value " + val);
	}
    }
    
    public String searchTreeIt (int val) {
	Node tmpNode = root;
	boolean res;
	
	while (true) {    	
    	    if (val == tmpNode.getValue()) {
    		res = true;
    		break;
    	    } else if (tmpNode.getValue() > val) {
    		if (tmpNode.getRight() == null) {
    		    res = false;
    		    break;
    		} else {
    		    tmpNode = tmpNode.getRight();
    		    continue;
    		}
    	    } else if (tmpNode.getValue() < val) {
    		if (tmpNode.getLeft() == null) {
    		    res = false;
		    break;
    		} else {
    		    tmpNode = tmpNode.getLeft();
    		    continue;
    		}
    	    }
	}
	if (res) 
	    return "Value " + val + " found!";
	else
	    return "Value " + val + "not found!";
	
    }

}
