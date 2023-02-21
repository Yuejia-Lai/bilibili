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

 Date: 28/01/2023 20:59:50
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for t_video
-- ----------------------------
DROP TABLE IF EXISTS `t_video`;
CREATE TABLE `t_video` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint NOT NULL COMMENT '用户id',
  `url` varchar(500) NOT NULL COMMENT '视频链接',
  `thumbnail` varchar(500) NOT NULL COMMENT '封面链接',
  `title` varchar(255) NOT NULL COMMENT '视频标题',
  `type` varchar(5) NOT NULL COMMENT '视频类型：0原创，1转载',
  `duration` varchar(255) NOT NULL COMMENT '视频时长',
  `area` varchar(255) NOT NULL COMMENT '所在分区:0鬼畜，1音乐，2电影',
  `description` text COMMENT '视频简介',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='视频投稿记录表';

-- ----------------------------
-- Records of t_video
-- ----------------------------
BEGIN;
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (13, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频2', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:54:46', '2023-01-28 06:54:46');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (14, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频1', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:54:53', '2023-01-28 06:54:53');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (15, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频3', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:54:57', '2023-01-28 06:54:57');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (16, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频5', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:55:01', '2023-01-28 06:55:01');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (17, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频8', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:55:04', '2023-01-28 06:55:04');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (18, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频9', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:55:08', '2023-01-28 06:55:08');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (19, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频10', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:55:13', '2023-01-28 06:55:13');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (20, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频11', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:55:16', '2023-01-28 06:55:16');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (21, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频1', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:59:45', '2023-01-28 06:59:45');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (22, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频2', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:59:49', '2023-01-28 06:59:49');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (23, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频3', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:59:53', '2023-01-28 06:59:53');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (24, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频4', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:59:56', '2023-01-28 06:59:56');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (25, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频5', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 06:59:59', '2023-01-28 06:59:59');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (26, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频6', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 07:00:03', '2023-01-28 07:00:03');
INSERT INTO `t_video` (`id`, `user_id`, `url`, `thumbnail`, `title`, `type`, `duration`, `area`, `description`, `create_time`, `update_time`) VALUES (27, 2, 'xxxxxxxxx.mp4', 'http://example.com', '示例视频7', '0', '199', '0', '示例描述。。。。。。。。。。', '2023-01-28 07:00:06', '2023-01-28 07:00:06');
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
