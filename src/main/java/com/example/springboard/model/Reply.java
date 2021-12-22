package com.example.springboard.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
@Entity
public class Reply {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long replyId;

    @Size(max = 11)
    Long boardId;

    @Size(max = 50)
    String writer;

    @Size(max = 1000)
    String content;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date regdate;

}
