---- drop ----
DROP TABLE IF EXISTS `notifications`;

---- create ----
create table IF not exists `notifications`
(
 `id`               INT(20) AUTO_INCREMENT,
 `title`            VARCHAR(255) NOT NULL,
 `url`              VARCHAR(255) NOT NULL,
 `content`          VARCHAR(255) NOT NULL,
 `publish_datetime` Datetime DEFAULT NULL,
 `expired_datetime` Datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8 COLLATE=utf8_bin;