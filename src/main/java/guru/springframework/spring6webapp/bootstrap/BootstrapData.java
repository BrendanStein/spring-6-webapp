package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.domain.Publisher;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import guru.springframework.spring6webapp.repositories.PublisherRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component //indicates that the spring context must detect this when the application starts
// CommandLineRunner interface is a spring boot component, when it is detected in the context, it executes the run() method
public class BootstrapData implements CommandLineRunner
{
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;
    private final PublisherRepository publisherRepository;

    //constructor brings the above interfaces in here for use (autowires)
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository, PublisherRepository publisherRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
        this.publisherRepository = publisherRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        // creating an author
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Guru");

        // creating a book
        Book book1 = new Book();
        book1.setTitle("Domain Driven Design");
        book1.setIsbn("1234567890");

        // creating an author
        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        // creating a book
        Book book2 = new Book();
        book2.setTitle("J2EE Development without EJB");
        book2.setIsbn("987654321");

        // creating a publisher
        Publisher penguin = new Publisher();
        penguin.setPublisherName("Penguin");
        penguin.setAddress("51 Penguin Street");
        penguin.setCity("San Francisco");
        penguin.setState("CA");
        penguin.setZip("9999");

        // creating a publisher
        Publisher puffin = new Publisher();
        puffin.setPublisherName("Penguin");
        puffin.setAddress("51 Penguin Street");
        puffin.setCity("San Francisco");
        puffin.setState("CA");
        puffin.setZip("9999");

        // saving inside objects
        Author ericSaved = authorRepository.save(eric);
        Book book1Saved = bookRepository.save(book1);
        Publisher penguinSaved = publisherRepository.save(penguin);

        // saving inside objects
        Author rodSaved = authorRepository.save(rod);
        Book book2Saved = bookRepository.save(book2);
        Publisher puffinSaved = publisherRepository.save(puffin);

        //association between authors and books
        ericSaved.getBooks().add(book1Saved);
        rodSaved.getBooks().add(book2Saved);

        //persisting in the db
        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);
        publisherRepository.save(penguinSaved);
        publisherRepository.save(puffinSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());
        System.out.println("Publisher Count: " + publisherRepository.count());

    }
}
