CREATE TABLE author (
    id SERIAL PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    birth_year SMALLINT NOT NULL,
    death_year SMALLINT
);

CREATE TABLE book (
    id SERIAL PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    language CHAR(2) NOT NULL,
    download_count BIGINT NOT NULL,
    author_id INT NOT NULL,
    FOREIGN KEY (author_id) REFERENCES author(id)
);