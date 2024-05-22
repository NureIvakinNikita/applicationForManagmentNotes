CREATE SEQUENCE project.note_id_seq START WITH 1 INCREMENT BY 1;
ALTER TABLE project.note ALTER COLUMN note_id SET DEFAULT nextval('project.note_id_seq');
