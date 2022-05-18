package com.woruide.dao;

import com.woruide.entity.Perms;
import com.woruide.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface UserDao {
    void save(User user);

    User findByUsername(String username);

    User findRoleByUsername(String username);

    //根据角色id查询权限集合
    Role findPermByRoleId(String id);
    //根据角色id查询权限集合
    List<Perms> findPermsByRoleId2(String id);
}
