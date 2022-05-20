package Application.entity;

import javax.persistence.*;

@Entity
@Table(name="Books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "count", nullable = false)
    private int count;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "type_id",
            referencedColumnName = "id"
    )
    private BookTypes bookType;

    public Books() {

    }

    public Books(String name, int count, BookTypes bookType) {
        this.name = name;
        this.count = count;
        this.bookType = bookType;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public BookTypes getBookType() {
        return bookType;
    }

    public void setBookType(BookTypes bookType) {
        this.bookType = bookType;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                '}';
    }
}
