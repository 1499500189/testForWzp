package stack.Bracket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * @author
 * @date 2021 年 12 月 30 日
 */
//brackets.java
// stacks used to check matching brackets
    //to run this program : C>java BracketsApp
class StackX{
        private  int maxSize;
        private  char[] stackArray;
        private  int top;

    public StackX(int s) { //constructor
        this.maxSize = s;
        stackArray =new char[maxSize];
        top =-1;
    }
    public  void push(char j)//put item on top of stack
    {
        stackArray[++top]=j;
    }
    public  char pop(){  //take item from top of stack
        return  stackArray[top--];
    }
    public char peek(){   //peek at top of stack
        return  stackArray[top];
    }
    public  boolean isEmpty(){//true if stack is empty
        return  (top==-1);
    }
}//end class StackX
class  BracketChecker{
    private String input ; //input string

    public BracketChecker(String in) {//constructor
        this.input  =  in;
    }
    public  void  check(){
        int stackSize =input.length();//get max stack size
        StackX theStack = new StackX(stackSize);//make stack
        for (int j = 0; j <input.length() ; j++) {//get chars in turn
            char ch = input.charAt(j); //get char
            switch (ch) {
                case '{': //opening symbols
                case '[':
                case '(':
                    theStack.push(ch);   //push them
                    break;
                case '}':                      //closing symbols
                case ']':
                case ')':
                    if (!theStack.isEmpty()) //if stack not empty
                    {
                        char chx = theStack.pop(); //pop and check
                        if ((ch == '}' && chx != '{') ||
                                (ch == ']' && chx != '[') ||
                                (ch == ')' && chx != '(')
                           ) {
                            System.out.println("error : " + ch + " at " + j);

                        }
                    } else {   //prematurely empty
                        System.out.println("error : " + ch + " at " + j);
                    }
                    break;
                default: //no action on other characters
                    break;
            }//end switch
        }//end for
        //at this point,all characters have been processed
        if (!theStack.isEmpty()){
            System.out.println("Error: missing right delimiter" );
        }//end check()
    }//
}  //end class bracketChecker
public class bracket {
}
class  BracketsApp{
    public static void main(String[] args) {
        String input;
        while (true){
            System.out.println("Enter string containing delimiters:");
            System.out.flush();
            input=getString() ; //read a string from kbd
            if (input.equals(""))  //quit if (Enter)
            {
                break;
            }
             //make a BracketChecker
            BracketChecker theChecker = new BracketChecker(input);
            theChecker.check(); //check brackets
        }//end while
    } // end main()

    private static String getString() {

        InputStreamReader isr = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(isr);
        String s = null;
        try {
            s = br.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return  s;
    }   //end class BracketsApp
}
