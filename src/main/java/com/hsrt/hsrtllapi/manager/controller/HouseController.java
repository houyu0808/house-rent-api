package com.hsrt.hsrtllapi.manager.controller;

import com.hsrt.hsrtllapi.manager.dto.HouseDTO;
import com.hsrt.hsrtllapi.manager.service.HouseService;
import com.hsrt.hsrtllapi.manager.vo.HouseImgVO;
import com.hsrt.hsrtllapi.manager.vo.HouseVO;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/houseRent")
@Api(tags = "租房信息接口")
public class HouseController {

    @Autowired
    private HouseService houseService;

    @ApiOperation(value = "发布租房信息")
    @PostMapping("/houseInsert")
    public ResponseMessage houseInsert(@RequestBody HouseDTO houseDto){
        return houseService.houseInsert(houseDto);
    }

    @ApiOperation(value = "更新租房信息")
    @PostMapping("/houseUpdate")
    public ResponseMessage houseUpdate(@RequestBody HouseVO houseVO){
        return houseService.houseUpdate(houseVO);
    }

    @ApiOperation(value = "删除租房信息")
    @PostMapping("/houseDelete")
    public ResponseMessage houseDelete(@RequestBody List<Integer> idList){
        return houseService.houseDelete(idList);
    }

    @ApiOperation(value = "查询租房信息列表")
    @GetMapping("/houseSelect")
    public ResponseMessage houseSelect(String name,String userId, Pageable pageable){
        return houseService.houseSelect(name,userId,pageable);
    }

    @ApiOperation(value = "查询房源详细信息")
    @GetMapping("/houseDetail")
    public ResponseMessage houseDetail(@RequestParam Integer id){
        return houseService.houseDetail(id);
    }

    @ApiOperation(value = "审批房源信息")
    @PostMapping("/approvalHouse")
    public ResponseMessage approvalHouse(@RequestParam Integer id,@RequestParam Integer status){
        return houseService.approvalHouse(id,status);
    }

    @ApiOperation(value = "删除房源相册")
    @PostMapping("/deleteHouseImage")
    public ResponseMessage deleteHouseImage(@RequestBody List<Integer> idList){
        return houseService.deleteHouseImage(idList);
    }

    @ApiOperation(value = "添加房源相册")
    @PostMapping("/insertHouseImage")
    public ResponseMessage insertHouseImage(@RequestBody List<HouseImgVO> houseImgVOList){
        return houseService.insertHouseImage(houseImgVOList);
    }
}
