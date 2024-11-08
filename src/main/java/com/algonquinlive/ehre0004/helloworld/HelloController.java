package com.algonquinlive.ehre0004.helloworld;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HelloController implements ApplicationContextAware {

    public static final String message = "<!DOCTYPE html><html><head><title>Hello World</title><body><h1>Hello, $1!</h1></body></html>";
    private ApplicationContext context;

    @GetMapping(value = "/{name}", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String defaultMessage(@PathVariable String name) {
        return message.replace("$1", name);
    }

    @GetMapping(value = "/", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String defaultMessage() {
        return message.replace("$1", "world");
    }

    @GetMapping(value = "/abc/shutdown")
    public String shutdown() {
        ((ConfigurableApplicationContext)context).close();
        return "Done.";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

}
