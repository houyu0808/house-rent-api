package com.hsrt.hsrtllapi.manager.service.impl;

import com.hsrt.hsrtllapi.manager.dao.HouseImgRepository;
import com.hsrt.hsrtllapi.manager.dao.HouseRepository;
import com.hsrt.hsrtllapi.manager.dao.UserRepository;
import com.hsrt.hsrtllapi.manager.dto.HouseDTO;
import com.hsrt.hsrtllapi.manager.entity.House;
import com.hsrt.hsrtllapi.manager.entity.HouseImg;
import com.hsrt.hsrtllapi.manager.entity.User;
import com.hsrt.hsrtllapi.manager.service.HouseService;
import com.hsrt.hsrtllapi.manager.vo.HouseImgVO;
import com.hsrt.hsrtllapi.manager.vo.HouseVO;
import com.hsrt.hsrtllapi.manager.vo.UserVO;
import com.hsrt.hsrtllapi.utils.response.ResponseMessage;
import lombok.Data;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import javax.transaction.Transactional;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Service
public class HouseServiceImpl implements HouseService {

    @Value("${oss.local.upload-file-path}")
    private String uploadFilePath;
    @Value("${path.uploadPath}")
    private String uploadPath;

    @Autowired
    private HouseRepository houseRepository;
    @Autowired
    private HouseImgRepository houseImgRepository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public ResponseMessage houseInsert(HouseDTO houseDto) {
        try {
            HouseVO houseVO = houseDto.getHouseVO();
            House house = new House();
            BeanUtils.copyProperties(houseVO,house);
            house.setStatus(0);
            houseRepository.save(house);
            List<HouseImg> houseImgList = new ArrayList<>();
            for(HouseImgVO houseImgVO:houseDto.getHouseImgVOList()){
                HouseImg houseImg = new HouseImg();
                BeanUtils.copyProperties(houseImgVO,houseImg);
                houseImg.setHouseId(house.getId());
                houseImgList.add(houseImg);
            }
            houseImgRepository.saveAll(houseImgList);
            return ResponseMessage.ok("提交成功");
        }catch (Exception e){
            System.out.println(e.toString());
            return ResponseMessage.ok("提交失败");
        }
    }

    @Override
    public ResponseMessage houseUpdate(HouseVO houseVO) {
        if(houseVO.getId() != null){
            House house = new House();
            BeanUtils.copyProperties(houseVO,house);
            houseRepository.save(house);
            return ResponseMessage.ok("更新成功");
        }else{
            return ResponseMessage.ok(400,"不存在当前记录");
        }
    }

    @Override
    public ResponseMessage houseDelete(List<Integer> idList) {
        List<House> houseList = new ArrayList<>();
        for(Integer id: idList){
            House house = houseRepository.findById(id).get();
            houseList.add(house);
        }
        try {
            houseRepository.deleteAll(houseList);
        }catch (Exception e){
            return ResponseMessage.error(400,"删除失败");
        }
        return ResponseMessage.ok("删除成功");
    }

    @Override
    public ResponseMessage houseSelect(String name,String userId, Pageable pageable) {
        Page<House> housePage = houseRepository.findAllByNameContainingAndUserIdContaining(name,userId,pageable);
        return ResponseMessage.ok(housePage);
    }

    @Override
    public ResponseMessage houseDetail(Integer id) {
        House house = houseRepository.findById(id).get();
        houseDetailDTO houseDetailDTO = new houseDetailDTO();
        HouseVO houseVO = new HouseVO();
        if(!ObjectUtils.isEmpty(house)){
            BeanUtils.copyProperties(house,houseVO);
            houseDetailDTO.setHouseVO(houseVO);
            List<HouseImg> houseImgList =  houseImgRepository.findAllByHouseId(id);
            List<HouseImgVO> houseImgVOList = new ArrayList<>();
            for(HouseImg houseImg:houseImgList){
                HouseImgVO houseImgVO = new HouseImgVO();
                BeanUtils.copyProperties(houseImg,houseImgVO);
                houseImgVOList.add(houseImgVO);
            }
            houseDetailDTO.setHouseImgVOList(houseImgVOList);
            User user = userRepository.findByUserId(house.getUserId());
            UserVO userVO = new UserVO();
            BeanUtils.copyProperties(user,userVO);
            houseDetailDTO.setUserVO(userVO);
            return ResponseMessage.ok(houseDetailDTO);
        }else{
            return ResponseMessage.error(400,"房源信息有误");
        }
    }

    @Override
    public ResponseMessage approvalHouse(Integer id, Integer status) {
        House house = houseRepository.findById(id).get();
        if(!ObjectUtils.isEmpty(house)){
            house.setStatus(status);
            houseRepository.save(house);
            return ResponseMessage.ok("审批成功");
        }else{
            return ResponseMessage.error(400,"房源信息有误");
        }
    }

    @Override
    public ResponseMessage deleteHouseImage(List<Integer> idList) {
        try{
            for(Integer id:idList){
                HouseImg houseImg = houseImgRepository.findById(id).get();
                houseImgRepository.delete(houseImg);
            }
        }catch (Exception e){
            return ResponseMessage.error("删除失败");
        }
        return ResponseMessage.ok("删除成功");
    }

    @Override
    public ResponseMessage insertHouseImage(List<HouseImgVO> houseImgVOList) {
        for(HouseImgVO houseImgVO:houseImgVOList){
            HouseImg houseImg = new HouseImg();
            BeanUtils.copyProperties(houseImgVO,houseImg);
            houseImgRepository.save(houseImg);
        }
        return ResponseMessage.ok("添加成功");
    }

    @Data
    public static class houseDetailDTO {
        private HouseVO houseVO;
        private List<HouseImgVO> houseImgVOList;
        private UserVO userVO;
    }
}
