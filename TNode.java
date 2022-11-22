/**Chloe Nguyen - COM 212
 * Project 4 - Binary Search Tree
 */

/**class of the Node object for an AVL tree*/
public class TNode{

	TNode parent;
	TNode left;
	TNode right;
	Post post; //the post that the node holds
	long key; //timestamp of the post
	
	public TNode(Post po, TNode p, TNode l, TNode r){
		parent = p;
		left = l;
		right = r;
		post = po;
		key = post.getTimestamp();
		System.out.println(key);
	
	}

	
	/**method to display the key of a node */
	public void displayNode(){
		
		post.displayPost();
	}

}