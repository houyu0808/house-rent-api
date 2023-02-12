package com.hsrt.hsrtllapi.manager.vo;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class HouseVO {
    private Integer id;
    private String name;
    private String userId;
    private String coverUrl;
    private String address;
    private String province;
    private String city;
    private String district;
    private String communityName;
    private BigDecimal longitude;
    private BigDecimal latitude;
    private BigDecimal area;
    private String towards;
    private Integer floor;
    private Integer elevator;
    private String type;
    private String bedroomDevice;
    private String communalDevice;
    private Integer rentFee;
    private Integer cashMonths;
    private Integer feeMonths;
    private String introduce;
    private Integer status;
    private Integer room;
    private Integer hall;
    private Integer toilet;
    private Integer clickTimes;
}
