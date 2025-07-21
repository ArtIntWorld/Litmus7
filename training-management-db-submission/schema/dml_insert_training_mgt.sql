INSERT INTO usertype VALUES(1,"admin");
INSERT INTO usertype VALUES(2,"candidate");
INSERT INTO usertype VALUES(3,"trainer");

INSERT INTO login
VALUES
(1, 1, "Abdu", "AB1234DU!"),
(2, 2, "Adhi", "Aa!37628S1"),
(3, 2, "Eliza", "Ee!37628S1"),
(4, 2, "Neha", "Nn!37628S1"),
(5, 2, "Karthi", "Kk!37628S1"),
(6, 2, 'Bechu', 'Bb137628S1'),
(7, 2, 'Abhi', 'Aa137628S1'),
(8, 3, 'Fahad', 'Ff137628S1'),
(9, 3, 'Isaac', 'lli37628S1'),
(10, 3, 'Almia', 'Aa137628S1'),
(11, 3, 'Anakha', 'Aa137628S1'),
(12, 3, 'Aruna', 'Aa137628S1');

INSERT INTO candidate
VALUES
(101, 'Adhithya', 'adhi1234@gmail', '9563864839', 2),
(102, 'Elizabeth', 'elzabeth452@gmail', '9456738254', 3),
(103, 'Neha', 'nehag948@gmail', '9876712344', 4),
(104, 'Karthik', 'karthi777@gmail', '8439212224', 5),
(105, 'Bechu', 'bechuk734@gmail', '9605699331', 6),
(106, 'Abhijith', 'abhiaa35456@gmail', '8894555367', 7);

INSERT INTO trainer 
VALUES 
(211, 'Fahad', 8),
(212, 'Isaac', 9),
(213, 'Almia', 10),
(214, 'Anakha', 11),
(215, 'Arunachalam', 12);

INSERT INTO course
VALUES 
(11, 'Programming in Python'),
(12, 'Object Oriented Programming'),
(13, 'Data Structure'),
(14, 'Database Management System');

INSERT INTO topic
VALUES 
(26, 'OpenCV Module', 11),
(27, 'Django Framework', 11),
(28, 'Polymorphism', 12),
(29, 'Error Handling', 12),
(30, 'Graph and Tree', 13),
(31, 'Sorting', 13),
(32, 'Normalization', 14),
(33, 'Relational Database', 14);

INSERT INTO batch
VALUES 
(50, 'Batch-1', '2025-01-12', '2025-01-25', 11),
(51, 'Batch-2', '2025-02-02', '2025-02-20', 11),
(52, 'Batch-3', '2025-03-09', '2025-03-28', 12),
(53, 'Batch-4', '2025-04-17', '2025-04-30', 13),
(54, 'Batch-5', '2025-05-01', '2025-05-25', 13),
(55, 'Batch-6', '2025-05-29', '2025-06-22', 14),
(56, 'Batch-7', '2025-07-04', '2025-07-29', 14),
(57, 'Batch-8', '2025-08-15', '2025-08-30', 14);

INSERT INTO batch_trainer
VALUES 
(50, 211),
(50, 212),
(53, 211),
(53, 215),
(55, 213),
(55, 212),
(55, 214),
(56, 215);

INSERT INTO candidate_batch
VALUES 
(50, 101, 'In Progress'),
(50, 103, 'In Progress'),
(50, 104, 'Completed'),
(50, 105, 'Completed'),
(50, 106, 'Terminated'),
(53, 102, 'In Progress'),
(53, 104, 'In Progress'),
(53, 103, 'Completed'),
(56, 101, 'Terminated');

INSERT INTO assignment
VALUES 
(71, 'Web Development', '2025-01-23', 50),
(72, 'Object Detection Project', '2025-01-17', 50),
(73, 'Dijkstra''s Algorithm Problem', '2025-04-22', 53),
(74, 'ER Diagram Creation', '2025-07-11', 56);

INSERT INTO candidate_assignment
VALUES 
(71, 101, '2025-01-22', 10),
(71, 102, '2025-01-25', 8),
(71, 103, '2025-01-23', 10),
(71, 105, '2025-01-26', 6),
(73, 102, '2025-04-18', 10),
(73, 103, '2025-04-23', 9);