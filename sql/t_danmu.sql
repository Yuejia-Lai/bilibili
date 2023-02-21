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

 Date: 28/01/2023 20:58:36
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_danmu
-- ----------------------------
DROP TABLE IF EXISTS `t_danmu`;
CREATE TABLE `t_danmu` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `video_id` bigint DEFAULT NULL COMMENT '视频id',
  `content` text COMMENT '弹幕内容',
  `danmu_time` varchar(50) DEFAULT NULL COMMENT '弹幕出现时间',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='弹幕表';

-- ----------------------------
-- Records of t_danmu
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
