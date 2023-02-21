/*
 Navicat Premium Data Transfer

 Source Server         : 175.24.177.40
 Source Server Type    : MySQL
 Source Server Version : 80032 (8.0.32)
 Source Host           : 175.24.177.40:3306
 Source Schema         : bilibili

 Target Server Type    : MySQL
 Target Server Version : 80032 (8.0.32)
 File Encoding         : 65001

 Date: 28/01/2023 20:58:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_following_group
-- ----------------------------
DROP TABLE IF EXISTS `t_following_group`;
CREATE TABLE `t_following_group` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `name` varchar(50) DEFAULT NULL COMMENT '关注分组名称',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `type` varchar(5) DEFAULT NULL COMMENT '关注分组类型：0特别关注，1悄悄关注，2默认关注，3用户自定义关注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户关注分组表';

-- ----------------------------
-- Records of t_following_group
-- ----------------------------
BEGIN;
INSERT INTO `t_following_group` (`id`, `user_id`, `name`, `update_time`, `create_time`, `type`) VALUES (1, NULL, '特别关注', '2023-01-23 05:07:55', '2023-01-23 05:07:55', '0');
INSERT INTO `t_following_group` (`id`, `user_id`, `name`, `update_time`, `create_time`, `type`) VALUES (2, NULL, '悄悄关注', '2023-01-23 05:08:11', '2023-01-23 05:08:11', '1');
INSERT INTO `t_following_group` (`id`, `user_id`, `name`, `update_time`, `create_time`, `type`) VALUES (3, NULL, '默认关注', '2023-01-23 05:08:57', '2023-01-23 05:08:57', '2');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
