package com.myapp.Cart.controller;
import com.myapp.Cart.entity.User;
import com.myapp.Cart.services.UserSevices; // Ensure this is the correct import for your service

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/api") // Base URL for all endpoints in this controller
public class UserController {
    
    @Autowired
    private UserSevices userser; // Corrected the service class name

    // Endpoint to save a new user
    @PostMapping("/users") // Use POST for creating a resource
    public ResponseEntity<Map<String, Object>> createUser(@RequestBody User user) {
        userser.save(user); // Assuming save() method is correctly implemented in the service
        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "ok");
        response.put("data", user);
        return new ResponseEntity<>(response, HttpStatus.CREATED); // Return 201 Created
    }

    // Endpoint to retrieve a user by ID
    @GetMapping("/users/{id}") // Use GET for retrieving a resource
    public ResponseEntity<Map<String, Object>> getUser(@PathVariable("id") int userid) {
        User user = userser.getuser(userid); // Ensure getname() returns a User object
        
        if (user == null) {
            return new ResponseEntity<>(Map.of("status", "error", "message", "User not found"), HttpStatus.NOT_FOUND);
        }

        Map<String, Object> response = new LinkedHashMap<>();
        response.put("status", "ok");
        response.put("data", user);
        
        return new ResponseEntity<>(response, HttpStatus.OK); // Return 200 OK
    }
    
    
    
    @DeleteMapping("/users/{id}")
    public ResponseEntity<Map<String,Object>> delete(@PathVariable("id") int id){
    	Map<String,Object> res=new LinkedHashMap<String,Object>();
    	
    	User u=userser.getuser(id);
    	if (u==null) {
    	return new ResponseEntity<>(Map.of("not fouund","fine"),HttpStatus.NOT_FOUND);	
    	}
    	userser.delete(id);
    	return new ResponseEntity<>(Map.of("delted","succesfully"),HttpStatus.OK);
    	
    }

    @GetMapping("/users/findall")
    public ResponseEntity<Map<String,Object>> retriveall(){
    	Map<String,Object> res=new LinkedHashMap<>();
    	
    	List<User> users=userser.getAll();
    	res.put("data", users);
    	return new ResponseEntity<>(res,HttpStatus.OK);
    }
    
    
    @GetMapping("/users/finduser/{id}/{name}")
    public ResponseEntity<Map<String,Object>> retriveUserBynameandage(@PathVariable("id") int id,@PathVariable("name") String name){
    	Map<String,Object> res=new LinkedHashMap<>();
    	
    	User s=userser.getUserByNameAndAge(id, name);
    	
    	if (s==null) {
    		return new ResponseEntity<>(Map.of("entitiy not foudn","yeah"),HttpStatus.NOT_FOUND);
    	}
    	res.put("data", s);
    	return new ResponseEntity<>(res,HttpStatus.OK);
    }
    
    
    
    
    @PutMapping("/users/update/{id}")
    public ResponseEntity<Map<String,Object>> updateuser(@PathVariable("id") int id,@RequestBody User user){
    	
    	Map<String,Object> res=new LinkedHashMap<>();
    	
    	
    	User u=userser.getuser(id);
    	if (u==null){return new ResponseEntity<>(Map.of("status","not okay"),HttpStatus.NOT_FOUND);
    		
    	}
    	userser.updateUser(id,user.getName(),user.getAge());
    	
    	
		return new ResponseEntity<>(Map.of("statur","ok"),HttpStatus.OK);
    	
    }
}
