/*
Navicat MySQL Data Transfer

Source Server         : 192.168.254.246-mysql5.7
Source Server Version : 50717
Source Host           : 192.168.254.246:3308
Source Database       : webhooks

Target Server Type    : MYSQL
Target Server Version : 50717
File Encoding         : 65001

Date: 2017-11-02 08:56:50
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
) ENGINE=InnoDB AUTO_INCREMENT=1000029 DEFAULT CHARSET=utf8 COMMENT='gitlab user table';

-- ----------------------------
-- Table structure for sys_commit
-- ----------------------------
DROP TABLE IF EXISTS `sys_commit`;
CREATE TABLE `sys_commit` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `checkout_sha` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `commit_time` datetime DEFAULT NULL,
  `gitlab_url` varchar(255) NOT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3002704 DEFAULT CHARSET=utf8 COMMENT='gitlab commit';

-- ----------------------------
-- Table structure for sys_hook_push
-- ----------------------------
DROP TABLE IF EXISTS `sys_hook_push`;
CREATE TABLE `sys_hook_push` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `object_kind` varchar(255) DEFAULT NULL,
  `event_name` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `author_id` bigint(20) DEFAULT NULL,
  `type` int(11) DEFAULT NULL,
  `ref` varchar(255) DEFAULT NULL,
  `checkout_sha` varchar(255) DEFAULT NULL,
  `before` varchar(255) DEFAULT NULL,
  `after` varchar(255) DEFAULT NULL,
  `project_id` bigint(20) DEFAULT NULL,
  `total_commits_count` bigint(10) DEFAULT NULL,
  `event_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4001347 DEFAULT CHARSET=utf8 COMMENT='gitlab sys hook push';

-- ----------------------------
-- Table structure for sys_outgoing_group
-- ----------------------------
DROP TABLE IF EXISTS `sys_outgoing_group`;
CREATE TABLE `sys_outgoing_group` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(255) DEFAULT NULL,
  `gitlab_group_name` varchar(200) DEFAULT NULL COMMENT '标题',
  `im_type` varchar(10) DEFAULT NULL COMMENT '标题',
  `im_url` varchar(500) DEFAULT NULL COMMENT '标题',
  `description` varchar(255) DEFAULT NULL,
  `event` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2000020 DEFAULT CHARSET=utf8 COMMENT='outgoing group to IM';

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
) ENGINE=InnoDB AUTO_INCREMENT=5000070 DEFAULT CHARSET=utf8 COMMENT='gitlab project';

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
) ENGINE=InnoDB AUTO_INCREMENT=6002698 DEFAULT CHARSET=utf8 COMMENT='gitlab r push commit';
