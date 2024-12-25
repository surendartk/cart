package com.myapp.Cart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import com.myapp.Cart.entity.*;

@Repository
public interface UserRepositor extends JpaRepository<User,Integer> {
	
	@Query("select u from User u where id=:id and name=:name")
	public User findByNameAndAge(@Param("id")int id,String name);
	
	@Modifying
	@Query("UPDATE User u SET u.age = :age, u.name = :name WHERE u.id = :id")
	public void updateUser(@Param("id") int id, @Param("name") String name, @Param("age") int age);


}
