package queue.NoIntems;

/**
 * @author
 * @date 2021 年 12 月 31 日
 */
//不会 ，这其中的几个方法特别的麻烦
public class Queue {
    private  int maxSize;
    private long[] queArray;
    private  int front;
    private  int rear;

    public Queue(int s) {//constructor
        this.maxSize = s+1; //array is 1 cell larger
        queArray =new long[maxSize];  //than requested
        front = 0;
        rear =-1;
    }
    public void  insert (long j){ //put item at rear of queue
        if (rear==maxSize-1){
            rear =-1;
        }
        queArray[++rear] = j ;
    }
    public long remove(){       //take item from front of queue
        long temp = queArray[front++];
        if (front==maxSize){
            front = 0 ;
        }
        return temp;
    }
    public long peek(){   // peek at front of queue
        return queArray[front];
    }
    public boolean isEmpty(){
        return (rear+1== front||front+maxSize-1==rear) ;
    }
    public boolean isFull(){
        return (rear+2==front||front+maxSize-2==rear) ;
    }
    public int size(){ //(assumes queue not empty)
        if (rear>=front){     //contiguous sequence
            return  rear-front+1;
        }else {    //broken sequence
            return (maxSize-front)+(rear+1);
        }
    }

}//end class Queue

