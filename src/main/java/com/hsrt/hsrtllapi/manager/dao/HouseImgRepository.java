package com.hsrt.hsrtllapi.manager.dao;

import com.hsrt.hsrtllapi.manager.entity.HouseImg;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseImgRepository extends JpaRepository<HouseImg,Integer> {
    List<HouseImg> findAllByHouseId(Integer houseId);

    HouseImg findByImgUrl(String imgUrl);
}
