INSERT INTO students (name, phone, email, record_book_number) VALUES
('Bob', '90001', 'bob@mail.ru', 101),
('Alice', '90002', 'alice@mail.ru', 102),
('John', '90003', 'john@mail.ru', 103),
('Steve', '90004', 'steve@mail.ru', 104),
('Sarah', '90005', 'sarah@mail.ru', 105);

INSERT INTO courses (title, number, price) VALUES
('Java', 11, 49.99),
('Kotlin', 12, 59.99),
('Scala', 13, 69.99);

INSERT INTO professors (name, payment, course_id) VALUES
('Andrey Breslav', 1500, 2),
('Martin Odersky', 1500, 3);

INSERT INTO students_courses (student_id, course_id) VALUES
(1, 1), (1, 2), (1, 3),
(2, 1), (2, 2),
(3, 1), (3, 3),
(4, 2), (4, 3);

INSERT INTO completing_courses (student_id, course_id, mark) VALUES
(1, 1, 3), (1, 2, 4),
(1, 1, 4), (1, 2, 5),
(1, 1, 5), (1, 2, 5),
(1, 1, 5),

(2, 1, 4), (2, 2, 4),
(2, 1, 5), (2, 2, 5),
(2, 1, 5),

(3, 1, 4), (3, 3, 3),
           (3, 3, 5),

(4, 2, 3), (4, 3, 5),
(4, 2, 4), (4, 3, 4);
