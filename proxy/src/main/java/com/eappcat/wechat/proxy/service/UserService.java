package com.eappcat.wechat.proxy.service;

import com.eappcat.wechat.proxy.entity.User;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    void createUser();

    List<User> findAllUser();
}
