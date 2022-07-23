package com.wzj.mybatis_puls.enums;

import com.baomidou.mybatisplus.annotation.EnumValue;
import lombok.Getter;

@Getter
public enum SexEnum {
    MALE(1,"男"),
    FEMALE(2,"女");

    @EnumValue//将注解所标识的实现值存储到数据库中
    private Integer sex;
    private String sexName;


    SexEnum(Integer sex, String sexName) {
        this.sex = sex;
        this.sexName = sexName;
    }
}
