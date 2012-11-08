--
-- Database: `mydb`
--
-- --------------------------------------------------------
--
-- Table structure for table `ability`
--

DROP TABLE IF EXISTS `ability`;
CREATE TABLE IF NOT EXISTS `ability` (
 `ability_ID` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(30) NOT NULL,
 `description` text NOT NULL,
 `level_requirement` int(11) NOT NULL,
 PRIMARY KEY (`ability_ID`),
 UNIQUE KEY `name_UNIQUE` (`name`)
) ;
-- --------------------------------------------------------
--
-- Table structure for table `account`
--
DROP TABLE IF EXISTS `account`;
CREATE TABLE IF NOT EXISTS `account` (
 `account_ID` int(11) NOT NULL AUTO_INCREMENT,
 `account_name` varchar(20) NOT NULL,
 `password` varchar(20) NOT NULL,
 `email` varchar(45) NOT NULL,
 `first_name` varchar(45) NOT NULL,
 `last_name` varchar(45) NOT NULL,
 `ingame_currency` int(11) NOT NULL,
 PRIMARY KEY (`account_ID`),
 UNIQUE KEY `account_name_UNIQUE` (`account_name`)
) ;
-- --------------------------------------------------------
--
-- Table structure for table `character`
--
DROP TABLE IF EXISTS `character`;
CREATE TABLE IF NOT EXISTS `character` (
 `character_ID` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(30) NOT NULL,
 `race` varchar(15) NOT NULL,
 `model` varchar(45) NOT NULL,
 `strength` int(11) NOT NULL,
 `constitution` int(11) NOT NULL,
 `intelligence` int(11) NOT NULL,
 `wisdom` int(11) NOT NULL,
 `agility` int(11) NOT NULL,
 `dexterity` int(11) NOT NULL,
 `level` int(11) NOT NULL,
 `experience` int(11) NOT NULL,
 `account_ID` int(11) NOT NULL,
 PRIMARY KEY (`character_ID`),
 UNIQUE KEY `name_UNIQUE` (`name`),
 KEY `fk_character_account1_idx` (`account_ID`)
) ;
-- --------------------------------------------------------
--
-- Table structure for table `character_has_items`
--
DROP TABLE IF EXISTS `character_has_items`;
CREATE TABLE IF NOT EXISTS `character_has_items` (
 `character_character_ID` int(11) NOT NULL,
 `items_item_ID` int(11) NOT NULL,
 KEY `fk_character_has_items_items1_idx` (`items_item_ID`),
 KEY `fk_character_has_items_character1_idx` (`character_character_ID`)
);
-- --------------------------------------------------------
--
-- Table structure for table `character_has_skill`
--
DROP TABLE IF EXISTS `character_has_skill`;
CREATE TABLE IF NOT EXISTS `character_has_skill` (
 `character_character_ID` int(11) NOT NULL,
 `skill_skill_ID` int(11) NOT NULL,
 KEY `fk_character_has_skill_skill1_idx` (`skill_skill_ID`),
 KEY `fk_character_has_skill_character1_idx` (`character_character_ID`)
) ;
-- --------------------------------------------------------
--
-- Table structure for table `items`
--
DROP TABLE IF EXISTS `items`;
CREATE TABLE IF NOT EXISTS `items` (
 `item_ID` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(30) NOT NULL,
 `damage` int(11) NOT NULL,
 `armor` int(11) NOT NULL,
 `level_requirement` int(11) NOT NULL,
 `rarity` varchar(10) NOT NULL,
 `value` int(11) NOT NULL,
 `model` varchar(45) NOT NULL,
 `ability_ID` int(11) NOT NULL,
 PRIMARY KEY (`item_ID`),
 UNIQUE KEY `name_UNIQUE` (`name`),
 KEY `fk_items_abilitiy1_idx` (`ability_ID`)
) ;
-- --------------------------------------------------------
--
-- Table structure for table `skill`
--
DROP TABLE IF EXISTS `skill`;
CREATE TABLE IF NOT EXISTS `skill` (
 `skill_ID` int(11) NOT NULL AUTO_INCREMENT,
 `name` varchar(30) NOT NULL,
 `description` varchar(45) NOT NULL,
 `level_requirement` int(11) NOT NULL,
 PRIMARY KEY (`skill_ID`),
 UNIQUE KEY `name_UNIQUE` (`name`)
);
--
-- Constraints for table `character`
--
ALTER TABLE `character`
 ADD CONSTRAINT `character_ibfk_1` FOREIGN KEY (`account_ID`) REFERENCES `account` (`account_ID`);
--
-- Constraints for table `character_has_items`
--
ALTER TABLE `character_has_items`
 ADD CONSTRAINT `fk_character_has_items_character1` FOREIGN KEY (`character_character_ID`) REFERENCES `character` (`character_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
 ADD CONSTRAINT `fk_character_has_items_items1` FOREIGN KEY (`items_item_ID`) REFERENCES `items` (`item_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
--
-- Constraints for table `character_has_skill`
--
ALTER TABLE `character_has_skill`
 ADD CONSTRAINT `fk_character_has_skill_character1` FOREIGN KEY (`character_character_ID`) REFERENCES `character` (`character_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
 ADD CONSTRAINT `fk_character_has_skill_skill1` FOREIGN KEY (`skill_skill_ID`) REFERENCES `skill` (`skill_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;
--
-- Constraints for table `items`
--
ALTER TABLE `items`
 ADD CONSTRAINT `items_ibfk_1` FOREIGN KEY (`ability_ID`) REFERENCES `ability` (`ability_ID`);