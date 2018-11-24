CREATE SCHEMA `course_model` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

USE `course_model`;

CREATE TABLE `students` (
  `student_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin NOT NULL,
  `address` varchar(255) COLLATE utf8_bin NOT NULL,
  `phone` varchar(16) COLLATE utf8_bin NOT NULL,
  `email` varchar(60) COLLATE utf8_bin NOT NULL,
  `grade_book_num` int(11) NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE KEY `grade_book_num_UNIQUE` (`grade_book_num`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `teachers` (
  `teacher_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(128) COLLATE utf8_bin NOT NULL,
  `address` varchar(255) COLLATE utf8_bin NOT NULL,
  `phone` varchar(16) COLLATE utf8_bin NOT NULL,
  `salary` float NOT NULL,
  PRIMARY KEY (`teacher_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `courses_groups` (
  `course` int(11) NOT NULL,
  `student` int(11) NOT NULL,
  `status` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`course`,`student`),
  KEY `student_idx` (`student`),
  KEY `course_idx` (`course`),
  CONSTRAINT `course` FOREIGN KEY (`course`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student` FOREIGN KEY (`student`) REFERENCES `students` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `courses` (
  `course_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) COLLATE utf8_bin NOT NULL,
  `cost` float NOT NULL,
  `course_teacher` int(11) DEFAULT NULL,
  PRIMARY KEY (`course_id`),
  KEY `teacher_idx` (`course_teacher`),
  CONSTRAINT `teacher` FOREIGN KEY (`course_teacher`) REFERENCES `teachers` (`teacher_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

CREATE TABLE `course_scores` (
  `score_id` int(11) NOT NULL AUTO_INCREMENT,
  `student` int(11) NOT NULL,
  `course` int(11) NOT NULL,
  `score` int(11) NOT NULL,
  PRIMARY KEY (`score_id`),
  KEY `student_idx` (`student`),
  KEY `course_idx` (`course`),
  CONSTRAINT `course_id` FOREIGN KEY (`course`) REFERENCES `courses` (`course_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `student_id` FOREIGN KEY (`student`) REFERENCES `students` (`student_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;
