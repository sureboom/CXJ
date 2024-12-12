package com.ls.checkin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ls.checkin.entity.User;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {

    List<User> getAll();

}
