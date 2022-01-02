package com.wzp.ttt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
/*@MapperScan("com.wzp.test.mon.dao")*/
@SpringBootApplication
public class MonApplication {

    public static void main(String[] args) {
      try {
          SpringApplication.run(MonApplication.class, args);
      }catch (Exception e )
      {
          e.printStackTrace();
      }
    }

}
