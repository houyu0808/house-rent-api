package com.hsrt.hsrtllapi.manager.dto;

import com.hsrt.hsrtllapi.manager.vo.HouseImgVO;
import com.hsrt.hsrtllapi.manager.vo.HouseVO;
import lombok.Data;

import java.util.List;

@Data
public class HouseDTO {
    private HouseVO houseVO;
    private List<HouseImgVO> houseImgVOList;
}
