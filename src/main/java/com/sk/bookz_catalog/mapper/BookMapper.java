package com.sk.bookz_catalog.mapper;

import com.sk.bookz_catalog.dto.BookDto;
import com.sk.bookz_catalog.entity.Book;
import org.springframework.beans.BeanUtils;

public class BookMapper {

    public static BookDto toBookDto(Book book){
        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        return bookDto;
    }

    public static Book toBook(BookDto bookDto){
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book);
        return book;
    }
}
