package com.gwy.manager.security.listener;

import com.gwy.manager.util.DateUtilCustom;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationListener;
import org.springframework.security.authentication.event.AuthenticationSuccessEvent;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Component;

import java.util.Arrays;

/**
 * 自定义登录成功监听接口
 *
 * @author Tracy
 * @date 2020/11/23 16:27
 */
@Component
public class AuthenticationSucceedEventListener implements ApplicationListener<AuthenticationSuccessEvent> {

    private Logger logger = LoggerFactory.getLogger(AuthenticationSucceedEventListener.class);

    /**
     * 重写自定义登录成功监听方法
     * @param event 事件
     */
    @Override
    public void onApplicationEvent(AuthenticationSuccessEvent event) {
        String username = ((User) event.getAuthentication().getPrincipal()).getUsername();

        String ip = ((WebAuthenticationDetails) event.getAuthentication()
                .getDetails()).getRemoteAddress();

        String authorities = Arrays.toString(event.getAuthentication().getAuthorities().toArray());
        logger.info("username: {} login success!, authorities: {}, from ip: {}, at time: {}", username, authorities, ip, DateUtilCustom.getTime());
    }
}
