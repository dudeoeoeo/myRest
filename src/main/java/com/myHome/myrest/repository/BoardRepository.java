package com.myHome.myrest.repository;

import com.myHome.myrest.model.Board;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BoardRepository extends JpaRepository<Board, Long> {

    List<Board> findByTitle(String title);
    List<Board> findByTitleOrContent(String title, String content);

    // 제목 또는 내용으로 검색, 페이지도 같이 받음
    Page<Board> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);

}
