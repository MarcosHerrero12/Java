package com.abmc.abmc.Controller;

import com.abmc.abmc.Service.UserService;
import com.abmc.abmc.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class PersonController {
    @Autowired
    private UserService userService;

    @GetMapping("/findAllUsers")
    public List<Users> findAll(){
        return userService.findAll();
    }

    @GetMapping ("findById")
    public Users findById(@RequestParam int id){
        return userService.findById(id);

    }
    @PostMapping("/insertUser")
    public void insert(@RequestBody Users user){
        userService.insert(user);
    }
    @DeleteMapping("/deleteUser")
    public void delete(@RequestParam int id) {
        userService.deleteById(id);
    }
}
