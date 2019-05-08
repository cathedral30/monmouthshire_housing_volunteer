DROP SCHEMA IF EXISTS `mha_db`;
CREATE SCHEMA `mha_db`;
USE `mha_db`;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(10) unsigned NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_ibfk_1` (`userid`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `user_volunteer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `surname` varchar(30) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `emergency_number` varchar(15) NOT NULL,
  `mha_tennant` int(11) NOT NULL,
  `previous_volunteer` int(11) NOT NULL,
  `volunteer_experience` text DEFAULT NULL,
  `conditions` int(11) NOT NULL,
  `condition_details` text DEFAULT NULL,
  `medication` int(11) NOT NULL,
  `medication_details` text DEFAULT NULL,
  `allergies` int(11) NOT NULL,
  `allergy_details` text DEFAULT NULL,
  `current_balance` int(11) DEFAULT 0,
  `total_amount` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_volunteer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `achievement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(455) DEFAULT NULL,
  `credits` int(11) NOT NULL,
  `file_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `address_line_1` varchar(255) NOT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `postcode` varchar(10) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `allocation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date_allocated` datetime NOT NULL,
  `credits_allocated` int(11) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `allocation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `business` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_name` varchar(25) NOT NULL,
  `email_address` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `business_changes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `table_changed` varchar(255) DEFAULT NULL,
  `change_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `record_changes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `table_changed` varchar(45) DEFAULT NULL,
  `change_type` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `reward` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `reward_name` varchar(50) NOT NULL,
  `reward_description` text NOT NULL,
  `cost` int(11) NOT NULL,
  `business_id` int(10) unsigned NOT NULL,
  `num_times_redeemed` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `active` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `business_id` (`business_id`),
  CONSTRAINT `reward_ibfk_1` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `redemption` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `reward_id` int(10) unsigned NOT NULL,
  `redemption_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `reward_id` (`reward_id`),
  CONSTRAINT `redemption_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `redemption_ibfk_2` FOREIGN KEY (`reward_id`) REFERENCES `reward` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `reward_changes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `table_changed` varchar(45) NOT NULL,
  `r_description` varchar(500) DEFAULT NULL,
  `r_name` varchar(500) DEFAULT NULL,
  `changer` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `volunteer_achievement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `achievement_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `achievement_id` (`achievement_id`),
  CONSTRAINT `volunteer_achievement_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `volunteer_achievement_ibfk_2` FOREIGN KEY (`achievement_id`) REFERENCES `achievement` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `volunteer_application` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `emergency_number` varchar(15) NOT NULL,
  `address_line_1` varchar(255) NOT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `postcode` varchar(10) NOT NULL,
  `mha_tennant` int(11) NOT NULL,
  `previous_volunteer` int(11) NOT NULL,
  `volunteer_experience` text DEFAULT NULL,
  `conditions` int(11) NOT NULL,
  `condition_details` text DEFAULT NULL,
  `medication` int(11) NOT NULL,
  `medication_details` text DEFAULT NULL,
  `allergies` text NOT NULL,
  `allergy_details` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `api_keys` (
	`user_reference` varchar(255),
    `key` varchar(255) NOT NULL
);

CREATE TABLE `locked_users` (
	`username` varchar(255) NOT NULL,
    `attempts` int,
    `locked` int(1),
    `locked_until` datetime
);

DROP PROCEDURE IF EXISTS `createSystemAdministrator`;

DELIMITER $$
CREATE PROCEDURE `createSystemAdministrator`(email varchar(255), encpassword varchar(255), firstName varchar(255), surName varchar(255), pNumber varchar(15), eNumber varchar(15), mhaTenant int, previousVolunteer int, volunteerExperience varchar(255), conditions int, conditionDetails varchar(255), medication int, medicationDetails varchar(255), allerigies int, allergyDetails varchar(255), addressLine1 varchar(255), addressLine2 varchar(255), City varchar(255), postCode varchar(255))
BEGIN
	DECLARE new_id INT;
    
	INSERT into user(username, password) values(email, encpassword);
    
	SET new_id = (SELECT id FROM user WHERE username = email);
    
	INSERT into user_role(userid, role) values (new_id, "ROLE_ADMIN");
	INSERT into user_volunteer (user_id, first_name, surname, phone_number, emergency_number, mha_tennant, previous_volunteer, volunteer_experience, conditions, condition_details, medication, medication_details, allergies, allergy_details, current_balance, total_amount) values (new_id, firstName, surName, pNumber, eNumber, mhaTenant, previousVolunteer, volunteerExperience, conditions, conditionDetails, medication, medicationDetails, allerigies, allergyDetails, 0, 0);
	INSERT into address (address_line_1, address_line_2, city, postcode, user_id) values (addressLine1, addressLine2, City, postCode, new_id);

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `deleteBusiness`;

DELIMITER $$
CREATE PROCEDURE `deleteBusiness`(businessid INTEGER)
BEGIN
	declare bid int;
    
    set bid = (select id from business where id = businessid);
	
	DELETE from business where id = bid;

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `deleteReward`;

DELIMITER $$
CREATE PROCEDURE `deleteReward`(IN rid int)
BEGIN

	UPDATE reward
    SET active = 0
    WHERE id = rid;
    
    IF (SELECT active FROM reward WHERE id = rid) = 0 THEN
		SET rid = 0;
	ELSE
		SET rid = 1;
	END IF;

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `deleteUser`;

DELIMITER $$
CREATE PROCEDURE `deleteUser`(uid Integer)
BEGIN
	DELETE from address where user_id = uid;
	DELETE from user_volunteer where user_id = uid;
    DELETE from user_role where userid = uid;
    DELETE from allocation where user_id = uid;
    DELETE from redemption where user_id = uid;
    DELETE from user_volunteer where user_id = uid;
    DELETE from user where id = uid;

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `getMissingAchievements`;

DELIMITER $$
CREATE PROCEDURE `getMissingAchievements`(uid INTEGER)
BEGIN
select * from achievement where credits <= (select total_amount from user_volunteer where user_id = uid) and id not in (select achievement_id from volunteer_achievement where user_id = uid);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `giveAchievement`;

DELIMITER $$
CREATE PROCEDURE `giveAchievement`(uid INTEGER, aid INTEGER)
BEGIN
	INSERT into volunteer_achievement (user_id, achievement_id, date) values (uid, aid, CURRENT_TIMESTAMP());
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `increaseRewardsNumTimesRedeemed`;

DELIMITER $$
CREATE PROCEDURE `increaseRewardsNumTimesRedeemed`(rewards_id int)
BEGIN
	DECLARE AddOne int;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			ROLLBACK;
			SELECT "Transaction cancelled because an SQL execption occured." as Message;
		END;

	START TRANSACTION;
		
	IF (SELECT id from reward WHERE id = rewards_id) IS NOT NULL THEN
		SET @AddOne = 1;
        
        IF ((SELECT num_times_redeemed from reward WHERE id = rewards_id) IS NOT NULL) = TRUE THEN
			UPDATE reward
			SET num_times_redeemed = num_times_redeemed + @AddOne
			WHERE rewards_id = id;
		ELSE
			UPDATE reward
			SET num_times_redeemed = 1
			WHERE rewards_id = id;
		END IF;
	END IF;
    
    COMMIT;
    
    SELECT "Reward Redemptions for: id=", r.id, ", name=", r.reward_name, ", was incremented successfully." as Message;
    
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `redeemReward`;

DELIMITER $$
CREATE PROCEDURE `redeemReward`(uid Integer, rid Integer)
BEGIN
	UPDATE user_volunteer SET current_balance = current_balance - (SELECT cost from reward where id = rid) where user_id = uid;
	INSERT into redemption(user_id, reward_id, redemption_date) values(uid, rid, CURRENT_TIMESTAMP());
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `registerUser`;

DELIMITER $$
CREATE PROCEDURE `registerUser`(email varchar(255), encpassword varchar(255), firstName varchar(255), surName varchar(255), pNumber varchar(15), eNumber varchar(15), mhaTenant int, previousVolunteer int, volunteerExperience varchar(255), conditions int, conditionDetails varchar(255), medication int, medicationDetails varchar(255), allerigies int, allergyDetails varchar(255), addressLine1 varchar(255), addressLine2 varchar(255), City varchar(255), postCode varchar(255))
BEGIN
	DECLARE new_id INT;
    
	INSERT into user(username, password) values(email, encpassword);
	SET new_id = (SELECT id FROM user WHERE username = email);
	INSERT into user_role(userid, role) values (new_id, "ROLE_USER");
	INSERT into user_volunteer (user_id, first_name, surname, phone_number, emergency_number, mha_tennant, previous_volunteer, volunteer_experience, conditions, condition_details, medication, medication_details, allergies, allergy_details, current_balance, total_amount) values (new_id, firstName, surName, pNumber, eNumber, mhaTenant, previousVolunteer, volunteerExperience, conditions, conditionDetails, medication, medicationDetails, allerigies, allergyDetails, 0, 0);
	INSERT into address (address_line_1, address_line_2, city, postcode, user_id) values (addressLine1, addressLine2, City, postCode, new_id);

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `viewRedemptions`;

DELIMITER $$
CREATE PROCEDURE `viewRedemptions`()
BEGIN
SELECT redemption.id AS `Redemption Id`, user.id AS `User Id`, user_volunteer.first_name AS `First Name`, user_volunteer.surname, reward.reward_name, redemption.redemption_date FROM redemption
JOIN user on redemption.user_id = user.id
JOIN user_volunteer
JOIN reward ON redemption.reward_id=reward.id;
END$$
DELIMITER ;

INSERT INTO user(username, password) values ('admin@mha.com', '$2a$10$f7XUPOWAfkFyiucChl2KnOqyLxL1g3KGij6EukR0t4FoUDvwz3ZSG');
INSERT INTO user_role(userid, role) values(1, 'ROLE_ADMIN');

INSERT INTO achievement(name, description, credits, file_name) values('Started Volunteering', 'You have earned your first volunteering credit, a few more hours of work and you could earn yourself a wonderful meal at Bean & Bread!', 1, 'achievement1.png');
INSERT INTO achievement(name, description, credits, file_name) values('Experienced Volunteer', 'You''ve already earned 10 credits for doing great work in the community! Perhaps it''s time to reward yourself with a day out to Raglan Castle on us, get your ticket on the rewards page.', 10, 'achievement2.png');
INSERT INTO achievement(name, description, credits, file_name) values('Veteran Volunteer', '50 credits earned in total, wow! Now would be a great time for a walk with Strolls ''n'' Stories, breathe in the fresh air and admire the beautiful country views as you think about how much great work you''ve done.', 50, 'achievement3.png');
INSERT INTO achievement(name, description, credits, file_name) values('Master Volunteer', 'We can''t congratulate you enough for earning 100 credits, every credit earned was you making a difference. We hope you have enjoyed your time spent volunteering so far and made the most of those credits by spending them on treats for yourself and others. Keep up the great work!', 100, 'achievement4.png');

INSERT INTO business (business_name, email_address) values ('Cracking Cupcake Company', 'ccc@ccc.com');
INSERT INTO reward (reward_name, reward_description, cost, business_id, num_times_redeemed, creator, active) values ('Free Cupcake', 'You get a free cupcake!', 3, 1, 0, 'admin@test.com', 1);
INSERT INTO reward (reward_name, reward_description, cost, business_id, num_times_redeemed, creator, active) values ('Free Mug of Shale Oil', 'Have a free mug of our finest Shale Oil to wash down the cupcakes!', 8, 1, 0, 'admin@test.com', 1);

INSERT INTO api_keys values ('A key for testing access', 'AIzaSyClzfrOzB818x55FASHvX4JuGQciR9lv7q');

CALL registerUser('volunteer@mha.com', '$2a$10$f7XUPOWAfkFyiucChl2KnOqyLxL1g3KGij6EukR0t4FoUDvwz3ZSG', 'Ben', 'Morris', '07935964692', '', 1, 0, '', 0, '', 0, '', 0, '', '8 Stratford Rd', '', 'Salisbury', 'SP1 3JH');







DROP SCHEMA IF EXISTS `mha_db_test`;
CREATE SCHEMA `mha_db_test`;
USE `mha_db_test`;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `user_role` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `userid` int(10) unsigned NOT NULL,
  `role` varchar(45) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_role_ibfk_1` (`userid`),
  CONSTRAINT `user_role_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `user_volunteer` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `first_name` varchar(20) DEFAULT NULL,
  `surname` varchar(30) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `emergency_number` varchar(15) NOT NULL,
  `mha_tennant` int(11) NOT NULL,
  `previous_volunteer` int(11) NOT NULL,
  `volunteer_experience` text DEFAULT NULL,
  `conditions` int(11) NOT NULL,
  `condition_details` text DEFAULT NULL,
  `medication` int(11) NOT NULL,
  `medication_details` text DEFAULT NULL,
  `allergies` int(11) NOT NULL,
  `allergy_details` text DEFAULT NULL,
  `current_balance` int(11) DEFAULT 0,
  `total_amount` int(11) DEFAULT 0,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `user_volunteer_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `achievement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `description` varchar(455) DEFAULT NULL,
  `credits` int(11) NOT NULL,
  `file_name` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `address` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `address_line_1` varchar(255) NOT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `postcode` varchar(10) NOT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `address_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `allocation` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `date_allocated` datetime NOT NULL,
  `credits_allocated` int(11) NOT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `user_id` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `allocation_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `business` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `business_name` varchar(25) NOT NULL,
  `email_address` varchar(50) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `business_changes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` varchar(255) DEFAULT NULL,
  `table_changed` varchar(255) DEFAULT NULL,
  `change_type` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `record_changes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `table_changed` varchar(45) DEFAULT NULL,
  `change_type` varchar(45) DEFAULT NULL,
  `description` varchar(500) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `reward` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `reward_name` varchar(50) NOT NULL,
  `reward_description` text NOT NULL,
  `cost` int(11) NOT NULL,
  `business_id` int(10) unsigned NOT NULL,
  `num_times_redeemed` int(11) DEFAULT NULL,
  `creator` varchar(50) DEFAULT NULL,
  `active` int(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `business_id` (`business_id`),
  CONSTRAINT `reward_ibfk_1` FOREIGN KEY (`business_id`) REFERENCES `business` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `redemption` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `reward_id` int(10) unsigned NOT NULL,
  `redemption_date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `reward_id` (`reward_id`),
  CONSTRAINT `redemption_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `redemption_ibfk_2` FOREIGN KEY (`reward_id`) REFERENCES `reward` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `reward_changes` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `date` datetime NOT NULL,
  `table_changed` varchar(45) NOT NULL,
  `r_description` varchar(500) DEFAULT NULL,
  `r_name` varchar(500) DEFAULT NULL,
  `changer` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `volunteer_achievement` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(10) unsigned NOT NULL,
  `achievement_id` int(11) NOT NULL,
  `date` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `achievement_id` (`achievement_id`),
  CONSTRAINT `volunteer_achievement_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`),
  CONSTRAINT `volunteer_achievement_ibfk_2` FOREIGN KEY (`achievement_id`) REFERENCES `achievement` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

