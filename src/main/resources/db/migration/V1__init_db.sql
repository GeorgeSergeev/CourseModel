CREATE TABLE IF NOT EXISTS students (
    id                  SERIAL PRIMARY KEY,
    version             INT4 NOT NULL DEFAULT(0),
    name                VARCHAR NOT NULL,
    address             VARCHAR,
    phone               VARCHAR NOT NULL,
    email               VARCHAR NOT NULL,
    record_book_number  INT4 NOT NULL,
    avg_performance     REAL
);

CREATE TABLE IF NOT EXISTS courses (
    id                  SERIAL PRIMARY KEY,
    version             INT4 NOT NULL DEFAULT(0),
    title               VARCHAR NOT NULL,
    number              INT4 NOT NULL,
    price               REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS professors (
    id                  SERIAL PRIMARY KEY,
    version             INT4 NOT NULL DEFAULT(0),
    name                VARCHAR NOT NULL,
    address             VARCHAR,
    phone               VARCHAR,
    payment             REAL NOT NULL,
    course_id           INT4
);

CREATE TABLE IF NOT EXISTS students_courses (
    id                  SERIAL PRIMARY KEY,
    student_id          INT4 NOT NULL,
    course_id           INT4 NOT NULL,
    final_mark          REAL
);

CREATE TABLE IF NOT EXISTS completing_courses (
    id                  SERIAL PRIMARY KEY,
    version             INT4 NOT NULL DEFAULT(0),
    student_id          INT4 NOT NULL,
    course_id           INT4 NOT NULL,
    mark                INT4 NOT NULL
);

ALTER TABLE students            ADD CONSTRAINT student_phone_unique         UNIQUE (phone);
ALTER TABLE students            ADD CONSTRAINT email_unique                 UNIQUE (email);
ALTER TABLE students            ADD CONSTRAINT record_book_number_unique    UNIQUE (record_book_number);
ALTER TABLE courses             ADD CONSTRAINT number_unique                UNIQUE (number);
ALTER TABLE professors          ADD CONSTRAINT professor_phone_unique       UNIQUE (phone);
ALTER TABLE students_courses    ADD CONSTRAINT students_courses_unique      UNIQUE (student_id, course_id);
