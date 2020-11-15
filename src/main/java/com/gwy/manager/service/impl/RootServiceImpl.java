package com.gwy.manager.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Admin;
import com.gwy.manager.entity.Dept;
import com.gwy.manager.entity.Root;
import com.gwy.manager.enums.ResponseDataMsg;
import com.gwy.manager.mapper.AdminMapper;
import com.gwy.manager.mapper.DeptMapper;
import com.gwy.manager.mapper.RootMapper;
import com.gwy.manager.service.RootService;
import com.gwy.manager.util.MD5Util;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Tracy
 * @date 2020/11/10 16:45
 */
@Service
public class RootServiceImpl implements RootService {

    @Autowired
    private RootMapper rootMapper;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private DeptMapper deptMapper;

    @Override
    public ResultVO login(String account, String password) {

        ResultVO resultVO = new ResultVO();

        Root root = rootMapper.getRoot();

        if (account.equals(root.getAccount())) {
            resultVO.setData(ResponseDataMsg.NotFound.getMsg());
        } else if (!root.getPassword().equals(MD5Util.inputToDb(password))) {
            resultVO.setData(ResponseDataMsg.PasswordIncorrect.getMsg());
        } else {
            resultVO.success(ResponseDataMsg.Success.getMsg());
        }

        return resultVO;
    }

    @Override
    public ResultVO updatePassword(String password) {

        ResultVO resultVO = new ResultVO();

        int i = rootMapper.updatePassword(MD5Util.inputToDb(password));
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
        Map<String, Object> result = new LinkedHashMap<>();

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

            result.put("admin" + i++, tmpMap);
        }

        resultVO.success(result);

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

        String maxAdminNo = adminMapper.selectMaxAdminNo();

        //如果没有admin，默认adminNo从"10000"开始
        if (StringUtils.isEmpty(maxAdminNo)) {
            maxAdminNo = "10000";
        }

        admin.setAdminNo(String.valueOf(Integer.parseInt(maxAdminNo) + 1));
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
}
