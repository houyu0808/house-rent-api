package com.hsrt.hsrtllapi.manager.entity;

import com.hsrt.hsrtllapi.utils.DTO.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Getter
@Setter
@Table(name = "db_house")
@Entity

public class House {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
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
