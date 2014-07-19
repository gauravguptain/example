package com.example.service;

import com.example.bean.SampleBean;
import com.example.dao.SampleDao;
import com.example.web.config.BeanConfig;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.kubek2k.springockito.annotations.ReplaceWithMock;
import org.kubek2k.springockito.annotations.SpringockitoAnnotatedContextLoader;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = SpringockitoAnnotatedContextLoader.class, classes = {BeanConfig.class})
public class SampleServiceImplTest {

    @Autowired
    private SampleService sampleService;

    @ReplaceWithMock
    @Autowired
    private SampleDao sampleDao;

    @Test
    public void testHello() throws Exception {
        // given
        SampleBean inputBean = new SampleBean("name");
        Mockito.when(sampleDao.doSomething("name")).thenReturn("name");

        // when
        SampleBean outputBean = sampleService.hello(inputBean, "othername", 1);

        // then
        Assert.assertEquals("name-othername-1", outputBean.getName());
        Mockito.verify(sampleDao, Mockito.times(1)).doSomething("name");
    }
}
