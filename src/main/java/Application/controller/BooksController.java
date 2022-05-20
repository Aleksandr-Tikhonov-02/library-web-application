package Application.controller;

import Application.entity.Books;
import Application.exception.NotFoundException;
import Application.service.BooksService;
import Application.utils.BooksAndBookTypesFull;
import Application.utils.BooksAndBookTypesLittle;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BooksController {

    private final BooksService booksService;

    public BooksController(BooksService booksService) {
        this.booksService = booksService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Books>> getAllBooks() {
        List<Books> list = booksService.getAllBooks();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Books> getBooksById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(booksService.getBookById(id), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<Books>> getBooksByName(@RequestParam("name") String name) {
        try {
            return new ResponseEntity<>(booksService.getBookByName(name), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping(value = "/new", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<Books> addBook(@RequestBody Books book) {
        return new ResponseEntity<>(booksService.addBook(book), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<Void> updateBook(@PathVariable("id") long id, @RequestBody Books book) {
        try {
            booksService.updateBook(id, book);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable("id") long id) {
        try {
            booksService.deleteBook(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping("/joinBooksAndTypes")
    public ResponseEntity<List<BooksAndBookTypesLittle>> joinBooksAndTypes() {
        return new ResponseEntity<>(booksService.joinBooksAndTypes(), HttpStatus.OK);
    }

    @GetMapping("/joinBooksAndTypesFull")
    public ResponseEntity<List<BooksAndBookTypesFull>> joinBooksAndTypesFull() {
        return new ResponseEntity<>(booksService.joinBooksAndTypesFull(), HttpStatus.OK);
    }

}
