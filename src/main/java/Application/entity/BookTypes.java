package Application.entity;

import javax.persistence.*;

@Entity
@Table(name="Book_types")
public class BookTypes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "count", nullable = false)
    private int count;

    @Column(name = "fine", nullable = false, precision = 18, scale = 2)
    private double fine;

    @Column(name = "day_count", nullable = false)
    private int day_count;

    public BookTypes() {

    }

    public BookTypes(String name, int count, double fine, int day_count) {
        this.name = name;
        this.count = count;
        this.fine = fine;
        this.day_count = day_count;
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

    public double getFine() {
        return fine;
    }

    public void setFine(int fine) {
        this.fine = fine;
    }

    public int getDay_count() {
        return day_count;
    }

    public void setDay_count(int day_count) {
        this.day_count = day_count;
    }

    @Override
    public String toString() {
        return "Books{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", count=" + count +
                ", fine=" + fine +
                ", day_count=" + day_count +
                '}';
    }
}
