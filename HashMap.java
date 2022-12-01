import java.util.ArrayList;

public class HashNode{

	String key;
	User value;
	HashNode next;
	public HashNode(String loginname, User user>){
		this.key = element;
		this.value = value;
	}

}


public class HashMap<K,V> {
	private HashNode[] bucketArray;
	private int numBuckets;
	private int size;

	public HashMap(int cap){
		bucketArray = new HashNode[cap];
		numBuckets = 911;
		size = 0;

		for(int i= 0; i < numBuckets; i++){
			bucketArray.add(null);
		}

	}

	public int size(){
		return size; 
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	


	
}