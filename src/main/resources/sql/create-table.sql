DROP TABLE IF EXISTS `note`;
CREATE TABLE `note` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '物理ID',
  `name` VARCHAR(255) NOT NULL COMMENT '标题',
  `url` VARCHAR(255) NOT NULL COMMENT '地址',
  `author` VARCHAR(32) COMMENT '作者',
  `note_type` VARCHAR(32) COMMENT '类型',
  `last_update_time` VARCHAR(32) COMMENT '更新时间',
  `last_section` VARCHAR(128) COMMENT '最新章节',
  `path` VARCHAR(255) COMMENT '本地保存地址',
  `version` INT(11) DEFAULT 0 COMMENT '版本号',
  `insert_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `insert_user` INT(11) DEFAULT NULL COMMENT '创建人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` INT(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='小说信息';

DROP TABLE IF EXISTS `section`;
CREATE TABLE `section` (
  `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '物理ID',
  `note_id` INT(11) NOT NULL COMMENT '小说物理ID',
  `name` VARCHAR(255) NOT NULL COMMENT '标题',
  `url` VARCHAR(255) NOT NULL COMMENT '地址',
  `path` VARCHAR(255) COMMENT '本地保存地址',
  `section_index` INT(8) NOT NULL COMMENT '序号',
  `version` INT(11) DEFAULT 0 COMMENT '版本号',
  `insert_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `insert_user` INT(11) DEFAULT NULL COMMENT '创建人',
  `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_user` INT(11) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='章节信息';

DROP TABLE IF EXISTS `client_record`;
CREATE TABLE `client_record` (
    `id` INT(11) NOT NULL AUTO_INCREMENT COMMENT '物理ID',
    `note_id` INT(11) NOT NULL COMMENT '小说物理ID',
    `section_id` INT(11) NOT NULL COMMENT '最后阅读章节ID',
    `imei` VARCHAR(255) COMMENT '客户端标识符',
    `version` INT(11) DEFAULT 0 COMMENT '版本号',
    `insert_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `insert_user` INT(11) DEFAULT NULL COMMENT '创建人',
    `update_time` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_user` INT(11) DEFAULT NULL COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='客户端阅读记录';

