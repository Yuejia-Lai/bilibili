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

 Date: 28/01/2023 20:58:03
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_auth_role
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role`;
CREATE TABLE `t_auth_role` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(255) DEFAULT NULL COMMENT '角色名称',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `code` varchar(50) DEFAULT NULL COMMENT '唯一编码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限控制--角色表';

-- ----------------------------
-- Records of t_auth_role
-- ----------------------------
BEGIN;
INSERT INTO `t_auth_role` (`id`, `name`, `create_time`, `update_time`, `code`) VALUES (1, '等级0', '2023-01-24 09:34:56', '2023-01-24 09:34:56', 'Lv0');
INSERT INTO `t_auth_role` (`id`, `name`, `create_time`, `update_time`, `code`) VALUES (2, '等级1', '2023-01-24 09:35:08', '2023-01-24 09:35:08', 'Lv1');
INSERT INTO `t_auth_role` (`id`, `name`, `create_time`, `update_time`, `code`) VALUES (3, '等级2', '2023-01-24 09:35:19', '2023-01-24 09:35:19', 'Lv2');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
