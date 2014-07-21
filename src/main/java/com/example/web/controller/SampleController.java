package com.example.web.controller;

import com.example.bean.AppProperties;
import com.example.bean.SampleBean;
import com.example.bean.User;
import com.example.service.SampleService;
import com.example.service.UserService;
import com.example.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
public class SampleController {

    @Autowired
    private SampleService sampleService;

    @Autowired
    private UserService userService;

    private static final Logger LOG = LoggerFactory.getLogger(SampleController.class);

    @RequestMapping(
            value = "/sample/{otherName}",
            method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    @ResponseBody
    @Nonnull
    public SampleBean sample(
            @Nonnull @PathVariable String otherName,
            @Nullable @RequestParam(required = false) Integer index,
            @Nonnull @RequestBody SampleBean inputBean) {
        LOG.info("otherName: {}", otherName);
        LOG.info("index: {}", index);
        LOG.info("inputBean:\n{}", JsonUtils.toJson(inputBean));

        return sampleService.hello(inputBean,otherName, index);
    }

    @RequestMapping(
            value = "/app",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Nonnull
    public AppProperties app() {
        List<String> emails = new ArrayList<>();
        emails.add("gaurav.gupta@joshlabs.in");
        emails.add("gaurav.gupta.in@gmail.com");
        return new AppProperties("Example", "Staging", emails);
    }

    @RequestMapping(
            value = "/users",
            method = RequestMethod.GET
    )
    @ResponseBody
    @Nonnull
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

}
