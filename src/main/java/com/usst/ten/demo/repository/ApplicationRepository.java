package com.usst.ten.demo.repository;

import com.usst.ten.demo.entity.Application;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application, Integer> {
    List<Application> findByUid(Integer uid);

    Application findByUidAndTag(Integer uid, String tag);

    Application deleteByApid(Integer apid);

    Application findByApid(Integer apid);

    List<Application> findByState(String state);
}
