package guru.springframework.spring5webapp.bootstrap;

import guru.springframework.spring5webapp.domain.Author;
import guru.springframework.spring5webapp.domain.Book;
import guru.springframework.spring5webapp.domain.Publisher;
import guru.springframework.spring5webapp.repositories.AuthorRepository;
import guru.springframework.spring5webapp.repositories.BookRepository;
import guru.springframework.spring5webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BootStrapData implements CommandLineRunner {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    public BootStrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        System.out.println("Started in Bootstrap");

        Publisher oReilly = new Publisher();
        oReilly.setName("O'Reilly");
        oReilly.setCity("Sebastopol");
        oReilly.setState("Califórnia");

        publisherRepository.save(oReilly);

        Author eric = new Author("Eric", "Evans");
        Book ddd = new Book("Domain Driven Design", "123123");
        eric.getBooks().add(ddd);
        ddd.getAuthors().add(eric);

        oReilly.getBooks().add(ddd);
        ddd.setPublisher(oReilly);

        authorRepository.save(eric);
        bookRepository.save(ddd);
        publisherRepository.save(oReilly);

        Author rod = new Author("Rod", "Johnson");
        Book noEJB = new Book("J2EE Development without EJB","321654987");
        rod.getBooks().add(noEJB);
        noEJB.getAuthors().add(rod);

        oReilly.getBooks().add(noEJB);
        noEJB.setPublisher(oReilly);

        authorRepository.save(rod);
        bookRepository.save(noEJB);
        publisherRepository.save(oReilly);

        System.out.println("Number of Book: " + bookRepository.count());
        System.out.println("Publisher Number of Books: " + oReilly.getBooks().size());

    }
}
