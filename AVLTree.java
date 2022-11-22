/**Chloe Nguyen - COM 212
 * Project 4 - Bonus AVL Tree
 */

import java.io.*;
import java.util.*;
/**class to an AVL tree object to hold Post objects with the key being the timestamp of each post*/

public class AVLTree{
	TNode root;
	
	public AVLTree(){
		root = null;
		
	}
	/**inOrder traversal to display all the post in  chronological order
	 * @param TNode v the root of the tree
	 */
	public void inOrder(TNode v){
		if(v.right != null){
			inOrder(v.right);
		}
		v.displayNode();
		if(v.left != null){
			inOrder(v.left);
		}
	}
	/**helper method to insert a new Node recursively into a sub-tree
	 * @param Post p the new post that need to be inserted
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
	 * @param Post p the post that needed to be inserted
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

	/**method to get the most recent post in a tree
	 * @return TNode the node that holds the most recent post
	 */
	public TNode getRecentPost(){
		TNode v = getMax(root);
		return v;
	}


	/*------ method to maintain the balance of the AVL Tree ------*/

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

	
	
	

}//end of BST class