CREATE TABLE `volunteer_application` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `first_name` varchar(20) NOT NULL,
  `surname` varchar(30) NOT NULL,
  `username` varchar(50) NOT NULL,
  `phone_number` varchar(15) NOT NULL,
  `emergency_number` varchar(15) NOT NULL,
  `address_line_1` varchar(255) NOT NULL,
  `address_line_2` varchar(255) DEFAULT NULL,
  `city` varchar(50) NOT NULL,
  `postcode` varchar(10) NOT NULL,
  `mha_tennant` int(11) NOT NULL,
  `previous_volunteer` int(11) NOT NULL,
  `volunteer_experience` text DEFAULT NULL,
  `conditions` int(11) NOT NULL,
  `condition_details` text DEFAULT NULL,
  `medication` int(11) NOT NULL,
  `medication_details` text DEFAULT NULL,
  `allergies` text NOT NULL,
  `allergy_details` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;

DROP PROCEDURE IF EXISTS `createSystemAdministrator`;

DELIMITER $$
CREATE PROCEDURE `createSystemAdministrator`(email varchar(255), encpassword varchar(255), firstName varchar(255), surName varchar(255), pNumber varchar(15), eNumber varchar(15), mhaTenant int, previousVolunteer int, volunteerExperience varchar(255), conditions int, conditionDetails varchar(255), medication int, medicationDetails varchar(255), allerigies int, allergyDetails varchar(255), addressLine1 varchar(255), addressLine2 varchar(255), City varchar(255), postCode varchar(255))
BEGIN
	DECLARE new_id INT;
    
	INSERT into user(username, password) values(email, encpassword);
    
	SET new_id = (SELECT id FROM user WHERE username = email);
    
	INSERT into user_role(userid, role) values (new_id, "ROLE_ADMIN");
	INSERT into user_volunteer (user_id, first_name, surname, phone_number, emergency_number, mha_tennant, previous_volunteer, volunteer_experience, conditions, condition_details, medication, medication_details, allergies, allergy_details, current_balance, total_amount) values (new_id, firstName, surName, pNumber, eNumber, mhaTenant, previousVolunteer, volunteerExperience, conditions, conditionDetails, medication, medicationDetails, allerigies, allergyDetails, 0, 0);
	INSERT into address (address_line_1, address_line_2, city, postcode, user_id) values (addressLine1, addressLine2, City, postCode, new_id);

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `deleteBusiness`;

