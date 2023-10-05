USE chat_bi;
DROP TABLE IF EXISTS `scheam_meta_info`;
CREATE TABLE `scheam_meta_info` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `meta_name` varchar(64) NOT NULL COMMENT '维度，指标名称',
  `meta_zh_name` varchar(128) NOT NULL COMMENT '维度，指标中文名称',
  `meta_type` int(1) NOT NULL DEFAULT '1' COMMENT '维度1，指标2',
  `meta_expression` varchar(128) DEFAULT NULL COMMENT '维度，指标表达式',
  `meta_table_name` varchar(64) DEFAULT NULL COMMENT '维度，指标来源表名称',
  `meta_state` int(1) NOT NULL DEFAULT '0' COMMENT '操作类型，0:新增，1:更新，2:删除',
  `create_time` timestamp NULL DEFAULT NULL COMMENT '创建时间',
  `update_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='维度指标元数据信息表';
