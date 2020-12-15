package com.gwy.manager.security.authorize;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.*;
import java.io.IOException;

/**
 * @author Tracy
 * @date 2020/12/15 13:12
 */
@Component
public class AuthorizeSecurityInterceptor extends AbstractSecurityInterceptor implements Filter {

    @Autowired
    private AuthorizeSecurityMetadataSource authorizeSecurityMetadataSource;

    @Autowired
    private AuthorizeAccessDecisionManager authorizeAccessDecisionManager;

    @PostConstruct
    public void init() {
        super.setAccessDecisionManager(authorizeAccessDecisionManager);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        FilterInvocation fi = new FilterInvocation(request, response, chain);

        InterceptorStatusToken token = super.beforeInvocation(fi);

        try {
            fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
        } finally {
            super.afterInvocation(token, null);
        }
    }

    @Override
    public Class<?> getSecureObjectClass() {
        return FilterInvocation.class;
    }

    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.authorizeSecurityMetadataSource;
    }

    @Override
    public void init(FilterConfig filterConfig) {

    }

    @Override
    public void destroy() {

    }
}
