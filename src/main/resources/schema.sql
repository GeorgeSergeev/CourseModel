CREATE TABLE IF NOT EXISTS Professor
(
  id      INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  name    VARCHAR(50) NOT NULL COMMENT 'Имя',
  address VARCHAR(50) COMMENT 'Адрес',
  phone   VARCHAR(50) COMMENT 'Телефон',
  sale    FLOAT(5) COMMENT 'Оплата'
);

COMMENT ON TABLE Professor IS 'Профессор';

CREATE TABLE IF NOT EXISTS Kurs
(
  id           INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  professor_id INTEGER COMMENT 'Связь с таблицей Профессор',
  FOREIGN KEY (professor_id) REFERENCES Professor (id),
  name         VARCHAR(50) NOT NULL COMMENT 'Наименование',
  number       INTEGER(50) COMMENT 'Номер',
  sale         FLOAT(5) COMMENT 'Оплата'
);

COMMENT ON TABLE Kurs IS 'Курс';

CREATE TABLE IF NOT EXISTS Student
(
  id      INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  name    VARCHAR(50) NOT NULL COMMENT 'Имя',
  address VARCHAR(50) COMMENT 'Адрес',
  phone   VARCHAR(50) COMMENT 'Телефон',
  mail    VARCHAR(50) COMMENT 'электронный адрес',
);

COMMENT ON TABLE Student IS 'Студент';

CREATE TABLE IF NOT EXISTS Pr_kurs
(
  id         INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  ocenka     VARCHAR(50) COMMENT 'оценка',
  kurs_id    INTEGER COMMENT 'Связь с таблицей Курс',
  FOREIGN KEY (kurs_id) REFERENCES Kurs (id),
  student_id INTEGER COMMENT 'Связь с таблицей Студент',
  FOREIGN KEY (student_id) REFERENCES Student (id)
);

COMMENT ON TABLE Pr_kurs IS 'Прохождение курса';



CREATE INDEX I_Kurs ON Pr_kurs (kurs_id);
CREATE INDEX II_Student ON Pr_kurs (student_id);
CREATE INDEX III_Professor ON Kurs (professor_id);
