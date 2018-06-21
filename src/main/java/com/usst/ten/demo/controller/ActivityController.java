package com.usst.ten.demo.controller;

import com.usst.ten.demo.entity.Activity;
import com.usst.ten.demo.enumerate.ActivityState;
import com.usst.ten.demo.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ActivityController {

    private final UserRepository userRepo;
    private final ActivityRepository activityRepo;
    private final RecordRepository recordRepo;
    private final TagRepository tagRepo;
    private final ApplicationRepository applicationRepo;

    public ActivityController(UserRepository userRepo, ActivityRepository activityRepo, RecordRepository recordRepo,
                              TagRepository tagRepo, ApplicationRepository applicationRepo) {
        this.userRepo = userRepo;
        this.activityRepo = activityRepo;
        this.recordRepo = recordRepo;
        this.tagRepo = tagRepo;
        this.applicationRepo = applicationRepo;
    }

    /**
     * 前端业务：管理员：添加新的activity
     * 后端操作：向数据库activity表中插入一个新的元组
     * 相关接口：ActivityRepository
     * 算法实现:
     * 1.从前端获取活动信息
     * 2.实例化一个Activity对象
     * 3.使用activityRepo自带的save()方法，将数据插入数据库
     *
     * @param activity 活动信息：用户ID、活动ID、活动信息
     * @return 创建的活动的信息
     */
    @CrossOrigin
    @PostMapping(value = "/acts")
    public String createActivity(@RequestBody Activity activity) {
        activity.setState(ActivityState.REGISTERING.toString());
        activity.setNow(0);
        activity.setSignUpTime(System.currentTimeMillis());
        activityRepo.save(activity);
        return "success to create new activity";
    }

    /**
     * 前端业务：管理员修改一个已存在的activity
     * 后端操作：通过ID修改数据库activity表中的一个元组
     * 相关接口：ActivityRepository
     * 算法实现:
     * 1.从前端获取活动信息
     * 2.实例化一个Activity对象
     * 3.使用activityRepo自带的save()方法，从数据库修改（覆盖）活动信息
     *
     * @param activity 活动信息：用户ID、活动ID、活动信息
     * @return 更新的活动的信息
     */
    @CrossOrigin
    @PutMapping(value = "/acts/{aid}")
    public String updateActivity(@RequestBody Activity activity,
                                 @PathVariable("aid") Integer aid) {
        Activity a = activityRepo.findByAid(aid);
        if (a == null) {
            return "aid not exists";
        }
        a.setName(activity.getName());
        a.setAddress(activity.getAddress());
        a.setStartTime(activity.getStartTime());
        a.setEndTime(activity.getEndTime());
        a.setMax(activity.getMax());
        a.setMin(activity.getMin());
        a.setSummary(activity.getSummary());
        a.setTag(activity.getTag());
        a.setState(activity.getState());
        activityRepo.save(a);
        return "success to modify the activity";
    }

    /**
     * 前端业务：管理员：删除一个已有的activity
     * 后端操作：通过ID删除数据库activity表中的一个元组
     * 相关接口：ActivityRepository
     * 算法实现:
     * 1.从前端获取待删除的活动ID
     * 2.使用activityRepo自带的deleteById()方法，从数据库中删除
     *
     * @return 删除的活动的信息
     */
    @CrossOrigin
    @DeleteMapping(value = "/acts/{aid}")
    public String deleteActivity(@PathVariable("aid") Integer aid) {
        if (activityRepo.deleteByAid(aid) == null) {
            return "aid not exists";
        } else {
            return "success to delete the activity";
        }
    }

    /**
     * 前端业务：用户：获得所有可报名的活动
     * 后端操作：查询获得数据库activity表中的所有可以报名的活动信息（通过state来判断是否可报名）
     * 相关接口：ActivityRepository
     * 算法实现:
     * 1.通过activityRepo的findByTag()方法获得所有可以报名的活动列表（state为REGISTERING）
     *
     * @return 所有允许用户报名的活动列表
     */
    @CrossOrigin
    @GetMapping(value = "/acts")
    public List<Activity> unJoinedActivity() {
        return activityRepo.findByState(ActivityState.REGISTERING.toString());
    }

}
