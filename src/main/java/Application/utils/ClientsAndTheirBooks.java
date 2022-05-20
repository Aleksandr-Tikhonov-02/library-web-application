package Application.utils;

public class ClientsAndTheirBooks {
    private String firstName;
    private String lastName;
    private String patherName;
    private String bookName;

    public ClientsAndTheirBooks(String firstName, String lastName, String patherName, String bookName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.patherName = patherName;
        this.bookName = bookName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPatherName() {
        return patherName;
    }

    public void setPatherName(String patherName) {
        this.patherName = patherName;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "ClientsAndTheirBooks{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", patherName='" + patherName + '\'' +
                ", bookName='" + bookName + '\'' +
                '}';
    }
}
