package com.example.service;

import com.example.bean.User;
import com.example.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Nonnull
    @Override
    public List<User> getAllUsers() {
        return userDao.getAllUsers();
    }
}
