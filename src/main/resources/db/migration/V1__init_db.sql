CREATE TABLE IF NOT EXISTS students (
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR NOT NULL,
    address             VARCHAR,
    phone               VARCHAR NOT NULL,
    email               VARCHAR NOT NULL,
    record_book_number  INT4,
    avg_performance     FLOAT,
    course_completing   int4,
    version             INT4 NOT NULL DEFAULT(0)
);

CREATE TABLE IF NOT EXISTS professors (
    id                  SERIAL PRIMARY KEY,
    name                VARCHAR NOT NULL,
    address             VARCHAR,
    phone               VARCHAR NOT NULL,
    payment             FLOAT NOT NULL,
    course_id           INT4 NOT NULL,
    version             INT4 NOT NULL DEFAULT(0)
);

CREATE TABLE IF NOT EXISTS courses (
    id                  SERIAL PRIMARY KEY,
    title               VARCHAR NOT NULL,
    number              INT4 NOT NULL,
    price               FLOAT NOT NULL,
    version             INT4 NOT NULL DEFAULT(0)
);

CREATE TABLE IF NOT EXISTS students_courses (
    student_id          INT4 NOT NULL,
    course_id           INT4 NOT NULL
);

CREATE TABLE IF NOT EXISTS marks (
    student_id          INT4 NOT NULL,
    course_id           INT4 NOT NULL,
    mark                INT4 NOT NULL
);
