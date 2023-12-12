package com.example.springbootmongodbassessment.Service;

import com.example.springbootmongodbassessment.Model.Admin;
import com.example.springbootmongodbassessment.Repository.AdminRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;



@Service
public class  AdminService{
    private AdminRepository adminRepository;
    private  TokenService tokenService;
    @Autowired
    public  AdminService(AdminRepository adminRepository,TokenService tokenService){
        this.adminRepository=adminRepository;
        this.tokenService=tokenService;
    }

    //Get an User
    public Admin getUser(ObjectId id){
        Optional<Admin> optionalAdmin=adminRepository.findById(id);
        return optionalAdmin.orElseGet(optionalAdmin::get);
    }
    
    //Signup an User
    public String signup(Admin user){
        Admin savedUser=adminRepository.save(user);
        return
                "{"+
                        "\"message\":"+"Succesfully Created User\",\n"+
                        "\"data\":"+savedUser+",\n"+
                        "}";

    }
    //Login an User
    public  String login(String email, String password){
        List<Admin> foundUsers=adminRepository.getUserByEmail(email);
        if(foundUsers.isEmpty()){
            return "Authentication Failed User Not Found";
        }else if(!foundUsers.get(0).getPassword().equals(password)){

            return "Password Incorrect";
        }
        return "{\n" +
                "\"message\":"+"\" Successfully Logged-in\",\n"+
                "\"data\": {\n"+" Name : "+foundUsers.get(0).getName()+",\n"+
                "Email : "+foundUsers.get(0).getEmail()+"\n"+
                "\"token\":\""+tokenService.createToken(foundUsers.get(0).getId())+"\"" +
                "}";
    }
}
