package com.crhms.security.authorizationserver.security;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.crhms.security.authorizationserver.config.newauth.Permission;
import com.crhms.security.authorizationserver.config.newauth.PermissionDao;
import com.crhms.security.authorizationserver.config.newauth.UserDao;
import com.crhms.security.authorizationserver.model.SysUser;
import com.example.common.LoginUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author ：hkk
 * @date ：Created in 2019/10/10 16:35
 */

@Service
@Primary
public class UserDetailServiceImpl implements UserDetailsService {

    //    @Override
//    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        LoginUser user = new LoginUser("xieshaoping", "123", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN")));
//        user.setUuid("唯一标识");
//        return user;
//
//        //throw new UsernameNotFoundException("用户名不存在");
//    }
    @Autowired
    private UserDao userDao;

    @Autowired
    private PermissionDao permissionDao;

    @Override
    public UserDetails loadUserByUsername(String username) {
        QueryWrapper<SysUser> lqw = new QueryWrapper<>();
        lqw.lambda().eq(SysUser::getUsername, username);
        SysUser user = userDao.selectOne(lqw);
        if (user != null) {

            List<Permission> permissions = permissionDao.findByAdminUserId(user.getId());
            List<GrantedAuthority> grantedAuthorities = new ArrayList<>();
            for (Permission permission : permissions) {
                if (permission != null && permission.getName() != null) {

                    GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(permission.getName());
                    //1：此处将权限信息添加到 GrantedAuthority 对象中，在后面进行全权限验证时会使用GrantedAuthority 对象。
                    grantedAuthorities.add(grantedAuthority);
                }
            }
            return new LoginUser(user.getUsername(), user.getPassword(), grantedAuthorities);
        } else {
            throw new UsernameNotFoundException("admin: " + username + " do not exist!");
        }
    }


}
