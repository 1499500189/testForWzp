package main.java.test.demo.factorymethid.pizzastore;

/**
 * @author
 * @date 2021 年 11 月 22 日
 */
 public abstract class Pizza {
   protected   String name ;
   public  void bake (){
       System.out.println( name+"baking");
   }
    public  void cut (){
        System.out.println( name+"baking");
    }
    public  void box (){
        System.out.println( name+"baking");
    }

    public  void setName (String name){
         this.name  =name;
    }


}
