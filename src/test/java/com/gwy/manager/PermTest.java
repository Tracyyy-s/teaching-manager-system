package com.gwy.manager;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Tracy
 * @date 2020/12/3 15:11
 */
@SpringBootTest
public class PermTest {

    @Test
    void test01() {

        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin, delete, [a, b, c]");
        for (GrantedAuthority grantedAuthority : auth) {
            System.out.println(grantedAuthority);
        }

        System.out.println(auth.toString());
    }

    @Test
    void test02() {
        List<GrantedAuthority> auth = AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_admin, delete, ROLE_root");
        List<GrantedAuthority> auth2 = new ArrayList<>(auth.size());
        for (GrantedAuthority au : auth) {
            if (au.getAuthority().startsWith("ROLE_")) {
                auth2.add(au);
            }
        }
        System.out.println(auth2);
    }
}
