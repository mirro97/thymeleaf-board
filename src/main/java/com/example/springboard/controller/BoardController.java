package com.example.springboard.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

import com.example.springboard.model.Board;
import com.example.springboard.model.BoardTypeOrder;
import com.example.springboard.model.User;
import com.example.springboard.repository.BoardRepository;
import com.example.springboard.repository.BoardTypeOrderRepository;
import com.example.springboard.repository.UserRepository;
import com.example.springboard.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class BoardController {
    private String contentPath = "pages/board";
    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BoardRepository boardRepository;
    @Autowired
    BoardTypeOrderRepository boardTypeOrderRepository;

    @GetMapping(path = { "", "/dashboard" })
    public String index(Model model) {
        List<User> userList = userRepository.findAll();
        log.info(userList.toString());

        List<Board> boardList = boardRepository.findAllByOrderByRegdateDesc();

        List<BoardTypeOrder> typeOrderList = boardTypeOrderRepository.findAllByOrderByMenuOrderAsc();

        model.addAttribute("boardList", boardList);
        model.addAttribute("boardCount", boardList.size());
        model.addAttribute("typeOrderList", typeOrderList);

        return contentPath + "/index";
    }

    @GetMapping("/freeboard")
    public String getFreeboard(Model model) {
        List<Board> freeBoardList = boardRepository.findBoardByType("free");
        List<BoardTypeOrder> typeOrderList = boardTypeOrderRepository.findAllByOrderByMenuOrderAsc();

        model.addAttribute("boardList", freeBoardList);
        model.addAttribute("boardCount", freeBoardList.size());
        model.addAttribute("typeOrderList", typeOrderList);

        return contentPath + "/index";
    }

    @GetMapping("/dailyboard")
    public String getDailyboard(Model model) {
        List<Board> dailyBoardList = boardRepository.findBoardByType("daily");
        List<BoardTypeOrder> typeOrderList = boardTypeOrderRepository.findAllByOrderByMenuOrderAsc();

        model.addAttribute("boardList", dailyBoardList);
        model.addAttribute("boardCount", dailyBoardList.size());
        model.addAttribute("typeOrderList", typeOrderList);

        return contentPath + "/index";
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    class typelistbody {
        ArrayList<BoardTypeOrder> modifiedList;
    }

    @PostMapping(value = "/typeList", consumes = MediaType.APPLICATION_JSON_VALUE)
    public String changeTypeList(@RequestBody HashMap<String, Object> data) {
        try {
            ArrayList<?> list = (ArrayList<?>) data.get("modifiedList");
            ObjectMapper mapper = new ObjectMapper();
            ArrayList<BoardTypeOrder> orderList = new ArrayList<>();
            for (int i = 0; i < list.size(); ++i) {

                BoardTypeOrder boardTypeOrder = mapper.convertValue(list.get(i), BoardTypeOrder.class);
                Optional<BoardTypeOrder> searchOrder = boardTypeOrderRepository.findById(boardTypeOrder.getIdx());
                if (!searchOrder.isEmpty()) {
                    boardTypeOrder.setType(searchOrder.get().getType());
                    boardTypeOrderRepository.save(boardTypeOrder);
                }
                orderList.add(boardTypeOrder);
            }

            log.info(orderList.toString());
            // boardTypeOrderRepository.saveAll(orderList);
        } catch (Exception e) {
            // TODO: handle exception
            ;
            log.error(e.getMessage());
            e.printStackTrace();
        }
        // JSONPObject jObject = new JSONPObject("JSON.parse", data);

        // JSON

        // System.out.println(data);

        return contentPath + "/index";
    }

    // @GetMapping("/typeList")
    // public String updateTypeList() {
    // return contentPath + "/index";
    // }

    @GetMapping("/shareboard")
    public String getShareboard(Model model) {
        List<Board> shareBoardList = boardRepository.findBoardByType("share");
        List<BoardTypeOrder> typeOrderList = boardTypeOrderRepository.findAllByOrderByMenuOrderAsc();

        model.addAttribute("boardList", shareBoardList);
        model.addAttribute("boardCount", shareBoardList.size());
        model.addAttribute("typeOrderList", typeOrderList);

        return contentPath + "/index";
    }

    @GetMapping("/content")
    public String getContent(Model model,
            // RequetParam - 필수여부가 true라 반드시 필요한 경우 아니면 false 설정필요
            @RequestParam(required = false) Long boardId) {
        Board board = boardRepository.findBoardByBoardId(boardId);
        model.addAttribute("boardList", board);
        return contentPath + "/content";
    }

    @GetMapping("/new")
    public String moveWrite() {
        return contentPath + "/write";
    }

    // @PostMapping("/new")
    // public String writeContent(HttpServletRequest req, HttpServletResponse res)
    // throws Exception {
    // System.out.println("이거다!" + req.getParameter("title"));

    // return contentPath + "/write";
    // }

    @PostMapping("/new")
    public String writeContent(Board board) throws Exception {
        // log.info("board new:: " + board.toString());
        // Board entityBoard = Board.builder().content(board.getContent());
        if (board.getBoardId() == null) {
            board.setType("free");
            board.setWriter("chicken");
            board.setRegdate(new Date());
            boardRepository.save(board);
        }

        // Board.builder()
        // .title(board.getTitle())
        // .content(board.getContent())
        // .writer("chicken")
        // .regdate(new Date())
        // .build();
        return "redirect:" + "/";
    }

    @GetMapping("/findUser")
    public String test(User user) {
        List<User> userList1 = userRepository.findListByIntroduceLike("%" + user.getIntroduce() + "%");
        List<User> userList2 = userService.findListByIntroduceLike(user.getIntroduce());
        // userRepository.findListByIdAndNicknameAndIntroduce(user.getId(),
        // user.getNickname(), user.getIntroduce());
        // userRepository.findAll(Example.of(user));
        // userRepository.delete
        return contentPath + "/index";
    }
}
