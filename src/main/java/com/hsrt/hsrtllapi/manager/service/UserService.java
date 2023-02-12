package com.hsrt.hsrtllapi.manager.service;

import com.hsrt.hsrtllapi.manager.dto.UserDTO;
import com.hsrt.hsrtllapi.manager.entity.User;
import com.hsrt.hsrtllapi.manager.vo.UserVO;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    ResponseMessage getUserInfo(String userId);
    ResponseMessage registerUser(UserDTO userDto);
    ResponseMessage userLogin(UserDTO userDto);

    ResponseMessage managerLogin(UserDTO userDto);

    ResponseMessage getUserByName(String username, Pageable pageable);
    ResponseMessage deleteUserById(List<String> userIdList);

    ResponseMessage updateUserInfo(UserVO userVO);

    ResponseMessage getRenterList(Pageable pageable);

}
