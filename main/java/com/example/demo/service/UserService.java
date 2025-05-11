package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 회원가입
    public User registerUser(String name, String email, String password) {
        try {
            if (userRepository.findByEmail(email) != null) {
                throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
            }
            User user = new User(name, email, password);
            return userRepository.save(user);
        } catch (Exception e) {
            e.printStackTrace();  // 로그 출력 추가
            throw new RuntimeException("회원가입 중 문제가 발생했습니다.");
        }
    }

    // 로그인
    public User authenticateUser(String email, String password) {
        User user = userRepository.findByEmail(email);
        if (user == null || !user.getPassword().equals(password)) {
            throw new IllegalArgumentException("이메일 또는 비밀번호가 잘못되었습니다.");
        }
        return user;
    }
}