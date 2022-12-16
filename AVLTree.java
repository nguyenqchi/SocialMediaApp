

import java.util.*;
 
 /**class to an AVL tree object to hold Post objects with the key being the timestamp of each post*/
 
 public class AVLTree{
	 TNode root;
	 int size;
	 ArrayList<Post> allPost; //array list ot hold all the post in the tree
	 public AVLTree(){
		 root = null;
		 size = 0;
		 
	 }
	 //***BONUS BONUS BONUS inorder traversal go from the right child first then
	 //the left child so that the post can be displayed in reverse-chronological order
 
	 /**inOrder traversal to display all the post in  chronological order
	  * @param TNode v the root of the tree
	  * reverse-chronological order
	  */
	 public void inOrder(TNode v, List<Post> allPost){
		 if(v.right != null){
			 inOrder(v.right, allPost);
		 
		 }
		 
		 allPost.add(v.post);
	 
	 
		 if(v.left != null){
			 inOrder(v.left, allPost);
		 
		 }
		 
	 }
	 public ArrayList<Post> getPostList(boolean printPost){
	 	 if(isEmpty()){
	 	 	System.out.println("This user hasn't posted anything lately");
	 	 	return null;
	 	 }
		 ArrayList<Post> allpost = new ArrayList<Post>();
		 inOrder(root, allpost);
		 if(printPost){
			 for (int i = 0; i<allpost.size(); i++){
				 System.out.print(i+1);
				 allpost.get(i).displayPost();
				 System.out.println();
			 }
		 }
		 return allpost;
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
		  size ++;
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
	 
	 public  boolean isEmpty(){
		 return (root == null);
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
 
	 public static void main(String[] args){
		 Post mypost = new Post("Keep Calm & Carry on!", "cnguyen", 1657793162248l, 20);
		 Post mypost1 = new Post("Hello", "anahi");
		 Post mypost2 = new Post("Be grateful!", "wsmith", 1607793162248l, 32);
		 Post mypost3 = new Post("Happy Thanksgiving", "wtarimo", 1637793162248l, 35);
		 Post mypost4 = new Post("Happy New Year", "tnguyen", 1648793162248l, 37);
		 Post mypost5 = new Post("Happy New Year", "wnguyen", 1658793162248l, 5);
		 
		 AVLTree myTree = new AVLTree();
		 myTree.insert(mypost1);
		 myTree.insert(mypost2);
		 myTree.insert(mypost3);
		 myTree.insert(mypost4);
		 myTree.insert(mypost);
  
 
		 //display all the post chronological order
		 ArrayList<Post> recent = myTree.getPostList(true);
 
		 System.out.println("The most recent post is");
		 myTree.getRecentPost().displayNode();
	 }
	 
	 
 
 }//end of AVLTree class
 
 