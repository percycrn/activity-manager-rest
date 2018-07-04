package com.usst.ten.demo.controller;

import com.usst.ten.demo.entity.User;
import com.usst.ten.demo.pojo.Response;
import com.usst.ten.demo.pojo.SignData;
import com.usst.ten.demo.pojo.SignInResponse;
import com.usst.ten.demo.repository.*;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

@RestController
public class ServiceController {

    private final UserRepository userRepo;

    public ServiceController(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    /**
     * 前端业务：用户：登录
     * 后端操作：向数据库查询相应的手机号和密码
     * 相关接口：UserRepository & ActivityRecordRepository & ActivityRepository
     * 算法实现:
     * 1.从前端获取登录的手机号和密码
     * 2.通过userRepo的findByPhoneNumber()方法查询user表中数据，获得用户信息
     * 3.对比password正确与否返回信息，信息正确返回的用户对象不为null，不正确返回的errorMessage有内容
     *
     * @param signData 用户登录信息：手机号、密码
     * @return 用户信息，错误信息和用户已参加的信息
     */
    @CrossOrigin
    @PostMapping(value = "/signin")
    public SignInResponse signIn(@RequestBody SignData signData) {
        User user = userRepo.findByPhoneNumber(signData.getPhoneNumber());
        if (user == null || !user.getPassword().equals(signData.getPassword())) {
            return new SignInResponse("incorrect account or password", 400, -1);
        }
        return new SignInResponse("success to sign in", 200, user.getUid());
    }

    /**
     * 前端业务：用户：注册
     * 后端操作：向数据库user表中插入一个新的元组
     * 相关接口：UserRepository
     * 算法实现:
     * 1.从前端获取注册信息：手机号和密码
     * 2.通过userRepo的findByPhoneNumber()方法查询数据库user表中是否存在相同phoneNumber的元组，找到则返回错误信息(errorMessage)
     * 3.实例化一个新的User对象
     * 4.用userRepo的save()方法向表中插入新的元组
     *
     * @param signData 用户登录信息：手机号、密码
     * @return 用户信息，错误信息和用户已参加的信息
     */
    @CrossOrigin
    @PostMapping(value = "/signup")
    public Response signUp(@RequestBody SignData signData) {
        if (userRepo.findByPhoneNumber(signData.getPhoneNumber()) != null) {
            return new Response("account already exists", 400);
        }
        User user = new User();
        user.setTags("student");
        user.setPhoneNumber(signData.getPhoneNumber());
        user.setPassword(signData.getPassword());
        userRepo.save(user);
        return new Response("success to sign up", 200);
    }
}
