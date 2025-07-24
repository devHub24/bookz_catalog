package com.sk.bookz_catalog.service;

import com.sk.bookz_catalog.dto.BookDto;

import java.util.List;

public interface IBookService {

    public BookDto newBook(BookDto bookDto);
    public List<BookDto> getAllBooks();
    public List<BookDto> getBooksByAuthor(String author);
    public List<BookDto> getBooksByPublisher(String publisher);
    public List<BookDto> getBooksByGenre(String category);
    public BookDto getBookById(Long id);

    public List<BookDto> filterBooks(String author,String genre, String publisher);

}
