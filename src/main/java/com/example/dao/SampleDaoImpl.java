package com.example.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;

@Repository
public class SampleDaoImpl implements SampleDao {

    @Autowired
    private JdbcOperations jdbcOperations;

    @Nonnull
    @Override
    public String doSomething(@Nonnull String name) {
        return name;
    }
}
