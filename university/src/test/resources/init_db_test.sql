CREATE DATABASE IF NOT EXISTS `course_model_university_test`;
use `course_model_university_test`;

DROP TABLE IF EXISTS `ratings`;
DROP TABLE IF EXISTS `study_course`;
DROP TABLE IF EXISTS `course_professor`;
DROP TABLE IF EXISTS `course`;
DROP TABLE IF EXISTS `students`;
DROP TABLE IF EXISTS `professors`;
--
-- Table structure for table `professors`
--


CREATE TABLE `professors`
(
    `id`      bigint       NOT NULL AUTO_INCREMENT,
    `address` varchar(255) DEFAULT NULL,
    `name`    varchar(255) NOT NULL,
    `payment` float        DEFAULT NULL,
    `phone`   varchar(255) NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 40
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

--
-- Dumping data for table `professors`
--

LOCK TABLES `professors` WRITE;
/*!40000 ALTER TABLE `professors`
    DISABLE KEYS */;
INSERT INTO `professors`
VALUES (1, 'Спортивная 23', 'Амосов В.П.', 24000, '+79787594352'),
       (2, 'Спортивная 24', 'Герберт А.А.', 24800, '+79787594353');
/*!40000 ALTER TABLE `professors`
    ENABLE KEYS */;
UNLOCK TABLES;


--
-- Table structure for table `students`
--


/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `students`
(
    `id`          bigint NOT NULL AUTO_INCREMENT,
    `address`     varchar(255) DEFAULT NULL,
    `email`       varchar(255) DEFAULT NULL,
    `name`        varchar(255) DEFAULT NULL,
    `phone`       varchar(255) DEFAULT NULL,
    `progress`    float        DEFAULT NULL,
    `record_book` int          DEFAULT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UK_e2rndfrsx22acpq2ty1caeuyw` (`email`),
    UNIQUE KEY `UK_4j48kma5fa3dcya13gd0l3gi` (`phone`),
    UNIQUE KEY `UK_2qe78huwydu7x08yntuq701qc` (`record_book`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students`
    DISABLE KEYS */;
INSERT INTO `students`
VALUES (1, 'Фрунзе 43', 'ya@ya.ru', 'Иванов Иван', '+79787549393', NULL, 565656),
       (2, 'Фрунзе 45', 'ya2@ya.ru', 'Петров Петр', '+79787549394', NULL, 565657);
/*!40000 ALTER TABLE `students`
    ENABLE KEYS */;
UNLOCK TABLES;



--
-- Table structure for table `course`
--


CREATE TABLE `course`
(
    `id`           bigint NOT NULL AUTO_INCREMENT,
    `cost`         float        DEFAULT NULL,
    `name`         varchar(255) DEFAULT NULL,
    `number`       int          DEFAULT NULL,
    `professor_id` bigint       DEFAULT NULL,
    PRIMARY KEY (`id`),
    KEY `FK2fk1pja85xdw7534e2f1hrm4p` (`professor_id`),
    CONSTRAINT `FK2fk1pja85xdw7534e2f1hrm4p` FOREIGN KEY (`professor_id`) REFERENCES `professors` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 14
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;

--
-- Dumping data for table `course`
--

LOCK TABLES `course` WRITE;
/*!40000 ALTER TABLE `course`
    DISABLE KEYS */;
INSERT INTO `course`
VALUES (1, 1500, 'Математика', 555, 1),
       (2, 2500, 'Физика', 666, 1);
/*!40000 ALTER TABLE `course`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `course_professor`
--


CREATE TABLE `course_professor`
(
    `course_id`     bigint NOT NULL,
    `professors_id` bigint NOT NULL,
    PRIMARY KEY (`course_id`, `professors_id`),
    KEY `FK3wp9olpc84yp7og1gsbhsy7eg` (`professors_id`),
    CONSTRAINT `FK3wp9olpc84yp7og1gsbhsy7eg` FOREIGN KEY (`professors_id`) REFERENCES `professors` (`id`),
    CONSTRAINT `FK593ihroxwe5jxdg5a5be0vfev` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `course_professor`
--

LOCK TABLES `course_professor` WRITE;
/*!40000 ALTER TABLE `course_professor`
    DISABLE KEYS */;
INSERT INTO `course_professor`
VALUES (1, 1),
       (2, 1);
/*!40000 ALTER TABLE `course_professor`
    ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `study_course`
--

/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `study_course`
(
    `id`         bigint NOT NULL AUTO_INCREMENT,
    `course_id`  bigint NOT NULL,
    `student_id` bigint NOT NULL,
    PRIMARY KEY (`id`),
    UNIQUE KEY `UKqcov1y5f6d2x9q61wbnddci63` (`course_id`, `student_id`),
    KEY `FKkforxjlgbn46a7eskvokt0heh` (`student_id`),
    CONSTRAINT `FK3pmll6c8go78pjqvyrnsjhigy` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`),
    CONSTRAINT `FKkforxjlgbn46a7eskvokt0heh` FOREIGN KEY (`student_id`) REFERENCES `students` (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 19
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `study_course`
--

LOCK TABLES `study_course` WRITE;
/*!40000 ALTER TABLE `study_course`
    DISABLE KEYS */;
INSERT INTO `study_course`
VALUES (1, 1, 1),
       (2, 1, 2),
       (3, 2, 1),
       (4, 2, 2);
/*!40000 ALTER TABLE `study_course`
    ENABLE KEYS */;
UNLOCK TABLES;
--
-- Table structure for table `ratings`
--


/*!40101 SET @saved_cs_client = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `ratings`
(
    `id`              bigint NOT NULL AUTO_INCREMENT,
    `rating`          int    DEFAULT NULL,
    `study_course_id` bigint DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 17
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ratings`
--

LOCK TABLES `ratings` WRITE;
/*!40000 ALTER TABLE `ratings`
    DISABLE KEYS */;
INSERT INTO `ratings`
VALUES (1, 5, 1)
     , (2, 5, 1)
     , (3, 3, 1)
     , (4, 4, 2)
     , (5, 4, 2)
     , (6, 4, 2)
     , (7, 3, 3)
     , (8, 3, 3)
     , (9, 3, 3)
     , (10, 2, 4)
     , (11, 2, 4)
     , (12, 2, 4);
/*!40000 ALTER TABLE `ratings`
    ENABLE KEYS */;
UNLOCK TABLES;






