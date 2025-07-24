package com.sk.bookz_catalog.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(indexes = {
        @Index(name="idx_author_genre", columnList = "genre,author"),
        @Index(name="idx_author", columnList = "author")}
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name="title")
    private String title;

    @Column(name="author")
    private String author;

    @Column(name="genre")
    private String genre;

    @Column(name="publisher")
    private String publisher;

    @Column(name="published_date")
    private LocalDate publishedDate;

    @Column(name="price")
    private double price;
}
