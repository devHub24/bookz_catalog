package com.sk.bookz_catalog.service;

import com.sk.bookz_catalog.dto.BookDto;
import com.sk.bookz_catalog.entity.Book;
import com.sk.bookz_catalog.mapper.BookMapper;
import com.sk.bookz_catalog.repo.IBookRepository;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ImpBookService implements IBookService {

    private final IBookRepository bookRepository;
    private final KafkaTemplate<String, BookDto> kafkaTemplate;
    private final NewTopic topic;

    public ImpBookService(IBookRepository bookRepository, KafkaTemplate<String, BookDto> kafkaTemplate, NewTopic topic) {
        this.bookRepository = bookRepository;
        this.kafkaTemplate = kafkaTemplate;
        this.topic = topic;
    }

    @CachePut(value = "books", key = "#result.id")
    @Override
    public BookDto newBook(BookDto bookDto) {
        Book savedBook = bookRepository.save(BookMapper.toBook(bookDto));
        BookDto result = BookMapper.toBookDto(savedBook);
        this.kafkaTemplate.send(this.topic.name(), result);
        return result;
    }

    @Cacheable(value = "books", key = "'all'")
    @Override
    public List<BookDto> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(BookMapper::toBookDto)
                .toList();
    }

    @Cacheable(value="books_query", key = "'author::' + #author")
    @Override
    public List<BookDto> getBooksByAuthor(String author) {
        return listConvertor(bookRepository.findAllByAuthor(author));
    }

    @Cacheable(value="books_query", key = "'publisher::' + #publisher")
    @Override
    public List<BookDto> getBooksByPublisher(String publisher) {
        return listConvertor(bookRepository.findAllByPublisher(publisher));
    }

    @Cacheable(value="books_query", key = "'genre::' + #genre")
    @Override
    public List<BookDto> getBooksByGenre(String genre) {
        return listConvertor(bookRepository.findAllByGenre(genre));
    }

    @Cacheable(value = "book",key = "#id")
    @Override
    public BookDto getBookById(Long id) {
        return BookMapper.toBookDto(bookRepository.findById(id).orElse(null));
    }

    @Cacheable(value="books_query",key="@keygen.generateKey(#author,#genre,#publisher)")
    @Override
    public List<BookDto> filterBooks(String author, String genre, String publisher) {

        Specification<Book> spec = BookSpecification.hasAuthor(author)
                .and(BookSpecification.hasGenre(genre))
                .and(BookSpecification.hasPublisher(publisher));
        List<Book> result = bookRepository.findAll(spec);
        return listConvertor(result);
    }

    private List<BookDto> listConvertor(List<Book> books) {
        return books.stream().map(BookMapper::toBookDto).collect(Collectors.toList());
    }

    private static class BookSpecification{
        public static Specification<Book> hasAuthor(String author){
            return (root,
                    criteriaQuery,
                    criteriaBuilder)->
                    author==null ? null :criteriaBuilder.equal(root.get("author"),author);
        }

        public static Specification<Book> hasGenre(String genre){
            return (root,
            criteriaQuery,
            criteriaBuilder) ->
                    genre==null?null: criteriaBuilder.equal(root.get("genre"),genre);
        }

        public static Specification<Book> hasPublisher(String publisher){
            return (root,
                    criteriaQuery,
                    criteriaBuilder) ->
                    publisher==null? null : criteriaBuilder.equal(root.get("publisher"),publisher);
        }
    }
}
