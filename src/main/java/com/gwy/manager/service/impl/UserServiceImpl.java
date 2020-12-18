package com.gwy.manager.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.gwy.manager.constant.PageHelperConst;
import com.gwy.manager.constant.RoleName;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.*;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.enums.ResponseStatus;
import com.gwy.manager.enums.UserOption;
import com.gwy.manager.mapper.*;
import com.gwy.manager.security.GlobalPasswordEncoder;
import com.gwy.manager.service.UserService;
import com.gwy.manager.util.*;
import com.gwy.manager.util.file.ImportExcelFileUtil;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/24 23:50
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private VRCodeUtil vrCodeUtil;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private TermMapper termMapper;

    @Autowired
    private TeacherAssessMapper teacherAssessMapper;

    @Autowired
    private ImportExcelFileUtil importExcelFileUtil;

    @Override
    public ResultVO getUserById(String adminNo, String userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            User adminUser = userMapper.selectByPrimaryKey(adminNo);
            if (adminUser == null || !adminUser.getAvailableDeptIds().contains(user.getDeptId())) {
                return ResultVOUtil.error(ResponseDataMsg.PermissionDeny.getMsg());
            } else {
                return ResultVOUtil.success(BeanUtil.beanToMap(user));
            }
        }
    }

    @Override
    public ResultVO getUserById(String userId) {

        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        }

        return ResultVOUtil.success(BeanUtil.beanToMap(user));
    }

    @Override
    public ResultVO getUserMatchNameInDept(String adminNo, String deptId, String name) {

        ResultVO resultVO;

        //判断管理员用户是否存在
        User adminUser = userMapper.selectByPrimaryKey(adminNo);
        if (adminUser == null || !adminUser.getAvailableDeptIds().contains(deptId)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.PermissionDeny.getMsg());
            return resultVO;
        }

        List<User> users = userMapper.getUsersMatchNameInDept(deptId, name);
        if (CollectionUtils.isEmpty(users)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(BeanUtil.beansToList(users));
        }

        return resultVO;
    }

    @Override
    public ResultVO updateUser(User user) {

        ResultVO resultVO;

        int i = userMapper.updateByPrimaryKey(user);
        if (i == 0) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResultVO getUsersOfDept(int pageNum, int pageSize, String deptId) {

        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectUsersByDeptId(deptId);
        if (CollectionUtils.isEmpty(users)) {
            return ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        }

        Map<String, Object> infoMap = PageHelperUtil.pageInfoToMap(new PageInfo<>(users));

        Collection<Map<String, Object>> userMaps = (Collection<Map<String, Object>>) infoMap.get(PageHelperConst.LIST);
        Dept dept = deptMapper.selectByPrimaryKey(deptId);
        for (Map<String, Object> map : userMaps) {
            map.put("deptName", dept.getDeptName());
        }

        return ResultVOUtil.success(infoMap);
    }

    @Override
    @SuppressWarnings("unchecked")
    public ResultVO getUsersOfDept(int pageNum, int pageSize, String userId, String deptId) {

        ResultVO resultUsers = this.getUsersOfDept(pageNum, pageSize, deptId);

        if (resultUsers.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {

            //获得当前学期
            Term term = termMapper.getCurrentTerm(DateUtilCustom.getDate());
            if (term != null) {

                //获得已经查询到的结果集
                Collection<Map<String, Object>> maps = (Collection<Map<String, Object>>) ((Map<String, Object>) resultUsers.getData()).get(PageHelperConst.LIST);

                //存储查询结果的userId
                List<String> userIds = new ArrayList<>();
                for (Map<String, Object> map : maps) {
                    userIds.add((String)map.get("userId"));
                }

                //查询该教师在本学期已经评价的教师id
                List<String> assessedUserIds = teacherAssessMapper
                        .judgeAssessed(userId, userIds, term.getTermId());

                //遍历查询结果，判断是否被评价
                for (Map<String, Object> map : maps) {
                    map.put("assessed", assessedUserIds.contains((String) map.get("userId")));
                }

                int count = teacherAssessMapper.selectCountOfUserInTerm(userId, term.getTermId());
                HashMap<String, Object> cntMap = new HashMap<>();
                cntMap.put("count", count);
                maps.add(cntMap);

                return resultUsers;
            }

        }

        return resultUsers;
    }

    @Override
    public ResultVO getDeptIdsOfUser(String userId) {

        ResultVO resultVO;

        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            List<String> ids = Arrays.asList(user.getAvailableDeptIds().split(","));
            Map<String, Dept> res = deptMapper.getDeptByIds(ids);
            List<Dept> depts = new ArrayList<>();
            for (Map.Entry<String, Dept> dept : res.entrySet()) {
                depts.add(dept.getValue());
            }
            resultVO = ResultVOUtil.success(depts);
        }

        return resultVO;
    }

    @Override
    public ResultVO getUserInfo(String userId) {

        ResultVO resultVO;

        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVOUtil.success(BeanUtil.beanToMap(user));
        }

        return resultVO;
    }

    @Override
    public ResultVO sendCode(String userId) {
        return vrCodeUtil.sendCode(userId, RoleName.USER);
    }

    @Transactional(rollbackFor = {Exception.class})
    @Override
    public ResultVO updatePassword(String userId, String password, String vrCode) {

        return vrCodeUtil.updatePasswordByCode(UserOption.TEACHER.getUserType(), userId, password, vrCode);
    }

    @Override
    public ResultVO getAllAdmin(int pageNum, int pageSize) {

        ResultVO resultVO;

        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectUsersByRoleName(RoleName.ADMIN);
        if (CollectionUtils.isEmpty(users)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(PageHelperUtil.pageInfoToMap(new PageInfo<>(users)));
        }

        return resultVO;
    }

    @Override
    public ResultVO getAllUsers(int pageNum, int pageSize) {

        ResultVO resultVO;

        PageHelper.startPage(pageNum, pageSize);
        List<User> users = userMapper.selectAll();
        if (CollectionUtils.isEmpty(users)) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.NotFound.getMsg());
        } else {
            resultVO = ResultVOUtil.success(PageHelperUtil.pageInfoToMap(new PageInfo<>(users)));
        }

        return resultVO;
    }

    @Override
    public ResultVO updateAvailableDeptIds(String userId, List<String> list) {

        ResultVO resultVO;

        //获得用户所有的角色
        List<Role> roles = roleMapper.selectByUserId(userId);

        //存储用户所有角色id
        List<Integer> roleIds = new ArrayList<>();
        for (Role role : roles) {
            roleIds.add(role.getRoleId());
        }

        //获得管理员角色id
        Integer adminRoleId = roleMapper.selectRoleIdByName(RoleName.ADMIN);
        //若用户不存在管理员角色
        if (!roleIds.contains(adminRoleId)) {
            resultVO = ResultVOUtil.error("User Is Not Admin");
            return resultVO;
        }

        //若用户拥有管理员角色
        //添加用户可管理学院
        StringBuilder sb = new StringBuilder();
        //若修改后的学院id列表不存在用户所在学院id，则自动添加
        User user = userMapper.selectByPrimaryKey(userId);
        if (!list.contains(user.getDeptId())) {
            sb.append(user.getDeptId()).append(",");
        }
        for (String s : list) {
            sb.append(s).append(",");
        }
        String deptIds = sb.toString();

        int i = userMapper.updateAvailableDeptIds(userId, deptIds);
        if (i == 0) {
            resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO = ResultVOUtil.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Transactional(rollbackFor = {Exception.class})
    @SuppressWarnings("unchecked")
    @Override
    public ResultVO importUsersByFile(String deptId, String headerType, MultipartFile file) {

        ResultVO resultVO = importExcelFileUtil.importBeansByFile(deptId, headerType, file);

        if (resultVO.getResultCode().equals(ResponseStatus.SUCCESS.getCode())) {
            Map<String, Object> map = (Map<String, Object>) resultVO.getData();

            List<User> users = new ArrayList<>();

            for (Map.Entry<String, Object> entry : map.entrySet()) {
                Map<String, Object> dataMap = (Map<String, Object>) entry.getValue();
                users.addAll((List<User>) dataMap.get("dataList"));
            }

            Integer teacherRoleId = roleMapper.selectRoleIdByName(RoleName.TEACHER);

            //存储用户id, 增加用户-角色
            List<UserRole> userRoles = new ArrayList<>();
            for (User user : users) {
                UserRole userRole = new UserRole();
                userRole.setUserId(user.getUserId());
                userRole.setRoleId(teacherRoleId);

                userRoles.add(userRole);
            }

            try {
                int i, j;
                try {
                    i = userMapper.insertUsersByBatch(users);
                    j = userRoleMapper.insertByBatch(userRoles);
                } catch (Exception e) {
                    resultVO = ResultVOUtil.error("Exception in Executing");
                    return resultVO;
                }
                if (i == 0 || j == 0) {
                    resultVO = ResultVOUtil.error(ResponseDataMsg.Fail.getMsg());
                } else {
                    resultVO = ResultVOUtil.success(BeanUtil.beansToList(users));
                }
            } catch (Exception e) {
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }

        return resultVO;
    }

}
