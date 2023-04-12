package auth.controller;

import auth.request.LoginFormRequest;
import auth.request.SignUpFormRequest;
import auth.response.MessageResponse;
import auth.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/auth/")
public class AccountController {
    
    private final UserService userService;

    public AccountController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping(value = "/register")
    public ResponseEntity<MessageResponse> register(@Validated @RequestBody SignUpFormRequest signUpFormRequest) {
        return ResponseEntity.ok(userService.registerWithUserRole(signUpFormRequest));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<MessageResponse> login(@Validated @RequestBody LoginFormRequest loginFormRequest) {
        return ResponseEntity.ok(userService.login(loginFormRequest));
    }
}
