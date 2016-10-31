/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : tenxenqo

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-10-31 16:13:19
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for message
-- ----------------------------
DROP TABLE IF EXISTS `message`;
CREATE TABLE `message` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user` int(16) DEFAULT NULL COMMENT '发送人',
  `toUser` int(16) DEFAULT NULL COMMENT '接收人',
  `content` text COMMENT '内容',
  `status` int(1) DEFAULT NULL COMMENT '状态',
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

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
  `create_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', null, null, null, null, '1', '2016-10-31 16:01:38', '2016-10-31 16:01:38');
INSERT INTO `user` VALUES ('2', 'xiaodi', '123456', null, null, null, null, '1', '2016-10-31 16:12:39', '2016-10-31 16:12:39');
