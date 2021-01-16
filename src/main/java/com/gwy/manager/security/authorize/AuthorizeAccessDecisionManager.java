package com.gwy.manager.security.authorize;

import com.gwy.manager.domain.enums.ResponseDataMsg;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

/**
 * @author Tracy
 * @date 2020/12/15 13:01
 */
@Component
public class AuthorizeAccessDecisionManager implements AccessDecisionManager {

    /**
     * 根据资源权限判断当前用户是否有权限操作
     * @param authentication    认证信息
     * @param object    请求对象
     * @param configAttributes  资源权限
     * @throws AccessDeniedException    accessDeniedException
     * @throws InsufficientAuthenticationException  InsufficientAuthenticationException
     */
    @Override
    public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

        for (ConfigAttribute resourceAttr : configAttributes) {
            // 资源的权限
            String resourceRole = resourceAttr.getAttribute();

            // 用户的权限
            for (GrantedAuthority userAuth : authentication.getAuthorities()) {
                if (resourceRole.trim().equals(userAuth.getAuthority().trim())) {
                    return;
                }
            }
        }

        //用户权限中未找到该资源应有的权限
        throw new AccessDeniedException(ResponseDataMsg.PermissionDeny.getMsg());
    }

    /**
     * 被AbstractSecurityInterceptor调用，遍历ConfigAttribute集合，筛选出不支持的attribute
     */
    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    /**
     * 被AbstractSecurityInterceptor调用，验证AccessDecisionManager是否支持这个安全对象的类型。
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
