/*
SQLyog Ultimate v12.08 (64 bit)
MySQL - 8.0.18 : Database - ice
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
USE `ice`;

/*Table structure for table `oauth_client_details` */

DROP TABLE IF EXISTS `oauth_client_details`;

CREATE TABLE `oauth_client_details` (
  `client_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL,
  `resource_ids` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `client_secret` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `scope` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `authorized_grant_types` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `web_server_redirect_uri` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `authorities` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `access_token_validity` int(11) DEFAULT NULL,
  `refresh_token_validity` int(11) DEFAULT NULL,
  `additional_information` varchar(4096) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `autoapprove` varchar(256) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  PRIMARY KEY (`client_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='终端信息表';

/*Data for the table `oauth_client_details` */

insert  into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values ('admin',NULL,'$2a$10$J/Cn898iyRv870TnQ37t7.rgs6tnLUb/QNTmootsReqeDut.Bn4g6','server','sms,password,refresh_token',NULL,NULL,NULL,NULL,NULL,'true'),('app',NULL,'$2a$10$J/Cn898iyRv870TnQ37t7.rgs6tnLUb/QNTmootsReqeDut.Bn4g6','server','password,refresh_token',NULL,NULL,NULL,NULL,NULL,'true'),('phone',NULL,'$2a$10$J/Cn898iyRv870TnQ37t7.rgs6tnLUb/QNTmootsReqeDut.Bn4g6','server','refresh_token',NULL,NULL,NULL,NULL,NULL,'true');

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) CHARACTER SET utf8 DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='部门管理';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`id`,`name`,`sort`,`create_time`,`update_time`,`is_del`,`parent_id`) values (1,'总经办',0,1588995163241,1588995163241,0,0),(2,'行政中心',1,1588995163241,1588995163241,0,1),(3,'技术中心',2,1588995163241,1588995163241,0,1),(4,'运营中心',3,1588995163241,1588995163241,0,1),(5,'研发中心',5,1588995163241,1588995163241,0,3),(6,'产品中心',6,1588995163241,1588995163241,0,3),(7,'测试中心',7,1588995163241,1588995163241,0,3);

/*Table structure for table `sys_dept_relation` */

DROP TABLE IF EXISTS `sys_dept_relation`;

