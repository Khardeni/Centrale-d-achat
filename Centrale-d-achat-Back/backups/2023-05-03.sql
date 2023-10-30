-- MySQL dump 10.13  Distrib 8.0.33, for Win64 (x86_64)
--
-- Host: localhost    Database: pidev1
-- ------------------------------------------------------
-- Server version	5.7.24

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
-- Current Database: `pidev1`
--

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `pidev1` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `pidev1`;

--
-- Table structure for table `absence`
--

DROP TABLE IF EXISTS `absence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `absence` (
  `absence_id` int(11) NOT NULL AUTO_INCREMENT,
  `absence_type` varchar(255) DEFAULT NULL,
  `end_date` date DEFAULT NULL,
  `is_absent` bit(1) DEFAULT NULL,
  `reason` varchar(255) DEFAULT NULL,
  `start_date` date DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `employea_employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`absence_id`),
  KEY `FK6hivdrxo2mcs5eu522ix0tlbu` (`employea_employee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `absence`
--

LOCK TABLES `absence` WRITE;
/*!40000 ALTER TABLE `absence` DISABLE KEYS */;
/*!40000 ALTER TABLE `absence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `appel_offre`
--

DROP TABLE IF EXISTS `appel_offre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `appel_offre` (
  `appel_offre_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_expiration` date DEFAULT NULL,
  `date_publication` date DEFAULT NULL,
  `desc_appel_offre` varchar(255) DEFAULT NULL,
  `status_appel_offre` int(11) DEFAULT NULL,
  `titre_appel_offre` varchar(255) DEFAULT NULL,
  `user_id_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`appel_offre_id`),
  KEY `FKjx7fm7115vqt22hbcqnydhui9` (`user_id_user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `appel_offre`
--

LOCK TABLES `appel_offre` WRITE;
/*!40000 ALTER TABLE `appel_offre` DISABLE KEYS */;
/*!40000 ALTER TABLE `appel_offre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article` (
  `article_id` int(11) NOT NULL AUTO_INCREMENT,
  `barcode` varchar(255) DEFAULT NULL,
  `image` longblob,
  `like_count` int(11) NOT NULL,
  `nom_article` varchar(255) DEFAULT NULL,
  `prixht` float DEFAULT NULL,
  `promotion` float DEFAULT NULL,
  `seuil_stock` int(11) DEFAULT NULL,
  `categorie_article_id` int(11) DEFAULT NULL,
  `marque_id_marque_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`article_id`),
  KEY `FK7odrq7ki1is3albq02jtdv3hu` (`categorie_article_id`),
  KEY `FKh8adrtc6t1xkhxqefdxfi5mw7` (`marque_id_marque_id`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (1,'iVBORw0KGgoAAAANSUhEUgAAAa8AAAAyAQAAAADDn2mFAAAAWklEQVR42mPQfZM6sddY7J7rrGr/NJ2jm4zd7vlELk7JaFVeJ6J04lZVvkm+78SN0+YduVVdaZLR8q7nctIxPw2GUW2j2ka1jWob1TaqbVTbqLZRbaPa6K8NAI0qEyCVZud0AAAAAElFTkSuQmCC',NULL,0,'amortisseur',100,NULL,84,1,1),(2,'iVBORw0KGgoAAAANSUhEUgAAAcUAAAAyAQAAAAAFWv08AAAAYElEQVR42mPQfWY6cfu0T/PORCmqaN7l2GRmdPlytX9K2pHMTvFNJ26fmJj5JnXibGGx2Uc6u/0z92Tunbhs9u1TxhYLGEZ1juoc1Tmqc1TnqM5RnaM6R3WO6hzVCdQJABsYyMSWRYYZAAAAAElFTkSuQmCC',NULL,0,'bielle de suspension',50,NULL,200,1,1),(3,'iVBORw0KGgoAAAANSUhEUgAAAa8AAAAyAQAAAADDn2mFAAAAW0lEQVR42mPQfdY7ce8Up7S4U4LVSmdtNxlLmdw7fTMl7WjnRpN7J47OvlmVuXfili/fZh/pqvZ90Wo+vVAo9pwGw6i2UW2j2ka1jWob1TaqbVTbqLZRbfTXBgAqN7LkTcRlUwAAAABJRU5ErkJggg==',NULL,0,'Turbo',3333,NULL,15,1,1);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_emplacement`
--

DROP TABLE IF EXISTS `article_emplacement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_emplacement` (
  `id_assocae` bigint(20) NOT NULL AUTO_INCREMENT,
  `stocke` int(11) DEFAULT NULL,
  `article_article_id` int(11) DEFAULT NULL,
  `emplacement_emplacement_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id_assocae`),
  KEY `FKyhbvgrrhrdpims8tvn5s3y7a` (`article_article_id`),
  KEY `FK89vcylpyavuuwmqif2n1obmi9` (`emplacement_emplacement_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_emplacement`
--

LOCK TABLES `article_emplacement` WRITE;
/*!40000 ALTER TABLE `article_emplacement` DISABLE KEYS */;
INSERT INTO `article_emplacement` VALUES (1,420,2,1),(2,268,1,1);
/*!40000 ALTER TABLE `article_emplacement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_like`
--

DROP TABLE IF EXISTS `article_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_like` (
  `id_like` bigint(20) NOT NULL AUTO_INCREMENT,
  `article_article_id` int(11) DEFAULT NULL,
  `user_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id_like`),
  KEY `FKnh0w3pyuv55mvq1b55iwcbupy` (`article_article_id`),
  KEY `FKi6s7v9s23vekcekkudheyv04x` (`user_user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_like`
--

LOCK TABLES `article_like` WRITE;
/*!40000 ALTER TABLE `article_like` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article_rating`
--

DROP TABLE IF EXISTS `article_rating`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `article_rating` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rating` int(11) NOT NULL,
  `article_article_id` int(11) DEFAULT NULL,
  `user_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FKoodatgrbnxbfoo7wlxs9kn0b0` (`article_article_id`),
  KEY `FK9mak8jc1r383ic0a1s8saxcqi` (`user_user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article_rating`
--

LOCK TABLES `article_rating` WRITE;
/*!40000 ALTER TABLE `article_rating` DISABLE KEYS */;
/*!40000 ALTER TABLE `article_rating` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assoc_article_charge`
--

DROP TABLE IF EXISTS `assoc_article_charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assoc_article_charge` (
  `assoc_article_charge_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id_article_id` int(11) DEFAULT NULL,
  `charge_id_charge_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`assoc_article_charge_id`),
  KEY `FKmm8sfaytbxusxytqtyncqcf4o` (`article_id_article_id`),
  KEY `FK7o2vefn6drpuqsca8ua2da8kn` (`charge_id_charge_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assoc_article_charge`
--

LOCK TABLES `assoc_article_charge` WRITE;
/*!40000 ALTER TABLE `assoc_article_charge` DISABLE KEYS */;
/*!40000 ALTER TABLE `assoc_article_charge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assoc_article_impot`
--

DROP TABLE IF EXISTS `assoc_article_impot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assoc_article_impot` (
  `assoc_article_impot_id` int(11) NOT NULL AUTO_INCREMENT,
  `article_id_article_id` int(11) DEFAULT NULL,
  `impot_id_impot_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`assoc_article_impot_id`),
  KEY `FKa9a0kmehbogmui2lgc1ro648x` (`article_id_article_id`),
  KEY `FKq5sb6lcifglcry7qxe1c7xt0q` (`impot_id_impot_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assoc_article_impot`
--

LOCK TABLES `assoc_article_impot` WRITE;
/*!40000 ALTER TABLE `assoc_article_impot` DISABLE KEYS */;
/*!40000 ALTER TABLE `assoc_article_impot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `assoc_commande_article`
--

DROP TABLE IF EXISTS `assoc_commande_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assoc_commande_article` (
  `assoc_id` int(11) NOT NULL AUTO_INCREMENT,
  `quantite` int(11) DEFAULT NULL,
  `article_id_article_id` int(11) DEFAULT NULL,
  `commande_id_commande_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`assoc_id`),
  KEY `FKepsflh6rbv4pe1i2tmmxq8tu8` (`article_id_article_id`),
  KEY `FKsfh5q2oe6sfjtli6eqeok3cj8` (`commande_id_commande_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assoc_commande_article`
--

LOCK TABLES `assoc_commande_article` WRITE;
/*!40000 ALTER TABLE `assoc_commande_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `assoc_commande_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `categorie_article`
--

DROP TABLE IF EXISTS `categorie_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `categorie_article` (
  `categorie_article_id` int(11) NOT NULL AUTO_INCREMENT,
  `image_categorie` varchar(255) DEFAULT NULL,
  `nom_categorie` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`categorie_article_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `categorie_article`
--

LOCK TABLES `categorie_article` WRITE;
/*!40000 ALTER TABLE `categorie_article` DISABLE KEYS */;
INSERT INTO `categorie_article` VALUES (1,'','Test'),(2,'','Test');
/*!40000 ALTER TABLE `categorie_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `charge`
--

DROP TABLE IF EXISTS `charge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `charge` (
  `charge_id` int(11) NOT NULL AUTO_INCREMENT,
  `type_charge` int(11) DEFAULT NULL,
  `taux_charge` float DEFAULT NULL,
  `titre_charge` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`charge_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `charge`
--

LOCK TABLES `charge` WRITE;
/*!40000 ALTER TABLE `charge` DISABLE KEYS */;
/*!40000 ALTER TABLE `charge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commande`
--

DROP TABLE IF EXISTS `commande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commande` (
  `commande_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_commande` date DEFAULT NULL,
  `user_id_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`commande_id`),
  KEY `FKoh9399dxm64ywvm2qvjwswyao` (`user_id_user_name`)
) ENGINE=MyISAM AUTO_INCREMENT=25 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commande`
--

LOCK TABLES `commande` WRITE;
/*!40000 ALTER TABLE `commande` DISABLE KEYS */;
INSERT INTO `commande` VALUES (12,'2023-04-28','admin123'),(18,'2023-04-28','admin123'),(24,'2023-04-30','admin123');
/*!40000 ALTER TABLE `commande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `commentaire`
--

DROP TABLE IF EXISTS `commentaire`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `commentaire` (
  `commentaire_id` int(11) NOT NULL AUTO_INCREMENT,
  `commentaire` varchar(255) DEFAULT NULL,
  `dislikes` bigint(20) DEFAULT NULL,
  `likes` bigint(20) DEFAULT NULL,
  `publication_id_publication_id` int(11) DEFAULT NULL,
  `user_id_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`commentaire_id`),
  KEY `FK76wi1d1imsdf3woh01nyb12g6` (`publication_id_publication_id`),
  KEY `FKeft4r9jrrbibmivlyq5ct54rk` (`user_id_user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `commentaire`
--

LOCK TABLES `commentaire` WRITE;
/*!40000 ALTER TABLE `commentaire` DISABLE KEYS */;
/*!40000 ALTER TABLE `commentaire` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `conge`
--

DROP TABLE IF EXISTS `conge`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `conge` (
  `conge_id` int(11) NOT NULL AUTO_INCREMENT,
  `solde_conge` int(11) NOT NULL,
  `confirmation` bit(1) DEFAULT NULL,
  `date_debut` date DEFAULT NULL,
  `date_fin` date DEFAULT NULL,
  `payer` bit(1) DEFAULT NULL,
  `raison` varchar(255) DEFAULT NULL,
  `employe_conge_employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`conge_id`),
  KEY `FKn4sogy4oy0wc6jbhtgm2xg757` (`employe_conge_employee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `conge`
--

LOCK TABLES `conge` WRITE;
/*!40000 ALTER TABLE `conge` DISABLE KEYS */;
/*!40000 ALTER TABLE `conge` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency`
--

DROP TABLE IF EXISTS `currency`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency` (
  `currency_id` int(11) NOT NULL AUTO_INCREMENT,
  `rate` double DEFAULT NULL,
  `active` bit(1) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `symbol` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`currency_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency`
--

LOCK TABLES `currency` WRITE;
/*!40000 ALTER TABLE `currency` DISABLE KEYS */;
/*!40000 ALTER TABLE `currency` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `currency_history`
--

DROP TABLE IF EXISTS `currency_history`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `currency_history` (
  `ch_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_updated` datetime DEFAULT NULL,
  `exchange_rate` double DEFAULT NULL,
  `currency_currency_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ch_id`),
  KEY `FKsv371ewrwvf0pp8mroo0uenq0` (`currency_currency_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `currency_history`
--

LOCK TABLES `currency_history` WRITE;
/*!40000 ALTER TABLE `currency_history` DISABLE KEYS */;
/*!40000 ALTER TABLE `currency_history` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `demande_achat`
--

DROP TABLE IF EXISTS `demande_achat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `demande_achat` (
  `demande_achat_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_expiration` date DEFAULT NULL,
  `date_publication` date DEFAULT NULL,
  `descda` varchar(255) DEFAULT NULL,
  `statusda` int(11) DEFAULT NULL,
  `titreda` varchar(255) DEFAULT NULL,
  `user_id_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`demande_achat_id`),
  KEY `FK3ia79tw1lu49lg4a5lkx19kms` (`user_id_user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `demande_achat`
--

LOCK TABLES `demande_achat` WRITE;
/*!40000 ALTER TABLE `demande_achat` DISABLE KEYS */;
/*!40000 ALTER TABLE `demande_achat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `department_id` bigint(20) NOT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`department_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,NULL);
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emplacement`
--

DROP TABLE IF EXISTS `emplacement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emplacement` (
  `emplacement_id` int(11) NOT NULL AUTO_INCREMENT,
  `adresse_emplacement` varchar(255) DEFAULT NULL,
  `gouvernorat` varchar(255) DEFAULT NULL,
  `nom_emplacement` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`emplacement_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emplacement`
--

LOCK TABLES `emplacement` WRITE;
/*!40000 ALTER TABLE `emplacement` DISABLE KEYS */;
INSERT INTO `emplacement` VALUES (1,'Bizerte, el rimel','Bizerte',NULL),(2,'Tunis, rue requin','Tunis',NULL),(3,'gafsa, rue el fol','Gafsa',NULL);
/*!40000 ALTER TABLE `emplacement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `emplacement_departement`
--

DROP TABLE IF EXISTS `emplacement_departement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `emplacement_departement` (
  `emplacement_departement_id` int(11) NOT NULL AUTO_INCREMENT,
  `departement_id_department_id` bigint(20) DEFAULT NULL,
  `emplacement_id_emplacement_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`emplacement_departement_id`),
  KEY `FKp3jvfv5xbe8dpy1as85r7ulol` (`departement_id_department_id`),
  KEY `FK9rmjvd7ymikaoxgnkcy4bcl3s` (`emplacement_id_emplacement_id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `emplacement_departement`
--

LOCK TABLES `emplacement_departement` WRITE;
/*!40000 ALTER TABLE `emplacement_departement` DISABLE KEYS */;
INSERT INTO `emplacement_departement` VALUES (1,1,1);
/*!40000 ALTER TABLE `emplacement_departement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `employee_id` bigint(20) NOT NULL AUTO_INCREMENT,
  `total_worked_hours` double DEFAULT NULL,
  `job_title` varchar(255) DEFAULT NULL,
  `salary` double DEFAULT NULL,
  `emplacement_departement_emplacement_departement_id` int(11) DEFAULT NULL,
  `user_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`employee_id`),
  KEY `FKneiucvw4sdfn7mv43w0urvpcl` (`emplacement_departement_emplacement_departement_id`),
  KEY `FKskw2o44mixm9wtc64sn6aa7vr` (`user_user_name`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (1,NULL,NULL,NULL,NULL,'admin123');
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facture`
--

DROP TABLE IF EXISTS `facture`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facture` (
  `facture_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_facturation` date DEFAULT NULL,
  `etat_facture` int(11) DEFAULT NULL,
  `prixht` float DEFAULT NULL,
  `prixttc` float DEFAULT NULL,
  `solde` float DEFAULT NULL,
  `commande_id_commande_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`facture_id`),
  KEY `FKjxcnwsk3vpp5xoefrjf576tmy` (`commande_id_commande_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facture`
--

LOCK TABLES `facture` WRITE;
/*!40000 ALTER TABLE `facture` DISABLE KEYS */;
INSERT INTO `facture` VALUES (3,'2023-04-11',0,3500,4165,0,12),(8,'2023-06-15',0,1200,1428,0,18),(9,'2023-04-30',0,3000,3570,0,24);
/*!40000 ALTER TABLE `facture` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `facture_avoir`
--

DROP TABLE IF EXISTS `facture_avoir`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `facture_avoir` (
  `facture_avoir_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_facturation_avoir` date DEFAULT NULL,
  `etat_facture` int(11) DEFAULT NULL,
  `prixttc` float DEFAULT NULL,
  `facture_id_facture_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`facture_avoir_id`),
  KEY `FKmhhbxerm6j29ix8xopysha1we` (`facture_id_facture_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `facture_avoir`
--

LOCK TABLES `facture_avoir` WRITE;
/*!40000 ALTER TABLE `facture_avoir` DISABLE KEYS */;
/*!40000 ALTER TABLE `facture_avoir` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `hibernate_sequence`
--

DROP TABLE IF EXISTS `hibernate_sequence`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `hibernate_sequence`
--

LOCK TABLES `hibernate_sequence` WRITE;
/*!40000 ALTER TABLE `hibernate_sequence` DISABLE KEYS */;
INSERT INTO `hibernate_sequence` VALUES (2);
/*!40000 ALTER TABLE `hibernate_sequence` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `impot`
--

DROP TABLE IF EXISTS `impot`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `impot` (
  `impot_id` int(11) NOT NULL AUTO_INCREMENT,
  `is_active` bit(1) DEFAULT NULL,
  `taux_impot` double DEFAULT NULL,
  `titre_impot` varchar(255) DEFAULT NULL,
  `type_impot` int(11) DEFAULT NULL,
  PRIMARY KEY (`impot_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `impot`
--

LOCK TABLES `impot` WRITE;
/*!40000 ALTER TABLE `impot` DISABLE KEYS */;
/*!40000 ALTER TABLE `impot` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ligne_panier`
--

DROP TABLE IF EXISTS `ligne_panier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ligne_panier` (
  `ligne_panier_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_added` datetime DEFAULT NULL,
  `quantite` int(11) DEFAULT NULL,
  `status` int(11) DEFAULT NULL,
  `article_article_id` int(11) DEFAULT NULL,
  `panier_panier_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`ligne_panier_id`),
  KEY `FKo468a808v55iajx5y8bj671m3` (`article_article_id`),
  KEY `FKsul59cd9svildr36tl08dtwue` (`panier_panier_id`)
) ENGINE=MyISAM AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ligne_panier`
--

LOCK TABLES `ligne_panier` WRITE;
/*!40000 ALTER TABLE `ligne_panier` DISABLE KEYS */;
INSERT INTO `ligne_panier` VALUES (5,'2023-04-28 20:49:32',10,12,2,1),(4,'2023-04-28 20:49:26',30,12,1,1),(8,'2023-04-28 22:35:54',12,18,1,1),(9,'2023-04-30 20:42:21',30,24,1,1);
/*!40000 ALTER TABLE `ligne_panier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `likes`
--

DROP TABLE IF EXISTS `likes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `likes` (
  `like_id` int(11) NOT NULL AUTO_INCREMENT,
  `object_id` int(11) DEFAULT NULL,
  `type_like` int(11) DEFAULT NULL,
  `user_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`like_id`),
  KEY `FKfa5t0tf4lhckockxvk429ijgh` (`user_user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `likes`
--

LOCK TABLES `likes` WRITE;
/*!40000 ALTER TABLE `likes` DISABLE KEYS */;
/*!40000 ALTER TABLE `likes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livraison`
--

DROP TABLE IF EXISTS `livraison`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livraison` (
  `livraison_id` int(11) NOT NULL AUTO_INCREMENT,
  `adresse_livraison` varchar(255) DEFAULT NULL,
  `date_envoi` date DEFAULT NULL,
  `date_livraison` date DEFAULT NULL,
  `date_livraison_prevu` date DEFAULT NULL,
  `etat_livraison` int(11) DEFAULT NULL,
  `type_livraison` int(11) DEFAULT NULL,
  `commande_id_commande_id` int(11) DEFAULT NULL,
  `emplacement_id_emplacement_id` int(11) DEFAULT NULL,
  `facture_id_facture_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`livraison_id`),
  KEY `FK521k5ca2n0d8htji5c6d2lk0k` (`commande_id_commande_id`),
  KEY `FK3yehiw73dpd7yr0nxjwn4i0mw` (`emplacement_id_emplacement_id`),
  KEY `FKnrck6y19u2tgw5w2mtliagxhd` (`facture_id_facture_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livraison`
--

LOCK TABLES `livraison` WRITE;
/*!40000 ALTER TABLE `livraison` DISABLE KEYS */;
INSERT INTO `livraison` VALUES (3,'gafsa, cité koudhat','2023-04-30',NULL,'2023-05-03',0,1,12,3,3),(4,'gafsa, cité koudhat','2023-04-30','2023-04-30','2023-05-03',0,1,18,3,8),(5,'ariana',NULL,NULL,'2023-04-05',0,1,24,2,9);
/*!40000 ALTER TABLE `livraison` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livraison_livreur`
--

DROP TABLE IF EXISTS `livraison_livreur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livraison_livreur` (
  `livraison_livreur_id` int(11) NOT NULL AUTO_INCREMENT,
  `livraison_livraison_id` int(11) DEFAULT NULL,
  `livreur_livreur_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`livraison_livreur_id`),
  KEY `FK29ysvbbh1mmu1ap0q7odtqbgd` (`livraison_livraison_id`),
  KEY `FK3iasmoy9ud7an8xrcn9lggu0y` (`livreur_livreur_id`)
) ENGINE=MyISAM AUTO_INCREMENT=16 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livraison_livreur`
--

LOCK TABLES `livraison_livreur` WRITE;
/*!40000 ALTER TABLE `livraison_livreur` DISABLE KEYS */;
INSERT INTO `livraison_livreur` VALUES (15,3,13),(14,4,13);
/*!40000 ALTER TABLE `livraison_livreur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `livreur`
--

DROP TABLE IF EXISTS `livreur`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `livreur` (
  `livreur_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_livreur` varchar(255) DEFAULT NULL,
  `secteur` varchar(255) DEFAULT NULL,
  `societe_livraison` varchar(255) DEFAULT NULL,
  `date_ajout` date DEFAULT NULL,
  `num_livreur` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`livreur_id`)
) ENGINE=MyISAM AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `livreur`
--

LOCK TABLES `livreur` WRITE;
/*!40000 ALTER TABLE `livreur` DISABLE KEYS */;
INSERT INTO `livreur` VALUES (1,'mohamed ben mabrouk','ariana','first delivery','2023-04-27','95360255'),(2,'karim ben mohamed','ben arous','aramex','2023-04-27','29063300'),(13,'Houssem Hosni','gafsa','Tunisie Express','2023-04-30','29063300');
/*!40000 ALTER TABLE `livreur` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `marque`
--

DROP TABLE IF EXISTS `marque`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `marque` (
  `marque_id` int(11) NOT NULL AUTO_INCREMENT,
  `logo_marque` longblob,
  `nom_marque` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`marque_id`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `marque`
--

LOCK TABLES `marque` WRITE;
/*!40000 ALTER TABLE `marque` DISABLE KEYS */;
INSERT INTO `marque` VALUES (1,NULL,'Hello'),(2,NULL,'Hello');
/*!40000 ALTER TABLE `marque` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offre`
--

DROP TABLE IF EXISTS `offre`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offre` (
  `offre_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_offre` date DEFAULT NULL,
  `desc_offre` varchar(255) DEFAULT NULL,
  `etat_offre` int(11) DEFAULT NULL,
  `appel_offre_id_appel_offre_id` int(11) DEFAULT NULL,
  `user_id_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`offre_id`),
  KEY `FKi75y0ykkyuf5ir1p0ta24lh8t` (`appel_offre_id_appel_offre_id`),
  KEY `FK839mkbsvokw4yfbbgdskag0qu` (`user_id_user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offre`
--

LOCK TABLES `offre` WRITE;
/*!40000 ALTER TABLE `offre` DISABLE KEYS */;
/*!40000 ALTER TABLE `offre` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `offre_demande`
--

DROP TABLE IF EXISTS `offre_demande`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `offre_demande` (
  `offre_demande_id` int(11) NOT NULL AUTO_INCREMENT,
  `dateod` date DEFAULT NULL,
  `statusod` int(11) DEFAULT NULL,
  `demande_achat_id_demande_achat_id` int(11) DEFAULT NULL,
  `user_id_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`offre_demande_id`),
  KEY `FKgp79rmmrr7nupwemju6vd1bq9` (`demande_achat_id_demande_achat_id`),
  KEY `FKo880p8yxx97fraf52g7colpr9` (`user_id_user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `offre_demande`
--

LOCK TABLES `offre_demande` WRITE;
/*!40000 ALTER TABLE `offre_demande` DISABLE KEYS */;
/*!40000 ALTER TABLE `offre_demande` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `paiement`
--

DROP TABLE IF EXISTS `paiement`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `paiement` (
  `paiement_id` int(11) NOT NULL AUTO_INCREMENT,
  `card_number` int(11) DEFAULT NULL,
  `cvv` int(11) DEFAULT NULL,
  `etat_paiement` int(11) DEFAULT NULL,
  `expiration_date` date DEFAULT NULL,
  `methode_paiement` int(11) DEFAULT NULL,
  `currency_id_currency_id` int(11) DEFAULT NULL,
  `facture_id_facture_id` int(11) DEFAULT NULL,
  `date_paiement` date DEFAULT NULL,
  PRIMARY KEY (`paiement_id`),
  KEY `FK697qtmolulbax1fbn9x5i1asm` (`currency_id_currency_id`),
  KEY `FKtakxwsbabi08gv6gu8f1ili5t` (`facture_id_facture_id`)
) ENGINE=MyISAM AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `paiement`
--

LOCK TABLES `paiement` WRITE;
/*!40000 ALTER TABLE `paiement` DISABLE KEYS */;
INSERT INTO `paiement` VALUES (3,1234657,4123,NULL,'2026-02-03',1,NULL,3,'2023-04-28'),(4,1234657,4123,NULL,'2026-02-03',1,NULL,8,'2023-04-28'),(5,12149852,1254,NULL,'2023-03-13',2,NULL,9,'2023-04-30');
/*!40000 ALTER TABLE `paiement` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `panier`
--

DROP TABLE IF EXISTS `panier`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `panier` (
  `panier_id` int(11) NOT NULL AUTO_INCREMENT,
  `cout_total` float DEFAULT NULL,
  `date_updated` date DEFAULT NULL,
  `nbr_article` int(11) DEFAULT NULL,
  `user_id_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`panier_id`),
  KEY `FKewi70xi8e7d7ytyt4y1071dme` (`user_id_user_name`)
) ENGINE=MyISAM AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `panier`
--

LOCK TABLES `panier` WRITE;
/*!40000 ALTER TABLE `panier` DISABLE KEYS */;
INSERT INTO `panier` VALUES (1,7700,'2023-03-11',4,'admin123');
/*!40000 ALTER TABLE `panier` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `performance`
--

DROP TABLE IF EXISTS `performance`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `performance` (
  `performance_id` int(11) NOT NULL AUTO_INCREMENT,
  `performance_date` date DEFAULT NULL,
  `performance_rating` double DEFAULT NULL,
  `employep_employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`performance_id`),
  KEY `FKnsks00vcig87sk3wqxne3uchr` (`employep_employee_id`)
) ENGINE=MyISAM AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `performance`
--

LOCK TABLES `performance` WRITE;
/*!40000 ALTER TABLE `performance` DISABLE KEYS */;
INSERT INTO `performance` VALUES (1,'2023-04-30',4,1),(2,'2023-04-30',4,1),(3,'2023-04-30',4,1),(4,'2023-04-30',4,1),(5,'2023-04-30',4,1),(6,'2023-04-30',4,1),(7,'2023-04-30',4,1);
/*!40000 ALTER TABLE `performance` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `publication`
--

DROP TABLE IF EXISTS `publication`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `publication` (
  `publication_id` int(11) NOT NULL AUTO_INCREMENT,
  `contenu` varchar(255) DEFAULT NULL,
  `date_publication` date DEFAULT NULL,
  `dislikes` bigint(20) DEFAULT NULL,
  `likes` bigint(20) DEFAULT NULL,
  `titre_publication` varchar(255) DEFAULT NULL,
  `rubrique_id_rubrique_id` int(11) DEFAULT NULL,
  `user_id_user_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`publication_id`),
  KEY `FKjursv7eugg9eft8ecnkjwj9ob` (`rubrique_id_rubrique_id`),
  KEY `FKa3c316o5s5ss877mfpgxcoy7q` (`user_id_user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `publication`
--

LOCK TABLES `publication` WRITE;
/*!40000 ALTER TABLE `publication` DISABLE KEYS */;
/*!40000 ALTER TABLE `publication` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `retour`
--

DROP TABLE IF EXISTS `retour`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `retour` (
  `retour_id` int(11) NOT NULL AUTO_INCREMENT,
  `date_annulation` date DEFAULT NULL,
  `date_retour` date DEFAULT NULL,
  `livraison_id_livraison_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`retour_id`),
  KEY `FKn1f10x12xrdeutd3mbqcm3nio` (`livraison_id_livraison_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `retour`
--

LOCK TABLES `retour` WRITE;
/*!40000 ALTER TABLE `retour` DISABLE KEYS */;
/*!40000 ALTER TABLE `retour` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role`
--

DROP TABLE IF EXISTS `role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `role` (
  `role_name` varchar(255) NOT NULL,
  `role_description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`role_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role`
--

LOCK TABLES `role` WRITE;
/*!40000 ALTER TABLE `role` DISABLE KEYS */;
INSERT INTO `role` VALUES ('Admin','Admin role'),('Fournisseur','Fournisseur role'),('User','Default role for newly created record');
/*!40000 ALTER TABLE `role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `rubrique`
--

DROP TABLE IF EXISTS `rubrique`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `rubrique` (
  `rubrique_id` int(11) NOT NULL AUTO_INCREMENT,
  `titre_rubrique` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rubrique_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `rubrique`
--

LOCK TABLES `rubrique` WRITE;
/*!40000 ALTER TABLE `rubrique` DISABLE KEYS */;
/*!40000 ALTER TABLE `rubrique` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `type_article`
--

DROP TABLE IF EXISTS `type_article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `type_article` (
  `type_article_id` int(11) NOT NULL AUTO_INCREMENT,
  `nom_type_article` varchar(255) DEFAULT NULL,
  `categorie_article_id_categorie_article_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`type_article_id`),
  KEY `FKddlap9a3pw9dul21siiwp0bn2` (`categorie_article_id_categorie_article_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `type_article`
--

LOCK TABLES `type_article` WRITE;
/*!40000 ALTER TABLE `type_article` DISABLE KEYS */;
/*!40000 ALTER TABLE `type_article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user` (
  `user_name` varchar(255) NOT NULL,
  `code` varchar(255) DEFAULT NULL,
  `is_locked` int(11) DEFAULT NULL,
  `isverified` int(11) NOT NULL,
  `two_factor_auth` int(11) DEFAULT NULL,
  `user_adress` varchar(255) DEFAULT NULL,
  `user_cin` bigint(20) DEFAULT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_first_name` varchar(255) DEFAULT NULL,
  `user_last_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `verification_token` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES ('admin123',NULL,0,1,0,NULL,NULL,NULL,'admin','admin','$2a$10$Eoll7/uJxRu2591b7huL/eXYQM6FWYjOIYuNTqObZZkbhdg2bF28q','52087500',NULL);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `user_role` (
  `user_id` varchar(255) NOT NULL,
  `role_id` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`,`role_id`),
  KEY `FKa68196081fvovjhkek5m97n3y` (`role_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES ('admin123','Admin');
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `verification_token`
--

DROP TABLE IF EXISTS `verification_token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `verification_token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `expiry_date` datetime DEFAULT NULL,
  `token` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK31kx1end7xr79p590x1m0ixik` (`user_name`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `verification_token`
--

LOCK TABLES `verification_token` WRITE;
/*!40000 ALTER TABLE `verification_token` DISABLE KEYS */;
/*!40000 ALTER TABLE `verification_token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `worked_hours`
--

DROP TABLE IF EXISTS `worked_hours`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `worked_hours` (
  `id_wh` int(11) NOT NULL AUTO_INCREMENT,
  `start_date` date DEFAULT NULL,
  `work_hours` double DEFAULT NULL,
  `employeh_employee_id` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`id_wh`),
  KEY `FKkarodwwio728shva8789b7avx` (`employeh_employee_id`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `worked_hours`
--

LOCK TABLES `worked_hours` WRITE;
/*!40000 ALTER TABLE `worked_hours` DISABLE KEYS */;
/*!40000 ALTER TABLE `worked_hours` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2023-05-03 23:28:34
