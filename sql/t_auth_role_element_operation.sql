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

 Date: 28/01/2023 20:58:13
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_auth_role_element_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_role_element_operation`;
CREATE TABLE `t_auth_role_element_operation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint DEFAULT NULL COMMENT '角色id',
  `element_operation_id` bigint DEFAULT NULL COMMENT '元素操作id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限控制--角色与元素操作关联表';

-- ----------------------------
-- Records of t_auth_role_element_operation
-- ----------------------------
BEGIN;
INSERT INTO `t_auth_role_element_operation` (`id`, `role_id`, `element_operation_id`, `create_time`) VALUES (1, 2, 1, '2023-01-24 09:38:22');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
