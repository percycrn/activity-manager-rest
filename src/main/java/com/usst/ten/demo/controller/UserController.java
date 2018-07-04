package com.usst.ten.demo.controller;

import com.usst.ten.demo.entity.Activity;
import com.usst.ten.demo.entity.Record;
import com.usst.ten.demo.entity.User;
import com.usst.ten.demo.enumerate.ActivityState;
import com.usst.ten.demo.pojo.Response;
import com.usst.ten.demo.pojo.UserInfo;
import com.usst.ten.demo.repository.*;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@RestController
public class UserController {
    private final UserRepository userRepo;
    private final ActivityRepository activityRepo;
    private final RecordRepository recordRepo;
    private final TagRepository tagRepo;
    private final ApplicationRepository applicationRepo;

    public UserController(UserRepository userRepo, ActivityRepository activityRepo, RecordRepository recordRepo,
                          TagRepository tagRepo, ApplicationRepository applicationRepo) {
        this.userRepo = userRepo;
        this.activityRepo = activityRepo;
        this.recordRepo = recordRepo;
        this.tagRepo = tagRepo;
        this.applicationRepo = applicationRepo;
    }

    // 查询用户信息
    @CrossOrigin
    @GetMapping(value = "/users/{uid}")
    public User getUserInfo(@PathVariable("uid") Integer uid) {
        return userRepo.findByUid(uid);
    }

    /**
     * 前端业务：用户：修改个人信息
     * 后端操作：通过ID向数据库user表中查询元组并更新
     * 相关接口：UserRepository
     * 算法实现:
     * 1.从前端获取待更新的用户信息：邮箱、地址
     * 2.通过uid查找相对应的用户元组，未找到则返回null
     * 3.实例化一个新的User对象
     * 4.通过userRepo的save()方法修改（覆盖）表中数据
     *
     * @param userInfo 用户信息：用户ID、用户Email、用户住址
     * @return 更新的用户的信息
     */
    @CrossOrigin
    @PutMapping(value = "/users/{uid}")
    public Response updateUserInfo(@PathVariable("uid") Integer uid, @RequestBody UserInfo userInfo) {
        User user = userRepo.findByUid(uid);
        if (user == null) {
            return new Response("user not exists", 400);
        }
        user.setAddress(userInfo.getAddress());
        user.setEmail(userInfo.getEmail());
        user.setName(userInfo.getName());
        userRepo.save(user);
        return new Response("success to update user info", 200);
    }

    /**
     * 前端业务：用户：活动报名
     * 后端操作：向数据库activityRecord表中插入一个新的元组
     * 相关接口：ActivityRecordRepository & ActivityRepository
     * 算法实现:
     * 1.从前端获取活动ID和用户ID
     * 2.实例化一个新的ActivityRecord的对象
     * 3.通过调用activityRecordRepo的save()方法保存新的活动记录(ActivityRecord)
     * 4.通过activityRepo的findById()方法获得该活动信息
     *
     * @return 用户报名的活动的信息
     */
    @CrossOrigin
    @PostMapping(value = "/users/{uid}/acts/{aid}/tags/{tag}")
    public Response createActivityUser(@PathVariable("uid") Integer uid, @PathVariable("aid") Integer aid,
                                       @PathVariable("tag") String tag) {
        Record record = new Record();
        record.setUid(uid);
        record.setAid(aid);
        record.setTag(tag);
        record.setSignInTime(System.currentTimeMillis());
        recordRepo.save(record);
        return new Response("success to create a new record", 200);
    }

    /**
     * 前端业务：用户：取消报名
     * 后端操作：通过用户ID和活动ID删除数据库activityRecord表中的一个元组
     * 相关接口：ActivityRepository & ActivityRecordRepository
     * 算法实现:
     * 1.从前端获取用户ID和活动ID
     * 2.通过activityRecordRepo自定义的deleteByActivityIdAndUserId()删除该元组
     * 3.通过activityRepo的findById()方法获得该活动信息
     *
     * @return 用户取消报名的活动的信息
     */
    @CrossOrigin
    @Transactional
    @DeleteMapping(value = "/users/{uid}/acts/{aid}")
    public Response deleteActivityUser(@PathVariable("uid") Integer uid, @PathVariable("aid") Integer aid) {
        if (recordRepo.deleteByUidAndAid(uid, aid) == 1) {
            return new Response("success to delete the record", 200);
        } else {
            return new Response("record not exists", 400);
        }
    }

    /**
     * 前端业务：用户：活动签到
     * 后端操作：通过用户ID和活动ID更新数据库activityRecord表中的一个元组
     * 相关接口：ActivityRecordRepository & ActivityRepository
     * 算法实现:
     * 1.从前端获取用户ID和活动ID
     * 2.实例化一个ActivityRecord对象
     * 3.通过activityRecordRepo的save()方法覆盖原来的活动记录
     * 4.通过activityRepo的findById()方法获得该活动信息
     *
     * @return 用户签到
     */
    @CrossOrigin
    @PatchMapping(value = "/users/{uid}/acts/{aid}")
    public Response updateActivityUser(@PathVariable("uid") Integer uid, @PathVariable("aid") Integer aid) {
        Record record = recordRepo.findByUidAndAid(uid, aid);
        if (record == null) {
            return new Response("record not exists", 400);
        }
        if (record.getSignUpTime() != null) {
            return new Response("already sign up", 400);
        }
        record.setSignUpTime(System.currentTimeMillis());
        recordRepo.save(record);
        return new Response("success to sign up(签到)", 200);
    }

    /**
     * 前端业务：用户：获得个人相关的活动信息
     * 后端操作：查询获得数据库activity表中和该用户相关的所有活动信息
     * 相关接口：ActivityRepository
     * 算法实现:
     * 1.从前端获得用户ID
     * 2.通过activityRepo的findById()的方法获得该用户的所有活动信息
     *
     * @return 某用户已参加的活动列表（包括已报名但未参加的和已参加的）
     */
    @CrossOrigin
    @GetMapping(value = "/users/{uid}/acts")
    public List<Activity> joinedActivity(@PathVariable("uid") Integer uid,
                                         @RequestParam("type") String type) {
        switch (type) {
            case "joined":
                List<Record> recordLists = recordRepo.findByUid(uid);
                List<Activity> activityLists = new ArrayList<>();
                for (Record record : recordLists) {
                    activityLists.add(activityRepo.findByAid(record.getAid()));
                }
                return activityLists;
            case "unjoined":
                return activityRepo.findByState(ActivityState.REGISTERING.toString());
            default:
                return null;
        }
    }
}
