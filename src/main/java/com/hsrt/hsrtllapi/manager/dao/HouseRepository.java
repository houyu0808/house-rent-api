package com.hsrt.hsrtllapi.manager.dao;

import com.hsrt.hsrtllapi.manager.entity.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HouseRepository extends JpaRepository<House, Integer> {
    Page<House> findAllByNameContainingAndUserIdContaining(String name,String userId,Pageable pageable);
}
