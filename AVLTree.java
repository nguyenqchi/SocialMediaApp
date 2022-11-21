/**Chloe Nguyen - COM 212
 * Project 4 - Bonus AVL Tree
 */

import java.io.*;
import java.util.*;
/**class to an AVL tree object*/

public class AVLTree{
	TNode root;
	
	public AVLTree(){
		root = null;
		
	}

	/**helper method to insert a new Node recursively into a sub-tree
	 * @param long k the key of the new node
	 * @param TNode v the root of the sub-tree
	 */
	private void recinSert(Post p, TNode v){
		long k = p.getTimestamp();
		
		if( k > v.key){
			
			if(v.right != null){
				recinSert(p, v.right);
			}
			else{
				v.right = new TNode(p, v, null, null);
			}
		}
		else if( k <= v.key){
			
			if (v.left != null){
				recinSert(p, v.left);
			}
			else{
				v.left = new TNode(p, v, null, null);
			}
		}

		//balance the tree after inserting new node

		int bf = balanceFactor(v);
		if(bf > 1 || bf <-1){
			v = rebalance(v);
		}
	}
	//end of recinSert method

	/**method to insert a new Node with key k into the BST
	 * @param Post p the key of the new node
	 */
	public void insert(Post p){
	 	if(root == null){
	 		root = new TNode(p, null, null,null);
	 	}
	 	else{
	 		recinSert(p, root);
	 	}
	}
	//end of insert method
	 /**method to return the height of a node
	  * @param v a TNode
	  * @return the height of that node
	  */
	public static int height(TNode v){
	 	if(v == null){
	 		return 0;
	 	}

	 	return (1+Math.max(height(v.left), height(v.right)));
	}

	/**method to return the balance factor of a node
	 * @param TNode v a TNode
	 * @return the balance factor of that node
	 */
	public int balanceFactor(TNode v){
		return height(v.right) - height(v.left);
	}

	
	 /**method to find the node with the smallest key in a sub-tree
	  * @param TNode v the root of the sub-tree
	  * @return the node with maximum key 
	  */
	public TNode getMax(TNode v){
	 	if (v.right == null){
	 		return v;
	 	}
	 	else{
	 		return getMax(v.right);
	 	}
	}
	/**method to display the most recent post in a tree*/
	public void displayRecentPost(){
		TNode v = getMax(root);
		v.displayNode();
	}
	/**method to find the node with the smallest key in a sub-tree
	  * @param TNode v the root of the sub-tree
	  * @return the node with maximum key 
	  */
	private TNode getMin(TNode v){
	 	if (v.left == null){
	 		return v;
	 	}
	 	else{
	 		return getMin(v.left);
	 	}
	}
	 
	//  /**method to replace two nodes
	//   * @param TNode v the first node
	//   * @param TNode w the second node
	//   */
	//  private void replace(TNode v, TNode w){
	//  	if(v == root){
	//  		root = w;
	//  		if(w != null){
	//  			w.parent = null;
	//  		}

	//  	}
	//  	else{
	//  		if (v.parent.right == v){
	//  			v.parent.right = w;

	//  		}
	//  		else{
	//  			v.parent.left = w;
	//  		}
	//  		if (w != null){
	//  			w.parent = v.parent;
	//  		}
	//  	}
	//  }
	//  /**method to delete a Node with the target key in the tree
	//   * @param int k the target key
	//   * @return TNode v the removed node
	//   * @return null if k is not found
	//   */
	// public TNode deleteNode(TNode v){

	//  	//TNode v = find(k, root);
	 	
	//  	if(v.left == null && v.right == null){
	//  		//if v is a leaf
	 		
	//  		replace(v, null);
	//  	}
	//  	else if(v.left == null || v.right == null){
	 		
	//  		if(v.left != null){
	//  			TNode temp = v.left;
	//  			replace(v, v.left);
	 			
	 			
	//  		}
	//  		else{
	//  			TNode temp = v.right;
	//  			replace(v, v.right);
	 			
	//  		}
	//  	}
	//  	else{
	 		
	//  		//when v has two children
	 		
	//  		TNode s = getMin(v.right); //find the successor of v
	//  		v.key = s.key; //switching the key of the two node
	//  		replace(s, s.right);
	 		
	//  		//rebalance v right child where s is stored
	//  		while(s.parent != v){
	//  			int bf = balanceFactor(s.parent);
	//  			if (bf>1 || bf<-1){
	//  				rebalance(s.parent);
	//  			}
	//  			s = s.parent;
	//  		}
		 	
	//  	}

