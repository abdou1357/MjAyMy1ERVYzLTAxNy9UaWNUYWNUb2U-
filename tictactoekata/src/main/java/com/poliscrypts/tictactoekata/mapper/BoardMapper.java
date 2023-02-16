package com.poliscrypts.tictactoekata.mapper;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.model.Board;
import com.poliscrypts.tictactoekata.model.enumeration.Square;

import java.util.function.Function;

public class BoardMapper implements Function<Board, BoardDto> {

    public BoardDto apply(Board board) {
        String nextPlayer = board.getNextPlayer() == Square.BLANK ? "X can start the game" : String.format("%s Player", board.getNextPlayer());
        return BoardDto.builder()
                .id(board.getId())
                .endBoard(board.isEndBoard())
                .nextPlayer(nextPlayer)
                .build();
    }
}
