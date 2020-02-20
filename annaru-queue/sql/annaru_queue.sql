/*
 Navicat Premium Data Transfer

 Source Server         : conn
 Source Server Type    : MySQL
 Source Server Version : 50713
 Source Host           : localhost:3306
 Source Schema         : annaru_queue

 Target Server Type    : MySQL
 Target Server Version : 50713
 File Encoding         : 65001

 Date: 10/01/2020 14:41:45
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for qrtz_blob_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_blob_triggers`;
CREATE TABLE `qrtz_blob_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `BLOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `SCHED_NAME`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_blob_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_calendars
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_calendars`;
CREATE TABLE `qrtz_calendars`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CALENDAR` blob NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_cron_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_cron_triggers`;
CREATE TABLE `qrtz_cron_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `CRON_EXPRESSION` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TIME_ZONE_ID` varchar(80) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_cron_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_cron_triggers
-- ----------------------------
INSERT INTO `qrtz_cron_triggers` VALUES ('annaru-queue-Scheduler', 'TASK_1', 'DEFAULT', '0/1 * * * * ?', 'Asia/Shanghai');

-- ----------------------------
-- Table structure for qrtz_fired_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_fired_triggers`;
CREATE TABLE `qrtz_fired_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `ENTRY_ID` varchar(95) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `FIRED_TIME` bigint(13) NOT NULL,
  `SCHED_TIME` bigint(13) NOT NULL,
  `PRIORITY` int(11) NOT NULL,
  `STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `ENTRY_ID`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TRIG_INST_NAME`(`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_FT_INST_JOB_REQ_RCVRY`(`SCHED_NAME`, `INSTANCE_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_FT_J_G`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_T_G`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_FT_TG`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_fired_triggers
-- ----------------------------
INSERT INTO `qrtz_fired_triggers` VALUES ('annaru-queue-Scheduler', 'sheen-Jiang15786338740171578633874336', 'TASK_1', 'DEFAULT', 'sheen-Jiang1578633874017', 1578634253127, 1578634254000, 5, 'ACQUIRED', NULL, NULL, '0', '0');

-- ----------------------------
-- Table structure for qrtz_job_details
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_job_details`;
CREATE TABLE `qrtz_job_details`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `JOB_CLASS_NAME` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_DURABLE` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_NONCONCURRENT` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `IS_UPDATE_DATA` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REQUESTS_RECOVERY` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_J_REQ_RECOVERY`(`SCHED_NAME`, `REQUESTS_RECOVERY`) USING BTREE,
  INDEX `IDX_QRTZ_J_GRP`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_job_details
-- ----------------------------
INSERT INTO `qrtz_job_details` VALUES ('annaru-queue-Scheduler', 'TASK_1', 'DEFAULT', NULL, 'com.annaru.queue.quartz.utils.ScheduleJob', '0', '1', '0', '0', 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002F636F6D2E616E6E6172752E71756575652E71756172747A2E6D6F64656C2E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016F8D9640F27874000D302F31202A202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740005746573743274000074000474657374737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for qrtz_locks
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_locks`;
CREATE TABLE `qrtz_locks`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LOCK_NAME` varchar(40) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `LOCK_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_locks
-- ----------------------------
INSERT INTO `qrtz_locks` VALUES ('annaru-cabinet-Scheduler', 'STATE_ACCESS');
INSERT INTO `qrtz_locks` VALUES ('annaru-cabinet-Scheduler', 'TRIGGER_ACCESS');

-- ----------------------------
-- Table structure for qrtz_paused_trigger_grps
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_paused_trigger_grps`;
CREATE TABLE `qrtz_paused_trigger_grps`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_scheduler_state
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_scheduler_state`;
CREATE TABLE `qrtz_scheduler_state`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `INSTANCE_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `LAST_CHECKIN_TIME` bigint(13) NOT NULL,
  `CHECKIN_INTERVAL` bigint(13) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `INSTANCE_NAME`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_scheduler_state
-- ----------------------------
INSERT INTO `qrtz_scheduler_state` VALUES ('annaru-cabinet-Scheduler', 'sheen-Jiang1578573400211', 1578573540964, 15000);
INSERT INTO `qrtz_scheduler_state` VALUES ('annaru-queue-Scheduler', 'sheen-Jiang1578633874017', 1578634239423, 15000);

-- ----------------------------
-- Table structure for qrtz_simple_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simple_triggers`;
CREATE TABLE `qrtz_simple_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `REPEAT_COUNT` bigint(7) NOT NULL,
  `REPEAT_INTERVAL` bigint(12) NOT NULL,
  `TIMES_TRIGGERED` bigint(10) NOT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simple_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_simprop_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_simprop_triggers`;
CREATE TABLE `qrtz_simprop_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `STR_PROP_1` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_2` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `STR_PROP_3` varchar(512) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `INT_PROP_1` int(11) NULL DEFAULT NULL,
  `INT_PROP_2` int(11) NULL DEFAULT NULL,
  `LONG_PROP_1` bigint(20) NULL DEFAULT NULL,
  `LONG_PROP_2` bigint(20) NULL DEFAULT NULL,
  `DEC_PROP_1` decimal(13, 4) NULL DEFAULT NULL,
  `DEC_PROP_2` decimal(13, 4) NULL DEFAULT NULL,
  `BOOL_PROP_1` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `BOOL_PROP_2` varchar(1) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  CONSTRAINT `qrtz_simprop_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) REFERENCES `qrtz_triggers` (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for qrtz_triggers
-- ----------------------------
DROP TABLE IF EXISTS `qrtz_triggers`;
CREATE TABLE `qrtz_triggers`  (
  `SCHED_NAME` varchar(120) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `JOB_GROUP` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `DESCRIPTION` varchar(250) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `NEXT_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PREV_FIRE_TIME` bigint(13) NULL DEFAULT NULL,
  `PRIORITY` int(11) NULL DEFAULT NULL,
  `TRIGGER_STATE` varchar(16) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `TRIGGER_TYPE` varchar(8) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `START_TIME` bigint(13) NOT NULL,
  `END_TIME` bigint(13) NULL DEFAULT NULL,
  `CALENDAR_NAME` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `MISFIRE_INSTR` smallint(2) NULL DEFAULT NULL,
  `JOB_DATA` blob NULL,
  PRIMARY KEY (`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_J`(`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_JG`(`SCHED_NAME`, `JOB_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_C`(`SCHED_NAME`, `CALENDAR_NAME`) USING BTREE,
  INDEX `IDX_QRTZ_T_G`(`SCHED_NAME`, `TRIGGER_GROUP`) USING BTREE,
  INDEX `IDX_QRTZ_T_STATE`(`SCHED_NAME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_STATE`(`SCHED_NAME`, `TRIGGER_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_N_G_STATE`(`SCHED_NAME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NEXT_FIRE_TIME`(`SCHED_NAME`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST`(`SCHED_NAME`, `TRIGGER_STATE`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_STATE`) USING BTREE,
  INDEX `IDX_QRTZ_T_NFT_ST_MISFIRE_GRP`(`SCHED_NAME`, `MISFIRE_INSTR`, `NEXT_FIRE_TIME`, `TRIGGER_GROUP`, `TRIGGER_STATE`) USING BTREE,
  CONSTRAINT `qrtz_triggers_ibfk_1` FOREIGN KEY (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) REFERENCES `qrtz_job_details` (`SCHED_NAME`, `JOB_NAME`, `JOB_GROUP`) ON DELETE RESTRICT ON UPDATE RESTRICT
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of qrtz_triggers
-- ----------------------------
INSERT INTO `qrtz_triggers` VALUES ('annaru-queue-Scheduler', 'TASK_1', 'DEFAULT', 'TASK_1', 'DEFAULT', NULL, 1578628432000, -1, 5, 'WAITING', 'CRON', 1578628432000, 0, NULL, 2, 0xACED0005737200156F72672E71756172747A2E4A6F62446174614D61709FB083E8BFA9B0CB020000787200266F72672E71756172747A2E7574696C732E537472696E674B65794469727479466C61674D61708208E8C3FBC55D280200015A0013616C6C6F77735472616E7369656E74446174617872001D6F72672E71756172747A2E7574696C732E4469727479466C61674D617013E62EAD28760ACE0200025A000564697274794C00036D617074000F4C6A6176612F7574696C2F4D61703B787001737200116A6176612E7574696C2E486173684D61700507DAC1C31660D103000246000A6C6F6164466163746F724900097468726573686F6C6478703F4000000000000C7708000000100000000174000D4A4F425F504152414D5F4B45597372002F636F6D2E616E6E6172752E71756575652E71756172747A2E6D6F64656C2E5363686564756C654A6F62456E7469747900000000000000010200084C00086265616E4E616D657400124C6A6176612F6C616E672F537472696E673B4C000A63726561746554696D657400104C6A6176612F7574696C2F446174653B4C000E63726F6E45787072657373696F6E71007E00094C00056A6F6249647400104C6A6176612F6C616E672F4C6F6E673B4C000A6D6574686F644E616D6571007E00094C0006706172616D7371007E00094C000672656D61726B71007E00094C00067374617475737400134C6A6176612F6C616E672F496E74656765723B7870740008746573745461736B7372000E6A6176612E7574696C2E44617465686A81014B597419030000787077080000016F8D9640807874000D302F31202A202A202A202A203F7372000E6A6176612E6C616E672E4C6F6E673B8BE490CC8F23DF0200014A000576616C7565787200106A6176612E6C616E672E4E756D62657286AC951D0B94E08B02000078700000000000000001740005746573743274000074000474657374737200116A6176612E6C616E672E496E746567657212E2A0A4F781873802000149000576616C75657871007E0013000000007800);

-- ----------------------------
-- Table structure for t_schedule_job
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_job`;
CREATE TABLE `t_schedule_job`  (
  `job_id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `cron_expression` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'cron表达式',
  `status` tinyint(4) NULL DEFAULT NULL COMMENT '任务状态  0：正常  1：暂停',
  `remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_schedule_job
-- ----------------------------
INSERT INTO `t_schedule_job` VALUES (1, 'testTask', 'test2', '', '0/1 * * * * ?', 1, 'test', '2020-01-10 11:53:52');

-- ----------------------------
-- Table structure for t_schedule_job_log
-- ----------------------------
DROP TABLE IF EXISTS `t_schedule_job_log`;
CREATE TABLE `t_schedule_job_log`  (
  `log_id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '任务日志id',
  `job_id` bigint(19) NOT NULL COMMENT '任务id',
  `bean_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT 'spring bean名称',
  `method_name` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '方法名',
  `params` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '参数',
  `status` tinyint(4) NOT NULL COMMENT '任务状态    0：成功    1：失败',
  `error` varchar(2000) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '失败信息',
  `times` int(11) NOT NULL COMMENT '耗时(单位：毫秒)',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`log_id`) USING BTREE,
  INDEX `job_id`(`job_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 3185 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '定时任务日志' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_schedule_job_log
-- ----------------------------

-- ----------------------------
-- Table structure for t_sys_dic
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_dic`;
CREATE TABLE `t_sys_dic`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `parent_id` bigint(19) NOT NULL COMMENT '父变量ID',
  `var_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变量代码',
  `var_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '变量名称',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录修改时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录创建者（用户）',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录最后修改者（用户）',
  `is_sync` tinyint(4) NULL DEFAULT NULL COMMENT '数据是否同步(0:是,1:否)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_dic_code`(`var_code`) USING BTREE,
  INDEX `index_dic_name`(`var_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '数据字典表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_file
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_file`;
CREATE TABLE `t_sys_file`  (
  `id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `table_id` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件类型(哪个表的附件)',
  `record_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件ID(哪个表的记录Id)',
  `attachment_group` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '表的记录Id下的附件分组的组名',
  `attachment_name` varchar(60) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件名称',
  `attachment_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '附件路径',
  `attachment_type` tinyint(4) NULL DEFAULT NULL COMMENT '附件类型(0-word,1-excel,2-pdf,3-jpg,png,4-其他)',
  `save_type` tinyint(4) NULL DEFAULT NULL COMMENT '存储类型（0：本地存储，1:fastdfs）',
  `create_time` datetime(0) NULL DEFAULT NULL COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '记录最后修改时间',
  `create_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录创建者(用户)',
  `update_user` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录最后修改者(用户)',
  `is_sync` tinyint(4) NULL DEFAULT NULL COMMENT '数据是否同步(0:是,1:否)',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `t_sys_file_table_id`(`table_id`) USING BTREE,
  INDEX `t_sys_file_rrecord_id`(`record_id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '附件表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for t_sys_log
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_log`;
CREATE TABLE `t_sys_log`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '登陆名',
  `role_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '角色名',
  `type` tinyint(4) NULL DEFAULT NULL COMMENT '日志类型（0:系统日志，1：操作日志）',
  `operation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户操作',
  `class_name` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '类名',
  `method` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求方法',
  `params` varchar(1024) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '请求参数',
  `time` bigint(19) NULL DEFAULT NULL COMMENT '执行时长',
  `client_ip` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '客户端ip',
  `create_time` datetime(0) NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6347 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统日志表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_log

-- ----------------------------
-- Table structure for t_sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_resource`;
CREATE TABLE `t_sys_resource`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `parent_id` bigint(19) NULL DEFAULT NULL COMMENT '父级资源ID',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '资源名称',
  `url` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源路径',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源介绍',
  `icon` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '资源图标',
  `seq` tinyint(4) NOT NULL DEFAULT 0 COMMENT '排序',
  `resource_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '资源类别(0：菜单，1：按钮)',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '状态(0：开，1：关）',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录最后修改时间',
  `create_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录创建用户',
  `update_user` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '记录最后修改用户',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 321 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_resource
-- ----------------------------
INSERT INTO `t_sys_resource` VALUES (1, 0, '系统管理', '', '系统管理', 'config', 3, 0, 0, '2019-08-01 15:16:55', '2019-08-01 15:16:55', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (11, 1, '菜单管理', 'sys/menu', '菜单管理', 'menu', 3, 1, 0, '2019-08-08 15:47:19', '2019-08-08 15:47:19', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (12, 1, '角色管理', 'sys/role', '角色管理', 'role', 2, 1, 0, '2018-12-17 19:11:24', '2018-12-17 19:11:25', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (13, 1, '用户管理', 'sys/user', '用户管理', 'admin', 1, 1, 0, '2018-12-17 19:11:13', '2018-12-17 19:11:14', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (111, 11, '列表', 'sys/menu/list', '资源列表', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (112, 11, '添加', 'sys/menu/save', '资源添加', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (113, 11, '编辑', 'sys/menu/update', '资源编辑', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (114, 11, '删除', 'sys/menu/delete', '资源删除', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (115, 11, '选择', 'sys/menu/select', '菜单选择', NULL, 4, 2, 0, '2018-12-14 16:44:29', '2018-12-14 16:44:32', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (116, 11, '信息', 'sys/menu/info', '菜单信息', NULL, 5, 2, 0, '2018-12-14 16:45:04', '2018-12-14 16:45:06', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (121, 12, '列表', 'sys/role/list', '角色列表', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (122, 12, '添加', 'sys/role/save', '角色添加', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (123, 12, '编辑', 'sys/role/update', '角色编辑', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (124, 12, '删除', 'sys/role/delete', '角色删除', '', 0, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (125, 12, '选择', 'sys/role/select', '角色选择', NULL, 5, 2, 0, '2018-12-14 13:52:39', '2018-12-14 13:52:42', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (126, 12, '信息', 'sys/role/info', '角色信息', NULL, 6, 2, 0, '2018-12-14 14:32:15', '2018-12-14 14:32:15', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (131, 13, '列表', 'sys/user/list', '用户列表', '', 1, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (132, 13, '添加', 'sys/user/save', '用户添加', '', 2, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (133, 13, '编辑', 'sys/user/update', '用户编辑', '', 3, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (134, 13, '删除', 'sys/user/delete', '用户删除', '', 4, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (135, 13, '信息', 'sys/user/info', '用户信息', NULL, 5, 2, 0, '2018-12-14 14:05:39', '2018-12-14 14:05:41', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (227, 1, '操作日志', 'sys/log', '操作日志', 'log', 29, 1, 0, '2018-12-15 19:36:23', '2018-12-15 19:36:23', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (234, 1, '字典管理', 'sys/dic', '字典管理', 'menu', 4, 1, 0, '2018-12-17 19:12:03', '2018-12-17 19:12:04', NULL, 'admin');
INSERT INTO `t_sys_resource` VALUES (235, 234, '数据字典列表', 'sys/dic/list', '数据字典列表', '', 0, 2, 0, '2018-12-13 19:37:23', '2018-12-13 19:37:23', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (236, 234, '添加', 'sys/dic/save', '数据字典添加', '', 1, 2, 0, '2018-12-13 19:37:03', '2018-12-13 19:37:03', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (237, 234, '编辑', 'sys/dic/update', '数据字典编辑', '', 2, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (238, 234, '删除', 'sys/dic/delete', '数据字典删除', '', 3, 2, 0, '2018-12-13 19:32:40', '2018-12-13 19:32:40', NULL, NULL);
INSERT INTO `t_sys_resource` VALUES (239, 1, '定时任务', 'sys/schedule', NULL, 'job', 5, 1, 0, '2018-12-15 19:10:38', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (240, 239, '列表', 'sys/schedule/list', NULL, '', 1, 2, 0, '2018-12-15 19:15:10', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (241, 239, '添加', 'sys/schedule/save', NULL, '', 2, 2, 0, '2018-12-15 19:16:20', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (242, 239, '编辑', 'sys/schedule/update', '定时任务编辑', '', 3, 2, 0, '2018-12-15 19:22:18', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (243, 239, '删除', 'sys/schedule/delete', '定时任务删除', '', 4, 2, 0, '2018-12-15 19:23:09', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (244, 239, '信息', 'sys/schedule/info', '定时任务信息', '', 5, 2, 0, '2018-12-15 19:23:33', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (245, 239, '暂停', 'sys/schedule/pause', '定时任务暂停', '', 6, 2, 0, '2018-12-15 19:25:39', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (246, 239, '恢复', 'sys/schedule/resume', '定时任务恢复', '', 7, 2, 0, '2018-12-15 19:26:19', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (247, 239, '立即执行', 'sys/schedule/run', '定时任务立即执行', '', 8, 2, 0, '2018-12-15 19:27:46', NULL, 'admin', NULL);
INSERT INTO `t_sys_resource` VALUES (248, 239, '日志', 'sys/schedule/log', '定时任务日志', '', 9, 2, 0, '2018-12-15 19:28:40', NULL, 'admin', NULL);

-- ----------------------------
-- Table structure for t_sys_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role`;
CREATE TABLE `t_sys_role`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '角色名',
  `seq` tinyint(2) NOT NULL DEFAULT 0 COMMENT '排序号',
  `description` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '简介',
  `status` tinyint(2) NOT NULL DEFAULT 0 COMMENT '状态(0：开启，1：关闭)',
  `create_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录创建时间',
  `update_time` datetime(0) NOT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录最后修改时间',
  `create_user_id` bigint(19) NULL DEFAULT NULL COMMENT '记录创建者ID',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 6 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role
-- ----------------------------
INSERT INTO `t_sys_role` VALUES (1, 'admin', 1, '超级管理员', 0, '2020-01-10 10:29:12', '2020-01-10 10:29:13', 1);
INSERT INTO `t_sys_role` VALUES (2, '应用管理员', 2, '应用管理员', 0, '2019-08-08 14:44:44', '2019-08-08 14:44:45', 1);
INSERT INTO `t_sys_role` VALUES (3, '测试人员', 3, '测试人员', 0, '2019-08-08 14:21:26', '2019-08-08 14:21:26', 1);
INSERT INTO `t_sys_role` VALUES (4, '运维人员', 4, '运维人员', 0, '2019-08-08 14:21:43', '2019-08-08 14:21:43', 1);
INSERT INTO `t_sys_role` VALUES (5, '运营人员', 5, '运营人员', 0, '2019-08-08 14:29:53', '2019-08-08 14:29:53', 1);

-- ----------------------------
-- Table structure for t_sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_role_resource`;
CREATE TABLE `t_sys_role_resource`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  `resource_id` bigint(19) NOT NULL COMMENT '资源id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_role_resource_ids`(`role_id`, `resource_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 89773 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '角色资源表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_role_resource
-- ----------------------------
INSERT INTO `t_sys_role_resource` VALUES (89729, 1, 1);
INSERT INTO `t_sys_role_resource` VALUES (89743, 1, 11);
INSERT INTO `t_sys_role_resource` VALUES (89736, 1, 12);
INSERT INTO `t_sys_role_resource` VALUES (89730, 1, 13);
INSERT INTO `t_sys_role_resource` VALUES (89744, 1, 111);
INSERT INTO `t_sys_role_resource` VALUES (89745, 1, 112);
INSERT INTO `t_sys_role_resource` VALUES (89746, 1, 113);
INSERT INTO `t_sys_role_resource` VALUES (89747, 1, 114);
INSERT INTO `t_sys_role_resource` VALUES (89748, 1, 115);
INSERT INTO `t_sys_role_resource` VALUES (89749, 1, 116);
INSERT INTO `t_sys_role_resource` VALUES (89737, 1, 121);
INSERT INTO `t_sys_role_resource` VALUES (89738, 1, 122);
INSERT INTO `t_sys_role_resource` VALUES (89739, 1, 123);
INSERT INTO `t_sys_role_resource` VALUES (89740, 1, 124);
INSERT INTO `t_sys_role_resource` VALUES (89741, 1, 125);
INSERT INTO `t_sys_role_resource` VALUES (89742, 1, 126);
INSERT INTO `t_sys_role_resource` VALUES (89731, 1, 131);
INSERT INTO `t_sys_role_resource` VALUES (89732, 1, 132);
INSERT INTO `t_sys_role_resource` VALUES (89733, 1, 133);
INSERT INTO `t_sys_role_resource` VALUES (89734, 1, 134);
INSERT INTO `t_sys_role_resource` VALUES (89735, 1, 135);
INSERT INTO `t_sys_role_resource` VALUES (89765, 1, 227);
INSERT INTO `t_sys_role_resource` VALUES (89750, 1, 234);
INSERT INTO `t_sys_role_resource` VALUES (89751, 1, 235);
INSERT INTO `t_sys_role_resource` VALUES (89752, 1, 236);
INSERT INTO `t_sys_role_resource` VALUES (89753, 1, 237);
INSERT INTO `t_sys_role_resource` VALUES (89754, 1, 238);
INSERT INTO `t_sys_role_resource` VALUES (89755, 1, 239);
INSERT INTO `t_sys_role_resource` VALUES (89756, 1, 240);
INSERT INTO `t_sys_role_resource` VALUES (89757, 1, 241);
INSERT INTO `t_sys_role_resource` VALUES (89758, 1, 242);
INSERT INTO `t_sys_role_resource` VALUES (89759, 1, 243);
INSERT INTO `t_sys_role_resource` VALUES (89760, 1, 244);
INSERT INTO `t_sys_role_resource` VALUES (89761, 1, 245);
INSERT INTO `t_sys_role_resource` VALUES (89762, 1, 246);
INSERT INTO `t_sys_role_resource` VALUES (89763, 1, 247);
INSERT INTO `t_sys_role_resource` VALUES (89764, 1, 248);
INSERT INTO `t_sys_role_resource` VALUES (89766, 1, 314);
INSERT INTO `t_sys_role_resource` VALUES (89767, 1, 315);
INSERT INTO `t_sys_role_resource` VALUES (89768, 1, 316);
INSERT INTO `t_sys_role_resource` VALUES (89769, 1, 317);
INSERT INTO `t_sys_role_resource` VALUES (89770, 1, 318);
INSERT INTO `t_sys_role_resource` VALUES (89771, 1, 319);
INSERT INTO `t_sys_role_resource` VALUES (89772, 1, 320);

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `login_name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '登陆名',
  `name` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(64) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `salt` varchar(36) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码加密盐',
  `sex` tinyint(4) NOT NULL DEFAULT 0 COMMENT '性别(0:男，1：女)',
  `phone` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '手机号',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `user_type` tinyint(4) NOT NULL DEFAULT 0 COMMENT '用户类别（0：超级管理员，1：企业用户，2：监管用户, 3：药柜管理员）',
  `status` tinyint(4) NOT NULL DEFAULT 0 COMMENT '用户状态(0：正常，1：不正常)',
  `expired` tinyint(4) NULL DEFAULT 0 COMMENT '过期字段（0-不过期，1-过期）',
  `enterprise_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '所属企业',
  `department_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT '' COMMENT '所属部门',
  `job_id` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户职务',
  `is_leader` tinyint(4) NULL DEFAULT NULL COMMENT '是否领导（0-是，1-否）',
  `create_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录创建时间',
  `update_time` datetime(0) NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP(0) COMMENT '记录最后修改时间',
  `create_user_id` bigint(19) NULL DEFAULT NULL COMMENT '记录创建用户ID',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `index_user_login_name`(`login_name`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user
-- ----------------------------
INSERT INTO `t_sys_user` VALUES (1, 'admin', 'admin', 'b2ccd2d71e04f7dd479d79c5fe886c8f', 'b4752b4b73034de06afb2db30fe19061', 0, '18627026982', 'tuminglong@126.com', 0, 0, 0, '', '', NULL, 0, '2018-12-19 20:22:33', '2020-01-10 11:35:43', NULL);

-- ----------------------------
-- Table structure for t_sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_role`;
CREATE TABLE `t_sys_user_role`  (
  `id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `user_id` bigint(19) NOT NULL COMMENT '用户id',
  `role_id` bigint(19) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`) USING BTREE,
  INDEX `idx_user_role_ids`(`user_id`, `role_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '用户角色表' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_role
-- ----------------------------
INSERT INTO `t_sys_user_role` VALUES (1, 1, 1);

-- ----------------------------
-- Table structure for t_sys_user_token
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user_token`;
CREATE TABLE `t_sys_user_token`  (
  `user_id` bigint(19) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `token` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT 'token',
  `expire_time` datetime(0) NULL DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime(0) NULL DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`) USING BTREE,
  UNIQUE INDEX `token`(`token`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '系统用户Token' ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of t_sys_user_token
-- ----------------------------
INSERT INTO `t_sys_user_token` VALUES (1, 'eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1Nzg2MzU4MzIsInRva2VuIjoxfQ.huG2vV0VzkGkbK1sN3ZdZDbTaNeHuTM6sVv6jDZwdKKSRUu0GiMvYrXTAYPeWihzWDpCPE655SXRzoLB6FY-MQ', '2020-01-10 13:57:13', '2020-01-10 13:27:13');
INSERT INTO `t_sys_user_token` VALUES (2, 'eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1Njc2Nzc2NzksInRva2VuIjoyfQ.64DfUyp85_iC2mBgX_h3Fy80H0gVwOdwoRRNyDS2iVc039j4BGDFD8yT6e6daLiogWGAg4u3S3lRNxU6CD_QTg', '2019-09-05 18:01:19', '2019-09-05 17:31:19');
INSERT INTO `t_sys_user_token` VALUES (3, 'eyJhbGciOiJIUzUxMiJ9.eyJleHAiOjE1NzQ2NzQ3MzcsInRva2VuIjozfQ.tNWisig7DBlLNUlXjNF9w-edYiNAbSMXNTONMOzb5Bz1sNBbreF1AJsKjl_ZVAkTnKMiu31hTJA2teacqY_sTQ', '2019-11-25 17:38:58', '2019-11-25 17:08:58');

SET FOREIGN_KEY_CHECKS = 1;
