package com.myHome.myrest.service;

import com.myHome.myrest.model.Board;
import com.myHome.myrest.model.User;
import com.myHome.myrest.repository.BoardRepository;
import com.myHome.myrest.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private UserRepository userRepository;

    public Board save(Board board, String username) {
        User user = userRepository.findByUsername(username);
        board.setUser(user);
        return boardRepository.save(board);
    }
}
