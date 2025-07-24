package com.sk.bookz_catalog.cache;

import org.springframework.stereotype.Component;

@Component("keygen")
public class KeyGen {

    public String generateKey(String author, String genre, String publisher) {
        return String.valueOf(author) + "::" + genre + "::" + publisher;
    }
}
