/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : tenxenqo

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-11-07 18:33:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(16) NOT NULL COMMENT '主键',
  `title` varchar(255) DEFAULT NULL COMMENT '用户ID',
  `users` text COMMENT '好友',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group
-- ----------------------------

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user` int(16) DEFAULT NULL COMMENT '发送人',
  `to_user` int(16) DEFAULT NULL COMMENT '接收人',
  `to_group` int(16) DEFAULT NULL COMMENT '接收群',
  `type` tinyint(1) DEFAULT NULL COMMENT '消息类型',
  `content` text COMMENT '内容',
  `is_read` tinyint(1) DEFAULT NULL COMMENT '是否已读',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=281 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(50) DEFAULT NULL COMMENT '密码',
  `nickname` varchar(20) DEFAULT NULL COMMENT '昵称',
  `signature` varchar(255) DEFAULT NULL COMMENT '签名',
  `mobile` int(11) DEFAULT NULL COMMENT '手机号',
  `address` varchar(255) DEFAULT NULL COMMENT '地址',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `online` int(1) DEFAULT NULL COMMENT '是否在线',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', null, null, null, null, '1', '2016-11-01 13:45:46', '2016-11-07 18:21:54', '0');
INSERT INTO `user` VALUES ('2', 'xiaodi', '123456', null, null, null, null, '1', '2016-11-01 13:45:45', '2016-11-02 12:50:29', '0');
INSERT INTO `user` VALUES ('3', 'hahaha', '123456', '我大阿金', null, null, null, '1', null, '2016-11-02 18:01:58', '0');
INSERT INTO `user` VALUES ('4', 'douyuTV', '357953710', null, null, null, null, '1', null, '2016-11-02 17:28:54', '0');
INSERT INTO `user` VALUES ('5', 'wtt', '123456', null, null, null, null, '1', null, '2016-11-04 10:20:39', '0');

-- ----------------------------
-- Table structure for user_relation
-- ----------------------------
DROP TABLE IF EXISTS `user_relation`;
CREATE TABLE `user_relation` (
  `id` int(16) NOT NULL COMMENT '主键',
  `user_id` int(16) DEFAULT NULL COMMENT '用户ID',
  `friends` text COMMENT '好友',
  `groups` text COMMENT '群组',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_relation
-- ----------------------------
