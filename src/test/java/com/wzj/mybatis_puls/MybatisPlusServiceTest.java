package com.wzj.mybatis_puls;

import com.wzj.mybatis_puls.pojo.User;
import com.wzj.mybatis_puls.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class MybatisPlusServiceTest {
    @Autowired
    private UserService userService;

    @Test
    public void testGetCount(){
        //查询总记录数
        //SELECT COUNT( * ) FROM user
        long count = userService.count();
        System.out.println("总记录数："+count);

            }

    @Test
    public void testInsertMore(){
        List<User> list= new ArrayList<>();
        for (int i = 1; i <=10 ; i++) {
            User user=new User();
            user.setName("wzj"+i);
            user.setAge(20+i);
            list.add(user);
        }
        boolean b = userService.saveBatch(list);
        System.out.println(b);
    }
}
