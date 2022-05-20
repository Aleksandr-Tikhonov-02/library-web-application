package Application.repository;


import Application.entity.Journal;
import Application.utils.ClientsAndTheirBooks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Repository
public interface JournalRepository extends JpaRepository<Journal, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE journal SET book_id = ?1, client_id = ?2, data_beg = ?3," +
            "data_end = ?4, data_ret = ?5 " +
            "WHERE id = ?6", nativeQuery = true)
    int updateById(Long book_id, Long client_id, Timestamp data_beg, Timestamp data_end,
                   Timestamp data_ret, long id);



    @Query(value = "SELECT new Application.utils.ClientsAndTheirBooks(c.first_name, c.last_name, c.pather_name, b.name)" +
            " FROM Clients c JOIN Books b ON b.bookType.id = c.id")
    List<ClientsAndTheirBooks> joinClientsAndTheirBooks();

    @Query(value = "SELECT new Application.utils.ClientsAndTheirBooks(c.first_name, c.last_name, c.pather_name, b.name)" +
            " FROM Clients c JOIN Books b ON b.bookType.id = c.id" +
            " WHERE c.first_name = ?1 and c.last_name = ?2 and c.pather_name = ?3")
    List<ClientsAndTheirBooks> joinClientsAndTheirBooksWhereFullNameEquals(
            String firstName, String lastName, String patherName
    );

    @Query(value = "SELECT new Application.utils.ClientsAndTheirBooks(c.first_name, c.last_name, c.pather_name, b.name)" +
            " FROM Clients c JOIN Books b ON b.bookType.id = c.id" +
            " WHERE c.passport_seria = ?1 and c.passport_number = ?2")
    List<ClientsAndTheirBooks> joinClientsAndTheirBooksWherePassportEquals(
            String passportSeria, String passportNumber
    );

}
