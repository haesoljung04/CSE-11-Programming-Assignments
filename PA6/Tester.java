/**
 * Name: Haesol Jung
 * Email: haj008@ucsd.edu
 * PID: A17348180
 * 
 * This file is used to test the functionalities of 2 other files
 * that are Post.java and User.java
 */

/**
 * This class tests the files that are named post.java and user.java
 */
public class Tester {
    public static void main(String[] args) {
        User Peanut = new User("bignutsorry");
        User Walnut = new User("smallanchovies");
        Post pea = new Post("pea", "loving grean peas", Peanut);
        Post nut = new Post("i need nuts", pea, Peanut);
        Post almond = new Post("nonallergenic milk", nut, Peanut);
        Post nullPost = null;
        System.out.println(Peanut);
        System.out.println(Walnut);
        System.out.println(pea);
        System.out.println(nut);
        Peanut.addPost(pea);
        Peanut.addPost(nut);
        Peanut.upvote(pea);
        Peanut.upvote(nut);
        Peanut.downvote(pea);
        Peanut.downvote(nut);
        Peanut.downvote(pea);
        Peanut.downvote(nut);
        pea.updateUpvoteCount(true);
        nut.updateUpvoteCount(true);
        pea.updateDownvoteCount(false);
        nut.updateDownvoteCount(false);
        Walnut.getTopPost();
        Walnut.getTopComment();
        Walnut.getPosts();
        Walnut.getKarma();
        Walnut.upvote(nullPost);
        Walnut.downvote(nullPost);
        System.out.println(Peanut.getTopPost());
        System.out.println(Peanut.getTopComment());
        System.out.println(Peanut.getPosts());
        System.out.println(Peanut.getKarma());
        System.out.println(Peanut.toString());
        System.out.println(pea.getTitle());
        System.out.println(nut.getReplyTo());
        System.out.println(pea.getAuthor());
        System.out.println(nut.getUpvoteCount());
        System.out.println(pea.getDownvoteCount());
        System.out.println(almond.getThread());
        System.out.println(nut.getThread());
        System.out.println(pea.toString());
        System.out.println(almond.toString());
    }
}
