--
-- Database: `mydb`
--
--
-- Dumping data for table `ability`
--
INSERT INTO `ability` (`ability_ID`, `name`, `description`, `level_requirement`) VALUES
(0, 'None', 'This item has no special ability.', 0),
(1, 'Electricity', 'Strikes the opponent with electricity with each hit', 900),
(2, 'Fire', 'Flame ON!', 25),
(3, 'Water', 'Drown someone on land!', 25),
(4, 'telepathy', 'Can read other peoples'' thoughts and communicate with others with the mind', 80);
--
-- Dumping data for table `account`
--
INSERT INTO `account` (`account_ID`, `account_name`, `password`, `email`, `first_name`, `last_name`, `ingame_currency`) VALUES
(1, 'TestyMcTester', 'password', 'testy.mctester@email.com', 'Testy', 'McTester', 2000),
(2, 'Testit', 'asdjdfl', 'dude@email.ru', 'Dude', 'Sweeeeeet', 1902),
(3, 'WillAccount', 'adljkjfkl', 'will@gmail.com', 'Will', 'Stevens', 19280),
(4, 'karl', 'password', 'my@email.com', 'Karl', 'S', 99887633),
(5, 'trussm_test', 'Ilove5h3', 'trussm@msoe.edu', 'Malcolm', 'Truss', 9000);
--
-- Dumping data for table `items`
--
INSERT INTO `items` (`item_ID`, `name`, `damage`, `armor`, `level_requirement`, `rarity`, `value`, `model`, `ability_ID`) VALUES
(1, 'Fire Sword', 30, 5, 800, 'Rare', 2000, 'firesword.jpg', 3),
(2, 'King Slayer', 999, 20, 50, 'Mythic', 999999, 'ks.jpg', 0),
(3, 'Knife', 2, 1, 0, 'Common', 1, 'knife.jpg', 0);
--
-- Dumping data for table `skill`
--
INSERT INTO `skill` (`skill_ID`, `name`, `description`, `level_requirement`) VALUES
(1, 'Basic ', 'basic', 1),
(2, 'High Jump', 'Jump higher then the average person', 2);
--
-- Dumping data for table `character`
--
INSERT INTO `character` (`character_ID`, `name`, `race`, `model`, `strength`, `constitution`, `intelligence`, `wisdom`, `agility`, `dexterity`, `level`, `experience`, `account_ID`) VALUES
(1, 'Charles', 'Dwarf', 'test.jpg', 18, 15, 9, 10, 5, 7, 80, 1200, 2),
(2,'Bert','Elf','test.jpg',14,17,8,9,12,11,70,1400,1),
(3,'Sue','Human','test.jpg',18,12,11,11,11,4,20,1600,2),
(4,'John','Dwarf','test.jpg',18,15,10,15,16,3,30,1800,3),
(5,'Matt','Dwarf','test.jpg',17,17,7,14,15,14,70,2000,4);
--
-- Dumping data for table `character_has_items`
--
INSERT INTO `character_has_items` (`character_character_ID`, `items_item_ID`) VALUES
(1,3),
(2,1),
(3,1),
(4,2),
(5,3);
--
-- Dumping data for table `character_has_skill`
--
INSERT INTO `character_has_skill` (`character_character_ID`, `skill_skill_ID`) VALUES
(1,2),
(2,1),
(3,2),
(4,1),
(5,2);
