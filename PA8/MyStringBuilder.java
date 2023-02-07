public class MyStringBuilder {
    private CharNode start;
    private CharNode end;
    private int length;

    public MyStringBuilder(char ch) {
        start = new CharNode(ch);
        end = start;
        length = 1;
    }
    public MyStringBuilder(String str) {
        // Account for when string is null
        if (str == null) {
            throw new NullPointerException(); 
        }
        // Create charnode to keep track of charnodes
        CharNode tracker = start;
;        // Create new charnodes for each character in string
        for (int i = 0; i < str.length(); i++) {
            if (i == 0) {
                start.setNext(new CharNode(str.charAt(i)));
                tracker = start.getNext();  
            }
        }
    }
    public MyStringBuilder(MyStringBuilder other) {}
    public int length() {}
    public MyStringBuilder append(char ch) {
        
    }
    public MyStringBuilder append(String str) {}
    public String toString() {}
    public String subString(int startIdx) {}
    public String subString(int startIdx, int endIdx) {}
}
