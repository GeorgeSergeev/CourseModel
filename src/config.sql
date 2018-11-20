CREATE SCHEMA `course_model` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin ;

CREATE TABLE `course_model`.`students` (
  `student_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(16) NOT NULL,
  `email` VARCHAR(60) NOT NULL,
  `grade_book_num` INT NOT NULL,
  PRIMARY KEY (`student_id`),
  UNIQUE INDEX `grade_book_num_UNIQUE` (`grade_book_num` ASC) VISIBLE);

CREATE TABLE `course_model`.`teachers` (
  `teacher_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(128) NOT NULL,
  `address` VARCHAR(255) NOT NULL,
  `phone` VARCHAR(16) NOT NULL,
  `salary` FLOAT NOT NULL,
  PRIMARY KEY (`teacher_id`));

CREATE TABLE `course_model`.`courses` (
  `course_id` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(255) NOT NULL,
  `cost` FLOAT NOT NULL,
  `course_teacher` INT NULL,
  PRIMARY KEY (`course_id`),
  INDEX `teacher_idx` (`course_teacher` ASC) VISIBLE,
  CONSTRAINT `teacher`
    FOREIGN KEY (`course_teacher`)
    REFERENCES `course_model`.`teachers` (`teacher_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `course_model`.`courses_groups` (
  `course` INT NOT NULL,
  `student` INT NOT NULL,
  INDEX `student_idx` (`student` ASC) VISIBLE,
  INDEX `course_idx` (`course` ASC) VISIBLE,
  PRIMARY KEY (`course`, `student`),
  CONSTRAINT `student`
    FOREIGN KEY (`student`)
    REFERENCES `course_model`.`students` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `course`
    FOREIGN KEY (`course`)
    REFERENCES `course_model`.`courses` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);

CREATE TABLE `course_model`.`course_scores` (
  `score_id` INT NOT NULL AUTO_INCREMENT,
  `student` INT NOT NULL,
  `course` INT NOT NULL,
  `score` INT NOT NULL,
  PRIMARY KEY (`score_id`),
  INDEX `student_idx` (`student` ASC) VISIBLE,
  INDEX `course_idx` (`course` ASC) VISIBLE,
  CONSTRAINT `student_id`
    FOREIGN KEY (`student`)
    REFERENCES `course_model`.`students` (`student_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE,
  CONSTRAINT `course_id`
    FOREIGN KEY (`course`)
    REFERENCES `course_model`.`courses` (`course_id`)
    ON DELETE CASCADE
    ON UPDATE CASCADE);
