package seoultech.library.service;

import seoultech.library.model.Author;
import seoultech.library.model.User;

import java.util.Optional;

public interface AuthorService {

    Author save(Author author, User actioner);
    Author update(Author author, User actioner);

    Optional<Author> findByFirstNameAndLastName(String firstName, String lastName);
}
