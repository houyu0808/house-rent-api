package com.hsrt.hsrtllapi.manager.vo;

import lombok.Data;

@Data
public class RenterApplyVO {
    private Integer id;
    private String userId;
    private String realName;
    private String province;
    private String city;
    private String district;
    private Integer isComp;
    private String compImgUrl;
}
