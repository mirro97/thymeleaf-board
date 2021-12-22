package com.example.springboard.repository;

import java.util.List;

import com.example.springboard.model.Board;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<Board, Long> {
    public Board findBoardByBoardId(Long boardId);

    public List<Board> findAllByOrderByRegdateDesc();

    public List<Board> findBoardByType(String type);
}
