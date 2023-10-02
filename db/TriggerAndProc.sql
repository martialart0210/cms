--PROCEDURES
--add_quest_info
CREATE DEFINER=`root`@`localhost` PROCEDURE `martial_arts_meta_dev`.`add_quest_info`()
begin
declare done INT default false;
declare _char_id INT;
declare curs_char cursor for select CHARACTER_ID from user_character uc ;
open curs_char;

	Reading_Quest: loop
		fetch curs_char
	into
		_char_id;
	if done then leave Reading_Quest;
	end if;
	INSERT INTO character_quest_detail
	(IS_ACCEPT, IS_COMPLETED, IS_FINISHED, MAX_PERFORMED, PERFORMED_COUNT, CHARACTER_ID, QUEST_ID)
	VALUES(0, 0, 0, 1, 0, _char_id, 1);
		INSERT INTO character_quest_detail
	(IS_ACCEPT, IS_COMPLETED, IS_FINISHED, MAX_PERFORMED, PERFORMED_COUNT, CHARACTER_ID, QUEST_ID)
	VALUES(0, 0, 0, 1, 0, _char_id, 2);
	end loop;
close curs_char;
end

--insertCostume
CREATE DEFINER=`root`@`localhost` PROCEDURE `martial_arts_meta_dev`.`insertCostume`()
BEGIN
    DECLARE i int DEFAULT 1;

WHILE i <= 10 DO
        INSERT
	INTO
	costume (COSTUME_ID,
	COSTUME_NAME,
	COSTUME_PRICE,
	DESCRIPTION,
	GENDER,
	COSTUME_TYPE)
VALUES (i,
Concat('Costume ', i),
FLOOR(300 + RAND()*(1-300)),
Concat('Costume ', i),
MOD(FLOOR(RAND()*10), 2),
FLOOR(4 + RAND()*(0-4)) );

INSERT INTO shop_costume (ID,IS_SOLD_OUT,COSTUME_ID,SHOP_ID) VALUES
(i,0,i,3);

INSERT INTO my_room_item
(ITEM_ID, DESCRIPTION, HEIGHT, ITEM_NAME, ITEM_PRICE, `LENGTH`, ITEM_TYPE, WIDTH)
VALUES(i, Concat('ITEM TEST ',i), 3, Concat('TEST ',i), FLOOR(300 + RAND()*(1-300)), FLOOR(6 + RAND()*(1-6)), FLOOR(6 + RAND()*(1-6)), FLOOR(6 + RAND()*(1-6)));

INSERT INTO shop_room_item
(ID, IS_SOLD_OUT, ITEM_ID, SHOP_ID)
VALUES(i, 0, i, 3);


SET
i = i + 1;
END WHILE;
END

--TRIGGERS
--my_room_item.ADD_TO_SHOP
CREATE DEFINER=`root`@`localhost` TRIGGER `ADD_TO_SHOP` AFTER INSERT ON `my_room_item` FOR EACH ROW BEGIN
	DECLARE done INT DEFAULT FALSE ;
	DECLARE _shop_id INT;
	DECLARE curs_shop cursor for select ID from shop s WHERE SHOP_TYPE = 1 ;
	DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

open curs_shop;

	Reading_Shop: loop
		fetch curs_shop
	into
		_shop_id;

	if done then
	                leave Reading_Shop;
	end if;
	INSERT INTO shop_room_item
	(IS_SOLD_OUT, ITEM_ID, SHOP_ID)
	VALUES(0, new.ITEM_ID, _shop_id);
	end loop;
close curs_shop;
END

--shop.NEW_SHOP_DATA
CREATE DEFINER=`root`@`localhost` TRIGGER `NEW_SHOP_DATA` AFTER INSERT ON `shop` FOR EACH ROW begin
declare done INT default false;
declare _item_id INT;
declare _minigame_id INT;
declare curs_costume cursor for select COSTUME_ID from costume c ;
declare curs_minigame cursor for select ID from mini_game mg ;
declare curs_myroom_item cursor for select ITEM_ID from my_room_item mri  ;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

IF new.SHOP_TYPE = 0 THEN
	open curs_costume;

		Reading_Costume: loop
			fetch curs_costume
		into
			_item_id;

		if done then
		                leave Reading_Costume;
		end if;

		insert
			into
			shop_costume
		(IS_SOLD_OUT,
			COSTUME_ID,
			SHOP_ID)
		values(0,
		_item_id,
		new.ID);
		end loop;
	close curs_costume;
ELSE
	open curs_myroom_item;

		Reading_Myroom_Item: loop
			fetch curs_myroom_item
		into
			_item_id;

		if done then
		                leave Reading_Myroom_Item;
		end if;

		insert
			into
			shop_room_item
		(IS_SOLD_OUT,
			ITEM_ID,
			SHOP_ID)
		values(0,
		_item_id,
		new.ID);
		end loop;
	close curs_myroom_item;
END IF;
END

--user_character.NEW_DATA
CREATE DEFINER=`root`@`localhost` TRIGGER `NEW_DATA` AFTER INSERT ON `user_character` FOR EACH ROW begin
declare done INT default false;
declare _minigame_id INT;
declare _quest_id INT;
declare curs_minigame cursor for select ID from mini_game mg ;
declare curs_quest cursor for select QUEST_ID from quest_info qi ;
DECLARE CONTINUE HANDLER FOR NOT FOUND SET done = TRUE;

INSERT INTO martial_arts_meta_dev.shop(SHOP_TYPE, CHARACTER_ID) VALUES(0, new.CHARACTER_ID);
INSERT INTO martial_arts_meta_dev.shop(SHOP_TYPE, CHARACTER_ID) VALUES(1, new.CHARACTER_ID);

open curs_minigame;

	Reading_Minigame: loop
		fetch curs_minigame
	into
		_minigame_id;

	if done then
	                leave Reading_Minigame;
	end if;

	INSERT INTO mini_game_record
	(IS_CLAIMED, UPDATED_AT, CHARACTER_ID, GAME_ID, BEST_AT, BEST_RECORD, DAILY_RECORD)
	values(0,NOW(),new.CHARACTER_ID,_minigame_id,NULL,0,0);
	end loop;
close curs_minigame;

SET done = FALSE ;
open curs_quest;

	Reading_Quest: loop
		fetch curs_quest
	into
		_quest_id;
	if done then leave Reading_Quest;
	end if;
	INSERT INTO character_quest_detail
	(IS_ACCEPT, IS_COMPLETED, IS_FINISHED, MAX_PERFORMED, PERFORMED_COUNT, CHARACTER_ID, QUEST_ID)
	VALUES(0, 0, 0, 1, 0, new.CHARACTER_ID, _quest_id);

	end loop;
close curs_quest;

end