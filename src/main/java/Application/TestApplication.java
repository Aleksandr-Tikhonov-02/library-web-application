package Application;

import Application.entity.*;
import Application.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.util.Collections;

@Component
public class TestApplication implements CommandLineRunner {

    @Autowired
    private ClientsRepository clientsRepository;

    @Autowired
    private BookTypesRepository bookTypesRepository;

    @Autowired
    private BooksRepository booksRepository;

    @Autowired
    private JournalRepository journalRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        //fillTables();
    }

    private void fillTables() {
        Clients client1 = new Clients("Aleksandr", "Tikhonov",
                "Nikolaevich", "1111", "111111");
        Clients client2 = new Clients("Ivan", "Ivanov",
                "Ivanovich", "2222", "222222");
        Clients client3 = new Clients("Ivan", "Smirnov",
                "Nikolaevich", "3333", "333333");
        Clients client4 = new Clients("Aleksandr", "Sidorov",
                "Vladimirovich", "4444", "444444");
        Clients client5 = new Clients("Jhon", "James",
                "Jhonson", "5555", "555555");

        clientsRepository.save(client1);
        clientsRepository.save(client2);
        clientsRepository.save(client3);
        clientsRepository.save(client4);
        clientsRepository.save(client5);

        BookTypes historyType = new BookTypes("History", 1000, 1000, 10);
        BookTypes horrorType = new BookTypes("Horror", 500, 2000, 14);
        BookTypes fantasyType = new BookTypes("Fantasy", 600, 800, 21);
        BookTypes classicType = new BookTypes("Classic", 30000, 300, 11);
        BookTypes sfType = new BookTypes("Science fiction", 800, 900, 16);
        BookTypes romanceType = new BookTypes("Romance", 400, 400, 13);
        BookTypes detectiveType = new BookTypes("Detective", 400, 1200, 20);

        bookTypesRepository.save(historyType);
        bookTypesRepository.save(horrorType);
        bookTypesRepository.save(fantasyType);
        bookTypesRepository.save(classicType);
        bookTypesRepository.save(sfType);
        bookTypesRepository.save(romanceType);
        bookTypesRepository.save(detectiveType);

        Books book1 = new Books("name1",3000, historyType);
        Books book2 = new Books("name2",4000, horrorType);
        Books book3 = new Books("name3",5000, fantasyType);
        Books book4 = new Books("name4",6000, fantasyType);
        Books book5 = new Books("name5",7000, classicType);
        Books book6 = new Books("name6",8000, classicType);
        Books book7 = new Books("name7",2000, classicType);
        Books book8 = new Books("name8",2500, sfType);
        Books book9 = new Books("name9",4300, romanceType);
        Books book10 = new Books("name10",3100, romanceType);
        Books book11 = new Books("name11",2900, romanceType);
        Books book12 = new Books("name12",400, detectiveType);

        booksRepository.save(book1);
        booksRepository.save(book2);
        booksRepository.save(book3);
        booksRepository.save(book4);
        booksRepository.save(book5);
        booksRepository.save(book6);
        booksRepository.save(book7);
        booksRepository.save(book8);
        booksRepository.save(book9);
        booksRepository.save(book10);
        booksRepository.save(book11);
        booksRepository.save(book12);

        journalRepository.save(new Journal(book1, client1,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book2, client1,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book4, client1,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book4, client2,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book8, client2,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book11, client3,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book7, client4,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book5, client4,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book3, client4,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book9, client4,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book12, client5,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));
        journalRepository.save(new Journal(book2, client5,
                new Timestamp(1000), new Timestamp(2000), new Timestamp(1500)));

        userRepository.save(new User(
                "user",
                passwordEncoder.encode("pwd"),
                Collections.singletonList("ROLE_USER"))
        );
    }
}
