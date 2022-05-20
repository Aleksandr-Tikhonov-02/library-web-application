package Application.utils;

public class BooksAndBookTypesFull {
    private Long id;
    private String book;
    private String bookType;
    private double fine;
    private int day_count;
    private int count;

    public BooksAndBookTypesFull(Long id, String book, String bookType, double fine, int day_count, int count) {
        this.id = id;
        this.book = book;
        this.bookType = bookType;
        this.fine = fine;
        this.day_count = day_count;
        this.count = count;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBook() {
        return book;
    }

    public void setBook(String book) {
        this.book = book;
    }

    public String getBookType() {
        return bookType;
    }

    public void setBookType(String bookType) {
        this.bookType = bookType;
    }

    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public int getDay_count() {
        return day_count;
    }

    public void setDay_count(int day_count) {
        this.day_count = day_count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public String toString() {
        return "BooksAndBookTypesFull{" +
                "id=" + id +
                ", book='" + book + '\'' +
                ", bookType='" + bookType + '\'' +
                ", fine=" + fine +
                ", day_count=" + day_count +
                ", count=" + count +
                '}';
    }
}
