package com.example.dao;

import javax.annotation.Nonnull;

public interface SampleDao {

    /**
     * Do Something.
     */
    @Nonnull
    String doSomething(@Nonnull String name);
}
