package com.sk.bookz_catalog.repo;

import com.sk.bookz_catalog.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookRepository extends JpaRepository<Book,Long>, JpaSpecificationExecutor<Book> {

    public List<Book> findAllByAuthor(String author);
    public List<Book> findAllByGenre(String genre);
    public List<Book> findAllByPublisher(String publisher);
}
