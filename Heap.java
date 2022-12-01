class Heap{
	private Post[] A;
	private int n;

	public Heap(int N){
		A=new Post[N];
		n=0;
	}

	public boolean isEmpty(){
		return n == 0;
	}

	public Post getMin(){
		return A[1];
	}

	public void insert(int k){
		A[n+1] = k;
		n = n +1;
		int child = n;
		while(child>1 && A[child]<A[parent(child)]){
			swap(child, parent(child));
			child = parent(child);

		}

	}

	private int leftChild(int i){
		return i * 2;
	}

	private int rightChild(int i){
		return i * 2 + 1;
	}

	private boolean isLeaf(int i){
		return leftChild(i)> n;
	}

	private boolean hasSmallerChild(int i){
		if(isLeaf(i)){
			return false;
		}
		if(A[i]>A[leftChild(i)] || A[i]>A[rightChild(i)]){
			return true;
		}
		return false;
	}

	public int extractMin(){
		swap(1, n)
		n --;
		p = 1;
		while(hasSmallerChild(p)){
			if(rightChild(p)>n){
				swap(p, leftChild(p));
				p = leftChild(p);

			}
			else{
				if(A[leftChild(p)]<A[rightChild(p)]){
					swap(p, leftChild(p));
					p = leftChild(p);
				} 
				else{
					swap(p, rightChild(p));
					p = rightChild(p);
				}
			}
		}
	}
}
