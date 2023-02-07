# CSE 11 Fall 2022 PA8 - MyStringBuilder
**Due date: Thursday, Dec 1 @ 11:59PM PST**

There is an [FAQ](https://piazza.com/class/l871flmwsqy8i/post/1202) post on Piazza. Please read that post first if you have any questions.

*Note: there is no resubmission or hidden test cases for PA8.*
## Provided Files
- CharNode.java

## Goal
Programming assignment 8 is an introduction to data structures and handling exceptions. You will create MyStringBuilder class and its corresponding methods from scratch.

## Overview
- Survey [Google Form, 1 point]
- MyStringBuilder [Gradescope, 99 points]
    - Implementation [94 points]
    - Style [5 points]

## Survey [1 Point]
Please fill out [this survey](https://forms.gle/YjuPvYESrchkhCZK8) regarding your experience in this course.

## MyStringBuilder (MSB)
Strings are implemented differently across different programming languages. In Java, Strings are actual Objects! Furthermore, we have multiple classes which implement sequences of characters in varying ways (char arrays, ArrayLists of Characters, Strings, StringBuilder). These all have similar capabilities with different implementations internally. Here, we now explore one internal implementation by writing a simple string data structure. While it is similar to __the StringBuilder__ class, the internal workings of our StringBuilder will be one we have not seen before. We will call it **MyStringBuilder** to differentiate it from the default Java implementation.

The MyStringBuilder will be used to construct, store, and modify characters in a specific order. Before we talk about how our MyStringBuilder will be implemented, let’s pretend we were using a char array (we won’t use an actual char array in this assignment). In memory, elements of an array are stored next to each other like this:

![](https://i.imgur.com/6Y5EesY.png)


Suppose we made a mistake with “CSE” and we meant to spell “CASE”. The char array would have {‘C’, ‘S’, ‘E’}. To fix our mistake, we would have to (1) create a new array, (2) move ‘S’ and ‘E’ back, and then (3) assign the letter ‘A’ to position 1. 

1. Create a new array:
![](https://i.imgur.com/8Sl7Gnr.png)


2. & 3.  Copy items and add A:
![](https://i.imgur.com/CHxYQ0H.png)


This is inefficient and slow. If the array we are copying is very long, this could take a very long time! In MyStringBuilder, we will instead use **references** to link different letters together. CSE will be represented as `C → S → E`. Let’s think about how we could insert the letter ‘A’ into our MyStringBuilder now.

![](https://i.imgur.com/VnyGRnl.png)

Nice! We didn’t have to remake our entire MyStringBuilder and copy all of its contents!

But, char is a primitive type and not an Object-subclassed type. Primitive types don’t have the ability to point to each other. To solve this issue, we will contain the char inside a class whose sole purpose is to hold __chars__ and __references to the next char__ in the sequence. We will call this class a ***node***. In the case of this PA, we have provided a class called ***CharNode***.

**Note**: The diagrams in this writeup are not accurate representations, they are just for you to get a better sense of what is happening - we encourage you to draw your own accurate diagrams/memory models to visualize the logic behind the algorithms. For the diagrams, 
let `start` be the reference to the first CharNode, `end` be the reference to the last CharNode, 
`length` is the length of String, `next` is the next reference in CharNode, 
`data` is the character in CharNode. 

![](https://i.imgur.com/s9uGPQL.png)


This MyStringBuilder has the word “agc”. Notice that the chars are **not actually inside** **MyStringBuilder** - MyStringBuilder has a reference i.e. `start` which points to the first node, which contains information of a char ‘a’. The first node has a reference `next` that points to another node, which contains information of another char ‘g’. 

Note that `data` and `next` are simply instance variables of type `CharNode`. We can use these to join all of the CharNodes together. Notice that MyStringBuilder has a reference `end` that points to the last node.

To implement some of the methods, you might need to iterate through your MyStringBuilder, which requires you to find a way to keep track of where your current position. This will be done using an additional CharNode reference (i.e. the `currNode` reference in the figure above). Notice the two references pointing to the first CharNode. 

In this assignment, we will only be implementing a small subset of Java's StringBuilder methods.

> **Warning** 
> 
> **Use of libraries and other data structures of any kind in this part to store your MyStringBuilder content will result in a zero**. For example, you can’t use a Java StringBuilder to make your MyStringBuilder. Don’t use character arrays, Strings, etc. You **are** allowed to use String's `charAt()`, `substring()`, and `length()` instance methods. 

### CharNode.java
```java
public class CharNode {
    private CharNode next;
    private char data;
    public CharNode(char ch) {}
    public char getData() {}
    public CharNode getNext() {}
    public CharNode setData(char newData) {}
    public CharNode setNext(CharNode newNext) {}
}
```
For this assignment, CharNode.java is given to you. Make sure you read through the file and understand what it does.

*Note: Setters are usually void return types but here the setters are returning the objects for the purposes of testing and chaining.*


## Implementation [94 Points]

### MyStringBuilder.java
```java 
public class MyStringBuilder {
    private CharNode start;
    private CharNode end;
    private int length;

    public MyStringBuilder(char ch) {}
    public MyStringBuilder(String str) {}
    public MyStringBuilder(MyStringBuilder other) {}
    public int length() {}
    public MyStringBuilder append(char ch) {}
    public MyStringBuilder append(String str) {}
    public String toString() {}
    public String subString(int startIdx) {}
    public String subString(int startIdx, int endIdx) {}
}
```
### Instance Variables:

- `CharNode start` - a reference to our first node
- `CharNode end` - a reference to our last node
- `int length` - the length (number of CharNodes) in the current MyStringBuilder
    

### Methods:
#### **`public MyStringBuilder(char ch)`**
- Creates a MyStringBuilder with the single CharNode representing the input.
- Initialize the instance variables accordingly.
- **Hint**: both `start` and `end` should point to the same CharNode.

#### **`public MyStringBuilder(String str)`**
- Creates a MyStringBuilder from a String.
- Initialize the instance variables accordingly.
- If the String is null, throw a `NullPointerException`.
- If the String is empty, then the constructed MyStringBuilder object should also be empty (have no CharNodes).
- **Hint**: you may find one of the later methods helpful for this.

#### **`public MyStringBuilder(MyStringBuilder other)`**
- Creates a MyStringBuilder using a MyStringBuilder object by deep copying its contents.
- Initialize the instance variables accordingly.
- You should traverse from `start` to `end` and deep copy each CharNode by creating new CharNode references. 
- If `other` is null, throw a`NullPointerException`.

For example, if we have an existing MyStringBuilder object called `msb1` with the String `"cse"` in it, the diagram would look like this:
![](https://i.imgur.com/Wn2dWCd.png)


If we create a new variable `msb2` by using
`MyStringBuilder msb2 = new MyStringBuilder(msb1)`, the final result should look like this:
![](https://i.imgur.com/L2wryzn.png)
Notice that `msb1`'s start reference != `msb2`'s start reference.

An incorrect implementation could look like this, where only a shallow copy is performed. 
![](https://i.imgur.com/ezYPuNr.png)
Notice that `msb1`'s start reference == `msb2`'s start reference, which means that this is not a deep copy.


#### **`public int length()`**
- Returns the current length of MyStringBuilder.
- **Note**: Your other methods must appropriately update the `length` variable. 

#### **`public MyStringBuilder append(char ch)`**
- Appends a single char to the end of MyStringBuilder.
- Update the relevant instance variables.
- Return the updated MyStringBuilder object.
- If MyStringBuilder is originally empty, then `start` and `end` must point to a new CharNode containing this char. 

A fresh MyStringBuilder object `msb`, in a diagram:

![](https://i.imgur.com/5iWTfU4.png)

if we call `msb.append('a')`, then: 

![](https://i.imgur.com/R3VJ5da.png)

Afterwards if we call `msb.append(‘b’)`, then:

![](https://i.imgur.com/8nmNgUA.png)

#### **`public MyStringBuilder append (String str)`**
- Appends an entire String to the end of the current MyStringBuilder object.
- If the string is null, throw a `NullPointerException`.
- If the string is empty, nothing should be added to MyStringBuilder.
- Return the updated MyStringBuilder object.
- Feel free to use the method above `append (char ch)` in this method.


#### **`public String toString()`**
Since the MSB class is a way to manipulate and store Strings, we should have a `toString()` method which returns an actual `String` object of our stored String. This function takes this sequence of CharNodes and turns the sequence of chars into a `String`. 

#### **`public String subString(int startIdx)`**
- Returns a substring starting from `startIdx` to the end.
- If `startIdx` is invalid, throw an`IndexOutOfBoundsException`.

#### **`public String subString(int startIdx, int endIdx)`**
- Returns a substring from `startIdx` (inclusive) to `endIdx` (exclusive).
- If `startIdx == endIdx`, then empty string is returned. 
- `startIdx` must be within the bounds of MyStringBuilder and non-negative, `endIdx` must be less than or equal to the length of MyStringBuilder and be greater than or equal to `startIdx`. If any are not true, throw an `IndexOutOfBoundsException`. 

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
Submit the following file(s) to Gradescope by **Thursday, Dec 1 @ 11:59PM PST**.
 - MyStringBuilder.java

Even if your code does not pass all the tests, you will still be able to submit your work to receive partial points for the tests that you passed. Make sure your code compiles in order to receive partial credit.