	//  	return v;
	// }
	// /**method to delete a node with a target key in a tree
	//  * @param int k the target key
	//  * @param TNode v the root of the tree
	//  */ 
	// public void delete(int k, TNode v){
	// 	if(v == null){
	// 		System.out.println("Couldn't find the target value");
	// 	}
	// 	else if (v.key > k){
	// 		delete(k, v.left);
	// 	}
	// 	else if(v.key < k){
	// 		delete(k, v.right);
	// 	}
	// 	else{
	// 		deleteNode(v);
		
	// 	}

	// 	//rebalance the tree after deleting

	// 	int bf = balanceFactor(v);
	// 	if(bf > 1 || bf <-1){
			
	// 		v = rebalance(v);
	// 	}

	// }
	/**method to rebalance a sub-tree
	 * @param TNode v the root of the tree
	 * @return TNode the new root of the tree after rebalancing
	 */
	private TNode rebalance(TNode v){
		TNode newroot = null;
		int bf = balanceFactor(v);
		System.out.println("Imbalance occurs at Node "+ v.key+" with bf = "+ bf);
		if (bf > 1){
			if(balanceFactor(v.right) != -1){
				newroot = leftRotate(v);
			}
			else if(balanceFactor(v.right) == -1){
				
				v.right = rightRotate(v.right);
				newroot = leftRotate(v);
			}
		}
		else if(bf < -1){
			if(balanceFactor(v.left) != 1){
				newroot = rightRotate(v);
			}
			else{
				v.left = leftRotate(v.left);
				newroot = rightRotate(v);
			}
		}
		return newroot;
	}
	/**left rotation method to balance the tree
	 * @param TNode the node that need to be rotated
	 * @return TNode the new root of the subtree that is rotated
	 */
	private TNode leftRotate(TNode v){
		System.out.println("Left Rotation");
		TNode u = v.right;
		v.right = u.left;
		//update u.left parent
		if(u.left != null){
			u.left.parent = v;
		}

		u.left = v;
		//update parents of u and v
		u.parent = v.parent;
		if(v == root){
			root = u;
		}
		else{
			if(v.parent.right == v){
				v.parent.right = u;
			}
			else{
				v.parent.left = u;
			}
		}
		v.parent = u;
		return u;
	}
	/**right rotation method to balance the tree
	 * @param TNode the node that need to be rotated
	 * @return TNode the new root of the subtree that is rotated
	 */
	private TNode rightRotate(TNode v){
		System.out.println("Right Rotation");
		TNode u = v.left;
		v.left = u.right;

		//update u.right parent
		if(u.right != null){
			u.right.parent = v;
		}

		
		u.right = v;
		u.parent = v.parent;
		if(v == root){
			root = u;
		}
		else{
			if(v.parent.right == v){
				v.parent.right = u;
			}
			else{
				v.parent.left = u;
			}
		}
		v.parent = u;
		return u;
	}

	//  /**method to display the Binary Search Tree */

	// public void displayTree(){

	// 	Stack<TNode> globalStack = new Stack<TNode>();
	// 	globalStack.push(root);
	// 	int nBlanks = 32;
	// 	boolean isRowEmpty = false;
	// 	System.out.println(
	// 	"......................................................");
	// 	while(isRowEmpty==false)
	// 	{
	// 		Stack<TNode> localStack = new Stack<TNode>();
	// 		isRowEmpty = true;

	// 		for(int j=0; j<nBlanks; j++)
	// 		System.out.print(' ');

	// 		while(globalStack.isEmpty()==false)
	// 		{
	// 			TNode temp = (TNode)globalStack.pop();
	// 			if(temp != null)
	// 			{
	// 				System.out.print(temp.key);
	// 				localStack.push(temp.left);
	// 				localStack.push(temp.right);

	// 				if(temp.left != null ||
	// 						temp.right != null)
	// 				isRowEmpty = false;
	// 			}
	// 			else
	// 			{
	// 				System.out.print("--");
	// 				localStack.push(null);
	// 				localStack.push(null);
	// 			}
	// 			for(int j=0; j<nBlanks*2-2; j++)
	// 			System.out.print(' ');
	// 		}  // end while
	// 		System.out.println();
	// 		nBlanks /= 2;
	// 		while(localStack.isEmpty()==false)
	// 		globalStack.push( localStack.pop() );
	// 	}  // end while isRowEmpty is false
	// 	System.out.println(
	// 	"......................................................");
	// }  // end displayTree() 


	// /**preOrder traversal 
	//  * @param TNode v the root of the tree
	//  */
	// public void preOrder(TNode v){
	// 	v.displayNode();
	// 	if(v.left != null){
	// 		preOrder(v.left);
	// 	}
	// 	if(v.right != null){
	// 		preOrder(v.right);
	// 	}
	// }

