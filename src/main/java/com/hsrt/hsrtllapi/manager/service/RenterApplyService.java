package com.hsrt.hsrtllapi.manager.service;

import com.hsrt.hsrtllapi.manager.vo.RenterApplyVO;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RenterApplyService {
    ResponseMessage applyToRenter(RenterApplyVO renterApplyVO);

    ResponseMessage approvalApplyRenter(Integer id,Integer status);

    ResponseMessage deleteApply(List<Integer> idList);

    ResponseMessage queryApplyByName(String realName, Pageable pageable);

    ResponseMessage renterApplyDetail(String userId);
}
