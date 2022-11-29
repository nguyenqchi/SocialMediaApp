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


	//Method to set the user's name to display
	public void setDisplayName(String s){

	}

	//Method to set the user's password
	public void setPassword(String s){

	}

	//Method to check if the user entered the correct password
	public boolean checkPassword(String s){
		if(s == )
	}

	public void addPost(String s){
		Post newpost = new Post(s, loginName);
		allPost.insert(newpost);
	}
	public void displayAllPost(){
		if(allPost.root == null){
			System.out.println("You haven't posted anything recently.");
		}
		else{
			allPost.inOrder(allPost.root);
		}
	}
	public void displayFollowers(){
		if(followers.isEmpty()){
			System.out.println("You don't have any followers.");
		}
		else{
			System.out.println("Your list of followers is: ");
			followers.display();
		}
	}
	public void displayFollowing(){
		if(following.isEmpty()){
			System.out.println("You are not following anyone");
		}
		else{
			System.out.println("You are following: ");
			following.display();
		}
		
	}
	public void addFollower(String s){
		followers.addFirst(s);
	}
	public void addFollowing(String s){
		following.addFirst(s);
	}
	public static void main(String[] args){
		User me = new User("cnguyen2");
		me.addPost("Hello World");
		me.addPost("Merry Christmas");
		me.displayAllPost();
		me.addFollower("nle6");
		me.addFollowing("anahi");
		me.displayFollowers();
		me.displayFollowing();
	}


}