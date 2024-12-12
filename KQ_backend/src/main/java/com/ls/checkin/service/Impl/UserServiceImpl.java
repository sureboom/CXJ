package com.ls.checkin.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ls.checkin.entity.User;
import com.ls.checkin.mapper.UserMapper;
import com.ls.checkin.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserMapper , User> implements UserService {

    @Autowired
    private UserMapper userMapper;


    @Override
    public List<User> getAll() {
        Map<String , Object> map = new HashMap<>();
        map.put("name" , "666");
        return userMapper.selectByMap(map);
    }


}
