package com.poliscrypts.tictactoekata.controller;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.dto.TurnDto;
import com.poliscrypts.tictactoekata.service.TicTacToeService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tictactoe")
@Tag(name = "Tic Tac Toe Controller", description = "Contains Endpoints to create a new game and play a turn")
public class TicTacToeController {

    private TicTacToeService ticTacToeService;

    public TicTacToeController(TicTacToeService ticTacToeService) {
        this.ticTacToeService = ticTacToeService;
    }
    @Operation(summary = "API for create a new game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "board created for the game",content = {@Content(mediaType = "application/json")})
    })
    @GetMapping("/create")
    public ResponseEntity<BoardDto> createNewGame() {

        BoardDto boardDto = ticTacToeService.createNewGame();

        return ResponseEntity.ok(boardDto);
    }
    @Operation(summary = "API for play a game")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "board created for the next game",content = {@Content(mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Board requested does not exist",
                    content = {@Content(mediaType = "application/json")}),
    })
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
        return ResponseEntity.ok(ticTacToeService.play(turnDto));
    }
}
