package com.wzp.test.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @author
 * @date 2022 年 03 月 23 日
 */
@Data
//@TableName(value = "t_user") //指定对应表
public class User {
    private Long userId;
    private String username;
    private String ustatus;
}
