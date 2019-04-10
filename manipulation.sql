/*
 Navicat Premium Data Transfer

 Source Server         : hyx
 Source Server Type    : MySQL
 Source Server Version : 50725
 Source Host           : 119.23.10.167:3306
 Source Schema         : manipulation

 Target Server Type    : MySQL
 Target Server Version : 50725
 File Encoding         : 65001

 Date: 03/04/2019 11:15:41
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for members
-- ----------------------------
DROP TABLE IF EXISTS `members`;
CREATE TABLE `members`  (
  `members_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '会员id ',
  `members_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员姓名',
  `pinyin_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拼音码',
  `members_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `tuina_id` int(11) NULL DEFAULT NULL COMMENT '推拿类型id',
  `tuina_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推拿类型',
  `surplus_number` int(11) NULL DEFAULT NULL COMMENT '剩余次数',
  PRIMARY KEY (`members_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45590 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of members
-- ----------------------------
INSERT INTO `members` VALUES (45579, '胡亚星', 'hyx', '13839564023', 2, '近视治疗', 10);
INSERT INTO `members` VALUES (45580, '王冰', 'wb', '15936578573', 1, '小儿推拿', 10);
INSERT INTO `members` VALUES (45581, '白瑞娜', 'brn', '18939056827', 2, '近视治疗', 10);
INSERT INTO `members` VALUES (45586, '李紫沐', 'lzm', '13583523535', 2, '近视治疗', 10);
INSERT INTO `members` VALUES (45587, '李紫沐', 'lzm', '13836895685', 1, '小儿推拿', 10);
INSERT INTO `members` VALUES (45588, '李子木', 'lzm', '13353565665', 2, '近视治疗', 10);

-- ----------------------------
-- Table structure for record
-- ----------------------------
DROP TABLE IF EXISTS `record`;
CREATE TABLE `record`  (
  `record_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '记录id',
  `members_id` int(11) NULL DEFAULT NULL COMMENT '会员id',
  `members_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '会员名字',
  `record_time` datetime(0) NULL DEFAULT NULL COMMENT '记录时间',
  `staff_id` int(11) NULL DEFAULT NULL COMMENT '店员id',
  `staff_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '店员名',
  `tuina_type` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推拿种类',
  PRIMARY KEY (`record_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 51 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of record
-- ----------------------------
INSERT INTO `record` VALUES (1, 1, '胡亚星', '2019-03-25 12:51:34', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (3, 1, '胡亚星', '2019-03-25 12:52:04', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (4, 1, '胡亚星', '2019-03-25 12:52:06', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (5, 1, '胡亚星', '2019-03-25 12:52:08', 1, '李四', '小儿推拿');
INSERT INTO `record` VALUES (6, 1, '胡亚星', '2019-03-25 12:52:09', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (7, 1, '胡亚星', '2019-03-25 12:52:11', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (8, 1, '胡亚星', '2019-03-25 12:52:17', 1, '李四', '小儿推拿');
INSERT INTO `record` VALUES (9, 1, '胡亚星', '2019-03-27 12:52:18', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (10, 1, '胡亚星', '2019-03-25 00:00:00', 1, '张三', '近视治疗');
INSERT INTO `record` VALUES (11, 1, '胡亚星', '2019-03-25 12:52:18', 1, '李四', '小儿推拿');
INSERT INTO `record` VALUES (36, 3, '白瑞娜', '2019-03-28 00:26:09', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (37, 45564, '王冰', '2019-03-28 00:26:41', 1, '李四', '小儿推拿');
INSERT INTO `record` VALUES (38, 3, '白瑞娜', '2019-03-28 00:26:58', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (39, 3, '白瑞娜', '2019-03-28 00:51:02', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (40, 45577, '白瑞娜', '2019-03-28 05:25:44', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (41, 45577, '白瑞娜', '2019-03-28 06:45:32', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (42, 45577, '白瑞娜', '2019-03-26 23:59:59', 1, '张三', '近视治疗');
INSERT INTO `record` VALUES (43, 45577, '白瑞娜', '2019-03-28 06:51:13', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (44, 45577, '白瑞娜', '2019-03-28 07:02:17', 1, '张三', '小儿推拿');
INSERT INTO `record` VALUES (45, 45577, '白瑞娜', '2019-03-28 07:49:46', 5, '白瑞娜', '小儿推拿');
INSERT INTO `record` VALUES (46, 45579, '胡亚星', '2019-03-28 08:13:57', 2, '李四', '小儿推拿');
INSERT INTO `record` VALUES (48, 45577, '白瑞娜', '2019-03-29 01:20:22', 6, '白瑞娜', '近视治疗');
INSERT INTO `record` VALUES (49, 45577, '白瑞娜', '2019-03-29 05:44:25', 8, '李四', '近视治疗');

-- ----------------------------
-- Table structure for staff
-- ----------------------------
DROP TABLE IF EXISTS `staff`;
CREATE TABLE `staff`  (
  `staff_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '员工id',
  `staff_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `pinyin_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '拼音码',
  `staff_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工手机号',
  PRIMARY KEY (`staff_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 10 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of staff
-- ----------------------------
INSERT INTO `staff` VALUES (5, '白瑞娜', 'brn', '18939065549');
INSERT INTO `staff` VALUES (7, '张三', 'zs', '13856947510');
INSERT INTO `staff` VALUES (8, '李四', 'li', '188396544754');
INSERT INTO `staff` VALUES (9, '武凯焱', 'wky', '159930261451');

-- ----------------------------
-- Table structure for tuina_type
-- ----------------------------
DROP TABLE IF EXISTS `tuina_type`;
CREATE TABLE `tuina_type`  (
  `tuina_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '推拿种类id',
  `tuina_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '推拿种类',
  PRIMARY KEY (`tuina_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of tuina_type
-- ----------------------------
INSERT INTO `tuina_type` VALUES (1, '小儿推拿');
INSERT INTO `tuina_type` VALUES (2, '近视治疗');

SET FOREIGN_KEY_CHECKS = 1;
