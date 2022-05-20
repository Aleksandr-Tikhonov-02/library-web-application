package Application.controller;

import Application.repository.UserRepository;
import Application.security.jwt.JwtTokenProvider;
import Application.utils.AuthRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final AuthenticationManager authenticationManager;

    private final JwtTokenProvider jwtTokenProvider;

    private final UserRepository userRepository;

    public AuthController(AuthenticationManager authenticationManager, JwtTokenProvider jwtTokenProvider, UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.jwtTokenProvider = jwtTokenProvider;
        this.userRepository = userRepository;
    }

    @PostMapping("/sign-in")
    public ResponseEntity<Map<Object, Object>> signIn(@RequestBody AuthRequest request) {
        try {
            String name = request.getUserName();
            String token = jwtTokenProvider.createToken(
                    name,
                    userRepository.findUserByUserName(name).orElseThrow(
                            ()-> new UsernameNotFoundException("User not found")).getRoles()
            );
            Map<Object, Object> model = new HashMap<>();
            model.put("userName", name);
            model.put("token", token);
            return ResponseEntity.ok(model);
        } catch (AuthenticationException e) {
            throw  new BadCredentialsException("Invalid username or password");
        }
    }
}
