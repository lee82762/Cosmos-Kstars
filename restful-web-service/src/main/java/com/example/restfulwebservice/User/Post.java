package com.example.restfulwebservice.User;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "post")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String description;

    //User : Post ->1:(0~N) main:sub -> parent:child

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private User user;
}
