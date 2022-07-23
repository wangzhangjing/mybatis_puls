package com.wzj.mybatis_puls.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wzj.mybatis_puls.mapper.UserMapper;
import com.wzj.mybatis_puls.pojo.User;
import com.wzj.mybatis_puls.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceimpl extends ServiceImpl<UserMapper, User> implements UserService {


}
