package com.example.springboardproject.Domain.Repository;

import com.example.springboardproject.Domain.Entity.BoardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepository extends JpaRepository<BoardEntity,Long> {
}
