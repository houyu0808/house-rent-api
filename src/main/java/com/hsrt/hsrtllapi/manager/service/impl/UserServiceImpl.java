package com.hsrt.hsrtllapi.manager.service.impl;

import com.hsrt.hsrtllapi.manager.dao.RenterApplyRepository;
import com.hsrt.hsrtllapi.manager.dao.UserRepository;
import com.hsrt.hsrtllapi.manager.dto.UserDTO;
import com.hsrt.hsrtllapi.manager.dto.UserRenterDTO;
import com.hsrt.hsrtllapi.manager.entity.RenterApply;
import com.hsrt.hsrtllapi.manager.entity.User;
import com.hsrt.hsrtllapi.manager.service.UserService;
import com.hsrt.hsrtllapi.manager.vo.UserVO;
import com.hsrt.hsrtllapi.utils.randomUtil.RandomUtils;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import com.hsrt.hsrtllapi.utils.token.GenerateToken;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private HttpServletResponse httpServletResponse;

    @Autowired
    private RenterApplyRepository renterApplyRepository;

    @Override
    public ResponseMessage getUserInfo(String userId) {
        User user = userRepository.findByUserId(userId);
        if(ObjectUtils.isEmpty(user)){
            return ResponseMessage.error("未查询到该用户信息");
        }else{
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user,userVO);
            return ResponseMessage.ok(userVO);
        }
    }

    @Override
    public ResponseMessage registerUser(UserDTO userDto) {
        if(userDto.getAccount().isEmpty()){
            return  ResponseMessage.error(400,"账号不能为空");
        }
        if(userDto.getPassword().isEmpty()){
            return  ResponseMessage.error(400,"密码不能为空");
        }
        if(ObjectUtils.isEmpty(userRepository.findByUsername(userDto.getAccount()))){
            User user = new User();
            user.setUsername(userDto.getAccount());
            user.setPassword(userDto.getPassword());
            user.setUserId(RandomUtils.randomUtil());
            user.setRole(2);
            userRepository.save(user);
            return ResponseMessage.ok("注册成功");
        }else{
            return  ResponseMessage.error(400,"该用户名已存在，请重新输入");
        }
    }

    @Override
    public ResponseMessage userLogin(UserDTO userDto) {
        if(userDto.getAccount().isEmpty()){
            return  ResponseMessage.error(400,"账号不能为空");
        }
        if(userDto.getPassword().isEmpty()){
            return  ResponseMessage.error(400,"密码不能为空");
        }
        User user = userRepository.findByUsername(userDto.getAccount());
        if(ObjectUtils.isEmpty(user)){
            return  ResponseMessage.error(400,"该用户名不存在，请重新输入");
        }else{
            if(user.getPassword().equals(userDto.getPassword())){
                String jwt_token = "";
                jwt_token = GenerateToken.createToken(user.getUsername(),user.getUserId(),user.getRole().toString());
                httpServletResponse.addHeader("token",jwt_token);
                httpServletResponse.setHeader("Access-Control-Expose-Headers","token");
                UserVO userVO = new UserVO();
                BeanUtils.copyProperties(user,userVO);
                return ResponseMessage.ok(userVO,"登陆成功");
            }else{
                return ResponseMessage.error(400,"密码错误");
            }
        }
    }

    @Override
    public ResponseMessage managerLogin(UserDTO userDto) {
        if(userDto.getAccount().isEmpty()){
            return  ResponseMessage.error(400,"账号不能为空");
        }
        if(userDto.getPassword().isEmpty()){
            return  ResponseMessage.error(400,"密码不能为空");
        }
        User user = userRepository.findByUsername(userDto.getAccount());
        if(ObjectUtils.isEmpty(user)){
            return  ResponseMessage.error(400,"该用户名不存在，请重新输入");
        }else{
            if(user.getPassword().equals(userDto.getPassword())){
                //判断用户是否为管理员或者房东
                if(user.getRole().equals(1) || user.getRole().equals(3)){
                    String jwt_token = "";
                    jwt_token = GenerateToken.createToken(user.getUsername(),user.getUserId(),user.getRole().toString());
                    //响应头设置token
                    httpServletResponse.addHeader("token",jwt_token);
                    httpServletResponse.setHeader("Access-Control-Expose-Headers","token");
                    UserVO userVO = new UserVO();
                    BeanUtils.copyProperties(user,userVO);
                    return ResponseMessage.ok(userVO,"登陆成功");
                }else{
                    return ResponseMessage.error(400,"权限不足，登陆失败");
                }
            }else{
                return ResponseMessage.error(400,"密码错误");
            }
        }
    }

    @Override
    public ResponseMessage getUserByName(String username, Pageable pageable) {
        return ResponseMessage.ok(userRepository.findByUsernameContaining(username,pageable));
    }

    @Override
    public ResponseMessage deleteUserById(List<String> userIdList) {
        List<User> userList = new ArrayList<>();
        try{
            for (String userId:userIdList){
                User user = userRepository.findByUserId(userId);
                userList.add(user);
            }
            try {
                userRepository.deleteAll(userList);
            }catch (Exception e){
                return ResponseMessage.error(400,"删除失败");
            }
            return ResponseMessage.ok("删除成功");
        }catch(Exception e){
            return ResponseMessage.error("删除失败");
        }
    }

    @Override
    public ResponseMessage updateUserInfo(UserVO userVO) {
        User user = userRepository.findByUserId(userVO.getUserId());
        if(!ObjectUtils.isEmpty(userRepository.findByEmail(userVO.getEmail()))){
            return ResponseMessage.error(400,"该邮箱已被使用，请重新输入");
        }
        if(!ObjectUtils.isEmpty(userRepository.findByPhoneNumber(userVO.getPhoneNumber()))){
            return ResponseMessage.error(400,"该手机号已被使用，请重新输入");
        }
        if(ObjectUtils.isEmpty(user)){
            return ResponseMessage.error(400,"该用户信息不存在");
        }else{
            BeanUtils.copyProperties(userVO,user);
            try {
                userRepository.save(user);
            }catch (Exception e){
                return ResponseMessage.error(400,"保存失败");
            }
            return ResponseMessage.ok("更新成功");
        }
    }

    @Override
    public ResponseMessage getRenterList(Pageable pageable) {
        List<User> userList = userRepository.findAllByRole(3,pageable);
        List<UserRenterDTO> userRenterDTOList = new ArrayList<>();
        for (User user:userList){
            RenterApply renterApply = renterApplyRepository.findByUserId(user.getUserId());
            UserRenterDTO userRenterDTO = new UserRenterDTO();
            userRenterDTO.setUserId(user.getUserId());
            userRenterDTO.setNickName(user.getNickName());
            userRenterDTO.setUsername(user.getUsername());
            userRenterDTO.setAvatar(user.getAvatar());
            userRenterDTO.setGender(user.getGender());
            userRenterDTO.setPhoneNumber(user.getPhoneNumber());
            userRenterDTO.setEmail(user.getEmail());
            userRenterDTO.setRealName(renterApply.getRealName());
            userRenterDTO.setProvince(renterApply.getProvince());
            userRenterDTO.setCity(renterApply.getCity());
            userRenterDTO.setDistrict(renterApply.getDistrict());
            userRenterDTO.setIsComp(renterApply.getIsComp());
            userRenterDTO.setCompImgUrl(renterApply.getCompImgUrl());
            userRenterDTOList.add(userRenterDTO);
        }
        return ResponseMessage.ok(userRenterDTOList);
    }
}
