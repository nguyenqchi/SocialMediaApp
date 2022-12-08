import java.io.*;
import java.util.*;

public class SocialMedia {

	private HashMap myMap = new HashMap(911);
	public static Scanner sc = new Scanner(System.in);
	public List<Post> followingPost = new ArrayList<Post>();
	Heap allpost = new Heap(1000);
	
	//will add all posts to the heap in the constructor
	
	public void displayMenu(){
	
		System.out.println("A. Login to your account: " );
		System.out.println("B. Create a new account: ");
		System.out.println("C. Exit the program: ");
		
		
	}
	
	public void newFeed(User me){
		if(!me.allPost.isEmpty()){
			me.allPost.getRecentPost().displayNode();
		}
		else{
			System.out.println("You haven't posted anything recently");
		}
		if (allpost.isEmpty())
			System.out.println("There aren't any post in the system.");
		else { 
			System.out.println("The current post with the most likes among all users of our app is:");
			System.out.print("1");
			allpost.getMin().displayPost();
			followingPost.add(0, allpost.getMin());
		}
		
		if(me.following.isEmpty()){
			System.out.println("No post to display because you are not following anyone");
		}
		else{
			
			System.out.println("Most recent post of people you are following are:");
			LLNode cur = me.following.head;
			int i =1; 
			while(cur != null){
				User friend = myMap.get(cur.content);
				
				if(friend.allPost.isEmpty()){
					System.out.println("The people who you are following haven't posted anything recently");
					
				}
				else{
					System.out.print(i+1);
					followingPost.add(i, friend.allPost.getRecentPost().post);
					friend.allPost.getRecentPost().displayNode();
					i++;
				}
				cur = cur.next;
			}
			
		}
	}
	
	
	public void logIn(String loginName) {
		User me = myMap.get(loginName);
		String pw;
		
		if(me == null){
			System.out.println("The login name you typed in is not existed in our system.");
		}
		else{
			System.out.print("password: " + me.password);
			do{
				System.out.print("Enter your password: ");
				pw = sc.next();
				
			}
			while(!me.checkPassword(pw));
			newFeed(me);
			mainAction(me);
		}
	}
	
	private void displayUserMenu(){
		System.out.println("----- Main Menu -----");
		System.out.println("a. Like a post");
		System.out.println("b. Add a new post");
		System.out.println("c. View your list of followers");
		System.out.println("d. View your list of following");
		System.out.println("e. Follow a user");
		System.out.println("f.Log out");
	}
	public void mainAction(User me){
		
		String option;
		do{
			displayUserMenu();
			System.out.print("Enter your option: ");
			option = sc.next();
			
			if(option.equals("a")){
				System.out.println("Enter the post number: ");
				int p = sc.nextInt();
				
				try{
					Post chosen = followingPost.get(p-1);
					chosen.increaseLike();
					System.out.println("Successfully liked the post");
				} catch (Exception e){
					System.out.println("Invalid Syntax");
				}
			}
			else if(option.equals("b")){
				System.out.print("Write something: ");
				sc.nextLine();
				String post = sc.nextLine();
				Post newpost = me.addPost(post);
				allpost.insert(newpost);
				
			}
			else if(option.equals("c")){
				me.displayFollowers();
			}
			else if(option.equals("d")){
				if(me.following.isEmpty()){
					System.out.println("You are not following anyone");
				}
				else{
					me.displayFollowing();
					
					System.out.println("Enter a user who you want to view recent posts: ");
					String name = sc.next();
					User following = myMap.get(name);
					System.out.println(following.loginName);
					try{
						
						if(following.allPost.root == null){
							System.out.println(following.loginName+ " hasn't posted anything recently.");
							
						}
						else{
							List<Post> followingPost = following.allPost.getPostList();
							System.out.println("Enter the post number you want to engage with or type 0 if you want to come back to main menu: ");
							int p = sc.nextInt();
							if(p == 0){
								return;
							}
							try{
								
								Post chose = followingPost.get(p-1);
								System.out.println("a. Like the post \nb. Reply to "+ chose.owner);
								String opt = sc.next();
								while(!(opt.equals("a")|| opt.equals("b"))){
									System.out.println("Invalid Syntax. Type again.");
									System.out.println("a. Like the post \nb. Reply to"+ chose.owner);
									opt = sc.next();
								}
								if(option.equals("a")){
									chose.increaseLike();
									System.out.println("Successfully liked the post");
								}
								else{
									System.out.println("Type your reply:");
									sc.nextLine();
									String reply = sc.nextLine();
									chose.addReply(me.loginName, reply);
								}
						
								
							} catch (Exception e){
								System.out.println("Invalid Syntax");
							}
						}
						
						
					}catch (Exception e){
						System.out.println("Invalid Syntax");
					}
				}
			}else if(option.equals("e")){
				System.out.println("Enter the username of the User you want to follow: ");
				String newFollowing = sc.next();
				User newFollow = myMap.get(newFollowing);
				if(newFollow == null){
					System.out.println("User not found.");
				}
				else{
					if(me.following.find(newFollowing)){
						System.out.println("You already follow this user");
					}
					else{
					
						me.addFollowing(newFollowing);
						newFollow.addFollower(me.loginName);
						System.out.println("Successfully follow "+newFollowing);
					}
				}
			}else{
				System.out.println("Please enter your option again!");
			}
				
		}
		while(!option.equals("f"));
	}

			
	
	public void createNewUser(String loginName, String passWord, String userName) {
		
		
		User user = new User(loginName);
		
		myMap.add(loginName, user);
		
		
		user.setPassword(passWord);
	
		user.setDisplayName(userName);
		System.out.println("New account successfully created!");
		
	
		
	}	
	public static void main (String[] args){
	
		SocialMedia app = new SocialMedia();
		
		app.displayMenu();
		
		
		
		String option;
		do{
			option = sc.next();
			if(option.equals("B")){
				System.out.print("Please enter your login name: ");
				String loginName = sc.next();
				System.out.print("Please enter your password: ");
				String passWord = sc.next();
				System.out.print("Please enter your name: ");
				String userName = sc.next();
				app.createNewUser(loginName,passWord,userName);
			}
			else if(option.equals("A")){
				System.out.print("Please enter your login name: ");
				String loginName = sc.next();
				app.logIn(loginName);
	
			}
			
			app.displayMenu();
		}while(!option.equals("C"));
	}
		
}
			
			
			
		
		
