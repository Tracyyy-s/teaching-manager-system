package com.gwy.manager.security.listener;

import com.gwy.manager.util.DateUtilCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationFailureBadCredentialsEvent;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

/**
 * 自定义登录失败监听接口
 *
 * @author Tracy
 * @date 2020/11/23 16:28
 */
@Component
public class AuthenticationFailureEventListener implements ApplicationListener<AuthenticationFailureBadCredentialsEvent> {

    private Logger logger = LoggerFactory.getLogger(AuthenticationFailureEventListener.class);

    /**
     * 自定义登录失败监听方法
     * @param event 事件
     */
    @Override
    public void onApplicationEvent(AuthenticationFailureBadCredentialsEvent event) {

        Authentication authentication = event.getAuthentication();

        System.out.println(authentication);
        if (authentication != null) {
            String username = "";
            if (authentication.getPrincipal() != null) {
                username = authentication.getPrincipal().toString();
            }

            String ip = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();

            logger.error("username: {} login fail, from {}, at {}", username, ip, DateUtilCustom.getTime());
        }

    }
}
