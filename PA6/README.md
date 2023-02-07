# CSE 11 Fall 2022 PA6 - Simple Reddit
**Due date: Thursday, Nov 10 @ 11:59PM PDT**

There is an [FAQ post](https://piazza.com/class/l871flmwsqy8i/post/766) on Piazza. Please read that post first if you have any questions.

## Provided Files
None

## Goal
Programming Assignment 6 is an introduction to Classes and ArrayLists in Java. In this assignment, you will create two new classes from scratch with several instance variables and methods.

## Overview
- Survey [Google Form, 1 point]
- Simple Reddit [Gradescope, 99 points]
    - Implementation [84 points]
    - Testing [10 points]
    - Style [5 points]

## Survey [1 Point]
Please fill out [this survey](https://forms.gle/sxjBttUDfZXvmngX6) regarding your experience in this course.

## Simple Reddit
[Reddit](https://en.wikipedia.org/wiki/Reddit) is a popular social news aggregation, content rating, and discussion website. Users submit content to the site such as links, text posts, images, and videos, which are then voted up or down by other members. 

In this programming assignment, we will create a simplified version of Reddit, where users can create, upvote, and downvote posts. In order to do so, we will create two files: `Post.java` and `User.java` to represent and to implement the functionalities of these objects.

## Implementation [84 Points]
### Post.java
On Reddit, you can either create a post in some community (subreddit), or comment on someone's existing post. In this write-up, we will refer to them as "original posts" and "comments" to avoid confusion with a generic `Post` object. In our program, both are considered to be a `Post`, with some differences in instance variable values to differentiate them. The `Post` instance variables and methods are described below.
> **Warning** 
> 
> *Do NOT add additional imports other than `java.util.ArrayList`.*
#### Instance Variables:
```java
public class Post {
    ...
    private String title;
    private String content;
    private Post replyTo;
    private User author;
    private int upvoteCount;
    private int downvoteCount;
    ...
}
```
 - `String title` - the title of a Reddit post. If this post is a comment, then `title` should be `null`. `title` should be non-null if this post is an original post.
 - `String content` - the content of a Reddit post. If this post is a comment,  `content` is the comment a User made.
 - `Post replyTo` - the original post or comment this `Post` is replying to. If this `Post` is an original post, `replyTo` should be `null`. `replyTo` should be non-null if this post is a comment.
 - `User author` - the author of this `Post`.
 - `int upvoteCount` - the number of upvotes of this `Post`.
 - `int downvoteCount` - the number of downvotes of this `Post`.

#### Methods: 
```java
public class Post {
    ...
    public Post(String title, String content, User author);
    public Post(String content, Post replyTo, User author);
    public String getTitle();
    public Post getReplyTo();
    public User getAuthor();
    public int getUpvoteCount();
    public int getDownvoteCount();
    public void updateUpvoteCount(boolean isIncrement);
    public void updateDownvoteCount(boolean isIncrement);
    public ArrayList<Post> getThread();
    public String toString();
    ...
}
```


**`public Post(String title, String content, User author)`**
- The constructor for initializing an original post.
- Initialize the instance variables with the values from parameters.
- Set `upvoteCount` to 1. (The author upvotes their own post by default.) Set `downvoteCount` to 0.

**`public Post(String content, Post replyTo, User author)`**
- The constructor for initializing a comment.
- Initialize the instance variables with the values from parameters.
- Set `upvoteCount` to 1. Set `downvoteCount` to 0.

**`public String getTitle()`**
- Return the title of this `Post`.

**`public Post getReplyTo()`**
- Return the `Post` of that this `Post` is replying to.

**`public User getAuthor()`**
- Return the author of this `Post`.

**`public int getUpvoteCount()`**
- Return the number of upvotes of this `Post`.

**`public int getDownvoteCount()`**
- Return the number of downvotes of this `Post`.

**`public void updateUpvoteCount(boolean isIncrement)`**
- Increment `upvoteCount` by 1 if `isIncrement` is `true`, otherwise decrement `upvoteCount` by 1.
- You may assume that we will not call `updateUpvoteCount` that would result in a negative `upvoteCount`.

**`public void updateDownvoteCount(boolean isIncrement)`**
- Increment `downvoteCount` by 1 if `isIncrement` is `true`, otherwise decrement `downvoteCount` by 1.
- You may assume that we will not call `updateDownvoteCount` that would result in a negative `downvoteCount`.

**`public ArrayList<Post> getThread()`**
- Return a list of posts in the current thread, starting with the original post of this post and ending with `this` post.
- The original post should be the first item in the list, followed by its comment, then its comment's comment, etc. The last item in the list should be `this` post.
    - For example, if `Post` P1 is an original post, P2 is a reply (comment) to P1, and P3 is a reply to P2. Then `P3.getThread()` should return `[P1, P2, P3]`. Even if P1 has more than one reply comment, calling `P3.getThread()` should still return `[P1, P2, P3]`. Likewise, `P3.getThread()` should return `[P1, P2, P3]` even if there are replies to P3 or if P2 has replies other than P3.
    - If `Post` P1 is an original post, then `P1.getThread()` should return `[P1]` since there are no higher-level threads.
- You can implement this method either recursively or iteratively.

**`public String toString()`**
- Return a String representation of this `Post`.
- If this `Post` is an original post, the `toString()` should have the following format (`\t` is a tab):
    ```
    [upvoteCount|downvoteCount]\t Title
    \t Content
    ```
    Example:
    ```
    [1|0]   Title of Original Post
            Content of original post
    ```
- If this Post is a comment, the `toString()` should have the following format (`\t` is a tab):

    ```
    [upvoteCount|downvoteCount]\t Content
    ```
    Example:
    ```
    [2|0]   Content of a comment / reply
    ```
- Feel free to use the following format Strings with `String.format()` in your code:
    ```java
    String TO_STRING_POST_FORMAT = "[%d|%d]\t%s\n\t%s";
    String TO_STRING_COMMENT_FORMAT = "[%d|%d]\t%s";
    ```
    
### User.java
A `User` object represents a user in our simplified Reddit program. The `User` instance variables and methods are described below.

> **Warning** 
> 
> *Do NOT add additional imports other than `java.util.ArrayList`.*


#### Instance Variables:

```java
public class User {
    ...
    private int karma;
    private String username;
    private ArrayList<Post> posts;
    private ArrayList<Post> upvoted;
    private ArrayList<Post> downvoted;
    ...
```

- `String username` - this `User`'s username
- `int karma`
    - On Reddit, karma is like a user's score or reputation. 
    - In our program, karma is calculated as (the total number of upvotes) - (the total number of downvotes) a user has. This value can be negative.
- `ArrayList<Post> posts`
    - A list of posts this `User` has made, including original posts and comments.
- `ArrayList<Post> upvoted`
    - A list of other users' posts that this `User` upvoted.
- `ArrayList<Post> downvoted`
    - A list of other users' posts that this `User` downvoted.

#### Methods: 
```java
public class User {
    ...
    public User(String username);
    public void addPost(Post post);
    public void updateKarma();
    public int getKarma();
    public void upvote(Post post);
    public void downvote(Post post);
    public Post getTopPost();
    public Post getTopComment();
    public ArrayList<Post> getPosts();
    public String toString();
    ...
}
```


**`public User(String username)`**
- The constructor for a `User`.
- Initialize `username` to the parameter value and all ArrayLists to empty ArrayLists. Initialize `karma` to 0.

**`public void addPost(Post post)`**
- Add a `Post` to end of this `User`'s `posts`.
- If `post` is `null`, do not add it to `posts`.
- Update this `User`'s karma by calling `updateKarma()`.

**`public void updateKarma()`**
- Update this `User`'s karma by going through the user's `posts` and summing up `upvoteCount`$-$`downvoteCount` for each post.

**`public int getKarma()`**
- Return the current value of `karma`.

**`public void upvote(Post post)`**
- If `post` is null, return immediately.
- If `post` has already been upvoted by the `User` or if the author of `post` is the current user, return immediately as well.
- If `post` was originally downvoted by the `User`, remove it from `downvoted` and update `post`'s `downvoteCount` accordingly.
- Add `post` to `upvoted`, update `post`'s `upvoteCount` accordingly.
- Update the author's karma value.

**`public void downvote(Post post)`**
- Similar to `upvote`.
- If `post` is null, return immediately.
- If `post` has already been downvoted by the `User` or if the author of `post` is the current user, return immediately as well.
- If `post` was originally upvoted by the `User`, remove it from `upvoted` and update `post`'s `upvoteCount` accordingly.
- Add `post` to `downvoted`, update `post`'s `downvoteCount` accordingly.
- Update the author's karma value.

**`public Post getTopPost()`**
- Return the **top original post** determined by the greatest (`upvoteCount`$-$`downvoteCount`) value.
- If no such original post exists, return `null`.
- If there is a tie, return the first original post `Post` in the tie.  First meaning the lowest-indexed `Post` in `posts`.

**`public Post getTopComment()`**
- Return the **top comment** determined by the greatest (`upvoteCount`$-$`downvoteCount`) value.
- If no such comment exists, return `null`.
- If there is a tie, return the first comment `Post` in the tie. First meaning the lowest-indexed `Post` in `posts`.

**`public ArrayList<Post> getPosts()`**
- Return the list of posts (original posts and comments) made by the `User`.

**`public String toString()`**
- Return a String representation of this `User`.
- Format: `u/username Karma: #`
- Example: `u/CSE11Student Karma: 2`
- Feel free to use the following format String: `"u/%s Karma: %d"`

## Testing [10 Points]
For this programming assignment, you will also be graded on your demonstration of testing your code.

Create a file called `Tester.java`. In the `main` method, create instances of `User` and `Post`, call the methods you would like to test, and use print statements to verify your code's correctness. For example, the following code tests the constructors, `toString()`, `addPost()`, `getTopPost()`, and `getTopComment()`:

```java
public class Tester {
    public static void main(String[] args) {
        User u1 = new User("CSE11Student");
        Post p1 = new Post("Title", "Content", u1);
        Post c1 = new Post("Comment", p1, u1);
        System.out.println(u1);
        System.out.println(p1);
        System.out.println(c1);
        u1.addPost(p1);
        u1.addPost(c1);
        System.out.println(u1.getTopPost());
        System.out.println(u1.getTopComment());
    }
}
```
Running the tester produces the expected results:
```
$ javac Post.java User.java Tester.java
$ java Tester
u/CSE11Student Karma: 0
[1|0]   Title
        Content
[1|0]   Comment
[1|0]   Title
        Content
[1|0]   Comment
```

To receive full credit for this section, make sure your main method tests at **least 75% of both methods and lines** of your code. You will be able to see your code coverage under "Coverage" on the Gradescope Autograder. You'll get partial credits if your coverage is between 55% and 75%. No points will be awarded if your coverage is below 55%.

Note: Only the file header will be graded for style. You can use magic numbers and non-descriptive variable names as the main goal of this file is for testing.

## Style [5 Points]
Coding style is an important part of ensuring readability and maintainability of your code. We will grade your code style in all submitted code files according to the style guidelines. Namely, there are a few things you must have in each file/class/method:

1. File header
2. Class header
3. Method header(s)
4. Inline comments
5. Proper indentation
6. Descriptive variable names
7. No magic numbers
8. Reasonably short methods (if you have implemented each method according to specification in this write-up, you’re fine). This is not enforced as strictly.
9. Lines shorter than 80 characters
10. Javadoc conventions (@param, @return tags, /** comments */, etc.)

A full style guide can be found [here](https://github.com/CaoAssignments/style-guide/blob/main/README.md) and a sample styled file can be found [here](https://github.com/CaoAssignments/style-guide/blob/main/SampleFile.java). If you need any clarifications, feel free to ask on Piazza.


## Submission
Submit the following file(s) to Gradescope by **Thursday, Nov 10 @ 11:59PM PDT**.
 - Post.java
 - User.java
 - Tester.java

Even if your code does not pass all the tests, you will still be able to submit your work to receive partial points for the tests that you passed. Make sure your code compiles in order to receive partial credit.
