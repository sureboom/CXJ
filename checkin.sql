/*
 Navicat Premium Data Transfer

 Source Server         : one
 Source Server Type    : MySQL
 Source Server Version : 80023
 Source Host           : localhost:3306
 Source Schema         : demodemo

 Target Server Type    : MySQL
 Target Server Version : 80023
 File Encoding         : 65001

 Date: 20/12/2024 20:05:07
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for apply
-- ----------------------------
DROP TABLE IF EXISTS `apply`;
CREATE TABLE `apply`  (
  `apply_id` bigint NOT NULL AUTO_INCREMENT,
  `start_time` datetime NULL DEFAULT NULL,
  `during_time` int NULL DEFAULT NULL,
  `reason` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  `emp_id` bigint NOT NULL,
  PRIMARY KEY (`apply_id`, `emp_id`) USING BTREE,
  INDEX `emp_id`(`emp_id` ASC) USING BTREE,
  CONSTRAINT `apply_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employer` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 34 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of apply
-- ----------------------------
INSERT INTO `apply` VALUES (30, '2024-11-21 16:00:00', 1, '1', 5, 2, 1);
INSERT INTO `apply` VALUES (31, '2024-11-28 16:00:00', 1, '1', 1, 0, 4);
INSERT INTO `apply` VALUES (32, '2024-12-09 16:00:00', 1, '1', 7, 2, 4);
INSERT INTO `apply` VALUES (33, '2024-12-11 16:00:00', 1, '1', 1, 2, 1);
INSERT INTO `apply` VALUES (34, '2024-12-11 16:00:00', 20, '111', 1, 0, 1);
INSERT INTO `apply` VALUES (37, '2024-12-20 16:00:00', 10, '10', 0, 2, 17);
INSERT INTO `apply` VALUES (38, '2024-12-20 16:00:00', 10, '10', 7, 2, 17);
INSERT INTO `apply` VALUES (39, '2024-12-27 16:00:00', 1, '1', 1, 2, 17);
INSERT INTO `apply` VALUES (40, '2024-12-20 16:00:00', 1, '1', 6, 2, 17);
INSERT INTO `apply` VALUES (41, '2024-12-18 16:00:00', 10, '12', 5, 2, 17);

-- ----------------------------
-- Table structure for apply_record
-- ----------------------------
DROP TABLE IF EXISTS `apply_record`;
CREATE TABLE `apply_record`  (
  `apply_record_id` bigint NOT NULL AUTO_INCREMENT,
  `emp_id` bigint NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL,
  `total_time` int NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL,
  PRIMARY KEY (`apply_record_id`) USING BTREE,
  INDEX `emp_id`(`emp_id` ASC) USING BTREE,
  CONSTRAINT `apply_record_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employer` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 23 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of apply_record
-- ----------------------------
INSERT INTO `apply_record` VALUES (1, 1, 1, 1, 0);
INSERT INTO `apply_record` VALUES (2, 1, 4, 2, 2);
INSERT INTO `apply_record` VALUES (3, 1, 2, 2, 2);
INSERT INTO `apply_record` VALUES (4, 1, 5, 2, 2);
INSERT INTO `apply_record` VALUES (5, 1, 1, 5, 2);
INSERT INTO `apply_record` VALUES (6, 1, 1, 3, 2);
INSERT INTO `apply_record` VALUES (7, 1, 5, 2, 2);
INSERT INTO `apply_record` VALUES (8, 2, 4, 12, 2);
INSERT INTO `apply_record` VALUES (15, 1, 5, 1, 2);
INSERT INTO `apply_record` VALUES (16, 1, 5, 1, 2);
INSERT INTO `apply_record` VALUES (17, 1, 0, 1, 2);
INSERT INTO `apply_record` VALUES (18, 1, 5, 1, 2);
INSERT INTO `apply_record` VALUES (19, 1, 0, 1, 2);
INSERT INTO `apply_record` VALUES (20, 1, 5, 1, 2);
INSERT INTO `apply_record` VALUES (21, 4, 7, 1, 2);
INSERT INTO `apply_record` VALUES (22, 1, 1, 1, 2);
INSERT INTO `apply_record` VALUES (23, 1, 1, 20, 2);
INSERT INTO `apply_record` VALUES (24, 17, 0, 10, 2);
INSERT INTO `apply_record` VALUES (25, 17, 7, 10, 2);
INSERT INTO `apply_record` VALUES (26, 17, 1, 1, 2);
INSERT INTO `apply_record` VALUES (27, 17, 6, 1, 2);
INSERT INTO `apply_record` VALUES (28, 17, 5, 10, 2);

-- ----------------------------
-- Table structure for check_record
-- ----------------------------
DROP TABLE IF EXISTS `check_record`;
CREATE TABLE `check_record`  (
  `sign_id` bigint NOT NULL AUTO_INCREMENT,
  `time` datetime NULL DEFAULT NULL,
  `type` int NULL DEFAULT NULL COMMENT '0：上班打卡\n1：下班打卡\n2：外出打卡\n3：返回打卡',
  `emp_id` bigint NULL DEFAULT NULL,
  PRIMARY KEY (`sign_id`) USING BTREE,
  INDEX `emp_id`(`emp_id` ASC) USING BTREE,
  CONSTRAINT `check_record_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employer` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 253 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of check_record
-- ----------------------------
INSERT INTO `check_record` VALUES (226, '2024-12-01 14:59:30', 0, 1);
INSERT INTO `check_record` VALUES (227, '2024-12-01 15:01:18', 1, 1);
INSERT INTO `check_record` VALUES (228, '2024-12-01 15:01:27', 0, 1);
INSERT INTO `check_record` VALUES (229, '2024-12-01 15:10:26', 0, 4);
INSERT INTO `check_record` VALUES (230, '2024-12-01 15:10:28', 0, 4);
INSERT INTO `check_record` VALUES (231, '2024-12-01 15:10:29', 3, 4);
INSERT INTO `check_record` VALUES (232, '2024-12-01 15:10:30', 3, 4);
INSERT INTO `check_record` VALUES (233, '2024-12-01 15:10:40', 3, 2);
INSERT INTO `check_record` VALUES (234, '2024-12-01 20:20:50', 1, 1);
INSERT INTO `check_record` VALUES (235, '2024-12-01 20:21:03', 0, 1);
INSERT INTO `check_record` VALUES (236, '2024-12-01 20:21:05', 1, 1);
INSERT INTO `check_record` VALUES (237, '2024-12-01 20:21:06', 3, 1);
INSERT INTO `check_record` VALUES (238, '2024-12-01 20:29:20', 1, 1);
INSERT INTO `check_record` VALUES (239, '2024-12-01 20:29:30', 0, 1);
INSERT INTO `check_record` VALUES (240, '2024-12-02 00:37:56', 1, 2);
INSERT INTO `check_record` VALUES (241, '2024-12-02 00:40:15', 1, 3);
INSERT INTO `check_record` VALUES (242, '2024-12-02 00:40:15', 0, 3);
INSERT INTO `check_record` VALUES (243, '2024-12-02 00:40:16', 1, 3);
INSERT INTO `check_record` VALUES (244, '2024-12-02 00:40:17', 3, 3);
INSERT INTO `check_record` VALUES (245, '2024-12-02 00:40:18', 2, 3);
INSERT INTO `check_record` VALUES (246, '2024-12-02 19:50:04', 1, 1);
INSERT INTO `check_record` VALUES (247, '2024-12-02 19:50:04', 0, 1);
INSERT INTO `check_record` VALUES (248, '2024-12-03 17:19:27', 0, 3);
INSERT INTO `check_record` VALUES (249, '2024-12-03 17:30:19', 0, 11);
INSERT INTO `check_record` VALUES (250, '2024-12-03 17:30:20', 1, 11);
INSERT INTO `check_record` VALUES (251, '2024-12-03 17:30:22', 3, 11);
INSERT INTO `check_record` VALUES (252, '2024-12-05 15:28:45', 1, 1);
INSERT INTO `check_record` VALUES (253, '2024-12-12 17:10:39', 0, 1);

-- ----------------------------
-- Table structure for emp_state
-- ----------------------------
DROP TABLE IF EXISTS `emp_state`;
CREATE TABLE `emp_state`  (
  `state_id` bigint NOT NULL AUTO_INCREMENT,
  `emp_id` bigint NULL DEFAULT NULL,
  `state` int NULL DEFAULT NULL COMMENT '0：未在班\n1：在班',
  PRIMARY KEY (`state_id`) USING BTREE,
  INDEX `emp_id`(`emp_id` ASC) USING BTREE,
  CONSTRAINT `emp_state_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employer` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of emp_state
-- ----------------------------
INSERT INTO `emp_state` VALUES (1, 1, 1);
INSERT INTO `emp_state` VALUES (2, 2, 0);
INSERT INTO `emp_state` VALUES (3, 3, 1);
INSERT INTO `emp_state` VALUES (4, 4, 2);
INSERT INTO `emp_state` VALUES (5, 5, 0);
INSERT INTO `emp_state` VALUES (6, 6, 0);
INSERT INTO `emp_state` VALUES (7, 7, 0);
INSERT INTO `emp_state` VALUES (10, 11, 1);
INSERT INTO `emp_state` VALUES (11, 12, 0);
INSERT INTO `emp_state` VALUES (12, 13, 0);
INSERT INTO `emp_state` VALUES (13, 14, 0);
INSERT INTO `emp_state` VALUES (16, 17, 0);

-- ----------------------------
-- Table structure for employer
-- ----------------------------
DROP TABLE IF EXISTS `employer`;
CREATE TABLE `employer`  (
  `emp_id` bigint NOT NULL AUTO_INCREMENT,
  `account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `gender` int NULL DEFAULT NULL COMMENT '0：女\n1：男',
  `phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `salt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `create_time` datetime NULL DEFAULT NULL,
  `role` int NULL DEFAULT NULL,
  `avatar` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`emp_id`) USING BTREE,
  INDEX `emp_id`(`emp_id` ASC) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 16 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employer
-- ----------------------------
INSERT INTO `employer` VALUES (1, '001', '六十页', 1, '123123', '8pT3Amerl/xx4SdKbBuqEQdVSTk56XpFOKKJx1O10pw=', 'Wh6syKpW+OY=', '123@qq.com', '2022-04-06 16:32:38', 1, NULL);
INSERT INTO `employer` VALUES (2, '002', 'dxs', 1, '1231231', 'FLl04KxzooVsPjx9AMjg+3P3M/SrqbECW18UT9zrOTk=', 'x4mG3unXg5Q=', '123@163.com', '2022-04-05 20:33:18', 2, NULL);
INSERT INTO `employer` VALUES (3, '003', 'xxs', 0, '123123', '123123', NULL, '123@11.com', '2022-04-10 20:20:53', 3, NULL);
INSERT INTO `employer` VALUES (4, '004', '六世叶', 1, '123123', '123456', NULL, '321@qq.com', '2022-04-10 20:52:42', 4, NULL);
INSERT INTO `employer` VALUES (5, '004', '刘硕', 1, '123', '123456', NULL, '111@qq.com', '2021-03-22 09:15:58', 2, NULL);
INSERT INTO `employer` VALUES (6, '006', 'demo', 0, '123123123', '13123', NULL, '12313@123.com', '2022-04-12 00:00:00', 2, NULL);
INSERT INTO `employer` VALUES (7, '007', 'test', 0, '1231313', '132', NULL, '12313@qq.com', '2022-04-12 00:00:00', 2, NULL);
INSERT INTO `employer` VALUES (8, '008', '8号', 1, '123456', '123456', NULL, '1232@qq.com', '2024-12-05 11:04:41', 1, NULL);
INSERT INTO `employer` VALUES (11, '000', '122', 1, '1', '1', NULL, '1', '2024-12-03 00:00:00', 0, NULL);
INSERT INTO `employer` VALUES (12, '2002', 'demo', 0, '1', '123456', NULL, '123', '2024-12-05 10:40:45', 0, NULL);
INSERT INTO `employer` VALUES (13, '20000', 'lius', 1, '1111', '123456', NULL, '1@qq.com', '2024-11-29 10:40:49', 5, NULL);
INSERT INTO `employer` VALUES (14, '123123', '1223', 1, '123123123', '123123', NULL, '123', '2024-12-05 00:00:00', 3, NULL);
INSERT INTO `employer` VALUES (17, '121', '厕所', 1, '123', '+3ZHJjIQYbYE9H99+by349b//SEYjtzihiB3l0gWcgM=', 'q9pGKhFa7FA=', '123456', '2024-12-20 08:00:00', 0, NULL);

-- ----------------------------
-- Table structure for left_vacation
-- ----------------------------
DROP TABLE IF EXISTS `left_vacation`;
CREATE TABLE `left_vacation`  (
  `left_id` bigint NOT NULL AUTO_INCREMENT,
  `emp_id` bigint NULL DEFAULT NULL,
  `left_year` int NULL DEFAULT NULL,
  `left_hun_jia` int NULL DEFAULT NULL,
  `left_chan_jian` int NULL DEFAULT NULL,
  `left_chan_jia` int NULL DEFAULT NULL,
  `left_bu_ru` int NULL DEFAULT NULL,
  `left_pei_chan` int NULL DEFAULT NULL,
  `total` int NULL DEFAULT NULL,
  PRIMARY KEY (`left_id`) USING BTREE,
  INDEX `emp_id`(`emp_id` ASC) USING BTREE,
  CONSTRAINT `left_vacation_ibfk_1` FOREIGN KEY (`emp_id`) REFERENCES `employer` (`emp_id`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB AUTO_INCREMENT = 14 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of left_vacation
-- ----------------------------
INSERT INTO `left_vacation` VALUES (1, 1, 279, 1, 3, 0, 8, 5, 296);
INSERT INTO `left_vacation` VALUES (2, 2, 7, 3, 3, 20, 30, 5, 68);
INSERT INTO `left_vacation` VALUES (3, 3, 7, 3, 3, 8, 30, 5, 56);
INSERT INTO `left_vacation` VALUES (4, 4, 7, 3, 3, 20, 30, 5, 68);
INSERT INTO `left_vacation` VALUES (5, 5, 7, 3, 3, 20, 30, 5, 68);
INSERT INTO `left_vacation` VALUES (6, 6, 7, 3, 3, 20, 30, 5, 68);
INSERT INTO `left_vacation` VALUES (7, 7, 7, 3, 3, 20, 30, 5, 68);
INSERT INTO `left_vacation` VALUES (10, 11, 7, 3, 3, 20, 30, 5, 68);
INSERT INTO `left_vacation` VALUES (11, 12, 7, 3, 3, 20, 30, 5, 68);
INSERT INTO `left_vacation` VALUES (12, 13, 7, 3, 3, 20, 30, 5, 68);
INSERT INTO `left_vacation` VALUES (13, 14, 7, 3, 3, 20, 30, 5, 68);
INSERT INTO `left_vacation` VALUES (16, 17, 6, 3, 3, 20, 20, 4, 56);

SET FOREIGN_KEY_CHECKS = 1;
