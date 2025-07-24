package com.sk.bookz_catalog.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Builder
public class BookDto {

    @JsonProperty(value ="id", required=false, access = JsonProperty.Access.READ_ONLY)
    private Long id;

    @JsonProperty(value="title")
    private String title;

    @JsonProperty(value="genre")
    private String genre;

    @JsonProperty(value="author")
    private String author;

    @JsonProperty(value="publisher")
    private String publisher;

    @JsonProperty(value="publishedDate")
    private LocalDate publishedDate;

    @JsonProperty(value="price")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private double price;

    @JsonCreator
    public static BookDto createBookDto(Long id, String title, String genre, String author, String publisher, LocalDate publishedDate, double price) {
        return BookDto.builder()
                .id(id)
                .title(title)
                .genre(genre)
                .author(author)
                .publisher(publisher)
                .publishedDate(publishedDate)
                .price(price)
                .build();
    }

}
