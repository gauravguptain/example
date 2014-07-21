package com.example.service;

import com.example.bean.User;

import javax.annotation.Nonnull;
import java.util.List;

public interface UserService {

    @Nonnull
    List<User> getAllUsers();
}
