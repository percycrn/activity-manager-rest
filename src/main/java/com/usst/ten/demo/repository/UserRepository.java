package com.usst.ten.demo.repository;

import com.usst.ten.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


//用户操作类，用于连接数据库中相应的用户表，并实现相应的数据操作
public interface UserRepository extends JpaRepository<User, String> {
    User findByPhoneNumber(String phoneNumber);

    User findByUid(Integer uid);
}
