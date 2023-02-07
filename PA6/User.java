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
 * This class implements the functions of a user in Rediit,
 * who has a karma count, username, lists of posts they made,
 * and the ability to downvote or upvote a post
 */
public class User {

    /** Constants (Magic Numbers) */
    private static final String USER = "u/";
    private static final String KARMA = " Karma: ";
    
    /** Instance Variables */
    private int karma;
    private String username;
    private ArrayList<Post> posts;
    private ArrayList<Post> upvoted;
    private ArrayList<Post> downvoted;

    /**
     * The constructor for a user which initializes the username with 
     * the parameter value and sets all arraylists to empty. It also 
     * sets karma to 0.
     * @param username  The name of the user
     */
    public User(String username) {
        this.username = username;
        posts = new ArrayList<>();
        upvoted = new ArrayList<>();
        downvoted = new ArrayList<>();
        karma = 0;
    }

    /**
     * This function adds the parameter post to the list of the user's
     * posts unless the parameter is null. It also updates the user's
     * karma
     * @param post  A post to be added to the user's list of posts
     */
    public void addPost(Post post) {
        if (post != null) {
            posts.add(post);
        }
        updateKarma();
    }

    /**
     * This post updates the users karma by going through their list of
     * posts and summing up their all net number of upvotes minus downvotes.
     */
    public void updateKarma() {
        int postKarma = 0;
        int totalKarma = 0;
        // Loop that gets net karma for each post
        for (int i = 0; i < posts.size(); i++) {
            postKarma = (posts.get(i).getUpvoteCount()) 
            - (posts.get(i).getDownvoteCount());
            totalKarma += postKarma;
        }
        karma = totalKarma;
    }

    /**
     * This post returns the current user's karma
     * @return karma  The user's karma
     */
    public int getKarma() {
        return karma;
    }

    /**
     * Function that allows the user to upvote parameter post. Also adds
     * the post to the list of upvoted posts and this updates the author's
     * karma value as well.
     * @param post  The post being upvoted
     */
    public void upvote(Post post) {
        if (post == null) {
            return;
        } 
        // If the post has already been upvoted or user is author return
        if (upvoted.contains(post) || posts.contains(post)) {
            return;
        }
        // When the post was previously downvoted
        if (downvoted.contains(post)) {
            downvoted.remove(downvoted.indexOf(post));
            post.updateDownvoteCount(false);
        }
        upvoted.add(post);
        post.updateUpvoteCount(true);
        post.getAuthor().updateKarma();
    }

    /**
     * Function that allows the user to downvote parameter post. Also adds
     * the post to the list of downvoted posts and this updates the author's
     * karma value as well.
     * @param post  The post being downvoted
     */
    public void downvote(Post post) {
        if (post == null) {
            return;
        }
        // If the post has already been downvoted or user is author
        if (downvoted.contains(post) || posts.contains(post)) {
            return;
        }
        // When the post has been previously upvoted
        if (upvoted.contains(post)) {
            upvoted.remove(upvoted.indexOf(post));
            post.updateUpvoteCount(false);
        }
        downvoted.add(post);
        post.updateDownvoteCount(true);
        post.getAuthor().updateKarma();
    }

    /**
     * Get the best original post determined by the greastest karma value
     * @return the top original post
     */
    public Post getTopPost() {
        ArrayList<Post> originalPosts = new ArrayList<>();
        // First put all the original posts into a list
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getTitle() != null) {
                originalPosts.add(posts.get(i));
            }
        }
        // If there are no original posts then return null
        if (originalPosts.size() == 0) {
            return null;
        }
        Post topPost = null;
        int topPostCount = 0;
        // Loop to check which post has the best karma
        for (int i = 0; i < originalPosts.size(); i++) {
            if (i == 0) {
                topPost = originalPosts.get(i);
                topPostCount = topPost.getUpvoteCount() 
                - topPost.getDownvoteCount();
                continue;
            }
            if (originalPosts.get(i).getUpvoteCount() 
            - originalPosts.get(i).getDownvoteCount() > topPostCount) {
                topPost = originalPosts.get(i);
            }
        }
        return topPost;
    }

    /**
     * Get the best comment determined by the greatest karma value
     * @return the top comment
     */
    public Post getTopComment() {
        ArrayList<Post> comments = new ArrayList<>();
        // Get all the comments into a new list
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getTitle() == null) {
                comments.add(posts.get(i));
            }
        }
        // If there are no comments return null
        if (comments.size() == 0) {
            return null;
        }
        Post topComment = null;
        int topCommentCount = 0;
        // Loop to see which comment has the best karma
        for (int i = 0; i < comments.size(); i++) {
            if (i == 0) {
                topComment = comments.get(i);
                topCommentCount = topComment.getUpvoteCount() 
                - topComment.getDownvoteCount();
                continue;
            }
            if (comments.get(i).getUpvoteCount() 
            - comments.get(i).getDownvoteCount() > topCommentCount) {
                topComment = comments.get(i);
            }
        }
        return topComment;
    }
    /**
     * Return the list of posts made by the user being called
     * @return posts  The list of posts the user made
     */
    public ArrayList<Post> getPosts() {
        return posts;
    }

    /**
     * Return a correctly formatted string representation of user
     * @return user in following format string: "u/%s Karma: %d"
     */
    public String toString() {
        return (USER + this.username + KARMA + this.getKarma());
    }
}
