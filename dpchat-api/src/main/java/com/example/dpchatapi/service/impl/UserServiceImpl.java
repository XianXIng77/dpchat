package com.example.dpchatapi.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.dpchatapi.entity.User;
import com.example.dpchatapi.entity.dto.UserDto;
import com.example.dpchatapi.exception.BaseException;
import com.example.dpchatapi.service.UserService;
import com.example.dpchatapi.mapper.UserMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.coyote.BadRequestException;
import org.springframework.stereotype.Service;

/**
* @author XianXing
* @description 针对表【user(用户表)】的数据库操作Service实现
* @createDate 2025-09-18 11:10:54
*/
@RequiredArgsConstructor
@Service
@Slf4j
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

    private final UserMapper userMapper;

    @Override
    public void UserLogin(UserDto userDto) {
        //1.构造查询条件
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        //2.查询用户是否存在
        queryWrapper.eq(User::getUsername, userDto.getUsername());
        //3.用户赋值
        User user = userMapper.selectOne(queryWrapper);
        if (user == null){
            throw new BaseException("当前用户不存在");
        }
        if (!user.getPassword().equals(userDto.getPassword())){
            throw new BaseException("密码错误");
        }
    }
}




