package com.abmc.abmc.Service;

import com.abmc.abmc.Exceptions.ResourceNotFoundException;
import com.abmc.abmc.Persistence.UserMapper;
import com.abmc.abmc.entities.Users;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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
        Users user = userMapper.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException("User not found with ID: " + id);
        }
        return user;
    }
    public void deleteById(int id){
        Users user = userMapper.findById(id);
        if (user == null) {
            throw new ResourceNotFoundException("Usuario no encontrado con ID: " + id);
        }
        userMapper.deleteById(id);
    }

    public ResponseEntity<String> insert(@Valid Users user){
        Users existingUser = userMapper.findByEmail(user.getEmail());
        if (existingUser != null) {
            throw new IllegalArgumentException("Email is already in use: " + user.getEmail());
        }
        userMapper.insert(user);
        return ResponseEntity.ok("User inserted correctly");
    }

}
