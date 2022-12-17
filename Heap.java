/**class definition of a priority queues holding all post in the system (the most liked post has the highest priority) */
class Heap{

	private Post[] A; //the list of posts in the heap
	private int n; //the number of items in the heap

	public Heap(int N){
		A=new Post[N];
		n=0;
	}
	/**method to check if the heap is empty
	 * @return true if the heap is empty
	 * @return false otherwise
	 */
	public boolean isEmpty(){
		return n == 0;
	}

	/**method to get the post with the minimum key (most likes)
	 * @return the post with most likes
	 */
	public Post getMin(){
		return A[1];
	}

	//modify the insert method so it can insert a post instead of a number
	/**method to insert a post into the heap with the key = number of likes
	 * @param Post p the post that needs to be inserted
	 */ 
	public void insert(Post p){
		A[n+1] = p;
		n =n +1;
		int child = n;

		//the key will be minus the number of likes of each post so that the most liked post 
		//will have the highest priority (stored in the first place)
		while(child>1 && (-A[child].getLike()< -A[parent(child)].getLike())){
			
			swap(child, parent(child));
			child = parent(child);

		}

	}

	/**method to swap two post given the index
	 * @param i, j the index of the two posts that need to be swapped
	 */
	private void swap(int i, int j){
		Post temp = A[j];
		A[j] = A[i];
		A[i] = temp;
	}
	/**method to return the index of the parent of a post*/
	private int parent(int i){
		return i/2;
	}

	public static void main(String[] args){
		Heap myHeap = new Heap(100);
		Post mypost = new Post("Keep Calm & Carry on!", "cnguyen", 1657793162248l, 20);
        Post mypost1 = new Post("Hello", "anahi");
        Post mypost2 = new Post("Be grateful!", "wsmith", 1607793162248l, 32);
        Post mypost3 = new Post("Happy Thanksgiving", "wtarimo", 1637793162248l, 35);
        Post mypost4 = new Post("Happy New Year", "tnguyen", 1648793162248l, 37);
        Post mypost5 = new Post("Happy New Year", "wnguyen", 1658793162248l, 5);
        mypost.displayPost();
        mypost1.displayPost();
        mypost2.displayPost();
        mypost3.displayPost();
        mypost4.displayPost();
        mypost5.displayPost();
        myHeap.insert(mypost1);
        myHeap.insert(mypost2);
        myHeap.insert(mypost3);
        myHeap.insert(mypost4);
        myHeap.insert(mypost);
        myHeap.insert(mypost5);
        System.out.println("The post that has the most likes");
        myHeap.getMin().displayPost();

	}
}
