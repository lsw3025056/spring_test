package com.test.dao;

import com.test.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.io.Serializable;
import java.util.List;

public interface UserDao extends JpaRepository<User, Serializable> {
    User findById(Integer id);
    List<User> findByIdGreaterThanAndNameLike(Integer id, String name);
    @Query("select id,name from User where name = ?1")
    List<User> findUserByName(String name);
}