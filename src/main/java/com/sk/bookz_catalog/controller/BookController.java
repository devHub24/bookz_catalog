package com.sk.bookz_catalog.controller;

import com.sk.bookz_catalog.dto.BookDto;
import com.sk.bookz_catalog.mapper.BookMapper;
import com.sk.bookz_catalog.service.IBookService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {

    private final IBookService bookService;

    public BookController(IBookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public ResponseEntity<BookDto> newBook(@RequestBody BookDto bookDto){
        return ResponseEntity.ok(bookService.newBook(bookDto));
    }

    @GetMapping()
    public ResponseEntity<List<BookDto>> getBooks(){
        return ResponseEntity.status(HttpStatus.OK).body(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookById(@PathVariable(value = "id") Long id){
        return ResponseEntity.ok(bookService.getBookById(id));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<BookDto>> filterBooks(@RequestParam(value = "author", required = false) String author,
                                                  @RequestParam(value = "genre", required = false) String genre,
                                                  @RequestParam(value= "publisher", required=false) String publisher){

        return ResponseEntity.status(HttpStatus.OK).body(bookService.filterBooks(author,genre,publisher));
    }


}
