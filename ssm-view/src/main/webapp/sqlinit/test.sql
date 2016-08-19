/*
Navicat MySQL Data Transfer

Source Server         : my_test_db
Source Server Version : 50621
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50621
File Encoding         : 65001

Date: 2016-08-19 09:46:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `department`
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department` (
  `ID` varchar(40) NOT NULL COMMENT '主键',
  `P_ID` varchar(40) DEFAULT NULL COMMENT '上级部门编码',
  `CODE` varchar(30) NOT NULL COMMENT '部门编码',
  `NAME` varchar(30) NOT NULL COMMENT '部门名称',
  `ESTABLISH_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '用于演示日历控件使用，无实际意义',
  `STATUS` varchar(10) NOT NULL COMMENT '状态',
  `REMARK` varchar(1000) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '创建时间',
  `CREATE_USER` varchar(30) NOT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `UPDATE_USER` varchar(30) DEFAULT NULL COMMENT '修改人',
  `establishTime` datetime DEFAULT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `AK_Key_2` (`CODE`),
  KEY `Index_1` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES ('40283a834d03c6ff014d03ef56cf0002', '0', 'cd001', '市场部', '2015-04-29 14:48:46', 'enabled', '', '2015-04-29 14:48:37', 'xhf', '2015-04-29 14:48:46', null, null);
INSERT INTO `department` VALUES ('40283a834d046a10014d0473779c0001', '40283a834d03c6ff014d03ef56cf0002', 'cd001001', '市场1部', '2015-04-29 17:13:05', 'enabled', '', '2015-04-29 17:13:05', 'xhf', '2015-04-29 17:13:05', null, null);

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `ID` varchar(40) NOT NULL COMMENT '主键',
  `NAME` varchar(30) DEFAULT NULL COMMENT '角色名称',
  `CODE` varchar(30) NOT NULL COMMENT '角色编码',
  `REMARK` varchar(1000) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(30) NOT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `UPDATE_USER` varchar(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `AK_Key_2` (`CODE`),
  KEY `Index_1` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of role
-- ----------------------------

-- ----------------------------
-- Table structure for `role_source`
-- ----------------------------
DROP TABLE IF EXISTS `role_source`;
CREATE TABLE `role_source` (
  `ID` varchar(40) NOT NULL COMMENT '主键',
  `ROLE_ID` varchar(40) DEFAULT NULL COMMENT '角色ID',
  `SOURCE_ID` varchar(40) DEFAULT NULL COMMENT '资源ID',
  `REMARK` varchar(1000) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(30) NOT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `UPDATE_USER` varchar(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`),
  KEY `Index_1` (`ID`),
  KEY `FK2E659404D1ED699` (`SOURCE_ID`),
  KEY `FK2E6594047337D879` (`ROLE_ID`),
  CONSTRAINT `FK_Reference_3` FOREIGN KEY (`SOURCE_ID`) REFERENCES `source` (`ID`),
  CONSTRAINT `FK_Reference_4` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='角色资源表';

-- ----------------------------
-- Records of role_source
-- ----------------------------

-- ----------------------------
-- Table structure for `source`
-- ----------------------------
DROP TABLE IF EXISTS `source`;
CREATE TABLE `source` (
  `ID` varchar(40) NOT NULL COMMENT '主键',
  `P_ID` varchar(40) DEFAULT NULL COMMENT '上级资源ID',
  `NAME` varchar(50) DEFAULT NULL COMMENT '资源名称',
  `TAG` varchar(50) NOT NULL COMMENT '资源标识',
  `URL` varchar(50) DEFAULT NULL COMMENT '资源URL',
  `SORT` int(11) DEFAULT NULL COMMENT '排序',
  `REMARK` varchar(1000) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(30) NOT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `UPDATE_USER` varchar(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`),
  UNIQUE KEY `AK_Key_2` (`TAG`),
  KEY `Index_1` (`ID`),
  KEY `FKCA90681B728EE224` (`P_ID`),
  CONSTRAINT `FKCA90681B728EE224` FOREIGN KEY (`P_ID`) REFERENCES `source` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='资源表';

-- ----------------------------
-- Records of source
-- ----------------------------

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` varchar(40) NOT NULL DEFAULT 'uuid()',
  `name` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('0000909b-65ae-11e6-aa16-00ffaff13132', '用户孤傲苍狼', null);
INSERT INTO `t_user` VALUES ('00939352-62b0-11e6-9578-00ffaff13132', '孤傲苍狼124s', '备注');
INSERT INTO `t_user` VALUES ('40283a834c72923b014c72ad75660001', '问', '萨法');
INSERT INTO `t_user` VALUES ('40283a834c77a20d014c7810fa150002', '艾丝凡', '艾丝凡');
INSERT INTO `t_user` VALUES ('40283a834c77a20d014c781199440003', '问', '艾丝凡');
INSERT INTO `t_user` VALUES ('40283a834c77a20d014c7811c80c0005', '发顺丰萨芬', '阿萨德发 ');
INSERT INTO `t_user` VALUES ('40283a834c77a20d014c7811e42f0006', '阿萨德发色奋斗', '艾丝凡啊');
INSERT INTO `t_user` VALUES ('968e023e-651a-11e6-aa16-00ffaff13132', '用户孤傲苍狼', null);

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `ID` varchar(40) NOT NULL DEFAULT 'uuid()' COMMENT '主键',
  `DEPT_ID` varchar(40) DEFAULT NULL COMMENT '部门ID',
  `LOGIN_NAME` varchar(30) NOT NULL COMMENT '登陆用户名',
  `PASSWORD` varchar(30) NOT NULL COMMENT '密码',
  `NAME` varchar(30) NOT NULL COMMENT '姓名',
  `SEX` varchar(5) DEFAULT NULL COMMENT '性别',
  `TEL` varchar(15) DEFAULT NULL COMMENT '电话',
  `EMAIL` varchar(30) DEFAULT NULL COMMENT '邮箱',
  `MOBILE` varchar(15) DEFAULT NULL COMMENT '手机',
  `QQ` varchar(15) DEFAULT NULL COMMENT 'QQ',
  `AGE` int(3) DEFAULT NULL COMMENT '年龄',
  `STATUS` varchar(10) NOT NULL COMMENT '状态',
  `REMARK` varchar(1000) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(30) NOT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `UPDATE_USER` varchar(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`),
  KEY `Index_1` (`ID`),
  KEY `FK36EBCBF345FBE6` (`DEPT_ID`),
  CONSTRAINT `FK36EBCBF345FBE6` FOREIGN KEY (`DEPT_ID`) REFERENCES `department` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('40283a834d03c6ff014d03d1a95c0001', '40283a834d03c6ff014d03ef56cf0002', 'admin', '123456', '管理员', null, '', null, '17712345678', null, null, 'enabled', '', '2016-08-16 14:27:17', 'xhf', '2015-04-29 14:16:34', 'xhf');

-- ----------------------------
-- Table structure for `user_role`
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `ID` varchar(40) NOT NULL COMMENT '主键',
  `UESR_ID` varchar(40) DEFAULT NULL COMMENT '用户ID',
  `ROLE_ID` varchar(40) DEFAULT NULL COMMENT '角色ID',
  `REMARK` varchar(1000) DEFAULT NULL COMMENT '备注',
  `CREATE_TIME` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `CREATE_USER` varchar(30) NOT NULL COMMENT '创建人',
  `UPDATE_TIME` timestamp NOT NULL DEFAULT '0000-00-00 00:00:00' COMMENT '修改时间',
  `UPDATE_USER` varchar(30) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`ID`),
  KEY `Index_1` (`ID`),
  KEY `FK143BF46A7337D879` (`ROLE_ID`),
  KEY `FK143BF46A1440CB5` (`UESR_ID`),
  CONSTRAINT `FK143BF46A1440CB5` FOREIGN KEY (`UESR_ID`) REFERENCES `user` (`ID`),
  CONSTRAINT `FK143BF46A7337D879` FOREIGN KEY (`ROLE_ID`) REFERENCES `role` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='用户角色表';

-- ----------------------------
-- Records of user_role
-- ----------------------------
