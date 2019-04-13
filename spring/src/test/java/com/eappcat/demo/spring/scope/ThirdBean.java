package com.eappcat.demo.spring.scope;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.stereotype.Component;

import javax.inject.Provider;

@Component
public class ThirdBean {
    @Autowired
    SingletonBean singletonBean;
//    @Autowired
//    PrototypeBean prototypeBean;
//    @Autowired
//    Provider<PrototypeBean> prototypeBeanProvider;

    public SingletonBean getSingletonBean() {
        return singletonBean;
    }

//    public PrototypeBean getPrototypeBean() {
//        return prototypeBean;
//    }
//    public PrototypeBean getPrototypeBean() {
//        return prototypeBeanProvider.get();
//    }
    @Lookup
    public PrototypeBean getPrototypeBean() {
        return null;
    }
}
