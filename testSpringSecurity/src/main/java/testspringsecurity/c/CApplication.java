package testspringsecurity.c;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CApplication {

    public static void main(String[] args) {
        try{
            SpringApplication.run(CApplication.class, args);
        }catch (Exception e){
            e.printStackTrace();
            e.getMessage();
        }
    }

}
