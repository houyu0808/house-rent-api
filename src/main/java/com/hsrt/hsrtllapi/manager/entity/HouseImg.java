package com.hsrt.hsrtllapi.manager.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "db_house_img")
@Entity

public class HouseImg {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String imgUrl;
    private Integer houseId;
}
