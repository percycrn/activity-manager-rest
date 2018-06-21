package com.usst.ten.demo.repository;

import com.usst.ten.demo.entity.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Integer> {
    List<Activity> findByTag(String tag);

    List<Activity> findByState(String state);

    Activity findByAid(Integer aid);

    Activity deleteByAid(Integer aid);
}
