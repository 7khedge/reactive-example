CREATE TABLE `deltaRun` (
  `id`              INT(11) AUTO_INCREMENT,
  `name`            VARCHAR(128) COLLATE utf8_bin DEFAULT NULL,
  `status`          VARCHAR(45) COLLATE utf8_bin DEFAULT NULL,
  `startDateTime`   DATETIME         DEFAULT NULL,
  `stopDateTime`    DATETIME         DEFAULT NULL,
  PRIMARY KEY (`id`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin
