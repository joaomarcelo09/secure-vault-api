CREATE TABLE users (
                         id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
                         name VARCHAR(100) NOT NULL,
                         email VARCHAR(100) NOT NULL UNIQUE,
                         password VARCHAR(255) NOT NULL
);
