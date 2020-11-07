package com.gwy.manager.service.impl;

import com.gwy.manager.entity.ExcelSheetPO;
import com.gwy.manager.dto.ResultVO;
import com.gwy.manager.entity.Teacher;
import com.gwy.manager.mapper.TeacherMapper;
import com.gwy.manager.service.TeacherService;
import com.gwy.manager.util.ExcelHeaderFormat;
import com.gwy.manager.util.ExcelUtil;
import com.gwy.manager.util.MD5Util;
import com.gwy.manager.util.MultipartFileToFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.*;

/**
 * @author Tracy
 * @date 2020/11/1 23:13
 */
@Service
public class TeacherServiceImpl implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public Teacher getTeacher(String teacherNo) {
        return teacherMapper.selectByPrimaryKey(teacherNo);
    }

    @Override
    public ResultVO getTeacherByTnoInDept(String deptId, String tno) {

        ResultVO resultVO = new ResultVO();

        Teacher teacher = teacherMapper.selectByPrimaryKey(tno);

        //未找到教师
        if (teacher == null) {
            resultVO.setData("Not Found");
        } else if (!deptId.equals(teacher.getDeptId())) {
            //教师学院id不等于管理员学院id
            resultVO.setData("Permission Deny");
        } else {
            //添加教师
            resultVO.success(teacher);
        }

        return resultVO;
    }

    @Override
    public ResultVO login(String teacherNo, String password) {

        ResultVO resultVO = new ResultVO();

        Teacher teacher = teacherMapper.selectByPrimaryKey(teacherNo);

        //未找到教师
        if (teacher == null) {
            resultVO.setData("Not Found Teacher");
        } else if (!MD5Util.inputToDb(password).equals(teacher.getPassword())) {
            //密码错误
            resultVO.setData("Password Incorrect");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }

    @Override
    public ResultVO updateTeacher(Teacher teacher) {

        ResultVO resultVO = new ResultVO();

        int i = teacherMapper.updateByPrimaryKey(teacher);
        if (i == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }

    @Override
    public ResultVO updatePassword(String teacherNo, String password) {

        ResultVO resultVO = new ResultVO();

        int i = teacherMapper.updatePassword(teacherNo, MD5Util.inputToDb(password));
        if (i == 0) {
            resultVO.setData("Fail");
        } else {
            resultVO.success("Success");
        }

        return resultVO;
    }

    @Override
    public ResultVO getTeachersOfDept(String deptId) {

        ResultVO resultVO = new ResultVO();

        //获得学院内的所有教师
        List<Teacher> teachers = teacherMapper.selectByDeptId(deptId);
        //未找到
        if (teachers.size() == 0) {
            resultVO.setData("Not Found");
        } else {
            resultVO.success(teachers);
        }

        return resultVO;
    }

    @Override
    public ResultVO getTeacherMatchNameInDept(String deptId, String name) {

        ResultVO resultVO = new ResultVO();

        //根据学院号对教师名进行模糊匹配
        List<Teacher> teachers = teacherMapper.getTeachersMatchNameInDept(deptId, name);
        if (teachers.size() == 0) {
            resultVO.setData("Not Found");
        } else {
            resultVO.success(teachers);
        }

        return resultVO;
    }

    /**
     * 通过传入文件导入Bean对象
     * @param headerType    头类型
     * @param file  传入文件
     * @param <T>   导入的pojo类型
     * @return  结果集
     */
    @SuppressWarnings("unchecked")
    private <T> ResultVO importBeansByFile(String headerType, MultipartFile file) {

        ResultVO resultVO = new ResultVO();
        List<ExcelSheetPO> pos = new ArrayList<>();

        try {
            File tmpFile = MultipartFileToFile.multipartFileToFile(file);
            pos = ExcelUtil.readExcel(headerType, tmpFile);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*if (pos.get(0).getTitle().equals(ExcelHeaderFormat.InvalidHeaders)) {

            Map<String, Object> map = new LinkedHashMap<>();
            map.put("Failure", "表格标题不匹配");
            map.put("Expected", ExcelHeaderFormat.getValidHeaders(headerType));

            resultVO.setData(map);
        }*/

        List<T> data;

        Map<String, Object> map = new LinkedHashMap<>();

        //遍历所有页
        for (ExcelSheetPO po : pos) {
            Map<String, Object> thisSheet = new LinkedHashMap<>();

            thisSheet.put("title", po.getTitle());
            thisSheet.put("headers", po.getHeaders());

            List<List<Object>> dataList = po.getDataList();

            data = new ArrayList<>();

            //遍历本页中的所有数据
            for (List<Object> objects : dataList) {
                Map<String, Object> thisData = ExcelHeaderFormat.dataToBean(headerType, objects);

                //如果数据识别错误
                if (thisData != null && thisData.get("msg").equals("Fail")) {
                    resultVO.setData(thisData.get("data"));
                    return resultVO;
                } else if (thisData != null && thisData.get("msg").equals("Success")) {
                    data.add((T) thisData.get("data"));
                }
            }
            thisSheet.put("dataList", data);

            map.put(po.getSheetName(), thisSheet);
        }

        resultVO.success(map);
        return resultVO;
    }

    @Override
    public ResultVO importTeachersByFile(String headerType, MultipartFile file) {

        return this.importBeansByFile(headerType, file);
    }

}
