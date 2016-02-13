CREATE TABLE `deltaRun` (
  `deltaRunId`      INT(11) AUTO_INCREMENT,
  `name`            VARCHAR(128) NOT NULL,
  `dataKey`         VARCHAR(128) NOT NULL,
  `status`          VARCHAR(32) NOT NULL,
  PRIMARY KEY (`deltaRunId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin