package com.example.controller;

import com.example.entity.User;
import com.example.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.Map;

@Controller
public class UserController {

    @Autowired
    HttpSession httpSession;

    @Autowired
    UserMapper userMapper;

    @RequestMapping("/login")
    public String login() {
        return "login";
    }

    /**
     * loginCheck
     */
    @PostMapping("/checkUser")
    public String ckeckUser(User user, Model model, Map<String, Object> map) {
        User user1 = userMapper.selectByNameAndPwd(user);
        if (user1 != null) {
            httpSession.setAttribute("user", user1.getFullname());
            return "redirect:/users";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    @GetMapping("/users")
    public String list(Model model) {
        Collection<User> users = userMapper.selectAllUser();
        model.addAttribute("users", users);
        return "user/user";
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable("id") Integer id) {
        userMapper.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/user")
    public String toAddPage() {
        return "user/add";
    }

    @PostMapping("/user")
    public String addUser(User user) {
        user.setValid(1);
        userMapper.addUser(user);
        return "redirect:/users";
    }

    @GetMapping("/user/{id}")
    public String toEditPage(@PathVariable("id") Integer id, Model model) {
        User user = userMapper.selectById(id);
        model.addAttribute("user", user);
        return "user/edit";
    }

    @PutMapping("/user")
    public String editUser(User user) {
        userMapper.update(user);
        return "redirect:/users";
    }

}
