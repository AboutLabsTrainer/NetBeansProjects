package de.ibb.boot.ak.relations;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {

    java.util.List<Book> findByAuthor_Name(String name);
}
