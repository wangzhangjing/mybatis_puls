package com.wzj.mybatis_puls.pojo;


import com.baomidou.mybatisplus.annotation.*;
import com.wzj.mybatis_puls.enums.SexEnum;
import lombok.*;

//@NoArgsConstructor
//@AllArgsConstructor
//@Getter
//@Setter
//@EqualsAndHashCode
@Data
//设置实体类对应表名
//@TableName("t_user")
public class User {
    //将属性所对应的字段指定为主键
    //@TableId注解的value属性用于指定主键的字段
    //type的IdType属性设置主键生成策略 AUTO需要设置id自增
//    @TableId(value = "uid",type = IdType.AUTO)
    @TableId(value = "uid")
    private Long id;
    //指定属性所对应的字段名
    @TableField("user_name")
    private String name;
    private Integer age;
    private String email;
    private SexEnum sex;
    @TableLogic
    private Integer isDeleted;


}
