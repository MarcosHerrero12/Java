package com.abmc.abmc.Controller;

import com.abmc.abmc.Exceptions.ResourceNotFoundException;
import com.abmc.abmc.Service.UserService;
import com.abmc.abmc.entities.Users;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Users")
public class UserController {
    @Autowired
    private UserService userService;

    @Operation(summary = "gets all users")
    @GetMapping("/findAllUsers")
    public List<Users> findAll(){
        return userService.findAll();
    }

    @Operation(summary = "Gets a user by their ID", description = "Returns a user based on the given ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "User found",
                            content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Users.class)))})
    @GetMapping ("findById")
    public ResponseEntity<Users> findById(@RequestParam int id){
        Users user = userService.findById(id);
        if (user == null){
            throw new ResourceNotFoundException("User not found with ID :" + id);
        }
        return ResponseEntity.ok(user);
    }
    @Operation(summary = "Insert a new user")
    @PostMapping("/insertUser")
    public ResponseEntity<String> insert(@Valid @RequestBody Users user){
        userService.insert(user);
        return ResponseEntity.ok("User inserted correctly");
    }

    @Operation(summary = "Delete a user by their ID")
    @DeleteMapping("/deleteUser")
    public ResponseEntity<String> deleteById (@RequestParam int id) {
        userService.deleteById(id);
        return ResponseEntity.ok("Successfully deleted user");
    }
}
