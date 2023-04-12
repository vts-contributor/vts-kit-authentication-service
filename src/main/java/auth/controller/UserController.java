package auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/user")
public class UserController {

    @GetMapping
    public ResponseEntity<String> hello() {
        return ResponseEntity.ok("This is user role");
    }

}
