public class User{
	
	String displayName;
	String loginName;
	String password;
	AVLTree allPost = new AVLTree();
	LinkedList followers = new LinkedList();
	LinkedList following = new LinkedList();

	
	public User(String name){
		loginName = name;
	}

	public void addPost(String s){
		Post newpost = new Post(s, loginName);
		allPost.insert(newpost);
	}
	public void displayAllPost(){
		allPost.inOrder(allPost.root);
	}
	public void displayFollowers(){
		followers.display();
	}
	public void displayFollowing(){
		following.display();
	}
	public static void main(String[] args){
		User me = new User("cnguyen2");
		me.addPost("Hello World");
		me.addPost("Merry Christmas");
		me.displayAllPost();
	}


}