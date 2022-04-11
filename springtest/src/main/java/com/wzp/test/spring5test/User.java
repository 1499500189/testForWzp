package com.wzp.test.spring5test;

import com.sun.istack.internal.NotNull;
import lombok.Data;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

/**
 * @author
 * @date 2022 年 04 月 08 日
 */

@Component
@Data
public class  User {
    @Nullable
    public String getUsername() {
        return username;
    }

    public void setUsername(@Nullable String username) {
        this.username = username;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }
    @Nullable
    private  String username;
    @NotNull
    private  Integer Id;

    @Nullable
    public Local getLocal() {
        return local;
    }

    public void setLocal(@Nullable Local local) {
        this.local = local;
    }

    @Nullable
    private Local local ;

}
