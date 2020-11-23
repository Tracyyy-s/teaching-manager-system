package com.gwy.manager.security;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * @author Tracy
 * @date 2020/11/23 18:52
 */
@Component
public class GlobalPasswordEncoder extends BCryptPasswordEncoder {
}
