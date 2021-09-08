package com.myHome.myrest.controller;

import com.myHome.myrest.model.Board;
import com.myHome.myrest.repository.BoardRepository;
import com.myHome.myrest.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model) {
        List<Board> boards = boardRepository.findAll();
        model.addAttribute("boards", boards);
        return "board/list";
    }

    @GetMapping("/form")
    // @RequestParam(required = false) 필수인지 아닌지 체크, false 면 필요할 때만 사용하겠다는 뜻
    public String form(Model model, @RequestParam(required = false) Long id) {
        if(id != null) {
            Board board = boardRepository.findById(id).orElse(null);
            model.addAttribute("board", board);
        } else {
            model.addAttribute("board", new Board());
        }
        return "board/form";
    }

    @PostMapping("/form")
    public String greetingSubmit(@Valid Board board, BindingResult bindingResult) {
        boardValidator.validate(board, bindingResult);
        // save 시 Board 테이블에 이미 있는 id 라면 update 쿼리를 날림
        // 없는 id 라면 insert 쿼리를 날림
        if (bindingResult.hasErrors()) {
            return "board/form";
        }
        System.out.println("Post Form 호출: "+board.getTitle()+", "+board.getContent());
        boardRepository.save(board);
        return "redirect:/board/list";
    }
}
