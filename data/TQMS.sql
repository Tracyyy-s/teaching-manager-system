/*
 Navicat Premium Data Transfer

 Source Server         : tecent-mysql
 Source Server Type    : MySQL
 Source Server Version : 50718
 Source Host           : cdb-neqw262o.cd.tencentcdb.com:10127
 Source Schema         : TQMS

 Target Server Type    : MySQL
 Target Server Version : 50718
 File Encoding         : 65001

 Date: 29/03/2021 21:29:26
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for admin
-- ----------------------------
DROP TABLE IF EXISTS `admin`;
CREATE TABLE `admin`  (
  `admin_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '管理员账号',
  `admin_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `dept_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '管理员学院Id',
  `available_dept_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '可管理学院的id',
  `role_id` int(11) NULL DEFAULT NULL,
  PRIMARY KEY (`admin_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of admin
-- ----------------------------
INSERT INTO `admin` VALUES ('10004', '张薛秦', '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '110', '', 5);
INSERT INTO `admin` VALUES ('10006', '李懂秦', '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '111', '111,', 4);
INSERT INTO `admin` VALUES ('10007', '章玉栋', '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '111', '111,110,112,113,', 5);
INSERT INTO `admin` VALUES ('10008', '吴鲽仗', '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '111', '111,', 4);
INSERT INTO `admin` VALUES ('10009', '海闻', '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '112', '112,', 4);

-- ----------------------------
-- Table structure for root
-- ----------------------------
DROP TABLE IF EXISTS `root`;
CREATE TABLE `root`  (
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `role_id` int(11) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of root
-- ----------------------------
INSERT INTO `root` VALUES ('10000', '$2a$10$TFz6PFCGDS9d5UIW6fAXVeLYbIuQ31yt4/BzOAgNJEOcjVZEi97yS', 6);

-- ----------------------------
-- Table structure for test
-- ----------------------------
DROP TABLE IF EXISTS `test`;
CREATE TABLE `test`  (
  `date` date NULL DEFAULT NULL,
  `date_time` datetime(0) NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of test
-- ----------------------------
INSERT INTO `test` VALUES ('2020-11-04', NULL);
INSERT INTO `test` VALUES ('2020-11-04', '2020-11-04 11:17:48');
INSERT INTO `test` VALUES ('2020-11-04', '2020-11-04 11:19:38');
INSERT INTO `test` VALUES ('2020-11-04', '2020-11-04 11:24:02');
INSERT INTO `test` VALUES ('2020-11-04', '2020-11-04 19:24:59');
INSERT INTO `test` VALUES ('2020-11-12', '2020-11-12 17:16:03');
INSERT INTO `test` VALUES ('2020-11-12', '2020-11-12 17:16:03');
INSERT INTO `test` VALUES ('2020-11-12', '2020-11-12 17:16:30');
INSERT INTO `test` VALUES ('2020-11-12', '2020-11-12 17:21:52');
INSERT INTO `test` VALUES ('2020-11-12', '2020-11-12 17:23:30');
INSERT INTO `test` VALUES ('2020-11-12', '2020-11-12 17:23:43');
INSERT INTO `test` VALUES ('2020-11-12', '2020-11-12 17:23:54');
INSERT INTO `test` VALUES ('2020-11-12', '2020-11-12 17:24:32');
INSERT INTO `test` VALUES ('2020-11-12', '2020-11-12 17:24:32');
INSERT INTO `test` VALUES ('2020-11-15', '2020-11-15 11:32:13');
INSERT INTO `test` VALUES ('2020-11-15', '2020-11-15 11:59:37');
INSERT INTO `test` VALUES ('2020-11-15', '2020-11-15 12:05:16');
INSERT INTO `test` VALUES ('2020-11-15', '2020-11-15 12:30:46');
INSERT INTO `test` VALUES ('2020-11-15', '2020-11-15 16:10:21');
INSERT INTO `test` VALUES ('2020-11-15', '2020-11-15 16:34:25');
INSERT INTO `test` VALUES ('2020-11-15', '2020-11-15 18:09:52');
INSERT INTO `test` VALUES ('2020-11-15', '2020-11-15 18:11:00');
INSERT INTO `test` VALUES ('2020-11-15', '2020-11-15 18:30:54');
INSERT INTO `test` VALUES ('2020-11-15', '2020-11-15 18:32:04');
INSERT INTO `test` VALUES ('2020-11-16', '2020-11-16 22:31:46');
INSERT INTO `test` VALUES ('2020-11-17', '2020-11-17 12:21:11');
INSERT INTO `test` VALUES ('2020-11-17', '2020-11-17 12:22:43');
INSERT INTO `test` VALUES ('2020-11-17', '2020-11-17 12:30:08');
INSERT INTO `test` VALUES ('2020-11-17', '2020-11-17 12:43:50');
INSERT INTO `test` VALUES ('2020-11-17', '2020-11-17 12:51:33');
INSERT INTO `test` VALUES ('2020-11-17', '2020-11-17 12:59:07');

-- ----------------------------
-- Table structure for ts_course
-- ----------------------------
DROP TABLE IF EXISTS `ts_course`;
CREATE TABLE `ts_course`  (
  `course_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '课程号cno',
  `course_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程名',
  `hour` int(11) NULL DEFAULT NULL COMMENT '课程学时',
  `credit` int(11) NULL DEFAULT NULL COMMENT '课程学分',
  `dept_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院id',
  PRIMARY KEY (`course_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_course
-- ----------------------------
INSERT INTO `ts_course` VALUES ('11001', '数据结构', 64, 4, '110');
INSERT INTO `ts_course` VALUES ('11002', '软件工程导论', 56, 3, '110');
INSERT INTO `ts_course` VALUES ('11003', '计算机组成原理', 80, 4, '110');
INSERT INTO `ts_course` VALUES ('11004', '操作系统', 56, 3, '110');
INSERT INTO `ts_course` VALUES ('11005', '算法设计与分析', 72, 4, '110');
INSERT INTO `ts_course` VALUES ('11006', '计算机网络', 64, 3, '110');
INSERT INTO `ts_course` VALUES ('11007', '高等数学', 80, 5, '110');

-- ----------------------------
-- Table structure for ts_dept
-- ----------------------------
DROP TABLE IF EXISTS `ts_dept`;
CREATE TABLE `ts_dept`  (
  `dept_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院id',
  `dept_name` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学院名',
  PRIMARY KEY (`dept_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_dept
-- ----------------------------
INSERT INTO `ts_dept` VALUES ('110', '计算机科学学院');
INSERT INTO `ts_dept` VALUES ('111', '经济与管理学院');
INSERT INTO `ts_dept` VALUES ('112', '地理与资源学院');
INSERT INTO `ts_dept` VALUES ('113', '文学院');
INSERT INTO `ts_dept` VALUES ('114', '商学院');

-- ----------------------------
-- Table structure for ts_log
-- ----------------------------
DROP TABLE IF EXISTS `ts_log`;
CREATE TABLE `ts_log`  (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `authorities` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `locale` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `request_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `params` mediumtext CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `result_message` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `data_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type_explain` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 22535 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_log
-- ----------------------------
INSERT INTO `ts_log` VALUES (41, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/teacher/getTermCourses', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"studentNo\":\"2001110004\",\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:42:29');
INSERT INTO `ts_log` VALUES (42, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getTargets', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"studentNo\":\"2001110004\",\"userType\":\"teacher\",\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:42:29');
INSERT INTO `ts_log` VALUES (43, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getStudentAssessesByTcId', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"pageSize\":\"5\",\"studentNo\":\"2001110004\",\"tcId\":\"20211200111000211004\",\"pageNum\":\"1\",\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:42:29');
INSERT INTO `ts_log` VALUES (44, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getDeptsOfUser', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"studentNo\":\"2001110004\",\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:42:30');
INSERT INTO `ts_log` VALUES (45, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getTeachersByDept', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"deptId\":\"110\",\"pageSize\":5,\"studentNo\":\"2001110004\",\"pageNum\":1,\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:42:30');
INSERT INTO `ts_log` VALUES (46, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getTargets', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"studentNo\":\"2001110004\",\"userType\":\"student\",\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:42:30');
INSERT INTO `ts_log` VALUES (47, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/teacher/getTermCourses', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"pageSize\":\"5\",\"studentNo\":\"2001110004\",\"userId\":\"2001110004\",\"pageNum\":\"1\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:42:31');
INSERT INTO `ts_log` VALUES (48, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getDeptsOfUser', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"studentNo\":\"2001110004\",\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:42:31');
INSERT INTO `ts_log` VALUES (49, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getStudentsByDept', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"deptId\":\"110\",\"pageSize\":5,\"studentNo\":\"2001110004\",\"pageNum\":1,\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:42:32');
INSERT INTO `ts_log` VALUES (50, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getTargets', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"studentNo\":\"2001110004\",\"userType\":\"student\",\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:42:32');
INSERT INTO `ts_log` VALUES (51, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getTargets', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"studentNo\":\"2001110004\",\"userType\":\"student\",\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:43:06');
INSERT INTO `ts_log` VALUES (52, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getTargets', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"studentNo\":\"2001110004\",\"userType\":\"student\",\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:43:49');
INSERT INTO `ts_log` VALUES (53, '2001110004', '[ROLE_admin, ROLE_leader, ROLE_teacher]', 'zh_CN', 'http://127.0.0.1:8080/admin/getTargets', '127.0.0.1', '[{\"adminNo\":\"2001110004\",\"studentNo\":\"2001110004\",\"userType\":\"student\",\"userId\":\"2001110004\",\"account\":\"2001110004\"}]', 'SUCCESS', 'Success', '操作成功', 'OperationLog', '操作日志', '2020-12-15 23:44:05');


-- ----------------------------
-- Table structure for ts_major
-- ----------------------------
DROP TABLE IF EXISTS `ts_major`;
CREATE TABLE `ts_major`  (
  `major_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业id',
  `major_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '专业名',
  `dept_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业所在学院的id',
  PRIMARY KEY (`major_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_major
-- ----------------------------
INSERT INTO `ts_major` VALUES ('11001', '软件工程', '110');
INSERT INTO `ts_major` VALUES ('11002', '网络工程', '110');
INSERT INTO `ts_major` VALUES ('11003', '计算机科学与技术', '110');

-- ----------------------------
-- Table structure for ts_permission
-- ----------------------------
DROP TABLE IF EXISTS `ts_permission`;
CREATE TABLE `ts_permission`  (
  `permission_id` int(11) NOT NULL AUTO_INCREMENT,
  `permission_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_range` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `permission_vx_url` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`permission_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 18 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_permission
-- ----------------------------
INSERT INTO `ts_permission` VALUES (1, '导入学生名单', 'studentListImporting', 'import', 'null');
INSERT INTO `ts_permission` VALUES (2, '导入课表', 'timetableImporting', 'import', 'null');
INSERT INTO `ts_permission` VALUES (3, '导入老师名单', 'teacherListImporting', 'import', 'null');
INSERT INTO `ts_permission` VALUES (4, '管理教师档案', 'teacherFileManagement', 'null', 'null');
INSERT INTO `ts_permission` VALUES (5, '查看评价结果', 'viewAllEvaluationResults', 'evaluation', '/rootEvaForm/viewEva/viewEva');
INSERT INTO `ts_permission` VALUES (6, '发布学生评价指标', 'releaseStudentComments', 'manager', '/rootEvaForm/releaseComments/releaseComments');
INSERT INTO `ts_permission` VALUES (7, '发布教师评价指标', 'releaseTeacherComments', 'manager', '/rootEvaForm/releaseComments/releaseComments');
INSERT INTO `ts_permission` VALUES (8, '查看教师课表', 'teacherSchedule', 'teacher', 'null');
INSERT INTO `ts_permission` VALUES (9, '教师互评', 'teacherEvaluation', 'leader', '/teaEvaForm/teaEvaForm');
INSERT INTO `ts_permission` VALUES (10, '学生信息', 'studentInfo', 'all', '/pages/stuInfo/stuInfo');
INSERT INTO `ts_permission` VALUES (11, '资料卡片', 'teacherCard', 'all', '/pages/teaInfo/teaInfo');
INSERT INTO `ts_permission` VALUES (12, '用户列表', 'user_role_list', 'root', 'null');
INSERT INTO `ts_permission` VALUES (13, '角色权限管理', 'role_permission_modify', 'root', 'null');
INSERT INTO `ts_permission` VALUES (14, '评价教师', 'Evaluation', 'student', '/stuEvaForm/stuEvaForm');
INSERT INTO `ts_permission` VALUES (15, '查看学生课表', 'studentSchedule', 'student', 'null');
INSERT INTO `ts_permission` VALUES (16, '后台日志', 'Log', 'root', 'null');
INSERT INTO `ts_permission` VALUES (17, '导出评价列表', 'exportEvaluationList', 'evaluation', 'null');

-- ----------------------------
-- Table structure for ts_permission_api
-- ----------------------------
DROP TABLE IF EXISTS `ts_permission_api`;
CREATE TABLE `ts_permission_api`  (
  `permission_id` int(11) NOT NULL,
  `api` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`permission_id`, `api`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_permission_api
-- ----------------------------
INSERT INTO `ts_permission_api` VALUES (1, '/admin/getStudentById');
INSERT INTO `ts_permission_api` VALUES (1, '/admin/getStudentsByDept');
INSERT INTO `ts_permission_api` VALUES (1, '/admin/getStudentsMatchNameInDept');
INSERT INTO `ts_permission_api` VALUES (1, '/admin/importStudents');
INSERT INTO `ts_permission_api` VALUES (1, '/student/getStudentInfo');
INSERT INTO `ts_permission_api` VALUES (3, '/admin/getTeacherById');
INSERT INTO `ts_permission_api` VALUES (3, '/admin/getTeachersByDept');
INSERT INTO `ts_permission_api` VALUES (3, '/admin/getTeachersMatchNameInDept');
INSERT INTO `ts_permission_api` VALUES (3, '/admin/importTeachers');
INSERT INTO `ts_permission_api` VALUES (6, '/admin/addTarget');
INSERT INTO `ts_permission_api` VALUES (6, '/admin/deleteTarget');
INSERT INTO `ts_permission_api` VALUES (6, '/admin/getTargets');
INSERT INTO `ts_permission_api` VALUES (6, '/admin/publishTargets');
INSERT INTO `ts_permission_api` VALUES (6, '/admin/updateTarget');
INSERT INTO `ts_permission_api` VALUES (7, '/admin/addTarget');
INSERT INTO `ts_permission_api` VALUES (7, '/admin/deleteTarget');
INSERT INTO `ts_permission_api` VALUES (7, '/admin/getTargets');
INSERT INTO `ts_permission_api` VALUES (7, '/admin/publishTargets');
INSERT INTO `ts_permission_api` VALUES (7, '/admin/updateTarget');
INSERT INTO `ts_permission_api` VALUES (8, '/admin/getCoursesOfTeacher');
INSERT INTO `ts_permission_api` VALUES (8, '/teacher/getTermCourses');
INSERT INTO `ts_permission_api` VALUES (9, '/leader/postAssess');
INSERT INTO `ts_permission_api` VALUES (10, '/student/getStudentInfo');
INSERT INTO `ts_permission_api` VALUES (10, '/student/sendCode');
INSERT INTO `ts_permission_api` VALUES (10, '/student/updatePassword');
INSERT INTO `ts_permission_api` VALUES (11, '/teacher/sendCode');
INSERT INTO `ts_permission_api` VALUES (12, '/root/getAllAdmins');
INSERT INTO `ts_permission_api` VALUES (12, '/root/getAllRoles');
INSERT INTO `ts_permission_api` VALUES (12, '/root/getAllUsers');
INSERT INTO `ts_permission_api` VALUES (12, '/root/getPermissionsByRoleId');
INSERT INTO `ts_permission_api` VALUES (12, '/root/getUserRoles');
INSERT INTO `ts_permission_api` VALUES (12, '/root/updateUserRole');
INSERT INTO `ts_permission_api` VALUES (13, '/root/getAllPermissions');
INSERT INTO `ts_permission_api` VALUES (13, '/root/getAllRoles');
INSERT INTO `ts_permission_api` VALUES (13, '/root/updateRolePermission');
INSERT INTO `ts_permission_api` VALUES (14, '/student/getTermCourses');
INSERT INTO `ts_permission_api` VALUES (14, '/student/postAssess');
INSERT INTO `ts_permission_api` VALUES (15, '/student/getTermCourses');
INSERT INTO `ts_permission_api` VALUES (16, '/root/deleteLogs');
INSERT INTO `ts_permission_api` VALUES (16, '/root/exportLogs');
INSERT INTO `ts_permission_api` VALUES (16, '/root/getLogInfoByType');
INSERT INTO `ts_permission_api` VALUES (16, '/root/getLogTypeAndCount');
INSERT INTO `ts_permission_api` VALUES (16, '/root/getLogTypes');

-- ----------------------------
-- Table structure for ts_role
-- ----------------------------
DROP TABLE IF EXISTS `ts_role`;
CREATE TABLE `ts_role`  (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 24 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_role
-- ----------------------------
INSERT INTO `ts_role` VALUES (1, 'student');
INSERT INTO `ts_role` VALUES (2, 'teacher');
INSERT INTO `ts_role` VALUES (3, 'leader');
INSERT INTO `ts_role` VALUES (4, 'admin');
INSERT INTO `ts_role` VALUES (5, 'root');
INSERT INTO `ts_role` VALUES (6, 'newRole');

-- ----------------------------
-- Table structure for ts_role_permission
-- ----------------------------
DROP TABLE IF EXISTS `ts_role_permission`;
CREATE TABLE `ts_role_permission`  (
  `role_id` int(11) NOT NULL,
  `permission_id` int(11) NOT NULL,
  PRIMARY KEY (`role_id`, `permission_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_role_permission
-- ----------------------------
INSERT INTO `ts_role_permission` VALUES (1, 10);
INSERT INTO `ts_role_permission` VALUES (1, 14);
INSERT INTO `ts_role_permission` VALUES (1, 15);
INSERT INTO `ts_role_permission` VALUES (2, 8);
INSERT INTO `ts_role_permission` VALUES (2, 11);
INSERT INTO `ts_role_permission` VALUES (3, 9);
INSERT INTO `ts_role_permission` VALUES (3, 11);
INSERT INTO `ts_role_permission` VALUES (4, 1);
INSERT INTO `ts_role_permission` VALUES (4, 2);
INSERT INTO `ts_role_permission` VALUES (4, 3);
INSERT INTO `ts_role_permission` VALUES (4, 4);
INSERT INTO `ts_role_permission` VALUES (4, 5);
INSERT INTO `ts_role_permission` VALUES (4, 6);
INSERT INTO `ts_role_permission` VALUES (4, 7);
INSERT INTO `ts_role_permission` VALUES (4, 11);
INSERT INTO `ts_role_permission` VALUES (4, 17);
INSERT INTO `ts_role_permission` VALUES (5, 11);
INSERT INTO `ts_role_permission` VALUES (5, 12);
INSERT INTO `ts_role_permission` VALUES (5, 13);
INSERT INTO `ts_role_permission` VALUES (5, 16);
INSERT INTO `ts_role_permission` VALUES (6, 8);
INSERT INTO `ts_role_permission` VALUES (6, 11);

-- ----------------------------
-- Table structure for ts_student
-- ----------------------------
DROP TABLE IF EXISTS `ts_student`;
CREATE TABLE `ts_student`  (
  `student_no` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `student_name` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学生名字',
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `gender` int(4) NULL DEFAULT NULL COMMENT '性别',
  `birth` date NULL DEFAULT NULL COMMENT '出生年月',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `class_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在班级',
  `political` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政治面貌',
  `dept_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学院id',
  `entry_year` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '入学年份',
  PRIMARY KEY (`student_no`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_student
-- ----------------------------
INSERT INTO `ts_student` VALUES ('2017110111', '张三', '$2a$10$TFz6PFCGDS9d5UIW6fAXVeLYbIuQ31yt4/BzOAgNJEOcjVZEi97yS', 1, '1999-11-11', '234552@qq.com', '1711001', '共青团员', '112', '2017');
INSERT INTO `ts_student` VALUES ('2017110112', '李四', '$2a$10$TFz6PFCGDS9d5UIW6fAXVeLYbIuQ31yt4/BzOAgNJEOcjVZEi97yS', 1, '1999-11-20', '291434953@qq.com', '1711102', '共青团员', '111', '2017');
INSERT INTO `ts_student` VALUES ('2018110114', '王琦', '$2a$10$68avYxXdDi2.gNfsVvHVE.UFToQO/lWJ6BY.VHD6aCC7eK6Gig24m', 1, '2000-08-12', '12383@qq.com', '1811003', '共青团员', '110', '2018');
INSERT INTO `ts_student` VALUES ('2018110120', '纪立', '$2a$10$PKoWFiFT2LROuIjnjzCZLuR8Hu0JfOM/ywB5knvkXIM6HSSO/ZFmW', 0, '2000-02-02', '234552@qq.com', '1811304', '共青团员', '113', '2018');
INSERT INTO `ts_student` VALUES ('2018110145', '王瑞', '$2a$10$RvgyBCl/pHgj/TQiB8zCm.ctgL1cBckXgYdStNFjSiWH8OCZF09AS', 1, '2000-02-02', '234552@qq.com', '1811001', '共青团员', '110', '2018');
INSERT INTO `ts_student` VALUES ('2018110149', '朱风', '$2a$10$TFz6PFCGDS9d5UIW6fAXVeLYbIuQ31yt4/BzOAgNJEOcjVZEi97yS', 0, '2000-02-02', '234552@qq.com', '1811202', '共青团员', '112', '2018');
INSERT INTO `ts_student` VALUES ('2018110155', '于欣', '$2a$10$TFz6PFCGDS9d5UIW6fAXVeLYbIuQ31yt4/BzOAgNJEOcjVZEi97yS', 1, '2000-02-02', '234552@qq.com', '1811003', '共青团员', '110', '2018');
INSERT INTO `ts_student` VALUES ('2019110111', '冯军阳', '$2a$10$IhRTPYzkK2oPeBr2ZUKNsuxCIFjBU1bB7/lUhZrnnDXSb6yF7bI.S', 1, '2000-10-25', '234552@qq.com', '1911004', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110112', '冯凯燕', '$2a$10$dJMmwgDKQNovtQU6vxo8O.e0YPlg8lSOysQrPwDxMG8L24TQ157t.', 0, '2000-10-26', '234552@qq.com', '1911001', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110113', '冯丽弘', '$2a$10$QkaAs0sYec7od3S7aZnCBummNByPY1Vyz6YRDFQJAQwMC4iwrk5/y', 0, '2000-10-27', '234552@qq.com', '1911002', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110114', '伏雪', '$2a$10$JupiTN2nBE7JXgiqPJ/pCOOK6.xtbOHcRjLHTEIs.qEC/Yywascpm', 0, '2000-10-28', '234552@qq.com', '1911003', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110115', '付洪燕', '$2a$10$wS.q0rtk.Uz2dpRN5YdQ3u9oqHiSJmeQtn2tmRbg06QpnnmaOLaby', 0, '2000-10-29', '234552@qq.com', '1911004', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110116', '付敏', '$2a$10$kU3BBsx/JVmzrLsH4wul/Oro7zMa/2iA0shMpUrL6OnfIZts/9vky', 0, '2000-10-30', '234552@qq.com', '1911001', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110117', '付鹏', '$2a$10$Vnrw4faZrE37PRdGOf.B5.h/HUyLByOv8J8NnosS.7hrkYEU3t4Jy', 1, '2000-10-31', '234552@qq.com', '1911002', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110118', '甘兴友', '$2a$10$1naPa6Z1TriwXs.w44GjHudei2X8AYaMwZiYC9wq4l6VgRduy9ao6', 1, '2000-11-01', '234552@qq.com', '1911003', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110119', '甘元超', '$2a$10$RSSSJV2J89ouept1DuCGOuVWUQLB9sgLa7xM.cW07c6nlWIqYGdWa', 1, '2000-11-02', '234552@qq.com', '1911004', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110120', '高波', '$2a$10$BPEAsGKJHR2Mk6B.iWvqG.Oufu/w/LvixEG4Cziu0/tC2XGx/gExS', 1, '2000-11-03', '234552@qq.com', '1911005', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110121', '高菲', '$2a$10$2LRltYqM2mfLHwP3MkWAfeoBi6gcw7injWQAA1XWZ/naRdBn0oIVa', 0, '2000-11-04', '234552@qq.com', '1911101', '共青团员', '111', '2019');
INSERT INTO `ts_student` VALUES ('2019110122', '葛玉婷', '$2a$10$F3TWb9MXmjiZFxD.OOVugOoEtUuTKr5d27tQLewdDJORRouPYAlFu', 0, '2000-11-06', '234552@qq.com', '1811103', '共青团员', '112', '2019');
INSERT INTO `ts_student` VALUES ('2019110123', '龚婷', '$2a$10$xntpV3C3cfrC/K/C54HFMuvxm4PxCphSz/L.KnV/ANAVLQhlLh.9y', 0, '2000-11-09', '234552@qq.com', '1911101', '共青团员', '111', '2019');
INSERT INTO `ts_student` VALUES ('2019110124', '巩豪', '$2a$10$aiHAiSLtFIxyFiX6GEzjRu8Ym22K88rVuIWQcaNKcUYoj1KxFTWL.', 1, '2000-11-11', '234552@qq.com', '1911001', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110125', '巩兴秋', '$2a$10$jo4khGaOg2zDFemOHUlNgesnZsp.lm689oOWuynFhPbbmUG5ZMvrG', 0, '2000-11-11', '234552@qq.com', '1911002', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110126', '苟春梅', '$2a$10$AuzHo901yxnK6IidxIfy.Oh3XRDYc8xL0Ud8.tJdW5fiTYHJdjEIe', 0, '2000-11-20', '234552@qq.com', '1911003', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110127', '勾晨', '$2a$10$XYox2I4qs1xMWT5a2KXCG.0Zr0V3FNBuVm48ajAV65lmX2FWM6dou', 0, '2000-11-30', '234552234552@qq.com', '1911004', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110128', '桂梦茹', '$2a$10$eLUGAHXk8vhL4hH3LWPP2uo/nTVN0L504OrbVyifhzSyriaBpXgma', 0, '2000-10-12', '234552@qq.com', '1911205', '共青团员', '112', '2019');
INSERT INTO `ts_student` VALUES ('2019110129', '郭七瑞', '$2a$10$Wohs46Hk/wwXcDsOESfdAugiDdjOcWszj3xImG4ihm0aCe82SBc9y', 1, '2000-10-23', '234552@qq.com', '1911001', '共青团员', '110', '2019');
INSERT INTO `ts_student` VALUES ('2019110130', '郭佳鑫', '$2a$10$Z1fWpz.BUpu4KmtYZpPQp.PySomvh3C1Bg9eE5N72oPSEwJTCYbuO', 1, '2000-10-24', '234552@qq.com', '1911002', '共青团员', '110', '2019');

-- ----------------------------
-- Table structure for ts_student_assess
-- ----------------------------
DROP TABLE IF EXISTS `ts_student_assess`;
CREATE TABLE `ts_student_assess`  (
  `student_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学号',
  `tc_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师-课程号',
  `appraise_score` int(11) NULL DEFAULT NULL COMMENT '学生评价总分',
  `appraise_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL COMMENT '评价内容（文字部分）',
  `submit_time` datetime(0) NULL DEFAULT NULL COMMENT '评价提交时间',
  PRIMARY KEY (`student_no`, `tc_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_student_assess
-- ----------------------------
INSERT INTO `ts_student_assess` VALUES ('2017110111', '1819120051100311001', 5, '老师风格好，教学质量高', '2019-12-12 12:12:12');
INSERT INTO `ts_student_assess` VALUES ('2018110114', '20211200111000211004', 5, '教师教学质量好，学生都喜欢', '2020-12-17 19:36:40');
INSERT INTO `ts_student_assess` VALUES ('2018110114', '20211200111000211007', 4, '很好', '2020-12-18 23:05:09');
INSERT INTO `ts_student_assess` VALUES ('2018110114', '2021120051100311001', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110114', '2021120061104411002', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110114', '2021120061104411006', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110114', '2021120071102411003', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110120', '1819120051100311001', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110120', '1819120061104411002', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110120', '1819120071102411003', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110120', '20211200111000211004', 3, '11', '2021-01-16 16:58:33');
INSERT INTO `ts_student_assess` VALUES ('2018110120', '20211200111000211007', 4, '1', '2020-12-19 09:25:44');
INSERT INTO `ts_student_assess` VALUES ('2018110120', '2021120051100311001', 4, '教师教学态度好', '2020-12-17 10:28:27');
INSERT INTO `ts_student_assess` VALUES ('2018110120', '2021120061104411002', 4, '', '2020-12-19 00:29:17');
INSERT INTO `ts_student_assess` VALUES ('2018110120', '2021120061104411006', 4, '', '2020-12-19 08:59:31');
INSERT INTO `ts_student_assess` VALUES ('2018110120', '2021120071102411003', 2, 'guv', '2021-01-16 17:08:30');
INSERT INTO `ts_student_assess` VALUES ('2018110145', '20211200111000211004', 4, '', '2020-12-08 15:45:21');
INSERT INTO `ts_student_assess` VALUES ('2018110145', '20211200111000211007', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110145', '2021120051100311001', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110145', '2021120061104411002', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110145', '2021120061104411006', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110145', '2021120071102411003', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110149', '20211200111000211004', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110149', '20211200111000211007', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110149', '2021120051100311001', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110149', '2021120061104411002', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110149', '2021120061104411006', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110149', '2021120071102411003', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110155', '20211200111000211004', 3, '很好', '2020-12-13 17:17:09');
INSERT INTO `ts_student_assess` VALUES ('2018110155', '20211200111000211007', 4, '', '2020-12-18 10:27:52');
INSERT INTO `ts_student_assess` VALUES ('2018110155', '2021120051100311001', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110155', '2021120061104411002', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110155', '2021120061104411006', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2018110155', '2021120071102411003', NULL, NULL, NULL);
INSERT INTO `ts_student_assess` VALUES ('2019110111', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110111', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110111', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110111', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110111', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110111', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110111', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110111', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110112', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110112', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110112', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110112', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110112', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110112', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110112', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110112', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110113', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110113', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110113', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110113', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110113', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110113', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110113', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110113', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110114', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110114', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110114', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110114', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110114', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110114', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110114', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110114', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110115', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110115', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110115', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110115', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110115', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110115', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110115', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110115', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110116', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110116', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110116', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110116', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110116', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110116', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110116', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110116', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110117', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110117', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110117', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110117', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110117', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110117', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110117', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110117', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110118', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110118', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110118', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110118', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110118', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110118', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110118', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110118', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110119', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110119', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110119', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110119', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110119', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110119', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110119', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110119', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110120', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110120', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110120', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110120', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110120', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110120', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110120', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110120', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110121', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110121', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110121', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110121', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110121', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110121', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110121', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110121', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110122', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110122', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110122', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110122', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110122', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110122', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110122', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110122', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110123', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110123', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110123', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110123', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110123', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110123', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110123', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110123', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110124', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110124', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110124', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110124', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110124', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110124', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110124', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110124', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110125', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110125', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110125', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110125', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110125', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110125', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110125', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110125', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110126', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110126', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110126', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110126', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110126', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110126', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110126', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110126', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110127', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110127', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110127', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110127', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110127', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110127', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110127', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110127', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110128', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110128', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110128', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110128', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110128', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110128', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110128', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110128', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110129', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110129', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110129', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110129', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110129', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110129', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110129', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110129', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110130', '20211200111000211004', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110130', '20211200111000211007', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110130', '2021120051100311001', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110130', '2021120061104411002', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110130', '2021120061104411006', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110130', '2021120071102411003', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110130', '2021120201102411000', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');
INSERT INTO `ts_student_assess` VALUES ('2019110130', '2021120201102411040', 10, '教师教学质量好，教学手段先进', '2020-12-18 22:25:07');

-- ----------------------------
-- Table structure for ts_student_class
-- ----------------------------
DROP TABLE IF EXISTS `ts_student_class`;
CREATE TABLE `ts_student_class`  (
  `class_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '班级id，例：181103',
  `major_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '专业id',
  `stu_count` int(20) NULL DEFAULT NULL COMMENT '学生人数',
  PRIMARY KEY (`class_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_student_class
-- ----------------------------
INSERT INTO `ts_student_class` VALUES ('1711003', '11001', NULL);
INSERT INTO `ts_student_class` VALUES ('1811003', '11001', NULL);
INSERT INTO `ts_student_class` VALUES ('1911003', '11001', NULL);

-- ----------------------------
-- Table structure for ts_target
-- ----------------------------
DROP TABLE IF EXISTS `ts_target`;
CREATE TABLE `ts_target`  (
  `target_id` int(11) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '指标id',
  `target_content` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '指标内容',
  `target_object` int(11) NULL DEFAULT NULL COMMENT '指标授权对象，1为学生，0为教师',
  `weight` int(11) NULL DEFAULT NULL COMMENT '指标权重',
  PRIMARY KEY (`target_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 26 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_target
-- ----------------------------
INSERT INTO `ts_target` VALUES (1, '教师备课认真，教学质量高。', 0, 5);
INSERT INTO `ts_target` VALUES (2, '使用现代化的教学手段。', 0, 4);
INSERT INTO `ts_target` VALUES (3, '能够启发自我思考的能力，提高对问题的探索和解决能力。', 1, 4);
INSERT INTO `ts_target` VALUES (4, '教材和辅导材料适用，授课难度把控得当', 0, 2);
INSERT INTO `ts_target` VALUES (5, '对所学重点、难点进行多层次知识回顾，加深印象。	', 0, 5);
INSERT INTO `ts_target` VALUES (7, '教材和辅导材料适用，授课难度把控得当', 1, 2);
INSERT INTO `ts_target` VALUES (8, '热心指导学生实践，重视课程建设，积极参与教学研究', 0, 3);
INSERT INTO `ts_target` VALUES (9, '能够根据认知需求和接受程度合理安排课程主题。	', 1, 5);
INSERT INTO `ts_target` VALUES (10, '课前预习形式设置新颖，能够有效引导自主预习。', 1, 4);
INSERT INTO `ts_target` VALUES (11, '对本节课的主要内容进行总结，总结方式明确易懂。', 0, 2);
INSERT INTO `ts_target` VALUES (12, '设置课后考核，检验和加深对所学内容的回顾。	', 0, 3);
INSERT INTO `ts_target` VALUES (13, '教师上课为学生提供讨沦、质疑、探究、合作、交流的机会。', 0, 4);
INSERT INTO `ts_target` VALUES (14, '课堂管理严格，无歧视、侮辱、体罚或变相体罚学生的行为。', 1, 4);
INSERT INTO `ts_target` VALUES (15, '教师授课思路清晰，教学目的明确，能做到重点突出，难点处理得当。', 0, 3);
INSERT INTO `ts_target` VALUES (16, '按教学日历进度实施教学，按计划授课，不缺课，不随意调课，不迟到，不拖堂，不提前下课。', 1, 4);
INSERT INTO `ts_target` VALUES (17, '讲授内容正确，概念清楚，定义准确。', 1, 3);
INSERT INTO `ts_target` VALUES (18, '符合教学大纲或课程基本要求，突出重点，讲清难点。', 1, 4);
INSERT INTO `ts_target` VALUES (19, '密切结合本学科生产实践或实验，内容恰当，举例帮助学生加强对理论知识的理解。', 0, 4);
INSERT INTO `ts_target` VALUES (20, '讲课思路清晰，逻辑性强，语言生动，口齿清楚，板书适当。', 0, 3);

-- ----------------------------
-- Table structure for ts_teacher_assess
-- ----------------------------
DROP TABLE IF EXISTS `ts_teacher_assess`;
CREATE TABLE `ts_teacher_assess`  (
  `teacher_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `assessed_teacher_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `term_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `appraise_score` int(11) NULL DEFAULT NULL,
  `appraise_content` text CHARACTER SET utf8 COLLATE utf8_general_ci NULL,
  `submit_time` datetime(0) NULL DEFAULT NULL,
  PRIMARY KEY (`teacher_no`, `assessed_teacher_no`, `term_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_teacher_assess
-- ----------------------------
INSERT INTO `ts_teacher_assess` VALUES ('10000', '10000', '2020-2021-1', 4, '', '2020-12-18 10:54:39');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '2001110002', '2020-2021-1', 3, '', '2020-12-17 13:19:38');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '2001110003', '2020-2021-1', 5, '很好。。。。。', '2020-12-19 11:27:01');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '2004110002', '2020-2021-1', 10, '备课认真，教学形式多', '2020-12-18 18:41:08');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '200511003', '2020-2021-1', 10, NULL, '2020-12-18 18:41:08');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '200611044', '2020-2021-1', 10, '备课认真，教学形式多', '2020-12-12 12:12:12');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011033', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011044', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011055', '2020-2021-1', 10, '备课认真，教学形式多', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011056', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011057', '2020-2021-1', 10, '使用现代化的教学手段', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011058', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011059', '2020-2021-1', 10, '使用现代化的教学手段', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011060', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011061', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011062', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011063', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011064', '2020-2021-1', 10, '老师态度很好', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011065', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011066', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011067', '2020-2021-1', 10, '认真负责', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011068', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011070', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011072', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '201011074', '2020-2021-1', 10, '', '2020-12-18 22:09:49');
INSERT INTO `ts_teacher_assess` VALUES ('10000', '2012110101', '2020-2021-1', 5, '', '2020-12-18 23:42:57');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '10000', '2020-2021-1', 4, '', '2020-12-18 18:08:49');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '2001110002', '2020-2021-1', 5, '备课认真，教学形式多', '2020-12-09 20:00:59');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '200411010', '2020-2021-1', 2, '教学方法需要改进', '2020-12-13 17:09:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '200511003', '2020-2021-1', 4, '使用现代化的教学手段', '2020-12-17 17:25:26');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011033', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011044', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011055', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011056', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011057', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011058', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011059', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011060', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011061', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011062', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011063', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011064', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011065', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011066', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011067', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011068', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011069', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011070', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011071', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011072', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011073', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110002', '201011074', '2020-2021-1', 10, '', '2020-12-18 22:10:51');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '10000', '2020-2021-1', 5, '', '2020-12-18 18:41:08');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '2001110002', '2020-2021-1', 5, '', '2020-12-18 14:12:08');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '200511003', '2020-2021-1', 10, NULL, '2020-12-12 12:12:12');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '200611044', '2020-2021-1', 10, NULL, '2020-12-13 13:13:12');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011033', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011044', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011055', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011056', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011057', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011058', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011059', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011060', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011061', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011062', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011063', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011064', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011065', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011066', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011067', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011068', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011069', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011070', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011071', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011072', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011073', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('2001110004', '201011074', '2020-2021-1', 10, '', '2020-12-18 22:11:06');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '10000', '2020-2021-1', 8, NULL, '2020-12-18 18:41:08');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '2001110002', '2020-2021-1', 3, '教学质量高，学生容易听懂', '2020-12-12 14:12:02');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '200411010', '2020-2021-1', 5, '教学内容与教材同步，教学思路清晰', '2020-11-11 11:00:00');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '200511003', '2020-2021-1', 12, '教学质量高', '2020-11-28 17:32:22');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011033', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011044', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011055', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011056', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011057', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011058', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011059', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011060', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011061', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011062', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011063', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011064', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011065', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011066', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011067', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011068', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011069', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011070', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011071', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011072', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011073', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200411010', '201011074', '2020-2021-1', 10, '', '2020-12-18 22:11:18');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '10000', '2020-2021-1', 10, NULL, '2020-12-18 18:41:08');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '2001110002', '2020-2021-1', 10, NULL, '2020-12-18 18:41:08');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '2004110002', '2020-2021-1', 8, NULL, '2020-12-12 12:12:12');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '200511003', '2020-2021-1', 10, NULL, '2020-12-18 18:41:08');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011033', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011044', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011055', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011056', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011057', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011058', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011059', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011060', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011061', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011062', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011063', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011064', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011065', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011066', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011067', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011068', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011069', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011070', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011071', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011072', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011073', '2020-2021-1', 10, '', '2020-12-18 22:11:29');
INSERT INTO `ts_teacher_assess` VALUES ('200611044', '201011074', '2020-2021-1', 10, '', '2020-12-18 22:11:29');

-- ----------------------------
-- Table structure for ts_teacher_course
-- ----------------------------
DROP TABLE IF EXISTS `ts_teacher_course`;
CREATE TABLE `ts_teacher_course`  (
  `tc_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '教师-课程id',
  `course_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '课程号',
  `teacher_no` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '教师号',
  `term_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学期id',
  `appraise_sum` int(11) NOT NULL DEFAULT 0 COMMENT '评价总分',
  `address` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上课地址',
  `time` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '上课时间，包括周几第几节课',
  `begin_week` int(11) NULL DEFAULT NULL COMMENT '开课周数',
  `end_week` int(11) NULL DEFAULT NULL COMMENT '结课周数',
  PRIMARY KEY (`tc_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_teacher_course
-- ----------------------------
INSERT INTO `ts_teacher_course` VALUES ('1819120051100311001', '11001', '200511003', '2018-2019-1', 0, '第一教学楼A304', 'Mon_1-2,Wed_1-2,', 3, 19);
INSERT INTO `ts_teacher_course` VALUES ('1819120061104411002', '11002', '200611044', '2018-2019-1', 0, '第一教学楼C302', 'Tues_3-4,Fri_1-2,', 3, 16);
INSERT INTO `ts_teacher_course` VALUES ('1819120071102411003', '11003', '200711024', '2018-2019-1', 0, '第一教学楼B203', 'Mon_8-9,Thur_3-4,', 4, 18);
INSERT INTO `ts_teacher_course` VALUES ('20211200111000211004', '11004', '2001110002', '2020-2021-1', 122, '第一教学楼B213', 'Mon_1-2,Thur_6-7,', 3, 16);
INSERT INTO `ts_teacher_course` VALUES ('20211200111000211007', '11007', '2001110002', '2020-2021-1', 12, '第一教学楼B404', 'Fri_3-4,', 1, 14);
INSERT INTO `ts_teacher_course` VALUES ('2021120051100311001', '11001', '200511003', '2020-2021-1', 108, '第一教学楼A324', 'Wed_3-4,Wed_6-7,', 3, 19);
INSERT INTO `ts_teacher_course` VALUES ('2021120061104411002', '11002', '200611044', '2020-2021-1', 9, '第一教学楼C201', 'Tues_6-7,', 3, 19);
INSERT INTO `ts_teacher_course` VALUES ('2021120061104411006', '11006', '200611044', '2020-2021-1', 4, '第一教学楼B501', 'Mon_3-4,Wed_1-2,', 5, 16);
INSERT INTO `ts_teacher_course` VALUES ('2021120071102411003', '11003', '200711024', '2020-2021-1', 101, '第一教学楼A201', 'Thur_1-2,Fri_6-7,', 3, 19);
INSERT INTO `ts_teacher_course` VALUES ('2021120201102411000', '11005', '10000', '2020-2021-1', 99, '第一教学楼A201', 'Thur_3-4,Fri_1-2,', 3, 19);
INSERT INTO `ts_teacher_course` VALUES ('2021120201102411040', '11002', '2001110004', '2020-2021-1', 99, '第一教学楼A201', 'Tues_1-2,', 3, 19);

-- ----------------------------
-- Table structure for ts_term
-- ----------------------------
DROP TABLE IF EXISTS `ts_term`;
CREATE TABLE `ts_term`  (
  `term_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学期id',
  `term_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学期名',
  `begin_date` date NULL DEFAULT NULL COMMENT '开始时间',
  `end_date` date NULL DEFAULT NULL COMMENT '结束时间',
  `student_weight` double(11, 1) NULL DEFAULT NULL COMMENT '学生占比',
  `teacher_weight` double(11, 1) NULL DEFAULT NULL COMMENT '教师占比',
  PRIMARY KEY (`term_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_term
-- ----------------------------
INSERT INTO `ts_term` VALUES ('2018-2019-1', '2018-2019学年第一学期', '2018-08-15', '2019-01-31', 0.3, 0.7);
INSERT INTO `ts_term` VALUES ('2018-2019-2', '2018-2019学年第二学期', '2019-02-15', '2019-07-30', 0.5, 0.5);
INSERT INTO `ts_term` VALUES ('2019-2020-1', '2019-2020学年第一学期', '2019-08-15', '2020-01-31', 0.4, 0.6);
INSERT INTO `ts_term` VALUES ('2019-2020-2', '2019-2020学年第二学期', '2020-02-15', '2020-07-31', 0.2, 0.8);
INSERT INTO `ts_term` VALUES ('2020-2021-1', '2020-2021学年第一学期', '2020-08-15', '2021-02-10', 0.6, 0.4);
INSERT INTO `ts_term` VALUES ('2020-2021-2', '2020-2021学年第二学期', '2021-02-11', '2021-07-20', 0.4, 0.6);
INSERT INTO `ts_term` VALUES ('2021-2022-1', '2021-2022学年第一学期', '2021-07-21', '2022-01-31', NULL, NULL);
INSERT INTO `ts_term` VALUES ('2021-2022-2', '2021-2022学年第二学期', '2022-02-01', '2022-07-30', NULL, NULL);

-- ----------------------------
-- Table structure for ts_term_target
-- ----------------------------
DROP TABLE IF EXISTS `ts_term_target`;
CREATE TABLE `ts_term_target`  (
  `term_id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '学期号',
  `target_id` int(11) NOT NULL COMMENT '指标号',
  `weight` int(11) NULL DEFAULT NULL COMMENT '指标权重',
  `term_target_object` int(11) NULL DEFAULT NULL COMMENT '授予对象，1为学生，0为老师',
  PRIMARY KEY (`term_id`, `target_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_term_target
-- ----------------------------
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 1, 6, 1);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 2, 4, 0);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 3, 4, 1);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 4, 2, 0);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 5, 5, 0);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 7, 2, 1);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 8, 5, 1);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 9, 5, 1);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 10, 4, 1);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 11, 3, 0);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 12, 3, 0);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 13, 4, 0);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 14, 4, 1);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 15, 3, 0);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 16, 4, 1);
INSERT INTO `ts_term_target` VALUES ('2020-2021-1', 18, 4, 1);

-- ----------------------------
-- Table structure for ts_user
-- ----------------------------
DROP TABLE IF EXISTS `ts_user`;
CREATE TABLE `ts_user`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `username` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int(4) NULL DEFAULT NULL COMMENT '性别',
  `password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `birth` date NULL DEFAULT NULL COMMENT '生日',
  `degree` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `graduated` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '毕业院校',
  `political` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '政治面貌',
  `entry_year` int(20) NULL DEFAULT NULL COMMENT '入职年份',
  `sum_hour` int(11) NULL DEFAULT NULL COMMENT '总学时',
  `dept_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所在学院id',
  `professional` varchar(30) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称',
  `professional_time` date NULL DEFAULT NULL COMMENT '职称评定时间',
  `available_dept_ids` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `teacher_id`(`user_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_user
-- ----------------------------
INSERT INTO `ts_user` VALUES ('10000', 'ROOT2', 1, '$2a$10$0SY5E1IjTgpE8ZcoUxFM8.IlrHdJCou3Pel3D0vLJN9F0eh/a71D2', '123@qq.com', '2020-06-10', '硕士', '四川师范大学', '党员', 2011, 600, '110', '教授', '2020-12-24', '110,111,112,');
INSERT INTO `ts_user` VALUES ('2001110002', '张东山', 1, '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '123@qq.com', '1980-12-12', '硕士', '清华大学', '群众', 2011, 582, '110', '副教授', '2008-12-12', '');
INSERT INTO `ts_user` VALUES ('2001110003', '督导', 1, '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '123@qq.com', '1972-11-29', '博士', '清华大学', '党员', 2011, 582, '110', '教授', '2020-11-26', '');
INSERT INTO `ts_user` VALUES ('2001110004', '伍圆圆', 0, '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '123@qq.com', '1974-11-29', '博士', '清华大学', '党员', 2011, 582, '110', '教授', '2020-11-05', '110,');
INSERT INTO `ts_user` VALUES ('200411010', '陈志伟', 1, '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '123@qq.com', '1980-03-03', '硕士', '四川大学', '党员', 2005, 640, '110', '教授', '2008-10-10', '110,');
INSERT INTO `ts_user` VALUES ('200511003', '张东山', 1, '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '123@qq.com', '1980-12-12', '博士', '四川大学', '党员', 2005, 720, '110', '博士', '2008-12-12', '110,');
INSERT INTO `ts_user` VALUES ('200611044', '王新梅', 0, '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '123@qq.com', '1975-12-02', '博士', '电子科技大学', '党员', 2006, 640, '110', '副教授', '2009-11-11', '110,');
INSERT INTO `ts_user` VALUES ('200711024', '李辉鹏', 1, '$2a$10$Li0M5deZbOxs3jtAmN54FOwhP1KV8yG/RunFmihkF0/5sXp11ir4K', '123@qq.com', '1980-03-03', '硕士', '上海交通大学', '党员', 2007, 490, '111', '副教授', '2012-11-11', '');
INSERT INTO `ts_user` VALUES ('201011033', '蔡杨', 1, '$2a$10$iiJObbCPM1elPAA56SL91udXRCSxkPCw9vc2azogwmmMQ7Qd18GKG', '123@qq.com', '1975-10-25', '博士', '清华大学', '党员', 2010, 569, '110', '教授', '2005-10-10', NULL);
INSERT INTO `ts_user` VALUES ('201011044', '曹佳', 0, '$2a$10$3nxydEdOGYFGVcKRibkga.c5cx6JjoJZr4tGAX1x.rwe01y3/ZnaS', '123@qq.com', '1975-10-26', '博士', '清华大学', '党员', 2011, 582, '110', '教授', '2005-10-11', NULL);
INSERT INTO `ts_user` VALUES ('201011055', '周福清', 1, '$2a$10$Bl0wD/3SH.asbqQtccTG1.XjMYEJV785xRoMaHzHwFl9kQBKkYjO.', '123@qq.com', '1975-10-25', '博士', '清华大学', '党员', 2010, 569, '112', '教授', '2005-10-10', '');
INSERT INTO `ts_user` VALUES ('201011056', '李敏', 0, '$2a$10$rkM0oCwBDVaJ0ymgl6v0fuVSzpbO6TdKEV8DDWic8Q8dNlvL93IoO', '123@qq.com', '1975-10-26', '博士', '清华大学', '党员', 2011, 582, '113', '教授', '2005-10-11', '');
INSERT INTO `ts_user` VALUES ('201011057', '曹阳', 0, '$2a$10$tjfHtWa4Dbesuy7W4jLYzOerlQB8RdZ2skX22HHkf14xMXM28n8nG', '123@qq.com', '1975-10-27', '博士', '北京大学', '党员', 2011, 582, '110', '教授', '2005-10-12', NULL);
INSERT INTO `ts_user` VALUES ('201011058', '车小强', 0, '$2a$10$ul3W9f6wwcfQNUK5nEFXH.rKSoUxn/PNx.za9casowfbYL5gc9NPy', '123@qq.com', '1975-10-28', '博士', '北京大学', '党员', 2011, 582, '110', '教授', '2005-10-13', NULL);
INSERT INTO `ts_user` VALUES ('201011059', '陈贝贝', 0, '$2a$10$9wrR0pzNpvHgOIj4uU5utOxLam/g84VcnaeOrXJX.1pd061a3NwwO', '123@qq.com', '1975-10-29', '博士', '南京大学', '党员', 2011, 582, '110', '教授', '2005-10-14', NULL);
INSERT INTO `ts_user` VALUES ('201011060', '陈冰', 0, '$2a$10$XGal50pexhUVK2UQuBtgbObymA6LNuUfsICf5de4OOEEcX2iUTOJm', '123@qq.com', '1975-10-30', '博士', '南京大学', '党员', 2011, 582, '110', '教授', '2005-10-15', NULL);
INSERT INTO `ts_user` VALUES ('201011061', '陈昌达', 1, '$2a$10$umPN9uuHjlf1mpfBQcWVc.XuCr3TO5swZVqmjtk3NsXF6y7Q.ee0q', '123@qq.com', '1975-10-31', '博士', '西安交通大学', '党员', 2011, 582, '110', '教授', '2005-10-16', NULL);
INSERT INTO `ts_user` VALUES ('201011062', '陈晨', 1, '$2a$10$39/LIQFvwN6ZZ7hfKZWPsOAvMhRQsVEcdrngJyY.D0SECqeSrKTra', '123@qq.com', '1975-11-01', '博士', '复旦大学', '党员', 2011, 582, '110', '教授', '2005-10-17', NULL);
INSERT INTO `ts_user` VALUES ('201011063', '陈丹墨', 1, '$2a$10$ZydDeNqJotSVw6.ZiAGYs.FnEBhtwc2ZQoqqsWI/.cjwCAayxTkom', '123@qq.com', '1975-11-02', '博士', '复旦大学', '党员', 2011, 582, '110', '教授', '2005-10-18', NULL);
INSERT INTO `ts_user` VALUES ('201011064', '陈光龙', 1, '$2a$10$RGNa5F8gtgUCDEzw.rf9CeL1QYVy03qmQNPRdoBDHia.IRXHuJXKe', '123@qq.com', '1975-11-03', '博士', '复旦大学', '党员', 2011, 582, '110', '教授', '2005-10-19', NULL);
INSERT INTO `ts_user` VALUES ('201011065', '程诗晴', 0, '$2a$10$MMyna3WEv64M6Qb2/oEExeXFATwd7KmIm9x.HMDn5BpSaeKf4HZn6', '123@qq.com', '1975-11-04', '博士', '浙江大学', '党员', 2011, 582, '110', '教授', '2005-10-20', NULL);
INSERT INTO `ts_user` VALUES ('201011066', '程蕴贤', 0, '$2a$10$hySH.L6en7CsC9TkVl9hQuT0KrItf3iOSX33WfEz2VdjEuJrftMB2', '123@qq.com', '1975-11-05', '博士', '浙江大学', '党员', 2011, 582, '110', '教授', '2005-10-21', NULL);
INSERT INTO `ts_user` VALUES ('201011067', '仇香凤', 0, '$2a$10$l1nzGTzysilP6HTLz4mrSeIbw9YCx7uObW07kPds2VrWc.PYsBv3m', '123@qq.com', '1975-11-06', '博士', '浙江大学', '党员', 2011, 582, '110', '教授', '2005-10-22', NULL);
INSERT INTO `ts_user` VALUES ('201011068', '代国宏', 1, '$2a$10$pIBX7WP5/x0V1dmzZe2oe.fxuMS4Bl6IGucny2ijuJYaSHD5tPQ5O', '123@qq.com', '1975-11-07', '博士', '电子科技大学', '党员', 2011, 582, '110', '教授', '2005-10-23', NULL);
INSERT INTO `ts_user` VALUES ('201011069', '代小丽', 0, '$2a$10$vhPBV3KAfd5.QGB95yNyFea1NlimiCJSzVqBKyMmsPg.u4xx.1oRC', '123@qq.com', '1975-11-08', '博士', '四川大学', '党员', 2011, 582, '110', '教授', '2005-10-24', NULL);
INSERT INTO `ts_user` VALUES ('201011070', '戴世发', 0, '$2a$10$kZNpVj1b0Wl5oUhdh3xam.jK3uk5fGhPu2HX9n/olz2xTt8w6z2s.', '123@qq.com', '1975-11-09', '博士', '四川大学', '党员', 2011, 582, '110', '教授', '2005-10-25', NULL);
INSERT INTO `ts_user` VALUES ('201011071', '邓艾瑾', 0, '$2a$10$nAWP0uC28n5.mKnJ0lsKv.a5WmdlI4AdbN1BJRTWqvt7qLtzQBy2y', '123@qq.com', '1975-11-10', '博士', '武汉大学', '党员', 2011, 582, '110', '教授', '2005-10-26', NULL);
INSERT INTO `ts_user` VALUES ('201011072', '邓东梅', 0, '$2a$10$usMJpxwn5Q06ZrpH/iBn0OD9IOskaq/N1pCFq6fYm0nI0L/28s9ea', '123@qq.com', '1975-11-11', '博士', '武汉大学', '党员', 2011, 582, '110', '教授', '2005-10-27', NULL);
INSERT INTO `ts_user` VALUES ('201011073', '邓江发', 1, '$2a$10$9sQUChnU6zm1wwjbSwvEBexfpJsYmxror6pw0j.NbmA3zewp.0FZW', '123@qq.com', '1975-11-12', '博士', '上海交通大学', '党员', 2011, 582, '110', '教授', '2005-10-28', NULL);
INSERT INTO `ts_user` VALUES ('201011074', '丁仕杰', 1, '$2a$10$hFmJkUhUXCL38AJa344TBOopmsVGzq/LSAUbgDopAYzvg9Qba3.Ma', '123@qq.com', '1975-11-13', '博士', '上海交通大学', '党员', 2011, 582, '110', '教授', '2005-10-29', NULL);
INSERT INTO `ts_user` VALUES ('201011084', '林七七', 1, '$2a$10$UGPJYuU0SMFoNkVCARNPgOzEcEGHvuwwB6rfXq0Axb2vTDcu1BEVC', '123@qq.com', '1975-10-25', '博士', '清华大学', '党员', 2010, 569, '110', '教授', '2005-10-10', NULL);
INSERT INTO `ts_user` VALUES ('201011099', '赵六', 1, '$2a$10$RbzHUKtzBU82QB3MaBO8CO3WYgLvgjQr/O.Q.ldsNtwGQjJ2U8H/.', '123@qq.com', '1975-10-25', '博士', '清华大学', '党员', 2010, 569, '110', '教授', '2005-10-10', NULL);
INSERT INTO `ts_user` VALUES ('2012110101', '优秀教师1', 1, '$2a$10$qV5PoxRh1zBVJ1Ztj5pTEe1OBvlxScKF6V1GSjJtM9scswBKK8nGG', '123@qq.com', '1980-01-01', '博士', '清华大学', '党员', 2012, 600, '110', '教授', '2005-10-10', NULL);
INSERT INTO `ts_user` VALUES ('201212003', '董发武', 1, '$2a$10$/hibwg/CP98Xiu/yKebkEOY740WDJmlUH6rHjio0fCpbfQR43GFHa', '123@qq.com', '1975-10-25', '博士', '清华大学', '党员', 2010, 569, '111', '教授', '2005-10-10', NULL);
INSERT INTO `ts_user` VALUES ('201212004', '董红玲', 0, '$2a$10$9kB0XENhEDp9.O/Mr74ykevwSoJj7CsmD8D3cm6C.l39PR8WB8MYS', '123@qq.com', '1975-10-26', '博士', '清华大学', '党员', 2011, 582, '111', '教授', '2005-10-11', NULL);
INSERT INTO `ts_user` VALUES ('201212005', '董欢', 0, '$2a$10$mO2qUQ2xbuYdJkkWCcKvPe.zKbo.8I.NFCgmJd9dQbXt/J7aPeQzS', '123@qq.com', '1975-10-27', '博士', '北京大学', '党员', 2011, 582, '111', '教授', '2005-10-12', NULL);
INSERT INTO `ts_user` VALUES ('201212006', '杜佳红', 0, '$2a$10$KuV6A0NNzlHbuu5JV64dweUvDFEEvpoGH0fY7fd.9ITmvlzwL7xxG', '123@qq.com', '1975-10-28', '博士', '北京大学', '党员', 2011, 582, '111', '教授', '2005-10-13', NULL);
INSERT INTO `ts_user` VALUES ('201212007', '杜金琼', 0, '$2a$10$z2E/9Z.X8Gfk/V/KHqrv5ukbczL8rBB0Xa87g6/pT6iepTN0cNo0C', '123@qq.com', '1975-10-29', '博士', '南京大学', '党员', 2011, 582, '111', '教授', '2005-10-14', NULL);
INSERT INTO `ts_user` VALUES ('201212008', '杜明月', 0, '$2a$10$1ZjXo.CUBFvLwisZXogSnOB.wquEgf5y/8f2oXciTamOvX74ZLnqy', '123@qq.com', '1975-10-30', '博士', '南京大学', '党员', 2011, 582, '111', '教授', '2005-10-15', NULL);
INSERT INTO `ts_user` VALUES ('201212009', '杜希鹏', 1, '$2a$10$b4DRDBBDCL22eP8Ne02HMeHSCKzS/7Wym4jc5DZZLm.zqPGo52MtS', '123@qq.com', '1975-10-31', '博士', '西安交通大学', '党员', 2011, 582, '111', '教授', '2005-10-16', NULL);
INSERT INTO `ts_user` VALUES ('201212010', '凡涛', 1, '$2a$10$NrIUQIY8FuBH7zzxBqPN1eKd9JfaGPhduBrG8dP9pwWvi8.VAYEwy', '123@qq.com', '1975-11-01', '博士', '复旦大学', '党员', 2011, 582, '111', '教授', '2005-10-17', NULL);
INSERT INTO `ts_user` VALUES ('201212011', '范成军', 1, '$2a$10$.KlVD9cwXtIyjKxhZTnFqe4GCk3sGkkr868XgZjhLHlHDJATC1y2O', '123@qq.com', '1975-11-02', '博士', '复旦大学', '党员', 2011, 582, '111', '教授', '2005-10-18', NULL);
INSERT INTO `ts_user` VALUES ('201212012', '范潇', 1, '$2a$10$j4pPfG5kJQ9am4FqjILcKeRLp0puX8Fc9SMVOV5ABkWBMbcbZ8C8.', '123@qq.com', '1975-11-03', '博士', '复旦大学', '党员', 2011, 582, '111', '教授', '2005-10-19', NULL);
INSERT INTO `ts_user` VALUES ('201212013', '樊静', 0, '$2a$10$0XktjfTbPdh5eUcUUiKwtugNtsg5.BVdpg21O43uGjShSW.q2On2.', '123@qq.com', '1975-11-04', '博士', '浙江大学', '党员', 2011, 582, '111', '教授', '2005-10-20', NULL);
INSERT INTO `ts_user` VALUES ('201212014', '范冬梅', 0, '$2a$10$RR.vixf5FdDmrxabgP7UGeT4XRHrw13tdfiZy0dZS0y.4ttJkqX12', '123@qq.com', '1975-11-05', '博士', '浙江大学', '党员', 2011, 582, '111', '教授', '2005-10-21', NULL);
INSERT INTO `ts_user` VALUES ('201212015', '范佑丹', 0, '$2a$10$W.FfZzRFzmvkaiA4l3qzLO7CjuWwXo/JFCJvv2ISl6hv3hvsp8wmu', '123@qq.com', '1975-11-06', '博士', '浙江大学', '党员', 2011, 582, '111', '教授', '2005-10-22', NULL);
INSERT INTO `ts_user` VALUES ('201212016', '方东', 1, '$2a$10$GQrwvX.lyZlGZBdq8PI5QeL/xs6uGZyfOqg.VqAOQGGEPxqmSAE1a', '123@qq.com', '1975-11-07', '博士', '电子科技大学', '党员', 2011, 582, '111', '教授', '2005-10-23', NULL);
INSERT INTO `ts_user` VALUES ('201212017', '方若华', 0, '$2a$10$wLDD84gY9EV6j9hpoljPWula2c.37RfPWlFq5sq09QikYW5sFGayC', '123@qq.com', '1975-11-08', '博士', '四川大学', '党员', 2011, 582, '111', '教授', '2005-10-24', NULL);
INSERT INTO `ts_user` VALUES ('201212018', '戴世发', 0, '$2a$10$.3q0OwQn5WHXtbFEqd0sXu7dVM2Um781S1tA8pUEPdWYOPrjMWdwG', '123@qq.com', '1975-11-09', '博士', '四川大学', '党员', 2011, 582, '111', '教授', '2005-10-25', NULL);
INSERT INTO `ts_user` VALUES ('201212019', '方小雪', 0, '$2a$10$W2qkFtVBWUYvg6/Xp21umuLw5RO2AR1NqPKmpCLQ3hDBlez2CTwBC', '123@qq.com', '1975-11-10', '博士', '武汉大学', '党员', 2011, 582, '111', '教授', '2005-10-26', NULL);
INSERT INTO `ts_user` VALUES ('201212020', '房宗花', 0, '$2a$10$1cOF0Pt79o8FdmainCaN8ulm6jvj1E2FfaRppukdGyfZX/.RjmyA2', '123@qq.com', '1975-11-11', '博士', '武汉大学', '党员', 2011, 582, '111', '教授', '2005-10-27', NULL);
INSERT INTO `ts_user` VALUES ('201212021', '冯丹', 1, '$2a$10$ekqOGvPw1VOb8rXkAN8gaOe0Ckbc981qqjCILTV0UrY/OyawRvg/i', '123@qq.com', '1975-11-12', '博士', '上海交通大学', '党员', 2011, 582, '111', '教授', '2005-10-28', NULL);
INSERT INTO `ts_user` VALUES ('201212022', '冯国平', 1, '$2a$10$.eLFGic1ZtLwQ8PmBQki5uBQyXKa.eWUZv7eDfPMQDIL3ifdGJRVS', '123@qq.com', '1975-11-13', '博士', '上海交通大学', '党员', 2011, 582, '111', '教授', '2005-10-29', NULL);
INSERT INTO `ts_user` VALUES ('201212023', '冯凯', 1, '$2a$10$.eLFGic1ZtLwQ8PmBQki5uBQyXKa.eWUZv7eDfPMQDIL3ifdGJRVS', '123@qq.com', '2020-12-15', '博士', '上海交通大学', '党员', 2011, 582, '110', '教授', '2020-12-15', NULL);

-- ----------------------------
-- Table structure for ts_user_role
-- ----------------------------
DROP TABLE IF EXISTS `ts_user_role`;
CREATE TABLE `ts_user_role`  (
  `user_id` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of ts_user_role
-- ----------------------------
INSERT INTO `ts_user_role` VALUES ('10000', 2);
INSERT INTO `ts_user_role` VALUES ('10000', 3);
INSERT INTO `ts_user_role` VALUES ('10000', 4);
INSERT INTO `ts_user_role` VALUES ('10000', 5);
INSERT INTO `ts_user_role` VALUES ('2001110002', 2);
INSERT INTO `ts_user_role` VALUES ('2001110002', 3);
INSERT INTO `ts_user_role` VALUES ('2001110003', 2);
INSERT INTO `ts_user_role` VALUES ('2001110003', 3);
INSERT INTO `ts_user_role` VALUES ('2001110004', 2);
INSERT INTO `ts_user_role` VALUES ('2001110004', 3);
INSERT INTO `ts_user_role` VALUES ('2001110004', 4);
INSERT INTO `ts_user_role` VALUES ('200411010', 2);
INSERT INTO `ts_user_role` VALUES ('200411010', 3);
INSERT INTO `ts_user_role` VALUES ('200411010', 4);
INSERT INTO `ts_user_role` VALUES ('200511003', 2);
INSERT INTO `ts_user_role` VALUES ('200511003', 3);
INSERT INTO `ts_user_role` VALUES ('200511003', 4);
INSERT INTO `ts_user_role` VALUES ('200611044', 2);
INSERT INTO `ts_user_role` VALUES ('200611044', 3);
INSERT INTO `ts_user_role` VALUES ('200611044', 4);
INSERT INTO `ts_user_role` VALUES ('200711024', 2);
INSERT INTO `ts_user_role` VALUES ('201011033', 2);
INSERT INTO `ts_user_role` VALUES ('201011044', 2);
INSERT INTO `ts_user_role` VALUES ('201011055', 2);
INSERT INTO `ts_user_role` VALUES ('201011056', 2);
INSERT INTO `ts_user_role` VALUES ('201011057', 2);
INSERT INTO `ts_user_role` VALUES ('201011058', 2);
INSERT INTO `ts_user_role` VALUES ('201011059', 2);
INSERT INTO `ts_user_role` VALUES ('201011060', 2);
INSERT INTO `ts_user_role` VALUES ('201011061', 2);
INSERT INTO `ts_user_role` VALUES ('201011062', 2);
INSERT INTO `ts_user_role` VALUES ('201011063', 2);
INSERT INTO `ts_user_role` VALUES ('201011064', 2);
INSERT INTO `ts_user_role` VALUES ('201011065', 2);
INSERT INTO `ts_user_role` VALUES ('201011066', 2);
INSERT INTO `ts_user_role` VALUES ('201011067', 2);
INSERT INTO `ts_user_role` VALUES ('201011068', 2);
INSERT INTO `ts_user_role` VALUES ('201011069', 2);
INSERT INTO `ts_user_role` VALUES ('201011070', 2);
INSERT INTO `ts_user_role` VALUES ('201011071', 2);
INSERT INTO `ts_user_role` VALUES ('201011072', 2);
INSERT INTO `ts_user_role` VALUES ('201011073', 2);
INSERT INTO `ts_user_role` VALUES ('201011074', 2);
INSERT INTO `ts_user_role` VALUES ('201011084', 2);
INSERT INTO `ts_user_role` VALUES ('201011099', 2);
INSERT INTO `ts_user_role` VALUES ('2012110101', 2);
INSERT INTO `ts_user_role` VALUES ('201212003', 2);
INSERT INTO `ts_user_role` VALUES ('201212004', 2);
INSERT INTO `ts_user_role` VALUES ('201212005', 2);
INSERT INTO `ts_user_role` VALUES ('201212006', 2);
INSERT INTO `ts_user_role` VALUES ('201212007', 2);
INSERT INTO `ts_user_role` VALUES ('201212008', 2);
INSERT INTO `ts_user_role` VALUES ('201212009', 2);
INSERT INTO `ts_user_role` VALUES ('201212010', 2);
INSERT INTO `ts_user_role` VALUES ('201212011', 2);
INSERT INTO `ts_user_role` VALUES ('201212012', 2);
INSERT INTO `ts_user_role` VALUES ('201212013', 2);
INSERT INTO `ts_user_role` VALUES ('201212014', 2);
INSERT INTO `ts_user_role` VALUES ('201212015', 2);
INSERT INTO `ts_user_role` VALUES ('201212016', 2);
INSERT INTO `ts_user_role` VALUES ('201212017', 2);
INSERT INTO `ts_user_role` VALUES ('201212018', 2);
INSERT INTO `ts_user_role` VALUES ('201212019', 2);
INSERT INTO `ts_user_role` VALUES ('201212020', 2);
INSERT INTO `ts_user_role` VALUES ('201212021', 2);
INSERT INTO `ts_user_role` VALUES ('201212022', 2);
INSERT INTO `ts_user_role` VALUES ('201212023', 2);
INSERT INTO `ts_user_role` VALUES ('2017110111', 1);
INSERT INTO `ts_user_role` VALUES ('2017110112', 1);
INSERT INTO `ts_user_role` VALUES ('2018110114', 1);
INSERT INTO `ts_user_role` VALUES ('2018110120', 1);
INSERT INTO `ts_user_role` VALUES ('2018110145', 1);
INSERT INTO `ts_user_role` VALUES ('2018110149', 1);
INSERT INTO `ts_user_role` VALUES ('2018110155', 1);
INSERT INTO `ts_user_role` VALUES ('2019110111', 1);
INSERT INTO `ts_user_role` VALUES ('2019110112', 1);
INSERT INTO `ts_user_role` VALUES ('2019110113', 1);
INSERT INTO `ts_user_role` VALUES ('2019110114', 1);
INSERT INTO `ts_user_role` VALUES ('2019110115', 1);
INSERT INTO `ts_user_role` VALUES ('2019110116', 1);
INSERT INTO `ts_user_role` VALUES ('2019110117', 1);
INSERT INTO `ts_user_role` VALUES ('2019110118', 1);
INSERT INTO `ts_user_role` VALUES ('2019110119', 1);
INSERT INTO `ts_user_role` VALUES ('2019110120', 1);
INSERT INTO `ts_user_role` VALUES ('2019110121', 1);
INSERT INTO `ts_user_role` VALUES ('2019110122', 1);
INSERT INTO `ts_user_role` VALUES ('2019110123', 1);
INSERT INTO `ts_user_role` VALUES ('2019110124', 1);
INSERT INTO `ts_user_role` VALUES ('2019110125', 1);
INSERT INTO `ts_user_role` VALUES ('2019110126', 1);
INSERT INTO `ts_user_role` VALUES ('2019110127', 1);
INSERT INTO `ts_user_role` VALUES ('2019110128', 1);
INSERT INTO `ts_user_role` VALUES ('2019110129', 1);
INSERT INTO `ts_user_role` VALUES ('2019110130', 1);

-- ----------------------------
-- View structure for all_user
-- ----------------------------
DROP VIEW IF EXISTS `all_user`;
CREATE ALGORITHM = UNDEFINED SQL SECURITY DEFINER VIEW `all_user` AS select `ts_student`.`student_no` AS `account`,`ts_student`.`password` AS `password` from `ts_student` union select `ts_user`.`user_id` AS `account`,`ts_user`.`password` AS `password` from `ts_user`;

SET FOREIGN_KEY_CHECKS = 1;
