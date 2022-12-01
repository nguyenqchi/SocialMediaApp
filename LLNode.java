/* Chloe Nguyen - COM 212 - Project 2 */
/** 
 * Class definition for a Node of a singly linked list of Game Entries. 
 */
public class LLNode {

	/* Note: these fields are public so that they can be directly accessed
	 * from outside this class.  For example, if v is a Node object, 
	 * then v.entry and v.next can be used to access these fields. */
	 
	public String content;	//the high score entry 
	public LLNode next;		//refers to the next node in the list
	
	/** 
	 * Constructor: creates a node with the given entry and next Node. 
	 */
	public LLNode(String e, LLNode n) {
		content = e;
		next = n;
	}
}