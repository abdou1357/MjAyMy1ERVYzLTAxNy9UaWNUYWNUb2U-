package com.poliscrypts.tictactoekata.controller;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.dto.TurnDto;
import com.poliscrypts.tictactoekata.service.TicTacToeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tictactoe")
public class TicTacToeController {
    private static final Logger logger = LoggerFactory.getLogger(TicTacToeController.class);

    private TicTacToeService ticTacToeService;

    public TicTacToeController(TicTacToeService ticTacToeService) {
        this.ticTacToeService = ticTacToeService;
    }

    @GetMapping("/create")
    public ResponseEntity<BoardDto> createNewGame() {

        BoardDto boardDto = ticTacToeService.createNewGame();

        return ResponseEntity.ok(boardDto);
    }

    @PostMapping("/play")
    public ResponseEntity<BoardDto> playGame(@RequestBody TurnDto turnDto) {
        if (turnDto.getRow() < 0 || turnDto.getRow() > 2) {
            throw new IllegalArgumentException("Wrong row information, they should be between 0 and 2!");
        }
        if (turnDto.getCol() < 0 || turnDto.getCol() > 2) {
            throw new IllegalArgumentException("Wrong column information, they should be between 0 and 2!");
        }
        if (!"X".equals(turnDto.getPlayer()) && !"O".equals(turnDto.getPlayer())) {
            throw new IllegalArgumentException("Wrong player name, it should be X or O.");
        }
        return ResponseEntity.ok(null);
    }
}
