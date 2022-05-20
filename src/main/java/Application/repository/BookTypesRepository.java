package Application.repository;


import Application.entity.BookTypes;
import Application.utils.BooksAndCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface BookTypesRepository extends JpaRepository<BookTypes, Long> {

    List<BookTypes> findByName(String name);

    List<BookTypes> findBookTypesByCountEquals(int count);

    List<BookTypes> findBookTypesByFineIsGreaterThan(double count);

    List<BookTypes> findBookTypesByNameContains(String str);


    @Modifying
    @Transactional
    @Query(value = "UPDATE book_types SET name = ?1, count = ?2, fine = ?3, day_count = ?4 " +
            "WHERE id = ?5", nativeQuery = true)
    int updateById(String name, int count, double fine, int day_count, long id);



    @Query(value = "SELECT new Application.utils.BooksAndCount(b.name, b.count) FROM Books b " +
            "JOIN BookTypes bt ON bt.id = b.bookType.id " +
            "WHERE bt.name = ?1")
    List<BooksAndCount> joinBooksByBookType(String name);

    @Query(value = "DELETE FROM book_types WHERE id = ?1", nativeQuery = true)
    void deleteById(int id);


}
