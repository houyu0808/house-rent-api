package com.hsrt.hsrtllapi.manager.dto;

import lombok.Data;

@Data
public class UserRenterDTO {
    private String userId;
    private String nickName;
    private String username;
    private String avatar;
    private int gender;
    private String phoneNumber;
    private String email;
    private Integer role;
    private String realName;
    private String province;
    private String city;
    private String district;
    private Integer isComp;
    private String compImgUrl;
}
