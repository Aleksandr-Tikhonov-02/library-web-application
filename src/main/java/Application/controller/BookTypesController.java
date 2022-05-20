package Application.controller;

import Application.entity.BookTypes;
import Application.exception.NotFoundException;
import Application.service.BookTypesService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/book-types")
public class BookTypesController {

    private final BookTypesService bookTypesService;

    public BookTypesController(BookTypesService bookTypesService) {
        this.bookTypesService = bookTypesService;
    }

    @GetMapping("/all")
    public ResponseEntity<List<BookTypes>> getAllBooksTypes() {
        List<BookTypes> list = bookTypesService.getAllBookTypes();
        return new ResponseEntity<>(list, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookTypes> getBookTypeById(@PathVariable("id") long id) {
        try {
            return new ResponseEntity<>(bookTypesService.getBookTypeById(id), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @GetMapping()
    public ResponseEntity<List<BookTypes>> getBookTypeByName(@RequestParam("name") String name) {
        try {
            return new ResponseEntity<>(bookTypesService.getBookTypeByName(name), HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @PostMapping(value = "/new", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<BookTypes> addBook(@RequestBody BookTypes bookType) {
        return new ResponseEntity<>(bookTypesService.addBookType(bookType), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<Void> updateBookType(@PathVariable("id") long id, @RequestBody BookTypes bookType) {
        try {
            bookTypesService.updateBookType(id, bookType);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteBookType(@PathVariable("id") long id) {
        try {
            bookTypesService.deleteBookType(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

}
