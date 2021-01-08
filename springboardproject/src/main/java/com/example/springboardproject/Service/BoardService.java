package com.example.springboardproject.Service;

import com.example.springboardproject.Domain.Repository.BoardRepository;
import com.example.springboardproject.Dto.BoardDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@AllArgsConstructor
public class BoardService {
    private BoardRepository boardRepository;

    @Transactional
    public Long savePost(BoardDto boardDto){
        return boardRepository.save(boardDto.toEntity()).getId();
    }
}
