package requestApplication.jsonObjects;

public class JsonBookTypes extends JsonObject {
    private Long id;
    private String name;
    private int count;
    private double fine;
    private int day_count;

    public JsonBookTypes(String name, int count, double fine, int day_count) {
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

    public void setFine(double fine) {
        this.fine = fine;
    }

    public int getDay_count() {
        return day_count;
    }

    public void setDay_count(int day_count) {
        this.day_count = day_count;
    }

}
