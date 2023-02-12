package com.hsrt.hsrtllapi.manager.controller;

import com.hsrt.hsrtllapi.manager.service.RenterApplyService;
import com.hsrt.hsrtllapi.manager.vo.RenterApplyVO;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/renterApply")
@Api(tags = "房东申请接口")
public class RenterApplyController {

    @Autowired
    private RenterApplyService renterApplyService;

    @ApiOperation(value = "查询申请列表")
    @GetMapping("/queryApplyList")
    public ResponseMessage queryApplyList(String realName, Pageable pageable){
        return renterApplyService.queryApplyByName(realName,pageable);
    }

    @ApiOperation(value = "用户申请成为房东")
    @PostMapping("/applayToRenter")
    public ResponseMessage applyToRenter(@RequestBody RenterApplyVO renterApplyVO){
        return renterApplyService.applyToRenter(renterApplyVO);
    }

    @ApiOperation(value="审批房东申请")
    @PostMapping("/approvalApplyRenter")
    public ResponseMessage approvalApplyRenter(@RequestParam Integer id,@RequestParam Integer status){
        return renterApplyService.approvalApplyRenter(id,status);
    }

    @ApiOperation(value="删除房东申请")
    @PostMapping("/deleteApply")
    public ResponseMessage deleteApply(@RequestBody List<Integer> idList){
        return renterApplyService.deleteApply(idList);
    }

    @ApiOperation(value = "查询申请详细")
    @GetMapping("/queryApplyDetail")
    public ResponseMessage queryApplyDetail(String userId){
        return renterApplyService.renterApplyDetail(userId);
    }
}
