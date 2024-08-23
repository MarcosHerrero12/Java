package com.abmc.abmc.Service;

import com.abmc.abmc.Persistence.UserMapper;
import com.abmc.abmc.entities.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
   @Autowired
   private UserMapper userMapper;

    public List<Users> findAll(){
        return userMapper.findAll();
    }
    public Users findById(int id){
        return userMapper.findById(id);
    }
    public void deleteById(int id){
        userMapper.deleteById(id);
    }
    public void insert(Users user){
        userMapper.insert(user);
    }

}
