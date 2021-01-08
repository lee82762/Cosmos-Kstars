package com.example.springboardproject.Domain.Entity;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@Entity(name="board")

@NoArgsConstructor
public class BoardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;
    private String writer;
    private String title;
    private String content;

    @Builder
    public BoardEntity(Long id, String title, String content, String writer) {
        this.id = id;
        this.writer = writer;
        this.title = title;
        this.content = content;
    }
}
