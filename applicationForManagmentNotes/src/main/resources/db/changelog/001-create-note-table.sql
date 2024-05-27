CREATE TABLE project.note (
                                           note_id SERIAL PRIMARY KEY,
                                           title VARCHAR(255) NOT NULL UNIQUE ,
                                           description VARCHAR(255),
                                           creation_date DATE NOT NULL,
                                           change_date DATE
);