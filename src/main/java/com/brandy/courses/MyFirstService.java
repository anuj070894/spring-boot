package com.brandy.courses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

@Service
@PropertySources({
        @PropertySource("classpath:custom.properties"),
        @PropertySource("classpath:custom-file-2.properties"),
})
public class MyFirstService {
//    @Autowired
//    @Qualifier("bean1")
    public MyFirstApplication myFirstApplication;
    public Environment environment;

    @Value("${my.custom.name}")
    public String customName;

    public String getCustomName2() {
        return customName2;
    }

    @Value("${my.prop}")
    public String customName2;

    @Value("${my.prop.2}")
    private String customName3;

    public String getCustomName() {
        return customName;
    }

//    @Autowired
//    MyFirstService(MyFirstApplication myFirstApplication) {
//        this.myFirstApplication = myFirstApplication;
//    }

//    @Autowired
//    public void injectDependencies(@Qualifier("bean1") MyFirstApplication myFirstApplication) {
//        this.myFirstApplication = myFirstApplication;
//    }


    @Autowired
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    public String getOsName() {
        return this.environment.getProperty("os.name");
    }

    public String getCustomProp(String prop) {
        return this.environment.getProperty(prop);
    }

    @Autowired
    public void setMyFirstApplication(@Qualifier("bean1") MyFirstApplication myFirstApplication) {
        this.myFirstApplication = myFirstApplication;
    }

    public String tellAStory() {
        return "The story is " + this.myFirstApplication.sayHello();
    }

    public String getCustomName3() {
        return customName3;
    }
}
