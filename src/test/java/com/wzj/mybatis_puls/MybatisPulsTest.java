package com.wzj.mybatis_puls;


import com.wzj.mybatis_puls.mapper.UserMapper;
import com.wzj.mybatis_puls.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@SpringBootTest
public class MybatisPulsTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    //查询
    public void TestSelectList(){
        //通过条件构造器查询一个list集合，如果没有条件可以设置null为参数
        List<User> list = userMapper.selectList(null);
        list.forEach(System.out::println);
    }

    @Test
    //添加
    public void TestInsert(){
        //通过条件构造器查询一个list集合，如果没有条件可以设置null为参数
       User user =new User();
       user.setName("但是");
       user.setEmail("方法@wzj.com");
       user.setAge(5);
        int result = userMapper.insert(user);
        System.out.println("result:"+result);
        System.out.println("id:"+user.getId());
    }


    @Test
    public void testDeleteById(){
//        //通过id删除用户信息
//        int result = userMapper.deleteById(1545340196015251457l);
//        System.out.println(result);
        //根据map集合中设置的条件进行删除用户信息
//        Map<String ,Object> map=new HashMap<>();
//        map.put("name","李四");
//        map.put("age",45);
//        int result = userMapper.deleteByMap(map);
//        System.out.println(result);
        //通过多个id实现批量删除
        List<Long> list= Arrays.asList(1l,2l,3l);
        int i = userMapper.deleteBatchIds(list);
        System.out.println(i);
    }

    @Test
    public void testUpdata(){
        User user =new User();
        user.setId(4l);
        user.setName("王五");
        user.setEmail("wangwu@wzj.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
    @Test
    public void selectById(){
//        User user = userMapper.selectById(3l);
//        System.out.println(user);
//        List<Long> list= Arrays.asList(1l,2l,3l);
//        List<User> users = userMapper.selectBatchIds(list);
//        System.out.println(users);
//        Map<String ,Object> map=new HashMap<>();
//        map.put("name","李四");
//        List<User> users = userMapper.selectByMap(map);
//        users.forEach(System.out::println);
        //查询所有数据
//        List<User> users=userMapper.selectList(null);
//        users.forEach(System.out::println);\
//        Map<String, Object> map = userMapper.selectMapById(1l);
//        System.out.println(map);
    }
}
