
1. Create a SQL statement that reports all the student of a specific class when you know a

specific class ID (aka something like �show all students of the class 1b, which has the ID=2�)

SELECT * FROM students where ID_class=2 

2. Create a SQL statement that reports all the student of a specific class when you know a

specific class name (aka something like �show all students of the class �1b�; I don�t know the students ID�)

SELECT * FROM students
inner join classes on students.ID_class=classes.ID_class
where classes.class_name='1b'
