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

 Date: 28/01/2023 21:00:02
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_video_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_video_collection`;
CREATE TABLE `t_video_collection` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `video_id` bigint DEFAULT NULL COMMENT '视频投稿id',
  `user_id` bigint DEFAULT NULL COMMENT '用户id',
  `group_id` bigint DEFAULT NULL COMMENT '收藏分组',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频收藏表';

-- ----------------------------
-- Records of t_video_collection
-- ----------------------------
BEGIN;
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
