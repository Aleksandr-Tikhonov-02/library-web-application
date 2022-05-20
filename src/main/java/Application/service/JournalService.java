package Application.service;

import Application.entity.Journal;
import Application.exception.NotFoundException;
import Application.repository.JournalRepository;
import Application.utils.ClientsAndTheirBooks;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class JournalService {

    private final JournalRepository journalRepository;

    public JournalService(JournalRepository journalRepository) {
        this.journalRepository = journalRepository;
    }

    public Journal addJournal(Journal journal) {
        return journalRepository.save(journal);
    }

    public void deleteJournal(long id) {
        Optional<Journal> optionalJournal = journalRepository.findById(id);
        if (optionalJournal.isPresent()) {
            journalRepository.delete(optionalJournal.get());
        } else {
            throw new NotFoundException("Client not found");
        }
    }

    public void updateJournal(long id, Journal journal) {
        Optional<Journal> optionalJournal = journalRepository.findById(id);
        if (optionalJournal.isPresent()) {
            journalRepository.updateById(
                    journal.getBook().getId(),
                    journal.getClient().getId(),
                    journal.getData_beg(),
                    journal.getData_end(),
                    journal.getData_ret(),
                    id);
        } else {
            throw new NotFoundException("Client not found");
        }
    }

    public List<ClientsAndTheirBooks> joinClientsAndTheirBooks() {
        return journalRepository.joinClientsAndTheirBooks();
    }

    public List<ClientsAndTheirBooks> joinClientsAndTheirBooksWhereFullNameEquals(
            String firstName, String secondName, String patherName
    ) {
        return journalRepository.joinClientsAndTheirBooksWhereFullNameEquals(firstName, secondName, patherName);
    }

    public List<ClientsAndTheirBooks> joinClientsAndTheirBooksWherePassportEquals(
            String passportNumber, String passportSeria
    ) {
        return journalRepository.joinClientsAndTheirBooksWherePassportEquals(passportNumber, passportSeria);
    }
}
