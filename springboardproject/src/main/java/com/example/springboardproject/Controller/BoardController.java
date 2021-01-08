package com.example.springboardproject.Controller;

import com.example.springboardproject.Dto.BoardDto;
import com.example.springboardproject.Service.BoardService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class BoardController {
    @Autowired
    private BoardService boardService;

  //@RestController 사용시
 /*   @RequestMapping("/")
    public ModelAndView list(){
        ModelAndView modelAndView =new ModelAndView();
        modelAndView.setViewName("board/list");
        return  modelAndView;
    }*/
 @GetMapping("/")
  public String list(){
      return "board/list.html";
  }


    @GetMapping("/post")
    public String write(){
        return "board/write.html";
    }

    @PostMapping("/post")
    public String write1(BoardDto boardDto){
         boardService.savePost(boardDto);
        return "redirect:/";
    }
}
