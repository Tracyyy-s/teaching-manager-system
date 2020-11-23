package com.gwy.manager.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.gwy.manager.constant.RoleName;
import com.gwy.manager.entity.Dept;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Admin;
import com.gwy.manager.mapper.AdminMapper;
import com.gwy.manager.mapper.DeptMapper;
import com.gwy.manager.mapper.RoleMapper;
import com.gwy.manager.service.AdminService;
import com.gwy.manager.util.BeanUtil;
import com.gwy.manager.util.MD5Util;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/5 8:10
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleMapper roleMapper;

//    @Override
//    public ResultVO login(String adminNo, String password) {
//
//        ResultVO resultVO = new ResultVO();
//
//        Admin admin = adminMapper.selectByPrimaryKey(adminNo);
//        if (admin == null) {
//            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
//        } else if (!admin.getPassword().equals(MD5Util.inputToDb(password))) {
//            resultVO.setData(ResponseDataMsg.PasswordIncorrect.getMsg());
//        } else {
//            resultVO.success(BeanUtil.beanToMap(admin));
//        }
//
//        return resultVO;
//    }

    @Transactional
    @Override
    public ResultVO updatePassword(String adminNo, String password) {

        ResultVO resultVO = new ResultVO();

        String newPassword = passwordEncoder.encode(password);

        int i = adminMapper.updatePassword(adminNo, newPassword);
        if (i == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Transactional
    @Override
    public ResultVO updateAdmin(Admin admin) {

        ResultVO resultVO = new ResultVO();

        int i = adminMapper.updateByPrimaryKey(admin);
        if (i == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Override
    public ResultVO getAllAdmins() {
        ResultVO resultVO = new ResultVO();

        //获得所有管理员
        List<Admin> admins = adminMapper.selectAll();

        //如果没有找到
        if (CollectionUtils.isEmpty(admins)) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        }

        //存储结果
        List<Map<String, String>> list = new ArrayList<>();

        //获得院系id列表
        List<String> deptIdList = new ArrayList<>();
        for (Admin admin : admins) {
            if (!deptIdList.contains(admin.getDeptId())) {
                deptIdList.add(admin.getDeptId());
            }
        }

        //通过id列表获得dept列表
        Map<String, Dept> deptList = deptMapper.getDeptByIds(deptIdList);

        int i = 1;
        for (Admin admin : admins) {
            Map<String, String> tmpMap = new LinkedHashMap<>();
            tmpMap.put("adminNo", admin.getAdminNo());
            tmpMap.put("adminName", admin.getAdminName());
            tmpMap.put("adminDeptName", deptList.get(admin.getDeptId()).getDeptName());

            int deptCount = admin.getAvailableDeptIds().split(",").length;
            tmpMap.put("adminType", deptCount == 1 ? "一级管理员" : "二级管理员");

            list.add(tmpMap);
        }

        resultVO.success(list);

        return resultVO;
    }

    @Override
    public ResultVO addAdmin(Admin admin) {
        ResultVO resultVO = new ResultVO();

        Dept dept = deptMapper.selectByPrimaryKey(admin.getDeptId());

        if (dept == null) {
            resultVO.setData("Dept Not Exist");
            return resultVO;
        }

        //设置可以管理的学院
        admin.setAvailableDeptIds(admin.getDeptId() + ",");

        String maxAdminNo = adminMapper.selectMaxAdminNo();

        //如果没有admin，默认adminNo从"10000"开始
        if (StringUtils.isEmpty(maxAdminNo)) {
            maxAdminNo = "10000";
        }

        //设置管理员id
        admin.setAdminNo(String.valueOf(Integer.parseInt(maxAdminNo) + 1));
        //设置加密密码
        admin.setPassword(passwordEncoder.encode(admin.getPassword()));
        //设置角色id
        admin.setRoleId(roleMapper.selectRoleIdByName(RoleName.ADMIN1));

        int i = adminMapper.insert(admin);
        if (i == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Override
    public ResultVO deleteAdmin(String adminNo) {
        ResultVO resultVO = new ResultVO();

        //如果没找到对应的管理员
        Admin admin = adminMapper.selectByPrimaryKey(adminNo);
        if (admin == null) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
            return resultVO;
        }

        int i = adminMapper.deleteByPrimaryKey(adminNo);
        if (i == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Override
    public ResultVO getDeptsById(String adminNo) {

        ResultVO resultVO = new ResultVO();

        Admin admin = adminMapper.selectByPrimaryKey(adminNo);
        if (admin == null) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else {
            String[] deptIds = admin.getAvailableDeptIds().split(",");

            Map<String, Object> map = new HashMap<>();
            map.put("deptIdList", Arrays.asList(deptIds));
            resultVO.success(map);
        }

        return resultVO;
    }

    @Override
    public ResultVO updateDeptIds(String adminNo, List<String> list) {

        ResultVO resultVO = new ResultVO();

        StringBuilder sb = new StringBuilder();
        for (String s : list) {
            sb.append(s).append(",");
        }

        String deptIds = sb.toString();
        int deptCnt = deptIds.split(",").length;

        int i = adminMapper.updateDeptIds(adminNo, deptIds, deptCnt);
        if (i == 0) {
            resultVO.setData(ResponseDataMsg.Fail.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }
}
