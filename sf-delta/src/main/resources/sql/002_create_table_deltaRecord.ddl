CREATE TABLE `deltaRecord` (
  `deltaRecordId`   INT(11) AUTO_INCREMENT,
  `deltaRunId`      INT(11) NOT NULL,
  `deltaRunName`    VARCHAR(128) NOT NULL,
  `dataKeyValue`    VARCHAR(128) NOT NULL,
  `dataHash`        VARCHAR(64) NOT NULL,
  `dataOperation`   VARCHAR(32) NOT NULL,
  `isActive`        INT(11),
  PRIMARY KEY (`deltaRecordId`),
  FOREIGN KEY (`deltaRunId`) REFERENCES deltaRun(`deltaRunId`)
)
  ENGINE = InnoDB
  DEFAULT CHARSET = utf8
  COLLATE = utf8_bin