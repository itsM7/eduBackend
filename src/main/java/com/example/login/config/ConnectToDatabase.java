//package com.example.login.config;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.springframework.stereotype.Component;
//
//import javax.annotation.PostConstruct;
//
//import com.example.login.db.repository.entity.User;
//import com.example.login.repository.UserRepository;
//
//@Component
//public class ConnectToDatabase {
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//
//    // פונקציה להוספת משתמשים לדוגמה לבסיס הנתונים
//    @PostConstruct
//    public void addSampleUsers() {
//        if (userRepository.count() == 0) {
//            User admin = new User();
//            admin.setEmail("admin@example.com");
//            admin.setPassword(passwordEncoder.encode("123"));
//
//            userRepository.save(admin);
//        }
//    }
//}
//
