package com.example.dao;

import com.example.bean.User;

import javax.annotation.Nonnull;
import java.util.List;

public interface UserDao {

    @Nonnull
    List<User> getAllUsers();

    void addUser(@Nonnull User user);

}
