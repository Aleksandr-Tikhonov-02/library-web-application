package Application.service;

import Application.entity.Books;
import Application.exception.NotFoundException;
import Application.repository.BooksRepository;
import Application.utils.BooksAndBookTypesFull;
import Application.utils.BooksAndBookTypesLittle;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BooksService {

    private final BooksRepository booksRepository;

    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Books> getAllBooks() {
        return booksRepository.findAll();
    }


    public Books getBookById(long id) {
        Optional<Books> optionalBook = booksRepository.findById(id);
        if (optionalBook.isPresent()) {
            return optionalBook.get();
        } else {
            throw new NotFoundException("Book not found");
        }
    }

    public List<Books> getBookByName(String name) {
        List<Books> books = booksRepository.findByName(name);
        if (!books.isEmpty()) {
            return books;
        } else {
            throw new NotFoundException("Book not found");
        }
    }

    public Books addBook(Books book) {
        return booksRepository.save(book);
    }

    public void deleteBook(long id) {
        Books book;
        try {
            book = getBookById(id);
        } catch (NotFoundException exception) {
            throw new NotFoundException("Book not found");
        }
        booksRepository.delete(book);
    }

    public void updateBook(long id, Books book) {
        try {
            getBookById(id);
        } catch (NotFoundException exception) {
            throw new NotFoundException("Book not found");
        }
        booksRepository.updateById(book.getName(), book.getCount(), book.getBookType().getId(), id);
    }

    public List<BooksAndBookTypesLittle> joinBooksAndTypes() {
        return booksRepository.joinBooksWithTypes();
    }

    public List<BooksAndBookTypesFull> joinBooksAndTypesFull() {
        return booksRepository.joinBooksWithTypesFull();
    }
}
