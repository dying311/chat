package com.example.chat.Repository;

import com.example.chat.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    // 根据用户名查询用户
    User findByUsername(String username);

    // 获取所有用户名
    List<User> findAllBy();

    // 根据邮箱查询用户
    User findByEmail(String email);

    // 根据用户名和密码查询用户（用于登录验证）
    User findByUsernameAndPassword(String username, String password);
}

