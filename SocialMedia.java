import java.io.*;
import java.util.*;

public class SocialMedia {

	private HashMap myMap = new HashMap(911);
	public static Scanner sc = new Scanner(System.in);
	public ArrayList<Post> followingPost = new ArrayList<Post>();
	Heap allpost = new Heap(1000);
	
	//will add all posts to the heap in the constructor
	public SocialMedia(){
		readFile();
	}
	public void displayMenu(){
		System.out.println();
		System.out.println("--- CC SNAP TWEET ---\n");
		System.out.println("a. Login to your account " );
		System.out.println("b. Create a new account ");
		System.out.println("c. Exit the program ");
		
		
	}
	
	public void newFeed(User me){
		System.out.println("\nWelcome, "+me.displayName+"\n");

		if(!me.allPost.isEmpty()){
			System.out.println("Your most recent post is: ");
			me.allPost.getRecentPost().displayNode();
		}
		else{
			System.out.println("You haven't posted anything recently");
		}
		if (allpost.isEmpty())
			System.out.println("There aren't any post in the system.");
		else { 
			System.out.println("\nThe current post with the most likes among all users of our app is:");
			System.out.print("1");
			allpost.getMin().displayPost();
			followingPost.add(0, allpost.getMin());
		}
		
		if(me.following.isEmpty()){
			System.out.println("\nNo post to display because you are not following anyone");
		}
		else{
			
			System.out.println("\nMost recent post of people you are following are:");
			LLNode cur = me.following.head;
			int i =1; 
			while(cur != null){
				User friend = myMap.get(cur.content);
				
				if(friend.allPost.isEmpty()){
					System.out.println("\nYour following accounts haven't posted anything recently");
					
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
		System.out.println("----- MAIN MENU -----");
		System.out.println("a. Like a post");
		System.out.println("b. Add a new post");
		System.out.println("c. View your list of followers");
		System.out.println("d. View your list of following");
		System.out.println("e. Follow a user");
		System.out.println("f. View your following's posts");
		System.out.println("g. Log out");
	}
	
	private void displayFollowingPost(User me){

		LinkedList following = me.following;
		System.out.println("\nDisplaying your following's recent posts");
		LLNode cur = following.head;
		while(cur != null){
			User f = myMap.get(cur.content);
			System.out.println("\nRecent Posts from "+f.displayName);
			f.allPost.getPostList(true);
			cur = cur.next;
		}

	}
	public void mainAction(User me){
		
		String option;
		do{	
			System.out.println();
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
					chosen.displayPost();
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
				System.out.println("New post is successfully created");
				newpost.displayPost();
				
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
					
					System.out.println("Enter a user who you want to view recent posts\nOr enter 0 to come back to main menu:");
					String name = sc.next();
					if(name.equals("0")){
						continue; 
					}
					User following = myMap.get(name);

					try{
						
						if(following.allPost.root == null){
							System.out.println(following.loginName+ " hasn't posted anything recently.");
							
						}
						else{	
							System.out.println("Recent posts of @" + following.loginName + " :"); 
							ArrayList<Post> followingPost = following.allPost.getPostList(true);
							System.out.println("Enter the post number you want to engage with\nOr enter 0 if you want to come back to main menu: ");
							int p = sc.nextInt();
							if(p == 0){
								continue;
							}
							try{
								Post chose = followingPost.get(p-1);
								
								String opt;
								do{
									
									System.out.println("a. Like the post \nb. Reply to "+ chose.owner+"\nc. Back to main menu");
									System.out.println("Choose an option: ");
									opt = sc.next();
									if(opt.equals("a")){
										chose.increaseLike();
										System.out.println("Successfully liked the post");
									}
									else if(opt.equals("b")){
										System.out.println("Reply to @"+chose.owner+" :");
										sc.nextLine();
										String reply = sc.nextLine();
										chose.addReply(me.loginName, reply);
									}
									else if(!(opt.equals("c")|| opt.equals("a")||opt.equals("b"))){
										System.out.println("Invalid Syntax");
									}

								}while(!(opt.equals("c")));
								
								
							} catch (Exception e){
								System.out.println("Invalid Input");
							}
						}
						
						
					}catch (Exception e){
						System.out.println("Invalid Input");
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
			}
			else if(option.equals("f")){
				displayFollowingPost(me);
			}
			else if (!(option.equals("a") || option.equals("b") || option.equals("c") || option.equals("d") || option.equals("e") || option.equals("f") || option.equals("g"))) {
				System.out.println("Please enter your option again!");
			}
				
		}
		while(!option.equals("g"));
	}

			
	
	public void createNewUser(String loginName, String passWord, String userName) {
		
		
		User user = new User(loginName);
		
		myMap.add(loginName, user);
		
		
		user.setPassword(passWord);
	
		user.setDisplayName(userName);
		System.out.println("New account successfully created!");
		
	
		
	}	

	public void writeFile() {
		try {
			FileWriter file = new FileWriter("user1data.txt");
			BufferedWriter buffWriter = new BufferedWriter(file);
			
			ArrayList<User> users = myMap.getValues();
			int size = myMap.size();
			for (int i=0; i<size; i++) {
				User u = users.get(i);
				String name = u.displayName;
				buffWriter.write(name + "\n");
				String login = u.loginName;
				buffWriter.write(login + "\n");
				String pass = u.password;
				buffWriter.write(pass + "\n");
				String p ;
				
				//write posts
				if(u.allPost.isEmpty())
					buffWriter.write("\n");
				else{
					ArrayList<Post> posts = u.allPost.getPostList(false);
					for (int k=0; k<posts.size(); k++) {
						 p = "";
						 Post post = posts.get(k);
						 p += post.getTimestamp() + "," + post.getLike() + ", ";
						 p += "\"" + post.content +"\"";
						 
						 ArrayList<String> allReplies = post.replies.getNodeContent();
						 if (allReplies.size() == 0)
						 	p += "";
						 else {
							 for (int j=0; j<allReplies.size(); j++) {
							 	p += ", \"" + allReplies.get(j) + "\"";
							 	
							 }
						}
						 
						if (k != posts.size()-1)
							buffWriter.write("(" + p + ");");
					
						else if (k == posts.size()-1) {
							buffWriter.write("(" + p + ")\n");
						}
					}
				}
					
				
				//write following
				if(u.following.isEmpty())
					buffWriter.write("\n");
				else{
				
					ArrayList<String> following = u.following.getNodeContent();
					for (int x=0; x<following.size(); x++) {
						String followingUser = following.get(x);
						if (x != following.size()-1)
							buffWriter.write(followingUser + ",");
					
						else {
							buffWriter.write(followingUser + "\n");
						}
					}
				}
				
				//write follower
				if (u.followers.isEmpty())
					buffWriter.write("\n");
				else{
					ArrayList<String> follower = u.followers.getNodeContent();
					for (int y=0; y<follower.size(); y++) {
						String followerUser = follower.get(y);
						if (y != follower.size()-1)
							buffWriter.write(followerUser + ",");
					
						else {
							buffWriter.write(followerUser + "\n");
						}
					}
				}
				
			}
			
					 
			buffWriter.close();
			
			file.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void readFile(){
		//this try-catch statement is needed around this file input code
		//because the FileInputStream may throw a FileNotFoundException
		try {
			Scanner lineScanner = new Scanner(new FileInputStream("user1data.txt"));
			
			while (lineScanner.hasNext()) { //while more of the input file is still available for reading
								
				//display name
				String name = lineScanner.nextLine();  //reads an entire line of input
				
				//login name
				String login = lineScanner.nextLine();
				
				//password
				String pass = lineScanner.nextLine();
				
				User newUser = new User(login);
				newUser.setDisplayName(name);	
				newUser.setPassword(pass);

			
				String posts = lineScanner.nextLine(); //read the entire line of posts
				//now create a secondary scanner to actually scan through this list of events
				// to break them up into individual events
				
				try (Scanner postScanner = new Scanner(posts)) {
					//on this line of data, events are in quotes and delimited by commas, 
					// so we tell the scanner to look for a close paren followed by a comma "),"
					// to delimit each post
					postScanner.useDelimiter(";"); //need the backslash in front of special characters like "
					String p; //will hold each individual post
					//System.out.println("Posts are: ");
					while (postScanner.hasNext()){
						
						p = postScanner.next();
						p = p.substring(1, p.length()-1); //cut off the parens around each post
						
						
						try (//here's some extra code to demonstrate how to further break down each event string
												//System.out.println("(Now illustrating how to extract each piece of info about the post.)");
						Scanner pScanner = new Scanner(p)) {
							pScanner.useDelimiter(",");

							long timestamp = pScanner.nextLong();
							int numLike = pScanner.nextInt();

							String desc = ""; //to hold the text of the post itself and all the replies of the post
							while (pScanner.hasNext()){ //while there are words left...
								desc = desc + " " + pScanner.next(); //reads the description one 
							}
							
							String[] post_replies = desc.split("\" ", -1);

							String content = post_replies[0].replaceAll("\"", "").replace("  ","");
							
							Post newPost = new Post(content, login, timestamp, numLike);
							if(newPost.isExpired()){
								continue; //skip the expired post
							}
							for(int j = 1; j<post_replies.length; j++){
								String[] reply = post_replies[j].split("-", -1);
							
								newPost.addReply(reply[0].replaceAll(" \"",""), reply[1].replaceAll("\"", ""));
							}
							
							newUser.allPost.insert(newPost); //add new post to user record
							allpost.insert(newPost); //add new post the system
						}
						
						
					}
				}
				
			
				/* reads in next line and then breaks it into separate friends
				 * now the delimiter is just a comma because there are no quotes around
				 * each data item.  so this is a bit simpler than above procedure.*/
				String following = lineScanner.nextLine();
				try (Scanner followingScanner = new Scanner(following)) {
					followingScanner.useDelimiter(",");  
					String follow;
					//System.out.print("Friends are: ");
					while (followingScanner.hasNext()) {
						follow = followingScanner.next().replaceAll("\\s", "");
						newUser.following.addFirst(follow); //add to the user's following list
						
					}
				}
				
				/** now the same for followers ... 
				    reads in next line and then breaks it into separate followers
				 * now the delimiter is just a comma because there are no quotes around
				 * each data item.  so this is a bit simpler than above procedure.*/
				String f = lineScanner.nextLine();
				try (Scanner fScanner = new Scanner(f)) {
					fScanner.useDelimiter(",");  
					String fol;
					//System.out.print("Followers are: ");
					while (fScanner.hasNext()) {
						fol = fScanner.next().replaceAll("\\s", "");		
						newUser.followers.addFirst(fol);			
					}
				}
				
				myMap.add(login, newUser);
			} //end while
			
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
			System.out.print("Please choose an option: ");
			option = sc.next(); 
			if(option.equals("b")){
				System.out.print("Please enter your login name: ");
				String loginName = sc.next();

				while (app.myMap.get(loginName) != null){
					//the login name is already existed
					System.out.print("The login name you chose already existed in our system.\nPlease enter a new login name: ");
					loginName = sc.next();

				}
				
				System.out.print("Please enter your password: ");
				String passWord = sc.next();
				System.out.print("Please enter your display name: ");
				sc.nextLine();
				String userName = sc.nextLine();
				app.createNewUser(loginName,passWord,userName);
			}
			else if(option.equals("a")){
				System.out.print("Please enter your login name: ");
				String loginName = sc.next();
				app.logIn(loginName);
	
			}
			else if(!(option.equals("a") || option.equals("b") || option.equals("c"))) 
				System.out.println("Invalid Input. Please choose again!");
			
		}while(!option.equals("c"));
		app.writeFile();
	}
		
}
			
			
			
		
		
