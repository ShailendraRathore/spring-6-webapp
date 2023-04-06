package com.shailendra.spring6webapp.services;

import com.shailendra.spring6webapp.domain.Book;

public interface BookService {
    Iterable<Book> findAll();
}
