package com.wzp.test;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.wzp.test.entity.Course;
import com.wzp.test.mapper.CourseMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ShardingjdbctestApplicationTests {
@Autowired
private CourseMapper courseMapper;
    @Test
    void add() {
        //使用mp操作    ，   使用启动类启动
        Course course = new Course();
        course.setCname("nginx");
        course.setUserId(10L);
        course.setCstatus("Normal");
        courseMapper.insert(course);
    }
    @Test
    void select() {
        QueryWrapper<Course> wrapper = new QueryWrapper<>();
        wrapper.eq("cid",713332695370825729L);
        Course course = courseMapper.selectOne(wrapper);
        System.out.println(course);
    }

}
