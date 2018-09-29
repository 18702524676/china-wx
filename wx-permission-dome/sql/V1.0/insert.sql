-- ----------------------------
-- Records of sys_acl
-- ----------------------------
INSERT INTO `sys_acl` VALUES ('1', '20180904231235_46', '用户列表', '1', '/sys/user/pageUser.json', '1', '1', '3', '', 'cjfAdmin', '2018-09-16 19:13:35', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('2', '20180904231439_76', '角色管理', '1', '/sys/role/role.page', '1', '1', '2', '', 'cjfAdmin', '2018-09-16 19:10:43', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('3', '20180904232752_61', '订单管理页面', '2', '分页查询用户', '1', '1', '2', '', 'wx', '2018-09-17 00:05:05', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('4', '20180904232856_13', '用户管理', '1', '/sys/dept/dept.page', '1', '1', '1', '', 'wx', '2018-09-24 01:59:46', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('5', '20180904232921_76', '权限列表', '1', '/sys/acl/pageSysAcl.json', '1', '1', '4', '', 'cjfAdmin', '2018-09-16 19:14:31', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('6', '20180904233000_65', '修改角色权限', '1', '/sys/role/changeAcls.json', '2', '1', '5', '', 'wx', '2018-09-16 20:04:55', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('7', '20180904233020_14', '权限管理', '1', '/sys/aclModule/acl.page', '1', '1', '2', '', 'cjfAdmin', '2018-09-16 19:06:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl` VALUES ('8', '20180916200449_14', '修改用户角色', '1', '/sys/role/changeUsers.json', '2', '1', '6', '', 'wx', '2018-09-16 20:05:00', '0:0:0:0:0:0:0:1');


-- ----------------------------
-- Records of sys_acl_module
-- ----------------------------
INSERT INTO `sys_acl_module` VALUES ('1', '访问权限管', '0', '0', '1', '1', '', 'wx', '2018-09-23 15:06:37', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_acl_module` VALUES ('2', '自定义注解权限', '0', '0', '2', '1', '', 'cjfAdmin', '2018-09-16 19:10:31', '0:0:0:0:0:0:0:1');


-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('1', '技术部', '0', '0', '1', '', 'cjfAdmin', '2018-09-24 19:56:30', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('3', '后端开发', '1', '0.1', '3', '', 'cjfAdmin', '2018-09-16 19:01:34', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('4', 'java1', '3', '0.1.3', '1', '', 'cjfAdmin', '2018-09-16 19:00:47', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('5', '初级开发-1', '1', '0.1', '1', '', 'cjfAdmin', '2018-09-23 15:00:12', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('6', 'UI设计', '1', '0.1', '2', '', 'cjfAdmin', '2018-09-16 19:01:42', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('7', '中级开发', '1', '0.1', '2', '', 'cjfAdmin', '2018-09-23 13:50:44', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_dept` VALUES ('8', '测试开发', '1', '0.1', '1', '', 'cjfAdmin', '2018-09-23 13:51:26', '0:0:0:0:0:0:0:1');


-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('1', '1', '5', '{\"id\":5,\"name\":\"初级开发\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-23 14:06:56\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":5,\"name\":\"初级开发-1\",\"parentId\":1,\"level\":\"0.1\",\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-23 15:00:12\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'cjfAdmin', '2018-09-23 15:00:12', '0:0:0:0:0:0:0:1', '0');
INSERT INTO `sys_log` VALUES ('3', '5', '2', '{\"id\":2,\"name\":\"总经理\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-05 23:06:33\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":2,\"name\":\"总经理2\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:01:25\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'wx', '2018-09-23 15:01:26', '0:0:0:0:0:0:0:1', '0');
INSERT INTO `sys_log` VALUES ('4', '5', '1', '{\"id\":1,\"name\":\"管理员\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-23 14:09:11\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":1,\"name\":\"管理员1\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:03:51\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'wx', '2018-09-23 15:03:52', '0:0:0:0:0:0:0:1', '0');
INSERT INTO `sys_log` VALUES ('5', '3', '1', '{\"id\":1,\"name\":\"访问权限管\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"status\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-16 19:06:09\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":1,\"name\":\"访问权限管1\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"status\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:06:32\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'wx', '2018-09-23 15:06:32', '0:0:0:0:0:0:0:1', '0');
INSERT INTO `sys_log` VALUES ('6', '3', '1', '{\"id\":1,\"name\":\"访问权限管1\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"status\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:06:32\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":1,\"name\":\"访问权限管\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"status\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:06:37\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'wx', '2018-09-23 15:06:37', '0:0:0:0:0:0:0:1', '0');
INSERT INTO `sys_log` VALUES ('7', '4', '4', '{\"id\":4,\"code\":\"20180904232856_13\",\"name\":\"用户管理\",\"aclModuleId\":1,\"url\":\"/sys/dept/dept.page\",\"type\":1,\"status\":1,\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-16 19:05:30\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":4,\"name\":\"用户管理\",\"aclModuleId\":2,\"url\":\"/sys/dept/dept.page\",\"type\":1,\"status\":1,\"seq\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:06:54\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'wx', '2018-09-23 15:06:54', '0:0:0:0:0:0:0:1', '0');
INSERT INTO `sys_log` VALUES ('8', '4', '4', '{\"id\":4,\"code\":\"20180904232856_13\",\"name\":\"用户管理\",\"aclModuleId\":2,\"url\":\"/sys/dept/dept.page\",\"type\":1,\"status\":1,\"seq\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:06:54\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":4,\"name\":\"用户管理\",\"aclModuleId\":1,\"url\":\"/sys/dept/dept.page\",\"type\":1,\"status\":1,\"seq\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:07:02\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'wx', '2018-09-23 15:07:02', '0:0:0:0:0:0:0:1', '0');
INSERT INTO `sys_log` VALUES ('9', '4', '4', '{\"id\":4,\"name\":\"用户管理\",\"aclModuleId\":1,\"url\":\"/sys/dept/dept.page\",\"type\":1,\"status\":1,\"seq\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:07:02\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":4,\"code\":\"20180904232856_13\",\"name\":\"用户管理\",\"aclModuleId\":2,\"url\":\"/sys/dept/dept.page\",\"type\":1,\"status\":1,\"seq\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:06:54\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'wx', '2018-09-24 01:58:34', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `sys_log` VALUES ('10', '4', '4', '{\"id\":4,\"name\":\"用户管理\",\"aclModuleId\":2,\"url\":\"/sys/dept/dept.page\",\"type\":1,\"status\":1,\"seq\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:06:54\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":4,\"code\":\"20180904232856_13\",\"name\":\"用户管理\",\"aclModuleId\":1,\"url\":\"/sys/dept/dept.page\",\"type\":1,\"status\":1,\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-16 19:05:30\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'wx', '2018-09-24 01:59:46', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `sys_log` VALUES ('11', '5', '2', '{\"id\":2,\"name\":\"总经理2\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:01:25\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":2,\"name\":\"总经理\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-05 23:06:33\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'wx', '2018-09-24 02:07:31', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `sys_log` VALUES ('12', '5', '1', '{\"id\":1,\"name\":\"管理员1\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"wx\",\"operateTime\":\"2018-09-23 15:03:51\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":1,\"name\":\"管理员\",\"type\":1,\"status\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-23 14:09:11\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'wx', '2018-09-24 02:07:58', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `sys_log` VALUES ('13', '1', '1', '{\"id\":1,\"name\":\"技术部\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-16 17:45:35\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":1,\"name\":\"技术部2\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-24 18:47:42\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'cjfAdmin', '2018-09-24 18:47:43', '0:0:0:0:0:0:0:1', '0');
INSERT INTO `sys_log` VALUES ('14', '1', '1', '{\"id\":1,\"name\":\"技术部2\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-24 18:47:43\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":1,\"name\":\"技术部\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-24 19:56:00\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'sysAdmin', '2018-09-24 19:56:01', '127.0.0.1', '0');
INSERT INTO `sys_log` VALUES ('15', '1', '1', '{\"id\":1,\"name\":\"技术部\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-24 19:56:00\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":1,\"name\":\"技术部2\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-24 18:47:43\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'cjfAdmin', '2018-09-24 19:56:10', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `sys_log` VALUES ('16', '1', '1', '{\"id\":1,\"name\":\"技术部2\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-24 18:47:43\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', '{\"id\":1,\"name\":\"技术部\",\"parentId\":0,\"level\":\"0\",\"seq\":1,\"remark\":\"\",\"operator\":\"cjfAdmin\",\"operateTime\":\"2018-09-24 19:56:00\",\"operateIp\":\"0:0:0:0:0:0:0:1\"}', 'cjfAdmin', '2018-09-24 19:56:30', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `sys_log` VALUES ('17', '6', '1', '{\"roleId\":1,\"aclIdlist\":[4,1]}', '{\"roleId\":1,\"aclIdlist\":[4,7,1,5]}', 'sysAdmin', '2018-09-24 20:46:41', '127.0.0.1', '0');
INSERT INTO `sys_log` VALUES ('18', '6', '1', '{\"roleId\":1,\"aclIdlist\":[4,7,1,5]}', '{\"roleId\":1,\"aclIdlist\":[4,1]}', 'wx', '2018-09-24 20:47:00', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `sys_log` VALUES ('19', '6', '2', '{\"roleId\":2,\"aclIdlist\":[4,2,1]}', '{\"roleId\":2,\"aclIdlist\":[4,2,1,3]}', 'sysAdmin', '2018-09-24 20:47:12', '127.0.0.1', '0');
INSERT INTO `sys_log` VALUES ('20', '6', '2', '{\"roleId\":2,\"aclIdlist\":[4,2,1,3]}', '{\"roleId\":2,\"aclIdlist\":[4,2,1]}', 'wx', '2018-09-24 20:47:18', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `sys_log` VALUES ('21', '7', '2', '{\"roleId\":2,\"userIdlist\":[5,7,11,2]}', '{\"roleId\":2,\"userIdlist\":[2,5,7,11,1,3,4,6,8,9,10,12]}', 'sysAdmin', '2018-09-24 20:47:29', '127.0.0.1', '0');
INSERT INTO `sys_log` VALUES ('22', '7', '2', '{\"roleId\":2,\"userIdlist\":[2,5,7,11,1,3,4,6,8,9,10,12]}', '{\"roleId\":2,\"userIdlist\":[5,7,11,2]}', 'wx', '2018-09-24 20:47:56', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `sys_log` VALUES ('23', '7', '1', '{\"roleId\":1,\"userIdlist\":[2,1,3,4,5,6,7,8,9,10,11,12]}', '', 'sysAdmin', '2018-09-24 20:48:36', '127.0.0.1', '0');
INSERT INTO `sys_log` VALUES ('24', '7', '1', '', '{\"roleId\":1,\"userIdlist\":[2,1,3,4,5,6,7,8,9,10,11,12]}', 'wx', '2018-09-24 20:48:49', '0:0:0:0:0:0:0:1', '1');
INSERT INTO `sys_log` VALUES ('25', '7', '1', '{\"roleId\":1,\"userIdlist\":[2,1,3,4,5,6,7,8,9,10,11,12]}', '{\"roleId\":1,\"userIdlist\":[1,2,3,4,5,6,7,8,9,10,11]}', 'sysAdmin', '2018-09-24 20:49:01', '127.0.0.1', '0');
INSERT INTO `sys_log` VALUES ('26', '7', '1', '{\"roleId\":1,\"userIdlist\":[1,2,3,4,5,6,7,8,9,10,11]}', '{\"roleId\":1,\"userIdlist\":[2,1,3,4,5,6,7,8,9,10,11,12]}', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1', '1');


-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '1', '1', '', 'wx', '2018-09-24 02:07:58', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role` VALUES ('2', '总经理', '1', '1', '', 'wx', '2018-09-24 02:07:31', '0:0:0:0:0:0:0:1');


-- ----------------------------
-- Records of sys_role_acl
-- ----------------------------
INSERT INTO `sys_role_acl` VALUES ('116', '1', '4', 'wx', '2018-09-24 20:47:00', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES ('117', '1', '1', 'wx', '2018-09-24 20:47:00', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES ('122', '2', '4', 'wx', '2018-09-24 20:47:18', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES ('123', '2', '2', 'wx', '2018-09-24 20:47:18', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_acl` VALUES ('124', '2', '1', 'wx', '2018-09-24 20:47:18', '0:0:0:0:0:0:0:1');


-- ----------------------------
-- Records of sys_role_user
-- ----------------------------
INSERT INTO `sys_role_user` VALUES ('58', '2', '5', 'wx', '2018-09-24 20:47:56', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('59', '2', '7', 'wx', '2018-09-24 20:47:56', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('60', '2', '11', 'wx', '2018-09-24 20:47:56', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('61', '2', '2', 'wx', '2018-09-24 20:47:56', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('85', '1', '2', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('86', '1', '1', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('87', '1', '3', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('88', '1', '4', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('89', '1', '5', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('90', '1', '6', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('91', '1', '7', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('92', '1', '8', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('93', '1', '9', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('94', '1', '10', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('95', '1', '11', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_role_user` VALUES ('96', '1', '12', 'wx', '2018-09-24 20:49:15', '0:0:0:0:0:0:0:1');


-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'wx', '18702524676', 'admin@163.com', 'E10ADC3949BA59ABBE56E057F20F883E', '7', '1', '', 'sysadmin', '2018-08-30 22:22:03', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('2', 'cjfAdmin', '18702524675', '993152171@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '4', '1', '', 'sysadmin', '2018-08-30 22:14:06', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('3', 'mlAdmin', '18702525675', '993162175@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '6', '1', '', 'sysadmin', '2018-08-30 22:16:40', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('4', 'tybAdmin', '18702525685', '893162175@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '7', '1', '', 'sysadmin', '2018-08-30 22:16:56', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('5', 'zzAdmin', '18752525685', '893162575@qq.com', '', '4', '1', '', 'wx', '2018-09-01 00:36:03', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES ('6', 'zzAdmin', '18752325685', '893192575@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '4', '1', '', 'sysadmin', '2018-08-30 22:17:36', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('7', 'zzAdmin', '18752325688', '893192555@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '5', '1', '', 'sysadmin', '2018-08-30 22:56:59', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('8', 'zzAdmin', '18752321681', '893292555@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '4', '1', '', 'sysadmin', '2018-08-30 23:01:44', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('9', 'zzAdmin', '18752321581', '693292555@qq.com', 'E10ADC3949BA59ABBE56E057F20F883E', '4', '1', '', 'sysadmin', '2018-08-30 23:05:30', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('10', 'wx1993', '18702514676', '993162171@qq.com', '8C9DBE97B9D55052FE7F635D753278D7', '4', '1', '', 'sysadmin', '2018-08-31 22:15:14', '127.0.0.1');
INSERT INTO `sys_user` VALUES ('11', 'xingxing', '18702324676', '9454541@qq.com', '', '6', '1', '', 'wx', '2018-09-01 00:45:34', '0:0:0:0:0:0:0:1');
INSERT INTO `sys_user` VALUES ('12', '中级Java开发工程师', '18720224676', '4945454@qq.com', '', '7', '1', '', 'wx', '2018-09-01 00:46:33', '0:0:0:0:0:0:0:1');


-- ----------------------------
-- Records of user_token_info
-- ----------------------------
INSERT INTO `user_token_info` VALUES ('1', '2', '875f250c-ecca-4cbd-9186-726fd26f015d', '2018-09-24 18:18:03');
INSERT INTO `user_token_info` VALUES ('2', '1', 'd4deedf7-b878-42a7-b17e-faeb457a489b', '2018-09-24 20:46:27');


-- ----------------------------
-- Records of worker_node
-- ----------------------------
INSERT INTO `worker_node` VALUES ('1', '192.168.139.1', '1534669521782-2817', '2', '2018-08-19', '2018-08-19 17:05:21', '2018-08-19 17:05:21');
INSERT INTO `worker_node` VALUES ('2', '192.168.139.1', '1534669971475-19459', '2', '2018-08-19', '2018-08-19 17:12:51', '2018-08-19 17:12:51');
INSERT INTO `worker_node` VALUES ('3', '192.168.139.1', '1534670015494-93019', '2', '2018-08-19', '2018-08-19 17:13:35', '2018-08-19 17:13:35');
INSERT INTO `worker_node` VALUES ('4', '192.168.139.1', '1534670139803-96355', '2', '2018-08-19', '2018-08-19 17:15:39', '2018-08-19 17:15:39');
INSERT INTO `worker_node` VALUES ('5', '192.168.139.1', '1534670193806-24403', '2', '2018-08-19', '2018-08-19 17:16:33', '2018-08-19 17:16:33');
