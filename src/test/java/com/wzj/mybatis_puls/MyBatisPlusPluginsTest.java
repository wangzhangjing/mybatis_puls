package com.wzj.mybatis_puls;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.wzj.mybatis_puls.mapper.ProductMapper;
import com.wzj.mybatis_puls.mapper.UserMapper;
import com.wzj.mybatis_puls.pojo.Product;
import com.wzj.mybatis_puls.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MyBatisPlusPluginsTest {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ProductMapper productMapper;

    @Test
    public void test1(){
        //所有page创建分页功能
        Page<User> page=new Page<>(2,3);
        Page<User> userPage = userMapper.selectPage(page, null);
        //获取当前页的数据 getCurrent当前页码 getsize每页显示条数
        System.out.println(userPage.getRecords());
        //获取总页数
        System.out.println(userPage.getPages());
        //获取总记录数
        System.out.println(userPage.getTotal());
        //有没有下一页
        System.out.println(userPage.hasNext());
        //有没有上一页
        System.out.println(userPage.hasPrevious());
    }
    @Test
    public void test2(){
        Page<User> page=new Page<>(1,3);
        Page<User> userPage = userMapper.selectPageVo(page, 20);
        //获取当前页的数据 getCurrent当前页码 getsize每页显示条数
        System.out.println(userPage.getRecords());
        //获取总页数
        System.out.println(userPage.getPages());
        //获取总记录数
        System.out.println(userPage.getTotal());
        //有没有下一页
        System.out.println(userPage.hasNext());
        //有没有上一页
        System.out.println(userPage.hasPrevious());
    }

     @Test
     //测试商品操作  乐观锁，悲观锁
    public void test3(){
        //小李查询商品价格
         Product productli = productMapper.selectById(1);
         System.out.println("小李查询的商品价格"+productli.getPrice());
         //小王查询商品价格
         Product productwang = productMapper.selectById(1);
         System.out.println("小王查询的商品价格"+productwang.getPrice());
         //小李将商品价格加50
         productli.setPrice(productli.getPrice()+50);
         productMapper.updateById(productli);
         //小王将商品价格减30
         productwang.setPrice(productwang.getPrice()-30);
         int i = productMapper.updateById(productwang);
         if(i==0){
             Product productnew = productMapper.selectById(1);
             productnew.setPrice(productnew.getPrice()-30);
             productMapper.updateById(productnew);
         }

         //老板查询商品价格
         Product productlb = productMapper.selectById(1);
         System.out.println("老板查询的商品价格"+productlb.getPrice());
     }
}
