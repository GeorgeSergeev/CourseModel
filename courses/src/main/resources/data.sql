DELETE FROM COURSES;
DELETE FROM COURSE_PROGRESS;
DELETE FROM PROFESSOR_COURSE;
DELETE FROM PROFESSORS;
DELETE FROM STUDENTS;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO COURSES (ID, NAME, NUMBER, PRICE)
VALUES (100000, 'Java for beginners', 1, 1500.00),
       (100001, 'Java for professionals', 2, 2500.00),
       (100002, 'JavaScript for beginners', 3, 1000.00),
       (100003, 'JavaScript for professionals', 4, 2000.00);

INSERT INTO STUDENTS (ID, NAME, ADDRESS, PHONE, EMAIL, STUDENT_NUMBER, AVG_PERFORMANCE)
VALUES (100004, 'Alexander', 'Orel', '9101112233', 'Alexnder@mail.ru', 00012, 5.0),
       (100005, 'Alexey', 'Orel', '9112223344', 'Alexey@mail.ru', 00055, 4.0),
       (100006, 'Peter', 'New-York', '9123334455', 'Peter@gmail.com', 00052, 4.5),
       (100007, 'John', 'London', '9134445566', 'John@rambler.ru', 00033, 3.0),
       (100008, 'Gina', 'San-Francisco', '9011112223', 'Gina777@gmail.com', 00031, 3.0),
       (100009, 'Leslie Knope', 'Pawnee', '9037773344', 'super-leslie@pawnee.gov', 00003, 0.0),
       (100010, 'Ron Swanson', 'Pawnee', '9037773345', 'ron-fokin-swanson@pawnee.gov', 00051, 5.0),
       (100011, 'Andy Dwyer', 'Pawnee', '9028877774', 'andy@pawnee.gov', 00050, 3.6),
       (100012, 'Ann Perkins', 'Pawnee', '9009887773', 'ann@pawnee.gov', 00049, 3.0);

INSERT INTO PROFESSORS (ID, NAME, ADDRESS, PHONE, PAYMENT)
VALUES (100013, 'John Zoidberg', 'Decapod 10', '3940010101', 10.0),
       (100014, 'Hubert Farnsworth', 'New New-york', '5550007744', 100.00),
       (100015, 'Филипп Преображенский', 'Moscow', '9055554664', 150.00);

INSERT INTO PROFESSOR_COURSE (PROFESSOR_ID, COURSE_ID)
VALUES (100013, 100000),
       (100014, 100001),
       (100015, 100003);

INSERT INTO COURSE_PROGRESS (ID, STUDENT_ID, COURSE_ID, MARK)
VALUES (100016, 100004, 100000, 5),
       (100017, 100004, 100000, 5),
       (100018, 100004, 100000, 5),
       (100019, 100005, 100000, 4),
       (100020, 100005, 100000, 4),
       (100021, 100005, 100000, 4),
       (100022, 100006, 100000, 4),
       (100023, 100006, 100000, 5),
       (100024, 100007, 100000, 3),
       (100025, 100007, 100000, 3),
       (100026, 100010, 100001, 5),
       (100027, 100010, 100001, 5),
       (100028, 100010, 100001, 5),
       (100029, 100011, 100001, 5),
       (100030, 100011, 100001, 3),
       (100031, 100011, 100001, 3),
       (100032, 100012, 100001, 4),
       (100033, 100012, 100001, 2),
       (100034, 100007, 100002, 3),
       (100035, 100008, 100002, 3),
       (100036, 100011, 100003, 3),
       (100037, 100012, 100003, 3);