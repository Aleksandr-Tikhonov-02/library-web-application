package jdbcTest;

import java.sql.*;

public class JDBCUtils {

    public static Connection getNewConnection() throws SQLException {
        String jdbcUrl = "jdbc:postgresql://localhost:5432/coursework";
        Connection connection = DriverManager.getConnection(jdbcUrl, "postgres", "21471972");

        if (connection.isValid(1)) {
            System.out.println("Connection successful");
        }
        return connection;
    }

    public static void createTables(Connection connection) throws SQLException {
        createTableBookTypes(connection);
        createTableClients(connection);
        createTableBooks(connection);
        createTableJournal(connection);
    }

    private static void createTableBookTypes(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE BOOK_TYPES (
                ID serial PRIMARY KEY,
                NAME varchar(50),
                CNT numeric,
                FINE numeric,
                DAY_COUNT numeric
            )""";
        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private static void createTableClients(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE CLIENTS (
                 ID serial PRIMARY KEY,
                 FIRST_NAME varchar(20),
                 LAST_NAME varchar(20),
                 PATHER_NAME varchar(20),
                 PASSPORT_SERIA varchar(20),
                 PASSPORT_NUM varchar(20)
             )""";
        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private static void createTableBooks(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE BOOKS (
                ID serial PRIMARY KEY,
                NAME varchar(50),
                CNT numeric,
                TYPE_ID integer references BOOK_TYPES(ID)
            )""";
        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }

    private static void createTableJournal(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE JOURNAL (
                 ID serial PRIMARY KEY,
                 BOOK_ID integer references BOOKS(ID),
                 CLIENT_ID integer references CLIENTS(ID),
                 DATE_BEG timestamptz,
                 DATE_END timestamptz,
                 DATE_RET timestamptz
             )""";
        try(Statement statement = connection.createStatement()) {
            statement.execute(sql);
        }
    }



    /*public static void insertToTableBooks(
            Connection connection, String name, int count, int type_id
    ) throws  SQLException {
        String sql = "INSERT INTO books(name, cnt, type_id)" + "VALUES(?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, count);
            statement.setInt(3, type_id);
            statement.execute();
        }
    }

    public static void insertToTableBookTypes(
            Connection connection, String name, int count, int fine, int day_count
    ) throws  SQLException {
        String sql = "INSERT INTO book_types(name, cnt, fine, day_count)" + "VALUES(?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, name);
            statement.setInt(2, count);
            statement.setInt(3, fine);
            statement.setInt(4, day_count);
            statement.execute();
        }
    }

    public static void insertToTableClients(
            Connection connection, String firstName,
            String lastName, String patherName,
            String passportSeria, String passportNum
    ) throws  SQLException {
        String sql = "INSERT INTO clients(FIRST_NAME, LAST_NAME, PATHER_NAME, PASSPORT_SERIA, PASSPORT_NUM)" +
                "VALUES(?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, firstName);
            statement.setString(2, lastName);
            statement.setString(3, patherName);
            statement.setString(4, passportSeria);
            statement.setString(5, passportNum);
            statement.execute();
        }
    }

    public static void insertToTableJournal(
            Connection connection, String book_id, String client_id,
            Timestamp date_beg, Timestamp date_end, Timestamp date_ret
    ) throws  SQLException {
        String sql = "INSERT INTO journal(book_id, client_id, date_beg, date_end, date_ret) " +
                "VALUES(?, ?, ?, ?, ?)";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, book_id);
            statement.setString(2, client_id);
            statement.setTimestamp(3, date_beg);
            statement.setTimestamp(4, date_end);
            statement.setTimestamp(5, date_ret);
            statement.execute();
        }
    }

    public static void deleteFromTableBooksById(Connection connection, int id) throws  SQLException {
        String sql = "DELETE FROM books WHERE  id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }

    public static void deleteFromTableBookTypesById(Connection connection, int id) throws  SQLException {
        String sql = "DELETE FROM book_types WHERE  id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }

    public static void deleteFromTableClientsById(Connection connection, int id) throws  SQLException {
        String sql = "DELETE FROM clients WHERE  id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }

    public static void deleteFromTableJournalById(Connection connection, int id) throws  SQLException {
        String sql = "DELETE FROM journal WHERE  id=?";
        try(PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);
            statement.execute();
        }
    }*/

}
