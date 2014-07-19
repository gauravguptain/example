package com.example.service;

import com.example.bean.SampleBean;
import com.example.dao.SampleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

@Service
public class SampleServiceImpl implements SampleService {

    @Autowired
    private SampleDao sampleDao;

    @Nonnull
    @Override
    public SampleBean hello(@Nonnull SampleBean inputBean, @Nonnull String otherName, @Nullable Integer index) {
        String oldName = inputBean.getName();

        oldName = sampleDao.doSomething(oldName);

        String newName = oldName + '-' + otherName;
        if (index != null) {
            newName += '-' + index.toString();
        }

        return new SampleBean(newName);
    }
}
