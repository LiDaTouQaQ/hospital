/*
SQLyog Ultimate v12.09 (64 bit)
MySQL - 8.0.32 : Database - database1
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`database1` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `database1`;

/*Table structure for table `case_table` */

DROP TABLE IF EXISTS `case_table`;

CREATE TABLE `case_table` (
  `case_id` int NOT NULL AUTO_INCREMENT,
  `case_p_id` varchar(20) NOT NULL,
  `case_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  `past_disease` varchar(200) DEFAULT NULL,
  `diagnosis` varchar(200) DEFAULT '暂无记录',
  `opinion` varchar(200) DEFAULT '暂无记录',
  `cost` int DEFAULT NULL,
  `case_state` varchar(20) DEFAULT '未缴费',
  `case_d_id` varchar(20) DEFAULT NULL,
  `opinion_number` varchar(20) DEFAULT '0',
  `operation` varchar(100) DEFAULT '无手术安排',
  `medicine_state` varchar(20) DEFAULT '未取药',
  PRIMARY KEY (`case_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1021 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `case_table` */

insert  into `case_table`(`case_id`,`case_p_id`,`case_time`,`past_disease`,`diagnosis`,`opinion`,`cost`,`case_state`,`case_d_id`,`opinion_number`,`operation`,`medicine_state`) values (1010,'3208','2023-06-15 16:25:06',NULL,'暂无记录','暂无记录',NULL,'未缴费','12121','0','无手术安排','未取药'),(1011,'dada','2023-06-15 16:40:51',NULL,'暂无记录','暂无记录',NULL,'未缴费','12121','0','无手术安排','未取药'),(1019,'320401','2023-06-16 09:00:22','bbb','肚子疼','罗红霉素',275,'已缴费','测试医生','3','area: 4444 time: 2023-06-23','已取药'),(1020,'320401','2023-06-16 09:02:42',NULL,'暂无记录','暂无记录',NULL,'未缴费','测试医生','0','无手术安排','未取药');

/*Table structure for table `charge_table` */

DROP TABLE IF EXISTS `charge_table`;

CREATE TABLE `charge_table` (
  `c_id` varchar(20) NOT NULL,
  `c_name` varchar(20) NOT NULL,
  `c_tel` varchar(11) NOT NULL,
  `c_psw` varchar(20) NOT NULL,
  PRIMARY KEY (`c_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `charge_table` */

insert  into `charge_table`(`c_id`,`c_name`,`c_tel`,`c_psw`) values ('114422','大男孩','12341','111'),('320802','测试收费','183520','111'),('320844','测试收费','135124','111');

/*Table structure for table `d_table` */

DROP TABLE IF EXISTS `d_table`;

CREATE TABLE `d_table` (
  `d_id` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `d_name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `d_introduction` varchar(200) DEFAULT NULL,
  `d_surgery` varchar(20) DEFAULT '无科室',
  `d_tel` varchar(11) NOT NULL,
  `d_psw` varchar(20) NOT NULL,
  PRIMARY KEY (`d_tel`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `d_table` */

insert  into `d_table`(`d_id`,`d_name`,`d_introduction`,`d_surgery`,`d_tel`,`d_psw`) values ('12113','小飞',NULL,'无科室','12113','111'),('112121','12121','好医生是试出来的','儿童科','12121','111'),('',NULL,NULL,'无科室','121212','111'),('112234','大飞','还咋努力实习中','精神科','12136','111'),('122','111','1111344','耳鼻喉','123','111'),('1234','lidatou','很厉害','精神科','1234','111'),('320802','测试医生','测试内容','测试科室','135123','111'),('320804','医生1','测试医生','测试科','189520','111');

/*Table structure for table `disease_table` */

DROP TABLE IF EXISTS `disease_table`;

CREATE TABLE `disease_table` (
  `disease_id` int NOT NULL AUTO_INCREMENT,
  `disease_name` varchar(20) NOT NULL DEFAULT '疾病名字',
  `disease_type` varchar(20) NOT NULL DEFAULT '疾病类型',
  `disease_introduction` varchar(200) NOT NULL DEFAULT '疾病介绍',
  PRIMARY KEY (`disease_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1002 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `disease_table` */

insert  into `disease_table`(`disease_id`,`disease_name`,`disease_type`,`disease_introduction`) values (1001,'发烧','综合性疾病','身体发热，头疼，鼻涕等症状');

/*Table structure for table `g_table` */

DROP TABLE IF EXISTS `g_table`;

CREATE TABLE `g_table` (
  `g_id` varchar(10) NOT NULL,
  `g_name` varchar(20) NOT NULL,
  `g_type` varchar(20) NOT NULL,
  `g_price` varchar(10) NOT NULL,
  `g_number` varchar(10) NOT NULL,
  `g_brief` varchar(50) NOT NULL,
  `g_date` varchar(20) NOT NULL,
  PRIMARY KEY (`g_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `g_table` */

insert  into `g_table`(`g_id`,`g_name`,`g_type`,`g_price`,`g_number`,`g_brief`,`g_date`) values ('1','阿莫西林','处方药','12','198','消炎使用','2023-06-30'),('2','阿司匹林','非处方药','18','200','治疗抑郁','2023-06-30'),('3','青霉素','非处方药','20','200','伤口专用','2023-06-30'),('4','罗红霉素','非处方药','25','194','强效消炎药','2023-06-30'),('5','青蒿素','非处方药','67','196','很强中国药品','2023-06-30'),('6','布洛芬','非处方药','100','163','强效止疼药','2023-06-30');

/*Table structure for table `p_table` */

DROP TABLE IF EXISTS `p_table`;

CREATE TABLE `p_table` (
  `p_id` varchar(20) NOT NULL,
  `p_name` varchar(20) NOT NULL,
  `p_gender` varchar(2) DEFAULT NULL,
  `p_age` varchar(3) DEFAULT NULL,
  `p_nation` varchar(12) DEFAULT NULL,
  `p_tel` varchar(11) NOT NULL,
  `p_psw` varchar(20) NOT NULL,
  PRIMARY KEY (`p_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `p_table` */

insert  into `p_table`(`p_id`,`p_name`,`p_gender`,`p_age`,`p_nation`,`p_tel`,`p_psw`) values ('11111','lidatou',NULL,NULL,NULL,'1111','123'),('11233','小飞',NULL,NULL,NULL,'11233','111'),('1222','wangergou','女','20','维吾尔族','1233','1234'),('1239','好人','女','22','非洲','139','111'),('320401','测试病人','女','20','汉族','135152','111'),('3208','病人1',NULL,NULL,NULL,'187520','111'),('dada','ddee','女','20','汉族','1145','111'),('lili','lida','男','12','汉','1234567','111');

/*Table structure for table `pharmacy_table` */

DROP TABLE IF EXISTS `pharmacy_table`;

CREATE TABLE `pharmacy_table` (
  `ph_id` varchar(20) NOT NULL,
  `ph_name` varchar(20) NOT NULL,
  `ph_tel` varchar(11) NOT NULL,
  `ph_psw` varchar(20) NOT NULL,
  PRIMARY KEY (`ph_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `pharmacy_table` */

insert  into `pharmacy_table`(`ph_id`,`ph_name`,`ph_tel`,`ph_psw`) values ('320821','测试药房','135111','111'),('8888','药房人员','8888','111');

/*Table structure for table `r_table` */

DROP TABLE IF EXISTS `r_table`;

CREATE TABLE `r_table` (
  `r_time` datetime NOT NULL,
  `r_d_id` varchar(20) NOT NULL,
  `r_p_id` varchar(20) NOT NULL,
  `r_surgery` varchar(12) NOT NULL,
  PRIMARY KEY (`r_time`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `r_table` */

insert  into `r_table`(`r_time`,`r_d_id`,`r_p_id`,`r_surgery`) values ('2012-12-01 00:00:00','1222','1222','小病科'),('2023-06-14 12:08:00','12121','lili','眼药水科'),('2023-06-14 14:08:00','12121','dada','眼药水科'),('2023-06-15 11:47:49','12121','ddee ','小病科'),('2023-06-15 12:05:35','12121','1222','小病科'),('2023-06-15 12:24:47','111','1239','耳鼻喉'),('2023-06-15 13:06:50','111','5000','耳鼻喉'),('2023-06-15 13:06:52','111','5000','耳鼻喉'),('2023-06-15 13:48:23','大飞','11233','精神科'),('2023-06-15 13:48:29','大飞','11233','精神科'),('2023-06-15 16:22:40','医生1','3208','测试科'),('2023-06-15 16:25:06','12121','3208','儿童科'),('2023-06-15 16:40:51','12121','dada','儿童科'),('2023-06-16 09:00:22','测试医生','320401','测试科室'),('2023-06-16 09:02:42','测试医生','320401','测试科室');

/*Table structure for table `schedule_table` */

DROP TABLE IF EXISTS `schedule_table`;

CREATE TABLE `schedule_table` (
  `s_date` varchar(40) NOT NULL,
  `s_surgery` varchar(12) NOT NULL,
  `s_name` varchar(20) NOT NULL,
  `s_time` varchar(40) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

/*Data for the table `schedule_table` */

insert  into `schedule_table`(`s_date`,`s_surgery`,`s_name`,`s_time`) values ('2023-06-16','儿童科','12121','8:00-12:00'),('2023-06-16','精神科','大飞','12:00-14:00'),('2023-06-16','测试科室','测试医生','14:00-18:00'),('2023-06-16','耳鼻喉','111','18:00-22:00'),('2023-06-16','精神科','lidatou','22:00-2:00'),('2023-06-16','测试科','医生1','8:00-12:00'),('2023-06-16','无科室','小飞','12:00-14:00');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
