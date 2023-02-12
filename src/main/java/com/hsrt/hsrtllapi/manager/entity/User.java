package com.hsrt.hsrtllapi.manager.entity;

import com.hsrt.hsrtllapi.utils.DTO.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Table(name = "db_user")
@Entity
public class User {
    @Id
    @GeneratedValue(
            strategy = GenerationType.IDENTITY
    )
    private Integer id;
    private String userId;
    private String nickName;
    private String username;
    private String password;
    private String avatar;
    private int gender;
    private String phoneNumber;
    private String email;
    private Integer role;
    private Integer status;
}
