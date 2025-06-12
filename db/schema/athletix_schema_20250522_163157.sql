-- MySQL dump 10.13  Distrib 9.2.0, for Win64 (x86_64)
--
-- Host: localhost    Database: athletix
-- ------------------------------------------------------
-- Server version	9.2.0

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
-- Current Database: `athletix`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `athletix` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;

USE `athletix`;

--
-- Table structure for table `challenges`
--

DROP TABLE IF EXISTS `challenges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `challenges` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `duration` decimal(21,0) DEFAULT NULL,
  `km` int DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime(6) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  `km` int DEFAULT NULL,
  `location` varchar(255) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
  `id` int NOT NULL AUTO_INCREMENT,
  `friend_chat_id` int NOT NULL,
  `friend_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK348w1chw0vdks7p5d1qg16vk5` (`friend_chat_id`),
  KEY `FKc42eihjtiryeriy8axlkpejo7` (`friend_id`),
  KEY `FKlh21lfp7th1y1tn9g63ihkda9` (`user_id`),
  CONSTRAINT `FK348w1chw0vdks7p5d1qg16vk5` FOREIGN KEY (`friend_chat_id`) REFERENCES `friends_chats` (`id`),
  CONSTRAINT `FKc42eihjtiryeriy8axlkpejo7` FOREIGN KEY (`friend_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKlh21lfp7th1y1tn9g63ihkda9` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `friends_chats`
--

DROP TABLE IF EXISTS `friends_chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends_chats` (
  `id` int NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `groups_chats`
--

DROP TABLE IF EXISTS `groups_chats`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `groups_chats` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKh0xlpijndqr93vo2ouap2xa93` (`message_id`),
  CONSTRAINT `FKh0xlpijndqr93vo2ouap2xa93` FOREIGN KEY (`message_id`) REFERENCES `messages` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `grups`
--

DROP TABLE IF EXISTS `grups`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `grups` (
  `id` int NOT NULL AUTO_INCREMENT,
  `group_chat_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK465h8gfamh9joak5sbe2ea6gh` (`group_chat_id`),
  KEY `FKfv8rar1fvu8a7839pomp90erh` (`user_id`),
  CONSTRAINT `FK465h8gfamh9joak5sbe2ea6gh` FOREIGN KEY (`group_chat_id`) REFERENCES `groups_chats` (`id`),
  CONSTRAINT `FKfv8rar1fvu8a7839pomp90erh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `friend_chat_id` int NOT NULL,
  `message_chat_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmqsh9ply5dmho2dv4q605wv4r` (`friend_chat_id`),
  KEY `FK6wpvvek68afjv56f31p1l8jvv` (`message_chat_id`),
  CONSTRAINT `FK6wpvvek68afjv56f31p1l8jvv` FOREIGN KEY (`message_chat_id`) REFERENCES `groups_chats` (`id`),
  CONSTRAINT `FKmqsh9ply5dmho2dv4q605wv4r` FOREIGN KEY (`friend_chat_id`) REFERENCES `friends_chats` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `notification_type_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKi5ny8jwew8qd2i8psix6iw5q7` (`notification_type_id`),
  CONSTRAINT `FKi5ny8jwew8qd2i8psix6iw5q7` FOREIGN KEY (`notification_type_id`) REFERENCES `notifications_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `notifications_types`
--

DROP TABLE IF EXISTS `notifications_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKiu4hmn4m481j854flu0kldmfe` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `sports`
--

DROP TABLE IF EXISTS `sports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sports` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sport` enum('CLIMBING','CROSS_COUNTRY_RUNNING','CYCLING','HIKING','IRONMAN','MARATHON','MOUNTAIN_BIKING','ROAD_RUNNING','RUNNING','SKATING','SKIING','SNOWBOARDING','SWIMMING','TRAIL_RUNNING','TRIATHLON','ULTRAMARATHON','WALKING') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKc0thpg0qmf6ypeplmddn50tfx` (`sport`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `birth_date` varchar(255) DEFAULT NULL,
  `email` varchar(255) NOT NULL,
  `gender` enum('FEMALE','MALE','OTHER') DEFAULT NULL,
  `height` varchar(255) DEFAULT NULL,
  `name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `surname` varchar(255) NOT NULL,
  `surname2` varchar(255) DEFAULT NULL,
  `town` varchar(255) DEFAULT NULL,
  `username` varchar(255) NOT NULL,
  `weight` varchar(255) DEFAULT NULL,
  `trainer_id` int DEFAULT NULL,
  `user_type_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKcwlyooxbh9coetj7dinn3qgbt` (`trainer_id`),
  KEY `FK1kru266nedtut1u6y3lgc9svl` (`user_type_id`),
  CONSTRAINT `FK1kru266nedtut1u6y3lgc9svl` FOREIGN KEY (`user_type_id`) REFERENCES `users_types` (`id`),
  CONSTRAINT `FKcwlyooxbh9coetj7dinn3qgbt` FOREIGN KEY (`trainer_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_challenges`
--

DROP TABLE IF EXISTS `users_challenges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_challenges` (
  `id` int NOT NULL AUTO_INCREMENT,
  `duration` decimal(21,0) DEFAULT NULL,
  `km` int DEFAULT NULL,
  `challenge_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKmlpcc25nh5ach3u8clebkkv3i` (`challenge_id`),
  KEY `FK98h49yvp3jmd5l3sbd9c4bajy` (`user_id`),
  CONSTRAINT `FK98h49yvp3jmd5l3sbd9c4bajy` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKmlpcc25nh5ach3u8clebkkv3i` FOREIGN KEY (`challenge_id`) REFERENCES `challenges` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_events`
--

DROP TABLE IF EXISTS `users_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_events` (
  `id` int NOT NULL AUTO_INCREMENT,
  `registration_date` datetime(6) DEFAULT NULL,
  `role` enum('ADMIN','MODERATOR','TRAINER','USER') DEFAULT NULL,
  `event_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKrbcvthksj0joq0x32bjxsd0oe` (`event_id`),
  KEY `FKqq8vriw3reievxhjwkfnrehsr` (`user_id`),
  CONSTRAINT `FKqq8vriw3reievxhjwkfnrehsr` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKrbcvthksj0joq0x32bjxsd0oe` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_notifications`
--

DROP TABLE IF EXISTS `users_notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `notification_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKinx59ivejys2xgb2gnvre7fo2` (`notification_id`),
  KEY `FKil3tssmpyic5ruavb9jbbw2bb` (`user_id`),
  CONSTRAINT `FKil3tssmpyic5ruavb9jbbw2bb` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKinx59ivejys2xgb2gnvre7fo2` FOREIGN KEY (`notification_id`) REFERENCES `notifications` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_sports`
--

DROP TABLE IF EXISTS `users_sports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_sports` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `sport_id` int NOT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKbsuttiledh7sgllwglt4e6b85` (`sport_id`),
  KEY `FKoupoh0gohbi4b9gjdcb606nb1` (`user_id`),
  CONSTRAINT `FKbsuttiledh7sgllwglt4e6b85` FOREIGN KEY (`sport_id`) REFERENCES `sports` (`id`),
  CONSTRAINT `FKoupoh0gohbi4b9gjdcb606nb1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_types`
--

DROP TABLE IF EXISTS `users_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKed7r9282dr2vkrkyg0tslu3j7` (`description`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping routines for database 'athletix'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-05-22 16:31:57
