package com.hsrt.hsrtllapi.manager.dao;

import com.hsrt.hsrtllapi.manager.entity.RenterApply;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RenterApplyRepository extends JpaRepository<RenterApply,Integer> {
    Page<RenterApply> findByRealNameContaining(String realName, Pageable pageable);

    RenterApply findByUserId(String userId);
}
