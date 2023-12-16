package seoultech.library.service;

import seoultech.library.model.Book;
import seoultech.library.model.User;

import java.util.Optional;

public interface BookService {
    Book save(Book book, User actioner);

    Optional<Book> findByTitle(String title);
}
