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

 Date: 28/01/2023 20:57:47
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_auth_element_operation
-- ----------------------------
DROP TABLE IF EXISTS `t_auth_element_operation`;
CREATE TABLE `t_auth_element_operation` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `element_name` varchar(255) DEFAULT NULL COMMENT '页面元素名称',
  `element_code` varchar(50) DEFAULT NULL COMMENT '页面元素唯一编码',
  `operation_type` varchar(5) DEFAULT NULL COMMENT '操作类型：0可点击，1可见',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='权限控制--页面元素操作表';

-- ----------------------------
-- Records of t_auth_element_operation
-- ----------------------------
BEGIN;
INSERT INTO `t_auth_element_operation` (`id`, `element_name`, `element_code`, `operation_type`, `create_time`, `update_time`) VALUES (1, '视频投稿按钮', 'postVideoButton', '0', '2023-01-24 09:37:06', '2023-01-24 09:37:06');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
