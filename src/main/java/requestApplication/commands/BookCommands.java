package requestApplication.commands;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import requestApplication.jsonObjects.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookCommands {
    private final static Gson gson = new Gson();
    private final static RestTemplate template = new RestTemplate();
    private final static HttpHeaders headers = new HttpHeaders();
    private final static String url = "http://localhost:8080/books";

    public static void getAll() {
        try {
            ResponseEntity<String> response = template.getForEntity(url+"/all", String.class);
            System.out.println(response.getBody());
        } catch(Exception exception) {
            System.err.println("Server error");
        }
    }

    public static void findById(Scanner scanner) {
        try {
            System.out.println("Enter id");
            int id = 0;
            try {
                id = scanner.nextInt();
            } catch (InputMismatchException exception) {
                System.err.println("Incorrect type");
                scanner.next();
                return;
            }
            ResponseEntity<String> response = template.getForEntity(url + "/" + id, String.class);
            System.out.println(response.getBody());
        } catch(Exception exception) {
            System.err.println("Server error or no such id");
        }
    }

    public static void findByName(Scanner scanner) {
        try {
            System.out.println("Enter name of book");
            String name = scanner.next();
            ResponseEntity<String> response = template.getForEntity(url+"?name=" + name, String.class);
            System.out.println(response.getBody());
        } catch(Exception exception) {
            System.err.println("Server error or no such book with that name");
        }
    }

    public static void joinBooksAndTypes() {
        try {
            ResponseEntity<String> response = template.getForEntity(url+"/joinBooksAndTypes", String.class);
            System.out.println(response.getBody());
        } catch(Exception exception) {
            System.err.println("Server error");
        }
    }

    public static void joinBooksAndTypesFull() {
        try {
            ResponseEntity<String> response = template.getForEntity(url+"/joinBooksAndTypesFull", String.class);
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
        JsonBooks book = getBook(scanner);
        if (book == null) {
            return;
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(book), headers);
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
        JsonBooks book = getBook(scanner);
        if (book == null) {
            return;
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(book), headers);
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

    private static JsonBooks getBook(Scanner scanner) {
        System.out.println("Enter name of book");
        String name = scanner.next();
        int count;
        try {
            System.out.println("Enter count of book");
            count = scanner.nextInt();
        } catch (InputMismatchException e) {
            System.err.println("Incorrect type");
            scanner.next();
            return null;
        }
        System.out.println("Enter name of book type");
        String bookTypeName = scanner.next();
        String responseBody;
        try {
            String urlForBookType = "http://localhost:8080/book-types?name=" + bookTypeName;
            ResponseEntity<String> response = template.getForEntity(urlForBookType, String.class);
            responseBody = response.getBody();
            if (responseBody != null) {
                responseBody = responseBody.substring(1, responseBody.length() - 1);
            }
        } catch (Exception e) {
            System.err.println("There is no such book type");
            return null;
        }
        return new JsonBooks(name, count, gson.fromJson(responseBody, JsonBookTypes.class));
    }
}
