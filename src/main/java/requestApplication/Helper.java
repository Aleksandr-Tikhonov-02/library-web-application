package requestApplication;

public class Helper {

    public static String getHelp() {
        return """
                Type "auth" to authorize.
                Type "help.book" to get commands for books.
                Type "help.bookTypes" to get commands for bookTypes.
                Type "help.client" to get commands for clients.
                Type "help.journal" to get commands for journal.
               """;
    }

    public static String getHelpBook() {
        return """
                Type "b.all" to get all books.
                Type "b.findById" to get book by id.
                Type "b.findByName" to get book by name.
                Type "b.joinBooksAndTypes" to get all books with their types.
                Type "b.joinBooksAndTypesFull" to get full ingo about books and their types.
                Type "b.add" to add book.
                Type "b.update" to update book.
                Type "b.delete" to delete book.
               """;
    }

    public static String getHelpBookType() {
        return """
                Type "bt.all" to get all book types.
                Type "bt.findById" to get book type by id.
                Type "bt.findByName" to get book type by name.
                Type "bt.add" to add book type.
                Type "bt.update" to update book type.
                Type "bt.delete" to delete book type.
               """;
    }

    public static String getHelpClient() {
        return """
                Type "c.all" to get all clients.
                Type "c.findById" to client by id.
                Type "c.findByName" to get client by name.
                Type "c.add" to add client.
                Type "c.update" to update client.
                Type "c.delete" to delete client.
               """;
    }

    public static String getHelpJournal() {
        return """
                Type "j.joinClientsAndBooks" to get clients and their books.
                Type "j.joinClientsAndBooksByName" to get client by name and his books.
                Type "j.joinClientsAndBooksByPassport" to get client by passport and his books.
                Type "j.add" to add journal mark.
                Type "j.update" to update journal mark.
                Type "j.delete" to delete journal mark.
               """;
    }
}
