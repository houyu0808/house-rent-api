package com.hsrt.hsrtllapi.manager.entity;

import com.hsrt.hsrtllapi.utils.DTO.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "db_renter_apply")
@Entity

public class RenterApply {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;

    private String userId;
    private String realName;
    private String province;
    private String city;
    private String district;
    private Integer isComp;
    private String compImgUrl;
    private Integer status;
}
