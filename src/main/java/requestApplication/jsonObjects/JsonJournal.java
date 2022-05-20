package requestApplication.jsonObjects;

public class JsonJournal extends JsonObject {
    private Long id;
    private JsonBooks book;
    private JsonClient client;
    private String data_beg;
    private String data_end;
    private String data_ret;

    public JsonJournal(JsonBooks book, JsonClient client, String data_beg, String data_end, String data_ret) {
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

    public JsonBooks getBook() {
        return book;
    }

    public void setBook(JsonBooks book) {
        this.book = book;
    }

    public JsonClient getClient() {
        return client;
    }

    public void setClient(JsonClient client) {
        this.client = client;
    }

    public String getData_beg() {
        return data_beg;
    }

    public void setData_beg(String data_beg) {
        this.data_beg = data_beg;
    }

    public String getData_end() {
        return data_end;
    }

    public void setData_end(String data_end) {
        this.data_end = data_end;
    }

    public String getData_ret() {
        return data_ret;
    }

    public void setData_ret(String data_ret) {
        this.data_ret = data_ret;
    }
}
