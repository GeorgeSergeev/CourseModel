CREATE TABLE IF NOT EXISTS students (
    id                  SERIAL PRIMARY KEY,
    version             INT4 NOT NULL DEFAULT(0),
    name                VARCHAR NOT NULL,
    address             VARCHAR,
    phone               VARCHAR NOT NULL,
    email               VARCHAR NOT NULL,
    record_book_number  INT4,
    avg_performance     REAL
);

CREATE TABLE IF NOT EXISTS professors (
    id                  SERIAL PRIMARY KEY,
    version             INT4 NOT NULL DEFAULT(0),
    name                VARCHAR NOT NULL,
    address             VARCHAR,
    phone               VARCHAR NOT NULL,
    payment             REAL NOT NULL,
    course_id           INT4 NOT NULL
);

CREATE TABLE IF NOT EXISTS courses (
    id                  SERIAL PRIMARY KEY,
    version             INT4 NOT NULL DEFAULT(0),
    title               VARCHAR NOT NULL,
    number              INT4 NOT NULL,
    price               REAL NOT NULL
);

CREATE TABLE IF NOT EXISTS students_courses (
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