DELIMITER $$
CREATE PROCEDURE `deleteBusiness`(businessid INTEGER)
BEGIN
	declare bid int;
    
    set bid = (select id from business where id = businessid);
	
	DELETE from business where id = bid;

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `deleteReward`;

DELIMITER $$
CREATE PROCEDURE `deleteReward`(IN rid int)
BEGIN

	UPDATE reward
    SET active = 0
    WHERE id = rid;
    
    IF (SELECT active FROM reward WHERE id = rid) = 0 THEN
		SET rid = 0;
	ELSE
		SET rid = 1;
	END IF;

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `deleteUser`;

DELIMITER $$
CREATE PROCEDURE `deleteUser`(uid Integer)
BEGIN
	DELETE from address where user_id = uid;
	DELETE from user_volunteer where user_id = uid;
    DELETE from user_role where userid = uid;
    DELETE from allocation where user_id = uid;
    DELETE from redemption where user_id = uid;
    DELETE from user_volunteer where user_id = uid;
    DELETE from user where id = uid;

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `getMissingAchievements`;

DELIMITER $$
CREATE PROCEDURE `getMissingAchievements`(uid INTEGER)
BEGIN
select * from achievement where credits <= (select total_amount from user_volunteer where user_id = uid) and id not in (select achievement_id from volunteer_achievement where user_id = uid);
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `giveAchievement`;

