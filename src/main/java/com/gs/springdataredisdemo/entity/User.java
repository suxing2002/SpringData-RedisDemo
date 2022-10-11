package com.gs.springdataredisdemo.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author GuoShuo
 * @Time 2022/10/11 17:15
 * @Version 1.0
 * @Description
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private String name;
    private String password;
    private Integer age;
    private String sex;
}
