-- Get all batches a candidate is enrolled in, with their status.
SELECT candidate.candidate_name, batch.batch_name, candidate_batch.cb_status
FROM candidate_batch
INNER JOIN batch ON batch.batch_id=candidate_batch.batch_id
INNER JOIN candidate ON candidate.candidate_id=candidate_batch.candidate_id
ORDER BY candidate.candidate_name;

-- Get all trainers assigned to a batch.
SELECT trainer.trainer_name
FROM batch_trainer
INNER JOIN trainer ON batch_trainer.trainer_id=trainer.trainer_id
WHERE batch_trainer.batch_id=55;

-- Get all topics under a course.
SELECT topic.topic_name
FROM topic
WHERE course_id=12;

-- List assignment scores for a candidate in a batch
SELECT candidate.candidate_name, batch.batch_name, candidate_assignment.ca_score
FROM candidate_assignment
INNER JOIN candidate ON candidate.candidate_id=candidate_assignment.candidate_id
INNER JOIN assignment ON candidate_assignment.assignment_id=assignment.assignment_id
INNER JOIN batch ON assignment.batch_id=batch.batch_id
ORDER BY candidate.candidate_name;

-- List candidates with status "Completed" in a given batch
SELECT candidate.candidate_name, batch.batch_name
FROM candidate_batch
INNER JOIN candidate ON candidate.candidate_id=candidate_batch.candidate_id
INNER JOIN batch ON batch.batch_id=candidate_batch.batch_id
WHERE candidate_batch.cb_status="Completed";