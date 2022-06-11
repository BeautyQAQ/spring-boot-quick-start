DROP TABLE IF EXISTS question;

CREATE SEQUENCE question_id_seq;

CREATE TABLE question
(
    id            text PRIMARY KEY DEFAULT lpad(nextval('question_id_seq')::text, 9, '0'),
    questioner_id text NOT NULL,
    title         text NOT NULL,
    detail        text
);

-- 绑定这个SEQUENCE到question.id字段，这样如果这个表或者字段被删除，则这个SEQUENCE也会被删除
ALTER SEQUENCE question_id_seq OWNED BY question.id;

CREATE INDEX question_questioner_id_index ON question USING hash(questioner_id);
