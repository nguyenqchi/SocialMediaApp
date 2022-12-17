import java.util.*;    
/**class definition of a post object */
public class Post {
      private long timestamp; //the time stamp of the post
      String owner; 
      String content;
      private int numLikes;
      LinkedList replies = new LinkedList();

      //constructor for brand new post
      public Post(String c, String o){
            content = c;
            owner = o;
            numLikes = 0;
            timestamp = System.currentTimeMillis(); 
            
      }

      //constructor for posts that already have all the information(read from the text file)
      public Post(String c, String o, long t, int l){
            content = c;
            owner = o;
            numLikes = l;
            timestamp = t; 
            
      }
      /**return the number of likes of a post
       * @param int the number of likes of the post
      */
      public int getLike(){
            return numLikes;
      }
      /**increase the number of likes of a post */
      public void increaseLike(){
            numLikes ++;
      }
      /**display all the information of the post */
      public void displayPost(){
            System.out.println("-----"+owner+"-----");
            System.out.println(new Date(timestamp));
            System.out.println(content);
            System.out.println("Total Likes: "+ numLikes);
            displayReply();
            
      }
      /**return the time stamp of the post
       * @param long time stamp of the post
       */
      public long getTimestamp(){
            return timestamp;
      }

      /** method to add a reply to the post
       * @param s content of the reply
       * @param o name of the user replied to the post
       */
      public void addReply(String o, String s){
            replies.addFirst(o+"-"+s);
      }
      /**method to display all replies of a post
       */
      public void displayReply(){
            if(replies.isEmpty()){
                  System.out.println("The post has no reply yet");
            }
            else{
                  System.out.println("Total Replies: "+replies.size());
                  ArrayList<String> allReplies = replies.getNodeContent();
                  
                  for (int i = 0; i<allReplies.size(); i++){
                  	String[] owner_reply = allReplies.get(i).split("-", 2);
                  	System.out.println(owner_reply[0] +" replied to the post: "+owner_reply[1]);
                  }
            }


      }
      /**method to check if a post is expired or not
       * @return true if the post is more than a year(365) a ago
       * @return false if the post is within a year
       */
      public boolean isExpired(){
            int days = 365; //the expiration period of post is 365 days
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

      }

}     
