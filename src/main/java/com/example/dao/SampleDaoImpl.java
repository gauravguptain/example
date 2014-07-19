package com.example.dao;

import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;

@Repository
public class SampleDaoImpl implements SampleDao {

    @Nonnull
    @Override
    public String doSomething(@Nonnull String name) {
        return name;
    }
}
