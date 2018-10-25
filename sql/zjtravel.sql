/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50549
Source Host           : localhost:3306
Source Database       : zjtravel

Target Server Type    : MYSQL
Target Server Version : 50549
File Encoding         : 65001

Date: 2017-04-16 10:39:11
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `discount`
-- ----------------------------
DROP TABLE IF EXISTS `discount`;
CREATE TABLE `discount` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `percent` double DEFAULT NULL,
  `available` tinyint(4) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of discount
-- ----------------------------
INSERT INTO `discount` VALUES ('2', '第二次打折测试', '0.6', '0', '2017-03-04 22:07:01', '2017-04-08 15:15:52');
INSERT INTO `discount` VALUES ('3', '第一次打折测试', '0.1', '1', '2017-03-20 19:42:50', '2017-03-21 13:31:12');
INSERT INTO `discount` VALUES ('4', '五一劳动节大优惠', '0.9', '1', '2017-04-08 15:16:13', '2017-04-08 15:16:13');

-- ----------------------------
-- Table structure for `group_tour`
-- ----------------------------
DROP TABLE IF EXISTS `group_tour`;
CREATE TABLE `group_tour` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `label` varchar(50) DEFAULT NULL,
  `picture` text,
  `location` varchar(20) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `travel` text,
  `discount_ids` varchar(50) DEFAULT NULL,
  `available` tinyint(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_tour
-- ----------------------------
INSERT INTO `group_tour` VALUES ('2', '轮回', 'haha,hehe,heihei,xixi,,', '508aa838.jpg,', '工一312', 'Adsad', null, '', '0', '2017-03-04 20:14:58', '2017-04-08 15:19:27');
INSERT INTO `group_tour` VALUES ('13', '雷州西湖', '雷州,西湖,,', '5ea601e3.jpg,ddd62a2a.jpg,ec236a21.jpg,', '雷州', '雷州西湖位于国家历史文化名城广东省雷州市，雷州西湖公园是一座风景秀丽，人文荟萃，集自然风光、贤踪圣迹和各种游乐项目于一体的园林式综合性公园，人们游罢，不仅目睹独具南天一格的风貌，而且顷耳聆听一曲情节起伏的雷州史话。', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E9%9B%B7%E5%B7%9E%E8%A5%BF%E6%B9%961.jpg\" style = \"width:300px;height: 450px;\"/>\r\n<img src = \"http://ooarngwse.bkt.clouddn.com/%E9%9B%B7%E5%B7%9E%E8%A5%BF%E6%B9%962.jpg\" style = \"width:300px;height: 450px;\"/>', '4,', '1', '2017-03-16 17:22:30', '2017-04-13 15:46:55');
INSERT INTO `group_tour` VALUES ('14', 'Github', 'g,i,t,h,u,b,,', '508aa838.jpg,a1c7d268-d59f-4a3d-bde1-e3b9f3d7fbbd1cc605a3-9916-45cb-a4e4-90b122ed39d7.jpg,', '不知道', '不知道', '不知道', '', '0', '2017-03-24 15:53:50', '2017-04-08 15:19:13');
INSERT INTO `group_tour` VALUES ('15', '湛江湖光岩三日游', '湛江,湖光岩,三日游,,', '789a40b0.jpg,882c1006.jpg,93aa9acf.jpg,d7eb6da2.jpg,', '湛江', '湖光岩风景区位于中国大陆最南端湛江市区西南18公里处，被联合国地质专家称为研究地球与地质科学的“天然年鉴”。总面积为38平方公里，园区是一个以玛珥火山地质地貌为主体，兼有海岸地貌、构造地质地貌等多种地质遗迹，自然生态良好的公园。', '<img src = http://ooarngwse.bkt.clouddn.com/%E6%B9%96%E5%85%89%E5%B2%A91.jpg\" style = \"width:300px;height: 450px;\"/>\r\n<img src = http://ooarngwse.bkt.clouddn.com/%E6%B9%96%E5%85%89%E5%B2%A92.jpg\" style = \"width:300px;height: 450px;\"/>\r\n\r\n', '4,', '1', '2017-03-24 15:54:44', '2017-04-13 15:46:16');
INSERT INTO `group_tour` VALUES ('16', '雷州一日游', '雷州,,', 'a287261a.jpg,ddd62a2a.jpg,e1687687.jpg,ec236a21.jpg,', '雷州', '雷州市是中国广东省湛江市下辖的一个县级市，位于广东省西南端的雷州半岛中部，两面临海。雷州市原为海康县，历史悠久，文化底蕴深厚，是广东省唯一一个县级的“国家历史文化名城”。雷州是中国民间文化艺术之乡、中国著名水产加工生产基地、中国橡胶种植和生产基地。', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E9%9B%B7%E5%B7%9E%E4%B8%80%E6%97%A5%E6%B8%B8.jpg\" style = \"width:300px;height: 450px;\"/>\r\n', '4,', '1', '2017-03-24 15:56:33', '2017-04-13 15:41:42');
INSERT INTO `group_tour` VALUES ('17', '岭南师范大学', '湛江,岭南师范大学,,', '25f6edcc.jpg,31f7c139.jpg,8c536fe3.jpg,', '湛江', '岭南师范学院办学历史可追溯到1636年创办的雷阳书院，1902年，学校改建为雷州中学堂，1904年始设师范科教育，1935年改名为广东省立雷州师范学校，1978年正式改建为雷州师范专科学校，1991年升格并更名为湛江师范学院，2014年更为岭南师范学院.', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E5%B2%AD%E5%8D%97%E5%B8%88%E9%99%A21.jpg\" style = \"width:300px;height: 450px;\"/>\r\n<img src = \"http://ooarngwse.bkt.clouddn.com/%E5%B2%AD%E5%8D%97%E5%B8%88%E9%99%A22.jpg\" style = \"width:300px;height: 450px;\"/>\r\n', '4,', '1', '2017-03-24 15:57:11', '2017-04-13 15:43:17');
INSERT INTO `group_tour` VALUES ('18', '湛江海湾大桥', '湛江,', '54860a1e.jpg,9c08cd5d.jpg,b19aff77.jpg,', '湛江', '湛江海湾大桥，位于中国广东省湛江市，是 Shoudou 081(China).svg 081省道跨越麻斜海湾的一座双塔双索面混合梁斜拉桥。桥梁全长3,981.17米，主跨480米，桥宽25米。承载双向四车道一级公路兼顾城市快速道路，设计速度80千米/小时；桥下通航净高48米，净宽不小于400米。于2003年7月30日正式开工，2006年12月30日竣工通车。', '不知道', '4,', '1', '2017-03-24 16:01:14', '2017-04-08 16:16:05');
INSERT INTO `group_tour` VALUES ('19', '硇洲岛', '硇洲岛,,', 'a1c7d268-d59f-4a3d-bde1-e3b9f3d7fbbd1cc605a3-9916-45cb-a4e4-90b122ed39d7.jpg,e64ee6b1.jpg,', '湛江', '硇洲岛，位于中国广东省湛江市，毗邻东海岛，现行政归属于湛江市东海岛开发区硇洲镇。是一座火山形成岛屿。面积56平方公里，2009年10月统计人口为9万人。', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E7%A1%87%E6%B4%B2%E5%B2%9B.jpg\" style = \"width:300px;height: 450px;\"/>\r\n', '4,', '1', '2017-03-25 10:27:56', '2017-04-13 15:40:43');
INSERT INTO `group_tour` VALUES ('20', '湛江吴川吉兆湾旅游度假区', '湛江,吴川,吉兆湾,度假区,', '843f8a44.jpg,8977bdac.jpg,b45db48b.jpg,', '吴川', '吉兆湾位于湛江市东部50公里的广东省吴川市，以石多湾多著称，海岸线长11．2公里，是著名的海滨度假胜地，也是国内唯一可与泰国芭提雅媲美的海滩，漫步在松软的沙滩上，看着纯净的海水，岸边椰影婆娑，是领略这迷人亚热带海滨风情的最佳途径。如果赶上正月元宵节，还可以在这里观赏有名的“吴川三绝－－花桥、泥塑、飘色”，场面之热闹沸腾，丝毫不逊于国外的狂欢节。', '待定', '', '1', '2017-04-08 15:13:28', '2017-04-08 15:13:28');
INSERT INTO `group_tour` VALUES ('21', '吴川蛤岭村', '吴川,蛤岭村,,', '250dd241.jpg,c5580b99.jpg,ddd62a2a.jpg,', '吴川', '蛤岭村是一条革命老区村庄，历史由来已久，清代乾隆年间，化州杨梅镇殿西堂村陈元宾到吴川谋生路经此地，因这里水域源长，荷塘环绕且有一岭形似蛤（青蛙，吴川话曰：蛤），遂定居于此，并取名蛤岭。蛤岭村以独特的美景和文化沉淀被评为湛江最美的村庄之一。蛤岭牌坊、擎天水塔、真武神殿、十里荷塘，蛤岭村风景独秀。', '待定', '4,', '1', '2017-04-08 15:22:04', '2017-04-08 15:23:27');

-- ----------------------------
-- Table structure for `group_tour_detail`
-- ----------------------------
DROP TABLE IF EXISTS `group_tour_detail`;
CREATE TABLE `group_tour_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `group_tour_id` bigint(20) NOT NULL,
  `departure_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `available` tinyint(4) DEFAULT '1',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of group_tour_detail
-- ----------------------------
INSERT INTO `group_tour_detail` VALUES ('3', '13', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '1111.11', '1', '1', '2017-03-04 21:36:10', '2017-04-15 16:14:59');
INSERT INTO `group_tour_detail` VALUES ('17', '18', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '100', '96', '1', '2017-03-25 09:47:21', '2017-04-11 19:42:47');
INSERT INTO `group_tour_detail` VALUES ('18', '17', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '1', '94', '0', '2017-03-25 09:48:09', '2017-04-04 11:13:04');
INSERT INTO `group_tour_detail` VALUES ('20', '14', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '900', '1', '1', '2017-03-25 09:49:07', '2017-03-25 09:49:07');
INSERT INTO `group_tour_detail` VALUES ('21', '16', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '999', '3', '0', '2017-03-25 10:26:45', '2017-03-25 10:26:45');
INSERT INTO `group_tour_detail` VALUES ('22', '19', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '888', '1', '1', '2017-03-25 10:28:13', '2017-04-12 23:19:27');
INSERT INTO `group_tour_detail` VALUES ('23', '13', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '500', '46', '0', '2017-03-27 14:32:57', '2017-04-15 16:10:42');
INSERT INTO `group_tour_detail` VALUES ('24', '15', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '0.1', '100', '1', '2017-03-30 13:50:11', '2017-03-30 13:50:11');
INSERT INTO `group_tour_detail` VALUES ('25', '15', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '1', '93', '1', '2017-03-30 13:52:18', '2017-04-10 13:17:57');
INSERT INTO `group_tour_detail` VALUES ('26', '20', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '50', '97', '1', '2017-04-08 15:13:54', '2017-04-10 13:59:41');
INSERT INTO `group_tour_detail` VALUES ('27', '21', '2017-04-25 19:25:42', '2017-04-27 19:25:12', '88', '100', '1', '2017-04-08 15:23:51', '2017-04-08 15:23:51');

-- ----------------------------
-- Table structure for `order`
-- ----------------------------
DROP TABLE IF EXISTS `order`;
CREATE TABLE `order` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL,
  `goods_id` bigint(20) NOT NULL,
  `goods_type` tinyint(4) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `payment` double DEFAULT '0',
  `state` varchar(20) DEFAULT '0',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of order
-- ----------------------------
INSERT INTO `order` VALUES ('5', '2', '23', '1', '1', '300', 'paid', '2017-03-28 18:09:39', '2017-03-28 18:09:39');
INSERT INTO `order` VALUES ('6', '2', '23', '1', '1', '300', 'paid', '2017-03-28 18:11:44', '2017-03-28 18:11:44');
INSERT INTO `order` VALUES ('7', '2', '3', '1', '1', '666.6659999999999', 'paid', '2017-03-28 21:14:08', '2017-03-28 21:14:08');
INSERT INTO `order` VALUES ('10', '2', '22', '1', '1', '88.80000000000001', 'charge_back', '2017-03-30 13:41:51', '2017-03-30 13:48:04');
INSERT INTO `order` VALUES ('12', '2', '10', '2', '1', '52.8', 'paid', '2017-03-30 14:12:12', '2017-03-30 14:12:12');
INSERT INTO `order` VALUES ('13', '2', '2', '2', '1', '60', 'paid', '2017-03-30 16:28:08', '2017-03-30 16:28:08');
INSERT INTO `order` VALUES ('14', '2', '8', '2', '1', '18', 'paid', '2017-03-30 16:53:36', '2017-03-30 16:53:36');
INSERT INTO `order` VALUES ('15', '2', '24', '1', '1', '0.010000000000000002', 'charge_back_success', '2017-03-30 17:20:30', '2017-03-30 17:21:07');
INSERT INTO `order` VALUES ('16', '2', '8', '2', '1', '30', 'paid', '2017-03-30 21:04:56', '2017-03-30 21:04:56');
INSERT INTO `order` VALUES ('17', '2', '4', '2', '1', '1.1', 'paid', '2017-03-30 21:05:32', '2017-03-30 21:05:32');
INSERT INTO `order` VALUES ('18', '2', '4', '2', '3', '9.9', 'paid', '2017-04-02 11:42:37', '2017-04-02 11:42:37');
INSERT INTO `order` VALUES ('19', '1', '23', '1', '3', '1500', 'paid', '2017-04-02 17:35:33', '2017-04-02 17:35:33');
INSERT INTO `order` VALUES ('20', '1', '1', '2', '1', '100', 'paid', '2017-04-02 17:35:59', '2017-04-02 17:35:59');
INSERT INTO `order` VALUES ('21', '1', '18', '1', '4', '4', 'charge_back_success', '2017-04-02 17:36:47', '2017-04-02 23:06:26');
INSERT INTO `order` VALUES ('22', '1', '3', '1', '1', '1111.11', 'charge_back', '2017-04-02 22:17:28', '2017-04-02 22:54:54');
INSERT INTO `order` VALUES ('23', '1', '3', '1', '1', '1111.11', 'charge_back', '2017-04-02 22:51:33', '2017-04-02 22:51:54');
INSERT INTO `order` VALUES ('24', '1', '10', '2', '1', '88', 'paid', '2017-04-02 22:55:57', '2017-04-02 22:56:11');
INSERT INTO `order` VALUES ('25', '1', '9', '2', '18', '1260', 'paid', '2017-04-02 22:58:06', '2017-04-02 22:58:06');
INSERT INTO `order` VALUES ('26', '2', '18', '1', '4', '4', 'paid', '2017-04-03 15:17:12', '2017-04-03 15:17:12');
INSERT INTO `order` VALUES ('27', '2', '4', '2', '2', '4.4', 'paid', '2017-04-03 16:40:29', '2017-04-03 16:40:29');
INSERT INTO `order` VALUES ('28', '2', '4', '2', '2', '2.2', 'paid', '2017-04-03 16:50:52', '2017-04-03 16:50:52');
INSERT INTO `order` VALUES ('29', '2', '18', '1', '2', '2', 'paid', '2017-04-04 11:13:03', '2017-04-04 11:13:03');
INSERT INTO `order` VALUES ('30', '1', '5', '2', '1', '100', 'charge_back', '2017-04-05 23:05:26', '2017-04-05 23:08:36');
INSERT INTO `order` VALUES ('31', '2', '25', '1', '7', '44.1', 'paid', '2017-04-10 13:17:57', '2017-04-10 13:17:57');
INSERT INTO `order` VALUES ('32', '2', '11', '2', '6', '54', 'paid', '2017-04-10 13:25:27', '2017-04-10 13:25:27');
INSERT INTO `order` VALUES ('33', '2', '17', '1', '3', '270', 'paid', '2017-04-10 13:59:05', '2017-04-10 13:59:05');
INSERT INTO `order` VALUES ('34', '2', '26', '1', '1', '50', 'paid', '2017-04-10 13:59:28', '2017-04-10 13:59:28');
INSERT INTO `order` VALUES ('35', '2', '26', '1', '2', '100', 'paid', '2017-04-10 13:59:41', '2017-04-10 13:59:41');
INSERT INTO `order` VALUES ('36', '34', '17', '1', '1', '90', 'charge_back_success', '2017-04-11 19:42:47', '2017-04-11 19:44:12');
INSERT INTO `order` VALUES ('37', '2', '22', '1', '2', '1598.4', 'paid', '2017-04-12 23:19:27', '2017-04-12 23:19:27');
INSERT INTO `order` VALUES ('38', '1', '23', '1', '1', '450', 'paid', '2017-04-15 16:09:50', '2017-04-15 16:09:50');
INSERT INTO `order` VALUES ('39', '1', '23', '1', '3', '1350', 'charge_back_success', '2017-04-15 16:10:42', '2017-04-15 16:12:18');
INSERT INTO `order` VALUES ('40', '1', '3', '1', '1', '999.9989999999999', 'charge_back_success', '2017-04-15 16:10:53', '2017-04-15 16:12:15');
INSERT INTO `order` VALUES ('41', '1', '3', '1', '1', '999.9989999999999', 'charge_back_success', '2017-04-15 16:14:59', '2017-04-15 16:15:23');

-- ----------------------------
-- Table structure for `resource`
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(100) DEFAULT NULL,
  `type` varchar(50) DEFAULT NULL,
  `url` varchar(200) DEFAULT NULL,
  `parent_id` bigint(20) DEFAULT NULL,
  `parent_ids` varchar(100) DEFAULT NULL,
  `permission` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '1',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_resource_parent_id` (`parent_id`) USING BTREE,
  KEY `idx_resource_parent_idx` (`parent_ids`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of resource
-- ----------------------------
INSERT INTO `resource` VALUES ('1', '资源', 'menu', '', '0', '0/', '', '1', null, null);
INSERT INTO `resource` VALUES ('2', '后台管理页面权限', 'menu', '/backstage', '1', '0/1/', 'backstage:*', '0', '2017-03-31 16:08:15', '2017-03-31 16:55:12');
INSERT INTO `resource` VALUES ('21', '用户管理', 'menu', '/user', '1', '0/1/', 'user:*', '1', null, null);
INSERT INTO `resource` VALUES ('22', '用户新增', 'button', '', '21', '0/1/21/', 'user:create', '0', null, null);
INSERT INTO `resource` VALUES ('23', '用户修改', 'button', '', '21', '0/1/21/', 'user:update', '1', null, null);
INSERT INTO `resource` VALUES ('24', '用户删除', 'button', '', '21', '0/1/21/', 'user:delete', '1', null, null);
INSERT INTO `resource` VALUES ('25', '用户查看', 'button', '', '21', '0/1/21/', 'user:view', '1', null, null);
INSERT INTO `resource` VALUES ('31', '资源管理', 'menu', '/resource', '1', '0/1/', 'resource:*', '1', null, null);
INSERT INTO `resource` VALUES ('32', '资源新增', 'button', '', '31', '0/1/31/', 'resource:create', '1', null, null);
INSERT INTO `resource` VALUES ('33', '资源修改', 'button', '', '31', '0/1/31/', 'resource:update', '1', null, null);
INSERT INTO `resource` VALUES ('34', '资源删除', 'button', '', '31', '0/1/31/', 'resource:delete', '1', null, null);
INSERT INTO `resource` VALUES ('35', '资源查看', 'button', '', '31', '0/1/31/', 'resource:view', '1', null, null);
INSERT INTO `resource` VALUES ('41', '角色管理', 'menu', '/role', '1', '0/1/', 'role:*', '1', null, null);
INSERT INTO `resource` VALUES ('42', '角色新增', 'button', '', '41', '0/1/41/', 'role:create', '1', null, null);
INSERT INTO `resource` VALUES ('43', '角色修改', 'button', '', '41', '0/1/41/', 'role:update', '1', null, null);
INSERT INTO `resource` VALUES ('44', '角色删除', 'button', '', '41', '0/1/41/', 'role:delete', '1', null, null);
INSERT INTO `resource` VALUES ('45', '角色查看', 'button', '', '41', '0/1/41/', 'role:view', '1', null, null);
INSERT INTO `resource` VALUES ('47', '产品管理', 'menu', '/product', '1', '0/1/', 'product:*', '0', '2017-03-31 16:53:36', '2017-03-31 16:53:36');
INSERT INTO `resource` VALUES ('48', '产品新增', 'button', '', '47', '0/1/47/', 'product:create', '0', '2017-03-31 16:58:57', '2017-03-31 16:58:57');
INSERT INTO `resource` VALUES ('49', '产品修改', 'button', '', '47', '0/1/47/', 'product:update', '0', '2017-03-31 16:59:44', '2017-03-31 16:59:44');
INSERT INTO `resource` VALUES ('50', '产品查看', 'button', '', '47', '0/1/47/', 'product:view', '0', '2017-03-31 17:01:39', '2017-03-31 17:01:39');
INSERT INTO `resource` VALUES ('54', '产品图片管理', 'menu', '/product_image', '1', '0/1/', 'product_image:*', '0', '2017-03-31 17:09:24', '2017-03-31 17:09:24');
INSERT INTO `resource` VALUES ('55', '产品图片上传', 'button', '', '54', '0/1/54/', 'product_image:upload', '0', '2017-03-31 17:10:03', '2017-03-31 17:10:03');
INSERT INTO `resource` VALUES ('56', '产品图片删除', 'button', '', '54', '0/1/54/', 'product_image:delete', '0', '2017-03-31 17:10:30', '2017-03-31 17:10:30');
INSERT INTO `resource` VALUES ('57', '产品图片查看', 'button', '', '54', '0/1/54/', 'product_image:view', '0', '2017-03-31 17:10:57', '2017-03-31 17:10:57');
INSERT INTO `resource` VALUES ('58', '优惠管理', 'menu', '/discount', '1', '0/1/', 'discount:*', '0', '2017-03-31 17:12:13', '2017-03-31 17:12:13');
INSERT INTO `resource` VALUES ('59', '优惠新增', 'button', '', '58', '0/1/58/', 'discount:create', '0', '2017-03-31 17:12:39', '2017-03-31 17:12:39');
INSERT INTO `resource` VALUES ('60', '优惠修改', 'button', '', '58', '0/1/58/', 'discount:update', '0', '2017-03-31 17:13:18', '2017-03-31 17:13:18');
INSERT INTO `resource` VALUES ('61', '优惠删除', 'button', '', '58', '0/1/58/', 'discount:delete', '0', '2017-03-31 17:13:45', '2017-03-31 17:13:45');
INSERT INTO `resource` VALUES ('62', '优惠查看', 'button', '', '58', '0/1/58/', 'discount:view', '0', '2017-03-31 17:14:09', '2017-03-31 17:14:09');
INSERT INTO `resource` VALUES ('63', '订单管理', 'menu', '/order', '1', '0/1/', 'order:*', '0', '2017-03-31 17:45:42', '2017-03-31 17:45:42');
INSERT INTO `resource` VALUES ('64', '订单查看', 'button', '', '63', '0/1/63/', 'order:view', '0', '2017-03-31 17:46:20', '2017-03-31 17:46:20');
INSERT INTO `resource` VALUES ('65', '退单处理', 'button', '', '63', '0/1/63/', 'order:update', '0', '2017-03-31 17:46:59', '2017-03-31 17:46:59');
INSERT INTO `resource` VALUES ('66', '网站管理', 'menu', '/page', '1', '0/1/', 'page:*', '0', '2017-03-31 17:57:21', '2017-03-31 17:57:21');
INSERT INTO `resource` VALUES ('67', '图片轮播上传', 'button', '', '66', '0/1/66/', 'page:upload', '0', '2017-03-31 17:58:30', '2017-03-31 17:58:30');
INSERT INTO `resource` VALUES ('68', '图片轮播删除', 'button', '', '66', '0/1/66/', 'page:delete', '0', '2017-03-31 17:59:17', '2017-03-31 17:59:17');
INSERT INTO `resource` VALUES ('69', '图片轮播查看', 'button', '', '66', '0/1/66/', 'page:view', '0', '2017-03-31 17:59:49', '2017-03-31 17:59:49');

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `role` varchar(100) DEFAULT NULL,
  `description` varchar(100) DEFAULT NULL,
  `resource_ids` varchar(100) DEFAULT NULL,
  `available` tinyint(1) DEFAULT '0',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `idx_role_resource_ids` (`resource_ids`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin', '超级管理员', '2,21,31,41,47,54,58,63,66,', '0', null, '2017-03-31 18:02:42');
INSERT INTO `role` VALUES ('12', 'livid', '不可描述', '21,22,23,24,25,41,42,43,44,45,', '1', '2017-02-24 14:07:15', '2017-03-21 13:35:10');
INSERT INTO `role` VALUES ('15', 'general_user', '普通用户', '', '1', '2017-03-24 09:57:10', '2017-03-24 09:57:10');

-- ----------------------------
-- Table structure for `ticket`
-- ----------------------------
DROP TABLE IF EXISTS `ticket`;
CREATE TABLE `ticket` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `title` varchar(50) DEFAULT NULL,
  `label` varchar(50) DEFAULT NULL,
  `picture` text,
  `location` varchar(20) DEFAULT NULL,
  `introduce` varchar(255) DEFAULT NULL,
  `travel` text,
  `discount_ids` varchar(50) DEFAULT NULL,
  `available` tinyint(4) DEFAULT '0',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticket
-- ----------------------------
INSERT INTO `ticket` VALUES ('1', '湛江湖光岩', '湛江,湖光岩,,', '95f48163.jpg,9f5d998c.jpg,d7eb6da2.jpg,', '湛江', '湖光岩风景区位于中国大陆最南端湛江市区西南18公里处，被联合国地质专家称为研究地球与地质科学的“天然年鉴”。总面积为38平方公里，园区是一个以玛珥火山地质地貌为主体，兼有海岸地貌、构造地质地貌等多种地质遗迹，自然生态良好的公园。', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E6%B9%96%E5%85%89%E5%B2%A91.jpg\" style = \"width:300px;height: 450px;\"/>\r\n<img src = \"http://ooarngwse.bkt.clouddn.com/%E6%B9%96%E5%85%89%E5%B2%A92.jpg\" style = \"width:300px;height: 450px;\"/>\r\n\r\n', '4,', '1', '2017-03-29 19:36:58', '2017-04-13 15:53:40');
INSERT INTO `ticket` VALUES ('2', '雷州西湖', '雷州,西湖,,', '57af0010.jpg,93aa9acf.jpg,dd28070f.jpg,', '雷州', '不知道', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E9%9B%B7%E5%B7%9E%E8%A5%BF%E6%B9%961.jpg\" style = \"width:300px;height: 450px;\"/>\r\n<img src = \"http://ooarngwse.bkt.clouddn.com/%E9%9B%B7%E5%B7%9E%E8%A5%BF%E6%B9%962.jpg\" style = \"width:300px;height: 450px;\"/>', '', '1', '2017-03-29 19:55:37', '2017-04-13 15:54:00');
INSERT INTO `ticket` VALUES ('3', '湛江寸金桥公园', '湛江,,', '789a40b0.jpg,c6561eb7.jpg,ddd62a2a.jpg,', '湛江', '不知道', '不知道', '', '1', '2017-03-29 19:57:30', '2017-04-08 15:29:33');
INSERT INTO `ticket` VALUES ('4', '吴川海滩', '吴川,,', 'b45db48b.jpg,', '吴川', '不知道', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E5%90%B4%E5%B7%9D%E6%B5%B7%E6%BB%A91.jpg\" style = \"width:300px;height: 450px;\"/>\r\n<img src = \"http://ooarngwse.bkt.clouddn.com/%E5%90%B4%E5%B7%9D%E6%B5%B7%E6%BB%A92.jpg\" style = \"width:300px;height: 450px;\"/>\r\n\r\n', '4,', '1', '2017-03-29 20:00:15', '2017-04-13 15:51:35');
INSERT INTO `ticket` VALUES ('5', '雷州高山寺', '雷州,,', 'a1c7d268-d59f-4a3d-bde1-e3b9f3d7fbbd1cc605a3-9916-45cb-a4e4-90b122ed39d7.jpg,c6561eb7.jpg,dd28070f.jpg,ddd62a2a.jpg,ec236a21.jpg,', '雷州', '雷州高山寺位于中国历史文化名城--雷州城的北门外，占地面积30多亩，建筑面积6056.7平方米。始建于宋朝末年，重修于清朝顺治甲申年（1644年）。', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E9%9B%B7%E5%B7%9E%E9%AB%98%E5%B1%B1%E5%AF%BA1.jpg\" style = \"width:300px;height: 450px;\"/>\r\n<img src = \"http://ooarngwse.bkt.clouddn.com/%E9%9B%B7%E5%B7%9E%E9%AB%98%E5%B1%B1%E5%AF%BA2.jpg\" style = \"width:300px;height: 450px;\"/>', '4,', '1', '2017-03-29 20:01:22', '2017-04-13 15:54:51');
INSERT INTO `ticket` VALUES ('6', '徐闻度假村', '徐闻,,', '843f8a44.jpg,8977bdac.jpg,a1c7d268-d59f-4a3d-bde1-e3b9f3d7fbbd1cc605a3-9916-45cb-a4e4-90b122ed39d7.jpg,b45db48b.jpg,', '徐闻', '不知道', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E5%BE%90%E9%97%BB%E5%BA%A6%E5%81%87%E6%9D%911.jpg\" style = \"width:300px;height: 450px;\"/>\r\n<img src = \"http://ooarngwse.bkt.clouddn.com/%E5%BE%90%E9%97%BB%E5%BA%A6%E5%81%87%E6%9D%912.jpg\" style = \"width:300px;height: 450px;\"/>\r\n', '4,', '1', '2017-03-29 20:02:19', '2017-04-13 15:55:40');
INSERT INTO `ticket` VALUES ('7', '湛江森林公园', '湛江,公园,,', 'f54299d1.jpg,', '湛江', '三岭山国家森林公园位于中国大陆最南端——湛江市区西南三公里处。在2013年被官方评为“湛江新八景”之一，美誉“三岭叠翠”。', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E6%B9%9B%E6%B1%9F%E6%A3%AE%E6%9E%97%E5%85%AC%E5%9B%AD1.jpg\" style = \"width:300px;height: 450px;\"/>\r\n<img src = \"http://ooarngwse.bkt.clouddn.com/%E6%B9%9B%E6%B1%9F%E6%A3%AE%E6%9E%97%E5%85%AC%E5%9B%AD2.jpg\" style = \"width:300px;height: 450px;\"/>\r\n', '4,', '1', '2017-03-29 20:04:06', '2017-04-13 15:52:31');
INSERT INTO `ticket` VALUES ('8', '雷州三元塔公园', '雷州,三元塔,,', '9df7b111.jpg,a287261a.jpg,', '雷州', '三元塔位于中国广东省雷州市城区三元塔公园内，又名启秀塔、文魁塔，是一座明代楼阁式砖塔，1979年被列为广东省文物保护单位。\r\n明万历四腔铺木楼板。塔刹为紫铜葫芦状，高2.2米。底层内径4.06米，顶层内径2.1米，逐层收分。', '<img src = \"http://ooarngwse.bkt.clouddn.com/%E9%9B%B7%E5%B7%9E%E4%B8%89%E5%85%83%E5%A1%94%E5%85%AC%E5%9B%AD1.jpg\" style = \"width:300px;height: 450px;\"/>\r\n<img src = \"http://ooarngwse.bkt.clouddn.com/%E9%9B%B7%E5%B7%9E%E4%B8%89%E5%85%83%E5%A1%94%E5%85%AC%E5%9B%AD2.jpg\" style = \"width:300px;height: 450px;\"/>\r\n', '4,', '1', '2017-04-08 15:42:03', '2017-04-13 15:56:21');

-- ----------------------------
-- Table structure for `ticket_detail`
-- ----------------------------
DROP TABLE IF EXISTS `ticket_detail`;
CREATE TABLE `ticket_detail` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `ticket_id` bigint(20) NOT NULL,
  `departure_time` datetime DEFAULT NULL,
  `price` double DEFAULT NULL,
  `stock` int(11) DEFAULT NULL,
  `available` tinyint(4) DEFAULT NULL,
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ticket_detail
-- ----------------------------
INSERT INTO `ticket_detail` VALUES ('1', '1', '2017-04-15 00:00:00', '100', '100', '1', '2017-03-29 19:50:08', '2017-03-29 19:50:08');
INSERT INTO `ticket_detail` VALUES ('2', '1', '2017-04-22 00:00:00', '100', '100', '1', '2017-03-29 19:50:31', '2017-03-29 19:50:31');
INSERT INTO `ticket_detail` VALUES ('3', '1', '2017-05-10 00:00:00', '100', '3', '1', '2017-03-29 19:55:52', '2017-03-29 19:55:52');
INSERT INTO `ticket_detail` VALUES ('4', '2', '2017-05-11 00:00:00', '11', '1', '1', '2017-03-29 19:56:27', '2017-04-03 16:50:53');
INSERT INTO `ticket_detail` VALUES ('5', '3', '2017-05-12 00:00:00', '100', '99', '1', '2017-03-29 19:57:46', '2017-04-05 23:05:27');
INSERT INTO `ticket_detail` VALUES ('6', '3', '2017-05-14 00:00:00', '99', '100', '1', '2017-03-29 19:58:04', '2017-03-29 19:58:04');
INSERT INTO `ticket_detail` VALUES ('7', '4', '2017-05-28 00:00:00', '50', '10', '1', '2017-03-29 20:00:32', '2017-03-29 20:00:32');
INSERT INTO `ticket_detail` VALUES ('8', '5', '2017-04-30 00:00:00', '30', '100', '0', '2017-03-29 20:02:41', '2017-03-29 20:02:41');
INSERT INTO `ticket_detail` VALUES ('9', '6', '2017-05-27 00:00:00', '70', '300', '1', '2017-03-29 20:02:57', '2017-03-29 20:02:57');
INSERT INTO `ticket_detail` VALUES ('10', '7', '2017-04-01 00:00:00', '88', '99', '1', '2017-03-29 20:04:20', '2017-03-29 20:04:20');
INSERT INTO `ticket_detail` VALUES ('11', '8', '2017-04-16 00:00:00', '10', '94', '1', '2017-04-08 15:42:31', '2017-04-10 13:25:27');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(100) DEFAULT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `salt` varchar(100) DEFAULT NULL,
  `role_ids` varchar(100) DEFAULT NULL,
  `locked` tinyint(1) DEFAULT '0',
  `gmt_create` datetime DEFAULT NULL,
  `gmt_modified` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_user_username` (`username`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=36 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'admin', '170b4577cf5630f51797578ca3a2388c', '13427581251', 'c3a07526ae08e9d4b152eee02175b746', '1,', '0', null, '2017-04-12 23:20:41');
INSERT INTO `user` VALUES ('2', 'livid', 'a388f99d37f08cb7f40c970c2179e2b5', '15611012138', 'f0d4eaf3821edd94410c2a3eda576dff', '1,', '0', null, '2017-03-23 00:07:14');
INSERT INTO `user` VALUES ('3', '林寒戈', '123456', '18820707715', 'b59ca65e5a969f5aadb1023a6d052530', '12,', '0', '2017-02-23 17:41:16', '2017-02-23 17:41:16');
INSERT INTO `user` VALUES ('34', '雷神大大', 'e68d98c6c3acc754326a3461db33bc1b', '18312090636', '56f155709b77fb87fea61ab82d5ad718', '15,', '0', '2017-04-02 23:14:35', '2017-04-13 13:32:47');

-- ----------------------------
-- Table structure for `wallet`
-- ----------------------------
DROP TABLE IF EXISTS `wallet`;
CREATE TABLE `wallet` (
  `user_id` bigint(20) NOT NULL,
  `money` double NOT NULL DEFAULT '1000',
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of wallet
-- ----------------------------
INSERT INTO `wallet` VALUES ('1', '4279.780000000001');
INSERT INTO `wallet` VALUES ('2', '927.5999999999999');
INSERT INTO `wallet` VALUES ('13', '100');
INSERT INTO `wallet` VALUES ('23', '100');
INSERT INTO `wallet` VALUES ('24', '0');
INSERT INTO `wallet` VALUES ('31', '100');
INSERT INTO `wallet` VALUES ('32', '100');
INSERT INTO `wallet` VALUES ('33', '811.2');
INSERT INTO `wallet` VALUES ('34', '100');
INSERT INTO `wallet` VALUES ('35', '100');
