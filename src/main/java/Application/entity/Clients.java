package Application.entity;

import javax.persistence.*;

@Entity
@Table(name="Clients")
public class Clients {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String first_name;

    @Column(name = "last_name", nullable = false, length = 20)
    private String last_name;

    @Column(name = "pather_name", nullable = false, length = 20)
    private String pather_name;

    @Column(name = "passport_seria", nullable = false, length = 20)
    private String passport_seria;

    @Column(name = "passport_num", nullable = false, length = 20)
    private String passport_number;

    public Clients() {

    }

    public Clients(String first_name, String last_name, String pather_name,
                   String passport_seria, String passport_number
    ) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.pather_name = pather_name;
        this.passport_seria = passport_seria;
        this.passport_number = passport_number;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getPather_name() {
        return pather_name;
    }

    public void setPather_name(String pather_name) {
        this.pather_name = pather_name;
    }

    public String getPassport_seria(){
        return passport_seria;
    }

    public void setPassport_seria(String passport_seria) {
        this.passport_seria = passport_seria;
    }

    public String getPassport_number() {
        return passport_number;
    }

    public void setPassport_number(String passport_number) {
        this.passport_number = passport_number;
    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", first_name='" + first_name + '\'' +
                ", last_name='" + last_name + '\'' +
                ", pather_name='" + pather_name + '\'' +
                ", passport_seria='" + passport_seria + '\'' +
                ", passport_number='" + passport_number + '\'' +
                '}';
    }
}
