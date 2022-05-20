package Application.repository;


import Application.entity.Clients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface ClientsRepository extends JpaRepository<Clients, Long> {

    @Query(value = "INSERT INTO clients(first_name, last_name, passport_num, " +
            "passport_seria, pather_name) values(?1, ?2, ?3, ?4, ?5)", nativeQuery = true)
    Clients saveClient(String first_name, String last_name, String pather_name,
                       String passport_seria, String passport_number);

    @Query(value = "SELECT * FROM clients WHERE first_name = ?1 and " +
            "last_name = ?2 and pather_name = ?3 ", nativeQuery = true)
    List<Clients> findByFullName(String firstName, String lastName, String patherName);

    @Query(value = "SELECT * FROM clients WHERE passport_seria = ?1 and passport_num = ?2", nativeQuery = true)
    List<Clients> findByPassport(String passport_seria, String passport_number);

    @Query(value = "DELETE FROM clients WHERE id = ?1", nativeQuery = true)
    void deleteById(int id);


    @Modifying
    @Transactional
    @Query(value = "UPDATE clients SET first_name = ?1, last_name = ?2, pather_name = ?3," +
            "passport_seria = ?4, passport_num = ?5 WHERE id = ?6", nativeQuery = true)
    int updateById(String firstName, String lastName, String patherName,
                   String passport_seria, String passport_number, long id);

}
