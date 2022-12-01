import java.util.ArrayList;

class HashNode{

	String key;
	User value;
	HashNode next;
	public HashNode(String loginname, User user){
		this.key = loginname;
		this.value = user;
	}

}


public class HashMap {
	private HashNode[] bucketArray;
	private int numBuckets;
	private int size;

	public HashMap(int cap){
		bucketArray = new HashNode[cap];
		numBuckets = 911;
		size = 0;

		for(int i= 0; i < numBuckets; i++){
			bucketArray[i] = null;
		}

	}

	public int size(){
		return size; 
	}

	public boolean isEmpty(){
		return size() == 0;
	}

	public long hashCode(HashNode e){
		String loginname = e.key;
		long hashVal = 0;
		for(int i = 0; i < loginname.length(); i++){
			hashVal = (hashVal * 26 + charCode(loginname.charAt(i))%size);

		}
		return hashVal;
	}
	private int charCode(char c) {

		char[] abc = {'a','b','c','d','e','f','g','h','i','j','k','l','m','n','o','p','q','r','s','t','u','v','w','x','y','z'};
		int index = binarySearch(c, abc);
		return index+1;
	}	
	private int binarySearch(char searchKey, char[] a){
		int low = 0;
		int hi = a.length - 1;
		int middle = 0; 
		while(low <= hi) {
			middle = (low + hi) / 2;
			if (a[middle] == searchKey){ //using binary search to see if the key is in the middle of the array
				return middle+1; //its in the middle! found it
			}
			else if (searchKey < a[middle]){
				hi = middle - 1;  //it was right part of the array
			} 
			else {
				low = middle + 1;  //it was in the left part of the array 
			}
		}
		return -1;
		

	}
}



	
