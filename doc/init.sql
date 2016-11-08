/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : tenxenqo

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-11-08 18:06:28
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for group
-- ----------------------------
DROP TABLE IF EXISTS `group`;
CREATE TABLE `group` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `owner` int(16) DEFAULT NULL COMMENT '群主ID',
  `title` varchar(255) DEFAULT NULL COMMENT '群标题',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `pic_path` varchar(255) DEFAULT NULL COMMENT '图片',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group
-- ----------------------------
INSERT INTO `group` VALUES ('1', '1', '主群', null, null, '1', '2016-11-08 10:24:45', '2016-11-08 10:24:55');

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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

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
  `pic_path` varchar(255) DEFAULT NULL COMMENT '图片地址',
  `online` int(1) DEFAULT NULL COMMENT '是否在线',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', null, null, null, null, null, '0', '1', '2016-11-01 13:45:46', '2016-11-08 17:38:58');
INSERT INTO `user` VALUES ('2', 'xiaodi', '123456', null, null, null, null, null, '0', '1', '2016-11-01 13:45:45', '2016-11-02 12:50:29');
INSERT INTO `user` VALUES ('3', 'hahaha', '123456', '我大阿金', null, null, null, null, '0', '1', null, '2016-11-02 18:01:58');
INSERT INTO `user` VALUES ('4', 'douyuTV', '357953710', null, null, null, null, null, '0', '1', null, '2016-11-02 17:28:54');
INSERT INTO `user` VALUES ('5', 'wtt', '123456', null, null, null, null, null, '0', '1', null, '2016-11-04 10:20:39');
INSERT INTO `user` VALUES ('6', 'qwerty', '123456', null, null, null, null, null, '0', '1', null, null);
INSERT INTO `user` VALUES ('7', 'asdfg', '123456', null, null, null, null, null, '0', '1', null, null);
INSERT INTO `user` VALUES ('8', 'zxcvb', '123456', null, null, null, null, null, '0', '1', null, null);
INSERT INTO `user` VALUES ('9', 'qwer1234', '123456', null, null, null, null, null, '0', '1', null, null);

-- ----------------------------
-- Table structure for user_friend
-- ----------------------------
DROP TABLE IF EXISTS `user_friend`;
CREATE TABLE `user_friend` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(16) DEFAULT NULL COMMENT '用户ID',
  `friend_id` int(16) DEFAULT NULL COMMENT '好友',
  `relation` tinyint(1) DEFAULT NULL COMMENT '关系',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_friend
-- ----------------------------
INSERT INTO `user_friend` VALUES ('1', '1', '2', '1', '1', '2016-11-08 10:24:11', '2016-11-08 10:24:14');
INSERT INTO `user_friend` VALUES ('2', '1', '3', '1', '1', '2016-11-08 10:24:11', '2016-11-08 15:43:16');
INSERT INTO `user_friend` VALUES ('3', '1', '4', '1', '1', '2016-11-08 10:24:11', '2016-11-08 15:43:16');
INSERT INTO `user_friend` VALUES ('4', '1', '5', '1', '1', '2016-11-08 10:24:11', '2016-11-08 15:43:17');
INSERT INTO `user_friend` VALUES ('5', '1', '6', '1', '1', '2016-11-08 10:24:11', '2016-11-08 15:43:18');
INSERT INTO `user_friend` VALUES ('6', '1', '7', '1', '1', '2016-11-08 10:24:11', '2016-11-08 15:43:18');
INSERT INTO `user_friend` VALUES ('7', '1', '8', '1', '1', '2016-11-08 10:24:11', '2016-11-08 15:43:19');
INSERT INTO `user_friend` VALUES ('8', '1', '9', '1', '1', '2016-11-08 10:24:11', '2016-11-08 15:43:20');

-- ----------------------------
-- Table structure for user_group
-- ----------------------------
DROP TABLE IF EXISTS `user_group`;
CREATE TABLE `user_group` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_id` int(16) DEFAULT NULL COMMENT '用户ID',
  `group_id` int(16) DEFAULT NULL COMMENT '群组ID',
  `relation` tinyint(1) DEFAULT NULL COMMENT '关系',
  `status` tinyint(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_group
-- ----------------------------
INSERT INTO `user_group` VALUES ('1', '1', '1', '1', '1', '2016-11-08 10:25:25', '2016-11-08 10:25:38');
