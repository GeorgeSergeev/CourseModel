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
  sale         FLOAT(5) COMMENT 'Стоимость',
);

COMMENT ON TABLE Kurs IS 'Курс';

CREATE TABLE IF NOT EXISTS Student
(
  id      INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  name    VARCHAR(50) NOT NULL COMMENT 'Имя',
  address VARCHAR(50) COMMENT 'Адрес',
  phone   VARCHAR(50) COMMENT 'Телефон',
  mail    VARCHAR(50) COMMENT 'электронный адрес',
  number_doc    VARCHAR(50) COMMENT 'номер зачетки',
  middle_progress    VARCHAR(50) COMMENT 'электронный адрес',
  is_active BOOLEAN COMMENT 'возможность записи',
  kurs_end VARCHAR(50) COMMENT  'прослушаный курс'
);

COMMENT ON TABLE Student IS 'Студент';

CREATE TABLE IF NOT EXISTS Pr_kurs
(
  id         INTEGER COMMENT 'Уникальный идентификатор' PRIMARY KEY AUTO_INCREMENT,
  ocenka     VARCHAR(50) COMMENT 'оценка',
  kurs_id    INTEGER COMMENT 'Связь с таблицей Курс',
  final_ocenka     VARCHAR(50) COMMENT 'финальная оценка',
  FOREIGN KEY (kurs_id) REFERENCES Kurs (id),
  student_id INTEGER COMMENT 'Связь с таблицей Студент',
  FOREIGN KEY (student_id) REFERENCES Student (id)
);

COMMENT ON TABLE Pr_kurs IS 'Прохождение курса';



CREATE INDEX I_Kurs ON Pr_kurs (kurs_id);
CREATE INDEX II_Student ON Pr_kurs (student_id);
CREATE INDEX III_Professor ON Kurs (professor_id);

