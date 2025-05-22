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
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `message` varchar(255) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `user_id` varchar(255) DEFAULT NULL,
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
  `id` int NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `user_types`
--

DROP TABLE IF EXISTS `user_types`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_types` (
  `id` int NOT NULL AUTO_INCREMENT,
  `description` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKeqcd1ffvf68b9hlgjv52xmyxx` (`description`)
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
  `trainees` varbinary(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`),
  UNIQUE KEY `UKr43af9ap4edm43mmtq01oddj6` (`username`),
  KEY `FKcwlyooxbh9coetj7dinn3qgbt` (`trainer_id`),
  KEY `FKp0utx8kvsuc78nb39dgyg6oko` (`user_type_id`),
  CONSTRAINT `FKcwlyooxbh9coetj7dinn3qgbt` FOREIGN KEY (`trainer_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKp0utx8kvsuc78nb39dgyg6oko` FOREIGN KEY (`user_type_id`) REFERENCES `user_types` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Table structure for table `users_challenges`
--

DROP TABLE IF EXISTS `users_challenges`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_challenges` (
  `id` int NOT NULL,
  `user_id` int NOT NULL,
  `challenge_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK98h49yvp3jmd5l3sbd9c4bajy` (`user_id`),
  KEY `FKmlpcc25nh5ach3u8clebkkv3i` (`challenge_id`),
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
  `role` varchar(255) DEFAULT NULL,
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

-- Dump completed on 2025-05-22  0:43:14
