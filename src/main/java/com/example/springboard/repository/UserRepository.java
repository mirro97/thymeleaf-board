package com.example.springboard.repository;

import java.util.List;

import com.example.springboard.model.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    public User findById(String id);

    public List<User> findListById(String id);

    public List<User> findListByIntroduceLike(String introduce);

    public List<User> findListByIdAndNicknameAndIntroduce(String id, String nickname, String introduce);

}
