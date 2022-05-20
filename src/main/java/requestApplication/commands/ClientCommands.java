package requestApplication.commands;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import requestApplication.jsonObjects.JsonClient;

import java.util.InputMismatchException;
import java.util.Scanner;

public class ClientCommands {
    private final static Gson gson = new Gson();
    private final static RestTemplate template = new RestTemplate();
    private final static HttpHeaders headers = new HttpHeaders();
    private final static String url = "http://localhost:8080/clients";

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
                scanner.next();
                System.err.println("Incorrect type");
                return;
            }
            ResponseEntity<String> response = template.getForEntity(url+"/" + id, String.class);
            System.out.println(response.getBody());
        } catch(Exception exception) {
            System.err.println("Server error or no such id");
        }
    }

    public static void findByName(Scanner scanner) {
        try {
            System.out.println("Enter first_name");
            String first_name = scanner.next();
            System.out.println("Enter last_name");
            String last_name = scanner.next();
            System.out.println("Enter pather_name");
            String pather_name = scanner.next();
            ResponseEntity<String> response = template.getForEntity(url+
                    "?first-name=" + first_name +
                    "&second-name=" + last_name +
                    "&pather-name=" + pather_name, String.class);
            System.out.println(response.getBody());
        } catch(Exception exception) {
            System.err.println("Server error or no such client with that name");
        }
    }

    public static void add(Scanner scanner, String token) {
        if (token == null) {
            System.out.println("You should authorize");
            return;
        }
        JsonClient client = getClient(scanner);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(client), headers);
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
        JsonClient client = getClient(scanner);
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(token);
        HttpEntity<String> request = new HttpEntity<>(gson.toJson(client), headers);
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

    private static JsonClient getClient(Scanner scanner) {
        System.out.println("Enter first_name");
        String first_name = scanner.next();
        System.out.println("Enter last_name");
        String last_name = scanner.next();
        System.out.println("Enter pather_name");
        String pather_name = scanner.next();
        System.out.println("Enter passport_seria");
        String passport_seria = scanner.next();
        System.out.println("Enter passport_number");
        String passport_number = scanner.next();
        return new JsonClient(first_name,last_name, pather_name, passport_seria, passport_number);
    }
}
