-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema enjoytrip
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `enjoytrip` DEFAULT CHARACTER SET utf8mb3 ;
USE `enjoytrip` ;

-- -----------------------------------------------------
-- Table `enjoytrip`.`user`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`user` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`user` (
  `uid` INT NOT NULL AUTO_INCREMENT,
  `id` VARCHAR(45) NOT NULL,
  `pwd` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `isadmin` TINYINT NOT NULL DEFAULT '0',
  `token` VARCHAR(1000) NULL,
  `image` VARCHAR(1000) NULL,
  PRIMARY KEY (`uid`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`board`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`board` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`board` (
  `boardid` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `title` VARCHAR(300) NOT NULL,
  `content` MEDIUMTEXT NOT NULL,
  `date` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `hit` INT NOT NULL DEFAULT 0,
  `isNotice` TINYINT NOT NULL DEFAULT 0,
  PRIMARY KEY (`boardid`),
  INDEX `board_user_uid_fk_idx` (`uid` ASC) VISIBLE,
  CONSTRAINT `board_user_uid_fk`
    FOREIGN KEY (`uid`)
    REFERENCES `enjoytrip`.`user` (`uid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`spot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`spot` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`spot` (
  `spotid` INT NOT NULL AUTO_INCREMENT,
  `title` VARCHAR(100) NULL DEFAULT NULL,
  `address` VARCHAR(100) NULL DEFAULT NULL,
  `tel` VARCHAR(100) NULL DEFAULT NULL,
  `image` VARCHAR(100) NULL DEFAULT NULL,
  `hit` INT NULL DEFAULT NULL,
  `theme` VARCHAR(100) NULL DEFAULT NULL,
  `contentid` VARCHAR(100) NOT NULL,
  `lang` VARCHAR(100) NULL DEFAULT NULL,
  `lat` VARCHAR(100) NULL DEFAULT NULL,
  `sidoid` VARCHAR(100) NULL DEFAULT NULL,
  `gugunid` VARCHAR(100) NULL DEFAULT NULL,
  PRIMARY KEY (`spotid`),
  UNIQUE INDEX `contentid_UNIQUE` (`contentid` ASC) VISIBLE)
ENGINE = InnoDB
AUTO_INCREMENT = 36438
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`description`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`description` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`description` (
  `descid` INT NOT NULL AUTO_INCREMENT,
  `contentid` VARCHAR(100) NOT NULL,
  `desc` MEDIUMTEXT NULL DEFAULT NULL,
  PRIMARY KEY (`descid`),
  INDEX `description_spot_spotid_fk` (`contentid` ASC) VISIBLE,
  CONSTRAINT `description_spot_spotid_fk`
    FOREIGN KEY (`contentid`)
    REFERENCES `enjoytrip`.`spot` (`contentid`))
ENGINE = InnoDB
AUTO_INCREMENT = 36220
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`sido`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`sido` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`sido` (
  `sidoid` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`sidoid`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`gugun`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`gugun` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`gugun` (
  `idx` INT NOT NULL AUTO_INCREMENT,
  `gugunid` INT NOT NULL,
  `sidoid` INT NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`idx`),
  INDEX `gugun_sido_sidoid_fk_idx` (`sidoid` ASC) VISIBLE,
  CONSTRAINT `gugun_sido_sidoid_fk`
    FOREIGN KEY (`sidoid`)
    REFERENCES `enjoytrip`.`sido` (`sidoid`))
ENGINE = InnoDB
AUTO_INCREMENT = 235
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`schedulespot`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`schedulespot` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`schedulespot` (
  `ssid` INT NOT NULL AUTO_INCREMENT,
  `boardid` INT NOT NULL,
  `spotid` INT NOT NULL,
  `memo` MEDIUMTEXT NULL,
  PRIMARY KEY (`ssid`),
  INDEX `schedulespot_spot_spotid_fk_idx` (`spotid` ASC) VISIBLE,
  INDEX `schedulespot_board_boardid_fk_idx` (`boardid` ASC) VISIBLE,
  CONSTRAINT `schedulespot_spot_spotid_fk`
    FOREIGN KEY (`spotid`)
    REFERENCES `enjoytrip`.`spot` (`spotid`),
  CONSTRAINT `schedulespot_board_boardid_fk`
    FOREIGN KEY (`boardid`)
    REFERENCES `enjoytrip`.`board` (`boardid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `enjoytrip`.`hotplace`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `enjoytrip`.`hotplace` ;

CREATE TABLE IF NOT EXISTS `enjoytrip`.`hotplace` (
  `hotplaceid` INT NOT NULL AUTO_INCREMENT,
  `uid` INT NOT NULL,
  `title` VARCHAR(100) NOT NULL,
  `image` VARCHAR(200) NOT NULL,
  `desc` VARCHAR(200) NULL,
  PRIMARY KEY (`hotplaceid`),
  INDEX `hotplace_user_userid_fk_idx` (`uid` ASC) VISIBLE,
  CONSTRAINT `hotplace_user_userid_fk`
    FOREIGN KEY (`uid`)
    REFERENCES `enjoytrip`.`user` (`uid`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
