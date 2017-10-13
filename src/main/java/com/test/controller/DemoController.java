package com.test.controller;

import com.test.entity.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/")
public class DemoController {
    @Autowired
    UserService userService;

    @RequestMapping("/login")
    public String info(Model model){
        List<User> users = userService.findAll();
        model.addAttribute("users",users);
        return "login";
    }
    @RequestMapping("/findall")
    @ResponseBody
    public Map<String, Object> getUser(){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.findAll());
        return map;
    }
    @RequestMapping("/findbyid")
    @ResponseBody
    public Map<String, Object> findById(Integer id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.findById(id));
        return map;
    }
    @RequestMapping("/add")
    @ResponseBody
    public Map<String, Object> save(String name){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.save(name));
        return map;
    }

    @RequestMapping("/findByLike")
    @ResponseBody
    public Map<String, Object> findByLike(Integer id,String name){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.findByIdGreaterThanAndNameLike(id,name));
        return map;
    }

    @RequestMapping("/findByName")
    @ResponseBody
    public Map<String, Object> findByName(String name){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        map.put("data", userService.findUserByName(name));
        return map;
    }

    @RequestMapping("/deleteUser")
    @ResponseBody
    public Map<String, Object> deleteUser(Integer id){
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("state", "success");
        try {
            userService.delete(id);
            map.put("data", 0);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("data", 1);
        }
        return map;
    }

    @RequestMapping(value = "list")
    public String listSourceCode(Model model,Integer pageNumber){
        if(pageNumber==null){
            pageNumber=1;
        }
        int pageSize = 3;
        Page<User> users = this.userService.getUser(pageNumber, pageSize);
        model.addAttribute("users",users.getContent());
        model.addAttribute("totalPageNumber",users.getTotalElements());
        model.addAttribute("pageSize",pageSize);
        return "login";
    }
}
