package sample.shiro;

import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import sample.dao.AuthUserRepository;
import sample.entity.AuthUser;
import sample.entity.Role;

import java.util.List;

/**
 * Created by liminghang on 1/17/17.
 */
public class MyShiroRealm extends AuthorizingRealm {

    @Autowired
    private AuthUserRepository authUserRepository;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {

        // To get the username, same to (String)principalCollection.fromRealm(getName()).iterator().next();
        String username = (String)super.getAvailablePrincipal(principalCollection);
        AuthUser user = authUserRepository.findByUsername(username);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //用户的角色集合
            info.setRoles(user.getRolesName());

            //用户的角色对应的所有权限，如果只使用角色定义访问权限，下面的两行可以不要
            List<Role> roleList = user.getRoleList();
            roleList.forEach(role -> info.addStringPermissions(role.getPermissionsName()));

            // 或者按下面这样添加
            //添加一个角色,不是配置意义上的添加,而是证明该用户拥有admin角色
//            simpleAuthorInfo.addRole("admin");
            //添加权限
//            simpleAuthorInfo.addStringPermission("admin:manage");
            return info;
        }
        return null;
    }


    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authenticationToken;
        AuthUser authUser = authUserRepository.findByUsername(token.getUsername());
        if (authUser != null) {
            // If exists, save the user into the SimpleAuthenticationInfo, Shrio will compare the password automatically later.
            return new SimpleAuthenticationInfo(authUser.getUsername(), authUser.getPassword(), "");
        }

        return null;
    }
}
