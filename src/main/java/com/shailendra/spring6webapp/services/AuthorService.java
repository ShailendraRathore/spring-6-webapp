package com.shailendra.spring6webapp.services;

import com.shailendra.spring6webapp.domain.Author;

public interface AuthorService {
    Iterable<Author> findAll();
}
