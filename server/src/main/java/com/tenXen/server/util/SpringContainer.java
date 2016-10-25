package com.tenXen.server.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Service;

public class SpringContainer implements ApplicationContextAware {
    private static ApplicationContext applicationContext;

    public static void init() {
        applicationContext = new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        for (String name : applicationContext.getBeanDefinitionNames()) {
            System.out.println(name);
        }
    }

    public static <T> T getBean(Class<T> paramClass) {
        return applicationContext.getBean(paramClass);
    }

    public static Object getBean(String paramString) {
        return applicationContext.getBean(paramString);
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        SpringContainer.applicationContext = applicationContext;
    }
}
