-- --------------------------------------------------------
-- Host:                         127.0.0.1
-- Server Version:               8.0.19 - MySQL Community Server - GPL
-- Server Betriebssystem:        Linux
-- HeidiSQL Version:             11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- Exportiere Datenbank Struktur für osm01
CREATE DATABASE IF NOT EXISTS `osm01` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `osm01`;

-- Exportiere Struktur von Tabelle osm01.rateme_poi
CREATE TABLE IF NOT EXISTS `rateme_poi` (
  `osm_id` bigint NOT NULL,
  `position` geometry NOT NULL,
  `poi_type` varchar(255) NOT NULL,
  PRIMARY KEY (`osm_id`),
  SPATIAL KEY `position` (`position`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle osm01.rateme_poi_tag
CREATE TABLE IF NOT EXISTS `rateme_poi_tag` (
  `osm_id` bigint NOT NULL,
  `tag` varchar(255) NOT NULL,
  `value` varchar(255) NOT NULL,
  PRIMARY KEY (`osm_id`,`tag`),
  CONSTRAINT `rateme_poi_tag_ibfk_1` FOREIGN KEY (`osm_id`) REFERENCES `rateme_poi` (`osm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle osm01.rateme_rating
CREATE TABLE IF NOT EXISTS `rateme_rating` (
  `rating_id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `osm_id` bigint NOT NULL,
  `rating_type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `grade` int DEFAULT NULL,
  `txt` varchar(2000) NOT NULL,
  `image_path` mediumblob,
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`rating_id`),
  KEY `user_id` (`user_id`),
  KEY `osm_id` (`osm_id`),
  CONSTRAINT `rateme_rating_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `rateme_user` (`user_id`),
  CONSTRAINT `rateme_rating_ibfk_2` FOREIGN KEY (`osm_id`) REFERENCES `rateme_poi` (`osm_id`)
) ENGINE=InnoDB AUTO_INCREMENT=34 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Daten Export vom Benutzer nicht ausgewählt

-- Exportiere Struktur von Tabelle osm01.rateme_user
CREATE TABLE IF NOT EXISTS `rateme_user` (
  `user_id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `E_Mail` varchar(50) NOT NULL,
  `firstname` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `street` varchar(30) NOT NULL,
  `streetNr` varchar(20) NOT NULL,
  `zip` varchar(20) NOT NULL,
  `city` varchar(30) NOT NULL,
  `password` varchar(200) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `modify_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- Daten Export vom Benutzer nicht ausgewählt

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
