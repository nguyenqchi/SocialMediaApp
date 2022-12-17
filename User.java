/**class definition of a User object */
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

	/**Method to set the user's name to display*/
	public void setDisplayName(String s){
		displayName = s;
	}

	/**Method to set the user's password*/
	public void setPassword(String s){
		password = s;
	}

	/**Method to check if the user entered the correct password*/
	public boolean checkPassword(String s){
		if(s.equals(password)){
			return true;
		}
		else{
			return false;
		}
	}
	/**method to add a new post
	 * @param String the content of the new post
	 */
	public Post addPost(String s){
		Post newpost = new Post(s, loginName);
		allPost.insert(newpost);
		return newpost;
	}
	/**method to display all the posts of a user in reverse-chronological order */
	public void displayAllPost(){
		if(allPost.root == null){
			System.out.println(loginName+" hasn't posted anything recently.");
		}
		else{
			allPost.getPostList(true);
		}
	}

	/**method to display all the accounts that follow the user */
	public void displayFollowers(){
		if(followers.isEmpty()){
			System.out.println("You don't have any followers.");
		}
		else{
			System.out.println("Your list of followers is: ");
			followers.display();
		}
	}
	/**method to display all the account that the user is following */
	public void displayFollowing(){
		if(following.isEmpty()){
			System.out.println("You are not following anyone");
		}
		else{
			System.out.println("You are following: ");
			following.display();
		}
		
	}
	
	/**method to add new follower */
	public void addFollower(String s){
		followers.addFirst(s);
	}
	/**method to add new following */
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