DELIMITER $$
CREATE PROCEDURE `giveAchievement`(uid INTEGER, aid INTEGER)
BEGIN
	INSERT into volunteer_achievement (user_id, achievement_id, date) values (uid, aid, CURRENT_TIMESTAMP());
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `increaseRewardsNumTimesRedeemed`;

DELIMITER $$
CREATE PROCEDURE `increaseRewardsNumTimesRedeemed`(rewards_id int)
BEGIN
	DECLARE AddOne int;
    
    DECLARE EXIT HANDLER FOR SQLEXCEPTION 
		BEGIN
			ROLLBACK;
			SELECT "Transaction cancelled because an SQL execption occured." as Message;
		END;

	START TRANSACTION;
		
	IF (SELECT id from reward WHERE id = rewards_id) IS NOT NULL THEN
		SET @AddOne = 1;
        
        IF ((SELECT num_times_redeemed from reward WHERE id = rewards_id) IS NOT NULL) = TRUE THEN
			UPDATE reward
			SET num_times_redeemed = num_times_redeemed + @AddOne
			WHERE rewards_id = id;
		ELSE
			UPDATE reward
			SET num_times_redeemed = 1
			WHERE rewards_id = id;
		END IF;
	END IF;
    
    COMMIT;
    
    SELECT "Reward Redemptions for: id=", r.id, ", name=", r.reward_name, ", was incremented successfully." as Message;
    
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `redeemReward`;

