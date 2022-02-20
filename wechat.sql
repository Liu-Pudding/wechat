-- -------------------------------------------------------------
-- TablePlus 3.11.0(352)
--
-- https://tableplus.com/
--
-- Database: wechat
-- Generation Time: 2022-02-20 17:41:54.0960
-- -------------------------------------------------------------


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


CREATE TABLE `jd_order` (
  `id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标记唯一订单行：订单+sku维度的唯一标识',
  `order_id` bigint(20) DEFAULT NULL COMMENT '订单号',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父单的订单号',
  `order_time` datetime DEFAULT NULL COMMENT '下单时间',
  `finish_time` datetime DEFAULT NULL COMMENT '完成时间',
  `plus` tinyint(1) DEFAULT NULL COMMENT '下单用户是否为PLUS会员',
  `sku_id` bigint(20) DEFAULT NULL COMMENT '商品id',
  `sku_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '商品名称',
  `sku_num` int(11) DEFAULT NULL COMMENT '商品数量',
  `price` double(10,2) DEFAULT NULL COMMENT '单价',
  `commission_rate` double(10,2) DEFAULT NULL COMMENT '佣金比例(投放的广告主计划比例)',
  `sub_side_rate` double(10,2) DEFAULT NULL COMMENT '分成比例（单位：%）',
  `subsidy_rate` double(10,2) DEFAULT NULL COMMENT '补贴比例（单位：%）',
  `final_rate` double(10,2) DEFAULT NULL COMMENT '最终分佣比例（单位：%）=分成比例+补贴比例',
  `estimate_cos_price` double(10,2) DEFAULT NULL COMMENT '预估计佣金额',
  `estimate_fee` double(10,2) DEFAULT NULL COMMENT '推客的预估佣金（预估计佣金额*佣金比例*最终比例），如订单完成前发生退款，此金额也会更新。',
  `actual_cos_price` double(10,2) DEFAULT NULL COMMENT '实际计算佣金的金额。订单完成后，会将误扣除的运费券金额更正。如订单完成后发生退款，此金额会更新。',
  `actual_fee` double(10,2) DEFAULT NULL COMMENT '推客分得的实际佣金（实际计佣金额*佣金比例*最终比例）。如订单完成后发生退款，此金额会更新。',
  `pay_month` varchar(25) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '预估结算时间，订单完成后才会返回，格式：yyyyMMdd，默认：0。表示最新的预估结算日期。当payMonth为当前的未来时间时，表示该订单可结算；当payMonth为当前的过去时间时，表示该订单已结算',
  `position_id` int(11) DEFAULT NULL COMMENT '推广位ID',
  `sub_unionId` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL COMMENT '子渠道标识，在转链时可自定义传入',
  `valid_code` int(11) DEFAULT NULL COMMENT 'sku维度的有效码（-1：未知,2.无效-拆单,3.无效-取消,4.无效-京东帮帮主订单,5.无效-账号异常,6.无效-赠品类目不返佣,7.无效-校园订单,8.无效-企业订单,9.无效-团购订单,11.无效-乡村推广员下单,13.无效-违规订单,14.无效-来源与备案网址不符,15.待付款,16.已付款,17.已完成（购买用户确认收货）,19.无效-佣金比例为0,20.无效-此复购订单对应的首购订单无效,21.无效-云店订单，22.无效-PLUS会员佣金比例为0，23.无效-支付有礼，24.已付定金',
  PRIMARY KEY (`id`) USING BTREE,
  KEY `idx_sub_unionId` (`sub_unionId`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `open_id` varchar(50) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '微信openId',
  `jd_pin` varchar(20) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '京东唯一识别码',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `idx_open_id` (`open_id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

CREATE TABLE `wallet` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `balance` double NOT NULL DEFAULT '0',
  `update_time` datetime NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;



/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;