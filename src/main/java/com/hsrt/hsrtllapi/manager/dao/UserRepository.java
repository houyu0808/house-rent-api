package com.hsrt.hsrtllapi.manager.dao;

import com.hsrt.hsrtllapi.manager.entity.User;
import com.hsrt.hsrtllapi.manager.vo.UserVO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
    User findByUserId(String userId);

    Page<User> findByUsernameContaining(String username, Pageable pageable);

    User findByPhoneNumber(String phoneNumber);

    User findByEmail(String email);

    List<User> findAllByRole(Integer role,Pageable pageable);
}
