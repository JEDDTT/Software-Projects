-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`VENDOR`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`VENDOR` (
  `VendorID` INT NOT NULL AUTO_INCREMENT,
  `VendorName` VARCHAR(100) NOT NULL,
  `VendorAddress` VARCHAR(200) NOT NULL,
  `Price` DECIMAL(10,2) NOT NULL,
  PRIMARY KEY (`VendorID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ORDER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ORDER` (
  `OrderID` INT NOT NULL AUTO_INCREMENT,
  `Cost` DECIMAL(10,2) NOT NULL,
  `DateSold` DATE NOT NULL,
  `Quantity` INT NOT NULL,
  `VendorID` INT NULL,
  PRIMARY KEY (`OrderID`),
  INDEX `OrderID` () VISIBLE,
  INDEX `VendorID_idx` (`VendorID` ASC) VISIBLE,
  CONSTRAINT `VendorID`
    FOREIGN KEY (`VendorID`)
    REFERENCES `mydb`.`VENDOR` (`VendorID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ITEM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ITEM` (
  `ItemNum` INT NOT NULL AUTO_INCREMENT,
  `Description` VARCHAR(2000) NOT NULL,
  `ItemType` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`ItemNum`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`ORDERITEM`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`ORDERITEM` (
  `OrderItemID` INT NOT NULL AUTO_INCREMENT,
  `OrderID` INT NULL,
  `ItemNum` INT NULL,
  `ExpectedDate` DATE NULL,
  `ActualDate` DATE NULL,
  PRIMARY KEY (`OrderItemID`),
  INDEX `ItemNum_idx` (`ItemNum` ASC) VISIBLE,
  CONSTRAINT `ItemNum`
    FOREIGN KEY (`ItemNum`)
    REFERENCES `mydb`.`ITEM` (`ItemNum`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`LOCATION`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`LOCATION` (
  `LocationID` INT NOT NULL AUTO_INCREMENT,
  `LocationName` VARCHAR(100) NOT NULL,
  `LocationAddress` VARCHAR(200) NOT NULL,
  PRIMARY KEY (`LocationID`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`INVENTORY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`INVENTORY` (
  `InventoryID` INT NOT NULL AUTO_INCREMENT,
  `QunatityOnHand` INT NOT NULL,
  `InvLocation` VARCHAR(50) NOT NULL,
  `Unit` VARCHAR(50) NOT NULL,
  `LocationID` INT NULL,
  PRIMARY KEY (`InventoryID`),
  INDEX `LocationID_idx` (`LocationID` ASC) VISIBLE,
  CONSTRAINT `LocationID`
    FOREIGN KEY (`LocationID`)
    REFERENCES `mydb`.`LOCATION` (`LocationID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`TRANSFER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`TRANSFER` (
  `TransferID` INT NOT NULL AUTO_INCREMENT,
  `TransferQuantity` INT NOT NULL,
  `ReceivedDate` DATE NOT NULL,
  `InventoryID` INT NULL,
  `ItemNUm` INT NULL,
  PRIMARY KEY (`TransferID`),
  INDEX `ItemNum_idx` (`ItemNUm` ASC) VISIBLE,
  INDEX `InventoryID_idx` (`InventoryID` ASC) VISIBLE,
  CONSTRAINT `ItemNum`
    FOREIGN KEY (`ItemNUm`)
    REFERENCES `mydb`.`ITEM` (`ItemNum`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `InventoryID`
    FOREIGN KEY (`InventoryID`)
    REFERENCES `mydb`.`INVENTORY` (`InventoryID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`CUSTOMER`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`CUSTOMER` (
  `CustID` INT NOT NULL AUTO_INCREMENT,
  `CustNum` INT NOT NULL,
  `CustFirstName` VARCHAR(100) NOT NULL,
  `CustLastName` VARCHAR(100) NOT NULL,
  `CustPhoneNum` INT NULL,
  `CustAddress` VARCHAR(200) NULL,
  PRIMARY KEY (`CustID`),
  UNIQUE INDEX `CustNum_UNIQUE` (`CustNum` ASC) VISIBLE,
  UNIQUE INDEX `CustPhoneNumb_UNIQUE` (`CustPhoneNum` ASC) VISIBLE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DELIVERY`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DELIVERY` (
  `DeliveryID` INT NOT NULL AUTO_INCREMENT,
  `CustID` INT NULL,
  `PurshaseDate` DATE NOT NULL,
  PRIMARY KEY (`DeliveryID`),
  INDEX `CustID_idx` (`CustID` ASC) VISIBLE,
  CONSTRAINT `CustID`
    FOREIGN KEY (`CustID`)
    REFERENCES `mydb`.`CUSTOMER` (`CustID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DELIVERYDETAIL`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DELIVERYDETAIL` (
  `DeliveryDetailID` INT NOT NULL AUTO_INCREMENT,
  `DeliveryQuantity` INT NOT NULL,
  `ExpectedDate` DATE NOT NULL,
  `ActualDate` DATE NOT NULL,
  `DeliveryID` INT NOT NULL,
  `InventoryID` INT NOT NULL,
  PRIMARY KEY (`DeliveryDetailID`),
  INDEX `DeliveryID_idx` (`DeliveryID` ASC) VISIBLE,
  INDEX `InventoryID_idx` (`InventoryID` ASC) VISIBLE,
  CONSTRAINT `DeliveryID`
    FOREIGN KEY (`DeliveryID`)
    REFERENCES `mydb`.`DELIVERY` (`DeliveryID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `InventoryID`
    FOREIGN KEY (`InventoryID`)
    REFERENCES `mydb`.`INVENTORY` (`InventoryID`)
    ON DELETE CASCADE
    ON UPDATE CASCADE)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
