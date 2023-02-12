package com.hsrt.hsrtllapi.manager.controller;


import com.hsrt.hsrtllapi.manager.dto.UserDTO;
import com.hsrt.hsrtllapi.manager.service.UserService;
import com.hsrt.hsrtllapi.manager.vo.UserVO;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@Api(tags = "用户接口")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "根据用户id获取用户信息")
    @GetMapping("/getUserInfoById")
    public ResponseMessage getUserInfo(@RequestParam String userId){
        return userService.getUserInfo(userId);
    }

    @ApiOperation(value = "用户注册")
    @PostMapping("/registerUser")
    public ResponseMessage registerUser(@RequestBody UserDTO userDto){
        return userService.registerUser(userDto);
    }

    @ApiOperation(value = "用户登录")
    @PostMapping("/userLogin")
    public ResponseMessage userLogin(@RequestBody UserDTO userDto){
        return userService.userLogin(userDto);
    }

    @ApiOperation(value = "后台登录")
    @PostMapping("/managerLogin")
    public ResponseMessage managerLogin(@RequestBody UserDTO userDto){return userService.managerLogin(userDto);}

    @ApiOperation(value = "根据用户名分页模糊查询")
    @GetMapping("/getUserInfoByName")
    public ResponseMessage getUserByName(String username, Pageable pageable){
        return userService.getUserByName(username,pageable);
    }

    @ApiOperation(value = "根据userid进行批量删除")
    @PostMapping("/deleteUserById")
    public ResponseMessage deleteUserById(@RequestBody List<String> userIdList){
        return userService.deleteUserById(userIdList);
    }

    @ApiOperation(value = "编辑用户信息")
    @PostMapping("/updateUserInfo")
    public ResponseMessage updateUserInfo(@RequestBody UserVO userVO){
        return userService.updateUserInfo(userVO);
    }

    @ApiOperation(value = "查询房东列表")
    @GetMapping("/getRenterList")
    public ResponseMessage getRenterList(Pageable pageable){
        return userService.getRenterList(pageable);
    }
}
