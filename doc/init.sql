/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : tenxenqo

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-11-23 12:03:10
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
) ENGINE=InnoDB AUTO_INCREMENT=131 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of message
-- ----------------------------
INSERT INTO `message` VALUES ('1', '1', '2', null, '1', 'asdasd', '0', '1', '2016-11-10 11:58:46', null);
INSERT INTO `message` VALUES ('2', '1', '2', null, '1', 'asdasdasd', '0', '1', '2016-11-10 11:58:48', null);
INSERT INTO `message` VALUES ('3', '3', '2', null, '1', 'sdasd', '0', '1', '2016-11-10 11:58:49', '2016-11-18 09:56:21');
INSERT INTO `message` VALUES ('4', '4', '2', null, '1', 'csasdf', '0', '1', '2016-11-10 11:58:53', '2016-11-18 09:56:22');
INSERT INTO `message` VALUES ('5', '1', '2', null, '1', 'sdasdasd', '0', '1', '2016-11-10 11:59:17', null);
INSERT INTO `message` VALUES ('6', '1', '2', null, '1', 'asdasdasdasdasdasdasd', '0', '1', '2016-11-10 11:59:21', null);
INSERT INTO `message` VALUES ('7', '1', '4', null, '1', 'asdasdasd', '0', '1', '2016-11-15 17:09:51', null);
INSERT INTO `message` VALUES ('8', '1', '4', null, '1', 'dasdasdasd', '0', '1', '2016-11-15 17:10:12', null);
INSERT INTO `message` VALUES ('9', '1', '4', null, '1', 'zxczxczxc', '0', '1', '2016-11-15 17:10:13', null);
INSERT INTO `message` VALUES ('10', '1', '3', null, '1', 'dasdasdasdd', '0', '1', '2016-11-15 17:10:21', null);
INSERT INTO `message` VALUES ('11', '1', '5', null, '1', 'asdasdasd', '0', '1', '2016-11-15 17:18:42', null);
INSERT INTO `message` VALUES ('12', '1', '3', null, '1', 'sdfsdfsdf', '0', '1', '2016-11-15 17:18:57', null);
INSERT INTO `message` VALUES ('13', '1', '2', null, '1', 'sdsadsa', '0', '1', '2016-11-15 17:19:06', null);
INSERT INTO `message` VALUES ('14', '1', '8', null, '1', 'dasdasd', '0', '1', '2016-11-15 17:19:16', null);
INSERT INTO `message` VALUES ('15', '1', '3', null, '1', 'asdasdasd', '0', '1', '2016-11-15 17:59:46', null);
INSERT INTO `message` VALUES ('16', '1', '3', null, '1', 'asdasdasd', '0', '1', '2016-11-15 17:59:55', null);
INSERT INTO `message` VALUES ('17', '1', '3', null, '1', 'asdasdasd', '0', '1', '2016-11-15 18:00:15', null);
INSERT INTO `message` VALUES ('18', '1', '3', null, '1', 'asdasdasd', '0', '1', '2016-11-15 18:00:34', null);
INSERT INTO `message` VALUES ('19', '1', '4', null, '1', 'sadsadasd', '0', '1', '2016-11-15 18:03:58', null);
INSERT INTO `message` VALUES ('20', '2', '4', null, '1', 'dsfsdfdsf', '1', '1', '2016-11-15 18:54:44', null);
INSERT INTO `message` VALUES ('21', '2', '4', null, '1', 'kdsfmklsdfsdf', '1', '1', '2016-11-15 18:54:59', null);
INSERT INTO `message` VALUES ('22', '4', '2', null, '1', '1111\n', '1', '1', '2016-11-15 18:55:18', null);
INSERT INTO `message` VALUES ('23', '2', '4', null, '1', 'dasdfsadasd\n', '1', '1', '2016-11-15 18:55:23', null);
INSERT INTO `message` VALUES ('24', '2', '4', null, '1', 'dsfsdfsd', '1', '1', '2016-11-15 18:55:39', null);
INSERT INTO `message` VALUES ('25', '2', '4', null, '1', '哈哈哈\n', '1', '1', '2016-11-15 18:55:42', null);
INSERT INTO `message` VALUES ('26', '1', '2', null, '1', 'asdasd\n', '1', '1', '2016-11-16 14:18:38', null);
INSERT INTO `message` VALUES ('27', '1', '2', null, '1', 'dsadasdasd', '1', '1', '2016-11-16 14:18:46', null);
INSERT INTO `message` VALUES ('28', '1', '2', null, '1', 'gsdfgsdgsdg', '1', '1', '2016-11-16 14:18:54', null);
INSERT INTO `message` VALUES ('29', '1', '2', null, '1', 'cvcxvxcvxc', '1', '1', '2016-11-16 14:19:01', null);
INSERT INTO `message` VALUES ('30', '1', '2', null, '1', 'vcxvxcvxcv', '1', '1', '2016-11-16 14:19:08', null);
INSERT INTO `message` VALUES ('31', '1', '2', null, '1', 'fdsfsdfsdf', '1', '1', '2016-11-16 14:19:22', null);
INSERT INTO `message` VALUES ('32', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-16 15:34:55', null);
INSERT INTO `message` VALUES ('33', '1', '2', null, '1', 'adasdasd', '1', '1', '2016-11-16 15:34:56', null);
INSERT INTO `message` VALUES ('34', '1', '2', null, '1', 'zxczxc', '1', '1', '2016-11-16 15:34:57', null);
INSERT INTO `message` VALUES ('35', '1', '2', null, '1', 'fasfasdasd', '1', '1', '2016-11-16 15:34:58', null);
INSERT INTO `message` VALUES ('36', '1', '2', null, '1', 'fsdfasdfdsf', '1', '1', '2016-11-16 15:35:03', null);
INSERT INTO `message` VALUES ('37', '1', '2', null, '1', 'xzczxczxczxc', '1', '1', '2016-11-16 15:35:05', null);
INSERT INTO `message` VALUES ('38', '1', '2', null, '1', 'dasdasd', '1', '1', '2016-11-16 15:35:06', null);
INSERT INTO `message` VALUES ('39', '1', '2', null, '1', 'zsdasdasd', '1', '1', '2016-11-16 15:35:41', null);
INSERT INTO `message` VALUES ('40', '1', '2', null, '1', 'dasdasd', '1', '1', '2016-11-16 15:37:14', null);
INSERT INTO `message` VALUES ('41', '1', '2', null, '1', 'fsdfsdfsdf', '1', '1', '2016-11-16 15:37:21', null);
INSERT INTO `message` VALUES ('42', '1', '2', null, '1', 'sadasdasd', '1', '1', '2016-11-16 15:37:22', null);
INSERT INTO `message` VALUES ('43', '1', '2', null, '1', 'aaaaa', '1', '1', '2016-11-16 15:37:29', null);
INSERT INTO `message` VALUES ('44', '1', '4', null, '1', 'asdasdasd', '0', '1', '2016-11-16 15:58:44', null);
INSERT INTO `message` VALUES ('45', '1', '2', null, '1', 'asdasdasdsad', '1', '1', '2016-11-16 15:58:57', null);
INSERT INTO `message` VALUES ('46', '1', '2', null, '1', 'asdasdasdsad', '1', '1', '2016-11-16 15:59:01', null);
INSERT INTO `message` VALUES ('47', '1', '2', null, '1', 'vsdvdsvsdv', '1', '1', '2016-11-16 15:59:02', null);
INSERT INTO `message` VALUES ('48', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-16 15:59:45', null);
INSERT INTO `message` VALUES ('49', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-16 16:02:49', null);
INSERT INTO `message` VALUES ('50', '1', '2', null, '1', 'asdsadasd', '1', '1', '2016-11-16 16:02:50', null);
INSERT INTO `message` VALUES ('51', '1', '2', null, '1', 'zxczxczxc', '1', '1', '2016-11-16 16:02:51', null);
INSERT INTO `message` VALUES ('52', '1', '2', null, '1', 'adasdasd', '1', '1', '2016-11-16 17:08:13', null);
INSERT INTO `message` VALUES ('53', '1', '2', null, '1', 'sadasdasda', '1', '1', '2016-11-16 17:08:22', null);
INSERT INTO `message` VALUES ('54', '1', '2', null, '1', 'cascasdsad', '1', '1', '2016-11-16 17:08:25', null);
INSERT INTO `message` VALUES ('55', '1', '2', null, '1', 'asdasdas', '1', '1', '2016-11-17 11:13:30', null);
INSERT INTO `message` VALUES ('56', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 11:13:56', null);
INSERT INTO `message` VALUES ('57', '1', '2', null, '1', 'sadasdasd', '1', '1', '2016-11-17 11:14:04', null);
INSERT INTO `message` VALUES ('58', '1', '2', null, '1', 'fdsfdsfsd', '1', '1', '2016-11-17 11:14:57', null);
INSERT INTO `message` VALUES ('59', '1', '2', null, '1', 'asdsadasd', '1', '1', '2016-11-17 11:19:01', null);
INSERT INTO `message` VALUES ('60', '1', '2', null, '1', 'sadasdasd', '1', '1', '2016-11-17 11:19:05', null);
INSERT INTO `message` VALUES ('61', '1', '2', null, '1', 'sdasdsad', '1', '1', '2016-11-17 11:19:06', null);
INSERT INTO `message` VALUES ('62', '1', '2', null, '1', 'asdsadasd', '1', '1', '2016-11-17 11:19:18', null);
INSERT INTO `message` VALUES ('63', '2', '4', null, '1', 'adasdasd', '0', '1', '2016-11-17 14:49:11', null);
INSERT INTO `message` VALUES ('64', '1', '3', null, '1', 'asdasdasd', '0', '1', '2016-11-17 14:49:23', null);
INSERT INTO `message` VALUES ('65', '1', '2', null, '1', 'asdasdsadas', '1', '1', '2016-11-17 14:49:36', null);
INSERT INTO `message` VALUES ('66', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 14:50:42', null);
INSERT INTO `message` VALUES ('67', '1', '2', null, '1', 'sadasdasdsdasd', '1', '1', '2016-11-17 14:50:57', null);
INSERT INTO `message` VALUES ('68', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 14:52:13', null);
INSERT INTO `message` VALUES ('69', '1', '2', null, '1', 'dasdasdasda', '1', '1', '2016-11-17 14:52:50', null);
INSERT INTO `message` VALUES ('70', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 14:57:40', null);
INSERT INTO `message` VALUES ('71', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 15:06:44', null);
INSERT INTO `message` VALUES ('72', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 15:08:36', null);
INSERT INTO `message` VALUES ('73', '1', '3', null, '2', 'emotion (3).gif', '0', '1', '2016-11-17 15:22:37', null);
INSERT INTO `message` VALUES ('74', '1', '3', null, '1', 'dsfsdfdsf', '0', '1', '2016-11-17 15:22:40', null);
INSERT INTO `message` VALUES ('75', '1', '3', null, '1', 'wedfwedwed', '0', '1', '2016-11-17 15:22:56', null);
INSERT INTO `message` VALUES ('76', '1', '3', null, '2', 'emotion (5).jpg', '0', '1', '2016-11-17 15:23:43', null);
INSERT INTO `message` VALUES ('77', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 16:16:43', null);
INSERT INTO `message` VALUES ('78', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 16:18:26', null);
INSERT INTO `message` VALUES ('79', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 16:18:44', null);
INSERT INTO `message` VALUES ('80', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 16:20:42', null);
INSERT INTO `message` VALUES ('81', '1', '2', null, '1', 'asdadasdasd', '1', '1', '2016-11-17 16:28:32', null);
INSERT INTO `message` VALUES ('82', '1', '2', null, '1', 'asdsadas', '1', '1', '2016-11-17 16:29:03', null);
INSERT INTO `message` VALUES ('83', '1', '2', null, '1', 'sadasdasd', '1', '1', '2016-11-17 16:29:43', null);
INSERT INTO `message` VALUES ('84', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 16:31:20', null);
INSERT INTO `message` VALUES ('85', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 16:32:41', null);
INSERT INTO `message` VALUES ('86', '1', '2', null, '1', 'asdasdasdsa', '1', '1', '2016-11-17 16:32:58', null);
INSERT INTO `message` VALUES ('87', '1', '2', null, '1', 'sdasdasd', '1', '1', '2016-11-17 16:33:22', null);
INSERT INTO `message` VALUES ('88', '1', '2', null, '1', 'dasdasdsad', '1', '1', '2016-11-17 16:33:50', null);
INSERT INTO `message` VALUES ('89', '1', '2', null, '1', 'sadasdasd', '1', '1', '2016-11-17 16:34:48', null);
INSERT INTO `message` VALUES ('90', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 16:38:22', null);
INSERT INTO `message` VALUES ('91', '1', '2', null, '1', 'adasdsad', '1', '1', '2016-11-17 16:40:41', null);
INSERT INTO `message` VALUES ('92', '1', '2', null, '1', 'asdasdasdas', '1', '1', '2016-11-17 16:42:45', null);
INSERT INTO `message` VALUES ('93', '1', '2', null, '1', 'sadasdasdsad', '1', '1', '2016-11-17 16:43:02', null);
INSERT INTO `message` VALUES ('94', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 16:45:57', null);
INSERT INTO `message` VALUES ('95', '1', '2', null, '1', 'sadasdasd', '1', '1', '2016-11-17 16:46:39', null);
INSERT INTO `message` VALUES ('96', '1', '2', null, '1', 'dasdasdasd', '1', '1', '2016-11-17 16:46:47', null);
INSERT INTO `message` VALUES ('97', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 16:46:58', null);
INSERT INTO `message` VALUES ('98', '1', '2', null, '1', 'sadasdasd', '1', '1', '2016-11-17 16:49:54', null);
INSERT INTO `message` VALUES ('99', '1', '2', null, '1', 'dsadasdasd', '1', '1', '2016-11-17 16:50:43', null);
INSERT INTO `message` VALUES ('100', '1', '2', null, '1', 'dsfsdfsdf', '1', '1', '2016-11-17 16:50:55', null);
INSERT INTO `message` VALUES ('101', '1', '2', null, '1', 'dfgfdgdfg', '1', '1', '2016-11-17 16:51:12', null);
INSERT INTO `message` VALUES ('102', '1', '2', null, '1', 'fdsdfsdf', '1', '1', '2016-11-17 16:51:36', null);
INSERT INTO `message` VALUES ('103', '1', '2', null, '1', 'asdasdasd', '1', '1', '2016-11-17 17:03:57', null);
INSERT INTO `message` VALUES ('104', '1', '2', null, '1', 'sdfsfsdfsdf', '1', '1', '2016-11-17 17:04:28', null);
INSERT INTO `message` VALUES ('105', '1', '2', null, '1', 'asdsadasdasdasd', '1', '1', '2016-11-17 17:04:39', null);
INSERT INTO `message` VALUES ('106', '2', '1', null, '1', 'asdasdasdasd', '1', '1', '2016-11-17 17:20:54', null);
INSERT INTO `message` VALUES ('107', '2', '1', null, '1', 'cascascascasc', '1', '1', '2016-11-17 17:21:25', null);
INSERT INTO `message` VALUES ('108', '2', '1', null, '1', 'asdasdasd', '1', '1', '2016-11-17 17:25:09', null);
INSERT INTO `message` VALUES ('109', '2', '1', null, '1', 'asdasdasdas', '1', '1', '2016-11-17 17:31:02', '2016-11-23 12:01:25');
INSERT INTO `message` VALUES ('110', '5', '1', null, '1', 'dasasdasd', '1', '1', '2016-11-17 17:32:06', '2016-11-23 12:01:25');
INSERT INTO `message` VALUES ('111', '5', '1', null, '1', 'asdasdasd', '1', '1', '2016-11-17 17:34:24', '2016-11-23 12:01:25');
INSERT INTO `message` VALUES ('112', '4', '1', null, '1', 'asdasdasdasd', '1', '1', '2016-11-17 17:34:44', '2016-11-23 12:01:25');
INSERT INTO `message` VALUES ('113', '2', '1', null, '1', 'sdasdasdasd', '1', '1', '2016-11-17 17:35:26', '2016-11-23 12:01:25');
INSERT INTO `message` VALUES ('114', '3', '1', null, '1', 'sdasdasdasd', '1', '1', '2016-11-17 17:35:49', '2016-11-23 12:01:25');
INSERT INTO `message` VALUES ('115', '4', '1', null, '1', 'fgsdfsdfsd', '1', '1', '2016-11-17 17:36:47', '2016-11-23 12:01:25');
INSERT INTO `message` VALUES ('116', '3', '1', null, '1', 'fdgdfgdfg', '1', '1', '2016-11-17 17:37:01', '2016-11-23 12:01:25');
INSERT INTO `message` VALUES ('117', '2', '1', null, '1', 'dfgdfgdfg', '1', '1', '2016-11-17 17:37:20', '2016-11-23 12:01:25');
INSERT INTO `message` VALUES ('118', '2', '1', null, '1', 'fdgdfgdfg', '1', '1', '2016-11-17 17:37:33', null);
INSERT INTO `message` VALUES ('119', '2', '1', null, '1', 'fghfghfgh', '1', '1', '2016-11-17 17:37:38', null);
INSERT INTO `message` VALUES ('120', '2', '1', null, '1', 'asdasdasd', '1', '1', '2016-11-17 17:39:21', null);
INSERT INTO `message` VALUES ('121', '2', '1', null, '1', 'vccasdsadasd', '1', '1', '2016-11-17 17:39:23', null);
INSERT INTO `message` VALUES ('122', '2', '1', null, '1', 'vsdavsvasdfsdf', '1', '1', '2016-11-17 17:39:25', null);
INSERT INTO `message` VALUES ('123', '2', '1', null, '1', 'jyuyjtyjtyr', '1', '1', '2016-11-17 17:39:34', null);
INSERT INTO `message` VALUES ('124', '2', '1', null, '1', '2412341234123', '1', '1', '2016-11-17 17:39:36', null);
INSERT INTO `message` VALUES ('125', '2', '1', null, '1', 'sadasdasdasdx1111', '1', '1', '2016-11-17 17:39:51', null);
INSERT INTO `message` VALUES ('126', '1', '3', null, '2', 'emotion (3).gif', '0', '1', '2016-11-18 15:53:34', null);
INSERT INTO `message` VALUES ('127', '1', '6', null, '1', 'cdfasdasdczxcz', '0', '1', '2016-11-23 11:43:52', null);
INSERT INTO `message` VALUES ('128', '1', '6', null, '1', 'dasdasdas', '0', '1', '2016-11-23 11:43:53', null);
INSERT INTO `message` VALUES ('129', '1', '6', null, '1', 'fweqwdqwdqwd', '0', '1', '2016-11-23 11:43:54', null);
INSERT INTO `message` VALUES ('130', '1', '6', null, '2', 'emotion (2).jpg', '0', '1', '2016-11-23 11:44:00', null);

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(16) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) DEFAULT NULL COMMENT '用户名',
  `pwd` varchar(20) DEFAULT NULL COMMENT '密码',
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', 'admin', '打算发', null, null, null, null, '0', '1', '2016-11-01 13:45:46', '2016-11-23 11:50:03');
INSERT INTO `user` VALUES ('2', 'xiaodi', '123456', '按时打算', null, null, null, null, '0', '1', '2016-11-01 13:45:45', '2016-11-17 17:40:05');
INSERT INTO `user` VALUES ('3', 'hahaha', '123456', '我大阿金', null, null, null, null, '0', '1', null, '2016-11-02 18:01:58');
INSERT INTO `user` VALUES ('4', 'douyuTV', '357953710', '阿萨德', null, null, null, null, '0', '1', null, '2016-11-15 18:55:50');
INSERT INTO `user` VALUES ('5', 'wtt', '123456', '最新从中选出', null, null, null, null, '0', '1', null, '2016-11-10 11:01:02');
INSERT INTO `user` VALUES ('6', 'qwerty', '123456', '按时打', null, null, null, null, '0', '1', null, '2016-11-10 11:01:04');
INSERT INTO `user` VALUES ('7', 'asdfg', '123456', '擦拭', null, null, null, null, '0', '1', null, '2016-11-10 11:01:05');
INSERT INTO `user` VALUES ('8', 'zxcvb', '123456', '阿打算', null, null, null, null, '0', '1', null, '2016-11-10 11:01:06');
INSERT INTO `user` VALUES ('9', 'qwer1234', '123456', '气温气温', null, null, null, null, '0', '1', null, '2016-11-10 11:01:07');
INSERT INTO `user` VALUES ('10', 'zxczxc', 'zxczxc', null, null, null, null, null, null, '1', null, null);

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
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

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
INSERT INTO `user_friend` VALUES ('9', '4', '2', '1', '1', '2016-11-15 18:53:56', '2016-11-15 18:53:59');
INSERT INTO `user_friend` VALUES ('10', '2', '4', '1', '1', '2016-11-15 18:54:06', '2016-11-15 18:54:08');
INSERT INTO `user_friend` VALUES ('11', '2', '1', '1', '1', '2016-11-17 16:37:39', '2016-11-17 16:37:42');

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
