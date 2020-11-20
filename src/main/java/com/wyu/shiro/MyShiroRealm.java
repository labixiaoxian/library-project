package com.wyu.shiro;

import com.wyu.entity.User;
import com.wyu.exception.UserException;
import com.wyu.service.user.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ObjectUtils;



/**
 * Created by XiaoXian on 2020/11/18.
 */
public class MyShiroRealm extends AuthorizingRealm  {

    @Autowired
    private UserService userService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        String primaryPrincipal = (String) principalCollection.getPrimaryPrincipal();
        //根据用户名获取角色和权限信息
        User user = userService.findRolesByUsername(primaryPrincipal);
        System.out.println("调用授权验证: "+primaryPrincipal);
        //角色授权
        if (!CollectionUtils.isEmpty(user.getRoles())) {
            SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
            user.getRoles().forEach(role -> {
                authorizationInfo.addRole(role.getRoleName());
                System.out.println(role.getRoleName());
            });
            return authorizationInfo;
        }
        return null;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String principal = (String)authenticationToken.getPrincipal();
        User user = userService.findUserByUsername(principal);
        if(user.getStatus()==0){
            throw new UserException("用户失效");
        }
        if (!ObjectUtils.isEmpty(user)) {
            SimpleAuthenticationInfo simpleAuthenticationInfo = new SimpleAuthenticationInfo(user.getUsername(), user.getPwdHash(),
                    new MyByteSource(user.getPwdSalt()), this.getName());
            Session session =  SecurityUtils.getSubject().getSession();
            session.setAttribute("USER_SESSION", user);
            return simpleAuthenticationInfo;
        }
        return null;
    }
}
