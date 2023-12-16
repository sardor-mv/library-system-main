package seoultech.library.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import seoultech.library.model.Author;
import seoultech.library.model.Book;
import seoultech.library.model.BookRepository;
import seoultech.library.model.User;

import java.util.Optional;

@Service
public class BookServiceImpl implements BookService{

    private BookRepository bookRepository;
    private AuthorService authorService;

    public BookServiceImpl(BookRepository bookRepository, AuthorService authorService) {
        this.bookRepository = bookRepository;
        this.authorService = authorService;
    }

    @Override
    @Transactional
    public Book save(Book book, User actioner) {

        Optional<Author> optAuthor = authorService.findByFirstNameAndLastName(book.getAuthor().getFirstName(), book.getAuthor().getLastName());
        if(optAuthor.isPresent()) {
            book.setAuthor(optAuthor.get());
        } else {
            Author author = authorService.save(book.getAuthor(), actioner);
            book.setAuthor(author);
        }

        book.setCreator(actioner);
        book.setUpdater(actioner);
        return bookRepository.save(book);
    }

    @Override
    public Optional<Book> findByTitle(String title) {
        return bookRepository.findByTitle(title);
    }
}