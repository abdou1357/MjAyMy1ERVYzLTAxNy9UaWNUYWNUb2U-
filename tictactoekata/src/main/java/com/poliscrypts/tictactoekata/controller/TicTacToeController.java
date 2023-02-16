package com.poliscrypts.tictactoekata.controller;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.service.TicTacToeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tictactoe")
public class TicTacToeController {
    private static final Logger logger = LoggerFactory.getLogger(TicTacToeController.class);

    private TicTacToeService ticTacToeService;
    @GetMapping("/create")
    public ResponseEntity<BoardDto> createNewGame() {

        logger.info("Create new Tic Tac Toe Game.");

        return ResponseEntity.ok(null);
    }
}
