DELIMITER $$
CREATE PROCEDURE InsertGrade(IN studentID INT, IN courseID INT, IN grade DECIMAL(3,2))
BEGIN
    INSERT INTO Grades (student_id, course_id, grade, grade_date)
    VALUES (studentID, courseID, grade, CURRENT_DATE);
END$$
DELIMITER ;