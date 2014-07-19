package com.example.service;

import com.example.bean.SampleBean;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public interface SampleService {

    /**
     * Update name with name + other name + index
     * @param inputBean Input sample bean
     * @param otherName Other name
     * @param index Index
     * @return Modified Sample bean
     */
    @Nonnull
    SampleBean hello(@Nonnull SampleBean inputBean, @Nonnull String otherName, @Nullable Integer index);
}
