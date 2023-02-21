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

 Date: 28/01/2023 20:59:30
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user_info
-- ----------------------------
DROP TABLE IF EXISTS `t_user_info`;
CREATE TABLE `t_user_info` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id（关联）',
  `nick` varchar(100) DEFAULT NULL COMMENT '昵称',
  `avatar` varchar(1024) DEFAULT NULL COMMENT '头像',
  `sign` text COMMENT '签名',
  `gender` varchar(2) DEFAULT NULL COMMENT '性别：0男，1女，2未知',
  `birth` varchar(20) DEFAULT NULL COMMENT '生日',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户基本信息表';

-- ----------------------------
-- Records of t_user_info
-- ----------------------------
BEGIN;
INSERT INTO `t_user_info` (`id`, `user_id`, `nick`, `avatar`, `sign`, `gender`, `birth`, `create_time`, `update_time`) VALUES (1, 1, '零零落落test', NULL, NULL, '1', '2000-01-01', '2023-01-23 03:41:54', '2023-01-23 04:19:05');
INSERT INTO `t_user_info` (`id`, `user_id`, `nick`, `avatar`, `sign`, `gender`, `birth`, `create_time`, `update_time`) VALUES (2, 2, '萌新', NULL, NULL, '2', '2000-01-01', '2023-01-24 06:12:39', '2023-01-24 06:12:39');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
