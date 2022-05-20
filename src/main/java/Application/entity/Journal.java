package Application.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name="Journal")
public class Journal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "book_id",
            referencedColumnName = "id"
    )
    private Books book;



    @ManyToOne(cascade = CascadeType.MERGE)
    @JoinColumn(
            name = "client_id",
            referencedColumnName = "id"
    )
    private Clients client;

    @Column(name = "data_beg", nullable = false)
    private Timestamp data_beg;

    @Column(name = "data_end", nullable = false)
    private Timestamp data_end;

    @Column(name = "data_ret", nullable = false)
    private Timestamp data_ret;

    public Journal() {

    }

    public Journal(Books book, Clients client, Timestamp data_beg, Timestamp data_end, Timestamp data_ret) {
        this.book = book;
        this.client = client;
        this.data_beg = data_beg;
        this.data_end = data_end;
        this.data_ret = data_ret;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getData_beg() {
        return data_beg;
    }

    public void setData_beg(Timestamp data_beg) {
        this.data_beg = data_beg;
    }

    public Timestamp getData_end() {
        return data_end;
    }

    public void setData_end(Timestamp data_end) {
        this.data_end = data_end;
    }

    public Timestamp getData_ret() {
        return data_ret;
    }

    public void setData_ret(Timestamp data_ret) {
        this.data_ret = data_ret;
    }

    public Books getBook() {
        return book;
    }

    public void setBook(Books book) {
        this.book = book;
    }

    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    @Override
    public String toString() {
        return "Journal{" +
                "id=" + id +
                ", data_beg=" + data_beg +
                ", data_end=" + data_end +
                ", data_ret=" + data_ret +
                '}';
    }
}
