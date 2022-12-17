import java.util.*;

/** 
 * Class definition for a Node of a singly linked list of string 
 */
class LLNode {

	/* Note: these fields are public so that they can be directly accessed
	 * from outside this class.  For example, if v is a Node object, 
	 * then v.entry and v.next can be used to access these fields. */
	 
	public String content;	//the high score entry 
	public LLNode next;		//refers to the next node in the list
	
	/** 
	 * Constructor: creates a node with the given string and next Node. 
	 */
	public LLNode(String e, LLNode n) {
		content = e;
		next = n;
	}
}

/**class definition of a singly linked list */
public class LinkedList{
	LLNode head;
	int size;

/**
 * Constructor that creates an empty list
 */
	public LinkedList(){
		head = null;
		size = 0;
	}

/** 
 * prints out all the game entries in the linked list
 */
	public void display(){
		LLNode current = head; //the current node
	
		while(current != null){

			System.out.println(current.content);
			current = current.next;
			

		}
	}
	/**return the size of the linked list */
	public int size(){
		return size;
	}

	/**return all the elements in the linked list as an array list so that each
	 * element can be easily accessed by indexing
	 */
	public ArrayList<String> getNodeContent(){
		LLNode current = head; //the current node
		ArrayList<String> nodeContents = new ArrayList<String>();
		while(current != null){
			nodeContents.add(current.content);
			current = current.next;

		}
		return nodeContents;
	}
/**
 * add a node to the head of the list
 * @param v
 * 		the Node object to be added
 */
	public void addFirst(String s){
		LLNode v = new LLNode(s, null);

		v.next = head;
		head = v; //adding new node
		size++;
	}
	public boolean isEmpty(){
		return (head == null);
	}
/**
* check if a node exists in the linked list or not
* @param String v
**/
	public boolean find(String v){
		LLNode current = head; //the current node
	
		while(current != null){

			if(current.content.equals(v)){
				return true;
			}
			current = current.next;
			
		}
		return false;
	}
}
