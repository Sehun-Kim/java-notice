package com.assignment.rsupport.noticejava.service;

import com.assignment.rsupport.noticejava.domain.user.User;
import com.assignment.rsupport.noticejava.domain.user.UserRepository;
import com.assignment.rsupport.noticejava.exception.UnAuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public User add(User user) {
        return userRepository.save(user);
    }

    public User login(String userId, String password) throws UnAuthenticationException {
        return userRepository.findByUserId(userId)
                .filter(u -> u.matchPassword(password))
                .orElseThrow(UnAuthenticationException::new);
    }
}
