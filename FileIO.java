import java.io.*;
import java.util.Scanner;

class FileIO{
	public static void main(String args[]){
		//this try-catch statement is needed around this file input code
		//because the FileInputStream may throw a FileNotFoundException
		try {
			Scanner lineScanner = new Scanner(new FileInputStream("user1data.txt"));
			
			while (lineScanner.hasNext()) { //while more of the input file is still available for reading
											
				System.out.println("+++++++++++++++++++++++++++");
				String name = lineScanner.nextLine();  //reads an entire line of input
				System.out.println("Display Name is: " + name);
				
				System.out.println("+++++++++++++++++++++++++++");
				String email = lineScanner.nextLine();
				System.out.println("Username is: " + email);
				
				System.out.println("+++++++++++++++++++++++++++");
				String pass = lineScanner.nextLine();
				System.out.println("Password is: " + pass);
				
				System.out.println("+++++++++++++++++++++++++++");
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
				System.out.println("Posts are: ");
				while (postScanner.hasNext()){
					System.out.println("--------------------------");
					p = postScanner.next();
					p = p.substring(1, p.length()-1); //cut off the parens around each post
					System.out.println(p);
					postsArray[i] = p;
					i++;
					//here's some extra code to demonstrate how to further break down each event string
					System.out.println("(Now illustrating how to extract each piece of info about the post.)");
					Scanner pScanner = new Scanner(p); //yet another scanner just for this particular post
					pScanner.useDelimiter(",");
					System.out.println("Time stamp: " + pScanner.nextLong());				
					String desc = ""; //to hold the text of the post itself
					while (pScanner.hasNext()){ //while there are words left...
						desc = desc + " " + pScanner.next(); //reads the description one word at a time
						String[] post = desc.split("-", -1);
						System.out.println("Text of post: " + post[0]);
					}
					
				}				
				
				System.out.println("+++++++++++++++++++++++++++");				

				/* reads in next line and then breaks it into separate friends
				 * now the delimiter is just a comma because there are no quotes around
				 * each data item.  so this is a bit simpler than above procedure.*/
				String friends = lineScanner.nextLine();
				Scanner friendScanner = new Scanner(friends);
				String[] friendArray = new String[20]; 
				i = 0;
				friendScanner.useDelimiter(",");  
				String friend;
				System.out.print("Friends are: ");
				while (friendScanner.hasNext()) {
					friend = friendScanner.next();
					System.out.print(friend + ",");
					friendArray[i] = friend; //stores friend into array of friends
					//i don't do anything with this array, but it is here just to demonstrate
					//(you may or may not be using an array to store the list of friends.)
					i++;
				}
				System.out.println();
				System.out.println("+++++++++++++++++++++++++++");
				
				/** now the same for followers ... 
				    reads in next line and then breaks it into separate followers
				 * now the delimiter is just a comma because there are no quotes around
				 * each data item.  so this is a bit simpler than above procedure.*/
				String f = lineScanner.nextLine();
				Scanner fScanner = new Scanner(f);
				String[] fArray = new String[20]; 
				i = 0;
				fScanner.useDelimiter(",");  
				String fol;
				System.out.print("Followers are: ");
				while (fScanner.hasNext()) {
					fol = fScanner.next();
					System.out.print(fol + ",");
					fArray[i] = fol; //stores friend into array of friends
					//i don't do anything with this array, but it is here just to demonstrate
					//(you may or may not be using an array to store the list of friends.)
					i++;
				}
				System.out.println();
				System.out.println("+++++++++++++++++++++++++++");
				
				
			} //end while
	
		} catch(FileNotFoundException ex) {
			System.out.println("File not Found");
			System.exit(0);
		}
		
	
	}
}