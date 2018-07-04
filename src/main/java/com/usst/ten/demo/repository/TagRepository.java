package com.usst.ten.demo.repository;

import com.usst.ten.demo.entity.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TagRepository extends JpaRepository<Tag, String> {
    Tag findByTag(String tag);
}
