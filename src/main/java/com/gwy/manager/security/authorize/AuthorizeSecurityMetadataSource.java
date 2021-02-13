package com.gwy.manager.security.authorize;

import com.gwy.manager.domain.constant.PassRequestPaths;
import com.gwy.manager.domain.entity.Permission;
import com.gwy.manager.domain.entity.SysPermissionApi;
import com.gwy.manager.mapper.PermissionMapper;
import com.gwy.manager.mapper.SysPermissionApiMapper;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.security.web.util.matcher.RequestMatcher;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author Tracy
 * @date 2020/12/14 19:49
 */
//@Component
public class AuthorizeSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final String POST = "POST";

    private static Map<RequestMatcher, Collection<ConfigAttribute>> resourceMap = null;

    @Autowired
    private SysPermissionApiMapper apiMapper;

    @Autowired
    private PermissionMapper permissionMapper;

    /**
     * 在Web服务器启动时，提取系统中的所有权限。
     * 先找出所有的资源url列表
     * 然后遍历每个资源url，循环执行如下操作
     * 根据匹配关系找到对应的角色列表
     * 将匹配的角色列表封装，存入map
     * 被@PostConstruct修饰的方法会在服务器加载Servle的时候运行，并且只会被服务器执行一次。PostConstruct在构造函数之后执行,init()方法之前执行。
     */
    @PostConstruct
    private void loadResourceDefine() {
        resourceMap = new HashMap<>(20);

        // null 表示当前api不需要授权，可以被匿名用户访问
        resourceMap.put(new AntPathRequestMatcher(PassRequestPaths.ROOT_REQUEST, null), null);
        resourceMap.put(new AntPathRequestMatcher(PassRequestPaths.LOGIN_REQUEST, null), null);
        resourceMap.put(new AntPathRequestMatcher(PassRequestPaths.SEND_CODE_REQUEST, POST), null);
        resourceMap.put(new AntPathRequestMatcher(PassRequestPaths.UPDATE_PASSWORD_REQUEST, POST), null);

        //存储api和permission的对应关系
        //key: api   value: 权限列表
        Map<String, List<String>> apiMap = new HashMap<>(20);

        List<SysPermissionApi> permissionApis = apiMapper.selectAll();
        Map<Integer, Permission> permissionMap = permissionMapper.selectAllForMap();

        //遍历所有接口
        for (SysPermissionApi permissionApi : permissionApis) {

            //若map中无此api，将其添加至map中
            apiMap.putIfAbsent(permissionApi.getApi(), new ArrayList<>());

            //获得该api接口的权限list
            List<String> permissionsOfApi = apiMap.get(permissionApi.getApi());

            //当前api接口需要的权限
            Permission needPermission = permissionMap.get(permissionApi.getPermissionId());

            //添加url至api对应的list中
            permissionsOfApi.add(needPermission.getPermissionUrl());
        }

        //遍历所有接口-权限关系
        for (Map.Entry<String, List<String>> entry : apiMap.entrySet()) {

            //创建权限属性列表
            Collection<ConfigAttribute> attributes = new ArrayList<>();
            for (String permission : entry.getValue()) {
                attributes.add(new SecurityConfig(permission));
            }

            resourceMap.put(new AntPathRequestMatcher(entry.getKey(), POST), attributes);
        }
    }

    /**
     * 每次请求进行拦截获得接口对应的权限
     * @param object    请求体
     * @return  结果集
     * @throws IllegalArgumentException Exception
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

        HttpServletRequest request = ((FilterInvocation) object).getRequest();

        if (resourceMap == null) {
            this.loadResourceDefine();
        }

        for (Map.Entry<RequestMatcher, Collection<ConfigAttribute>> entry : resourceMap.entrySet()) {

            //如果请求匹配
            if (entry.getKey().matches(request)) {
                //如果value中存在数据
                if (CollectionUtils.isNotEmpty(entry.getValue())) {
                    return entry.getValue();
                }
            }
        }

        return null;
    }

    /**
     * 用于被AbstractSecurityInterceptor调用，返回所有的 Collection<ConfigAttribute> ，以筛选出不符合要求的attribute
     */
    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<>();
    }

    /**
     * 用于被AbstractSecurityInterceptor调用，验证指定的安全对象类型是否被MetadataSource支持
     */
    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}
