package Application.repository;


import Application.entity.Books;
import Application.utils.BooksAndBookTypesFull;
import Application.utils.BooksAndBookTypesLittle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Books, Long> {

    @Query(value = "SELECT * FROM books WHERE name = ?1",nativeQuery = true)
    List<Books> findByName(String name);

    List<Books> findBooksByCountGreaterThanEqual(int i);

    @Modifying
    @Transactional
    @Query(value = "UPDATE books SET name = ?1, count = ?2, type_id = ?3 " +
            "WHERE id = ?4", nativeQuery = true)
    int updateById(String name, int count, Long type_id, long id);



    @Query(value = "SELECT new Application.utils.BooksAndBookTypesLittle(b.name, bt.name) " +
            "FROM Books b JOIN b.bookType bt")
    List<BooksAndBookTypesLittle> joinBooksWithTypes();

    @Query(value = "SELECT new Application.utils.BooksAndBookTypesFull" +
            "(b.id, b.name, bt.name, bt.fine, bt.day_count, bt.count) " +
            "FROM Books b JOIN b.bookType bt")
    List<BooksAndBookTypesFull> joinBooksWithTypesFull();

}



