/**
 * Start here.
 */

package nl.pczeeuw.init;

import nl.pczeeuw.model.Node;
import nl.pczeeuw.model.NodeTree;

public class Main {
    
    public static void main (String [] args) {
	//Set size of tree
	int size = 100000;
	
	//The normal execution. Uncomment to execute.
	//normalExecution (size);
	
	//Times execution. Uncomment to execute.
	//timedExecution (size);
	
	//memoryExecution (size);
	
	altExecution (size);
	
    }
    
    /**
     * Normal execution.
     * Prints total amount of nodes on screen, and the result of a random search.
     * @param size
     */
    public static void normalExecution (int size) {
	//Generate tree
	NodeTree tree = generateTree (size);
	
	//print tree
	//tree.printTree();
	
	//Search a random val in tree
	searchInTree(tree, size);
    }
    
    public static void altExecution (int size) {
	NodeTree tree = altGenerateTree (size);
	System.out.println("Tree created!" + tree.getCOUNT() + " nodes");
	//tree.printTree();
	
	altSearchInTree(tree, 0);
    }
    
    public static void memoryExecution (int size) {
	long memoryStart = Runtime.getRuntime().freeMemory();
	normalExecution(size);
	long memoryUsed = Runtime.getRuntime().freeMemory() - memoryStart;
	System.out.println("Memory used: " + memoryUsed);
    }
    
    /**
     * Measures time of tree-creation and searches the tree @size times for a random value.
     * Prints the measurements in the console.
     * @param size
     */
    public static void timedExecution (int size) {
	
	//Generate tree and measure time
	long startT = System.currentTimeMillis();
	long memoryStart = Runtime.getRuntime().totalMemory();;
	System.out.println("Memory in JVM before creation and searching: " + (memoryStart / (1024 * 1024)) + "mb");
	
	NodeTree tree = generateTree (size);
	long creationTime = System.currentTimeMillis() - startT;
	
	//Start timer:
	startT = System.currentTimeMillis();
	long memoryUsedForSearch = 0;
	long memoryPreSearch = Runtime.getRuntime().totalMemory();
	
	System.out.println("Memory in JVM after creation and before searching" + (memoryPreSearch / (1024 * 1024)) + "mb");
	//Search tree and measure time of 'size' searches
	for (int i = 0; i < size; i++) {
	   timedSearchInTree(tree, size);
	   memoryUsedForSearch = Runtime.getRuntime().totalMemory() - memoryPreSearch;
	   if (i % (size/10) == 0) {
	       System.out.println("During search no" + i + " mem usage = " + (memoryUsedForSearch / (1024)) + "kb");
	   }
	}
		
	long memoryUsed = Runtime.getRuntime().totalMemory() - memoryStart;
	System.out.println("Memory used: " + memoryUsed / (1024 * 1024) + "mb");
	
	//Stop timer
	long searchTime = System.currentTimeMillis() - startT;
	
	//Print result:
	System.out.println("Time needed to create the tree with " + tree.getCOUNT() +
		" nodes: " + creationTime + "ms");
	System.out.println("Total time of " + size + " random searches: " + searchTime + "ms");
    }
    
    /**
     * Standard tree-creation fucntion.
     * @param size
     * @return
     */
    public static NodeTree generateTree (int size) {
	NodeTree tree = new NodeTree (new Node (size/2));
	
	for (int i=0;i<size;i++) {

	    tree.addNode(new Node((int) (Math.random() * size)));
	}
	System.out.println("Total amount of Nodes: " + tree.getCOUNT());
	return tree;
    }
    /**
     * Alternative tree creation. 
     * @param size
     * @return
     */
    public static NodeTree altGenerateTree (int size) {
	int floor = -(size/2) + 1;
	int ceiling = (size/2);
	NodeTree tree = new NodeTree (new Node (floor));		
	for (int i=floor; i < ceiling; i++) {
	    if (i % (size/10) == 0) 
		System.out.println("Adding node " + i + " and no exception so far!");
	    tree.addNodeIt(new Node (i));
	}
	
	return tree;
	
	
	    
	
	/*
	NewThread thr = new NewThread();
	thr.init(size);
	thr.run();
	return thr.getTree();
	*/
    }
    
    
    /**
     * Standard tree-search function. Print result in console.
     * @param tree
     * @param size
     */
    public static void searchInTree (NodeTree tree, int size) {
	String result = tree.searchTree((int) (Math.random() * size));
	System.out.println(result);
    }
    
    public static void altSearchInTree (NodeTree tree, int val) {
	String result = tree.searchTreeIt(val);
	System.out.println(result);
    }
    
    //Not needed.. 
    public static NodeTree timedGenerateTree (int size) {
	//Start timer
	long startTime = System.currentTimeMillis();
	
	//Make tree
	NodeTree res = generateTree (size);
	
	//Stop timer
	long estimatedTime = System.currentTimeMillis() - startTime;
	
	//Print time
	System.out.println("Created tree in " + estimatedTime + "ms");
	return res;
    }
    
    /**
     * (old) Timed fucntion, doesnt print a result to prevent console spamming.
     * @param tree
     * @param size
     */
    public static void timedSearchInTree (NodeTree tree, int size) {
	//Start timer <- Does not work, search is too fast to register in milliseconds
	//long startTime = System.currentTimeMillis();
	
	//Search
	tree.searchTree((int) (Math.random() * size));
	
	//Stop timer
	//long estimatedTime = System.currentTimeMillis() - startTime;
	
	//Print time
	//System.out.println("Searched tree in " + estimatedTime + "ms");
	
	//return estimatedTime;
    }
    
    public static class NewThread extends Thread {
	private NodeTree tree;
	private int size;
	
	public void init (int size) {	    
	    this.size = size;
	}

	@Override
	public void run() {
	    
	    int floor = -(size/2) + 1;
	    int ceiling = (size/2);
	    tree = new NodeTree (new Node (floor));		
	    for (int i=floor; i < ceiling; i++) {
		tree.addNode(new Node (i));
	    }	    
	}
	
	public NodeTree getTree () {
	    return tree;
	}


	
    }


}
