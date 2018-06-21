package com.usst.ten.demo.controller;

import com.usst.ten.demo.entity.Activity;
import com.usst.ten.demo.entity.Application;
import com.usst.ten.demo.enumerate.ApplicationState;
import com.usst.ten.demo.pojo.ApplicationInfo;
import com.usst.ten.demo.repository.*;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AppController {
    private final ApplicationRepository applicationRepo;

    public AppController(ApplicationRepository applicationRepo) {
        this.applicationRepo = applicationRepo;
    }

    /**
     * 前端业务：用户：申请新的身份
     * 后端操作：向数据库application表中插入一个元组
     * 相关接口：ApplicationRepository
     * 算法实现:
     * 1.从前端获取用户ID，标签tag和申请时间
     * 2.实例化一个Application对象
     * 3.通过applicationRep的save()方法插入该元组
     *
     * @return 用户新申请的信息
     */
    @CrossOrigin
    @PostMapping(value = "/users/{uid}/apps")
    public String createApp(@PathVariable("uid") Integer uid, @RequestBody ApplicationInfo applicationInfo) {
        Application application = new Application();
        if (applicationRepo.findByUidAndTag(uid, applicationInfo.getTag()) != null) {
            return "already apply this position";
        }
        application.setUid(uid);
        application.setApplyTime(System.currentTimeMillis());
        application.setTag(applicationInfo.getTag());
        application.setState(ApplicationState.PENDDING.toString());
        applicationRepo.save(application);
        return "success to apply";
    }

    /**
     * 前端业务：用户：取消申请
     * 后端操作：通过用户ID和申请tag删除数据库application表中的一个元组
     * 相关接口：ApplicationRepository
     * 算法实现:
     * 1.从前端获取用户ID和申请tag
     * 2.通过applicationRepo的deleteByUidAndTag()方法删除该元组
     *
     * @return 用户删除的申请的信息
     */
    @CrossOrigin
    @DeleteMapping(value = "/users/apps/{apid}")
    public String deleteApp(@PathVariable("apid") Integer apid) {
        if (applicationRepo.deleteByApid(apid) == null) {
            return "application not exists";
        } else {
            return "success to delete the application";
        }
    }

    // 查询申请
    @CrossOrigin
    @GetMapping(value = "/users/{uid}/apps")
    public List<Application> queryApp(@PathVariable("uid") Integer uid) {
        System.out.println(uid);
        if (uid == -1) {
            return applicationRepo.findByState(ApplicationState.PENDDING.toString());
        } else {
            return applicationRepo.findByUid(uid);
        }
    }

    /**
     * 前端业务：管理员审核申请表
     * 后端操作：通过用户ID和申请状态更新数据库application表中的元组
     * 相关接口：ApplicationRepository
     * 算法实现:
     * 1.从前端获取用户ID，申请状态
     * 2.实例化一个Application的对象
     * 3.通过使用applicationRepo的save()方法覆盖原来的元组
     *
     * @param applicationInfo 申请信息：用户ID、标签名、申请时间、申请状态
     * @return 管理员更新用户申请状态后的申请的信息
     */
    @CrossOrigin
    @PatchMapping(value = "/apps/{apid}")
    public String updateApp(@PathVariable("apid") Integer apid, @RequestBody ApplicationInfo applicationInfo) {
        Application application = applicationRepo.findByApid(apid);
        if (application == null) {
            return "application not exists";
        }
        application.setState(applicationInfo.getState());
        applicationRepo.save(application);
        return "success to update the application";
    }

}
