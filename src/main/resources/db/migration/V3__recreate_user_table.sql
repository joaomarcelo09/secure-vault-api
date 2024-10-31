DROP TABLE users;

CREATE TABLE users (
                       id VARCHAR(100) PRIMARY KEY,
                       name VARCHAR(100) NOT NULL,
                       email VARCHAR(100) NOT NULL UNIQUE,
                       password VARCHAR(255) NOT NULL
);