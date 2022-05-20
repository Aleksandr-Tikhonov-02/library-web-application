package requestApplication;

import requestApplication.commands.*;

import java.util.Scanner;

public class RequestApplication {

    public static void main(String[] args) {

        try (Scanner scanner = new Scanner(System.in)) {

            System.out.println("Hi! This is program to interact with library. You can type \"help\" for getting commands" +
                    " or \"end\" for shut down the program.");

            String command;
            String token = null;

            while (true) {
                Thread.sleep(100);
                System.out.println("--------------------------------------------\nEnter command:");

                command = scanner.next();

                switch (command) {
                    case "end" -> System.exit(0);

                    case "help" -> System.out.println(Helper.getHelp());
                    case "help.book" -> System.out.println(Helper.getHelpBook());
                    case "help.bookTypes" -> System.out.println(Helper.getHelpBookType());
                    case "help.client" -> System.out.println(Helper.getHelpClient());
                    case "help.journal" -> System.out.println(Helper.getHelpJournal());

                    case "auth" -> {
                        token = AuthorizationCommands.authorize(scanner);
                        System.out.println("Successful authorization");
                    }

                    case "b.all" -> BookCommands.getAll();
                    case "b.findById" -> BookCommands.findById(scanner);
                    case "b.findByName" -> BookCommands.findByName(scanner);
                    case "b.joinBooksAndTypes" -> BookCommands.joinBooksAndTypes();
                    case "b.joinBooksAndTypesFull" -> BookCommands.joinBooksAndTypesFull();
                    case "b.add" -> BookCommands.add(scanner, token);
                    case "b.update" -> BookCommands.update(scanner, token);
                    case "b.delete" -> BookCommands.delete(scanner, token);

                    case "bt.all" -> BookTypeCommands.getAll();
                    case "bt.findById" -> BookTypeCommands.findById(scanner);
                    case "bt.findByName" -> BookTypeCommands.findByName(scanner);
                    case "bt.add" -> BookTypeCommands.add(scanner, token);
                    case "bt.update" -> BookTypeCommands.update(scanner, token);
                    case "bt.delete" -> BookTypeCommands.delete(scanner, token);

                    case "c.all" -> ClientCommands.getAll();
                    case "c.findById" -> ClientCommands.findById(scanner);
                    case "c.findByName" -> ClientCommands.findByName(scanner);
                    case "c.add" -> ClientCommands.add(scanner, token);
                    case "c.update" -> ClientCommands.update(scanner, token);
                    case "c.delete" -> ClientCommands.delete(scanner, token);

                    case "j.joinClientsAndBooks" -> JournalCommands.joinClientsAndBooks();
                    case "j.joinClientsAndBooksByName" -> JournalCommands.joinClientsAndBooksByName(scanner);
                    case "j.joinClientsAndBooksByPassport" -> JournalCommands.joinClientsAndBooksByPassport(scanner);
                    case "j.add" -> JournalCommands.add(scanner, token);
                    case "j.update" -> JournalCommands.update(scanner, token);
                    case "j.delete" -> JournalCommands.delete(scanner, token);

                    default -> System.out.println("Unknown command. Type \"help\" to get help.");
                }
            }
        } catch (Exception exception) {
            System.err.println(exception.getMessage());
        }

    }
}

