package com.gwy.manager.service.impl;

import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Admin;
import com.gwy.manager.mapper.AdminMapper;
import com.gwy.manager.service.AdminService;
import com.gwy.manager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author Tracy
 * @date 2020/11/5 8:10
 */
@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public ResultVO login(String adminNo, String password) {

        ResultVO resultVO = new ResultVO();

        Admin admin = adminMapper.selectByPrimaryKey(adminNo);
        if (admin == null) {
            resultVO.setData("Not Found Admin");
        } else if (!admin.getPassword().equals(MD5Util.inputToDb(password))) {
            resultVO.setData("Password Incorrect");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }

    @Override
    public ResultVO updatePassword(String adminNo, String password) {

        ResultVO resultVO = new ResultVO();

        String newPassword = MD5Util.inputToDb(password);

        int i = adminMapper.updatePassword(adminNo, newPassword);
        if (i == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }

    @Override
    public ResultVO updateAdmin(Admin admin) {

        ResultVO resultVO = new ResultVO();

        int i = adminMapper.updateByPrimaryKey(admin);
        if (i == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }
}
