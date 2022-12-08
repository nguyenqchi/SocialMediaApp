import java.io.*;
import java.util.*;

public class SocialMedia {

	private HashMap myMap = new HashMap(911);
	public static Scanner sc = new Scanner(System.in);
	public List<Post> followingPost = new ArrayList<Post>();
	Heap allpost = new Heap(1000);
	
	//will add all posts to the heap in the constructor
	public SocialMedia(){
		readFile();
	}
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


	public void readFile(){
		//this try-catch statement is needed around this file input code
		//because the FileInputStream may throw a FileNotFoundException
		try {
			Scanner lineScanner = new Scanner(new FileInputStream("user1data.txt"));
			
			while (lineScanner.hasNext()) { //while more of the input file is still available for reading
								
				//System.out.println("+++++++++++++++++++++++++++");
				String name = lineScanner.nextLine();  //reads an entire line of input
				//System.out.println("Display Name is: " + name);
				
				//System.out.println("+++++++++++++++++++++++++++");
				String email = lineScanner.nextLine();
				//System.out.println("Username is: " + email);
				
				
				//System.out.println("+++++++++++++++++++++++++++");
				String pass = lineScanner.nextLine();
				//System.out.println("Password is: " + pass);
				
				User newUser = new User(email);
				newUser.setDisplayName(name);	
				newUser.setPassword(pass);

				//System.out.println("+++++++++++++++++++++++++++");
				String posts = lineScanner.nextLine(); //read the entire line of posts
				//now create a secondary scanner to actually scan through this list of events
				// to break them up into individual events
				System.out.println(posts);
				Scanner postScanner = new Scanner(posts);
				String[] postsArray = new String[10]; //will store the individual posts for now until you replace it with a BST
				int i = 0; //array index counter
				//on this line of data, events are in quotes and delimited by commas, 
				// so we tell the scanner to look for a close paren followed by a comma "),"
				// to delimit each post
				postScanner.useDelimiter(";"); //need the backslash in front of special characters like "
				String p; //will hold each individual post
				//System.out.println("Posts are: ");
				while (postScanner.hasNext()){
					//System.out.println("--------------------------");
					p = postScanner.next();
					p = p.substring(1, p.length()-1); //cut off the parens around each post
					System.out.println(p);
					postsArray[i] = p;
					i++;
					//here's some extra code to demonstrate how to further break down each event string
					//System.out.println("(Now illustrating how to extract each piece of info about the post.)");
					Scanner pScanner = new Scanner(p); //yet another scanner just for this particular post
					pScanner.useDelimiter(",");
					long timestamp = pScanner.nextLong();
					//System.out.println("Time stamp: " + timestamp);				
					String desc = ""; //to hold the text of the post itself
					while (pScanner.hasNext()){ //while there are words left...
						desc = desc + " " + pScanner.next(); //reads the description one word at a time
						
					}
					String[] post = desc.split("\"", -1);
					String content = post[1];
					int numLike = Integer.parseInt(post[2]);  
					Post newPost = new Post(content, email, timestamp, numLike);
					newUser.allPost.insert(newPost);
					//System.out.println("Text of post: " + post[1]+ "number of likes" + post[2]);
					
				}				
				
				//System.out.println("+++++++++++++++++++++++++++");				

				/* reads in next line and then breaks it into separate friends
				 * now the delimiter is just a comma because there are no quotes around
				 * each data item.  so this is a bit simpler than above procedure.*/
				String following = lineScanner.nextLine();
				Scanner followingScanner = new Scanner(following);
				//String[] followingArray = new String[20]; 
				i = 0;
				followingScanner.useDelimiter(",");  
				String follow;
				//System.out.print("Friends are: ");
				while (followingScanner.hasNext()) {
					follow = followingScanner.next().replaceAll("\\s", "");
					newUser.following.addFirst(follow);
					//System.out.print(follow+ ",");
					//friendArray[i] = friend; //stores friend into array of friends
					//i don't do anything with this array, but it is here just to demonstrate
					//(you may or may not be using an array to store the list of friends.)
					i++;
				}
				//System.out.println();
				//System.out.println("+++++++++++++++++++++++++++");
				
				/** now the same for followers ... 
				    reads in next line and then breaks it into separate followers
				 * now the delimiter is just a comma because there are no quotes around
				 * each data item.  so this is a bit simpler than above procedure.*/
				String f = lineScanner.nextLine();
				Scanner fScanner = new Scanner(f);
				//String[] fArray = new String[20]; 
				i = 0;
				fScanner.useDelimiter(",");  
				String fol;
				//System.out.print("Followers are: ");
				while (fScanner.hasNext()) {
					fol = fScanner.next().replaceAll("\\s", "");
					//System.out.print(fol + ",");
					newUser.followers.addFirst(fol);
					//fArray[i] = fol; //stores friend into array of friends
					//i don't do anything with this array, but it is here just to demonstrate
					//(you may or may not be using an array to store the list of friends.)
					i++;
				}
				//System.out.println();
				//System.out.println("+++++++++++++++++++++++++++");
				
				myMap.add(email, newUser);
			} //end while


			User check = myMap.get("kbergeron");
			//System.out.println("displayname"+check.displayName+" password"+check.password);
			check.allPost.getRecentPost().displayNode();
			check.following.display();
			check.followers.display();

	
		} catch(FileNotFoundException ex) {
			System.out.println("File not Found");
			System.exit(0);
		}
		
	
	}
	public static void main (String[] args){
	
		SocialMedia app = new SocialMedia();
		
		
		
		
		String option;
		do{
			app.displayMenu();
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
			
			
		}while(!option.equals("C"));
	}
		
}
			
			
			
		
		
