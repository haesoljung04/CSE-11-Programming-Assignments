/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * 
 * This file is used to complete a part of Programming 
 * Assignment 6
 */

// Imported Libraries
import java.util.ArrayList;

/**
 * This class implements the features of a post in Reddit,
 * where that post can be a original post or a comment.
 */
public class Post {

    /** Constants (Magic Numbers) */
    private static final String LEFT_BRACKET = "[";
    private static final String RIGHT_BRACKET = "]";
    private static final String DIVIDER = "|";
    private static final String TAB = "\t";
    private static final String NEWLINE = "\n";

    /** Instance variables */
    private String title;
    private String content;
    private Post replyTo;
    private User author;
    private int upvoteCount;
    private int downvoteCount;

    /**
     * The constructor used to initialize an original post.
     * This also sets the default upvote count to 1 and the
     * default downvote count to 0.
     * @param title   The title of the post
     * @param content What the post will contain
     * @param author  The name of the user who wrote the post
     */
    public Post(String title, String content, User author) {
        this.title = title;
        this.content = content;
        this.author = author;
        this.replyTo = null;
        upvoteCount = 1;
        downvoteCount = 0;
    }

    /**
     * The constructor used to initialize a comment. This also sets
     * the default upvote couunt to 1 and the default downvote count
     * to 0.
     * @param content  What the post will contain
     * @param replyTo  The post that this comment is replying to
     * @param author   The name of the user who wrote this comment
     */
    public Post(String content, Post replyTo, User author) {
        this.title = null;
        this.content = content;
        this.replyTo = replyTo;
        this.author = author;
        upvoteCount = 1;
        downvoteCount = 0;
    }

    /**
     * Method that returns the title of a post. If the object being called
     * is a comment then the title is null.
     * @return this.title  The title of the object being referred to.
     */
    public String getTitle() {
        return this.title;
    }
    
    /**
     * Method that returns the post that the comment is replying to.
     * If the object being called is an original post then the replyTo
     * is null
     * @return this.replyTo  The post being replied to
     */
    public Post getReplyTo() {
        return this.replyTo;
    }

    /**
     * Method that returns the name of the user who wrote the post.
     * @return this.author  The name of the author
     */
    public User getAuthor() {
        return this.author;
    }
    /**
     * Method that returns the number of upvotes that the post being
     * called has
     * @return upvoteCount the amount of upvotes 
     */
    public int getUpvoteCount() {
        return upvoteCount;
    }

    /**
     * Method that returns the number of downvotes that the post being
     * called has
     * @return downvoteCount the amount of downvotes 
     */
    public int getDownvoteCount() {
        return downvoteCount;
    }

    /**
     * Method that increments upvote count by 1 if isIncrement is true
     * and decrements upvote count by 1 is isIncrement is false
     * @param isIncrement  A boolean value either true or false
     */
    public void updateUpvoteCount(boolean isIncrement) {
        if (isIncrement) {
            upvoteCount++;
        }
        else {
            upvoteCount--;
        }
    }

    /**
     * Method that increments downvote count by 1 if isIncrement is true
     * and decrements downvote count by 1 if isIncrement is false.
     * @param isIncrement  A boolean value either true or false
     */
    public void updateDownvoteCount(boolean isIncrement) {
        if (isIncrement) {
            downvoteCount++;
        }
        else {
            downvoteCount--;
        }
    }

    /**
     * This method returns a list of posts in the thread of the object
     * being called.
     * @return postList, a list of posts in the thread of the object
     * being called, starting with original post and ending with the
     * post that was called.
     */
    public ArrayList<Post> getThread() {
        // Create a new arraylist to keep track of posts in thread
        ArrayList<Post> postList = new ArrayList<>();
        // If the given post is an original post return itself
        if (this.replyTo == null) {
            postList.add(this);
            return postList;
        }
        Post comment = this;
        // As long as the post is a comment continue adding to arraylist
        while (comment != null) {
            postList.add(0, comment);
            if (postList.get(0) == null) {
                break;
            }
            comment = (postList.get(0)).getReplyTo();
        }  
        return postList;
    }

    /**
     * A method that formats the post correctly and returns it 
     * as a string
     * @return A string representation of the post being called
     */
    public String toString() {
        if (this.title == null) {
            return(LEFT_BRACKET + upvoteCount + DIVIDER + downvoteCount 
            + RIGHT_BRACKET + TAB + this.content);
        }
        else {
            return(LEFT_BRACKET + upvoteCount + DIVIDER + downvoteCount 
            + RIGHT_BRACKET + TAB + this.title + NEWLINE + TAB + this.content);
        }
    }
}