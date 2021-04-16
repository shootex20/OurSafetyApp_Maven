/* Boxuan Lu, Wenhao Liu, Daniel Quach, Azeb Amare, Parleen Deol */ 

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

DROP SCHEMA IF EXISTS `oursafetydb` ;

CREATE SCHEMA IF NOT EXISTS `oursafetydb` DEFAULT CHARACTER SET latin1;
USE `oursafetydb` ;

CREATE TABLE IF NOT EXISTS `oursafetydb`.`typeLibrary` (
`typeLibrary_ID` int AUTO_INCREMENT,
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
/* Come back to this */ 
`type` varchar(30), 
`description` varchar(55), 
`isCategory` CHAR, /*  T being true, F being false.*/ 
PRIMARY KEY (`typeLibrary_ID`)) 
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`company` ( 
`company_ID` int AUTO_INCREMENT, 
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`shortname` VARCHAR(30), 
`name` VARCHAR(100), 
`description` VARCHAR(255), 
`saltHash` VARCHAR(255), 
`account` VARCHAR(100), 
`industry` VARCHAR(30),  
PRIMARY KEY (`company_ID`)) 
ENGINE = InnoDB; 


CREATE TABLE IF NOT EXISTS `oursafetydb`.`person` ( 
`person_ID` int AUTO_INCREMENT, /*PK*/  
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`firstName` VARCHAR(255), 
`lastName` VARCHAR(255), 
`dateOfBirth` date, 
`gender` CHAR(1),
`emergencyContact_ID` int,  
PRIMARY KEY (`person_ID`), 
INDEX `person_emergencyContact_id_fk_idx` (`emergencyContact_ID` ASC),
CONSTRAINT `fk_person_emergencyContact_id`
    FOREIGN KEY (`emergencyContact_ID`)
    REFERENCES `oursafetydb`.`emergencyContact` (`emergencyContact_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`companyPerson` ( 
`companyPerson_ID` int AUTO_INCREMENT, /*PK*/ 
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`company_ID` int, /*FK*/ 
`person_ID` int, /*FK*/ 
`email` VARCHAR(60),
`isEmployeeActive` BIT NOT NULL, 
PRIMARY KEY (`companyPerson_ID`), 
INDEX `companyPerson_company_id_fk_idx` (`company_ID` ASC),
INDEX `companyPerson_person_id_fk_idx` (`person_ID` ASC),
CONSTRAINT `fk_companyPerson_company_id`
    FOREIGN KEY (`company_ID`)
    REFERENCES `oursafetydb`.`company` (`company_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_companyPerson_person_id`
    FOREIGN KEY (`person_ID`)
    REFERENCES `oursafetydb`.`person` (`person_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


/*Can use either person_id OR companyPerson_ID, this will need to be decided upon.*/
CREATE TABLE IF NOT EXISTS `oursafetydb`.`logins` (
`user_id` int AUTO_INCREMENT, 
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`username` varchar(30), 
`password` varchar(200), 
`company_ID` int, 
`isActive` CHAR, 
`isAdmin` CHAR, 
PRIMARY KEY (`user_ID`), 
INDEX `logins_company_id_fk_idx` (`company_ID` ASC), 
CONSTRAINT `logins_company_id_fk` 
    FOREIGN KEY (`company_ID`) 
    REFERENCES `oursafetydb`.`company` (`company_ID`) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`url` ( 
`url_ID` int AUTO_INCREMENT,
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`typeLibrary_ID`  int, 
`URL` VARCHAR(255), 
`company_ID` int, 
PRIMARY KEY (`url_ID`), 
INDEX `fk_url_typelibrary_idx` (`typeLibrary_ID` ASC),
INDEX `fk_url_company_idx` (`company_ID` ASC),
CONSTRAINT `fk_url_typelibrary`
    FOREIGN KEY (`typeLibrary_ID`)
    REFERENCES `oursafetydb`.`typeLibrary` (`typeLibrary_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_url_company`
    FOREIGN KEY (`company_ID`)
    REFERENCES `oursafetydb`.`company` (`company_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`manual` ( 
`manual_ID` int AUTO_INCREMENT,
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`typeLibrary_ID` int,
`parent` int,
`title` VARCHAR(30), 
`intention` VARCHAR(30), 
`overview` VARCHAR(30), 
`content` VARCHAR(30), 
PRIMARY KEY (`manual_ID`), 
INDEX `fk_manual_typelibrary_idx` (`typeLibrary_ID` ASC),
CONSTRAINT `fk_manual_typelibrary`
    FOREIGN KEY (`typeLibrary_ID`)
    REFERENCES `oursafetydb`.`typeLibrary` (`typeLibrary_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB; 


CREATE TABLE IF NOT EXISTS `oursafetydb`.`manualUse` ( 
`manualUse_ID` int AUTO_INCREMENT,
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`manual_ID` int,
`company_ID` int, 
`companyRole` int, 
`job` int, 
PRIMARY KEY (`manualUse_ID`), 
INDEX `fk_manualUse_manual_idx` (`manual_ID` ASC),
CONSTRAINT `fk_manualUse_manual`
    FOREIGN KEY (`manual_ID`)
    REFERENCES `oursafetydb`.`manual` (`manual_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`itemClassFields` (
`itemClassFields_ID` int AUTO_INCREMENT,
`dateAdded`  date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved`  int, 
`typeLibrary_ID`  int, 
`fieldDescr`  VARCHAR(30), 
`fieldDescrType` VARCHAR(30),
PRIMARY KEY(`itemClassFields_ID`), 
INDEX `fk_itemClassFields_typelibrary_idx` (`typeLibrary_ID` ASC),
CONSTRAINT `fk_itemClassFields_typelibrary`
    FOREIGN KEY (`typeLibrary_ID`)
    REFERENCES `oursafetydb`.`typeLibrary` (`typeLibrary_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`itemClass` ( 
`itemClass_ID` int AUTO_INCREMENT, 
`DateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`itemType` VARCHAR (50), 
`itemClassFields_ID` int, /*FK*/ 
`itemClassInformation` VARCHAR(100),
PRIMARY KEY (`ItemClass_ID`), 
INDEX `fk_itemClass_itemClassFields_idx` (`itemClassFields_ID` ASC), 
CONSTRAINT `fk_itemClass_itemClassFields_ID` 
    FOREIGN KEY(`itemClassFields_ID`) 
    REFERENCES `oursafetydb`.`itemClassFields` (`itemClassFields_ID`) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION)
ENGINE = InnoDB;




CREATE TABLE IF NOT EXISTS `oursafetydb`.`companyPersonPhone` ( 
`companyPersonPhone_ID` int AUTO_INCREMENT, 
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`companyPerson_ID` int, 
`phone_ID` int, 
PRIMARY KEY (`companyPersonPhone_ID`), 
INDEX `fk_companyPersonPhone_CompanyPerson_idx` (`companyPerson_ID` ASC),
INDEX `fk_companyPersonPhone_Phone_idx` (`phone_ID` ASC),
CONSTRAINT `fk_companypersonphone_companypersonid`
    FOREIGN KEY(`companyPerson_ID`)
    REFERENCES `oursafetydb`.`companyPerson` (`companyPerson_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_companypersonphone_phone`
    FOREIGN KEY(`phone_ID`)
    REFERENCES `oursafetydb`.`phone` (`phone_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`item` ( 
`item_ID` int AUTO_INCREMENT, /*PK*/ 
`dateAdded` date, 
`DateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`itemClass_ID` int, /*FK*/ 
`model` VARCHAR(30), 
`company_ID` int, /*FK*/ 
`isChargeableType` BOOLEAN, 
`isDepletingType` BOOLEAN, 
`isDepreactiationType` BOOLEAN, 
`itemClassInformation` VARCHAR(30),
`serialNumber` VARCHAR(55), 
`purchaseDate` date,
PRIMARY KEY (`item_ID`), 
INDEX `fk_item_companyID` (`company_ID` ASC),
INDEX `fk_item_itemClass` (`itemClass_ID` ASC),
CONSTRAINT `fk_company_companyID`
    FOREIGN KEY (`company_ID`)
    REFERENCES `oursafetydb`.`company` (`company_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `fk_itemClass_itemClass_ID`
    FOREIGN KEY (`itemClass_ID`)
    REFERENCES `oursafetydb`.`itemClass` (`itemClass_ID`)
    on DELETE NO ACTION
    ON UPDATE NO ACTION
)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`phone` ( 
`phone_ID` int AUTO_INCREMENT,
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`typeLibrary_ID` int,
`countryCode` VARCHAR(10), 
`areaCode` VARCHAR(10), 
`localNumber` VARCHAR(10), 
`extension` VARCHAR(10), 
 PRIMARY KEY (`phone_ID`), 
INDEX `fk_phone_typelibrary_id_idx` (`typeLibrary_ID`),
CONSTRAINT `phone_typeLibrary_id_fk` 
    FOREIGN KEY (`typeLibrary_ID`) 
    REFERENCES `oursafetydb`.`typeLibrary` (`typeLibrary_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`companyRelationship` (
`companyRelationship_ID` int AUTO_INCREMENT, /*PK*/ 
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`parent` int, 
`child` int,  
`typeLibrary_ID` int, 
`company_ID` int,
PRIMARY KEY (`companyRelationship_ID`), 
INDEX `companyRelationship_typeLibrary_ID_fk _idx` (`typeLibrary_ID` ASC), 
INDEX `companyRelationship_company_ID_fk _idx` (`company_ID` ASC), 
CONSTRAINT `companyRelationship_typeLibrary_ID_fk`
    FOREIGN KEY (`typeLibrary_ID`)
    REFERENCES `oursafetydb`.`typeLibrary` (`typeLibrary_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
CONSTRAINT `companyRelationship_company_ID_fk`
    FOREIGN KEY (`company_ID`)
    REFERENCES `oursafetydb`.`company` (`company_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB; 


CREATE TABLE IF NOT EXISTS `oursafetydb`.`emergencyContact` ( 
`emergencyContact_ID` int AUTO_INCREMENT, /*PK*/ 
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int,
`emergencyContactFirstName` VARCHAR(30),
`emergencyContactLastName` VARCHAR(30),
`emergencyContactNumber` VARCHAR(30),
`emergencyContactRelationship` VARCHAR(20),
PRIMARY KEY(`emergencyContact_ID`))
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `oursafetydb`.`address` ( 
`address_ID`  int AUTO_INCREMENT, 
`dateAdded`  date, 
`dateRemoved`  date, 
`userAdded`  int, 
`userRemoved`  int, 
`typeLibrary_ID`  int, 
`addressLine1`  VARCHAR(200), 
`addressLine2`  VARCHAR(200),
`city` VARCHAR(100),
`province` VARCHAR(20),
`country` VARCHAR(100),
`postalCode`  VARCHAR(6), 
PRIMARY KEY(`address_ID`), 
INDEX `fk_address_typelibrary_idx` (`typeLibrary_ID` ASC), 
CONSTRAINT `fk_address_typelibrary_ID` 
    FOREIGN KEY (`typeLibrary_ID`) 
    REFERENCES `oursafetydb`.`typeLibrary` (`typeLibrary_ID`) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`companyPersonAddress` ( 
`companyPersonAddress_ID` int AUTO_INCREMENT,  
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`companyPerson_ID` int, 
`address_ID` int, 
PRIMARY KEY (`companyPersonAddress_ID`), 
INDEX `fk_companyPersonAddress_companyPerson_idx` (`companyPerson_ID` ASC), 
INDEX `fk_companyPersonAddress_address_ID_idx` (`address_ID` ASC),
CONSTRAINT `fk_companyPersonAddress_companyPerson` 
    FOREIGN KEY (`companyPerson_ID`) 
    REFERENCES `oursafetydb`.`companyPerson` (`companyPerson_ID`)
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION,
CONSTRAINT `fk_companyPersonAddress_address_ID`
    FOREIGN KEY (`address_ID`)
    REFERENCES `oursafetydb`.`address` (`address_ID`) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
 
 
CREATE TABLE IF NOT EXISTS `oursafetydb`.`companyNotes` ( 
`companyNotes_ID` int AUTO_INCREMENT, /*PK*/ 
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`companyPerson_ID` int,  /*FK*/ 
`noteDate` date, 
`noteIndex` int, 
`note` VARCHAR(255), 
PRIMARY KEY (`companyNotes_ID`), 
INDEX `fk_companyNotes_companyPerson_idx` (`companyPerson_ID` ASC),
CONSTRAINT `fk_companyNotes_companyPerson`
    FOREIGN KEY (`companyPerson_ID`)
    REFERENCES `oursafetydb`.`companyPerson` (`companyPerson_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
 
 
CREATE TABLE IF NOT EXISTS `oursafetydb`.`companyType` ( 
`companyType_ID` int AUTO_INCREMENT, 
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`company_ID` int, /*FK*/ 
`typeLibrary_ID` int,   /*FK*/ 
PRIMARY KEY (`companyType_ID`), 
INDEX `fk_companyType_company_ID_idx` (`company_ID` ASC),
INDEX `fk_companyType_typeLibrary_ID_idx` (`typeLibrary_ID` ASC),
CONSTRAINT `fk_companyType_company_ID`
    FOREIGN KEY (`company_ID`)
    REFERENCES `oursafetydb`.`company` (`company_ID`) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION, 
CONSTRAINT `fk_companyType_typeLibrary_ID`
    FOREIGN KEY (`typeLibrary_ID`)
    REFERENCES `oursafetydb`.`typeLibrary` (`typeLibrary_ID`) 
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION)
ENGINE = InnoDB;
 
 
CREATE TABLE IF NOT EXISTS `oursafetydb`.`companyPositions` ( 
`companyPositions_ID` int AUTO_INCREMENT, 
`dateAdded` date, 
`dateRemoved` date, 
`userAdded` int, 
`userRemoved` int, 
`company_ID` int, 
`companyPerson_ID` int, 
`positionTitle` VARCHAR(100), 
PRIMARY KEY (`companyPositions_ID`), 
INDEX `fk_companyPositions_company_ID_idx` (`company_id` ASC),
INDEX `fk_companyPositions_companyPerson_idx` (`companyPerson_ID` ASC),
CONSTRAINT `fk_companyPositions_company_ID` 
    FOREIGN KEY (`company_ID`) 
    REFERENCES `oursafetydb`.`company` (`company_ID`) 
    ON DELETE NO ACTION
    ON UPDATE NO ACTION, 
CONSTRAINT `fk_companyPositions_companyPerson_ID`
    FOREIGN KEY (`companyPerson_ID`) 
    REFERENCES `oursafetydb`.`companyPerson` (`companyPerson_ID`)
    ON DELETE NO ACTION 
    ON UPDATE NO ACTION) 
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`country`(
`country_ID`  int AUTO_INCREMENT,
`countryName` VARCHAR(15),
PRIMARY KEY(`country_ID`))
ENGINE = InnoDB;


CREATE TABLE IF NOT EXISTS `oursafetydb`.`province`(
`province_ID`  int AUTO_INCREMENT,
`provinceName` VARCHAR(15),
`country_ID` int,
PRIMARY KEY(`province_ID`),
INDEX `fk_province_country_idx` (`country_ID` ASC),
CONSTRAINT `fk_province_country_ID`
    FOREIGN KEY(`country_ID`)
    REFERENCES `oursafetydb`.`country` (`country_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE TABLE IF NOT EXISTS `oursafetydb`.`city`(
`city_ID`  int AUTO_INCREMENT,
`cityName` VARCHAR(15),
`province_ID` int,
PRIMARY KEY(`city_ID`),
INDEX `fk_province_country_idx` (`province_ID` ASC),
CONSTRAINT `fk_city_province_ID`
    FOREIGN KEY(`province_ID`)
    REFERENCES `oursafetydb`.`province` (`province_ID`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


 



-- Dumping data for table oursafetydb.address: ~2 rows (approximately)
DELETE FROM `address`;
/*!40000 ALTER TABLE `address` DISABLE KEYS */;
INSERT INTO `address` (`address_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `typeLibrary_ID`, `addressLine1`, `addressLine2`, `city`, `province`, `country`, `postalCode`) VALUES
	(1, '2021-02-09', NULL, 1, NULL, 311, '112Street', 'ABC SW', 'Edmonton', 'Alberta', 'Canada', 'T2A1W2'),
	(2, '2021-02-09', NULL, 1, NULL, 311, '13Street', 'AC NW', 'Calgary', 'Alberta', 'Canada', 'T3C1Q4');
/*!40000 ALTER TABLE `address` ENABLE KEYS */;

-- Dumping data for table oursafetydb.city: ~4 rows (approximately)
DELETE FROM `city`;
/*!40000 ALTER TABLE `city` DISABLE KEYS */;
INSERT INTO `city` (`city_ID`, `cityName`, `province_ID`) VALUES
	(1, 'Calgary', 1),
	(2, 'Edmonton', 1),
	(3, 'Vancouver', 2),
	(4, 'Ottawa', 3);
/*!40000 ALTER TABLE `city` ENABLE KEYS */;

-- Dumping data for table oursafetydb.company: ~2 rows (approximately)
DELETE FROM `company`;
/*!40000 ALTER TABLE `company` DISABLE KEYS */;
INSERT INTO `company` (`company_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `shortname`, `name`, `description`, `saltHash`, `account`, `industry`) VALUES
	(1, '2021-02-09', NULL, 1, NULL, 'ABC_company', 'ABC_company', 'ABC company', NULL, NULL, NULL),
	(2, '2021-02-09', NULL, 1, NULL, 'BBC_company', 'BBC_company', 'BBC company', NULL, NULL, NULL);
/*!40000 ALTER TABLE `company` ENABLE KEYS */;

-- Dumping data for table oursafetydb.companyNotes: ~1 rows (approximately)
DELETE FROM `companyNotes`;
/*!40000 ALTER TABLE `companyNotes` DISABLE KEYS */;
INSERT INTO `companyNotes` (`companyNotes_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `companyPerson_ID`, `noteDate`, `noteIndex`, `note`) VALUES
	(1, NULL, NULL, 2, NULL, 2, '2021-02-12', 0, 'equipment company');
/*!40000 ALTER TABLE `companyNotes` ENABLE KEYS */;

-- Dumping data for table oursafetydb.companyPerson: ~3 rows (approximately)
DELETE FROM `companyPerson`;
/*!40000 ALTER TABLE `companyPerson` DISABLE KEYS */;
INSERT INTO `companyPerson` (`companyPerson_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `company_ID`, `person_ID`, `email`, `isEmployeeActive`) VALUES
	(1, '2021-02-09', NULL, 2, NULL, NULL, NULL, NULL, 1),
	(2, '2021-02-09', NULL, 2, NULL, 1, 3, 'charls@gmail.com', 1),
	(3, '2021-02-12', NULL, 2, NULL, 1, 2, 'jason@gmail.com', 1);
/*!40000 ALTER TABLE `companyPerson` ENABLE KEYS */;

-- Dumping data for table oursafetydb.companyPersonAddress: ~2 rows (approximately)
DELETE FROM `companyPersonAddress`;
/*!40000 ALTER TABLE `companyPersonAddress` DISABLE KEYS */;
INSERT INTO `companyPersonAddress` (`companyPersonAddress_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `companyPerson_ID`, `address_ID`) VALUES
	(1, '2021-02-09', NULL, 1, NULL, 3, 2),
	(2, '2021-02-09', NULL, 1, NULL, 2, 1);
/*!40000 ALTER TABLE `companyPersonAddress` ENABLE KEYS */;

-- Dumping data for table oursafetydb.companyPersonPhone: ~1 rows (approximately)
DELETE FROM `companyPersonPhone`;
/*!40000 ALTER TABLE `companyPersonPhone` DISABLE KEYS */;
INSERT INTO `companyPersonPhone` (`companyPersonPhone_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `companyPerson_ID`, `phone_ID`) VALUES
	(1, NULL, NULL, 2, NULL, 2, 1),
        (2, NULL, NULL, 2, NULL, 3, 2);
/*!40000 ALTER TABLE `companyPersonPhone` ENABLE KEYS */;

-- Dumping data for table oursafetydb.companyPositions: ~2 rows (approximately)
DELETE FROM `companyPositions`;
/*!40000 ALTER TABLE `companyPositions` DISABLE KEYS */;
INSERT INTO `companyPositions` (`companyPositions_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `company_ID`, `companyPerson_ID`, `positionTitle`) VALUES
	(1, '2021-02-12', NULL, 2, NULL, 1, 2, 'CEO'),
	(2, '2021-02-12', NULL, 2, NULL, 1, 3, 'MANAGER');
/*!40000 ALTER TABLE `companyPositions` ENABLE KEYS */;

-- Dumping data for table oursafetydb.companyRelationship: ~1 rows (approximately)
DELETE FROM `companyRelationship`;
/*!40000 ALTER TABLE `companyRelationship` DISABLE KEYS */;
INSERT INTO `companyRelationship` (`companyRelationship_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `parent`, `child`, `typeLibrary_ID`, `company_ID`) VALUES
	(1, NULL, NULL, NULL, NULL, NULL, NULL, 301, 1);
/*!40000 ALTER TABLE `companyRelationship` ENABLE KEYS */;

-- Dumping data for table oursafetydb.companyType: ~2 rows (approximately)
DELETE FROM `companyType`;
/*!40000 ALTER TABLE `companyType` DISABLE KEYS */;
INSERT INTO `companyType` (`companyType_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `company_ID`, `typeLibrary_ID`) VALUES
	(1, '2021-02-09', NULL, 2, NULL, 1, 321),
	(2, '2021-02-09', NULL, 2, NULL, 2, 321);
/*!40000 ALTER TABLE `companyType` ENABLE KEYS */;

-- Dumping data for table oursafetydb.country: ~1 rows (approximately)
DELETE FROM `country`;
/*!40000 ALTER TABLE `country` DISABLE KEYS */;
INSERT INTO `country` (`country_ID`, `countryName`) VALUES
	(1, 'Canada');
/*!40000 ALTER TABLE `country` ENABLE KEYS */;

-- Dumping data for table oursafetydb.emergencyContact: ~3 rows (approximately)
DELETE FROM `emergencyContact`;
/*!40000 ALTER TABLE `emergencyContact` DISABLE KEYS */;
INSERT INTO `emergencyContact` (`emergencyContact_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `emergencyContactFirstName`, `emergencyContactLastName`, `emergencyContactNumber`, `emergencyContactRelationship`) VALUES
	(1, '2021-02-09', NULL, 2, NULL, NULL, NULL, NULL, NULL),
	(2, '2021-02-09', NULL, 2, NULL, 'Wenhao', 'Liu', '1-273-345-5647', 'father'),
	(3, '2021-02-09', NULL, 2, NULL, 'Boxuan', 'Lu', '1-333-999-4567', 'friend');
/*!40000 ALTER TABLE `emergencyContact` ENABLE KEYS */;

-- Dumping data for table oursafetydb.item: ~2 rows (approximately)
DELETE FROM `item`;
/*!40000 ALTER TABLE `item` DISABLE KEYS */;
INSERT INTO `item` (`item_ID`, `dateAdded`, `DateRemoved`, `userAdded`, `userRemoved`, `itemClass_ID`, `model`, `company_ID`, `isChargeableType`, `isDepletingType`, `isDepreactiationType`, `itemClassInformation`, `serialNumber`, `purchaseDate`) VALUES
(2, '2021-02-12', NULL, 1, NULL, 2, 'Milwaukee', 2, 1, 1, 1, 'screwdriver infor', '123457', '2021-02-12'),
(3, '2021-04-16', NULL, 3, NULL, NULL, 'Honda Civic', 1, 1, 1, 1, '2007, Blue', 'JH4KA3140HC006995', '2021-04-01'),
(4, '2021-04-16', NULL, 3, NULL, NULL, 'Motormaster Drill', 1, 1, 1, 1, 'Motomaster Drill, Blue', '2', '2021-03-02'),
(5, '2021-04-16', NULL, 3, NULL, NULL, 'Husky Lawn Mower', 1, 1, 1, 1, 'Push Mower', '345365342', '2021-03-30'),
(6, '2021-04-16', NULL, 3, NULL, NULL, 'John Deer Tractor Trailer', 1, 1, 1, 1, '2021, Green', 'FD45Z7HJKQ', '2020-11-04');
/*!40000 ALTER TABLE `item` ENABLE KEYS */;

-- Dumping data for table oursafetydb.itemClass: ~2 rows (approximately)
DELETE FROM `itemClass`;
/*!40000 ALTER TABLE `itemClass` DISABLE KEYS */;
INSERT INTO `itemClass` (`itemClass_ID`, `DateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `itemType`, `itemClassFields_ID`, `itemClassInformation`) VALUES
	(1, '2021-02-09', NULL, 1, NULL, NULL, 1, 'Make / Year and color of the Vehicle'),
	(2, '2021-02-09', NULL, 1, NULL, NULL, 2, 'Trailer size and type'),
	(3, '2021-02-09', NULL, 1, NULL, NULL, 3, 'Model and hardware brand'),
	(4, '2021-02-09', NULL, 1, NULL, NULL, 4, 'Make / Year and color of the Heavy Machinery'),
	(5, '2021-02-09', NULL, 1, NULL, NULL, 5, 'Make / Year and color of the Light Machinery'),
	(6, '2021-02-09', NULL, 1, NULL, NULL, 6, 'Power Tool brand'),
	(7, '2021-02-09', NULL, 1, NULL, NULL, 7, 'Hand Tool brand'),
	(8, '2021-02-09', NULL, 1, NULL, NULL, 8, 'Make and type of Agriculture Tool');
/*!40000 ALTER TABLE `itemClass` ENABLE KEYS */;

-- Dumping data for table oursafetydb.itemClassFields: ~2 rows (approximately)
DELETE FROM `itemClassFields`;
/*!40000 ALTER TABLE `itemClassFields` DISABLE KEYS */;
INSERT INTO `itemClassFields` (`itemClassFields_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `typeLibrary_ID`, `fieldDescr`, `fieldDescrType`) VALUES
	(1, '2021-02-09', NULL, 1, NULL, 201, 'Vehicle', 'Vehicle'),
	(2, '2021-02-09', NULL, 1, NULL, 202, 'Trailer', 'Trailer'),
	(3, '2021-02-09', NULL, 1, NULL, 203, 'Hardware', 'Hardware'),
	(4, '2021-02-09', NULL, 1, NULL, 204, 'Heavy Machinery', 'Heavy Machinery'),
	(5, '2021-02-09', NULL, 1, NULL, 205, 'Light Machinery', 'Light Machinery'),
	(6, '2021-02-09', NULL, 1, NULL, 206, 'Power Tools', 'Power Tools'),
	(7, '2021-02-09', NULL, 1, NULL, 207, 'Hand Tools', 'Hand Tools'),
	(8, '2021-02-09', NULL, 1, NULL, 208, 'Agriculture', 'Agriculture');
/*!40000 ALTER TABLE `itemClassFields` ENABLE KEYS */;

-- Dumping data for table oursafetydb.logins: ~2 rows (approximately)
DELETE FROM `logins`;
/*!40000 ALTER TABLE `logins` DISABLE KEYS */;
INSERT INTO `logins` (`user_id`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `username`, `password`, `company_ID`, `isActive`, `isAdmin`) VALUES
	(1, '2021-02-09', NULL, 0, NULL, 'admin', 'password', NULL, 'T', 'T'),
	(2, '2021-02-09', NULL, 1, NULL, 'oursafetyapplication@gmail.com', 'password', 2, 'T', 'F'),
        (3, '2021-03-07', NULL, 1, NULL, 'manager2', 'password', 1, 'T', 'F');
/*!40000 ALTER TABLE `logins` ENABLE KEYS */;

-- Dumping data for table oursafetydb.manual: ~2 rows (approximately)
DELETE FROM `manual`;
/*!40000 ALTER TABLE `manual` DISABLE KEYS */;
INSERT INTO `manual` (`manual_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `typeLibrary_ID`, `parent`, `title`, `intention`, `overview`, `content`) VALUES
	(1, '2021-02-09', NULL, 2, NULL, 101, NULL, 'safety manual', 'safety', NULL, 'safety manual'),
	(2, '2021-02-12', NULL, 2, NULL, 101, NULL, 'equipment manual', 'equipment', NULL, 'equipment manual');
/*!40000 ALTER TABLE `manual` ENABLE KEYS */;

-- Dumping data for table oursafetydb.manualUse: ~1 rows (approximately)
DELETE FROM `manualUse`;
/*!40000 ALTER TABLE `manualUse` DISABLE KEYS */;
INSERT INTO `manualUse` (`manualUse_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `manual_ID`, `company_ID`, `companyRole`, `job`) VALUES
	(1, '2021-02-12', NULL, NULL, NULL, 1, NULL, NULL, NULL);
/*!40000 ALTER TABLE `manualUse` ENABLE KEYS */;

-- Dumping data for table oursafetydb.person: ~3 rows (approximately)
DELETE FROM `person`;
/*!40000 ALTER TABLE `person` DISABLE KEYS */;
INSERT INTO `person` (`person_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `firstName`, `lastName`, `dateOfBirth`, `gender`, `emergencyContact_ID`) VALUES
	(1, '2021-02-09', NULL, 2, NULL, NULL, NULL, NULL, NULL, NULL),
	(2, '2021-02-09', NULL, 2, NULL, 'Jason', 'Bourn', '2001-02-09', 'M', 3),
	(3, '2021-02-09', NULL, 2, NULL, 'charly', 'Bob', '2021-02-09', 'F', 2);
/*!40000 ALTER TABLE `person` ENABLE KEYS */;

-- Dumping data for table oursafetydb.phone: ~3 rows (approximately)
DELETE FROM `phone`;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` (`phone_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `typeLibrary_ID`, `countryCode`, `areaCode`, `localNumber`, `extension`) VALUES
	(1, '2021-02-09', NULL, 2, NULL, 2, '1', '403', '333-4356', '3333'),
	(2, '2021-02-09', NULL, 2, NULL, 2, '1', '403', '222-3945', '2222'),
	(3, '2021-02-09', NULL, 2, NULL, 3, '1', '403', '111-8921', '1111');
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;

-- Dumping data for table oursafetydb.province: ~3 rows (approximately)
DELETE FROM `province`;
/*!40000 ALTER TABLE `province` DISABLE KEYS */;
INSERT INTO `province` (`province_ID`, `provinceName`, `country_ID`) VALUES
	(1, 'AB', 1),
	(2, 'BC', 1),
	(3, 'ON', 1);
/*!40000 ALTER TABLE `province` ENABLE KEYS */;

-- Dumping data for table oursafetydb.typeLibrary: ~21 rows (approximately)
DELETE FROM `typeLibrary`;
/*!40000 ALTER TABLE `typeLibrary` DISABLE KEYS */;
INSERT INTO `typeLibrary` (`typeLibrary_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `type`, `description`, `isCategory`) VALUES
(1, '2021-02-09', NULL, 1, NULL, 'phone', 'phone', 'T'),
	(2, '2021-02-09', NULL, 1, NULL, 'phone', 'company phone', 'F'),
	(3, '2021-02-09', NULL, 1, NULL, 'phone', 'fax', 'F'),
	(4, '2021-02-09', NULL, 1, NULL, 'phone', 'personal phone', 'F'),
	(100, '2021-02-09', NULL, 1, NULL, 'manual', 'manual', 'T'),
	(101, '2021-02-09', NULL, 1, NULL, 'manual', 'safety manual', 'F'),
	(102, '2021-02-09', NULL, 1, NULL, 'manual', 'equipment manual', 'F'),
	(103, '2021-02-09', NULL, 1, NULL, 'manual', 'construction manual', 'F'),
	(200, '2021-02-09', NULL, 1, NULL, 'equipment', 'equipment', 'T'),
	(201, '2021-02-09', NULL, 1, NULL, 'equipment', 'Vehicle', 'F'),
	(202, '2021-02-09', NULL, 1, NULL, 'equipment', 'Trailer', 'F'),
	(203, '2021-02-09', NULL, 1, NULL, 'equipment', 'Hardware', 'F'),
	(204, '2021-02-09', NULL, 1, NULL, 'equipment', 'Heavy Machinery', 'F'),
	(205, '2021-02-09', NULL, 1, NULL, 'equipment', 'Light Machinery', 'F'),
	(206, '2021-02-09', NULL, 1, NULL, 'equipment', 'Power Tool', 'F'),
	(207, '2021-02-09', NULL, 1, NULL, 'equipment', 'Hand Tool', 'F'),
	(208, '2021-02-09', NULL, 1, NULL, 'equipment', 'Agriculture', 'F'),
	(300, '2021-02-09', NULL, 1, NULL, 'companyRelationship', 'company relationship', 'T'),
	(301, '2021-02-09', NULL, 1, NULL, 'companyRelationship', 'cooperate', 'F'),
	(302, '2021-02-09', NULL, 1, NULL, 'companyRelationship', 'sponsor', 'F'),
	(310, '2021-02-09', NULL, 1, NULL, 'address', 'address', 'T'),
	(311, '2021-02-09', NULL, 1, NULL, 'address', 'company address', 'F'),
	(312, '2021-02-09', NULL, 1, NULL, 'address', 'personal address', 'F'),
	(320, '2021-02-09', NULL, 1, NULL, 'companyType', 'company type', 'T'),
	(321, '2021-02-09', NULL, 1, NULL, 'companyType', 'construction company', 'F'),
	(350, '2021-02-09', NULL, 1, NULL, 'url', 'url', 'T'),
	(351, '2021-02-09', NULL, 1, NULL, 'url', 'official website', 'F');
/*!40000 ALTER TABLE `typeLibrary` ENABLE KEYS */;

-- Dumping data for table oursafetydb.url: ~2 rows (approximately)
DELETE FROM `url`;
/*!40000 ALTER TABLE `url` DISABLE KEYS */;
INSERT INTO `url` (`url_ID`, `dateAdded`, `dateRemoved`, `userAdded`, `userRemoved`, `typeLibrary_ID`, `URL`, `company_ID`) VALUES
	(1, '2021-02-09', NULL, 2, NULL, 351, '123.ca', 1),
	(2, '2021-02-09', NULL, 2, NULL, 351, '223.ca', 1);
/*!40000 ALTER TABLE `url` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IFNULL(@OLD_FOREIGN_KEY_CHECKS, 1) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40111 SET SQL_NOTES=IFNULL(@OLD_SQL_NOTES, 1) */;