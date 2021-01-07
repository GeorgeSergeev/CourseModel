DELETE FROM COURSES;
DELETE FROM COURSE_PROGRESS;
DELETE FROM PROFESSOR_COURSE;
DELETE FROM PROFESSORS;
DELETE FROM STUDENT_COURSE;
DELETE FROM STUDENTS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO COURSES (NAME, NUMBER, PRICE)
VALUES ('Java for beginners', 1, 1500.00),
       ('Java for professionals', 2, 2500.00),
       ('JavaScript for beginners', 3, 1000.00),
       ('JavaScript for professionals', 4, 2000.00);

INSERT INTO STUDENTS (NAME, ADDRESS, PHONE, EMAIL, STUDENT_NUMBER, AVG_PERFORMANCE)
VALUES ('Alexander', 'Orel', '9101112233', 'Alexnder@mail.ru', 00012, 5.0),
       ('Alexey', 'Orel', '9112223344', 'Alexey@mail.ru', 00055, 4.0),
       ('Peter', 'New-York', '9123334455', 'Peter@gmail.com', 00052, 4.5),
       ('John', 'London', '9134445566', 'John@rambler.ru', 00033, 3.0),
       ('Gina', 'San-Francisco', '9011112223', 'Gina777@gmail.com', 00031, 3.0),
       ('Leslie Knope', 'Pawnee', '9037773344', 'super-leslie@pawnee.gov', 00003, 0.0),
       ('Ron Swanson', 'Pawnee', '9037773345', 'ron-fokin-swanson@pawnee.gov', 00051, 5.0),
       ('Andy Dwyer', 'Pawnee', '9028877774', 'andy@pawnee.gov', 00050, 3.6),
       ('Ann Perkins', 'Pawnee', '9009887773', 'ann@pawnee.gov', 00049, 3.0);

INSERT INTO PROFESSORS (NAME, ADDRESS, PHONE, PAYMENT)
VALUES ('John Zoidberg', 'Decapod 10', '3940010101', 10.0),
       ('Hubert Farnsworth', 'New New-york', '5550007744', 100.00),
       ('Филипп Преображенский', 'Moscow', '9055554664', 150.00);

INSERT INTO PROFESSOR_COURSE (PROFESSOR_ID, COURSE_ID)
VALUES (100013, 100000),
       (100014, 100001),
       (100015, 100003);

INSERT INTO COURSE_PROGRESS (STUDENT_ID, COURSE_ID, MARK)
VALUES (100004, 100000, 5),
       (100004, 100000, 5),
       (100004, 100000, 5),
       (100005, 100000, 4),
       (100005, 100000, 4),
       (100005, 100000, 4),
       (100006, 100000, 4),
       (100006, 100000, 5),
       (100007, 100000, 3),
       (100007, 100000, 3),
       (100010, 100001, 5),
       (100010, 100001, 5),
       (100010, 100001, 5),
       (100011, 100001, 5),
       (100011, 100001, 3),
       (100011, 100001, 3),
       (100012, 100001, 4),
       (100012, 100001, 2),
       (100007, 100002, 3),
       (100008, 100002, 3),
       (100011, 100003, 3),
       (100012, 100003, 3);