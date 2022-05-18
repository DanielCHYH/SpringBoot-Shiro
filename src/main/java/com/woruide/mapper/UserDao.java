package com.woruide.mapper;

import com.woruide.entity.User;

/**
 * @ClassName UserDao
 * @Description
 * @Author DanielChen
 * @Date 2022/5/18 17:59
 * @Version 1.0
 **/
public interface UserDao {
    //根据用户名查询所有角色
    User findRolesByUserName(String username);

}
