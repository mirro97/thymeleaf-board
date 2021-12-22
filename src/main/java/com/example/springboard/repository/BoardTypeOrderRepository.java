package com.example.springboard.repository;

import java.util.List;

import com.example.springboard.model.BoardTypeOrder;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardTypeOrderRepository extends JpaRepository<BoardTypeOrder, Long> {
    public List<BoardTypeOrder> findAllByOrderByMenuOrderAsc();
}
