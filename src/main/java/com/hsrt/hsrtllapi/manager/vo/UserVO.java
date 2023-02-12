package com.hsrt.hsrtllapi.manager.vo;

import lombok.Data;

@Data
public class UserVO {
    private Integer id;
    private String userId;
    private String nickName;
    private String password;
    private String username;
    private String avatar;
    private int gender;
    private String phoneNumber;
    private String email;
    private Integer role;
}
