package com.test.service.impl;

import com.test.dao.UserDao;
import com.test.entity.User;
import com.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserDao userDao;
    @Override
    public User findById(Integer id) {
        return userDao.findById(id);
    }
    @Override
    public User save(String name) {
        return userDao.save(new User(name));
    }
    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }

    @Override
    public List<User> findByIdGreaterThanAndNameLike(Integer id, String name) {
        return userDao.findByIdGreaterThanAndNameLike(id,name+"%");
    }

    @Override
    public List<User> findUserByName(String name) {
        return userDao.findUserByName(name);
    }

    @Override
    public Page<User> getUser(int pageNumber, int pageSize) {
        PageRequest request = this.buildPageRequest(pageNumber,pageSize);
        Page<User> users= this.userDao.findAll(request);
        return users;
    }
    //构建PageRequest
    private PageRequest buildPageRequest(int pageNumber, int pagzSize) {
        return new PageRequest(pageNumber - 1, pagzSize, null);
    }

    @Override
    public void delete(Integer id) {
        User user = new User();
        user.setId(id);
        userDao.delete(user);
    }
}
