package template;

/**
 * @author
 * @date 2021 年 12 月 16 日
 */
//抽象类
public abstract  class SoyaMilk {
    //模板方法，make ，模板方法可以做成final，不让子类去覆盖 。
      final    void make(){
          select();
          if (customerWantCondiments()){
              addCondiments();
          }
          soak();
          beat();

    }
    //钩子方法
    boolean customerWantCondiments(){
        return  true;
    }
     //选材料
    void  select(){
        System.out.println("第一步， 序号新鲜的黄豆");

    }
    //添加不同的配料
    abstract  void addCondiments();
      //浸泡
    void  soak(){
        System.out.println("第三步，黄豆和配料开始浸泡，需要3小时");
    }
    void beat (){
        System.out.println("第四步  打碎豆浆");
    }


}
