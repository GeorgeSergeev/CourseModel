-- phpMyAdmin SQL Dump
-- version 4.6.5.2
-- https://www.phpmyadmin.net/
--
-- Хост: 127.0.0.1:3306
-- Время создания: Июн 22 2018 г., 02:19
-- Версия сервера: 5.5.53
-- Версия PHP: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- База данных: `CourseModel`
--

-- --------------------------------------------------------

--
-- Структура таблицы `course`
--

CREATE TABLE `course` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `jhi_number` int(11) DEFAULT NULL,
  `jhi_cost` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `course`
--

INSERT INTO `course` (`id`, `name`, `jhi_number`, `jhi_cost`) VALUES
(1, 'Java', 3413, 10000),
(2, 'C#', 2312, 6000),
(3, 'Python', 9001, 2500),
(4, 'JS', 9003, 5000);

-- --------------------------------------------------------

--
-- Структура таблицы `DATABASECHANGELOG`
--

CREATE TABLE `DATABASECHANGELOG` (
  `ID` varchar(255) NOT NULL,
  `AUTHOR` varchar(255) NOT NULL,
  `FILENAME` varchar(255) NOT NULL,
  `DATEEXECUTED` datetime NOT NULL,
  `ORDEREXECUTED` int(11) NOT NULL,
  `EXECTYPE` varchar(10) NOT NULL,
  `MD5SUM` varchar(35) DEFAULT NULL,
  `DESCRIPTION` varchar(255) DEFAULT NULL,
  `COMMENTS` varchar(255) DEFAULT NULL,
  `TAG` varchar(255) DEFAULT NULL,
  `LIQUIBASE` varchar(20) DEFAULT NULL,
  `CONTEXTS` varchar(255) DEFAULT NULL,
  `LABELS` varchar(255) DEFAULT NULL,
  `DEPLOYMENT_ID` varchar(10) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `DATABASECHANGELOG`
--

INSERT INTO `DATABASECHANGELOG` (`ID`, `AUTHOR`, `FILENAME`, `DATEEXECUTED`, `ORDEREXECUTED`, `EXECTYPE`, `MD5SUM`, `DESCRIPTION`, `COMMENTS`, `TAG`, `LIQUIBASE`, `CONTEXTS`, `LABELS`, `DEPLOYMENT_ID`) VALUES
('00000000000001', 'jhipster', 'config/liquibase/changelog/00000000000000_initial_schema.xml', '2018-06-19 15:18:43', 1, 'EXECUTED', '7:1a93aa1077a0f85c08092bf95f44c635', 'createTable tableName=jhi_user; createIndex indexName=idx_user_login, tableName=jhi_user; createIndex indexName=idx_user_email, tableName=jhi_user; createTable tableName=jhi_authority; createTable tableName=jhi_user_authority; addPrimaryKey tableN...', '', NULL, '3.5.3', NULL, NULL, '9410722916'),
('20180619121746-1', 'jhipster', 'config/liquibase/changelog/20180619121746_added_entity_Student.xml', '2018-06-19 15:37:48', 2, 'EXECUTED', '7:ea0ca3ca6f2b19efafa29a7315964275', 'createTable tableName=student; createTable tableName=student_course; addPrimaryKey tableName=student_course', '', NULL, '3.5.3', NULL, NULL, '9411868848'),
('20180619121747-1', 'jhipster', 'config/liquibase/changelog/20180619121747_added_entity_Teacher.xml', '2018-06-19 15:37:48', 3, 'EXECUTED', '7:233c30a18b01cf99338628675ab64070', 'createTable tableName=teacher; createTable tableName=teacher_course; addPrimaryKey tableName=teacher_course', '', NULL, '3.5.3', NULL, NULL, '9411868848'),
('20180619121748-1', 'jhipster', 'config/liquibase/changelog/20180619121748_added_entity_Course.xml', '2018-06-19 15:37:48', 4, 'EXECUTED', '7:9f2f26986723f99aac0d16d6cdd6a738', 'createTable tableName=course', '', NULL, '3.5.3', NULL, NULL, '9411868848'),
('20180619121749-1', 'jhipster', 'config/liquibase/changelog/20180619121749_added_entity_Subject.xml', '2018-06-19 15:37:48', 5, 'EXECUTED', '7:92782d4985736d8e254eef762729fffe', 'createTable tableName=subject', '', NULL, '3.5.3', NULL, NULL, '9411868848'),
('20180619121746-2', 'jhipster', 'config/liquibase/changelog/20180619121746_added_entity_constraints_Student.xml', '2018-06-19 15:37:49', 6, 'EXECUTED', '7:c9219c96bb000d2b5744964e9a9ac271', 'addForeignKeyConstraint baseTableName=student_course, constraintName=fk_student_course_students_id, referencedTableName=student; addForeignKeyConstraint baseTableName=student_course, constraintName=fk_student_course_courses_id, referencedTableName...', '', NULL, '3.5.3', NULL, NULL, '9411868848'),
('20180619121747-2', 'jhipster', 'config/liquibase/changelog/20180619121747_added_entity_constraints_Teacher.xml', '2018-06-19 15:37:49', 7, 'EXECUTED', '7:70cc55d499f835576aaac0773136649f', 'addForeignKeyConstraint baseTableName=teacher_course, constraintName=fk_teacher_course_teachers_id, referencedTableName=teacher; addForeignKeyConstraint baseTableName=teacher_course, constraintName=fk_teacher_course_courses_id, referencedTableName...', '', NULL, '3.5.3', NULL, NULL, '9411868848'),
('20180619121749-2', 'jhipster', 'config/liquibase/changelog/20180619121749_added_entity_constraints_Subject.xml', '2018-06-19 15:37:49', 8, 'EXECUTED', '7:c9e750164b804bfbafd757c710866a51', 'addForeignKeyConstraint baseTableName=subject, constraintName=fk_subject_course_id, referencedTableName=course', '', NULL, '3.5.3', NULL, NULL, '9411868848');

-- --------------------------------------------------------

--
-- Структура таблицы `DATABASECHANGELOGLOCK`
--

CREATE TABLE `DATABASECHANGELOGLOCK` (
  `ID` int(11) NOT NULL,
  `LOCKED` bit(1) NOT NULL,
  `LOCKGRANTED` datetime DEFAULT NULL,
  `LOCKEDBY` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `DATABASECHANGELOGLOCK`
--

INSERT INTO `DATABASECHANGELOGLOCK` (`ID`, `LOCKED`, `LOCKGRANTED`, `LOCKEDBY`) VALUES
(1, b'0', NULL, NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `jhi_authority`
--

CREATE TABLE `jhi_authority` (
  `name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `jhi_authority`
--

INSERT INTO `jhi_authority` (`name`) VALUES
('ROLE_ADMIN'),
('ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `jhi_persistent_audit_event`
--

CREATE TABLE `jhi_persistent_audit_event` (
  `event_id` bigint(20) NOT NULL,
  `principal` varchar(50) NOT NULL,
  `event_date` timestamp NULL DEFAULT NULL,
  `event_type` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `jhi_persistent_audit_event`
--

INSERT INTO `jhi_persistent_audit_event` (`event_id`, `principal`, `event_date`, `event_type`) VALUES
(1, 'user', '2018-06-19 12:18:58', 'AUTHENTICATION_SUCCESS'),
(2, 'admin', '2018-06-19 12:45:13', 'AUTHENTICATION_SUCCESS'),
(3, 'admin', '2018-06-19 16:16:26', 'AUTHENTICATION_FAILURE'),
(4, 'admin', '2018-06-19 16:16:28', 'AUTHENTICATION_SUCCESS'),
(5, 'admin', '2018-06-19 18:41:00', 'AUTHENTICATION_SUCCESS'),
(6, 'admin', '2018-06-19 21:33:33', 'AUTHENTICATION_SUCCESS'),
(7, 'admin', '2018-06-20 19:20:30', 'AUTHENTICATION_SUCCESS'),
(8, 'admin', '2018-06-20 20:33:20', 'AUTHENTICATION_SUCCESS'),
(9, 'admin', '2018-06-20 20:39:01', 'AUTHENTICATION_SUCCESS'),
(10, 'user', '2018-06-21 02:03:42', 'AUTHENTICATION_SUCCESS'),
(11, 'user', '2018-06-21 02:44:20', 'AUTHENTICATION_SUCCESS'),
(12, 'user', '2018-06-21 02:49:02', 'AUTHENTICATION_SUCCESS'),
(13, 'admin', '2018-06-21 02:49:10', 'AUTHENTICATION_SUCCESS'),
(14, 'user', '2018-06-21 02:50:11', 'AUTHENTICATION_SUCCESS'),
(15, 'user', '2018-06-21 02:50:50', 'AUTHENTICATION_SUCCESS'),
(16, 'user', '2018-06-21 02:51:48', 'AUTHENTICATION_SUCCESS'),
(17, 'user', '2018-06-21 02:54:08', 'AUTHENTICATION_SUCCESS'),
(18, 'user', '2018-06-21 02:55:37', 'AUTHENTICATION_SUCCESS'),
(19, 'user', '2018-06-21 02:55:41', 'AUTHENTICATION_FAILURE'),
(20, 'user', '2018-06-21 02:58:41', 'AUTHENTICATION_SUCCESS'),
(21, 'user', '2018-06-21 02:58:48', 'AUTHENTICATION_FAILURE'),
(22, 'user', '2018-06-21 02:59:22', 'AUTHENTICATION_SUCCESS'),
(23, 'admin', '2018-06-21 02:59:45', 'AUTHENTICATION_SUCCESS'),
(24, 'admin', '2018-06-21 03:00:13', 'AUTHENTICATION_SUCCESS'),
(25, 'admin', '2018-06-21 03:01:19', 'AUTHENTICATION_SUCCESS'),
(26, 'admin', '2018-06-21 03:01:24', 'AUTHENTICATION_FAILURE'),
(27, 'admin', '2018-06-21 03:02:30', 'AUTHENTICATION_SUCCESS'),
(28, 'admin', '2018-06-21 03:03:01', 'AUTHENTICATION_SUCCESS'),
(29, 'null', '2018-06-21 03:03:03', 'AUTHENTICATION_FAILURE'),
(30, 'admin', '2018-06-21 03:09:29', 'AUTHENTICATION_SUCCESS'),
(31, 'admin', '2018-06-21 03:09:30', 'AUTHENTICATION_SUCCESS'),
(32, 'user', '2018-06-21 03:10:50', 'AUTHENTICATION_SUCCESS'),
(33, 'user', '2018-06-21 03:10:53', 'AUTHENTICATION_SUCCESS'),
(34, 'admin', '2018-06-21 03:10:58', 'AUTHENTICATION_SUCCESS'),
(35, 'user', '2018-06-21 03:16:06', 'AUTHENTICATION_SUCCESS'),
(36, 'admin', '2018-06-21 03:23:42', 'AUTHENTICATION_SUCCESS'),
(37, 'admin', '2018-06-21 03:38:45', 'AUTHENTICATION_SUCCESS'),
(38, 'user', '2018-06-21 03:47:10', 'AUTHENTICATION_SUCCESS'),
(39, 'admin', '2018-06-21 03:59:58', 'AUTHENTICATION_SUCCESS'),
(40, 'admin', '2018-06-21 04:08:45', 'AUTHENTICATION_SUCCESS'),
(41, 'admin', '2018-06-21 04:49:59', 'AUTHENTICATION_SUCCESS'),
(42, 'admin', '2018-06-21 15:32:56', 'AUTHENTICATION_SUCCESS'),
(43, 'admin', '2018-06-21 15:38:14', 'AUTHENTICATION_SUCCESS'),
(44, 'admin', '2018-06-21 15:42:36', 'AUTHENTICATION_SUCCESS'),
(45, 'admin', '2018-06-21 16:33:51', 'AUTHENTICATION_SUCCESS'),
(46, 'admin', '2018-06-21 18:13:08', 'AUTHENTICATION_SUCCESS'),
(47, 'admin', '2018-06-21 18:24:04', 'AUTHENTICATION_SUCCESS'),
(48, 'admin', '2018-06-21 18:55:18', 'AUTHENTICATION_SUCCESS'),
(49, 'admin', '2018-06-21 18:56:12', 'AUTHENTICATION_SUCCESS'),
(50, 'admin', '2018-06-21 19:09:01', 'AUTHENTICATION_SUCCESS'),
(51, 'user', '2018-06-21 22:37:42', 'AUTHENTICATION_SUCCESS'),
(52, 'user', '2018-06-21 22:40:39', 'AUTHENTICATION_SUCCESS'),
(53, 'admin', '2018-06-21 22:40:52', 'AUTHENTICATION_SUCCESS'),
(54, 'admin', '2018-06-21 22:46:16', 'AUTHENTICATION_SUCCESS'),
(55, 'admin', '2018-06-21 22:53:30', 'AUTHENTICATION_SUCCESS'),
(56, 'admin', '2018-06-21 23:02:01', 'AUTHENTICATION_SUCCESS'),
(57, 'admin', '2018-06-21 23:02:03', 'AUTHENTICATION_SUCCESS'),
(58, 'admin', '2018-06-21 23:02:06', 'AUTHENTICATION_SUCCESS'),
(59, 'admin', '2018-06-21 23:03:12', 'AUTHENTICATION_SUCCESS'),
(60, 'admin', '2018-06-21 23:13:27', 'AUTHENTICATION_SUCCESS');

-- --------------------------------------------------------

--
-- Структура таблицы `jhi_persistent_audit_evt_data`
--

CREATE TABLE `jhi_persistent_audit_evt_data` (
  `event_id` bigint(20) NOT NULL,
  `name` varchar(150) NOT NULL,
  `value` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `jhi_persistent_audit_evt_data`
--

INSERT INTO `jhi_persistent_audit_evt_data` (`event_id`, `name`, `value`) VALUES
(1, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(2, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(3, 'message', 'Bad credentials'),
(3, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(3, 'type', 'org.springframework.security.authentication.BadCredentialsException'),
(4, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(5, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(6, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(7, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(7, 'sessionId', 'OipiFgNMdJ-c99brRR24dSWJcw1GL_dLA0hcnNyc'),
(8, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(9, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(9, 'sessionId', '35RTrFHB-0aSrC-FVUU5WIuljgkGuA6_OkR2wcHr'),
(10, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(10, 'sessionId', 'IbYQBW5fSedpLohPZEKaAxNcmuQVan0hWXrkSzGs'),
(11, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(12, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(12, 'sessionId', 'g1aXUy7HP9j7RMqsezBCliu0DskxCBxzJOdatzUD'),
(13, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(13, 'sessionId', '5xK8ZEynEUmGOlzGn1P4W5CCa-PsjpgXn-qjYw22'),
(14, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(14, 'sessionId', 'y70ecD4WDwQbnxsjJVKXh3CE2RA8UQhSAwQTAmzy'),
(15, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(15, 'sessionId', '0yITBA7tJGq9cMLmZE2a6EFNIqCgp5YEeclc-eTg'),
(16, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(16, 'sessionId', 'VSMcEnFR7Zk89gKQRPOTZD9HxYciivdVG88q0T9R'),
(17, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(18, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(18, 'sessionId', 'VrKkLYGX4oQ3XyTqweZKbM2h-UOSab_4mfQ3hGmv'),
(19, 'message', 'Bad credentials'),
(19, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(19, 'sessionId', 'f-3XlYpbnNsO1CZGUTir-PFI1xlLRyQWDs2pk942'),
(19, 'type', 'org.springframework.security.authentication.BadCredentialsException'),
(20, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(21, 'message', 'Bad credentials'),
(21, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(21, 'sessionId', 'TNV7Vv-nCCMFSaRRm4Kg5P0ZnCraaeEqYCti5mjr'),
(21, 'type', 'org.springframework.security.authentication.BadCredentialsException'),
(22, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(23, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(24, 'remoteAddress', '127.0.0.1'),
(24, 'sessionId', 'GgvyK1f4pa38u5sYNffRJpgPdLyjW6509SjnCNmb'),
(25, 'remoteAddress', '127.0.0.1'),
(25, 'sessionId', 'ZkQ-jQ7XH0Hd9PX9uZjAzL-CE8Df4vsvNy2XpHX2'),
(26, 'message', 'Bad credentials'),
(26, 'remoteAddress', '127.0.0.1'),
(26, 'sessionId', '6nyecg2KwjbT9HzIzDQ2k3hZ8KDH1rGZGi8XYKTE'),
(26, 'type', 'org.springframework.security.authentication.BadCredentialsException'),
(27, 'remoteAddress', '127.0.0.1'),
(28, 'remoteAddress', '127.0.0.1'),
(28, 'sessionId', 'LVhuUdeWMNheXTmWjrvt8U2VRcIzOYB4mg1TI-GS'),
(29, 'message', 'Bad credentials'),
(29, 'remoteAddress', '127.0.0.1'),
(29, 'sessionId', 'rDLQLtb3daWQk9h0OLzCc7Fmbaijl-3EQj3-6bGj'),
(29, 'type', 'org.springframework.security.authentication.BadCredentialsException'),
(30, 'remoteAddress', '127.0.0.1'),
(31, 'remoteAddress', '127.0.0.1'),
(31, 'sessionId', '3xDLwfDwhbf3Vettg4WfgTI5NMOBtkdb9aN085NN'),
(32, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(32, 'sessionId', '85pHoSqXNYttpH_AnW_P0nMr4Z-R_Ezzk9bKJQG4'),
(33, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(33, 'sessionId', 'd-k0WyygIRDRDNTqSVKz7ytJCHfDg6h0G5z_HBJN'),
(34, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(34, 'sessionId', 'UqJelO-VPqOJuo_8dQv0yRvTjKQ8xIhCDFDbEReq'),
(35, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(36, 'remoteAddress', '127.0.0.1'),
(36, 'sessionId', 'Cer1Bj44l68BSk9aaAbPVZkCV9w0zkruvMBRCUj_'),
(37, 'remoteAddress', '127.0.0.1'),
(37, 'sessionId', '-3z91X37NLsFOnnkuHux_S9jxkz91oHMD-n2f2qC'),
(38, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(39, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(40, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(40, 'sessionId', 'NTV6JDyiJtCYhcJvhTjEafklEsAruI2-c3KMLhv6'),
(41, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(42, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(43, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(44, 'remoteAddress', '127.0.0.1'),
(44, 'sessionId', 'R6STdJVqCKgTPI5j_NLh5c3zK8SW8rQa9FrIlcQb'),
(45, 'remoteAddress', '127.0.0.1'),
(45, 'sessionId', 'hiZGIndgIArRah3gj0shWU-D8MFWD2_LDM6TQ90l'),
(46, 'remoteAddress', '127.0.0.1'),
(46, 'sessionId', 'B9sscJddN3I_0N0r6Wn-Kv1rRj3LhpPTjEHmIL1J'),
(47, 'remoteAddress', '127.0.0.1'),
(48, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(48, 'sessionId', 'XzKU6UqN1AVPyeWgVi1W1rVNr7eq6B4iyXSIdlCi'),
(49, 'remoteAddress', '127.0.0.1'),
(49, 'sessionId', '4D8q4gsxPRk2anUwAUXHn9FM8HoCbzk1eIOBNWz-'),
(50, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(51, 'remoteAddress', '127.0.0.1'),
(52, 'remoteAddress', '127.0.0.1'),
(52, 'sessionId', '7Cu6zJULArfpFBi8LbTjcChI5R73tK5kAQIZSlfl'),
(53, 'remoteAddress', '127.0.0.1'),
(54, 'remoteAddress', '0:0:0:0:0:0:0:1'),
(55, 'remoteAddress', '127.0.0.1'),
(55, 'sessionId', 'RI-1v9KzdXOVc7zQFKAzAHvNLyGT_rX0kRdGF9Wf'),
(56, 'remoteAddress', '127.0.0.1'),
(56, 'sessionId', 'uVhrJN3Q_0irLFxtSYDv2P5zHugJbp_TwA22MgrV'),
(57, 'remoteAddress', '127.0.0.1'),
(57, 'sessionId', 'JOL9tswTpexyuAeblJ-myyCeQ3ctHY9pwGE3fbgx'),
(58, 'remoteAddress', '127.0.0.1'),
(58, 'sessionId', 'oLjezvESc4DI4CV3L8VvJU5Z9HjvyRRdRpFydERZ'),
(59, 'remoteAddress', '127.0.0.1'),
(59, 'sessionId', 'Zddl-Sx8b02n_ZqdcOjlh0XhP_v2VmlZNQf4QxZO'),
(60, 'remoteAddress', '127.0.0.1'),
(60, 'sessionId', 'oD2gE_zlabLNEBnFfvvnfryyq9PNMzu3XsigrHSy');

-- --------------------------------------------------------

--
-- Структура таблицы `jhi_persistent_token`
--

CREATE TABLE `jhi_persistent_token` (
  `series` varchar(20) NOT NULL,
  `user_id` bigint(20) DEFAULT NULL,
  `token_value` varchar(20) NOT NULL,
  `token_date` date DEFAULT NULL,
  `ip_address` varchar(39) DEFAULT NULL,
  `user_agent` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `jhi_persistent_token`
--

INSERT INTO `jhi_persistent_token` (`series`, `user_id`, `token_value`, `token_date`, `ip_address`, `user_agent`) VALUES
('0bxvxB3a2VvCCIqpbO0b', 3, 'klci4I0uJVFpyclDSUyM', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('0MP1Khk9eEWnJJcDOfKP', 3, 'HoqlomkppYci8JrjUGi6', '2018-06-19', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('11HBpY6EIodVMnGFBvoF', 3, 'y5p2XpZ6k4w1PUugxNwR', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('1CYoNNsJ3BOfuC4YdPkQ', 3, 'zlA8YgwVIodZV2y1RuN6', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('2Ev1uBQxP2OCfwC5VkXQ', 4, 'mp78eggj0oeDEmRdhuSI', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('2oj95f5wPwoXc5iVeOVW', 3, 'P942lkGOzPqqjr9XMlf5', '2018-06-22', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('3JMKaYtxnxyY4Ajm4gLu', 3, '9H5jWYnwpckWlxQSLbl9', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('3UhfPg6RrC0QaCnTnYNR', 3, 'jNcDCPbZ47hkYiVV7XX7', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('8SAU5bwIycrAJ1KLwfXM', 3, 'UJkp0cocDlSVKzy4NTux', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('aYTBYCagUacrFPDJ2V7T', 4, 'CNIAF5jCLruaTVzTA0Sp', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('bCDYq26gnPGoVcaZvYCp', 3, '8Tx6xR52cuZY0SvHPD72', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('bGll9DFRI3QHX8zvxYod', 3, 'd4p5IgJj7C63LdC5dUoF', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('Czq2zczAvveQxfmHnfTj', 3, 'QRRftPef3OKxyhaLZCu0', '2018-06-20', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('fGMXQxqXxYYzMu8GBFRW', 3, 'IzHbosMqsogpBWAnzOEw', '2018-06-20', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('fPQ2xNaTtoW2jdpp8CvB', 3, '3iY1QulwGZejDpuPa4Tu', '2018-06-22', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('GPGr2jPevnyVcr4oOa85', 4, 'Z652sKfLf8ufVoIcnUXI', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('HPYa2fjUXIg1gZIAxw55', 3, 'Ni2WslvuBabojTSCezXm', '2018-06-20', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('HwEFM2iAQOXgGd1gHdU8', 3, 'WFhM8LA5BpBOREYlS2U8', '2018-06-22', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('hZNHImKu1sTZrT0XmTwj', 3, 'aBNsykLIQN7dOjUiIfgw', '2018-06-22', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('i8PpxjvJHEZdr9jUaaAE', 4, '4v24IjKF2Xz79whYFoFl', '2018-06-22', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('jkcHQZ5icQCc4BtqGWAR', 3, 'WkdnUzpkltNnd8CyCudu', '2018-06-22', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('JOt87uQ19P6qM9uBtcYa', 3, 'zLVJ9xt1ysAY5C1FbDcx', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('kjbZwsjiS2DPC7sAGGYP', 4, 'Vzxjwtrq6niyQAzSXfAw', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('klRVrNSQ11Zizd7KqmdY', 3, 'fqNyqQjjlWdEqSUvVBKI', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('kQSwbMzwvQp9nJR9P2J2', 3, 'nE4zUxwgHq2oZ9WRdC4x', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('ku0uBXKp6glnSsO8iy8s', 4, 'ZgS6BKfjI7XftdCPJSRm', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('LdJNFxm91rXRJ68rXDtX', 4, 'O2tAsmLVKaLh23xt2YKd', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('mFTR5NW6EAZgLUsWDPFb', 3, 'T3HVeCnD5yLdXUwnaKec', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('mXY2NbvH5eI93g9yKaaB', 3, 'tG5uaw6kLuGbZnsayAfC', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('N8fE4ExRwQudsIeTLQIA', 4, 's2iYlDhirHGWJeDAR0qj', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('OcguC806stpydorjGADD', 3, '9Be6Xlx9oQqz7iZYMUd2', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('OdPKHiZoveJ0JFtLp5W1', 3, 'hZlsJQmX48w3pDb39mLl', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('OnYagu6GrY5AwfhNiTui', 3, 'qLO9zlivoP8weQpzWzZy', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('pA0y4GXpH80mJcOJVdcJ', 3, 'FfpDKpJhGvxPTkpThp6y', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('s96rgYZCKVMWkUnDFcW5', 3, 'bO2xx3JEIhBFF3PHs3Cc', '2018-06-21', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('secHd0rwBnhbGm82Rp7n', 3, 'ZbKxkhV0fBRVi5VrHBmn', '2018-06-19', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('sgBkzHOMAnEbV3E3z3BI', 3, 'gwiAnoUxY8LbuIwIEbVK', '2018-06-22', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('SsSHuMdJXiWxlqx8iAHd', 4, '93Y5TYxtAOBE5repCBV4', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('TKfhERjyAHs67tRYi9NK', 4, 'WKxWD06kPvPAuUpW7Fba', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('UB8f0x2S15OLfY0ItoZK', 3, 'x2UFaUITKkbZGhT2oVvc', '2018-06-22', '127.0.0.1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('vyIMRp8AI4S3M5qlH9s9', 4, 'zPq1OLEbN6s7qZu9wKOD', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('wvalKHebSYNgBvLpTaxe', 3, '5KFeIeltETXFCauzFHYb', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('X9wKJvqQL3GQOdk8F8BG', 3, 'DxpeuA6nFHBSF1jV3WiV', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('xd0wRe3zpqP856uK2CUB', 3, 'DKCeQA1orJL0WfTYWhVc', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('YCi0cCS6P271WvY7us2v', 4, 'kVgFydQLM7ONldUmL1QW', '2018-06-21', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36'),
('zWvNm8YmZSiKPqiuXyJP', 3, 'qdV0mpLyR9NAZQlxflEC', '2018-06-19', '0:0:0:0:0:0:0:1', 'Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/67.0.3396.87 Safari/537.36');

-- --------------------------------------------------------

--
-- Структура таблицы `jhi_user`
--

CREATE TABLE `jhi_user` (
  `id` bigint(20) NOT NULL,
  `login` varchar(50) NOT NULL,
  `password_hash` varchar(60) DEFAULT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `image_url` varchar(256) DEFAULT NULL,
  `activated` bit(1) NOT NULL,
  `lang_key` varchar(6) DEFAULT NULL,
  `activation_key` varchar(20) DEFAULT NULL,
  `reset_key` varchar(20) DEFAULT NULL,
  `created_by` varchar(50) NOT NULL,
  `created_date` timestamp NOT NULL,
  `reset_date` timestamp NULL DEFAULT NULL,
  `last_modified_by` varchar(50) DEFAULT NULL,
  `last_modified_date` timestamp NULL DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `jhi_user`
--

INSERT INTO `jhi_user` (`id`, `login`, `password_hash`, `first_name`, `last_name`, `email`, `image_url`, `activated`, `lang_key`, `activation_key`, `reset_key`, `created_by`, `created_date`, `reset_date`, `last_modified_by`, `last_modified_date`) VALUES
(1, 'system', '$2a$10$mE.qmcV0mFU5NcKh73TZx.z4ueI/.bDWbj0T1BYyqP481kGGarKLG', 'System', 'System', 'system@localhost', '', b'1', 'en', NULL, NULL, 'system', '2018-06-19 12:18:43', NULL, 'system', NULL),
(2, 'anonymoususer', '$2a$10$j8S5d7Sr7.8VTOYNviDPOeWX8KcYILUVJBsYV83Y5NtECayypx9lO', 'Anonymous', 'User', 'anonymous@localhost', '', b'1', 'en', NULL, NULL, 'system', '2018-06-19 12:18:43', NULL, 'system', NULL),
(3, 'admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator', 'admin@localhost', '', b'1', 'en', NULL, NULL, 'system', '2018-06-19 12:18:43', NULL, 'system', NULL),
(4, 'user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost', '', b'1', 'en', NULL, NULL, 'system', '2018-06-19 12:18:43', NULL, 'system', NULL);

-- --------------------------------------------------------

--
-- Структура таблицы `jhi_user_authority`
--

CREATE TABLE `jhi_user_authority` (
  `user_id` bigint(20) NOT NULL,
  `authority_name` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `jhi_user_authority`
--

INSERT INTO `jhi_user_authority` (`user_id`, `authority_name`) VALUES
(1, 'ROLE_ADMIN'),
(3, 'ROLE_ADMIN'),
(1, 'ROLE_USER'),
(3, 'ROLE_USER'),
(4, 'ROLE_USER');

-- --------------------------------------------------------

--
-- Структура таблицы `student`
--

CREATE TABLE `student` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `idstudentbook` bigint(20) DEFAULT NULL,
  `avaragemark` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `student`
--

INSERT INTO `student` (`id`, `name`, `address`, `phone`, `email`, `idstudentbook`, `avaragemark`) VALUES
(1, 'Алекс', 'Москва', '9788649194', 'dzhemaletdinov.a.i14@gmail.com', 1234, 4.54),
(2, 'Juluy', 'New York', '1238302304', 'test1234@gmail.com', 231123, 4.52),
(3, 'John', 'Крымская, 55', '9788649194', 'dzhemaletdinov.a.i14@gmail.com', 3, 4),
(5, 'Jim', 'Oslo', '39102312', 'test1234@gmail.com', 1234, 3.9);

-- --------------------------------------------------------

--
-- Структура таблицы `student_course`
--

CREATE TABLE `student_course` (
  `courses_id` bigint(20) NOT NULL,
  `students_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `student_course`
--

INSERT INTO `student_course` (`courses_id`, `students_id`) VALUES
(1, 1),
(1, 3),
(3, 2),
(3, 3),
(4, 2),
(4, 3);

-- --------------------------------------------------------

--
-- Структура таблицы `subject`
--

CREATE TABLE `subject` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `rating` int(11) DEFAULT NULL,
  `course_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `subject`
--

INSERT INTO `subject` (`id`, `name`, `rating`, `course_id`) VALUES
(1, 'OOP', NULL, 1),
(2, 'Multithreading', NULL, 1);

-- --------------------------------------------------------

--
-- Структура таблицы `teacher`
--

CREATE TABLE `teacher` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `phonenumber` varchar(255) DEFAULT NULL,
  `salary` float DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `teacher`
--

INSERT INTO `teacher` (`id`, `name`, `address`, `phonenumber`, `salary`) VALUES
(1, 'Ivan Ivanov', 'Simferopol', '7978643123', 15000),
(2, 'Danil Danilov', 'Sevastopol', '892312311', 35000),
(3, 'Arsen', 'Moskow', '12312', 123);

-- --------------------------------------------------------

--
-- Структура таблицы `teacher_course`
--

CREATE TABLE `teacher_course` (
  `courses_id` bigint(20) NOT NULL,
  `teachers_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

--
-- Дамп данных таблицы `teacher_course`
--

INSERT INTO `teacher_course` (`courses_id`, `teachers_id`) VALUES
(1, 1),
(2, 2);

--
-- Индексы сохранённых таблиц
--

--
-- Индексы таблицы `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `DATABASECHANGELOGLOCK`
--
ALTER TABLE `DATABASECHANGELOGLOCK`
  ADD PRIMARY KEY (`ID`);

--
-- Индексы таблицы `jhi_authority`
--
ALTER TABLE `jhi_authority`
  ADD PRIMARY KEY (`name`);

--
-- Индексы таблицы `jhi_persistent_audit_event`
--
ALTER TABLE `jhi_persistent_audit_event`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `idx_persistent_audit_event` (`principal`,`event_date`);

--
-- Индексы таблицы `jhi_persistent_audit_evt_data`
--
ALTER TABLE `jhi_persistent_audit_evt_data`
  ADD PRIMARY KEY (`event_id`,`name`),
  ADD KEY `idx_persistent_audit_evt_data` (`event_id`);

--
-- Индексы таблицы `jhi_persistent_token`
--
ALTER TABLE `jhi_persistent_token`
  ADD PRIMARY KEY (`series`),
  ADD KEY `fk_user_persistent_token` (`user_id`);

--
-- Индексы таблицы `jhi_user`
--
ALTER TABLE `jhi_user`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `ux_user_login` (`login`),
  ADD UNIQUE KEY `idx_user_login` (`login`),
  ADD UNIQUE KEY `ux_user_email` (`email`),
  ADD UNIQUE KEY `idx_user_email` (`email`);

--
-- Индексы таблицы `jhi_user_authority`
--
ALTER TABLE `jhi_user_authority`
  ADD PRIMARY KEY (`user_id`,`authority_name`),
  ADD KEY `fk_authority_name` (`authority_name`);

--
-- Индексы таблицы `student`
--
ALTER TABLE `student`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `student_course`
--
ALTER TABLE `student_course`
  ADD PRIMARY KEY (`students_id`,`courses_id`),
  ADD KEY `fk_student_course_courses_id` (`courses_id`);

--
-- Индексы таблицы `subject`
--
ALTER TABLE `subject`
  ADD PRIMARY KEY (`id`),
  ADD KEY `fk_subject_course_id` (`course_id`);

--
-- Индексы таблицы `teacher`
--
ALTER TABLE `teacher`
  ADD PRIMARY KEY (`id`);

--
-- Индексы таблицы `teacher_course`
--
ALTER TABLE `teacher_course`
  ADD PRIMARY KEY (`teachers_id`,`courses_id`),
  ADD KEY `fk_teacher_course_courses_id` (`courses_id`);

--
-- AUTO_INCREMENT для сохранённых таблиц
--

--
-- AUTO_INCREMENT для таблицы `course`
--
ALTER TABLE `course`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT для таблицы `jhi_persistent_audit_event`
--
ALTER TABLE `jhi_persistent_audit_event`
  MODIFY `event_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;
--
-- AUTO_INCREMENT для таблицы `jhi_user`
--
ALTER TABLE `jhi_user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;
--
-- AUTO_INCREMENT для таблицы `student`
--
ALTER TABLE `student`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;
--
-- AUTO_INCREMENT для таблицы `subject`
--
ALTER TABLE `subject`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT для таблицы `teacher`
--
ALTER TABLE `teacher`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;
--
-- Ограничения внешнего ключа сохраненных таблиц
--

--
-- Ограничения внешнего ключа таблицы `jhi_persistent_audit_evt_data`
--
ALTER TABLE `jhi_persistent_audit_evt_data`
  ADD CONSTRAINT `fk_evt_pers_audit_evt_data` FOREIGN KEY (`event_id`) REFERENCES `jhi_persistent_audit_event` (`event_id`);

--
-- Ограничения внешнего ключа таблицы `jhi_persistent_token`
--
ALTER TABLE `jhi_persistent_token`
  ADD CONSTRAINT `fk_user_persistent_token` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`);

--
-- Ограничения внешнего ключа таблицы `jhi_user_authority`
--
ALTER TABLE `jhi_user_authority`
  ADD CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `jhi_user` (`id`),
  ADD CONSTRAINT `fk_authority_name` FOREIGN KEY (`authority_name`) REFERENCES `jhi_authority` (`name`);

--
-- Ограничения внешнего ключа таблицы `student_course`
--
ALTER TABLE `student_course`
  ADD CONSTRAINT `fk_student_course_courses_id` FOREIGN KEY (`courses_id`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `fk_student_course_students_id` FOREIGN KEY (`students_id`) REFERENCES `student` (`id`);

--
-- Ограничения внешнего ключа таблицы `subject`
--
ALTER TABLE `subject`
  ADD CONSTRAINT `fk_subject_course_id` FOREIGN KEY (`course_id`) REFERENCES `course` (`id`);

--
-- Ограничения внешнего ключа таблицы `teacher_course`
--
ALTER TABLE `teacher_course`
  ADD CONSTRAINT `fk_teacher_course_courses_id` FOREIGN KEY (`courses_id`) REFERENCES `course` (`id`),
  ADD CONSTRAINT `fk_teacher_course_teachers_id` FOREIGN KEY (`teachers_id`) REFERENCES `teacher` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
