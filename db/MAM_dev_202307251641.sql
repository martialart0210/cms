-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: martial_arts_meta_dev
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `character_quest_detail`
--

DROP TABLE IF EXISTS `character_quest_detail`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `character_quest_detail` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `IS_ACCEPT` bit(1) NOT NULL,
  `IS_COMPLETED` bit(1) NOT NULL,
  `IS_FINISHED` bit(1) NOT NULL,
  `MAX_PERFORMED` int NOT NULL,
  `PERFORMED_COUNT` int NOT NULL,
  `CHARACTER_ID` bigint NOT NULL,
  `QUEST_ID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKmoewyu54jmsj8sppc04bre8sh` (`CHARACTER_ID`),
  KEY `FKo5qcwnp3dn15kw8kuqynxtdc6` (`QUEST_ID`),
  CONSTRAINT `FKmoewyu54jmsj8sppc04bre8sh` FOREIGN KEY (`CHARACTER_ID`) REFERENCES `user_character` (`CHARACTER_ID`),
  CONSTRAINT `FKo5qcwnp3dn15kw8kuqynxtdc6` FOREIGN KEY (`QUEST_ID`) REFERENCES `quest_info` (`QUEST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=18 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `character_quest_detail`
--

LOCK TABLES `character_quest_detail` WRITE;
/*!40000 ALTER TABLE `character_quest_detail` DISABLE KEYS */;
INSERT INTO `character_quest_detail` VALUES (2,_binary '\0',_binary '\0',_binary '\0',1,0,2,1),(3,_binary '\0',_binary '\0',_binary '\0',1,0,11,1),(4,_binary '\0',_binary '\0',_binary '\0',1,0,12,1),(5,_binary '\0',_binary '\0',_binary '\0',1,0,13,1),(6,_binary '\0',_binary '\0',_binary '\0',1,0,14,1),(7,_binary '\0',_binary '\0',_binary '\0',1,0,15,1),(8,_binary '\0',_binary '\0',_binary '\0',1,0,16,1),(9,_binary '\0',_binary '\0',_binary '\0',1,0,17,1),(10,_binary '\0',_binary '\0',_binary '\0',1,0,2,2),(11,_binary '\0',_binary '\0',_binary '\0',1,0,11,2),(12,_binary '\0',_binary '\0',_binary '\0',1,0,12,2),(13,_binary '\0',_binary '\0',_binary '\0',1,0,13,2),(14,_binary '\0',_binary '\0',_binary '\0',1,0,14,2),(15,_binary '\0',_binary '\0',_binary '\0',1,0,15,2),(16,_binary '\0',_binary '\0',_binary '\0',1,0,16,2),(17,_binary '\0',_binary '\0',_binary '\0',1,0,17,2);
/*!40000 ALTER TABLE `character_quest_detail` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `contact`
--

DROP TABLE IF EXISTS `contact`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `contact` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CONTACT_TYPE` smallint NOT NULL,
  `CHARACTER_ID` bigint NOT NULL,
  `CONTACT_ID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKr4q5c5cferlay75gg9qpcemcl` (`CHARACTER_ID`),
  KEY `FKqfixeyrik4i4w6ssnju1cnfkg` (`CONTACT_ID`),
  CONSTRAINT `FKqfixeyrik4i4w6ssnju1cnfkg` FOREIGN KEY (`CONTACT_ID`) REFERENCES `user_character` (`CHARACTER_ID`),
  CONSTRAINT `FKr4q5c5cferlay75gg9qpcemcl` FOREIGN KEY (`CHARACTER_ID`) REFERENCES `user_character` (`CHARACTER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `contact`
--

LOCK TABLES `contact` WRITE;
/*!40000 ALTER TABLE `contact` DISABLE KEYS */;
/*!40000 ALTER TABLE `contact` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `costume`
--

DROP TABLE IF EXISTS `costume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `costume` (
  `COSTUME_ID` bigint NOT NULL AUTO_INCREMENT,
  `COSTUME_NAME` varchar(255) NOT NULL,
  `COSTUME_PRICE` int NOT NULL,
  `DESCRIPTION` varchar(255) NOT NULL,
  `GENDER` smallint DEFAULT NULL,
  `COSTUME_TYPE` smallint DEFAULT NULL,
  PRIMARY KEY (`COSTUME_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `costume`
--

LOCK TABLES `costume` WRITE;
/*!40000 ALTER TABLE `costume` DISABLE KEYS */;
/*!40000 ALTER TABLE `costume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dojo`
--

DROP TABLE IF EXISTS `dojo`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dojo` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `ACCEPT_TYPE` smallint DEFAULT NULL,
  `CREATED_AT` datetime(6) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `DOJO_NAME` varchar(255) DEFAULT NULL,
  `LIMIT_SUB` int DEFAULT NULL,
  `CLOSED_DATE` datetime(6) DEFAULT NULL,
  `GUILD_LEADER` varchar(255) DEFAULT NULL,
  `MEMBERS` int DEFAULT NULL,
  `DOJO_STATUS` smallint NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dojo`
--

LOCK TABLES `dojo` WRITE;
/*!40000 ALTER TABLE `dojo` DISABLE KEYS */;
/*!40000 ALTER TABLE `dojo` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dojo_member`
--

DROP TABLE IF EXISTS `dojo_member`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dojo_member` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CREATED_AT` datetime(6) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `position` smallint DEFAULT NULL,
  `CHARACTER_ID` bigint NOT NULL,
  `DOJO_ID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKofcor1dmc1l0qdk0p1akohqp8` (`CHARACTER_ID`),
  KEY `FKkj2gt2i22ucht2pvd0yw76ugr` (`DOJO_ID`),
  CONSTRAINT `FKkj2gt2i22ucht2pvd0yw76ugr` FOREIGN KEY (`DOJO_ID`) REFERENCES `dojo` (`ID`),
  CONSTRAINT `FKofcor1dmc1l0qdk0p1akohqp8` FOREIGN KEY (`CHARACTER_ID`) REFERENCES `user_character` (`CHARACTER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dojo_member`
--

LOCK TABLES `dojo_member` WRITE;
/*!40000 ALTER TABLE `dojo_member` DISABLE KEYS */;
/*!40000 ALTER TABLE `dojo_member` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dojo_request`
--

DROP TABLE IF EXISTS `dojo_request`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `dojo_request` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `CREATED_AT` datetime(6) DEFAULT NULL,
  `CREATED_BY` varchar(255) DEFAULT NULL,
  `REQUEST_STATUS` smallint DEFAULT NULL,
  `UPDATED_AT` datetime(6) DEFAULT NULL,
  `UPDATED_BY` varchar(255) DEFAULT NULL,
  `CHARACTER_ID` bigint NOT NULL,
  `DOJO_ID` bigint NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKo8cbqei7empp3if7c19nv26w4` (`CHARACTER_ID`),
  KEY `FKcmuu841hhd84ywtuv9wnm55cm` (`DOJO_ID`),
  CONSTRAINT `FKcmuu841hhd84ywtuv9wnm55cm` FOREIGN KEY (`DOJO_ID`) REFERENCES `dojo` (`ID`),
  CONSTRAINT `FKo8cbqei7empp3if7c19nv26w4` FOREIGN KEY (`CHARACTER_ID`) REFERENCES `user_character` (`CHARACTER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dojo_request`
--

LOCK TABLES `dojo_request` WRITE;
/*!40000 ALTER TABLE `dojo_request` DISABLE KEYS */;
/*!40000 ALTER TABLE `dojo_request` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `drawner_item`
--

DROP TABLE IF EXISTS `drawner_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `drawner_item` (
  `ITEM_ID` bigint NOT NULL,
  `DRAWER_ID` bigint NOT NULL,
  KEY `FKdp5l0trb9s1506wnt4jauxfhp` (`DRAWER_ID`),
  KEY `FKs3m7enh2dw379p3v27pkn4c5p` (`ITEM_ID`),
  CONSTRAINT `FKdp5l0trb9s1506wnt4jauxfhp` FOREIGN KEY (`DRAWER_ID`) REFERENCES `room_drawer` (`DRAWER_ID`),
  CONSTRAINT `FKs3m7enh2dw379p3v27pkn4c5p` FOREIGN KEY (`ITEM_ID`) REFERENCES `my_room_item` (`ITEM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `drawner_item`
--

LOCK TABLES `drawner_item` WRITE;
/*!40000 ALTER TABLE `drawner_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `drawner_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mini_game`
--

DROP TABLE IF EXISTS `mini_game`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mini_game` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `REWARD` decimal(38,0) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mini_game`
--

LOCK TABLES `mini_game` WRITE;
/*!40000 ALTER TABLE `mini_game` DISABLE KEYS */;
/*!40000 ALTER TABLE `mini_game` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `mini_game_record`
--

DROP TABLE IF EXISTS `mini_game_record`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `mini_game_record` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `IS_CLAIMED` bit(1) DEFAULT NULL,
  `RECORD` int DEFAULT NULL,
  `UPDATED_AT` datetime(6) DEFAULT NULL,
  `CHARACTER_ID` bigint DEFAULT NULL,
  `GAME_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKeh6uhiqg4arf28bsuj8bpvkfr` (`CHARACTER_ID`),
  KEY `FK9vna5t1f871wu77s6m5oo2kqs` (`GAME_ID`),
  CONSTRAINT `FK9vna5t1f871wu77s6m5oo2kqs` FOREIGN KEY (`GAME_ID`) REFERENCES `mini_game` (`ID`),
  CONSTRAINT `FKeh6uhiqg4arf28bsuj8bpvkfr` FOREIGN KEY (`CHARACTER_ID`) REFERENCES `user_character` (`CHARACTER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `mini_game_record`
--

LOCK TABLES `mini_game_record` WRITE;
/*!40000 ALTER TABLE `mini_game_record` DISABLE KEYS */;
/*!40000 ALTER TABLE `mini_game_record` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_room`
--

DROP TABLE IF EXISTS `my_room`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_room` (
  `ROOM_ID` bigint NOT NULL AUTO_INCREMENT,
  `HEIGHT` int DEFAULT NULL,
  `LENGTH` int DEFAULT NULL,
  `LEVEL` int DEFAULT NULL,
  `WIDTH` int DEFAULT NULL,
  `FLOORING_ID` bigint DEFAULT NULL,
  `WALLPAPER_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ROOM_ID`),
  KEY `FKct3kib1qbxlson1fm295jssx7` (`FLOORING_ID`),
  KEY `FK4op074flhu89s1j8lq4wvgb40` (`WALLPAPER_ID`),
  CONSTRAINT `FK4op074flhu89s1j8lq4wvgb40` FOREIGN KEY (`WALLPAPER_ID`) REFERENCES `my_room_item` (`ITEM_ID`),
  CONSTRAINT `FKct3kib1qbxlson1fm295jssx7` FOREIGN KEY (`FLOORING_ID`) REFERENCES `my_room_item` (`ITEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_room`
--

LOCK TABLES `my_room` WRITE;
/*!40000 ALTER TABLE `my_room` DISABLE KEYS */;
INSERT INTO `my_room` VALUES (1,3,6,1,5,NULL,NULL),(2,3,6,1,5,NULL,NULL),(3,3,6,1,5,NULL,NULL),(4,3,6,1,5,NULL,NULL),(5,3,7,2,6,NULL,NULL),(6,3,6,1,5,NULL,NULL),(7,3,6,1,5,NULL,NULL),(8,3,6,1,5,NULL,NULL),(9,3,6,1,5,NULL,NULL);
/*!40000 ALTER TABLE `my_room` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_room_item`
--

DROP TABLE IF EXISTS `my_room_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_room_item` (
  `ITEM_ID` bigint NOT NULL AUTO_INCREMENT,
  `DESCRIPTION` varchar(255) NOT NULL,
  `HEIGHT` int DEFAULT NULL,
  `ITEM_NAME` varchar(255) NOT NULL,
  `ITEM_PRICE` int NOT NULL,
  `LENGTH` int DEFAULT NULL,
  `ITEM_TYPE` smallint DEFAULT NULL,
  `WIDTH` int DEFAULT NULL,
  PRIMARY KEY (`ITEM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_room_item`
--

LOCK TABLES `my_room_item` WRITE;
/*!40000 ALTER TABLE `my_room_item` DISABLE KEYS */;
INSERT INTO `my_room_item` VALUES (1,'ITEM TEST 1',3,'TEST 1',3000,2,0,2),(2,'ITEM TEST 2',3,'TEST 2',4000,4,0,3);
/*!40000 ALTER TABLE `my_room_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `my_room_item_place`
--

DROP TABLE IF EXISTS `my_room_item_place`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `my_room_item_place` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `HEIGHT` int DEFAULT NULL,
  `isFloorItem` bit(1) DEFAULT NULL,
  `itemId` bigint DEFAULT NULL,
  `LENGTH` int DEFAULT NULL,
  `rotateNumber` int DEFAULT NULL,
  `ITEM_TYPE` smallint DEFAULT NULL,
  `WIDTH` int DEFAULT NULL,
  `ROOM_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKjpj154nh8lo470l480vpnp73s` (`ROOM_ID`),
  CONSTRAINT `FKjpj154nh8lo470l480vpnp73s` FOREIGN KEY (`ROOM_ID`) REFERENCES `my_room` (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `my_room_item_place`
--

LOCK TABLES `my_room_item_place` WRITE;
/*!40000 ALTER TABLE `my_room_item_place` DISABLE KEYS */;
/*!40000 ALTER TABLE `my_room_item_place` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `npc`
--

DROP TABLE IF EXISTS `npc`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `npc` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `NPC_NAME` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `npc`
--

LOCK TABLES `npc` WRITE;
/*!40000 ALTER TABLE `npc` DISABLE KEYS */;
/*!40000 ALTER TABLE `npc` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `quest_info`
--

DROP TABLE IF EXISTS `quest_info`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `quest_info` (
  `QUEST_ID` bigint NOT NULL AUTO_INCREMENT,
  `QUEST_DESCRIPTION` varchar(255) NOT NULL,
  `QUEST_NAME` varchar(255) NOT NULL,
  `REWARD` int NOT NULL,
  PRIMARY KEY (`QUEST_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `quest_info`
--

LOCK TABLES `quest_info` WRITE;
/*!40000 ALTER TABLE `quest_info` DISABLE KEYS */;
INSERT INTO `quest_info` VALUES (1,'QUEST TEST 1 DES','QUEST TEST 1',100),(2,'QUEST TEST 2 DES','QUEST TEST 2',200);
/*!40000 ALTER TABLE `quest_info` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `ID` int NOT NULL,
  `CREATED_DATE` datetime(6) DEFAULT NULL,
  `CREATED_USER` varchar(20) DEFAULT NULL,
  `ROLE_DESCRIPTION` varchar(2000) DEFAULT NULL,
  `ROLE_NAME` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'2023-04-14 03:08:10.000000','ADMIN','SYSTEM ADMIN','ADMIN');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_drawer`
--

DROP TABLE IF EXISTS `room_drawer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_drawer` (
  `DRAWER_ID` bigint NOT NULL AUTO_INCREMENT,
  `ROOM_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`DRAWER_ID`),
  KEY `FK2ljbhx3wycmo4y680aaeswuui` (`ROOM_ID`),
  CONSTRAINT `FK2ljbhx3wycmo4y680aaeswuui` FOREIGN KEY (`ROOM_ID`) REFERENCES `my_room` (`ROOM_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_drawer`
--

LOCK TABLES `room_drawer` WRITE;
/*!40000 ALTER TABLE `room_drawer` DISABLE KEYS */;
INSERT INTO `room_drawer` VALUES (1,1);
/*!40000 ALTER TABLE `room_drawer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `room_scrap_book`
--

DROP TABLE IF EXISTS `room_scrap_book`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `room_scrap_book` (
  `SCRAP_BOOK_ID` bigint NOT NULL AUTO_INCREMENT,
  `ROOM_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`SCRAP_BOOK_ID`),
  KEY `FK1hah46aaamrxbr3hv1xqw2wli` (`ROOM_ID`),
  CONSTRAINT `FK1hah46aaamrxbr3hv1xqw2wli` FOREIGN KEY (`ROOM_ID`) REFERENCES `my_room` (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `room_scrap_book`
--

LOCK TABLES `room_scrap_book` WRITE;
/*!40000 ALTER TABLE `room_scrap_book` DISABLE KEYS */;
/*!40000 ALTER TABLE `room_scrap_book` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop`
--

DROP TABLE IF EXISTS `shop`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `SHOP_TYPE` smallint DEFAULT NULL,
  `CHARACTER_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKfpnhgyfowduuwfduy9se86841` (`CHARACTER_ID`),
  CONSTRAINT `FKfpnhgyfowduuwfduy9se86841` FOREIGN KEY (`CHARACTER_ID`) REFERENCES `user_character` (`CHARACTER_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop`
--

LOCK TABLES `shop` WRITE;
/*!40000 ALTER TABLE `shop` DISABLE KEYS */;
INSERT INTO `shop` VALUES (3,0,13),(4,1,13);
/*!40000 ALTER TABLE `shop` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_costume`
--

DROP TABLE IF EXISTS `shop_costume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_costume` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `IS_SOLD_OUT` bit(1) DEFAULT NULL,
  `COSTUME_ID` bigint DEFAULT NULL,
  `SHOP_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKfiu2fmbp33cnxih0b7x33kj27` (`COSTUME_ID`),
  KEY `FKtcsn145cy3534dfef9tl8hkd` (`SHOP_ID`),
  CONSTRAINT `FKfiu2fmbp33cnxih0b7x33kj27` FOREIGN KEY (`COSTUME_ID`) REFERENCES `costume` (`COSTUME_ID`),
  CONSTRAINT `FKtcsn145cy3534dfef9tl8hkd` FOREIGN KEY (`SHOP_ID`) REFERENCES `shop` (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_costume`
--

LOCK TABLES `shop_costume` WRITE;
/*!40000 ALTER TABLE `shop_costume` DISABLE KEYS */;
/*!40000 ALTER TABLE `shop_costume` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `shop_room_item`
--

DROP TABLE IF EXISTS `shop_room_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `shop_room_item` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `IS_SOLD_OUT` bit(1) DEFAULT NULL,
  `ITEM_ID` bigint DEFAULT NULL,
  `SHOP_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK9v6nlfni79ppqvy56j6s0ymhb` (`ITEM_ID`),
  KEY `FKnblpwt01w7k5x4fw8gsuarfx0` (`SHOP_ID`),
  CONSTRAINT `FK9v6nlfni79ppqvy56j6s0ymhb` FOREIGN KEY (`ITEM_ID`) REFERENCES `my_room_item` (`ITEM_ID`),
  CONSTRAINT `FKnblpwt01w7k5x4fw8gsuarfx0` FOREIGN KEY (`SHOP_ID`) REFERENCES `shop` (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `shop_room_item`
--

LOCK TABLES `shop_room_item` WRITE;
/*!40000 ALTER TABLE `shop_room_item` DISABLE KEYS */;
INSERT INTO `shop_room_item` VALUES (4,_binary '\0',1,3),(5,_binary '\0',2,4);
/*!40000 ALTER TABLE `shop_room_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_character`
--

DROP TABLE IF EXISTS `user_character`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_character` (
  `CHARACTER_ID` bigint NOT NULL AUTO_INCREMENT,
  `CHARACTER_MODEL` varchar(255) DEFAULT NULL,
  `CHARACTER_NAME` varchar(255) DEFAULT NULL,
  `GENDER` smallint DEFAULT NULL,
  `GOLD` decimal(38,0) DEFAULT NULL,
  `COSTUME_ACCESSORY` bigint DEFAULT NULL,
  `COSTUME_BOTTOM` bigint DEFAULT NULL,
  `COSTUME_HAIR` bigint DEFAULT NULL,
  `COSTUME_SHOE` bigint DEFAULT NULL,
  `COSTUME_TOP` bigint DEFAULT NULL,
  `ROOM_ID` bigint DEFAULT NULL,
  `USER_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`CHARACTER_ID`),
  KEY `FK9taqc0mkn3vo4ify80wv2imrw` (`COSTUME_ACCESSORY`),
  KEY `FK4uhawa1cj6bj9odcu1vlidann` (`COSTUME_BOTTOM`),
  KEY `FKpd6kturxo9hk256usw2clfkoc` (`COSTUME_HAIR`),
  KEY `FK4o74hpipnk4un2lmvkkiq2me4` (`COSTUME_SHOE`),
  KEY `FKikkr44iih7oc7kcrex7fmijnk` (`COSTUME_TOP`),
  KEY `FKffehg9kha7m7vwx9yahurhe74` (`ROOM_ID`),
  KEY `FKg53332sy0y9nlj3vgv5ea6kty` (`USER_ID`),
  CONSTRAINT `FK4o74hpipnk4un2lmvkkiq2me4` FOREIGN KEY (`COSTUME_SHOE`) REFERENCES `costume` (`COSTUME_ID`),
  CONSTRAINT `FK4uhawa1cj6bj9odcu1vlidann` FOREIGN KEY (`COSTUME_BOTTOM`) REFERENCES `costume` (`COSTUME_ID`),
  CONSTRAINT `FK9taqc0mkn3vo4ify80wv2imrw` FOREIGN KEY (`COSTUME_ACCESSORY`) REFERENCES `costume` (`COSTUME_ID`),
  CONSTRAINT `FKffehg9kha7m7vwx9yahurhe74` FOREIGN KEY (`ROOM_ID`) REFERENCES `my_room` (`ROOM_ID`),
  CONSTRAINT `FKg53332sy0y9nlj3vgv5ea6kty` FOREIGN KEY (`USER_ID`) REFERENCES `users` (`ID`),
  CONSTRAINT `FKikkr44iih7oc7kcrex7fmijnk` FOREIGN KEY (`COSTUME_TOP`) REFERENCES `costume` (`COSTUME_ID`),
  CONSTRAINT `FKpd6kturxo9hk256usw2clfkoc` FOREIGN KEY (`COSTUME_HAIR`) REFERENCES `costume` (`COSTUME_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_character`
--

LOCK TABLES `user_character` WRITE;
/*!40000 ALTER TABLE `user_character` DISABLE KEYS */;
INSERT INTO `user_character` VALUES (2,NULL,'TEST1',0,100,NULL,NULL,NULL,NULL,NULL,1,127),(11,NULL,'TEST2',0,100,NULL,NULL,NULL,NULL,NULL,3,129),(12,NULL,'TEST3',0,100,NULL,NULL,NULL,NULL,NULL,4,130),(13,NULL,'TEST4',0,100,NULL,NULL,NULL,NULL,NULL,5,131),(14,NULL,'TEST5',0,100,NULL,NULL,NULL,NULL,NULL,6,132),(15,NULL,'TEST6',0,100,NULL,NULL,NULL,NULL,NULL,7,133),(16,NULL,'TEST7',0,100,NULL,NULL,NULL,NULL,NULL,8,134),(17,NULL,'TEST8',0,100,NULL,NULL,NULL,NULL,NULL,9,135),(19,NULL,'Phuoc123',0,500,NULL,NULL,NULL,NULL,NULL,1,138),(20,NULL,'Test567',0,600,NULL,NULL,NULL,NULL,NULL,1,1);
/*!40000 ALTER TABLE `user_character` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `ID` int NOT NULL AUTO_INCREMENT,
  `ROLE_ID` int DEFAULT NULL,
  `USER_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (1,1,127),(2,1,128),(3,1,129),(4,1,130),(5,1,131),(6,1,132),(7,1,133),(8,1,134),(9,1,135);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `AGE_RANGE` varchar(255) DEFAULT NULL,
  `ANS_ADOPT_CNT` int DEFAULT NULL,
  `ANSWERED_CNT` int DEFAULT NULL,
  `ARTICLE_CNT` int DEFAULT NULL,
  `BAN_REASON` varchar(255) DEFAULT NULL,
  `BANK_ACC_NUM` varchar(255) DEFAULT NULL,
  `BANK_NAME` varchar(255) DEFAULT NULL,
  `BANK_OWNER` varchar(255) DEFAULT NULL,
  `CONNECTION_STATUS` bit(1) DEFAULT NULL,
  `CREATED_AT` datetime(6) DEFAULT NULL,
  `CUMUL_POINT` int DEFAULT NULL,
  `CURRENT_POINT` int DEFAULT NULL,
  `DELETED_AT` datetime(6) DEFAULT NULL,
  `SPC_AREA_CODE` varchar(255) DEFAULT NULL,
  `EMAIL` varchar(255) DEFAULT NULL,
  `EXPANSION_COUPON_NUMBER` int DEFAULT NULL,
  `FD_ACTVTY_CODE` varchar(255) DEFAULT NULL,
  `GENDER` char(1) DEFAULT NULL,
  `GET_PSNLQ_CNT` int DEFAULT NULL,
  `IMAGE_URL` varchar(2000) DEFAULT NULL,
  `IS_BANNED` char(1) DEFAULT NULL,
  `IS_UPGRADING` char(1) DEFAULT NULL,
  `LAST_ACCESS` datetime(6) DEFAULT NULL,
  `LC_ACTVTY_CODE` varchar(255) DEFAULT NULL,
  `LC_RESIDENCE` varchar(255) DEFAULT NULL,
  `LONG_INTRO` varchar(255) DEFAULT NULL,
  `MEMBER_TYPE` varchar(255) DEFAULT NULL,
  `MY_UPVOTE_CNT` int DEFAULT NULL,
  `NAME` varchar(255) DEFAULT NULL,
  `NICKNAME` varchar(255) DEFAULT NULL,
  `ONE_LINE_INTRO` varchar(255) DEFAULT NULL,
  `PASSWORD` varchar(255) DEFAULT NULL,
  `PHONE` varchar(255) DEFAULT NULL,
  `PROFILE_IMAGE` varchar(255) DEFAULT NULL,
  `PROVIDER` varchar(255) DEFAULT NULL,
  `PROVIDER_ID` varchar(255) DEFAULT NULL,
  `PSNLQ_CNT` int DEFAULT NULL,
  `QUESTION_CNT` int DEFAULT NULL,
  `REASON_SUSPENSION` varchar(255) DEFAULT NULL,
  `RECORD` varchar(255) DEFAULT NULL,
  `ROLE` varchar(20) DEFAULT NULL,
  `SCRAPED_CNT` int DEFAULT NULL,
  `STATUS` smallint DEFAULT NULL,
  `SUSPENSION_END` datetime(6) DEFAULT NULL,
  `SUSPENSION_START` datetime(6) DEFAULT NULL,
  `SYS_CREATED_CHARACTER` datetime(6) DEFAULT NULL,
  `TRIPNOTE_CNT` int DEFAULT NULL,
  `UPDATED_AT` datetime(6) DEFAULT NULL,
  `USER_TAGS` varchar(255) DEFAULT NULL,
  `USERNAME` varchar(255) DEFAULT NULL,
  `UUID` varchar(32) DEFAULT NULL,
  `MAXIMUM_ACCESS_USER` int DEFAULT NULL,
  `UPDATE_ACCESS_USER` datetime(6) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'30+',0,0,0,'',NULL,NULL,NULL,_binary '','2023-07-04 09:59:02.000000',0,0,NULL,NULL,'phuoc9999@gmail.com',0,NULL,'M',0,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,0,'phuoc234',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','556677444',NULL,NULL,NULL,0,0,NULL,NULL,NULL,0,3,'2024-11-18 07:00:00.000000','2008-11-15 07:00:00.000000','2023-07-07 05:34:42.287711',0,NULL,NULL,'phuoc125','456789'),(127,NULL,0,0,0,NULL,NULL,NULL,NULL,_binary '\0','2023-04-14 03:08:10.000000',0,0,NULL,NULL,'admin123@gmail.com',0,NULL,'M',0,NULL,'0','0',NULL,NULL,NULL,NULL,NULL,0,'system admin','admin',NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','11111111111',NULL,NULL,NULL,0,0,NULL,NULL,NULL,0,3,'2023-11-18 07:00:00.000000','2017-11-18 07:00:00.000000','2023-07-07 05:34:42.287711',0,NULL,NULL,'admin','123123'),(129,'30+',NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-06-26 03:21:27.000000',NULL,NULL,NULL,NULL,'baolong123@gmail.com',0,NULL,'M',NULL,NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,'baolong123',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','12345678912',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,'2024-11-18 07:00:00.000000','2020-11-18 07:00:00.000000','2023-07-07 05:34:42.287711',NULL,NULL,NULL,'baolong123',NULL),(130,'30+',NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-06-27 08:45:12.000000',NULL,NULL,NULL,NULL,'admin1234@gmail.com',0,NULL,'M',NULL,NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,'com.mac.martial_arts_cms.model.dto.UserDto',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','1234567891',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,'2023-07-07 05:34:42.287711',NULL,NULL,NULL,'admin1234','123456'),(131,'30+',NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-06-27 08:45:12.000000',NULL,NULL,NULL,NULL,'devaccount1@gmail.com',0,NULL,'M',NULL,NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,'devaccount1',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','1234567891',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,'2023-07-07 05:34:42.287711',NULL,NULL,NULL,'devaccount1',NULL),(132,'30+',NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-06-27 08:45:12.000000',NULL,NULL,NULL,NULL,'devaccount2@gmail.com',0,NULL,'M',NULL,NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,'devaccount2',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','1234567891',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,'2023-07-07 05:34:42.287711',NULL,NULL,NULL,'devaccount2',NULL),(133,'30+',NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-06-27 08:45:12.000000',NULL,NULL,NULL,NULL,'devaccount3@gmail.com',0,NULL,'M',NULL,NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,'devaccount3',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','1234567891',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,'2023-07-07 05:34:42.287711',NULL,NULL,NULL,'devaccount3',NULL),(134,'30+',NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-06-27 08:45:12.000000',NULL,NULL,NULL,NULL,'devaccount4@gmail.com',0,NULL,'M',NULL,NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,'devaccount4',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','1234567891',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,'2023-07-07 05:34:42.287711',NULL,NULL,NULL,'devaccount4',NULL),(135,'30+',NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-06-27 08:45:12.000000',NULL,NULL,NULL,NULL,'devaccount5@gmail.com',0,NULL,'M',NULL,NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,'devaccount5',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','1234567891',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,'2023-07-07 05:34:42.287711',NULL,NULL,NULL,'devaccount5',NULL),(136,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-07-04 09:59:02.000000',NULL,NULL,NULL,NULL,'123@gmail.com',0,NULL,'M',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,'123',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','556677444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,'2023-07-07 05:34:42.287711',NULL,NULL,NULL,'admin321',NULL),(137,'30+',NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-07-04 09:59:02.000000',NULL,NULL,NULL,NULL,'doquangphuoc9999@gmail.com',0,NULL,'M',NULL,NULL,'0','0',NULL,NULL,NULL,NULL,NULL,NULL,'phuoc123','admin',NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','556677444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,3,NULL,NULL,'2023-07-07 05:34:42.287711',NULL,NULL,NULL,'phuoc123','123321'),(138,'30+',NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-07-04 09:59:02.000000',NULL,NULL,NULL,NULL,'phuoc@gmail.com',0,NULL,'M',NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,'phuoc',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','556677444',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'2023-07-07 05:34:42.287711',NULL,NULL,NULL,NULL,'456789'),(140,'',NULL,NULL,NULL,NULL,NULL,NULL,NULL,_binary '\0','2023-07-10 08:37:34.000000',NULL,NULL,NULL,NULL,'chido@gmail.com',0,NULL,NULL,NULL,NULL,NULL,'0',NULL,NULL,NULL,NULL,NULL,NULL,'ChiDo1',NULL,NULL,'$2a$10$sBweFqkh4Rrewipo3qJ2NuQXtAT7x8roIjPkKV7/NeXgRH70Iv/oi','',NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,1,NULL,NULL,NULL,NULL,NULL,NULL,'ChiDo',NULL,NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `videos`
--

DROP TABLE IF EXISTS `videos`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `videos` (
  `VIDEO_ID` bigint NOT NULL AUTO_INCREMENT,
  `CREATED_DATE` datetime(6) DEFAULT NULL,
  `PLAYLIST` varchar(255) DEFAULT NULL,
  `VIDEO_LINK` varchar(1000) DEFAULT NULL,
  `SCRAP_BOOK_ID` bigint DEFAULT NULL,
  `VIDEO_CATEGORY_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`VIDEO_ID`),
  KEY `FKc877xenvuxdrkyfanc6ymjm03` (`SCRAP_BOOK_ID`),
  CONSTRAINT `FKc877xenvuxdrkyfanc6ymjm03` FOREIGN KEY (`SCRAP_BOOK_ID`) REFERENCES `room_scrap_book` (`SCRAP_BOOK_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `videos`
--

LOCK TABLES `videos` WRITE;
/*!40000 ALTER TABLE `videos` DISABLE KEYS */;
INSERT INTO `videos` VALUES (1,'2023-07-17 06:49:31.891041',NULL,'https://www.youtube.com/watch?v=DISPAJ3Hbks&list=PLmAVoNqyB8jDOJYq5VlSLJTTHyAWgS7Op',NULL,NULL),(2,'2023-07-17 07:11:06.363574',NULL,'https://www.youtube.com/watch?v=DISPAJ3Hbks%26list=PLmAVoNqyB8jDOJYq5VlSLJTTHyAWgS7Op',NULL,NULL),(4,'2023-07-17 07:16:20.601298',NULL,'https://www.youtube.com/watch?v=DISPAJ3Hbks&ab_channel=%EB%B9%84%EB%94%94%ED%86%A0%EB%A6%AC',NULL,NULL);
/*!40000 ALTER TABLE `videos` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `wardrobe_item`
--

DROP TABLE IF EXISTS `wardrobe_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `wardrobe_item` (
  `ID` bigint NOT NULL AUTO_INCREMENT,
  `IS_EQUIPPED` bit(1) NOT NULL,
  `ITEM_ID` bigint NOT NULL,
  `ROOM_ID` bigint DEFAULT NULL,
  PRIMARY KEY (`ID`),
  KEY `FKdbnh7j6x7h7ro84aqe55uaw75` (`ROOM_ID`),
  CONSTRAINT `FKdbnh7j6x7h7ro84aqe55uaw75` FOREIGN KEY (`ROOM_ID`) REFERENCES `my_room` (`ROOM_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `wardrobe_item`
--

LOCK TABLES `wardrobe_item` WRITE;
/*!40000 ALTER TABLE `wardrobe_item` DISABLE KEYS */;
/*!40000 ALTER TABLE `wardrobe_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'martial_arts_meta_dev'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-07-25 16:41:25

--     Create table quartz --
DROP TABLE IF EXISTS QRTZ_FIRED_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_PAUSED_TRIGGER_GRPS;
DROP TABLE IF EXISTS QRTZ_SCHEDULER_STATE;
DROP TABLE IF EXISTS QRTZ_LOCKS;
DROP TABLE IF EXISTS QRTZ_SIMPLE_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_SIMPROP_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_CRON_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_BLOB_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_TRIGGERS;
DROP TABLE IF EXISTS QRTZ_JOB_DETAILS;
DROP TABLE IF EXISTS QRTZ_CALENDARS;

CREATE TABLE QRTZ_JOB_DETAILS(
                                 SCHED_NAME VARCHAR(120) NOT NULL,
                                 JOB_NAME VARCHAR(190) NOT NULL,
                                 JOB_GROUP VARCHAR(190) NOT NULL,
                                 DESCRIPTION VARCHAR(250) NULL,
                                 JOB_CLASS_NAME VARCHAR(250) NOT NULL,
                                 IS_DURABLE VARCHAR(1) NOT NULL,
                                 IS_NONCONCURRENT VARCHAR(1) NOT NULL,
                                 IS_UPDATE_DATA VARCHAR(1) NOT NULL,
                                 REQUESTS_RECOVERY VARCHAR(1) NOT NULL,
                                 JOB_DATA BLOB NULL,
                                 PRIMARY KEY (SCHED_NAME,JOB_NAME,JOB_GROUP))
    ENGINE=InnoDB;

CREATE TABLE QRTZ_TRIGGERS (
                               SCHED_NAME VARCHAR(120) NOT NULL,
                               TRIGGER_NAME VARCHAR(190) NOT NULL,
                               TRIGGER_GROUP VARCHAR(190) NOT NULL,
                               JOB_NAME VARCHAR(190) NOT NULL,
                               JOB_GROUP VARCHAR(190) NOT NULL,
                               DESCRIPTION VARCHAR(250) NULL,
                               NEXT_FIRE_TIME BIGINT(13) NULL,
                               PREV_FIRE_TIME BIGINT(13) NULL,
                               PRIORITY INTEGER NULL,
                               TRIGGER_STATE VARCHAR(16) NOT NULL,
                               TRIGGER_TYPE VARCHAR(8) NOT NULL,
                               START_TIME BIGINT(13) NOT NULL,
                               END_TIME BIGINT(13) NULL,
                               CALENDAR_NAME VARCHAR(190) NULL,
                               MISFIRE_INSTR SMALLINT(2) NULL,
                               JOB_DATA BLOB NULL,
                               PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
                               FOREIGN KEY (SCHED_NAME,JOB_NAME,JOB_GROUP)
                                   REFERENCES QRTZ_JOB_DETAILS(SCHED_NAME,JOB_NAME,JOB_GROUP))
    ENGINE=InnoDB;

CREATE TABLE QRTZ_SIMPLE_TRIGGERS (
                                      SCHED_NAME VARCHAR(120) NOT NULL,
                                      TRIGGER_NAME VARCHAR(190) NOT NULL,
                                      TRIGGER_GROUP VARCHAR(190) NOT NULL,
                                      REPEAT_COUNT BIGINT(7) NOT NULL,
                                      REPEAT_INTERVAL BIGINT(12) NOT NULL,
                                      TIMES_TRIGGERED BIGINT(10) NOT NULL,
                                      PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
                                      FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
                                          REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
    ENGINE=InnoDB;

CREATE TABLE QRTZ_CRON_TRIGGERS (
                                    SCHED_NAME VARCHAR(120) NOT NULL,
                                    TRIGGER_NAME VARCHAR(190) NOT NULL,
                                    TRIGGER_GROUP VARCHAR(190) NOT NULL,
                                    CRON_EXPRESSION VARCHAR(120) NOT NULL,
                                    TIME_ZONE_ID VARCHAR(80),
                                    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
                                    FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
                                        REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
    ENGINE=InnoDB;

CREATE TABLE QRTZ_SIMPROP_TRIGGERS
(
    SCHED_NAME VARCHAR(120) NOT NULL,
    TRIGGER_NAME VARCHAR(190) NOT NULL,
    TRIGGER_GROUP VARCHAR(190) NOT NULL,
    STR_PROP_1 VARCHAR(512) NULL,
    STR_PROP_2 VARCHAR(512) NULL,
    STR_PROP_3 VARCHAR(512) NULL,
    INT_PROP_1 INT NULL,
    INT_PROP_2 INT NULL,
    LONG_PROP_1 BIGINT NULL,
    LONG_PROP_2 BIGINT NULL,
    DEC_PROP_1 NUMERIC(13,4) NULL,
    DEC_PROP_2 NUMERIC(13,4) NULL,
    BOOL_PROP_1 VARCHAR(1) NULL,
    BOOL_PROP_2 VARCHAR(1) NULL,
    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
    FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
        REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
    ENGINE=InnoDB;

CREATE TABLE QRTZ_BLOB_TRIGGERS (
                                    SCHED_NAME VARCHAR(120) NOT NULL,
                                    TRIGGER_NAME VARCHAR(190) NOT NULL,
                                    TRIGGER_GROUP VARCHAR(190) NOT NULL,
                                    BLOB_DATA BLOB NULL,
                                    PRIMARY KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP),
                                    INDEX (SCHED_NAME,TRIGGER_NAME, TRIGGER_GROUP),
                                    FOREIGN KEY (SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP)
                                        REFERENCES QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP))
    ENGINE=InnoDB;

CREATE TABLE QRTZ_CALENDARS (
                                SCHED_NAME VARCHAR(120) NOT NULL,
                                CALENDAR_NAME VARCHAR(190) NOT NULL,
                                CALENDAR BLOB NOT NULL,
                                PRIMARY KEY (SCHED_NAME,CALENDAR_NAME))
    ENGINE=InnoDB;

CREATE TABLE QRTZ_PAUSED_TRIGGER_GRPS (
                                          SCHED_NAME VARCHAR(120) NOT NULL,
                                          TRIGGER_GROUP VARCHAR(190) NOT NULL,
                                          PRIMARY KEY (SCHED_NAME,TRIGGER_GROUP))
    ENGINE=InnoDB;

CREATE TABLE QRTZ_FIRED_TRIGGERS (
                                     SCHED_NAME VARCHAR(120) NOT NULL,
                                     ENTRY_ID VARCHAR(95) NOT NULL,
                                     TRIGGER_NAME VARCHAR(190) NOT NULL,
                                     TRIGGER_GROUP VARCHAR(190) NOT NULL,
                                     INSTANCE_NAME VARCHAR(190) NOT NULL,
                                     FIRED_TIME BIGINT(13) NOT NULL,
                                     SCHED_TIME BIGINT(13) NOT NULL,
                                     PRIORITY INTEGER NOT NULL,
                                     STATE VARCHAR(16) NOT NULL,
                                     JOB_NAME VARCHAR(190) NULL,
                                     JOB_GROUP VARCHAR(190) NULL,
                                     IS_NONCONCURRENT VARCHAR(1) NULL,
                                     REQUESTS_RECOVERY VARCHAR(1) NULL,
                                     PRIMARY KEY (SCHED_NAME,ENTRY_ID))
    ENGINE=InnoDB;

CREATE TABLE QRTZ_SCHEDULER_STATE (
                                      SCHED_NAME VARCHAR(120) NOT NULL,
                                      INSTANCE_NAME VARCHAR(190) NOT NULL,
                                      LAST_CHECKIN_TIME BIGINT(13) NOT NULL,
                                      CHECKIN_INTERVAL BIGINT(13) NOT NULL,
                                      PRIMARY KEY (SCHED_NAME,INSTANCE_NAME))
    ENGINE=InnoDB;

CREATE TABLE QRTZ_LOCKS (
                            SCHED_NAME VARCHAR(120) NOT NULL,
                            LOCK_NAME VARCHAR(40) NOT NULL,
                            PRIMARY KEY (SCHED_NAME,LOCK_NAME))
    ENGINE=InnoDB;

CREATE INDEX IDX_QRTZ_J_REQ_RECOVERY ON QRTZ_JOB_DETAILS(SCHED_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_J_GRP ON QRTZ_JOB_DETAILS(SCHED_NAME,JOB_GROUP);

CREATE INDEX IDX_QRTZ_T_J ON QRTZ_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_JG ON QRTZ_TRIGGERS(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_T_C ON QRTZ_TRIGGERS(SCHED_NAME,CALENDAR_NAME);
CREATE INDEX IDX_QRTZ_T_G ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_T_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_N_G_STATE ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_GROUP,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NEXT_FIRE_TIME ON QRTZ_TRIGGERS(SCHED_NAME,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST ON QRTZ_TRIGGERS(SCHED_NAME,TRIGGER_STATE,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_STATE);
CREATE INDEX IDX_QRTZ_T_NFT_ST_MISFIRE_GRP ON QRTZ_TRIGGERS(SCHED_NAME,MISFIRE_INSTR,NEXT_FIRE_TIME,TRIGGER_GROUP,TRIGGER_STATE);

CREATE INDEX IDX_QRTZ_FT_TRIG_INST_NAME ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME);
CREATE INDEX IDX_QRTZ_FT_INST_JOB_REQ_RCVRY ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,INSTANCE_NAME,REQUESTS_RECOVERY);
CREATE INDEX IDX_QRTZ_FT_J_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_JG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,JOB_GROUP);
CREATE INDEX IDX_QRTZ_FT_T_G ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_NAME,TRIGGER_GROUP);
CREATE INDEX IDX_QRTZ_FT_TG ON QRTZ_FIRED_TRIGGERS(SCHED_NAME,TRIGGER_GROUP);

commit;
