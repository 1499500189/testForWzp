/**
 * @author
 * @date 2021 年 12 月 20 日
 */
 class Person {
    private  String lastName;
    private  String firstName;
    private  int age;

    public Person(String lastName, String firstName, int age) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.age = age;
    }
    public  void  displayPerson(){
        System.out.println("last name"+lastName);
        System.out.println("first name "+firstName);
        System.out.println("age "+age);
    }
    public  String getLast(){
        return  lastName;
    }
}
public  class  ClassDataArray{
     private  Person[] a;
     private  int nElems;

    public ClassDataArray(int max) {
        a = new Person[max];
        nElems =0 ;   //问进行初始化
    }
    public  Person find (String searchName)
    {
        int j   =  0 ;
        for (j = 0;  j<nElems ; j++) {
            if (a[j].getLast().equals(searchName))
                break;
            }

        return  null;
    }
}
