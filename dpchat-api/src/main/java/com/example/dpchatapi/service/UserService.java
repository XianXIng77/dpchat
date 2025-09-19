package com.example.dpchatapi.service;

import com.example.dpchatapi.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.dpchatapi.entity.dto.UserDto;

/**
* @author XianXing
* @description 针对表【user(用户表)】的数据库操作Service
* @createDate 2025-09-18 11:10:54
*/
public interface UserService extends IService<User> {

    void UserLogin(UserDto userDto);
}
