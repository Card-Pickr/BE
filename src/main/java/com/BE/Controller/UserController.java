package com.BE.Controller;

import com.BE.Common.LoginMethod;
import com.BE.Dto.LoginRequest;
import com.BE.Entity.UserEntity;
import com.BE.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody UserEntity userEntity) {
        // 이메일 중복 확인
        if (userService.findByEmail(userEntity.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body("Email is already registered");
        }
        userEntity.setLoginMethod(LoginMethod.LOCAL); // 자체 로그인은 항상 LOCAL
        userService.registerUser(userEntity);
        return ResponseEntity.ok("User registered successfully");
    }
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody LoginRequest loginRequest) {
        boolean isValid = userService.validateLogin(loginRequest.getEmail(), loginRequest.getPassword());
        if (isValid) {
            return ResponseEntity.ok("Login successful");
        }
        return ResponseEntity.status(401).body("Invalid email or password");
    }

//    @PostMapping("/login")
//    public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password) {
//        boolean isValid = userService.validateLogin(email, password);
//        if (isValid) {
//            return ResponseEntity.ok("Login successful");
//        }
//        return ResponseEntity.status(401).body("Invalid email or password");
//    }

    //외부 로그인 (지금은 x)
//    @GetMapping("/oauth2/success")
//    public ResponseEntity<String> oauth2Success() {
//        return ResponseEntity.ok("OAuth2 login successful");
//    }
}
