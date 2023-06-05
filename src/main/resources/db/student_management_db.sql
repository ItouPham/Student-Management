-- MySQL dump 10.13  Distrib 8.0.31, for Win64 (x86_64)
--
-- Host: localhost    Database: student_management_db
-- ------------------------------------------------------
-- Server version	8.0.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `classes`
--

DROP TABLE IF EXISTS `classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `classes` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `created_date` datetime DEFAULT NULL,
  `end_date` datetime DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `start_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `classes`
--

LOCK TABLES `classes` WRITE;
/*!40000 ALTER TABLE `classes` DISABLE KEYS */;
INSERT INTO `classes` VALUES (1,'2023-05-17 11:37:38',NULL,'GCC001',NULL),(2,'2023-05-17 11:40:16',NULL,'GCC002',NULL),(3,'2023-05-17 11:42:35',NULL,'GCC003',NULL),(4,'2023-05-17 14:03:06',NULL,'GCC004',NULL),(5,'2023-05-17 14:18:41',NULL,'GCC005',NULL),(6,'2023-05-17 14:20:26',NULL,'GCC006','2023-05-25 00:00:00'),(7,'2023-05-17 14:39:08',NULL,'GCC007','2023-05-25 00:00:00'),(8,'2023-05-17 14:48:47',NULL,'GCC008','2023-05-25 20:00:00'),(9,'2023-05-17 15:05:19',NULL,'GCC009','2023-05-25 22:00:00'),(10,'2023-05-17 15:08:34',NULL,'GCC010','2023-05-25 22:00:00'),(11,'2023-05-17 15:10:30',NULL,'GCC011','2023-05-25 22:00:00');
/*!40000 ALTER TABLE `classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `majors`
--

DROP TABLE IF EXISTS `majors`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `majors` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `majors`
--

LOCK TABLES `majors` WRITE;
/*!40000 ALTER TABLE `majors` DISABLE KEYS */;
INSERT INTO `majors` VALUES (1,'INFORMATION TECHNOLOGY'),(2,'GRAPHIC DESIGN'),(3,'BUSINESS MANAGEMENT'),(4,'MARKETING'),(5,'EVENT MANAGEMENT'),(6,'PUBLIC RELATIONS AND COMMUNICATIONS'),(7,'INTERNATIONAL BUSINESS');
/*!40000 ALTER TABLE `majors` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `roles`
--

DROP TABLE IF EXISTS `roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `roles` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `roles`
--

LOCK TABLES `roles` WRITE;
/*!40000 ALTER TABLE `roles` DISABLE KEYS */;
INSERT INTO `roles` VALUES (1,'ADMIN'),(2,'STUDENT'),(3,'TEACHER');
/*!40000 ALTER TABLE `roles` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subject_classes`
--

DROP TABLE IF EXISTS `subject_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subject_classes` (
  `class_id` bigint NOT NULL,
  `subject_id` int NOT NULL,
  KEY `FKitnnog6jm0qvg0fyivvfe64of` (`subject_id`),
  KEY `FKhntniubqkx6m4froyepa5qbrb` (`class_id`),
  CONSTRAINT `FKhntniubqkx6m4froyepa5qbrb` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `FKitnnog6jm0qvg0fyivvfe64of` FOREIGN KEY (`subject_id`) REFERENCES `subjects` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subject_classes`
--

LOCK TABLES `subject_classes` WRITE;
/*!40000 ALTER TABLE `subject_classes` DISABLE KEYS */;
INSERT INTO `subject_classes` VALUES (1,1),(1,2),(2,1),(2,2),(3,1),(3,2),(4,1),(4,2),(5,1),(5,2),(6,1),(6,2),(7,1),(7,2),(8,1),(8,2),(9,1),(9,2),(10,1),(10,2),(11,1),(11,2);
/*!40000 ALTER TABLE `subject_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subjects`
--

DROP TABLE IF EXISTS `subjects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `subjects` (
  `id` int NOT NULL AUTO_INCREMENT,
  `code` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `major_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK6u68cdt6bbhwl1asx5s9lakv` (`major_id`),
  CONSTRAINT `FK6u68cdt6bbhwl1asx5s9lakv` FOREIGN KEY (`major_id`) REFERENCES `majors` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subjects`
--

LOCK TABLES `subjects` WRITE;
/*!40000 ALTER TABLE `subjects` DISABLE KEYS */;
INSERT INTO `subjects` VALUES (1,'IT01','PROGRAMMING',1),(2,'IT02','DATA STRUCTURES AND ALGORITHMS',1),(3,'IT03','DATABASE',1),(4,'IT04','WEB APPLICATION',1);
/*!40000 ALTER TABLE `subjects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `age` int DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `full_name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,24,'admin@gmail.com','Cuong Pham','$2a$10$okeutwnR8megj1nkoqaWbeA2Z6M9I00y6D2Z3MafFxxJ6Q.kgWVJi','admin'),(2,18,'student@gmail.com','Dung Phan','$2a$10$b.kI5qggCPS.4tHwuVWTBePTww5kl13aoNNkYxMaM4EPoYgaDUcL.','student'),(3,35,'teacher@gmail.com','Tri Nguyen','$2a$10$Eq0ZNNv2L.3zwg6eAH7C8usEMfH2IetHfwWqCx80SwYkkmwDpETQ6','teacher'),(4,20,'test@gmail.com','Hong Tran','$2a$10$hktEH0yBUll7gam2J/.NzuF0KW90cbB/glI3C0JaGwN10OmZ51bN2','test'),(5,18,'student1@gmail.com','Nam Tran','$2a$10$sE2Xs1Jk6Frsda4zmqNK3.ZwzvBoDNWZh52LFkuN3SoIm6fWEGpdi','student1'),(6,18,'student2@gmail.com','Student 2','$2a$10$qKQuV2MiT2N1gruqAXcqHOGGChdOWfd6u1mrdKBGwRhZqRYSD1fvS','student2'),(7,18,'student3@gmail.com','Student 3','$2a$10$LM4sYPPIYvEo40thtLv3pOc5tFsrrxVzpISxv6bJV6CUHOx6sZV8G','student3'),(8,18,'student4@gmail.com','Student 4','$2a$10$RaG4XP933KZtHpANa30Qn.q6MDwidIlV8ZF2bqeGyQFChzhtXniie','student4');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_classes`
--

DROP TABLE IF EXISTS `users_classes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_classes` (
  `class_id` bigint NOT NULL,
  `user_id` bigint NOT NULL,
  KEY `FKj5b5lbmhv0ukolpah9abok2fk` (`user_id`),
  KEY `FK9lyum3j9vro64kadv85twetwr` (`class_id`),
  CONSTRAINT `FK9lyum3j9vro64kadv85twetwr` FOREIGN KEY (`class_id`) REFERENCES `classes` (`id`),
  CONSTRAINT `FKj5b5lbmhv0ukolpah9abok2fk` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_classes`
--

LOCK TABLES `users_classes` WRITE;
/*!40000 ALTER TABLE `users_classes` DISABLE KEYS */;
INSERT INTO `users_classes` VALUES (1,3),(1,5),(1,6),(1,7),(1,8),(2,3),(2,5),(2,6),(2,7),(2,8),(3,3),(3,5),(3,6),(3,7),(3,8),(4,3),(4,5),(4,6),(4,7),(4,8),(5,3),(5,5),(5,6),(5,7),(5,8),(6,3),(6,5),(6,6),(6,7),(6,8),(7,3),(7,5),(7,6),(7,7),(7,8),(8,3),(8,5),(8,6),(8,7),(8,8),(9,3),(9,5),(9,6),(9,7),(9,8),(10,3),(10,5),(10,6),(10,7),(10,8),(11,3),(11,5),(11,6),(11,7),(11,8);
/*!40000 ALTER TABLE `users_classes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users_roles`
--

DROP TABLE IF EXISTS `users_roles`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users_roles` (
  `user_id` bigint NOT NULL,
  `role_id` int NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKj6m8fwv7oqv74fcehir1a9ffy` (`role_id`),
  CONSTRAINT `FK2o0jvgh89lemvvo17cbqvdxaa` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`),
  CONSTRAINT `FKj6m8fwv7oqv74fcehir1a9ffy` FOREIGN KEY (`role_id`) REFERENCES `roles` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users_roles`
--

LOCK TABLES `users_roles` WRITE;
/*!40000 ALTER TABLE `users_roles` DISABLE KEYS */;
INSERT INTO `users_roles` VALUES (1,1),(2,2),(5,2),(6,2),(7,2),(8,2),(3,3);
/*!40000 ALTER TABLE `users_roles` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-06-05 17:13:46
