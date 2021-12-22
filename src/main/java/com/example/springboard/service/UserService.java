package com.example.springboard.service;

import java.util.List;

import com.example.springboard.model.User;
import com.example.springboard.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public List<User> findListByIntroduceLike(String introduce) {
        List<User> userList = userRepository.findListByIntroduceLike("%" + introduce + "%");

        return userList;
    }
}
