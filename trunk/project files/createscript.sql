SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;

SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;

SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';



CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci ;

USE `mydb` ;



-- -----------------------------------------------------

-- Table `mydb`.`account`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `mydb`.`account` (

  `account_ID` INT NOT NULL ,

  `account_name` VARCHAR(20) NOT NULL ,

  `password` VARCHAR(20) NOT NULL ,

  `email` VARCHAR(45) NOT NULL ,

  `first_name` VARCHAR(45) NOT NULL ,

  `last_name` VARCHAR(45) NOT NULL ,

  `ingame_currency` INT NOT NULL ,

  PRIMARY KEY (`account_ID`) ,

  UNIQUE INDEX `account_ID_UNIQUE` (`account_ID` ASC) ,

  UNIQUE INDEX `account_name_UNIQUE` (`account_name` ASC) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `mydb`.`character`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `mydb`.`character` (

  `character_ID` INT NOT NULL ,

  `name` VARCHAR(30) NOT NULL ,

  `race` VARCHAR(15) NOT NULL ,

  `model` VARCHAR(45) NOT NULL ,

  `strength` INT NOT NULL ,

  `constitution` INT NOT NULL ,

  `intelligence` INT NOT NULL ,

  `wisdom` INT NOT NULL ,

  `agility` INT NOT NULL ,

  `dexterity` INT NOT NULL ,

  `level` INT NOT NULL ,

  `experience` INT NOT NULL ,

  `account_account_ID` INT NOT NULL ,

  PRIMARY KEY (`character_ID`) ,

  UNIQUE INDEX `character_ID_UNIQUE` (`character_ID` ASC) ,

  INDEX `fk_character_account1_idx` (`account_account_ID` ASC) ,

  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,

  CONSTRAINT `fk_character_account1`

    FOREIGN KEY (`account_account_ID` )

    REFERENCES `mydb`.`account` (`account_ID` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `mydb`.`abilitiy`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `mydb`.`abilitiy` (

  `ability_ID` INT NOT NULL ,

  `name` VARCHAR(30) NOT NULL ,

  `description` VARCHAR(45) NOT NULL ,

  `level_requirement` INT NOT NULL ,

  PRIMARY KEY (`ability_ID`) ,

  UNIQUE INDEX `ability_ID_UNIQUE` (`ability_ID` ASC) ,

  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `mydb`.`items`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `mydb`.`items` (

  `item_ID` INT NOT NULL ,

  `name` VARCHAR(30) NOT NULL ,

  `damage` INT NOT NULL ,

  `armor` INT NOT NULL ,

  `level_requirement` INT NOT NULL ,

  `rarity` VARCHAR(10) NOT NULL ,

  `value` INT NOT NULL ,

  `model` VARCHAR(45) NOT NULL ,

  `abilitiy_ability_ID` INT NOT NULL ,

  PRIMARY KEY (`item_ID`) ,

  INDEX `fk_items_abilitiy1_idx` (`abilitiy_ability_ID` ASC) ,

  UNIQUE INDEX `item_ID_UNIQUE` (`item_ID` ASC) ,

  UNIQUE INDEX `name_UNIQUE` (`name` ASC) ,

  CONSTRAINT `fk_items_abilitiy1`

    FOREIGN KEY (`abilitiy_ability_ID` )

    REFERENCES `mydb`.`abilitiy` (`ability_ID` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `mydb`.`skill`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `mydb`.`skill` (

  `skill_ID` INT NOT NULL ,

  `name` VARCHAR(30) NOT NULL ,

  `description` VARCHAR(45) NOT NULL ,

  `level_requirement` INT NOT NULL ,

  PRIMARY KEY (`skill_ID`) ,

  UNIQUE INDEX `skill_ID_UNIQUE` (`skill_ID` ASC) ,

  UNIQUE INDEX `name_UNIQUE` (`name` ASC) )

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `mydb`.`character_has_skill`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `mydb`.`character_has_skill` (

  `character_character_ID` INT NOT NULL ,

  `skill_skill_ID` INT NOT NULL ,

  PRIMARY KEY (`character_character_ID`, `skill_skill_ID`) ,

  INDEX `fk_character_has_skill_skill1_idx` (`skill_skill_ID` ASC) ,

  INDEX `fk_character_has_skill_character1_idx` (`character_character_ID` ASC) ,

  CONSTRAINT `fk_character_has_skill_character1`

    FOREIGN KEY (`character_character_ID` )

    REFERENCES `mydb`.`character` (`character_ID` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_character_has_skill_skill1`

    FOREIGN KEY (`skill_skill_ID` )

    REFERENCES `mydb`.`skill` (`skill_ID` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;





-- -----------------------------------------------------

-- Table `mydb`.`character_has_items`

-- -----------------------------------------------------

CREATE  TABLE IF NOT EXISTS `mydb`.`character_has_items` (

  `character_character_ID` INT NOT NULL ,

  `items_item_ID` INT NOT NULL ,

  PRIMARY KEY (`character_character_ID`, `items_item_ID`) ,

  INDEX `fk_character_has_items_items1_idx` (`items_item_ID` ASC) ,

  INDEX `fk_character_has_items_character1_idx` (`character_character_ID` ASC) ,

  CONSTRAINT `fk_character_has_items_character1`

    FOREIGN KEY (`character_character_ID` )

    REFERENCES `mydb`.`character` (`character_ID` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION,

  CONSTRAINT `fk_character_has_items_items1`

    FOREIGN KEY (`items_item_ID` )

    REFERENCES `mydb`.`items` (`item_ID` )

    ON DELETE NO ACTION

    ON UPDATE NO ACTION)

ENGINE = InnoDB;







SET SQL_MODE=@OLD_SQL_MODE;

SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;

SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;

