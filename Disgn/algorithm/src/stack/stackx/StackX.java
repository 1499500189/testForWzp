package stack.stackx;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * @author
 * @date 2021 年 12 月 30 日
 */
//revere.java
    //stack used to reverse a string
    //to run this program ; c>java reverseApp
 class StackX {
     private  int maxSize;
     private  char[] stackArray;
     private  int top;
     //---------------------------------

    public StackX(int max) {
        maxSize = max;
        stackArray=new char[maxSize];
        top =-1;
    }
    public  void push(char j ) //put item on top of stack
    {
        stackArray[++top] =j;
    }
    public  char pop(){  //peek at top of stack
        return  stackArray[top--];
    }
    public char peek() //peek at top of stack
    {
        return  stackArray[top];
    }
    public boolean isEmpty()//true if stack is empty
    {
        return (top==-1);
    }
}//end class stackX
class  Reverser
{
    private  String input ; //input string
    private  String output;//output string

    public Reverser(String in) { //constructor
        this.input = in;
    }
    public  String doRev(){    //reverse the string
        int stackSize =input.length();  //get max stack size
        StackX theStack = new StackX(stackSize); //make stack
        for (int j=0;j<input.length();j++){
            char ch = input.charAt(j);  //get a char from input
            theStack.push(ch);  //push it
        }
        output="";
        while (!theStack.isEmpty()){
            char ch =theStack.pop();//pop a char,
            output =output+ch; //append to output
        }
        return  output;
    }//end doRev()
}//end Reverser
class  ReverseApp{
    public static void main(String[] args) throws IOException {
        String input,output;
          while (true){
              System.out.println("Enter a string");
              System.out.flush();
              input =getString();     //read a string from kbd
              if (input.equals("")){ //quit a Reverser
                  break;
              }  //make a Reverser
              Reverser theReverser = new Reverser(input);
              output = theReverser.doRev();//use it
              System.out.println("Reversed:"+output);
          }//end while
    }//end main()


    private static String getString() throws IOException {
        BufferedReader br;
        InputStreamReader isr = new InputStreamReader(System.in);
            br = new BufferedReader(isr);
        String s = br.readLine();
        return s;
    }
}
