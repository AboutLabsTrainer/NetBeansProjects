CREATE TABLE authors (
    id BIGINT NOT NULL AUTO_INCREMENT,
    name VARCHAR(150) NOT NULL,
    PRIMARY KEY (id)
) ENGINE=InnoDB;

CREATE TABLE books (
    id BIGINT NOT NULL AUTO_INCREMENT,
    title VARCHAR(200) NOT NULL,
    isbn VARCHAR(17) NOT NULL,
    author_id BIGINT NOT NULL,
    PRIMARY KEY (id),
    UNIQUE KEY uk_books_isbn (isbn),
    KEY idx_books_author_id (author_id),
    CONSTRAINT fk_books_author
        FOREIGN KEY (author_id) REFERENCES authors (id)
) ENGINE=InnoDB;
