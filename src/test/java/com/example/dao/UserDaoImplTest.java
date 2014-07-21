package com.example.dao;

import com.example.bean.User;
import com.example.web.config.BeanConfig;
import com.github.springtestdbunit.DbUnitTestExecutionListener;
import com.github.springtestdbunit.annotation.DatabaseSetup;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {BeanConfig.class})
@TestExecutionListeners({ DependencyInjectionTestExecutionListener.class,
        DbUnitTestExecutionListener.class })
public class UserDaoImplTest {

    @Autowired
    private UserDao userDao;

    @Test
    @DatabaseSetup("UserData.xml")
    public void testGetAllUsers() throws Exception {
        List<User> users = userDao.getAllUsers();
        Assert.assertEquals(2, users.size());
    }
}