	/**inOrder traversal 
	 * @param TNode v the root of the tree
	 */
	public void inOrder(TNode v){

		if(v.left != null){
			inOrder(v.left);
		}
		v.displayNode();
		if(v.right != null){
			inOrder(v.right);
		}
	}
	// /**postOrder traversal 
	//  * @param TNode v the root of the tree
	//  */
	// public void postOrder(TNode v){

	// 	if(v.left != null){
	// 		postOrder(v.left);
	// 	}
	// 	if(v.right != null){
	// 		postOrder(v.right);
	// 	}
	// 	v.displayNode();
	// }

	// /**method to traverse the tree 
	//  * @param char traverseType traversal type
	//  */

	// public void traverse(char traverseType){
	// 	if(root == null){
	// 		System.out.println("There is nothing in the tree");
	// 	}
	// 	else{
	// 		switch(traverseType)
	// 		{
	// 			case 'p': System.out.print("\nPreorder traversal: ");
	// 				preOrder(root);
	// 				break;
	// 			case 'i': System.out.print("\nInorder traversal:  ");
	// 				inOrder(root);
	// 				break;
	// 			case 't': System.out.print("\nPostorder traversal: ");
	// 				postOrder(root);
	// 				break;
	// 		}
	// 		System.out.println();
	// 	}
	// }

}//end of BST class

//This code was adapted from Lafore Data Structures Text


// class TreeApp
// {

// 	public static void main(String[] args) throws IOException{
// 		int choice, value;
// 		AVLTree theTree = new AVLTree();

// 		theTree.insert(50);
// 		theTree.insert(25);
// 		theTree.insert(75);
// 		theTree.insert(12);
// 		theTree.insert(37);
		
// 		theTree.insert(43);
		
// 		theTree.insert(30);
// 		theTree.insert(33);
// 		theTree.insert(87);
// 		theTree.insert(93);
// 		theTree.insert(97);
		
		
// 		do
// 		{
// 			System.out.print("\nEnter a letter: \n\n quit program (q) \n show tree (s) \n ");
// 			System.out.print("insert (i) \n find (f) \n delete (d) \n traverse (t) \n\t\t --> ");
// 			choice = getChar();
// 			//to see how switch statements in java work, see:
// 			// http://www.cafeaulait.org/course/week2/42.html
// 			switch(choice)
// 			{
// 				case 's':
// 					theTree.displayTree();
// 					break;
// 				case 'i':
// 					System.out.print("\nEnter value to insert: ");
// 					value = getInt();
// 					theTree.insert(value);
// 					break;
// 				case 'f':
// 					System.out.print("\nEnter value to find: ");
// 					value = getInt();
// 					TNode found = theTree.find(value, theTree.root);
// 					if(found != null)
// 					{
// 						System.out.print("\nFound: ");
// 						found.displayNode();
// 						System.out.print("\n");
// 					}
// 					else 
// 					{
// 						System.out.print("\nCould not find ");
// 						System.out.print("" + value + '\n');
// 					}
// 					break;
// 				case 'd':
// 					System.out.print("\nEnter value to delete: ");
// 					value = getInt();
// 					try{
// 						theTree.delete(value, theTree.root);
// 						System.out.println("Deleted "+value);
// 					} catch(Exception e){
// 						System.out.println("Invalid Input");
// 					}
// 					break;
// 				case 't':
// 					System.out.print("\nEnter a letter: preorder (p), inorder (i), or postorder (t) --> ");
// 					char letter = getChar();
// 					theTree.traverse(letter);
// 					break;
// 				case 'q':
// 					System.out.println("\nGoodbye.");
// 					break;
// 				default:
// 					System.out.print("\nNot a valid entry.\n");
// 			}  // end switch
// 		} while(choice != 'q');
// 	}  // end main()
// 	// -------------------------------------------------------------
// 	public static String getString() throws IOException
// 	{
// 		InputStreamReader isr = new InputStreamReader(System.in);
// 		BufferedReader br = new BufferedReader(isr);
// 		String s = br.readLine();
// 		return s;
// 	}
// 	// -------------------------------------------------------------
// 	public static char getChar() throws IOException
// 	{
// 		String s = getString();
// 		return s.charAt(0);
// 	}
// 	//-------------------------------------------------------------
// 	public static int getInt() throws IOException
// 	{
// 		String s = getString();
// 		return Integer.parseInt(s);
// 	}
// 	// -------------------------------------------------------------
// }  // end class TreeApp

