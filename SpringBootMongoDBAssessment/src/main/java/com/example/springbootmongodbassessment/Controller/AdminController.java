package com.example.springbootmongodbassessment.Controller;

import com.example.springbootmongodbassessment.Model.Admin;
import com.example.springbootmongodbassessment.Service.AdminService;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;


@RestController
@RequestMapping("admins")
public class AdminController {
    private AdminService adminService;
    @Autowired
    private  AdminController(AdminService adminService){
        this.adminService=adminService;
    }
    @PostMapping(value = "/add-admin")
    public String Signup(@RequestBody Admin user){
        return adminService.signup(user);
    }
    @PostMapping(value = "/login")
    public String login(@RequestBody Map<String,Object> map){
        return  adminService.login(map.get("email").toString(), map.get("password").toString());
    }

}
