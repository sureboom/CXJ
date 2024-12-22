package com.ls.checkin.service;


import com.baomidou.mybatisplus.extension.service.IService;
import com.ls.checkin.entity.User;

import java.util.List;

public interface UserService extends IService<User> {
    List<User> getAll();
}
