package requestApplication.commands;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import requestApplication.jsonObjects.JsonBookTypes;

import java.util.InputMismatchException;
import java.util.Scanner;

public class BookTypeCommands {
    private final static Gson gson = new Gson();
    private final static RestTemplate template = new RestTemplate();
    private final static HttpHeaders headers = new HttpHeaders();
    private final static String url = "http://localhost:8080/book-types";

    public static void getAll() {
        try {
            ResponseEntity<String> response = template.getForEntity(url + "/all", String.class);
            System.out.println(response.getBody());
        } catch (Exception exception) {
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
                scanner.next();
                System.err.println("Incorrect type");
                return;
            }
            ResponseEntity<String> response = template.getForEntity(url + "/" + id, String.class);
            System.out.println(response.getBody());
        } catch (Exception exception) {
            System.err.println("Server error or no such id");
        }
    }

    public static void findByName(Scanner scanner) {
        try {
            System.out.println("Enter name of book");
            String name = scanner.next();
            ResponseEntity<String> response = template.getForEntity(url + "?name=" + name, String.class);
            System.out.println(response.getBody());
        } catch (Exception exception) {
            System.err.println("Server error or no such book type with that name");
        }
    }

    public static void add(Scanner scanner, String token) {
        if (token == null) {
            System.out.println("You should authorize");
            return;
        }
        JsonBookTypes bookType = getBookType(scanner);
        if (bookType == null) {
            return;
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(bookType), headers);
        try {
            template.exchange(
                    url + "/new", HttpMethod.POST, request, String.class
            );
            System.out.println("Successful post");
        } catch (Exception exception) {
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
        JsonBookTypes bookType = getBookType(scanner);
        if (bookType == null) {
            return;
        }
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(bookType), headers);
        try {
            template.exchange(
                    url + "/update/" + id, HttpMethod.PUT, request, String.class
            );
            System.out.println("Successful put");
        } catch (Exception exception) {
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
        } catch (Exception e) {
            System.err.println("Server error or token expired");
        }
    }

    private static JsonBookTypes getBookType(Scanner scanner) {
        System.out.println("Enter name of book type");
        String bookTypeName = scanner.next();
        int countOfBookTypes;
        double fine;
        int day_count;
        try {
            System.out.println("Enter count of book types");
            countOfBookTypes = scanner.nextInt();
            System.out.println("Enter fine");
            fine = scanner.nextDouble();
            System.out.println("Enter day_count");
            day_count = scanner.nextInt();
        } catch (Exception e) {
            System.err.println("Incorrect type");
            return null;
        }
        return new JsonBookTypes(
                bookTypeName, countOfBookTypes, fine, day_count
        );
    }
}
