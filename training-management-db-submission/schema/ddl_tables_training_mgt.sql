CREATE TABLE usertype (
usertype_id INT PRIMARY KEY,
usertype_type VARCHAR(20)
);

CREATE TABLE login (
login_id INT PRIMARY KEY,
usertype_id INT,
login_username VARCHAR(40),
login_password VARCHAR(15),
FOREIGN KEY(usertype_id) REFERENCES usertype(usertype_id) ON DELETE SET NULL
);

CREATE TABLE trainer (
trainer_id INT PRIMARY KEY,
trainer_name VARCHAR(40),
login_id INT,
FOREIGN KEY(login_id) REFERENCES login(login_id) ON DELETE SET NULL
);

CREATE TABLE candidate (
candidate_id INT PRIMARY KEY,
candidate_name VARCHAR(40),
candidate_email VARCHAR(40),
candidate_phone INT,
login_id INT,
FOREIGN KEY(login_id) REFERENCES login(login_id) ON DELETE SET NULL
);

CREATE TABLE course (
course_id INT PRIMARY KEY,
course_name VARCHAR(40)
);

CREATE TABLE batch (
batch_id INT PRIMARY KEY,
batch_name VARCHAR(40),
batch_start_date DATE,
batch_end_date DATE,
course_id INT,
FOREIGN KEY(course_id) REFERENCES course(course_id) ON DELETE SET NULL
);

CREATE TABLE topic (
topic_id INT,
topic_name VARCHAR(40),
course_id INT,
PRIMARY KEY(topic_id, course_id),
FOREIGN KEY(course_id) REFERENCES course(course_id) ON DELETE CASCADE
);

CREATE TABLE assignment (
assignment_id INT,
assignment_title VARCHAR(40),
assignment_submission_date DATE,
batch_id INT,
PRIMARY KEY(assignment_id, batch_id),
FOREIGN KEY(batch_id) REFERENCES batch(batch_id) ON DELETE CASCADE
);

CREATE TABLE candidate_batch (
batch_id INT,
candidate_id INT,
cb_status VARCHAR(40),
PRIMARY KEY(batch_id, candidate_id),
FOREIGN KEY(batch_id) REFERENCES batch(batch_id) ON DELETE CASCADE,
FOREIGN KEY(candidate_id) REFERENCES candidate(candidate_id) ON DELETE CASCADE
);

CREATE TABLE candidate_assignment (
assignment_id INT,
candidate_id INT,
ca_submission_date DATE,
ca_score INT,
PRIMARY KEY(candidate_id, assignment_id),
FOREIGN KEY(candidate_id) REFERENCES candidate(candidate_id) ON DELETE CASCADE,
FOREIGN KEY(assignment_id) REFERENCES assignment(assignment_id) ON DELETE CASCADE
);

CREATE TABLE batch_trainer (
batch_id INT,
trainer_id INT,
FOREIGN KEY(batch_id) REFERENCES batch(batch_id) ON DELETE CASCADE,
FOREIGN KEY(trainer_id) REFERENCES trainer(trainer_id) ON DELETE CASCADE
);