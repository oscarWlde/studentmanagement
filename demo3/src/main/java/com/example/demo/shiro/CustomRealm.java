package com.example.demo.shiro;

import com.example.demo.bean.Student;
import com.example.demo.bean.Teacher;
import com.example.demo.mapper.Student_mapper;
import com.example.demo.mapper.Teacher_mapper;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.Set;

public class CustomRealm extends AuthorizingRealm {

    @Autowired
    private Student_mapper student_mapper;
    @Autowired
    private Teacher_mapper teacher_mapper;

    /**
     * 获取身份验证信息
     * Shiro中，最终是通过 Realm 来获取应用程序中的用户、角色及权限信息的。
     *
     * @param authenticationToken 用户身份信息 token
     * @return 返回封装了用户信息的 AuthenticationInfo 实例
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("————身份认证方法————");
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        // 从数据库获取对应用户名密码的用户
        System.out.println(token.getUsername()+"-----------");
        String stu_password = student_mapper.findByStu(token.getUsername());
       // String tea_password = teacher_mapper.findbyTea(token.getUsername());
//        if (stu_password==null&&tea_password==null) {
//            throw new AccountException("用户名不正确");
//        } else if (!stu_password.equals(new String((char[]) token.getCredentials()))&&!tea_password.equals(new String((char[]) token.getCredentials()))) {
//            throw new AccountException("密码不正确");
//        }
//        if(stu_password!=null) {
//            return new SimpleAuthenticationInfo(token.getPrincipal(), stu_password, getName());
//        }
//        else if(tea_password!=null){
//            return new SimpleAuthenticationInfo(token.getPrincipal(), tea_password, getName());
//        }
        return new SimpleAuthenticationInfo(token.getPrincipal(),token.getPassword(),getName());
    }

    /**
     * 获取授权信息
     *
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("————权限认证————");
        String username = (String) SecurityUtils.getSubject().getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        //获得该用户角色
        String role_stu = student_mapper.findByStu(username);
        System.out.println(role_stu.toString());
        String role_tea= teacher_mapper.findbyTea(username);
        Set<String> set = new HashSet<>();
        //需要将 role 封装到 Set 作为 info.setRoles() 的参数
        if(role_stu!=null){
        set.add("student");
        //设置该用户拥有的角色
        info.setRoles(set);
        return info;}
        else if(role_tea!=null){
            set.add("teacher");
            return info;
        }
        return info;
    }
}
