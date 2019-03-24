package com.eappcat.wechat.proxy.dao;

import com.eappcat.wechat.proxy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,String> {
}
