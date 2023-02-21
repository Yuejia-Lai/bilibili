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

 Date: 28/01/2023 20:58:57
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_refresh_token
-- ----------------------------
DROP TABLE IF EXISTS `t_refresh_token`;
CREATE TABLE `t_refresh_token` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `refresh_token` varchar(500) DEFAULT NULL COMMENT '刷新令牌',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='刷新令牌记录表';

-- ----------------------------
-- Records of t_refresh_token
-- ----------------------------
BEGIN;
INSERT INTO `t_refresh_token` (`id`, `user_id`, `refresh_token`, `create_time`) VALUES (2, 2, 'eyJraWQiOiIyIiwidHlwIjoiSldUIiwiYWxnIjoiUlMyNTYifQ.eyJpc3MiOiLnrb7lj5HogIUiLCJleHAiOjE2NzU0ODEyMTd9.A4Oi8_f-pOTZ6C1hJx6Md80GN6hAsEDGVzQoFKnWDz8LZNUOu_8r0mXt1bIotfjz9duMrTJGxBussp6ey1vBPPzFiK4JGL9bAIUDIH67eeBxS9s6xwU6pFNrHFGj3ks1HCGiFC44koDL0ddZL_YbFcQ1-sbjn_-fL_eNelCMeSo', '2023-01-28 03:26:57');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
