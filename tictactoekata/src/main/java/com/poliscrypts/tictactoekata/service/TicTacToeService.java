package com.poliscrypts.tictactoekata.service;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.dto.TurnDto;

public interface TicTacToeService {
    BoardDto createNewGame();

    BoardDto play(TurnDto turnDto);
}
