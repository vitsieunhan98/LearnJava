-- MySQL Script generated by MySQL Workbench
-- Thu Sep 20 16:21:35 2018
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema hotelreserve
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema hotelreserve
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `hotelreserve` DEFAULT CHARACTER SET utf8 ;
USE `hotelreserve` ;

-- -----------------------------------------------------
-- Table `hotelreserve`.`City`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelreserve`.`City` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` NVARCHAR(255) NOT NULL,
  `ImageLink` VARCHAR(255) NULL,
  PRIMARY KEY (`Id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelreserve`.`Hotel`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelreserve`.`Hotel` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Name` NVARCHAR(255) NOT NULL,
  `Address` NVARCHAR(255) NOT NULL,
  `Rate` INT NULL,
  `ImageLink` VARCHAR(255) NULL,
  `City_Id` INT NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Hotel_City_idx` (`City_Id` ASC),
  CONSTRAINT `fk_Hotel_City`
    FOREIGN KEY (`City_Id`)
    REFERENCES `hotelreserve`.`City` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelreserve`.`TypeRoom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelreserve`.`TypeRoom` (
  `Name` NVARCHAR(255) NOT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelreserve`.`Room`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelreserve`.`Room` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `Number` INT NOT NULL,
  `Price` INT NOT NULL,
  `Hotel_Id` INT NOT NULL,
  `TypeRoom_Name` NVARCHAR(255) NOT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_Room_Hotel1_idx` (`Hotel_Id` ASC),
  INDEX `fk_Room_TypeRoom1_idx` (`TypeRoom_Name` ASC),
  CONSTRAINT `fk_Room_Hotel1`
    FOREIGN KEY (`Hotel_Id`)
    REFERENCES `hotelreserve`.`Hotel` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Room_TypeRoom1`
    FOREIGN KEY (`TypeRoom_Name`)
    REFERENCES `hotelreserve`.`TypeRoom` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelreserve`.`Role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelreserve`.`Role` (
  `Name` VARCHAR(255) NOT NULL,
  PRIMARY KEY (`Name`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelreserve`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelreserve`.`User` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `UserName` VARCHAR(255) NOT NULL,
  `Password` VARCHAR(255) NOT NULL,
  `Role_Name` VARCHAR(255) NOT NULL,
  `FullName` NVARCHAR(255) NULL,
  `Phone` VARCHAR(255) NULL,
  `HotelId` INT NULL,
  PRIMARY KEY (`Id`),
  INDEX `fk_User_Role1_idx` (`Role_Name` ASC),
  CONSTRAINT `fk_User_Role1`
    FOREIGN KEY (`Role_Name`)
    REFERENCES `hotelreserve`.`Role` (`Name`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `hotelreserve`.`Order`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `hotelreserve`.`Order` (
  `Id` INT NOT NULL AUTO_INCREMENT,
  `From` DATE NOT NULL,
  `To` DATE NOT NULL,
  `Room_Id` INT NOT NULL,
  `User_Id` INT NOT NULL,
  PRIMARY KEY (`Id`, `Room_Id`),
  INDEX `fk_Order_Room1_idx` (`Room_Id` ASC),
  INDEX `fk_Order_User1_idx` (`User_Id` ASC),
  CONSTRAINT `fk_Order_Room1`
    FOREIGN KEY (`Room_Id`)
    REFERENCES `hotelreserve`.`Room` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Order_User1`
    FOREIGN KEY (`User_Id`)
    REFERENCES `hotelreserve`.`User` (`Id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;