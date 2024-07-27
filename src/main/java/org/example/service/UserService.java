package org.example.service;

import org.example.dao.UserDao;
import org.example.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;
    private final PasswordEncoder passwordEncoder;

    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public boolean register(String username, String password) {
        if (userDao.usernameExist(username)) {
            return false;
        }
        User user = new User();
        user.setUsername(username);
        user.setPasswordHash(passwordEncoder.encode(password));
        user.setRegistrationDate(new Date());
        userDao.saveUser(user);
        return true;
    }

    @Transactional
    public boolean login(String username, String password) {
        User user = userDao.findByUsername(username);
        if (user == null) {
            return false;
        }
        return passwordEncoder.matches(password, user.getPasswordHash());
    }

    @Transactional
    public void updateUser(String username, String password) {
        User currentUser = getCurrentUser();
        currentUser.setUsername(username);
        currentUser.setPasswordHash(passwordEncoder.encode(password));
        userDao.saveUser(currentUser);
    }

    @Transactional
    public void deleteCurrentUser() {
        User currentUser = getCurrentUser();
        userDao.deleteUser(currentUser);
    }

    public User getCurrentUser() {
        String currentUsername = SecurityContextHolder.getContext().getAuthentication().getName();
        return userDao.findByUsername(currentUsername);
    }
}
