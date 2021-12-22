package com.example.springboard.controller;

import java.util.List;

import com.example.springboard.model.Board;
import com.example.springboard.model.User;
import com.example.springboard.repository.BoardRepository;
import com.example.springboard.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserRepository userRepository;

    @Autowired
    BoardRepository boardRepository;

    @GetMapping("/list")
    public ModelAndView getUserInfo() {
        ModelAndView view = new ModelAndView("index");
        List<User> userList = userRepository.findAll();

        List<Board> boardList = boardRepository.findAll();
        view.addObject("userList", userList);
        view.addObject("boardList", boardList);
        // return findUser;
        return view;
    }

    // @PostMapping("/Update")
    @GetMapping("/update")
    public ModelAndView updateUserInfo(User user) {
        ModelAndView view = new ModelAndView("pages/test");
        User updateUser = userRepository.save(user);
        // view.addObject("userInfo", updateUser);
        view.addObject("message", "수정이");
        return view;
    }

}
