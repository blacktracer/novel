package com.virgin.novel.controller.test;

import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;

/**
 * Created by gp-zhengjiudong on 2016/10/19.
 */
public class Person {

    @NotNull(message = "id不能为空")
    private String id;
    @NotEmpty
    private String name;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
