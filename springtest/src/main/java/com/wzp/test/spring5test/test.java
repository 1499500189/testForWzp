package com.wzp.test.spring5test;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author
 * @date 2022 年 04 月 08 日
 */
public class test {

    @Autowired
    private  User user;

    @Test
    public  void c() {
        String username = user.getUsername();
        System.out.println(username);

          user.setUsername(null);
          user.setId(null);
        Integer id = user.getId();
        System.out.println(id);
        Local local = user.getLocal();
        String localName = local.getLocalName();
        System.out.println(localName);

    }
}
