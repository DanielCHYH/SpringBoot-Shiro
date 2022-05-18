package com.woruide.shiro.realm;

import com.woruide.entity.ApplicationContextUtil;
import com.woruide.entity.User;
import com.woruide.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.util.ObjectUtils;

/**
 * @ClassName CustomerRealm
 * @Description
 * @Author DanielChen
 * @Date 2022/5/18 17:11
 * @Version 1.0
 **/
public class CustomerRealm extends AuthorizingRealm {
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String principal = (String) principalCollection.getPrimaryPrincipal();
        if ("zhangsan".equals(principal)) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            info.addRole("admin");
            info.addRole("user");
            info.addStringPermission("user:find:*");
            info.addStringPermission("admin:*");
            return info;
        }
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String) authenticationToken.getPrincipal();
        //获取UserService对象
        UserService userService = (UserService) ApplicationContextUtil.getBean("userService");
        //System.out.println(userService);
        User user = userService.findByUsername(principal);
        if (!ObjectUtils.isEmpty(user)) {
            return new SimpleAuthenticationInfo(user.getUsername(), user.getPassword(), ByteSource.Util.bytes(user.getSalt()), this.getName());
        }
        return null;
    }
}
