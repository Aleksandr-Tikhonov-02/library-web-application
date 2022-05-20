package requestApplication.commands;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import requestApplication.jsonObjects.JsonBooks;
import requestApplication.jsonObjects.JsonClient;
import requestApplication.jsonObjects.JsonJournal;

import java.util.InputMismatchException;
import java.util.Scanner;

public class JournalCommands {

    private final static Gson gson = new Gson();
    private final static RestTemplate template = new RestTemplate();
    private final static HttpHeaders headers = new HttpHeaders();
    private final static String url = "http://localhost:8080/journal";

    public static void joinClientsAndBooks() {
        try {
            ResponseEntity<String> response = template.getForEntity(
                    url + "/joinClientsAndBooks", String.class
            );
            System.out.println(response.getBody());
        } catch(Exception exception) {
            System.err.println("Server error");
        }
    }

    public static void joinClientsAndBooksByName(Scanner scanner) {
        try {
            System.out.println("Enter first_name");
            String first_name = scanner.next();
            System.out.println("Enter last_name");
            String last_name = scanner.next();
            System.out.println("Enter pather_name");
            String pather_name = scanner.next();
            ResponseEntity<String> response = template.getForEntity(
                    url+ "/joinClientsAndBooksByName" +
                    "?first-name=" + first_name +
                    "&second-name=" + last_name +
                    "&pather-name=" + pather_name, String.class);
            System.out.println(response.getBody());
        } catch(Exception exception) {
            System.err.println("Server error");
        }
    }

    public static void joinClientsAndBooksByPassport(Scanner scanner) {
        try {
            System.out.println("Enter pass-num");
            String pass_num = scanner.next();
            System.out.println("Enter pass-ser");
            String pass_ser = scanner.next();
            ResponseEntity<String> response = template.getForEntity(url+
                    "?pass-num=" + pass_num +
                    "&pass-ser=" + pass_ser, String.class);
            System.out.println(response.getBody());
        } catch(Exception exception) {
            System.err.println("Server error");
        }

    }

    public static void add(Scanner scanner, String token) {
        if (token == null) {
            System.out.println("You should authorize");
            return;
        }
        JsonJournal journal = getJournal(scanner);
        if (journal == null) {
            return;
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(journal), headers);
        try {
            template.exchange(
                    url + "/new", HttpMethod.POST, request, String.class
            );
            System.out.println("Successful post");
        } catch(Exception exception) {
            System.err.println("Server error or token expired");
        }
    }

    public static void update(Scanner scanner, String token) {
        if (token == null) {
            System.out.println("You should authorize");
            return;
        }
        System.out.println("Enter id");
        int id = 0;
        try {
            id = scanner.nextInt();
        } catch (InputMismatchException exception) {
            scanner.next();
            System.err.println("Incorrect type");
            return;
        }
        JsonJournal journal = getJournal(scanner);
        if (journal == null) {
            return;
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(journal), headers);
        try {
            template.exchange(
                    url + "/update/" + id, HttpMethod.PUT, request, String.class
            );
            System.out.println("Successful put");
        } catch(Exception exception) {
            System.err.println("Server error or token expired");
        }
    }

    public static void delete(Scanner scanner, String token) {
        if (token == null) {
            System.out.println("You should authorize");
            return;
        }
        try {
            System.out.println("Enter id");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException exception) {
                scanner.next();
                System.err.println("Incorrect type");
                return;
            }
            headers.setContentType(MediaType.APPLICATION_JSON);
            headers.setBearerAuth(token);
            HttpEntity<?> request = new HttpEntity<>(headers);
            template.exchange(
                    url + "/delete/" + id, HttpMethod.DELETE, request, String.class
            );

            System.out.println("Successful delete");
        } catch(Exception e) {
            System.err.println("Server error or token expired");
        }
    }

    private static JsonJournal getJournal(Scanner scanner) {
        try {
            System.out.println("Enter name of book");
            String book = scanner.next();
            String baseUrl = "http://localhost:8080/books?name=" + book;
            ResponseEntity<String> responseBook = template.getForEntity(baseUrl, String.class);
            String responseBodyBook = responseBook.getBody();
            if (responseBodyBook != null) {
                responseBodyBook = responseBodyBook.substring(1, responseBodyBook.length() - 1);
            }
            System.out.println("Enter first_name of client");
            String first_name = scanner.next();
            System.out.println("Enter last_name of client");
            String last_name = scanner.next();
            System.out.println("Enter pather_name of client");
            String pather_name = scanner.next();
            baseUrl = "http://localhost:8080/clients?first_name=" + first_name +
                    "&last_name=" + last_name +
                    "&pather_name" + pather_name;
            ResponseEntity<String> responseClient = template.getForEntity(baseUrl, String.class);
            String responseBodyClient = responseClient.getBody();
            if (responseBodyClient != null) {
                responseBodyClient = responseBodyClient.substring(1, responseBodyClient.length() - 1);
            }
            System.out.println("Enter data_beg");
            String data_beg = scanner.next();
            System.out.println("Enter data_end");
            String data_end = scanner.next();
            System.out.println("Enter data_ret");
            String data_ret = scanner.next();
            return new JsonJournal(
                    gson.fromJson(responseBodyBook, JsonBooks.class),
                    gson.fromJson(responseBodyClient, JsonClient.class),
                    data_beg, data_end, data_ret
            );
        } catch(Exception e) {
            System.err.println("There is no such element");
            return null;
        }
    }
}
