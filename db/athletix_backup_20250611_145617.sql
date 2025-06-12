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
-- Dumping data for table `challenges`
--

LOCK TABLES `challenges` WRITE;
/*!40000 ALTER TABLE `challenges` DISABLE KEYS */;
/*!40000 ALTER TABLE `challenges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `events`
--

DROP TABLE IF EXISTS `events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `events` (
  `id` int NOT NULL AUTO_INCREMENT,
  `activity` enum('CYCLING','HIKING','MOUNTAIN_BIKING','RUNNING','SWIMMING','TRAIL_RUNNING','WALKING') DEFAULT NULL,
  `date` datetime(6) NOT NULL,
  `description` varchar(2000) DEFAULT NULL,
  `difficulty` enum('EASY','HARD','MEDIUM') DEFAULT NULL,
  `km` float DEFAULT NULL,
  `latitude` double DEFAULT NULL,
  `location` varchar(255) NOT NULL,
  `longitude` double DEFAULT NULL,
  `profile_image` varchar(255) DEFAULT NULL,
  `short_description` varchar(255) DEFAULT NULL,
  `title` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `events`
--

LOCK TABLES `events` WRITE;
/*!40000 ALTER TABLE `events` DISABLE KEYS */;
/*!40000 ALTER TABLE `events` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `friends`
--

DROP TABLE IF EXISTS `friends`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `friends` (
  `id` int NOT NULL AUTO_INCREMENT,
  `status` enum('ACCEPTED','PENDING','REJECTED') DEFAULT NULL,
  `user1_id` int NOT NULL,
  `user2_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKixmq3ir3069oq850dloi7kr3t` (`user1_id`),
  KEY `FKt5nty8dt2595lp0j0erwe4655` (`user2_id`),
  CONSTRAINT `FKixmq3ir3069oq850dloi7kr3t` FOREIGN KEY (`user1_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKt5nty8dt2595lp0j0erwe4655` FOREIGN KEY (`user2_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `friends`
--

LOCK TABLES `friends` WRITE;
/*!40000 ALTER TABLE `friends` DISABLE KEYS */;
INSERT INTO `friends` VALUES (1,'ACCEPTED',1,2),(2,'ACCEPTED',1,3);
/*!40000 ALTER TABLE `friends` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `images`
--

DROP TABLE IF EXISTS `images`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `images` (
  `id` int NOT NULL AUTO_INCREMENT,
  `height` int DEFAULT NULL,
  `imageurl` varchar(255) DEFAULT NULL,
  `width` int DEFAULT NULL,
  `challenge_id` int DEFAULT NULL,
  `event_id` int DEFAULT NULL,
  `tracking_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK2xqkt01y16qyxcpyr0bndolpv` (`challenge_id`),
  KEY `FKjqkpwof0kqmekscxaso6u1bl3` (`event_id`),
  KEY `FKil4giuxx9a9jgq3jto140v3ij` (`tracking_id`),
  CONSTRAINT `FK2xqkt01y16qyxcpyr0bndolpv` FOREIGN KEY (`challenge_id`) REFERENCES `challenges` (`id`),
  CONSTRAINT `FKil4giuxx9a9jgq3jto140v3ij` FOREIGN KEY (`tracking_id`) REFERENCES `trackings` (`id`),
  CONSTRAINT `FKjqkpwof0kqmekscxaso6u1bl3` FOREIGN KEY (`event_id`) REFERENCES `events` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `images`
--

LOCK TABLES `images` WRITE;
/*!40000 ALTER TABLE `images` DISABLE KEYS */;
/*!40000 ALTER TABLE `images` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `messages`
--

DROP TABLE IF EXISTS `messages`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `messages` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `readed` bit(1) NOT NULL,
  `friendship_id` int DEFAULT NULL,
  `sender_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FKda55kkmpj70c0qn3dra1sb8r8` (`friendship_id`),
  KEY `FK4ui4nnwntodh6wjvck53dbk9m` (`sender_id`),
  CONSTRAINT `FK4ui4nnwntodh6wjvck53dbk9m` FOREIGN KEY (`sender_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKda55kkmpj70c0qn3dra1sb8r8` FOREIGN KEY (`friendship_id`) REFERENCES `friends` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `messages`
--

LOCK TABLES `messages` WRITE;
/*!40000 ALTER TABLE `messages` DISABLE KEYS */;
INSERT INTO `messages` VALUES (1,'2025-06-10 23:48:28.623553','Kaixo',_binary '\0',1,1),(2,'2025-06-10 23:49:07.143780','Kaixo Lagunok',_binary '\0',1,1),(3,'2025-06-10 23:58:01.818279','Agur',_binary '\0',1,1),(4,'2025-06-11 00:37:37.386975','Kaixo',_binary '\0',1,1),(5,'2025-06-11 00:40:58.494184','Kaixo',_binary '\0',1,1),(6,'2025-06-11 00:42:12.808013','Kaixo Lagunok',_binary '\0',1,1),(7,'2025-06-11 00:43:24.138832','Agur',_binary '\0',1,1),(8,'2025-06-11 00:44:48.577061','Kaixo',_binary '\0',1,1),(9,'2025-06-11 00:44:58.437056','hola',_binary '\0',1,2),(10,'2025-06-11 00:45:43.954444','Hola Jone',_binary '\0',1,2),(11,'2025-06-11 00:45:47.760017','kk',_binary '\0',1,1),(12,'2025-06-11 00:45:55.811747','eiggi',_binary '\0',1,1),(13,'2025-06-11 00:45:59.254550','wawawa',_binary '\0',1,1),(14,'2025-06-11 00:46:01.136246','kakakaka',_binary '\0',1,1),(15,'2025-06-11 00:46:06.612422','ksaooendmalska',_binary '\0',1,1),(16,'2025-06-11 00:46:53.772697','kk',_binary '\0',1,1),(17,'2025-06-11 00:46:58.114718','ñamñamñam',_binary '\0',1,1),(18,'2025-06-11 00:47:01.362045','suricata',_binary '\0',1,1),(19,'2025-06-11 00:47:24.840400','jone',_binary '\0',1,1),(20,'2025-06-11 00:47:26.335835','zorrilla',_binary '\0',1,1),(21,'2025-06-11 00:47:27.882509','juingutu',_binary '\0',1,1),(22,'2025-06-11 00:47:29.208137','padilla',_binary '\0',1,1),(23,'2025-06-11 00:47:32.479540','gorritxategi',_binary '\0',1,1),(24,'2025-06-11 00:47:41.812596','<`ç',_binary '\0',1,1),(25,'2025-06-11 00:47:47.224826',':)',_binary '\0',1,1),(26,'2025-06-11 00:47:51.644437','huh',_binary '\0',1,1),(27,'2025-06-11 00:47:54.728711','ñamñam',_binary '\0',1,1),(28,'2025-06-11 00:47:56.852777','pacman',_binary '\0',1,1),(29,'2025-06-11 00:48:13.989693','688751323',_binary '\0',1,1),(30,'2025-06-11 00:48:21.355755','hola',_binary '\0',1,2),(31,'2025-06-11 00:48:28.676663','ibai matricuula de honor?',_binary '\0',1,1),(32,'2025-06-11 00:48:33.908581','phyton',_binary '\0',1,1),(33,'2025-06-11 00:48:33.998757','no creo',_binary '\0',1,2),(34,'2025-06-11 00:48:36.848632','por',_binary '\0',1,1),(35,'2025-06-11 00:49:01.115338','10',_binary '\0',1,1),(36,'2025-06-11 00:49:11.773059','Ibai Z-ren nota proiektuan: 10/10',_binary '\0',1,1),(37,'2025-06-11 00:54:51.715398','hola',_binary '\0',1,2),(38,'2025-06-11 01:34:22.056788','kaixo',_binary '\0',1,1),(39,'2025-06-11 01:38:23.545556','agur',_binary '\0',1,1),(40,'2025-06-11 01:54:20.131282','Kaixo',_binary '\0',1,1),(41,'2025-06-11 01:56:04.767683','asdsadsadasd',_binary '\0',1,1),(42,'2025-06-11 13:37:33.188098','Kaixo',_binary '\0',1,2),(43,'2025-06-11 13:37:38.845027','Agur',_binary '\0',1,1),(44,'2025-06-11 13:39:03.545006','buenasss',_binary '\0',1,2),(45,'2025-06-11 13:41:28.542411','Ibai',_binary '\0',1,1),(46,'2025-06-11 13:41:58.521096','dasdas',_binary '\0',1,1),(47,'2025-06-11 13:42:07.468246','asd',_binary '\0',2,3),(48,'2025-06-11 13:42:53.317622','asd',_binary '\0',2,3),(49,'2025-06-11 13:59:40.790289','Kaixo',_binary '\0',1,2),(50,'2025-06-11 13:59:43.848484','Kaixo',_binary '\0',2,3),(51,'2025-06-11 14:00:22.380278','jakbcsd',_binary '\0',1,2),(52,'2025-06-11 14:00:26.209238','dahsiudasiucas',_binary '\0',2,3),(53,'2025-06-11 14:00:36.122365','aadasd',_binary '\0',2,3),(54,'2025-06-11 14:00:39.510378','dasdadasd',_binary '\0',1,2),(55,'2025-06-11 14:34:24.124107','Kas',_binary '\0',1,2),(56,'2025-06-11 14:34:32.459513','ads',_binary '\0',1,1),(57,'2025-06-11 14:36:42.395302','Kaixo',_binary '\0',1,1),(58,'2025-06-11 14:41:00.953721','asdasd',_binary '\0',1,2),(59,'2025-06-11 14:45:34.044141','Kaixo',_binary '\0',1,2),(60,'2025-06-11 14:45:39.978246','Agur',_binary '\0',1,1),(61,'2025-06-11 14:45:50.324335','Kaixo',_binary '\0',1,1),(62,'2025-06-11 14:48:41.061950','asd',_binary '\0',1,1),(63,'2025-06-11 14:48:41.416934','asda',_binary '\0',1,1),(64,'2025-06-11 14:48:41.746170','as',_binary '\0',1,1),(65,'2025-06-11 14:48:42.015443','da',_binary '\0',1,1),(66,'2025-06-11 14:48:42.229893','da',_binary '\0',1,1),(67,'2025-06-11 14:48:42.422389','das',_binary '\0',1,1),(68,'2025-06-11 14:48:42.623334','das',_binary '\0',1,1),(69,'2025-06-11 14:48:42.759577','sd',_binary '\0',1,1),(70,'2025-06-11 14:48:42.924752','sa',_binary '\0',1,1);
/*!40000 ALTER TABLE `messages` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notifications`
--

DROP TABLE IF EXISTS `notifications`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notifications` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` date DEFAULT NULL,
  `message` varchar(255) NOT NULL,
  `title` varchar(255) NOT NULL,
  `type` enum('CREATE_EVENT','CREATE_TRACKING','EDIT_EVENT','REMOVE_EVENT','REMOVE_TRACKING') DEFAULT NULL,
  `user_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK9y21adhxn0ayjhfocscqox7bh` (`user_id`),
  CONSTRAINT `FK9y21adhxn0ayjhfocscqox7bh` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notifications`
--

LOCK TABLES `notifications` WRITE;
/*!40000 ALTER TABLE `notifications` DISABLE KEYS */;
/*!40000 ALTER TABLE `notifications` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `sports`
--

DROP TABLE IF EXISTS `sports`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `sports` (
  `id` int NOT NULL AUTO_INCREMENT,
  `sport` enum('CYCLING','HIKING','MOUNTAIN_BIKING','RUNNING','SWIMMING','TRAIL_RUNNING','WALKING') NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UKc0thpg0qmf6ypeplmddn50tfx` (`sport`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `sports`
--

LOCK TABLES `sports` WRITE;
/*!40000 ALTER TABLE `sports` DISABLE KEYS */;
/*!40000 ALTER TABLE `sports` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `trackings`
--

DROP TABLE IF EXISTS `trackings`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `trackings` (
  `id` int NOT NULL AUTO_INCREMENT,
  `date` datetime(6) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `duration` decimal(21,0) DEFAULT NULL,
  `km` float DEFAULT NULL,
  `pace` decimal(21,0) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `user_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK46pqx73trht863vq20x6iau7h` (`user_id`),
  CONSTRAINT `FK46pqx73trht863vq20x6iau7h` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `trackings`
--

LOCK TABLES `trackings` WRITE;
/*!40000 ALTER TABLE `trackings` DISABLE KEYS */;
/*!40000 ALTER TABLE `trackings` ENABLE KEYS */;
UNLOCK TABLES;

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,NULL,'ibai@ibai.ibai','MALE','123','Ibai','$2a$10$Ocen3pwcGlUz6UoyR1rJm.2RqeIGh4dFFPibI0gr1KTUt6dgeeMkW','98765432',NULL,'Zorrilla','Junguitu','Elorrio','Ibai','123',NULL,NULL),(2,NULL,'as@a.as','MALE','12','as','$2a$10$X274KY8DEHRaD2Qi/ow61uXDTlT6Oqs6TABltZ8H/u2Pog30ihkNC','12',NULL,'as','as','a','Alex','12',NULL,NULL),(3,NULL,'e@e.com','MALE','12','asd','$2a$10$gkwqZvR67fXzMKFitLfyH.sAPkjh0xyE1CQJLdqQHjZW4FI3TqV..','123456',NULL,'asd','asd','buhjn','Aimar','12',NULL,NULL);
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `users_challenges`
--

LOCK TABLES `users_challenges` WRITE;
/*!40000 ALTER TABLE `users_challenges` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_challenges` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_events`
--

DROP TABLE IF EXISTS `users_events`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_events` (
  `id` int NOT NULL AUTO_INCREMENT,
  `registration_date` datetime(6) DEFAULT NULL,
  `role` enum('CREATOR','PARTICIPANT') DEFAULT NULL,
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
-- Dumping data for table `users_events`
--

LOCK TABLES `users_events` WRITE;
/*!40000 ALTER TABLE `users_events` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_events` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `users_sports`
--

LOCK TABLES `users_sports` WRITE;
/*!40000 ALTER TABLE `users_sports` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_sports` ENABLE KEYS */;
UNLOCK TABLES;

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
-- Dumping data for table `users_types`
--

LOCK TABLES `users_types` WRITE;
/*!40000 ALTER TABLE `users_types` DISABLE KEYS */;
/*!40000 ALTER TABLE `users_types` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2025-06-11 14:56:17
