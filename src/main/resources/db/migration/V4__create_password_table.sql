CREATE TABLE password
(
    id VARCHAR(100) PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    password VARCHAR(100) NOT NULL,
    id_user VARCHAR(100),
    UNIQUE (name,password)
);

ALTER TABLE password ADD FOREIGN KEY(id_user) REFERENCES users (id)