package com.example.Welcome.controller;

import com.example.Welcome.model.User;
import com.example.Welcome.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(AuthController.class); // 🔥 로그 추가

    public AuthController(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // 로그인 페이지로 이동
    @GetMapping("/login")
    public String showLoginPage() {
        return "login"; // login.html을 반환
    }

    // 회원가입 페이지로 이동
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register"; // register.html을 반환
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password) {
        logger.info("🔥 로그인 시도 - username: {}", username);

        Optional<User> userOptional = userRepository.findByUsername(username);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (passwordEncoder.matches(password, user.getPassword())) {
                logger.info("✅ 로그인 성공 - username: {}", username);
                return "redirect:/welcome";
            } else {
                logger.warn("❌ 로그인 실패 - 비밀번호 불일치");
            }
        } else {
            logger.warn("❌ 로그인 실패 - 존재하지 않는 사용자");
        }
        return "redirect:/login?error=true";
    }
}
