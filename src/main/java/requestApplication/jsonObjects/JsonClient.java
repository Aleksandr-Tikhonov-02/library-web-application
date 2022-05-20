package requestApplication.jsonObjects;

public class JsonClient extends JsonObject {
    private Long id;
    private String first_name;
    private String last_name;
    private String pather_name;
    private String passport_seria;
    private String passport_number;



    public JsonClient(String first_name, String last_name, String pather_name, String passport_seria, String passport_number) {
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

    public String getPassport_seria() {
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
}