CREATE TABLE `sys_dept_relation` (
  `ancestor` int(11) NOT NULL COMMENT '祖先节点',
  `descendant` int(11) NOT NULL COMMENT '后代节点',
  PRIMARY KEY (`ancestor`,`descendant`),
  KEY `idx1` (`ancestor`),
  KEY `idx2` (`descendant`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 ROW_FORMAT=DYNAMIC COMMENT='部门关系表';

/*Data for the table `sys_dept_relation` */

insert  into `sys_dept_relation`(`ancestor`,`descendant`) values (1,1),(1,2),(1,3),(1,4),(1,5),(1,6),(1,7),(2,2),(3,3),(3,5),(3,6),(3,7),(4,4),(5,5),(6,6),(7,7);

/*Table structure for table `sys_dict` */

DROP TABLE IF EXISTS `sys_dict`;

CREATE TABLE `sys_dict` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` bigint(13) NOT NULL COMMENT '创建时间',
  `update_time` bigint(13) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `system` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0',
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_del_flag` (`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典表';

/*Data for the table `sys_dict` */

/*Table structure for table `sys_dict_item` */

DROP TABLE IF EXISTS `sys_dict_item`;

CREATE TABLE `sys_dict_item` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `dict_id` int(11) NOT NULL,
  `value` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `label` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `sort` int(10) NOT NULL DEFAULT '0' COMMENT '排序（升序）',
  `create_time` bigint(20) NOT NULL COMMENT '创建时间',
  `update_time` bigint(20) NOT NULL COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `is_del` tinyint(1) DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`is_del`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典项';

/*Data for the table `sys_dict_item` */

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '服务ID',
  `create_by` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '创建者',
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `ip` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '操作方式',
  `params` text CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci COMMENT '操作提交的数据',
  `time` int(11) DEFAULT NULL COMMENT '执行时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '删除标记',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=70 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_croatian_ci ROW_FORMAT=DYNAMIC COMMENT='日志表';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`type`,`title`,`service_id`,`create_by`,`create_time`,`ip`,`user_agent`,`request_uri`,`method`,`params`,`time`,`is_del`) values (15,'0','查询用户',NULL,'anonymousUser',1588748371387,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',411,0),(16,'0','查询用户',NULL,'anonymousUser',1588751678723,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',425,0),(17,'0','查询用户',NULL,'anonymousUser',1588751693892,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',18,0),(18,'0','查询用户',NULL,'anonymousUser',1588752000860,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',14,0),(19,'0','查询用户',NULL,'anonymousUser',1588752003651,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',28,0),(20,'0','查询用户',NULL,'anonymousUser',1588752113345,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',319,0),(21,'0','查询用户',NULL,'anonymousUser',1588755506950,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',362,0),(22,'0','查询用户',NULL,'anonymousUser',1588755527209,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',77,0),(23,'0','查询用户',NULL,'anonymousUser',1588755584816,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',9,0),(24,'0','查询用户',NULL,'anonymousUser',1588755619885,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',10,0),(25,'0','查询用户',NULL,'anonymousUser',1588755830611,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',10,0),(26,'0','查询用户',NULL,'anonymousUser',1588755845394,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',19,0),(27,'0','查询用户',NULL,'anonymousUser',1588756052705,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',20,0),(28,'0','查询用户',NULL,'anonymousUser',1588756226875,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',9,0),(29,'0','查询用户',NULL,'anonymousUser',1588758551754,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',289,0),(30,'0','查询用户',NULL,'anonymousUser',1588815169802,'192.168.226.1','okhttp/3.12.0','/users/info/ovaet279xh','GET','type=%5Busername%5D',58,0),(31,'0','查询用户',NULL,'anonymousUser',1588833669658,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',415,0),(32,'0','查询用户',NULL,'anonymousUser',1588834054970,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',21,0),(33,'0','查询用户',NULL,'anonymousUser',1588834195723,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',35,0),(34,'0','查询用户',NULL,'anonymousUser',1588834279855,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',21,0),(35,'0','查询用户',NULL,'anonymousUser',1588834324058,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',18,0),(36,'0','查询用户',NULL,'anonymousUser',1588834410656,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',16,0),(37,'0','查询用户',NULL,'anonymousUser',1588834435837,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',21,0),(38,'0','查询用户',NULL,'anonymousUser',1588834485321,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',15,0),(39,'0','查询用户',NULL,'anonymousUser',1588834773725,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',16,0),(40,'0','查询用户',NULL,'anonymousUser',1588834797682,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',18,0),(41,'0','查询用户',NULL,'anonymousUser',1588835107720,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',18,0),(42,'0','查询用户',NULL,'anonymousUser',1588835153548,'192.168.226.1','okhttp/3.12.0','/users/info/tre','GET','type=%5Busername%5D',7,0),(43,'0','查询用户',NULL,'anonymousUser',1588835163013,'192.168.226.1','okhttp/3.12.0','/users/info/er','GET','type=%5Busername%5D',6,0),(44,'0','查询用户',NULL,'anonymousUser',1588835175721,'192.168.226.1','okhttp/3.12.0','/users/info/fds','GET','type=%5Busername%5D',6,0),(45,'0','查询用户',NULL,'anonymousUser',1588835360142,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',19,0),(46,'0','查询用户',NULL,'anonymousUser',1588835661559,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',14,0),(47,'0','查询用户',NULL,'anonymousUser',1588836320660,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',18,0),(48,'0','查询用户',NULL,'anonymousUser',1588836933049,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',250,0),(49,'0','查询用户',NULL,'anonymousUser',1588837056468,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',20,0),(50,'0','查询用户',NULL,'anonymousUser',1588837329445,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',17,0),(51,'0','查询用户',NULL,'anonymousUser',1588838468779,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',880,0),(52,'0','查询用户',NULL,'anonymousUser',1588838489859,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',12,0),(53,'0','查询用户',NULL,'anonymousUser',1588838558248,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',12,0),(54,'0','查询用户',NULL,'anonymousUser',1588839441045,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',10,0),(55,'0','查询用户',NULL,'anonymousUser',1588840302191,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',16,0),(56,'0','查询用户',NULL,'anonymousUser',1588901921379,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',422,0),(57,'0','查询用户',NULL,'anonymousUser',1588902079593,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',16,0),(58,'0','查询用户',NULL,'anonymousUser',1588902118193,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',8,0),(59,'0','查询用户',NULL,'anonymousUser',1588903370615,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',17,0),(60,'0','查询用户',NULL,'anonymousUser',1588904912303,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',54,0),(61,'0','查询用户',NULL,'anonymousUser',1588919653590,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',160,0),(62,'0','查询用户',NULL,'anonymousUser',1588919676295,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',28,0),(63,'0','查询用户',NULL,'anonymousUser',1588919714816,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',6,0),(64,'0','查询用户',NULL,'anonymousUser',1588919767834,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',6,0),(65,'0','查询用户',NULL,'anonymousUser',1588922140922,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',110,0),(66,'0','查询用户',NULL,'anonymousUser',1588922359255,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',19,0),(67,'0','查询用户',NULL,'anonymousUser',1588922474282,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',14,0),(68,'0','查询用户',NULL,'anonymousUser',1588922496677,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',18,0),(69,'0','查询用户',NULL,'anonymousUser',1588922560924,'192.168.226.1','okhttp/3.12.0','/users/info/admin','GET','type=%5Busername%5D',17,0);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '菜单名称',
  `permission` varchar(32) DEFAULT NULL COMMENT '菜单权限标识',
  `path` varchar(128) DEFAULT NULL COMMENT '前端URL',
  `parent_id` int(11) DEFAULT NULL COMMENT '父菜单ID',
  `icon` varchar(32) DEFAULT NULL COMMENT '图标',
  `component` varchar(64) DEFAULT NULL COMMENT 'VUE页面',
  `sort` int(11) DEFAULT '1' COMMENT '排序值',
  `is_keep_alive` tinyint(1) DEFAULT '0' COMMENT '0-开启，1- 关闭',
  `type` char(1) DEFAULT NULL COMMENT '菜单类型 （0菜单 1按钮）',
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '逻辑删除标记(0--正常 1--删除)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='菜单权限表';

/*Data for the table `sys_menu` */

insert  into `sys_menu`(`id`,`name`,`permission`,`path`,`parent_id`,`icon`,`component`,`sort`,`is_keep_alive`,`type`,`create_time`,`update_time`,`is_del`) values (1000,'权限管理',NULL,'/admin',-1,'lock','Layout',0,0,'0',NULL,NULL,0),(1100,'用户管理',NULL,'/admin/user/index',1000,'user','user',1,0,'0',NULL,NULL,0),(1101,'用户新增','sys_user_add',NULL,1100,'',NULL,NULL,0,'1',NULL,NULL,0),(1102,'用户修改','sys_user_edit',NULL,1100,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1103,'用户删除','sys_user_del',NULL,1100,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1200,'菜单管理',NULL,'/admin/menu/index',1000,'menu','menu',2,0,'0',NULL,NULL,0),(1201,'菜单新增','sys_menu_add',NULL,1200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1202,'菜单修改','sys_menu_edit',NULL,1200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1203,'菜单删除','sys_menu_del',NULL,1200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1300,'角色管理',NULL,'/admin/role/index',1000,'peoples','role',3,0,'0',NULL,NULL,0),(1301,'角色新增','sys_role_add',NULL,1300,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1302,'角色修改','sys_role_edit',NULL,1300,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1303,'角色删除','sys_role_del',NULL,1300,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1304,'分配权限','sys_role_perm',NULL,1300,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1400,'部门管理',NULL,'/admin/dept/index',1000,'dept','dept',4,0,'0',NULL,NULL,0),(1401,'部门新增','sys_dept_add',NULL,1400,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1402,'部门修改','sys_dept_edit',NULL,1400,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1403,'部门删除','sys_dept_del',NULL,1400,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2000,'系统管理',NULL,'/setting',-1,'setting','Layout',1,0,'0',NULL,NULL,0),(2100,'日志管理',NULL,'/setting/log/index',2000,'log','log',5,0,'0',NULL,NULL,0),(2101,'日志删除','sys_log_del',NULL,2100,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2200,'字典管理',NULL,'/setting/dict/index',2000,'dict','dict',6,0,'0',NULL,NULL,0),(2201,'字典删除','sys_dict_del',NULL,2200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2202,'字典新增','sys_dict_add',NULL,2200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2203,'字典修改','sys_dict_edit',NULL,2200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2300,'令牌管理',NULL,'/setting/token/index',2000,'token','token',11,0,'0',NULL,NULL,0),(2301,'令牌删除','sys_token_del',NULL,2300,NULL,NULL,1,0,'1',NULL,NULL,0),(2400,'终端管理','','/setting/client/index',2000,'client','client',9,0,'0',NULL,NULL,0),(2401,'客户端新增','sys_client_add',NULL,2400,'1',NULL,NULL,0,'1',NULL,NULL,0),(2402,'客户端修改','sys_client_edit',NULL,2400,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2403,'客户端删除','sys_client_del',NULL,2400,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2500,'服务监控',NULL,'http://127.0.0.1:5001',2000,'monitor',NULL,10,0,'0',NULL,NULL,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` bigint(13) NOT NULL COMMENT '更新时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`remarks`,`create_time`,`update_time`,`is_del`) values (1,'ROLE_ADMIN','管理员',20171029154551,20181226140911,0),(2,'ROLE_USER','普通用户',1111111111,11111111111,0);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (1,1000),(1,1100),(1,1101),(1,1102),(1,1103),(1,1200),(1,1201),(1,1202),(1,1203),(1,1300),(1,1301),(1,1302),(1,1303),(1,1304),(1,1400),(1,1401),(1,1402),(1,1403),(1,2000),(1,2100),(1,2101),(1,2200),(1,2201),(1,2202),(1,2203),(1,2300),(1,2301),(1,2400),(1,2401),(1,2402),(1,2403),(1,2500),(1,3000),(1,3100),(1,3200),(1,3300),(1,3301),(1,3302),(1,3303),(1,3400),(1,9999);

/*Table structure for table `sys_social_user` */

DROP TABLE IF EXISTS `sys_social_user`;

CREATE TABLE `sys_social_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '第三方类型',
  `access_token` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'access_token',
  `refresh_token` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'refresh_token',
  `open_id` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT 'open_id',
  `uid` int(11) DEFAULT NULL COMMENT 'uid',
  PRIMARY KEY (`id`),
  KEY `uid` (`uid`),
  CONSTRAINT `sys_social_user_ibfk_1` FOREIGN KEY (`uid`) REFERENCES `sys_user` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Data for the table `sys_social_user` */

insert  into `sys_social_user`(`id`,`type`,`access_token`,`refresh_token`,`open_id`,`uid`) values (2,'QQ','D10A65312EE57D86EDC56C1CBD874947','112C5B864599059C582DA20CAA0642B1','40D878D357FC9308AE2FCE5B976DBEBC',15),(3,'QQ','08EB929AD448480FE446D248B4053599','93CE93D79603CA7B2E304630C53111F7','592B3D01CCCE2A7654CB18DAD8100F99',16),(4,'QQ','40DDF3FBF83175F0FC342E741F5672D7','81FD51B0A02AC0EFA9E62D657D6B1430','57D78F37619F49D2D5CDE9AD14280702',17);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `is_lock_account` tinyint(1) DEFAULT '0' COMMENT '是否锁定',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `user_idx1_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`nickname`,`phone`,`avatar`,`remarks`,`dept_id`,`create_time`,`update_time`,`is_lock_account`,`is_del`) values (1,'admin','$2a$10$RpFJjxYiXdEsAGnWp/8fsOetMuOON96Ntk/Ym2M/RKRyU0GZseaDC','管理员','18279130562','fsdf','fsdfsd',1,1588995163241,1588995163241,0,0),(2,'user','$2a$10$RpFJjxYiXdEsAGnWp/8fsOetMuOON96Ntk/Ym2M/RKRyU0GZseaDC','用户','17034642999','',NULL,1,1588995163241,1588995163241,0,0),(3,'lock','$2a$10$RpFJjxYiXdEsAGnWp/8fsOetMuOON96Ntk/Ym2M/RKRyU0GZseaDC','锁定','17034642999','',NULL,1,1588995163241,1588995163241,1,0),(14,'sdf','fsd','fsdfsd',NULL,NULL,NULL,NULL,1588995163241,1588995163241,0,0),(15,'x5qlnj75t0','$2a$10$KEm6N1oSgAQf./QwAaZLwuJE7bgFVxY0zDbA1gAC4P/792jd6SlXK','iceS',NULL,'http://thirdqq.qlogo.cn/g?b=oidb&k=D7sSngx0CGDPOrrEAFUJOA&s=100&t=1555751467',NULL,10,1588995163241,1588995163241,0,0),(16,'580bp4r8ou','$2a$10$3TjrfhDX2MiOVyvr27N35O067WwnvOxhlUt8at4ymS1sbSDiVcl7i','忍i',NULL,'http://thirdqq.qlogo.cn/g?b=oidb&k=jrD4gOYXdfiaE8gnDq5ejfQ&s=100&t=1555636918',NULL,10,1588995163241,1588995163241,0,0),(17,'ovaet279xh','$2a$10$yEVrQU6mBBxboscRmv9C1e8Y8dxbWTs.ztUUKIs3h27lu1rXKs5Ki','04',NULL,'http://thirdqq.qlogo.cn/g?b=oidb&k=8aR9SClRlfzL1Tg6V6tFog&s=100&t=1555257554',NULL,10,1588995163241,1588995163241,0,0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1),(1,2);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
