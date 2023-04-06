package com.shailendra.spring6webapp.bootstrap;

import com.shailendra.spring6webapp.domain.Author;
import com.shailendra.spring6webapp.domain.Book;
import com.shailendra.spring6webapp.domain.Publisher;
import com.shailendra.spring6webapp.repositories.AuthorRepository;
import com.shailendra.spring6webapp.repositories.BookRepository;
import com.shailendra.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootstrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
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
        savedBook.getAuthors().add(savedAuthor);
        noEJBSaved.getAuthors().add(rodSaved);

        Publisher  publisher = new Publisher();
        publisher.setPublisherName("Penguin");
        publisher.setAddress("Flat no1");
        publisher.setCity("Pune");
        publisher.setState("Maharashtra");
        publisher.setZip("12345");
        publisherRepository.save(publisher);

        savedBook.setPublisher(publisher);
        noEJBSaved.setPublisher(publisher);

        authorRepository.save(savedAuthor);
        authorRepository.save(rodSaved);
        bookRepository.save(savedBook);
        bookRepository.save(noEJBSaved);





        System.out.println("In Bootstrap data: ");
        System.out.println("Author count :" + authorRepository.count());
        System.out.println("Book count :" + bookRepository.count());
        System.out.println("Publisher count :" + publisherRepository.count());
    }

}
