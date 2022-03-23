package com.wzp.test.entity;

import lombok.Data;

/**
 * @author
 * @date 2022 年 03 月 23 日
 */
@Data
public class Course {
    private Long cid;
    private String cname;
    private Long userId;
    private String cstatus;
}
