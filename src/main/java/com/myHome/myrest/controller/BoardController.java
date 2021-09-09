package com.myHome.myrest.controller;

import com.myHome.myrest.model.Board;
import com.myHome.myrest.repository.BoardRepository;
import com.myHome.myrest.validator.BoardValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.validation.Valid;

@Controller
@RequestMapping("/board")
public class BoardController {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private BoardValidator boardValidator;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 2) Pageable pageable,
                       @RequestParam(required = false, defaultValue = "") String searchText) {
        // JPA는 page가 0부터 시작
        // Page<Board> boards = boardRepository.findAll(PageRequest.of(0, 2)); 페이지도 변수로 바꿔줌
        // Page<Board> boards = boardRepository.findAll(pageable);
        Page<Board> boards = boardRepository.findByTitleContainingOrContentContaining(searchText, searchText, pageable);
        int startPage = Math.max(1, boards.getPageable().getPageNumber() - 4) ;
        int endPage = Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4) == 0 ? 1 : Math.min(boards.getTotalPages(), boards.getPageable().getPageNumber() + 4);
        model.addAttribute("startPage", startPage);
        model.addAttribute("endPage", endPage);
        model.addAttribute("boards", boards);

        System.out.printf("board/list 호출, startPage: %d, endPage: %d, searchText: %s, totalPage: %d \n",startPage, endPage, searchText, boards.getTotalPages());
        return "board/list";
    }

    @GetMapping("/form")
    // @RequestParam(required = false) 필수인지 아닌지 체크, false 면 필요할 때만 사용하겠다는 뜻
    public String form(Model model, @RequestParam(required = false) Long id) {
        System.out.println("board/form 호출: "+id);
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
