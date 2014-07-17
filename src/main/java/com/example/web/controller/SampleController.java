package com.example.web.controller;

import com.example.bean.AppProperties;
import com.example.bean.SampleBean;
import com.example.util.JsonUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

        String oldName = inputBean.getName();

        String newName = oldName + '-' + otherName;
        if (index != null) {
            newName += '-' + index.toString();
        }
        return new SampleBean(newName);
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

}
