package com.pepaproch.massmailmailer.controlers;

import com.pepaproch.massmailmailer.db.entity.UserInfo;
import com.pepaproch.massmailmailer.security.UserLogin;
import java.util.Collection;
import org.springframework.context.ApplicationContext;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 *
 * @author Filip
 */
public class AppContext {

    private static ApplicationContext ctx;

    public static void setApplicationContext(ApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return ctx;
    }

    public static UserLogin getUserDetails() {
        UserLogin userLogin = null;
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if ((auth != null) && auth.getPrincipal() instanceof UserLogin) {
            userLogin = (UserLogin) auth.getPrincipal();

        }

        return userLogin;
    }

    public static Boolean isUserInRole(String role) {
        Boolean result = Boolean.FALSE;
        RoleHierarchy rh = (RoleHierarchyImpl) ctx.getBean("roleHierarchy");
        Collection<? extends GrantedAuthority> reachableGrantedAuthorities = rh.getReachableGrantedAuthorities(getUserDetails().getAuthorities());
        for (GrantedAuthority ga : reachableGrantedAuthorities) {
            if (ga.getAuthority().equalsIgnoreCase(role)) {
                result = Boolean.TRUE;
                break;
            }
        }

        return result;

    }

}
