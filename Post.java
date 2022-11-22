import java.sql.Timestamp;    
import java.util.Date;    

public class Post {
      private long timestamp;
      private String owner;
      private String content;
      private int numLikes;

      //constructor for brand new post
      public Post(String c, String o){
            content = c;
            owner = o;
            numLikes = 0;
            timestamp = System.currentTimeMillis(); 
            
      }

      //constructor for posts that already have all the information
      public Post(String c, String o, long t, int l){
            content = c;
            owner = o;
            numLikes = l;
            timestamp = t; 
            
            
      }
      public int getLike(){
            return numLikes;
      }
      public void increaseLike(){
            numLikes ++;
      }
      public void displayPost(){
            System.out.println("-----"+owner+"-----");
            System.out.println(new Date(timestamp));
            System.out.println(content);
            System.out.println("Total Likes:"+ numLikes+"\n");
      }
      public long getTimestamp(){
            return timestamp;
      }
      public static void main(String[] args){
            Post mypost = new Post("Keep Calm & Carry on!", "cnguyen", 1657793162248l, 20);
            Post mypost1 = new Post("Hello", "anahi");
            // mypost.displayPost();
            // post1.displayPost();
            // mypost.increaseLike();
            // mypost.displayPost();
            Post mypost2 = new Post("Be grateful!", "wsmith", 1607793162248l, 32);
            mypost2.increaseLike();
            //mypost2.displayPost();
            Post mypost3 = new Post("Happy Thanksgiving", "wtarimo", 1637793162248l, 35);
            Post mypost4 = new Post("Happy New Year", "tnguyen", 1648793162248l, 35);
            Post mypost5 = new Post("Happy New Year", "wnguyen", 1658793162248l, 35);
            AVLTree myTree = new AVLTree();
            myTree.insert(mypost1);
            myTree.insert(mypost2);
            myTree.insert(mypost3);
            myTree.insert(mypost4);
            myTree.insert(mypost);
            //System.out.println(myTree.root + "Root");
            //myTree.root.displayNode();

            //display all the post chronological order
            myTree.inOrder(myTree.root);

            // System.out.println("The most recent post is");
            // myTree.getRecentPost().displayNode();

      }
      


}     