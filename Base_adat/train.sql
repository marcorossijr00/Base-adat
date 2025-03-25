-- Adatbázis létrehozása
CREATE DATABASE UniversityDB;
USE UniversityDB;

-- Hallgatók tábla létrehozása
CREATE TABLE Students (
    student_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    birth_date DATE NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20),
    enrollment_date DATE DEFAULT CURRENT_DATE
);

-- Oktatók tábla létrehozása
CREATE TABLE Professors (
    professor_id INT PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE,
    phone VARCHAR(20)
);

-- Tantárgyak tábla létrehozása
CREATE TABLE Courses (
    course_id INT PRIMARY KEY AUTO_INCREMENT,
    course_name VARCHAR(100) NOT NULL,
    credits INT CHECK (credits > 0),
    professor_id INT,
    FOREIGN KEY (professor_id) REFERENCES Professors(professor_id)
);

-- Jegyek tábla létrehozása
CREATE TABLE Grades (
    grade_id INT PRIMARY KEY AUTO_INCREMENT,
    student_id INT,
    course_id INT,
    grade DECIMAL(3, 2) CHECK (grade >= 1.0 AND grade <= 5.0),
    grade_date DATE DEFAULT CURRENT_DATE,
    FOREIGN KEY (student_id) REFERENCES Students(student_id),
    FOREIGN KEY (course_id) REFERENCES Courses(course_id)
);

-- Index létrehozása a hallgatók e-mail mezőjén
CREATE INDEX idx_students_email ON Students(email);

-- Néhány minta adat beszúrása
INSERT INTO Students (first_name, last_name, birth_date, email, phone) VALUES
('Márk', 'Oroszi', '2004-03-15', 'markoroszi@example.com', '+36301234567'),
('Péter', 'Kovács', '2002-07-22', 'peter.kovacs@example.com', '+36306543210');

INSERT INTO Professors (first_name, last_name, email, phone) VALUES
('Dr. Tamás', 'Szabó', 'tamas.szabo@example.com', '+36301112233');

INSERT INTO Courses (course_name, credits, professor_id) VALUES
('Adatbázisok', 5, 1),
('Programozás Alapjai', 4, 1);

INSERT INTO Grades (student_id, course_id, grade) VALUES
(1, 1, 4.5),
(2, 2, 3.7);

-- Tárolt eljárás: Jegy rögzítése
DELIMITER $$
CREATE PROCEDURE InsertGrade(IN studentID INT, IN courseID INT, IN grade DECIMAL(3,2))
BEGIN
    INSERT INTO Grades (student_id, course_id, grade, grade_date)
    VALUES (studentID, courseID, grade, CURRENT_DATE);
END$$
DELIMITER ;

-- Trigger: Új hallgató felvételekor üdvözlő e-mail generálása
DELIMITER $$
CREATE TRIGGER after_student_insert
AFTER INSERT ON Students
FOR EACH ROW
BEGIN
    INSERT INTO Notifications (message, student_id)
    VALUES (CONCAT('Üdvözlünk, ', NEW.first_name, ' ', NEW.last_name, '!'), NEW.student_id);
END$$
DELIMITER ;

-- Nézet: Hallgatók és átlagaik
CREATE VIEW StudentAverages AS
SELECT s.student_id, s.first_name, s.last_name, AVG(g.grade) AS avg_grade
FROM Students s
LEFT JOIN Grades g ON s.student_id = g.student_id
GROUP BY s.student_id;

-- Lekérdezés: Hallgatók, tantárgyaik és oktatóik
SELECT s.first_name, s.last_name, c.course_name, p.first_name AS professor_first, p.last_name AS professor_last
FROM Students s
JOIN Grades g ON s.student_id = g.student_id
JOIN Courses c ON g.course_id = c.course_id
JOIN Professors p ON c.professor_id = p.professor_id;
