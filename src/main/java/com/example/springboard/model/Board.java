package com.example.springboard.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long boardId;

    @Size(max = 50)
    String writer;

    @Size(max = 50)
    String title;

    @Size(max = 1000)
    String content;

    @Temporal(TemporalType.TIMESTAMP)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @Column(columnDefinition = "timestamp default current_timestamp")
    Date regdate;

    @Size(max = 50)
    String type = "free";

    // 롬복의 빌더를 써야함
    @Builder
    public Board(Long boardId, String writer, String title, String content, Date regdate, String type) {
        this.boardId = boardId;
        this.writer = writer;
        this.title = title;
        this.content = content;
        this.regdate = regdate;
        this.type = type;
    }
}
