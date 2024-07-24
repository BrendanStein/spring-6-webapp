package guru.springframework.spring6webapp.bootstrap;

import guru.springframework.spring6webapp.domain.Author;
import guru.springframework.spring6webapp.domain.Book;
import guru.springframework.spring6webapp.repositories.AuthorRepository;
import guru.springframework.spring6webapp.repositories.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component //indicates that the spring context must detect this when the application starts
// CommandLineRunner interface is a spring boot component, when it is detected in the context, it executes the run() method
public class BootstrapData implements CommandLineRunner
{
    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;

    //constructor brings the above interfaces in here for use (autowires)
    public BootstrapData(AuthorRepository authorRepository, BookRepository bookRepository) {
        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }

    @Override
    public void run(String... args) throws Exception
    {
        Author eric = new Author();
        eric.setFirstName("Eric");
        eric.setLastName("Guru");

        Book book1 = new Book();
        book1.setTitle("Domain Driven Design");
        book1.setIsbn("1234567890");

        Author ericSaved = authorRepository.save(eric);
        Book book1Saved = bookRepository.save(book1);

        Author rod = new Author();
        rod.setFirstName("Rod");
        rod.setLastName("Johnson");

        Book book2 = new Book();
        book2.setTitle("J2EE Development without EJB");
        book2.setIsbn("987654321");

        Author rodSaved = authorRepository.save(rod);
        Book book2Saved = bookRepository.save(book2);

        //association between authors and books
        ericSaved.getBooks().add(book1Saved);
        rodSaved.getBooks().add(book2Saved);

        //persisting in the db
        authorRepository.save(ericSaved);
        authorRepository.save(rodSaved);

        System.out.println("In Bootstrap");
        System.out.println("Author Count: " + authorRepository.count());
        System.out.println("Book Count: " + bookRepository.count());

    }
}
