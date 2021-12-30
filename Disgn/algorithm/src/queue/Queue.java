package queue;

/**
 * @author
 * @date 2021 年 12 月 30 日
 */
//queue.java
// demonstrates queue
    //to run this program: c>java QueueApp
public class Queue {
    private  int maxSize;
    private  long[] queArray;
    private  int front;
    private  int rear;
    private  int nItems;

    public Queue(int s) {//constructor
        maxSize = s;
        queArray =   new  long[maxSize];
        front = 0 ;
        rear=-1;
        nItems = 0;
    }
    public void  insert(long j ) //put item at rear of queue
    {
        if (rear==maxSize-1){

        }
    }
}
