package com.hsrt.hsrtllapi.manager.service;

import com.hsrt.hsrtllapi.manager.dto.HouseDTO;
import com.hsrt.hsrtllapi.manager.vo.HouseImgVO;
import com.hsrt.hsrtllapi.manager.vo.HouseVO;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import io.swagger.models.auth.In;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface HouseService {
    ResponseMessage houseInsert(HouseDTO houseDto);

    ResponseMessage houseUpdate(HouseVO houseVO);

    ResponseMessage houseDelete(List<Integer> idList);

    ResponseMessage houseSelect(String name,String userId, Pageable pageable);

    ResponseMessage houseDetail(Integer id);

    ResponseMessage approvalHouse(Integer id,Integer status);

    ResponseMessage deleteHouseImage(List<Integer> idList);

    ResponseMessage insertHouseImage(List<HouseImgVO> houseImgVOList);
}
