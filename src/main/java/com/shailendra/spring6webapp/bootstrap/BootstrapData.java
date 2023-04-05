package com.shailendra.spring6webapp.bootstrap;

import com.shailendra.spring6webapp.domain.Author;
import com.shailendra.spring6webapp.domain.Book;
import com.shailendra.spring6webapp.repositories.AuthorRepository;
import com.shailendra.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Author authorTom = new Author();
        authorTom.setFirstName("Tom");
        authorTom.setLastName("Shelby");

        Book tomBook = new Book();
        tomBook.setTitle("Tom Book 1");
        tomBook.setIsbn("12345");

        Author savedAuthor = authorRepository.save(authorTom);
        Book savedBook = bookRepository.save(tomBook);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book noEJB = new Book();
        noEJB.setTitle("J2EE Development without EJB");
        noEJB.setIsbn("54757585");

        Author rodSaved = authorRepository.save(rod);
        Book noEJBSaved = bookRepository.save(noEJB);

        savedAuthor.getBooks().add(savedBook);
        rodSaved.getBooks().add(noEJBSaved);

        authorRepository.save(savedAuthor);
        authorRepository.save(rodSaved);

        System.out.println("In Bootstrap data: ");
        System.out.println("Author count :" + authorRepository.count());
        System.out.println("Book count :" + bookRepository.count());
    }

}
