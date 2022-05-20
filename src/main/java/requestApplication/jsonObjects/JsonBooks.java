package requestApplication.jsonObjects;


public class JsonBooks extends JsonObject {
    private Long id;
    private String name;
    private int count;
    private JsonBookTypes bookType;

    public JsonBooks(String name, int count, JsonBookTypes bookType) {
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

    public JsonBookTypes getBookType() {
        return bookType;
    }

    public void setBookType(JsonBookTypes bookType) {
        this.bookType = bookType;
    }
}
