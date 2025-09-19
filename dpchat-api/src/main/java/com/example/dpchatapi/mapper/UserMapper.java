package com.example.dpchatapi.mapper;

import com.example.dpchatapi.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;


/**
* @author XianXing
* @description 针对表【user(用户表)】的数据库操作Mapper
* @createDate 2025-09-18 11:10:54
* @Entity com.example.dpchatapi.entity.User
*/
@Mapper
public interface UserMapper extends BaseMapper<User> {

}




