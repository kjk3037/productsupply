/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50731
Source Host           : localhost:3306
Source Database       : product_supply

Target Server Type    : MYSQL
Target Server Version : 50731
File Encoding         : 65001

Date: 2021-08-05 17:53:15
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for workflew
-- ----------------------------
DROP TABLE IF EXISTS `workflew`;
CREATE TABLE `workflew` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `desc` varchar(255) DEFAULT NULL,
  `sys_module_id` int(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workflew
-- ----------------------------
INSERT INTO `workflew` VALUES ('1', '采购支付申请审批流程', null, null);

-- ----------------------------
-- Table structure for workflew_approver
-- ----------------------------
DROP TABLE IF EXISTS `workflew_approver`;
CREATE TABLE `workflew_approver` (
  `id` int(11) NOT NULL,
  `workflew_id` int(11) DEFAULT NULL,
  `workflew_node_id` int(11) DEFAULT NULL,
  `approver_type` varchar(255) DEFAULT NULL,
  `approver_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workflew_approver
-- ----------------------------

-- ----------------------------
-- Table structure for workflew_entity
-- ----------------------------
DROP TABLE IF EXISTS `workflew_entity`;
CREATE TABLE `workflew_entity` (
  `id` int(11) NOT NULL,
  `workflew_id` int(11) DEFAULT NULL,
  `workflew_node_id` int(11) DEFAULT NULL,
  `approver_id` int(11) DEFAULT NULL,
  `approver_result` varchar(255) DEFAULT NULL,
  `approver_suggestion` varchar(255) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workflew_entity
-- ----------------------------

-- ----------------------------
-- Table structure for workflew_node
-- ----------------------------
DROP TABLE IF EXISTS `workflew_node`;
CREATE TABLE `workflew_node` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `type` int(11) DEFAULT NULL COMMENT '0、开始节点；1、审批节点；2、填写节点；3、分支节点；4、抄送节点；5、结束节点',
  `node_name` varchar(255) DEFAULT NULL,
  `workflew_id` int(11) DEFAULT NULL COMMENT '流程id',
  `approver_id` int(11) DEFAULT NULL COMMENT '执行人id',
  `prev_node_id` int(11) DEFAULT NULL COMMENT '前节点',
  `next_node_id` int(11) DEFAULT NULL COMMENT '后节点',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workflew_node
-- ----------------------------
INSERT INTO `workflew_node` VALUES ('1', null, '交付经理审批', '1', '1', null, '2');
INSERT INTO `workflew_node` VALUES ('2', null, '运营会计审批', '1', '2', '1', '3');
INSERT INTO `workflew_node` VALUES ('3', null, '总账会计审批', '1', '3', '2', null);

-- ----------------------------
-- Table structure for workflow_branch
-- ----------------------------
DROP TABLE IF EXISTS `workflow_branch`;
CREATE TABLE `workflow_branch` (
  `id` int(11) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `workflow_id` int(11) DEFAULT NULL,
  `workflow_node_id` int(11) DEFAULT NULL,
  `result` varchar(255) DEFAULT NULL,
  `workflow_nextnode_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workflow_branch
-- ----------------------------

-- ----------------------------
-- Table structure for workflow_table_field
-- ----------------------------
DROP TABLE IF EXISTS `workflow_table_field`;
CREATE TABLE `workflow_table_field` (
  `id` int(11) NOT NULL,
  `workflow_id` int(11) DEFAULT NULL,
  `workflow_node_id` int(11) DEFAULT NULL,
  `field_id` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL COMMENT '1、可见；2、可编辑；3、隐藏；4、禁用',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of workflow_table_field
-- ----------------------------
