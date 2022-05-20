package jdbcTest;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try(Connection connection = JDBCUtils.getNewConnection()) {
            //JDBCUtils.createTables(connection);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
