package io.renren.modules.generator.entity;

import lombok.Data;

import java.util.Date;

/**
 * @author
 * @date 2022 年 03 月 08 日
 */
@Data
public class TbTelnumInfoEntity {

    private String    id ;
    private String            mobile ;
    private String    province ;
    private String            city ;
    private String    corp ;
    private String            area_code ;
    private String    post_code ;
    private Date create_time ;
    private Date    last_time ;
    private Integer            deleted ;
    private Date    delete_time ;
    private Long           sortno ;
    private Integer    version ;

}
