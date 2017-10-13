package com.test.service;

import com.test.entity.User;
import org.springframework.data.domain.Page;

import java.util.List;

public interface UserService {
    User findById(Integer id);
    User save(String name);
    void delete(Integer id);
    List<User> findAll();
    Page<User> getUser(int pageNumber, int pageSize);
    List<User> findByIdGreaterThanAndNameLike(Integer id,String name);
    List<User> findUserByName(String name);
}