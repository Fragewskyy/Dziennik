-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema dziennik
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema dziennik
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `dziennik` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `dziennik` ;

-- -----------------------------------------------------
-- Table `dziennik`.`role`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`role` (
  `role_id` INT(11) NOT NULL AUTO_INCREMENT,
  `role_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`users`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`users` (
  `user_id` INT(11) NOT NULL AUTO_INCREMENT,
  `login` VARCHAR(45) NOT NULL,
  `password` VARCHAR(45) NOT NULL,
  `name` VARCHAR(45) NOT NULL,
  `surname` VARCHAR(45) NOT NULL,
  `role_id` INT(11) NULL DEFAULT NULL,
  PRIMARY KEY (`user_id`),
  INDEX `role_id_idx` (`role_id` ASC),
  CONSTRAINT `role_id`
    FOREIGN KEY (`role_id`)
    REFERENCES `dziennik`.`role` (`role_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 86
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`teacher`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`teacher` (
  `teacher_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`teacher_id`),
  INDEX `user_id2_idx` (`user_id` ASC),
  CONSTRAINT `user_id2`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`users` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`classes`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`classes` (
  `class_id` INT(11) NOT NULL AUTO_INCREMENT,
  `class_name` VARCHAR(45) NOT NULL,
  `teacher_id` INT(11) NOT NULL,
  `students_amount` INT(11) NOT NULL,
  PRIMARY KEY (`class_id`),
  INDEX `teacher_id_idx` (`teacher_id` ASC),
  CONSTRAINT `teacher_id`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `dziennik`.`teacher` (`teacher_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`subjects`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`subjects` (
  `subject_id` INT(11) NOT NULL AUTO_INCREMENT,
  `subject_name` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`subject_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`lessons`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`lessons` (
  `lesson_id` INT(11) NOT NULL AUTO_INCREMENT,
  `class_id` INT(11) NOT NULL,
  `teacher_id` INT(11) NOT NULL,
  `date` DATETIME NOT NULL,
  `subject_id` INT(11) NOT NULL,
  PRIMARY KEY (`lesson_id`),
  INDEX `class_id_idx` (`class_id` ASC),
  INDEX `teacher_id_idx` (`teacher_id` ASC),
  INDEX `subject_id_idx` (`subject_id` ASC),
  CONSTRAINT `class_id1`
    FOREIGN KEY (`class_id`)
    REFERENCES `dziennik`.`classes` (`class_id`),
  CONSTRAINT `subject_id1`
    FOREIGN KEY (`subject_id`)
    REFERENCES `dziennik`.`subjects` (`subject_id`),
  CONSTRAINT `teacher_id2`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `dziennik`.`teacher` (`teacher_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`student`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`student` (
  `student_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  `class_id` INT(11) NOT NULL,
  `teacher_id` INT(11) NOT NULL,
  `phone_number` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`student_id`),
  INDEX `user_id4_idx` (`user_id` ASC),
  INDEX `teacher_id_idx` (`teacher_id` ASC),
  INDEX `class_id_idx` (`class_id` ASC),
  CONSTRAINT `class_id`
    FOREIGN KEY (`class_id`)
    REFERENCES `dziennik`.`classes` (`class_id`),
  CONSTRAINT `teacher_id1`
    FOREIGN KEY (`teacher_id`)
    REFERENCES `dziennik`.`teacher` (`teacher_id`),
  CONSTRAINT `user_id4`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`users` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 18
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`absences`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`absences` (
  `absence_id` INT(11) NOT NULL AUTO_INCREMENT,
  `lesson_id` INT(11) NOT NULL,
  `student_id` INT(11) NOT NULL,
  `is_on_lesson` BIT(1) NOT NULL,
  PRIMARY KEY (`absence_id`),
  INDEX `lesson_id_idx` (`lesson_id` ASC),
  INDEX `student_id_idx` (`student_id` ASC),
  CONSTRAINT `lesson_id`
    FOREIGN KEY (`lesson_id`)
    REFERENCES `dziennik`.`lessons` (`lesson_id`),
  CONSTRAINT `student_id2`
    FOREIGN KEY (`student_id`)
    REFERENCES `dziennik`.`student` (`student_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`admin`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`admin` (
  `admin_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`admin_id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `user_id`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`users` (`user_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 2
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`grades`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`grades` (
  `grade_id` INT(11) NOT NULL AUTO_INCREMENT,
  `grade` INT(11) NOT NULL,
  `student_id` INT(11) NOT NULL,
  `subject_id` INT(11) NOT NULL,
  PRIMARY KEY (`grade_id`),
  INDEX `subject_id_idx` (`subject_id` ASC),
  INDEX `student_id_idx` (`student_id` ASC),
  CONSTRAINT `student_id`
    FOREIGN KEY (`student_id`)
    REFERENCES `dziennik`.`student` (`student_id`),
  CONSTRAINT `subject_id`
    FOREIGN KEY (`subject_id`)
    REFERENCES `dziennik`.`subjects` (`subject_id`))
ENGINE = InnoDB
AUTO_INCREMENT = 303
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`guardian`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`guardian` (
  `guardian_id` INT(11) NOT NULL AUTO_INCREMENT,
  `phonenumber` VARCHAR(45) NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`guardian_id`),
  INDEX `user_id3_idx` (`user_id` ASC),
  CONSTRAINT `user_id3`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`lessonperyear`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`lessonperyear` (
  `lessonPerYearId` INT(11) NOT NULL AUTO_INCREMENT,
  `class_id` INT(11) NOT NULL,
  `subject_id` INT(11) NOT NULL,
  `amountOfLessons` INT(11) NOT NULL,
  PRIMARY KEY (`lessonPerYearId`),
  INDEX `classid_idx` (`class_id` ASC),
  INDEX `subjectid_idx` (`subject_id` ASC),
  CONSTRAINT `classid`
    FOREIGN KEY (`class_id`)
    REFERENCES `dziennik`.`classes` (`class_id`),
  CONSTRAINT `subjectid`
    FOREIGN KEY (`subject_id`)
    REFERENCES `dziennik`.`subjects` (`subject_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`messages`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`messages` (
  `message_id` INT(11) NOT NULL AUTO_INCREMENT,
  `message_subject` LONGTEXT NOT NULL,
  `message_text` LONGTEXT NOT NULL,
  `date` DATETIME NOT NULL,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`message_id`),
  INDEX `user_id7_idx` (`user_id` ASC),
  CONSTRAINT `user_id7`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


-- -----------------------------------------------------
-- Table `dziennik`.`principal`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `dziennik`.`principal` (
  `principal_id` INT(11) NOT NULL AUTO_INCREMENT,
  `user_id` INT(11) NOT NULL,
  PRIMARY KEY (`principal_id`),
  INDEX `user_id_idx` (`user_id` ASC),
  CONSTRAINT `user_id1`
    FOREIGN KEY (`user_id`)
    REFERENCES `dziennik`.`users` (`user_id`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb4
COLLATE = utf8mb4_0900_ai_ci;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
