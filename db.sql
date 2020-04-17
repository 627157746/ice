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

insert  into `oauth_client_details`(`client_id`,`resource_ids`,`client_secret`,`scope`,`authorized_grant_types`,`web_server_redirect_uri`,`authorities`,`access_token_validity`,`refresh_token_validity`,`additional_information`,`autoapprove`) values ('admin',NULL,'$2a$10$J/Cn898iyRv870TnQ37t7.rgs6tnLUb/QNTmootsReqeDut.Bn4g6','server','password,refresh_token',NULL,NULL,NULL,NULL,NULL,'true'),('app',NULL,'app','server','password,refresh_token',NULL,NULL,NULL,NULL,NULL,'true'),('gen',NULL,'gen','server','password,refresh_token',NULL,NULL,NULL,NULL,NULL,'true'),('pig',NULL,'pig','server','password,refresh_token,authorization_code,client_credentials','http://localhost:4040/sso1/login,http://localhost:4041/sso1/login',NULL,NULL,NULL,NULL,'true'),('test',NULL,'test','server','password,refresh_token',NULL,NULL,NULL,NULL,NULL,'true');

/*Table structure for table `sys_dept` */

DROP TABLE IF EXISTS `sys_dept`;

CREATE TABLE `sys_dept` (
  `dept_id` int(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '部门名称',
  `sort` int(11) DEFAULT '0' COMMENT '排序',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '是否删除  -1：已删除  0：正常',
  `parent_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`dept_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='部门管理';

/*Data for the table `sys_dept` */

insert  into `sys_dept`(`dept_id`,`name`,`sort`,`create_time`,`update_time`,`del_flag`,`parent_id`) values (1,'总经办',0,NULL,NULL,'0',0),(2,'行政中心',1,NULL,NULL,'0',1),(3,'技术中心',2,NULL,NULL,'0',1),(4,'运营中心',3,NULL,NULL,'0',1),(5,'研发中心',5,NULL,NULL,'0',3),(6,'产品中心',6,NULL,NULL,'0',3),(7,'测试中心',7,NULL,NULL,'0',3);

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
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `description` varchar(100) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `system` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0',
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典表';

/*Data for the table `sys_dict` */

insert  into `sys_dict`(`id`,`type`,`description`,`create_time`,`update_time`,`remarks`,`system`,`del_flag`) values (1,'dict_type','字典类型','2019-05-16 14:16:20','2019-05-16 14:20:16','系统类不能修改','1','0'),(2,'log_type','日志类型','2020-03-13 14:21:01','2020-03-13 14:21:01','0-正常 1 异常','1','0'),(3,'a','sdf','2020-04-16 11:50:26','2020-04-16 11:50:26','','0','0');

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
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remarks` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL,
  `del_flag` char(1) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT '0',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `sys_dict_value` (`value`) USING BTREE,
  KEY `sys_dict_label` (`label`) USING BTREE,
  KEY `sys_dict_del_flag` (`del_flag`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci COMMENT='字典项';

/*Data for the table `sys_dict_item` */

insert  into `sys_dict_item`(`id`,`dict_id`,`value`,`label`,`type`,`description`,`sort`,`create_time`,`update_time`,`remarks`,`del_flag`) values (1,1,'1','系统类','dict_type','系统类字典',0,'2019-05-16 14:20:40','2019-05-16 14:20:40','不能修改删除','0'),(2,1,'0','业务类','dict_type','业务类字典',0,'2019-05-16 14:20:59','2019-05-16 14:20:59','可以修改','0'),(3,2,'0','正常','log_type','正常',0,'2020-03-13 14:23:22','2020-03-13 14:23:22','正常','0'),(4,2,'9','异常','log_type','异常',1,'2020-03-13 14:23:35','2020-03-13 14:23:35','异常','0');

/*Table structure for table `sys_log` */

DROP TABLE IF EXISTS `sys_log`;

CREATE TABLE `sys_log` (
  `id` bigint(64) NOT NULL AUTO_INCREMENT COMMENT '编号',
  `type` char(1) DEFAULT '1' COMMENT '日志类型',
  `title` varchar(255) DEFAULT '' COMMENT '日志标题',
  `service_id` varchar(32) DEFAULT NULL COMMENT '服务ID',
  `create_by` varchar(64) DEFAULT NULL COMMENT '创建者',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  `remote_addr` varchar(255) DEFAULT NULL COMMENT '操作IP地址',
  `user_agent` varchar(1000) DEFAULT NULL COMMENT '用户代理',
  `request_uri` varchar(255) DEFAULT NULL COMMENT '请求URI',
  `method` varchar(10) DEFAULT NULL COMMENT '操作方式',
  `params` text COMMENT '操作提交的数据',
  `time` mediumtext COMMENT '执行时间',
  `del_flag` char(1) DEFAULT '0' COMMENT '删除标记',
  `exception` text COMMENT '异常信息',
  PRIMARY KEY (`id`),
  KEY `sys_log_create_by` (`create_by`),
  KEY `sys_log_request_uri` (`request_uri`),
  KEY `sys_log_type` (`type`),
  KEY `sys_log_create_date` (`create_time`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='日志表';

/*Data for the table `sys_log` */

insert  into `sys_log`(`id`,`type`,`title`,`service_id`,`create_by`,`create_time`,`update_time`,`remote_addr`,`user_agent`,`request_uri`,`method`,`params`,`time`,`del_flag`,`exception`) values (1,'0','添加字典','pig','admin','2020-04-16 11:50:27',NULL,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36','/dict','POST','','57','0',NULL),(2,'0','新增数据源表','pig','admin','2020-04-16 14:55:16',NULL,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36','/dsconf','POST','','120','0',NULL),(3,'0','修改数据源表','pig','admin','2020-04-16 14:58:18',NULL,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36','/dsconf','PUT','','45','0',NULL),(4,'0','修改数据源表','pig','admin','2020-04-16 15:08:56',NULL,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36','/dsconf','PUT','','39','0',NULL),(5,'0','修改数据源表','pig','admin','2020-04-16 15:13:25',NULL,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36','/dsconf','PUT','','11305','0',NULL),(6,'0','修改数据源表','pig','admin','2020-04-16 15:14:56',NULL,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36','/dsconf','PUT','','36','0',NULL),(7,'0','修改数据源表','pig','admin','2020-04-16 15:15:17',NULL,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36','/dsconf','PUT','','36','0',NULL),(8,'0','修改数据源表','pig','admin','2020-04-16 15:17:42',NULL,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36','/dsconf','PUT','','11278','0',NULL),(9,'0','修改数据源表','pig','admin','2020-04-16 15:18:05',NULL,'127.0.0.1','Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/80.0.3987.163 Safari/537.36','/dsconf','PUT','','35','0',NULL);

/*Table structure for table `sys_menu` */

DROP TABLE IF EXISTS `sys_menu`;

CREATE TABLE `sys_menu` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '菜单ID',
  `name` varchar(32) NOT NULL COMMENT '菜单名称',
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

insert  into `sys_menu`(`id`,`name`,`permission`,`path`,`parent_id`,`icon`,`component`,`sort`,`is_keep_alive`,`type`,`create_time`,`update_time`,`is_del`) values (1000,'权限管理',NULL,'/admin',-1,'icon-quanxianguanli',NULL,0,0,'0',NULL,NULL,0),(1100,'用户管理',NULL,'/admin/user/index',1000,'icon-yonghuguanli',NULL,1,0,'0',NULL,NULL,0),(1101,'用户新增','sys_user_add',NULL,1100,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1102,'用户修改','sys_user_edit',NULL,1100,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1103,'用户删除','sys_user_del',NULL,1100,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1200,'菜单管理',NULL,'/admin/menu/index',1000,'icon-caidanguanli',NULL,2,0,'0',NULL,NULL,0),(1201,'菜单新增','sys_menu_add',NULL,1200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1202,'菜单修改','sys_menu_edit',NULL,1200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1203,'菜单删除','sys_menu_del',NULL,1200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1300,'角色管理',NULL,'/admin/role/index',1000,'icon-jiaoseguanli',NULL,3,0,'0',NULL,NULL,0),(1301,'角色新增','sys_role_add',NULL,1300,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1302,'角色修改','sys_role_edit',NULL,1300,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1303,'角色删除','sys_role_del',NULL,1300,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1304,'分配权限','sys_role_perm',NULL,1300,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1400,'部门管理',NULL,'/admin/dept/index',1000,'icon-web-icon-',NULL,4,0,'0',NULL,NULL,0),(1401,'部门新增','sys_dept_add',NULL,1400,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1402,'部门修改','sys_dept_edit',NULL,1400,NULL,NULL,NULL,0,'1',NULL,NULL,0),(1403,'部门删除','sys_dept_del',NULL,1400,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2000,'系统管理',NULL,'/setting',-1,'icon-xitongguanli',NULL,1,0,'0',NULL,NULL,0),(2100,'日志管理',NULL,'/admin/log/index',2000,'icon-rizhiguanli',NULL,5,0,'0',NULL,NULL,0),(2101,'日志删除','sys_log_del',NULL,2100,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2200,'字典管理',NULL,'/admin/dict/index',2000,'icon-navicon-zdgl',NULL,6,0,'0',NULL,NULL,0),(2201,'字典删除','sys_dict_del',NULL,2200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2202,'字典新增','sys_dict_add',NULL,2200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2203,'字典修改','sys_dict_edit',NULL,2200,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2300,'令牌管理',NULL,'/admin/token/index',2000,'icon-denglvlingpai',NULL,11,0,'0',NULL,NULL,0),(2301,'令牌删除','sys_token_del',NULL,2300,NULL,NULL,1,0,'1',NULL,NULL,0),(2400,'终端管理','','/admin/client/index',2000,'icon-shouji',NULL,9,0,'0',NULL,NULL,0),(2401,'客户端新增','sys_client_add',NULL,2400,'1',NULL,NULL,0,'1',NULL,NULL,0),(2402,'客户端修改','sys_client_edit',NULL,2400,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2403,'客户端删除','sys_client_del',NULL,2400,NULL,NULL,NULL,0,'1',NULL,NULL,0),(2500,'服务监控',NULL,'http://127.0.0.1:5001',2000,'icon-server',NULL,10,0,'0',NULL,NULL,0),(3000,'开发平台',NULL,'/gen',-1,'icon-shejiyukaifa-',NULL,3,1,'0',NULL,NULL,0),(3100,'数据源管理',NULL,'/gen/datasource',3000,'icon-mysql',NULL,1,1,'0',NULL,NULL,0),(3200,'代码生成',NULL,'/gen/index',3000,'icon-weibiaoti46',NULL,2,0,'0',NULL,NULL,0),(3300,'表单管理',NULL,'/gen/form',3000,'icon-record',NULL,3,1,'0',NULL,NULL,0),(3301,'表单新增','gen_form_add',NULL,3300,'',NULL,0,0,'1',NULL,NULL,0),(3302,'表单修改','gen_form_edit',NULL,3300,'',NULL,1,0,'1',NULL,NULL,0),(3303,'表单删除','gen_form_del',NULL,3300,'',NULL,2,0,'1',NULL,NULL,0),(3400,'表单设计',NULL,'/gen/design',3000,'icon-biaodanbiaoqian',NULL,4,1,'0',NULL,NULL,0),(9999,'系统官网',NULL,'https://pig4cloud.com/#/',-1,'icon-guanwangfangwen',NULL,9,0,'0',NULL,NULL,0);

/*Table structure for table `sys_role` */

DROP TABLE IF EXISTS `sys_role`;

CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '角色名',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `create_time` bigint(13) NOT NULL COMMENT '更新时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '更新时间',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '删除标识（0-正常,1-删除）',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci ROW_FORMAT=DYNAMIC COMMENT='系统角色表';

/*Data for the table `sys_role` */

insert  into `sys_role`(`id`,`name`,`remark`,`create_time`,`update_time`,`is_del`) values (1,'ROLE_ADMIN','管理员',20171029154551,20181226140911,0);

/*Table structure for table `sys_role_menu` */

DROP TABLE IF EXISTS `sys_role_menu`;

CREATE TABLE `sys_role_menu` (
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  `menu_id` int(11) NOT NULL COMMENT '菜单ID',
  PRIMARY KEY (`role_id`,`menu_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='角色菜单表';

/*Data for the table `sys_role_menu` */

insert  into `sys_role_menu`(`role_id`,`menu_id`) values (1,1000),(1,1100),(1,1101),(1,1102),(1,1103),(1,1200),(1,1201),(1,1202),(1,1203),(1,1300),(1,1301),(1,1302),(1,1303),(1,1304),(1,1400),(1,1401),(1,1402),(1,1403),(1,2000),(1,2100),(1,2101),(1,2200),(1,2201),(1,2202),(1,2203),(1,2300),(1,2301),(1,2400),(1,2401),(1,2402),(1,2403),(1,2500),(1,3000),(1,3100),(1,3200),(1,3300),(1,3301),(1,3302),(1,3303),(1,3400),(1,9999);

/*Table structure for table `sys_user` */

DROP TABLE IF EXISTS `sys_user`;

CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `username` varchar(64) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '用户名',
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci NOT NULL COMMENT '密码',
  `nickname` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '昵称',
  `phone` varchar(13) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '手机号',
  `avatar` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '头像',
  `remark` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci DEFAULT NULL COMMENT '备注',
  `dept_id` int(11) DEFAULT NULL COMMENT '部门ID',
  `create_time` bigint(13) DEFAULT NULL COMMENT '创建时间',
  `update_time` bigint(13) DEFAULT NULL COMMENT '修改时间',
  `is_lock` tinyint(1) DEFAULT '0' COMMENT '是否锁定',
  `is_del` tinyint(1) DEFAULT '0' COMMENT '是否删除',
  PRIMARY KEY (`id`),
  KEY `user_idx1_username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COLLATE=utf8_bin ROW_FORMAT=DYNAMIC COMMENT='用户表';

/*Data for the table `sys_user` */

insert  into `sys_user`(`id`,`username`,`password`,`nickname`,`phone`,`avatar`,`remark`,`dept_id`,`create_time`,`update_time`,`is_lock`,`is_del`) values (1,'admin','$2a$10$RpFJjxYiXdEsAGnWp/8fsOetMuOON96Ntk/Ym2M/RKRyU0GZseaDC','管理员','17034642999','',NULL,1,NULL,NULL,0,0);

/*Table structure for table `sys_user_role` */

DROP TABLE IF EXISTS `sys_user_role`;

CREATE TABLE `sys_user_role` (
  `user_id` int(11) NOT NULL COMMENT '用户ID',
  `role_id` int(11) NOT NULL COMMENT '角色ID',
  PRIMARY KEY (`user_id`,`role_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC COMMENT='用户角色表';

/*Data for the table `sys_user_role` */

insert  into `sys_user_role`(`user_id`,`role_id`) values (1,1);

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
