package seoultech.library.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import seoultech.library.model.Author;
import seoultech.library.model.AuthorRepository;
import seoultech.library.model.User;

import java.util.Optional;

@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    @Transactional
    public Author save(Author author, User actioner) {
        author.setCreator(actioner);
        author.setUpdater(actioner);
        return authorRepository.save(author);
    }

    @Override
    @Transactional
    public Author update(Author author, User actioner) {
        author.setUpdater(actioner);
        return authorRepository.save(author);
    }

    @Override
    public Optional<Author> findByFirstNameAndLastName(String firstName, String lastName) {
        return authorRepository.findByFirstNameAndLastName(firstName, lastName);
    }

}