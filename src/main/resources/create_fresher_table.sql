CREATE TABLE `candidate_db`.`fresher` (
  `id` VARCHAR(50) NOT NULL,
  `name` VARCHAR(512) NULL,
  `birthday` DATETIME NULL,
  `phone` VARCHAR(10) NULL,
  `email` VARCHAR(512) NULL,
  `type` VARCHAR(255) NULL,
  `gradutation` DATETIME NULL,
  `rank` VARCHAR(255) NULL,
  `university` VARCHAR(45) NULL,
  PRIMARY KEY (`id`),
UNIQUE INDEX `email_UNIQUE` (`email` ASC));
