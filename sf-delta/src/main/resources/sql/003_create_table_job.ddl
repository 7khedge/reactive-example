CREATE TABLE `job` (
  `jobId`           INT(11) AUTO_INCREMENT,
  `jobName`         VARCHAR(128) NOT NULL,
  `dataKey`    VARCHAR(128) NOT NULL,
  PRIMARY KEY (`jobId`)
 )
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin