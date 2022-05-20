package requestApplication.commands;

import com.google.gson.Gson;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import requestApplication.jsonObjects.JsonAuth;
import requestApplication.jsonObjects.JsonAuthResponse;

import java.util.Scanner;

public class AuthorizationCommands {

    private final static Gson gson = new Gson();
    private final static RestTemplate template = new RestTemplate();
    private final static HttpHeaders headers = new HttpHeaders();
    private final static StringBuilder url = new StringBuilder("http://localhost:8080/auth/");

    public static String authorize(Scanner scanner) {
        try {
            System.out.println("Enter user name");
            String user = scanner.next();
            System.out.println("Enter password");
            String password = scanner.next();
            JsonAuth jsonAuth = new JsonAuth(user, password);

            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<String> request = new HttpEntity<>(gson.toJson(jsonAuth), headers);

            ResponseEntity<String> response = template.exchange(
                    url.append("sign-in").toString(), HttpMethod.POST, request, String.class
            );

            JsonAuthResponse jsonAuthResponse = gson.fromJson(response.getBody(), JsonAuthResponse.class);
            //System.out.println("token = " + jsonAuthResponse.getToken());
            return jsonAuthResponse.getToken();
        } catch (Exception e) {
            System.err.println("Server error");
            return null;
        }
    }
}
