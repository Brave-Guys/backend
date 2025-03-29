package com.example.Welcome.service;

import com.example.Welcome.model.User;
import com.example.Welcome.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private static final Logger logger = LoggerFactory.getLogger(UserService.class); // 🔥 로그 추가

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void registerUser(String username, String password, String email) {
        // 비밀번호 암호화 후 저장
        String encodedPassword = passwordEncoder.encode(password);
        logger.info("🔥 회원가입 요청 - username: {}, encodedPassword: {}, email: {}", username, encodedPassword, email);

        User user = new User(username, encodedPassword, email, "USER");
        userRepository.save(user);
        logger.info("✅ 회원가입 완료 - username: {}", username);
    }
}
