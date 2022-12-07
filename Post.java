import java.sql.Timestamp;    
import java.util.Date;    


public class Post {
      private long timestamp;
      String owner;
      private String content;
      private int numLikes;
      private LinkedList replies = new LinkedList();

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
            System.out.println("Total Likes:"+ numLikes);
            System.out.println("Replies:");
            displayReply();
            
      }
      public long getTimestamp(){
            return timestamp;
      }
      /** method to add a reply to the post
       * @param s content of the reply
       * @param o name of the user replied to the post
       */
      public void addReply(String s, String o){
            replies.addFirst(o+" replied to your post: "+s);
      }
      /**method to display all replies of a post
       * 
       */

      public void displayReply(){
            if(replies.isEmpty()){
                  System.out.println("Your post has no reply yet");
            }
            else{
                  replies.display();
            }


      }
      /**method to check if a post is expired or not
       * @return true if the post is more than a year(365) a ago
       * @return false if the post is within a year
       */
      public boolean isExpired(){
            int days = 365;
            long diff = (System.currentTimeMillis()-getTimestamp())/(24*60*60*1000);
            //if the difference is smaller than 365 days, return true (the post is not expired yet)
            if(diff < days){
                  return false;
            }
            else{
                  return true;
            }

      }
      public static void main(String[] args){

            // Date mydate = new Date(122, 9, 30);
            // System.out.println(mydate.getTime());
            // System.out.println(mydate.toString());
            // long diff = (System.currentTimeMillis()-mydate.getTime())/(24*60*60*1000);

            // System.out.println(diff);

            Post mypost = new Post("Keep Calm & Carry on!", "cnguyen", 1657793162248l, 20);
            Post mypost1 = new Post("Hello", "anahi");
            mypost.displayPost();
            System.out.println("Expired" + mypost.isExpired());
            mypost1.displayPost();
            System.out.println("Expired" + mypost1.isExpired());
            Post mypost2 = new Post("Be grateful!", "wsmith", 1607793162248l, 32);
            mypost2.increaseLike();
            mypost2.displayPost();
            System.out.println("Expired" + mypost2.isExpired());
            Post mypost3 = new Post("Happy Thanksgiving", "wtarimo", 1636093162248l, 35);
            Post mypost4 = new Post("Happy New Year", "tnguyen", 1648793162248l, 37);
            Post mypost5 = new Post("Happy New Year", "wnguyen", 1658793162248l, 5);
            mypost5.addReply("I love you", "anahi");
            mypost5.addReply("Happy New Year", "william");
            mypost3.displayPost();
            System.out.println("Expired" + mypost3.isExpired());
            mypost4.displayPost();
            System.out.println("Expired" + mypost4.isExpired());
            mypost5.displayPost();
            System.out.println("Expired" + mypost5.isExpired());

            
            
            
            //test the Heap
            


      }
      


}     
