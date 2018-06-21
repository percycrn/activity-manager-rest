package com.usst.ten.demo.repository;

import com.usst.ten.demo.entity.Record;
import com.usst.ten.demo.entity.RecordId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RecordRepository extends JpaRepository<Record, RecordId> {
    Record findByUidAndAid(Integer uid, Integer aid);

    List<Record> findByUid(Integer uid);

    int deleteByUidAndAid(Integer uid, Integer aid);
}
