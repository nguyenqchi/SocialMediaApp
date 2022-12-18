import java.util.ArrayList;
/**class definition for a Node of a hash table*/
class HashNode{

	String key;
	User value;
	HashNode next;

	public HashNode(String loginname, User user){
		key = loginname;
		value = user;
	}

}
/**class definition for a hash table to hold all users in the app */
public class HashMap {

	private HashNode[] bucketArray; 
	private int capacity; //the capacity of the hash table
	private int size; //the actual size of the hash table
	ArrayList<User> values = new ArrayList<User>(); //array list to hold all values(user object) in the hash map
	
	/**constructor to create a hash map given the capacity*/
	public HashMap(int cap){
		bucketArray = new HashNode[cap]; //the array to hold all hash node
		capacity = cap; //capacity of the bucket array
		size = 0;//the current size of the bucket array

		for(int i= 0; i < capacity; i++){
			bucketArray[i] = null; //all the buckets are null at the beginning
		}

	}
	/**return the number of user objects in the hash table*/
	public int size(){
		return size; 
	}
	/**check if the hash table is empty*/
	public boolean isEmpty(){
		return size() == 0;
	}
	/**hash function using horner's hash method*/
	public long hashCode(String key){
		//hash using Horner's method
		long hashVal = 0;
		for(int i = 0; i < key.length(); i++){
			hashVal = (hashVal * 36 + charCode(key.charAt(i))%capacity);
			
		}
		return hashVal;
	}
	/**method to return letter/number into integer*/
	private int charCode(char c) {

		char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9'};
		int index = binarySearch(c, abc);
		
		return index+1;
	}	
	/**method to find the position of a character in an array*/
	private int binarySearch(char searchKey, char[] a){
		int low = 0;
		int hi = a.length - 1;
		int middle = 0; 
		while(low <= hi) {
			middle = (low + hi) / 2;
			if (a[middle] == searchKey){ //using binary search to see if the key is in the middle of the array
				return middle; //its in the middle! found it
			}
			else if (searchKey < a[middle]){
				hi = middle - 1;  //it was right part of the array
			} 
			else {
				low = middle + 1;  //it was in the left part of the array 
			}
		}
		return -1; //when it is not found
		

	}
	/**method to return the index of a hash node in the bucket array given the key*/
	private int getBucketIndex(String key){
        long hashCode = hashCode(key);
       
        long index = hashCode % capacity;

        return (int) index;
    }

    /** method to return the user object(value) given the login name (key)
     * @param String login name
     * @return the user object associated with the login name
     */
    public User get(String loginname){
    	// Find the index of the key in the bucket array
        int bucketIndex = getBucketIndex(loginname);

        // the first node in that bucket
        HashNode head = bucketArray[bucketIndex];
 		
        // loop through each item in the chain to find the node that have the target key
        while (head != null) {
            if (head.key.equals(loginname)) {
                return head.value; //return the user object
            }
            head = head.next;
        }
        return null; //when the login name does not exist in the hash table
    }

    /**method to add new Node into the hash map
     * @param String the login name of the user
     * @param User the user object associated with the login name
     */
    public void add(String key, User value){

        // Find head of chain for given key
        int bucketIndex = getBucketIndex(key);
        HashNode head = bucketArray[bucketIndex];
 		if(head == null){

 			bucketArray[bucketIndex] = new HashNode(key, value);

 		}
 		else{
	        while (head != null) {
	            if (head.key.equals(key)) {
	                //if the key (login name) is already present
	                System.out.println("The login name is already existed");
	                return; //end the method
	            }
	            head = head.next;
	        }
	        //add the new node to the hash table
	        head = new HashNode(key, value);
	    }
        //System.out.println("Successfully add new item in the hash table at bucket index"+ bucketIndex);
        values.add(value);
        size ++;
    }

    /**method to return all values(user object) in the hash map
     */
    public ArrayList<User> getValues(){
    	return values;
    }

    public static void main(String[] args){

    	//create some user objects to test
    	User m1 = new User("anahi");
    	User m2 = new User("chloe");
    	User m3 = new User("danna");
    	User m4 = new User("michelle");
    	HashMap myMap = new HashMap(911); 
    	myMap.add(m1.loginName, m1);
    	myMap.add(m3.loginName, m3);
    	myMap.add(m4.loginName, m4);
    	myMap.add(m4.loginName, m4); //will mark that the login name is already existed
    	myMap.add(m2.loginName, m2);

    	User michelle = myMap.get("michelle");
    	michelle.addPost("Christmas is comingggg!!");
    	michelle.displayAllPost();

    	System.out.println(myMap.getValues());

    } 

}



	
