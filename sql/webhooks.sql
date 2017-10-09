/*
Navicat MySQL Data Transfer

Source Server         : 192.168.254.246-mysql5.7
Source Server Version : 50717
Source Host           : 192.168.254.246:3308
Source Database       : webhooks

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-09-30 16:20:56
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_author
-- ----------------------------
DROP TABLE IF EXISTS `sys_author`;
CREATE TABLE `sys_author` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gitlab_user_id` bigint(20) DEFAULT NULL,
  `user_name` varchar(50) DEFAULT NULL COMMENT 'English name',
  `name` varchar(50) DEFAULT NULL COMMENT 'real name',
  `email` varchar(255) DEFAULT NULL,
  `user_avatar` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `unique_email` (`email`)
) ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=utf8 COMMENT='gitlab user table';

-- ----------------------------
-- Records of sys_author
-- ----------------------------
INSERT INTO `sys_author` VALUES ('1000000', '1', 'root', 'Administrator', 'admin@example.com', 'http://www.gravatar.com/avatar/e64c7d89f26bd1972efa854d13d7dd61?s=80&d=identicon');

-- ----------------------------
-- Table structure for sys_commit
-- ----------------------------
DROP TABLE IF EXISTS `sys_commit`;
CREATE TABLE `sys_commit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `checkout_sha` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `commit_time` date DEFAULT NULL,
  `gitlab_url` varchar(255) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3000001 DEFAULT CHARSET=utf8 COMMENT='gitlab commit';

-- ----------------------------
-- Records of sys_commit
-- ----------------------------

-- ----------------------------
-- Table structure for sys_hook_push
-- ----------------------------
DROP TABLE IF EXISTS `sys_hook_push`;
CREATE TABLE `sys_hook_push` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `author_id` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `checkout_sha` varchar(255) DEFAULT NULL,
  `before` varchar(255) DEFAULT NULL,
  `after` varchar(255) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4000001 DEFAULT CHARSET=utf8 COMMENT='gitlab sys hook push';

-- ----------------------------
-- Records of sys_hook_push
-- ----------------------------

-- ----------------------------
-- Table structure for sys_outgoing_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_outgoing_group`;
CREATE TABLE `sys_outgoing_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `gitlab_group_name` varchar(200) DEFAULT NULL COMMENT '标题',
  `im_type` varchar(10) DEFAULT NULL COMMENT '标题',
  `im_url` varchar(500) DEFAULT NULL COMMENT '标题',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2000002 DEFAULT CHARSET=utf8 COMMENT='outgoing group to IM';

-- ----------------------------
-- Records of sys_outgoing_group
-- ----------------------------
INSERT INTO `sys_outgoing_group` VALUES ('2000001', 'root-test', 'dingtalk', 'http://sdfsdfs.com');

-- ----------------------------
-- Table structure for sys_project
-- ----------------------------
DROP TABLE IF EXISTS `sys_project`;
CREATE TABLE `sys_project` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL,
  `web_url` varchar(255) DEFAULT NULL,
  `avatar_url` varchar(255) DEFAULT NULL,
  `git_ssh_url` varchar(255) DEFAULT NULL,
  `git_http_url` varchar(255) DEFAULT NULL,
  `group_name` varchar(100) DEFAULT NULL,
  `path_with_namespace` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5000001 DEFAULT CHARSET=utf8 COMMENT='gitlab project';

-- ----------------------------
-- Records of sys_project
-- ----------------------------

-- ----------------------------
-- Table structure for sys_r_push_commit
-- ----------------------------
DROP TABLE IF EXISTS `sys_r_push_commit`;
CREATE TABLE `sys_r_push_commit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'relation table about push and commit',
  `push_id` bigint(20) NOT NULL,
  `commit_id` bigint(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `INDEX_PUSH` (`push_id`),
  KEY `INDEX_COMMIT` (`commit_id`),
  CONSTRAINT `INDEX_COMMIT` FOREIGN KEY (`commit_id`) REFERENCES `sys_commit` (`id`),
  CONSTRAINT `INDEX_PUSH` FOREIGN KEY (`push_id`) REFERENCES `sys_hook_push` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6000001 DEFAULT CHARSET=utf8 COMMENT='gitlab r push commit';

-- ----------------------------
-- Records of sys_r_push_commit
-- ----------------------------
