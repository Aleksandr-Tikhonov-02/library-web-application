package Application.controller;

import Application.entity.Journal;
import Application.exception.NotFoundException;
import Application.service.JournalService;
import Application.utils.ClientsAndTheirBooks;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @GetMapping("/joinClientsAndBooks")
    public ResponseEntity<List<ClientsAndTheirBooks>> joinClientsAndTheirBooks() {
        return new ResponseEntity<>(journalService.joinClientsAndTheirBooks(), HttpStatus.OK);
    }

    @GetMapping("/joinClientsAndBooksByName")
    public ResponseEntity<List<ClientsAndTheirBooks>> joinClientsAndTheirBooksWhereFullNameEquals(
            @RequestParam("first-name") String firstName,
            @RequestParam("second-name") String secondName,
            @RequestParam("pather-name") String patherName
    ) {
        return new ResponseEntity<>(journalService.joinClientsAndTheirBooksWhereFullNameEquals(
                firstName, secondName, patherName
        ), HttpStatus.OK);
    }

    @GetMapping("/joinClientsAndBooksByPassport")
    public ResponseEntity<List<ClientsAndTheirBooks>> joinClientsAndTheirBooksWherePassportEquals(
            @RequestParam("pass-num") String passportNumber,
            @RequestParam("pass-ser") String passportSeria
    ) {
        return new ResponseEntity<>(journalService.joinClientsAndTheirBooksWherePassportEquals(
                passportNumber, passportSeria
        ), HttpStatus.OK);
    }

    @PostMapping(value = "/new", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<Journal> addJournal(@RequestBody Journal journal) {
        return new ResponseEntity<>(journalService.addJournal(journal), HttpStatus.OK);
    }

    @PutMapping(value = "/update/{id}", consumes = "Application/json", produces = "Application/json")
    public ResponseEntity<Void> updateJournal(@PathVariable("id") long id, @RequestBody Journal journal) {
        try {
            journalService.updateJournal(id, journal);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteJournal(@PathVariable("id") long id) {
        journalService.deleteJournal(id);
        try {
            journalService.deleteJournal(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NotFoundException exception) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, exception.getMessage());
        }
    }
}
