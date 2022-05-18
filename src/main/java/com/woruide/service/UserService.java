package com.woruide.service;

import com.woruide.entity.Perms;
import com.woruide.entity.User;
import org.springframework.context.annotation.Role;

import java.util.List;

public interface UserService {
    //注册用户方法
    void register(User user);
    //根据用户名查找用户
    User findByUsername(String username);

    User findRoleByUsername(String username);

    //根据角色id查询权限集合
    Role findPermByRoleId(String id);
    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId2(String id);

}
