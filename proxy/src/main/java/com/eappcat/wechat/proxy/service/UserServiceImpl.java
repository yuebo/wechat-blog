package com.eappcat.wechat.proxy.service;

import com.eappcat.wechat.proxy.dao.UserRepository;
import com.eappcat.wechat.proxy.entity.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository dao;
    @Transactional
    public void createUser(){
        log.info("class:{}",this.getClass());
        dao.save(new User("aaa","aaa",1));
        throw new RuntimeException("test rollback");
    }
    @Override
    public List<User> findAllUser(){
        return dao.findAll();
    }
}
