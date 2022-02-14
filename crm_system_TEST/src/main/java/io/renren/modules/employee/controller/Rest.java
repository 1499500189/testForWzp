package io.renren.modules.employee.controller;

/**
 * @author
 * @date 2022 年 02 月 10 日
 */
public abstract   class Rest {
    public Rest(String name) {
        this.name = name;
    }

    public   String  name;

    abstract  void  test2();

    void ttt(){

        System.out.println("SFDAsfa");
    };
}
