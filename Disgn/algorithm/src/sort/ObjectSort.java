package sort;

/**
 * @author
 * @date 2021 年 12 月 27 日
 */
public class ObjectSort {
    public static void main(String[] args) {

        int[] nums ={3,12,551,11,1,9,8 };

    }
}
class Person{
    private String lastName;
    private String firstName;
    private int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }
    public  void  displayPerson(){
        System.out.println("last name " +lastName);
        System.out.println("first name " +firstName);
        System.out.println("age  " +age);
    }
    public  String getLast(){
        return  lastName;
    }
}
//这个方法是往数组中添加元素的类
class  ArrayInOb{
    private  Person[] a;
    private  int nElems; //number of data items

    public ArrayInOb(int max) {
        a = new Person[max];
        nElems = 0;
    }
    public  void  insert(String last,String first,int age){
        a[nElems] =new Person(last,first,age);
        nElems++;  //increment size
    }
    public  void  display(){
        for (int j =0 ;j<nElems;j++){
            a[j].displayPerson();
            System.out.println("");
        }
    }
    public  void  insertionSort(){
        int in ,out ;
        for (out = 1; out <nElems ; out++) {
            Person temp = a[out];
            in =out ;
            while (in>0&&a[in-1].getLast().compareTo(temp.getLast())>0){
                a[in] =a[in-1];
                --in;
            }
            a[in] =temp; //insert marked item
        }    //end for
    }   //end class ArrayInOb
}
class ObjectSortApp{
    public static void main(String[] args) {
        int maxSize = 100; //array size
        ArrayInOb arr;   //reference to array
        arr = new ArrayInOb(maxSize); //create the array
        arr.insert("eveb",":oo",24);
        arr.insert("Smith","doc ",59);
        arr.insert("Yee","Tom",43);
        System.out.println("Before sorting >>>>>>>>>");
        arr.display();
        arr.insertionSort();
        System.out.println("After sorting <<<<<<<<<");
        arr.display();
    }//end main()
}
