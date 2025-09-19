package com.example.dpchatapi.controller;

import com.example.dpchatapi.entity.dto.UserDto;
import com.example.dpchatapi.result.Result;
import com.example.dpchatapi.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/ai")
@Slf4j
@Tag(name = "用户接口", description = "用户相关的接口")
public class UserController {

    private final UserService userService;

    @PostMapping("login")
    @Operation(summary = "用户登录", description = "用户pc端登录")
    public Result userLogin(@RequestBody UserDto userDto){
        log.info("用户登录:{}",userDto);
        userService.UserLogin(userDto);
        return Result.success();
    }
}
