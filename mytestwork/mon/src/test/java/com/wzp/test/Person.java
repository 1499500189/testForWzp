package com.wzp.test;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author
 * @date 2022 年 02 月 07 日
 */
public class Person {
    private Set _courses =new HashSet();
    public  void addCourse(Course arg){
        _courses.add(arg);
    }
    public  void removeCourse(Course arg){
        _courses.remove(arg);
    }
    public void setCourses(Set arg){
        Iterator iterator = arg.iterator();
        while (iterator.hasNext()){
            addCourse((Course)iterator.next());
        }
    }
    public void initializeCourses(Set arg){
        Iterator iter = arg.iterator();
        while (iter.hasNext()){
            addCourse((Course)iter.next());
        }

    }
    public Set  getCoursers(){
        return Collections.unmodifiableSet(_courses);

      /*  HashSet hashSet = new HashSet(_courses);
        return  hashSet;*/
    }


}