DELIMITER $$
CREATE PROCEDURE `redeemReward`(uid Integer, rid Integer)
BEGIN
	UPDATE user_volunteer SET current_balance = current_balance - (SELECT cost from reward where id = rid) where user_id = uid;
	INSERT into redemption(user_id, reward_id, redemption_date) values(uid, rid, CURRENT_TIMESTAMP());
END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `registerUser`;

DELIMITER $$
CREATE PROCEDURE `registerUser`(email varchar(255), encpassword varchar(255), firstName varchar(255), surName varchar(255), pNumber varchar(15), eNumber varchar(15), mhaTenant int, previousVolunteer int, volunteerExperience varchar(255), conditions int, conditionDetails varchar(255), medication int, medicationDetails varchar(255), allerigies int, allergyDetails varchar(255), addressLine1 varchar(255), addressLine2 varchar(255), City varchar(255), postCode varchar(255))
BEGIN
	DECLARE new_id INT;
    
	INSERT into user(username, password) values(email, encpassword);
	SET new_id = (SELECT id FROM user WHERE username = email);
	INSERT into user_role(userid, role) values (new_id, "ROLE_USER");
	INSERT into user_volunteer (user_id, first_name, surname, phone_number, emergency_number, mha_tennant, previous_volunteer, volunteer_experience, conditions, condition_details, medication, medication_details, allergies, allergy_details, current_balance, total_amount) values (new_id, firstName, surName, pNumber, eNumber, mhaTenant, previousVolunteer, volunteerExperience, conditions, conditionDetails, medication, medicationDetails, allerigies, allergyDetails, 0, 0);
	INSERT into address (address_line_1, address_line_2, city, postcode, user_id) values (addressLine1, addressLine2, City, postCode, new_id);

