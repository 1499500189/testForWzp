package testspringsecurity.c.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author
 * @date 2022 年 02 月 14 日
 */
@RestController
@RequestMapping("/test")
public class TestController {
         @GetMapping("hello")
          public  String hello(){
              return "hello security";
         }
}
