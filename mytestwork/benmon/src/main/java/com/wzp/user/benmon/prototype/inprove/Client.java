package com.wzp.user.benmon.prototype.inprove;

/**
 * @author
 * @date 2021 年 12 月 02 日
 */
public class Client {
    public static void main(String[] args) {

            Sheep sheep =new Sheep("tom",11,"heise ");
            sheep.setFriend(new Sheep("ccc",11,"pp"));
        System.out.println(sheep.getFriend());
            Sheep sheep2= (Sheep) sheep.clone();
            System.out.println(sheep2.getAge()+" - "+sheep.getName()+"+ "+sheep2.getFriend());
            Sheep sheep1 =(Sheep) sheep.clone();

    }
}
