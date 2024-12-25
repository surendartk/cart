package com.myapp.Cart.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myapp.Cart.entity.User;
import com.myapp.Cart.repository.UserRepositor;

import jakarta.transaction.Transactional;

@Service
public class UserSevices {
	@Autowired
	private UserRepositor userrepo;
	
	public void save(User user) {
		userrepo.save(user);
	}
	
	public User getuser(int id) {
		return userrepo.findById(id).get();
		
	}
	
	public void delete(int id) {
		userrepo.deleteById(id);
	}
	
	
	public List<User> getAll() {
		return userrepo.findAll();
	}
	
	public User getUserByNameAndAge(int id ,String name) {
		return userrepo.findByNameAndAge(id,name);
	}

	 @Transactional 
	public void updateUser(int id, String name, int age) {
	    userrepo.updateUser(id, name, age);
	}


}
