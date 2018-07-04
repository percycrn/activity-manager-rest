package com.usst.ten.demo.controller;

import com.usst.ten.demo.entity.Tag;
import com.usst.ten.demo.pojo.Response;
import com.usst.ten.demo.repository.TagRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TagController {
    private final TagRepository tagRepo;

    public TagController(TagRepository tagRepo) {
        this.tagRepo = tagRepo;
    }

    // 添加申请的职位
    @CrossOrigin
    @PostMapping(value = "/tags")
    public Response createPosition(@RequestBody String position) {
        if (tagRepo.findByTag(position) != null) {
            return new Response("already has this position", 400);
        }
        Tag tag = new Tag();
        tag.setTag(position);
        tagRepo.save(tag);
        return new Response("success to create new position", 200);
    }

    // 获得所有职位
    @CrossOrigin
    @GetMapping(value = "/tags")
    public List<Tag> getTags() {
        return tagRepo.findAll();
    }
}
