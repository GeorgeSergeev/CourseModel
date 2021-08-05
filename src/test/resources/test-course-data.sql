INSERT INTO students (id, name, phone, email, record_book_number) VALUES
(1, 'Bob', '90001', 'bob@mail.ru', 101),
(2, 'Alice', '90002', 'alice@mail.ru', 102);

INSERT INTO courses (id, title, number, price) VALUES
(1, 'Java', 11, 49.99),
(2, 'Kotlin', 12, 59.99);

INSERT INTO students_courses (student_id, course_id) VALUES
(1, 1), (1, 2),
(2, 1);

INSERT INTO students_courses (student_id, course_id, final_mark) VALUES
(2, 2, 5);

INSERT INTO completing_courses (student_id, course_id, mark) VALUES
(2, 2, 5);
