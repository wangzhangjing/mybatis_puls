package com.wzj.mybatis_puls;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.wzj.mybatis_puls.mapper.UserMapper;
import com.wzj.mybatis_puls.pojo.User;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

@SpringBootTest
public class MyBatisPlusWarpperTest {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void test1(){
        //查询用户名包含a 年龄在20到30之间，邮件不为空的用户信息
//        SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age BETWEEN ? AND ? AND email IS NOT NULL)
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                        .between("age",20,30)
                                .isNotNull("email");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    @Test
    public void test2(){
        //查询用户信息按照年龄降序排序，如果年龄相同按照id升序排
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 ORDER BY age DESC,uid ASC
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("uid");
        List<User> list = userMapper.selectList(queryWrapper);
        list.forEach(System.out::println);
    }
    @Test
    //输出邮箱地址为null的用户信息
    public void test3(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.isNull("email");
        int result = userMapper.delete(queryWrapper);
        System.out.println("result:"+result);

    }
    @Test
    public void test4(){
        //将（年龄大于20且用户名有a的）或者邮箱为空的应用信息修改
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.gt("age",20)
                        .like("user_name","a")
                                .or().isNull("email");
        User user=new User();
        user.setName("小明");
        user.setEmail("test@wzj.com");
        int i = userMapper.update(user, queryWrapper);
        System.out.println(i);
    }
    @Test
    //将用户名包含a 并且（年龄大于20或者邮箱为null)的用户信息修改
    //lambda (.and(i->i.gt)中的条件先执行
    public void test5(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.like("user_name","a")
                .and(i->i.gt("age",20).or().isNull("email"));
        User user=new User();
        user.setName("小红");
        user.setEmail("test22@wzj.com");
        int i = userMapper.update(user, queryWrapper);
        System.out.println(i);
    }
    @Test
    public void test6(){
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.select("user_name","age","email");
        List<Map<String, Object>> maps = userMapper.selectMaps(queryWrapper);
        maps.forEach(System.out::println);
    }
    @Test
    public void test7(){
        //查询id小于100的用户信息
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (uid IN (select uid from t_user where uid<=100))
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        queryWrapper.inSql("uid","select uid from t_user where uid<=100");
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);

    }
    @Test
    public void test08(){
        //将用户名包含a 并且（年龄大于20或者邮箱为null)的用户信息修改
        UpdateWrapper<User> updateWrapper=new UpdateWrapper<>();
        updateWrapper.like("user_name","a")
                .and(i ->i.gt("age",20).or().isNull("email"));
        updateWrapper.set("user_name","小黑").set("email","xiaohei@wzj.com");
        int i = userMapper.update(null, updateWrapper);
        System.out.println(i
        );

    }
    @Test
    public void test9(){
        //SELECT uid AS id,user_name AS name,age,email,is_deleted FROM t_user WHERE is_deleted=0 AND (user_name LIKE ? AND age >= ? AND age <= ?)
        String username="小";
        Integer ageBegin=20;
        Integer ageEnd=30;
        QueryWrapper<User> queryWrapper=new QueryWrapper<>();
        if (StringUtils.isNotBlank(username)){
            //isNotBlank判断某个字符串是否为空字符串，不为null，不为空白符
            queryWrapper.like("user_name",username);
        }
        if (ageBegin !=null){
            queryWrapper.ge("age",ageBegin);
        }
        if (ageEnd !=null){
            queryWrapper.le("age",ageEnd);
        }
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    @Test
    public void test10() {
        String username = "小";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(username),"user_name",username)
                .ge(ageBegin!=null,"age",ageBegin)
                .le(ageEnd!=null,"age",ageEnd);
        List<User> users = userMapper.selectList(queryWrapper);
        users.forEach(System.out::println);
    }
    @Test
    public void test11(){
        String username = "小";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> lambdaQueryWrapper =new LambdaQueryWrapper<>();
        lambdaQueryWrapper.like(StringUtils.isNotBlank(username),User::getName,username)
                .ge(ageBegin !=null,User::getAge,ageBegin)
                .le(ageEnd!=null,User::getAge,ageEnd);
        List<User> users = userMapper.selectList(lambdaQueryWrapper);
        users.forEach(System.out::println);
    }
    @Test
    public void test12(){
        //将用户名包含a 并且（年龄大于20或者邮箱为null)的用户信息修改
       LambdaUpdateWrapper<User> updateWrapper=new LambdaUpdateWrapper<>();
        updateWrapper.like(User::getName,"a")
                .and(i ->i.gt(User::getAge,20).or().isNull(User::getEmail));
        updateWrapper.set(User::getName,"小黑").set(User::getEmail,"xiaohei@wzj.com");
        int i = userMapper.update(null, updateWrapper);
        System.out.println(i
        );

    }
}
