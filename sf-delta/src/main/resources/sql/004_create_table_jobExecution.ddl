CREATE TABLE `jobExecution` (
  `jobExecutionId`  INT(11) AUTO_INCREMENT,
  `jobId`           INT(11) NOT NULL,
  `properties`      JSON NOT NULL,
  `startDateTime`   DATETIME NOT NULL,
  `stopDateTime`    DATETIME,
  `status`          VARCHAR(32),
  `exitMessage`     VARCHAR(2048),
PRIMARY KEY (`jobExecutionId`),
FOREIGN KEY (`jobId`) REFERENCES job(`jobId`)
)
ENGINE = InnoDB
DEFAULT CHARSET = utf8
COLLATE = utf8_bin