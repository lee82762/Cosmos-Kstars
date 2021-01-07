package com.example.restfulwebservice.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/jpa")
public class UseJpaController {
    @Autowired
    private UserRepository userRepository;
    @Autowired //무조건 해줘야한다.
    private  PostRepository postRepository;

    @GetMapping("/users")
    @Transactional
    public List<User>retrieveAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping("/users/{id}")
    public  EntityModel<User> retrieveUser(@PathVariable int id){
       //userdata가 아니라 정보가 없을 수도 있기때문에 optional 사용
        Optional<User>user=userRepository.findById(id);
        //dsd

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        EntityModel<User> model=new EntityModel<>(user.get());
        WebMvcLinkBuilder linkTo = linkTo(methodOn(this.getClass()).retrieveAllUsers());
        model.add(linkTo.withRel("all-users"));

        return model;
    }

    @DeleteMapping("/users/{id}")
    public void deleteUser(@PathVariable int id){
        userRepository.deleteById(id);
    }

    @PostMapping("/users")
    public ResponseEntity<User> creatUser(@Valid @RequestBody User user){
        User saveduser=userRepository.save(user);

        URI location=ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(saveduser.getId())
                .toUri();
        return ResponseEntity.created(location).build();
    }

    @GetMapping("/users/{id}/posts")
    @Transactional  //LAZY 방식으로 할때는
    public  List<Post> retrieveAllPostsByUser(@PathVariable int id){
        //그니까 내 말은 user들의 post 정보를 찾고싶은거잖아? 어어
        //userdata가 아니라 정보가 없을 수도 있기때문에 optional 사용
        //1. @Path를 이용해서 입력받은 id로 유저 정보 조회
        Optional<User> user = userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }

        List<Post> posts = user.get().getPosts();

        for(int i=0; i<posts.size(); i++) {
            int temp = posts.get(i).getId();
        }

        return posts;
    }

    @PostMapping("/users/{id}/posts")
    public ResponseEntity<Post> creatPost(@PathVariable int id, @RequestBody Post post){
        Optional<User>user=userRepository.findById(id);

        if(!user.isPresent()){
            throw new UserNotFoundException(String.format("ID[%s] not found",id));
        }
       // System.out.println(user.get().getId());
        //System.out.println(user.get());

        post.setUser(user.get());

        Post savedpost=postRepository.save(post);

        URI location=ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(savedpost.getId())
                .toUri();

        return ResponseEntity.created(location).build();
    }

}
