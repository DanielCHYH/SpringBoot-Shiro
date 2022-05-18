package com.woruide.service;

import com.woruide.dao.UserDao;
import com.woruide.entity.Perms;
import com.woruide.entity.User;
import com.woruide.util.SaltUtil;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Role;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
    @Autowired
    private UserDao userDao;

    @Override
    public void register(User user) {
        //1.获取随机盐
        String salt = SaltUtil.getSalt(8);
        //2.将随机盐保存到数据
        user.setSalt(salt);
        //3.明文密码进行md5 + salt + hash散列
       Md5Hash MD5 = new Md5Hash(user.getPassword(),salt,1024);
       user.setPassword(MD5.toHex());
       userDao.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userDao.findByUsername(username);
    }

    @Override
    public User findRoleByUsername(String username) {
        return userDao.findRoleByUsername(username);
    }

    @Override
    public Role findPermByRoleId(String id) {
        return userDao.findPermByRoleId(id);
    }

    @Override
    public List<Perms> findPermsByRoleId2(String id) {
        return userDao.findPermsByRoleId2(id);
    }
}
