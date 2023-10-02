
DELIMITER $
CREATE PROCEDURE PR_LOGS( IN statementType 		varchar(255) ,
						IN detail		      	varchar(255),
                        IN userId				bigint)
	BEGIN
		IF (statementType = 'disable stop')
			THEN
				insert into operationLogs(DETAIL,LABEL,TIMESTAMP,USER_ID,COLOR)
				values(detail,'disable stop',NOW(),userId,'#F4F413');
			END IF;
		IF (statementType = 'account suspension')
			THEN
				insert into operationLogs (DETAIL,LABEL,TIMESTAMP,USER_ID,COLOR)
				values(detail,'account suspension',NOW(),userId,'#F4F413');
			END IF;

		IF (statementType = 'change')
			THEN
				insert into operationLogs (DETAIL,LABEL,TIMESTAMP,USER_ID,COLOR)
				values(detail,'change',NOW(),userId,'#f6b26b');
			END IF;
		IF (statementType = 'Guild')
			THEN
				insert into gameLogs (DETAIL,LABEL,TIMESTAMP,USER_ID,COLOR)
				values(detail,'Guild',NOW(),userId,'#F4F413');
			END IF;
		IF (statementType = 'Minigames')
			THEN
				insert into gameLogs (DETAIL,LABEL,TIMESTAMP,USER_ID,COLOR)
				values(detail,'Minigames',NOW(),userId,'#F4F413');
			END IF;
		IF (statementType = 'Daily quests')
			THEN
				insert into gameLogs (DETAIL,LABEL,TIMESTAMP,USER_ID,COLOR)
				values(detail,'Daily quests',NOW(),userId,'#F4F413');
			END IF;
END $
DELIMITER ;

DELIMITER $$
CREATE TRIGGER before_update_user before update on users for each row
BEGIN
	if (new.STATUS = 4) then
		call PR_LOGS('disable stop', 'Stop the account because it was removed from the admin', new.ID);
	end if;
	if (NOW() between new.SUSPENSION_START and new.SUSPENSION_END) then
		call PR_LOGS('account suspension',concat(new.SUSPENSION_START,'~', new.SUSPENSION_END , ' Violation of operation policy due to abusive behavior'), new.ID);
	end if;
END$$
DELIMITER ;

DELIMITER $$
CREATE trigger before_insert_dojo_member after insert on dojo_member for each row
BEGIN

	SELECT user_character.USER_ID INTO @id
			FROM user_character
				JOIN dojo_member
				ON dojo_member.CHARACTER_ID = user_character.CHARACTER_ID
				where user_character.CHARACTER_ID = new.CHARACTER_ID
			limit 1;
	SELECT DOJO_NAME INTO @dojo_name
		FROM dojo_member JOIN dojo
			ON dojo.ID = dojo_member.DOJO_ID
			where dojo.ID = new.DOJO_ID
		limit 1;
	call PR_LOGS('Guild',concat('Joined guild <', @dojo_name,'>'), @id);
END$$
DELIMITER ;

DELIMITER $$
CREATE trigger after_deleted_dojo_member after delete on dojo_member for each row
BEGIN

	SELECT user_character.USER_ID INTO @id
			FROM user_character
				JOIN dojo_member
				ON dojo_member.CHARACTER_ID = user_character.CHARACTER_ID
				where user_character.CHARACTER_ID = old.CHARACTER_ID
			limit 1;
	SELECT DOJO_NAME INTO @dojo_name
		FROM dojo_member JOIN dojo
			ON dojo.ID = dojo_member.DOJO_ID
			where dojo.ID = old.DOJO_ID
		limit 1;
	call PR_LOGS('Guild',concat('Leaving guild <', @dojo_name,'>'), @id);
END$$
DELIMITER ;

DELIMITER $$
CREATE trigger before_update_user_character before update on user_character for each row
BEGIN
	 if (new.CHARACTER_NAME != old.CHARACTER_NAME) then
		call PR_LOGS('change',concat('Changed character name to ', new.CHARACTER_NAME), new.USER_ID);
	end if;
END$$
DELIMITER ;

-- update mini_game_record
DELIMITER $$
create trigger after_update_miniGameRecord after update on mini_game_record for each row
BEGIN
	 if (new.BEST_RECORD != old.BEST_RECORD) then
		SELECT user_character.USER_ID INTO @id
			FROM user_character
				JOIN mini_game_record
				ON mini_game_record.CHARACTER_ID = user_character.CHARACTER_ID
				where mini_game_record.BEST_RECORD = new.BEST_RECORD and new.BEST_RECORD <> old.BEST_RECORD
			limit 1;
		SELECT mini_game.NAME INTO @minigameName
			FROM mini_game
				JOIN mini_game_record
				ON mini_game_record.GAME_ID = mini_game.ID
				where mini_game_record.BEST_RECORD = new.BEST_RECORD and new.BEST_RECORD <> old.BEST_RECORD
			limit 1;
		call PR_LOGS('Minigames', concat('The best record in ', @minigameName, ' is ', new.BEST_RECORD), @id);
	end if;
END$$
DELIMITER ;


-- update character_quest_detail
DELIMITER $$
create trigger after_update_character_quest_detail after update on character_quest_detail for each row
BEGIN

	-- Accept quest
	 if (new.IS_ACCEPT = 1 and old.IS_ACCEPT = 0) then
		SELECT user_character.USER_ID INTO @id
			FROM user_character
				JOIN character_quest_detail
				ON character_quest_detail.CHARACTER_ID = user_character.CHARACTER_ID
				where new.IS_ACCEPT = 1 and old.IS_ACCEPT = 0
			limit 1;
		SELECT quest_info.QUEST_NAME INTO @questName
			FROM quest_info
				JOIN character_quest_detail
				ON character_quest_detail.QUEST_ID = quest_info.QUEST_ID
				where new.IS_ACCEPT = 1 and old.IS_ACCEPT = 0
			limit 1;
		call PR_LOGS('Daily quests',concat('Accept quest of ', @questName), @id);
	end if;

    -- Completed quest
	 if (new.IS_COMPLETED = 1 and old.IS_COMPLETED = 0) then
		SELECT user_character.USER_ID INTO @id
			FROM user_character
				JOIN character_quest_detail
				ON character_quest_detail.CHARACTER_ID = user_character.CHARACTER_ID
				where new.IS_COMPLETED = 1 and old.IS_COMPLETED = 0
			limit 1;
		SELECT quest_info.QUEST_NAME INTO @questName
			FROM quest_info
				JOIN character_quest_detail
				ON character_quest_detail.QUEST_ID = quest_info.QUEST_ID
				where new.IS_COMPLETED = 1 and old.IS_COMPLETED = 0
			limit 1;
		call PR_LOGS('Daily quests',concat('Complete quest of ', @questName), @id);
	end if;
END$$
DELIMITER ;


