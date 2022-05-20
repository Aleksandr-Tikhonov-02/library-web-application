package Application.utils;

public class BooksAndBookTypesLittle {
    private String book;
    private String bookType;

    public BooksAndBookTypesLittle(String book, String bookType) {
        this.book = book;
        this.bookType = bookType;
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

    @Override
    public String toString() {
        return "BooksAndBooksTypes{" +
                "book='" + book + '\'' +
                ", bookType='" + bookType + '\'' +
                '}';
    }
}
