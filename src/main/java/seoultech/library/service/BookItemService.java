package seoultech.library.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import seoultech.library.model.BookItem;
import seoultech.library.model.BookStatus;
import seoultech.library.model.User;

import java.util.List;

public interface BookItemService {
    BookItem save(BookItem bookItem, String classNumber, User actioner);

    Long count();
    Long countByStatus(BookStatus status);

    BookItem findByCallNumber(String callNumber);

    List<BookItem> findAll();
    Page<BookItem> findAll(Pageable pageable);
}
