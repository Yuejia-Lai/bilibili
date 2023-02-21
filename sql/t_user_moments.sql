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

 Date: 28/01/2023 20:59:37
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_user_moments
-- ----------------------------
DROP TABLE IF EXISTS `t_user_moments`;
CREATE TABLE `t_user_moments` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `type` varchar(5) DEFAULT NULL COMMENT '动态类型：0视频，1直播，2动态专栏',
  `content_id` bigint DEFAULT NULL COMMENT '内容详情id',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户动态表';

-- ----------------------------
-- Records of t_user_moments
-- ----------------------------
BEGIN;
INSERT INTO `t_user_moments` (`id`, `user_id`, `type`, `content_id`, `create_time`, `update_time`) VALUES (11, 2, '0', 2, '2023-01-24 13:58:05', '2023-01-24 13:58:05');
INSERT INTO `t_user_moments` (`id`, `user_id`, `type`, `content_id`, `create_time`, `update_time`) VALUES (12, 2, '0', 2, '2023-01-24 13:58:47', '2023-01-24 13:58:47');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
