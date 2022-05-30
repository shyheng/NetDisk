package com.example.net_file.controller;

import com.alibaba.fastjson.JSON;
import com.example.net_file.entity.User;
import com.example.net_file.service.IUserService;
import it.cosenonjaviste.crypto.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author shyheng
 * @since 2022-05-26
 */
@RestController
public class UserController {

    @Autowired
    IUserService userService;

    @PostMapping("/login")
    public String login(@RequestBody User user){
        List<User> list = userService.list();
        Map<String,Object> map = new HashMap<>();
        for (int i = 0; i <list.size(); i++) {
            if (list.get(i).getName().equals(user.getName())){
                if (list.get(i).getPass().equals(user.getPass())){
                    map.put("token",list.get(i).getId());
                    map.put("msg","登录成功");
                    map.put("flg",true);
                    return JSON.toJSONString(map);
                }
            }
        }
        map.put("flg",false);
        map.put("msg","密码错误");
        return JSON.toJSONString(map);
    }

    @PostMapping("/reg")
    public String reg(@RequestBody User user){
        Map<String,Object> map = new HashMap<>();
        List<User> list = userService.list();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getName().equals(user.getName())){
                map.put("msg","用户名重复");
                map.put("flg",false);
                return JSON.toJSONString(map);
            }
        }
        String hashed = BCrypt.hashpw(user.getPass(), BCrypt.gensalt());
        user.setToken(hashed);
        userService.save(user);
        map.put("msg","注册成功");
        map.put("flg",true);
        return JSON.toJSONString(map);
    }


}
