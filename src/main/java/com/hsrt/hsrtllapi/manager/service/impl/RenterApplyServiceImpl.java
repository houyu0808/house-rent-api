package com.hsrt.hsrtllapi.manager.service.impl;

import com.hsrt.hsrtllapi.manager.dao.RenterApplyRepository;
import com.hsrt.hsrtllapi.manager.dao.UserRepository;
import com.hsrt.hsrtllapi.manager.entity.RenterApply;
import com.hsrt.hsrtllapi.manager.entity.User;
import com.hsrt.hsrtllapi.manager.service.RenterApplyService;
import com.hsrt.hsrtllapi.manager.vo.RenterApplyVO;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class RenterApplyServiceImpl implements RenterApplyService {

    @Autowired
    private RenterApplyRepository renterApplyRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseMessage applyToRenter(RenterApplyVO renterApplyVO) {
        try{
            RenterApply renterApply = new RenterApply();
            BeanUtils.copyProperties(renterApplyVO,renterApply);
            renterApplyRepository.save(renterApply);
            return ResponseMessage.ok("申请成功");
        }catch (Exception e){
            return ResponseMessage.error(400,"申请失败");
        }
    }

    @Override
    public ResponseMessage approvalApplyRenter(Integer id, Integer status) {
        RenterApply renterApply = renterApplyRepository.findById(id).get();
        if(!ObjectUtils.isEmpty(renterApply)){
            renterApply.setStatus(status);
            renterApplyRepository.save(renterApply);
            if(status == 1){
                User user = userRepository.findByUserId(renterApply.getUserId());
                user.setRole(3);
                userRepository.save(user);
            }
        }
        return ResponseMessage.ok("审核成功");
    }

    @Override
    public ResponseMessage deleteApply(List<Integer> idList) {
        List<RenterApply> renterApplyList = new ArrayList<>();
        for(Integer id: idList){
            RenterApply renterApply = renterApplyRepository.findById(id).get();
            renterApplyList.add(renterApply);
        }
        try {
            renterApplyRepository.deleteAll(renterApplyList);
        }catch (Exception e){
            return ResponseMessage.error(400,"删除失败");
        }
        return ResponseMessage.ok("删除成功");
    }

    @Override
    public ResponseMessage queryApplyByName(String realName, Pageable pageable) {
        return ResponseMessage.ok(renterApplyRepository.findByRealNameContaining(realName,pageable));
    }

    @Override
    public ResponseMessage renterApplyDetail(String userId) {
        RenterApply renterApply = renterApplyRepository.findByUserId(userId);
        if(ObjectUtils.isEmpty(renterApply)){
            return ResponseMessage.error(400,"信息不存在");
        }else{
            RenterApplyVO renterApplyVO = new RenterApplyVO();
            BeanUtils.copyProperties(renterApply,renterApplyVO);
            return ResponseMessage.ok(renterApplyVO);
        }
    }
}
