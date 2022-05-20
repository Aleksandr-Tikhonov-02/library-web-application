package Application.service;

import Application.entity.BookTypes;
import Application.exception.NotFoundException;
import Application.repository.BookTypesRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BookTypesService {

    private final BookTypesRepository bookTypesRepository;

    public BookTypesService(BookTypesRepository bookTypesRepository) {
        this.bookTypesRepository = bookTypesRepository;
    }

    public List<BookTypes> getAllBookTypes() {
        return bookTypesRepository.findAll();
    }

    public BookTypes getBookTypeById(long id) {
        Optional<BookTypes> optionalBookTypes = bookTypesRepository.findById(id);
        if (optionalBookTypes.isPresent()) {
            return optionalBookTypes.get();
        } else {
            throw new NotFoundException("Book type not found");
        }
    }

    public List<BookTypes> getBookTypeByName(String name) {
        List<BookTypes> bookTypes = bookTypesRepository.findByName(name);
        if (!bookTypes.isEmpty()) {
            return bookTypes;
        } else {
            throw new NotFoundException("Book Type not found");
        }
    }

    public BookTypes addBookType(BookTypes book) {
        return bookTypesRepository.save(book);
    }

    public void deleteBookType(long id) {
        BookTypes bookType;
        try {
            bookType = getBookTypeById(id);
        } catch (NotFoundException exception) {
            throw new NotFoundException("Book type not found");
        }
        bookTypesRepository.delete(bookType);
    }

    public void updateBookType(long id, BookTypes bookType) {
        try {
            getBookTypeById(id);
        } catch (NotFoundException exception) {
            throw new NotFoundException("Book type not found");
        }
        bookTypesRepository.updateById(
                bookType.getName(), bookType.getCount(), bookType.getFine(), bookType.getDay_count(), id);
    }
}
