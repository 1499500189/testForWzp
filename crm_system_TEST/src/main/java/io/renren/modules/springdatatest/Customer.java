package io.renren.modules.springdatatest;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * @author
 * @date 2022 年 02 月 25 日
 */
@Data
public class Customer {
    @Id
    private Long id;
    private String lastName,firstName;
    private EmailAddress emailAddress;
    private Address address;
}
