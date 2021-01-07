package com.example.restfulwebservice.User;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
//@JsonIgnoreProperties(value ={"password"} )
@NoArgsConstructor//default 생성자 생성
//@JsonFilter("UserInfo")
//@ApiModel(description = "사용자 상세정보를 위한 도메인 객체")
@Entity(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Size(min=2, message = "Name은 2글자 이상 입력해 주세요.")
    private String name;
    @Past
    private Date joinDate;
    private String password;
    private String ssn;


   //EAGER을 사용하면 사용자 조회가 가능하나 lAZY를 사용할 경우 에러
    @OneToMany( mappedBy = "user" ,fetch = FetchType.LAZY)
    private List<Post> posts;

    public User(int id, String name, Date joinDate, String password, String ssn) {
        this.id=id;
        this.name=name;
        this.joinDate=joinDate;
        this.password=password;
        this.ssn=ssn;
    }
}
