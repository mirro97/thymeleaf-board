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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userIdx;

    @Size(max = 50)
    String id;

    @Size(max = 100)
    String pw;

    @DateTimeFormat(pattern = "yyyy-MM-dd hh:mm:ss")
    Date regdate;

    @Size(max = 50)
    String nickname;

    @Size(max = 1000)
    String introduce;

    @Size(max = 50)
    String imageUrl;
}