END$$
DELIMITER ;

DROP PROCEDURE IF EXISTS `viewRedemptions`;

DELIMITER $$
CREATE PROCEDURE `viewRedemptions`()
BEGIN
SELECT redemption.id AS `Redemption Id`, user.id AS `User Id`, user_volunteer.first_name AS `First Name`, user_volunteer.surname, reward.reward_name, redemption.redemption_date FROM redemption
JOIN user on redemption.user_id = user.id
JOIN user_volunteer
JOIN reward ON redemption.reward_id=reward.id;
END$$
DELIMITER ;

INSERT INTO user(username, password) values ('admin@test.com', '$2a$10$f7XUPOWAfkFyiucChl2KnOqyLxL1g3KGij6EukR0t4FoUDvwz3ZSG');
INSERT INTO user_role(userid, role) values(1, 'ROLE_ADMIN');

INSERT INTO achievement(name, description, credits, file_name) values('Started Volunteering', 'You have earned your first volunteering credit, a few more hours of work and you could earn yourself a wonderful meal at Bean & Bread!', 1, 'achievement1.png');
INSERT INTO achievement(name, description, credits, file_name) values('Experienced Volunteer', 'You''ve already earned 10 credits for doing great work in the community! Perhaps it''s time to reward yourself with a day out to Raglan Castle on us, get your ticket on the rewards page.', 10, 'achievement2.png');
INSERT INTO achievement(name, description, credits, file_name) values('Veteran Volunteer', '50 credits earned in total, wow! Now would be a great time for a walk with Strolls ''n'' Stories, breathe in the fresh air and admire the beautiful country views as you think about how much great work you''ve done.', 50, 'achievement3.png');
INSERT INTO achievement(name, description, credits, file_name) values('Master Volunteer', 'We can''t congratulate you enough for earning 100 credits, every credit earned was you making a difference. We hope you have enjoyed your time spent volunteering so far and made the most of those credits by spending them on treats for yourself and others. Keep up the great work!', 100, 'achievement4.png');

INSERT INTO business (business_name, email_address) values ('Cracking Cupcake Company', 'ccc@ccc.com');
INSERT INTO reward (reward_name, reward_description, cost, business_id, num_times_redeemed, creator, active) values ('Free Cupcake', 'You get a free cupcake!', 3, 1, 0, 'admin@test.com', 1);
INSERT INTO reward (reward_name, reward_description, cost, business_id, num_times_redeemed, creator, active) values ('Free Mug of Shale Oil', 'Have a free mug of our finest Shale Oil to wash down the cupcakes!', 8, 1, 0, 'admin@test.com', 1);

CALL registerUser('volunteer@test.com', '$2a$10$f7XUPOWAfkFyiucChl2KnOqyLxL1g3KGij6EukR0t4FoUDvwz3ZSG', 'Lakesh', 'Morris', '07935964692', '', 1, 0, '', 0, '', 0, '', 0, '', '8 Stratford Rd', '', 'Salisbury', 'SP1 3JH');