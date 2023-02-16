package com.poliscrypts.tictactoekata.mapper;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.model.Board;
import org.junit.jupiter.api.Test;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

class BoardMapperTest {
    private static final BoardMapper boardMapper = new BoardMapper();
    @Test
    void  shouldMapBoardToBoardDto(){

        // Given
        Board board = new Board().toBuilder()
                .id(UUID.randomUUID())
                .build();

        // When
        BoardDto boardDto = boardMapper.apply(board);

        // Then
        assertNotNull(boardDto);
        assertNotNull(boardDto.getId());
        assertFalse(boardDto.isEndBoard());
        assertEquals(board.getId(),boardDto.getId());
        assertEquals("X can start the game", boardDto.getNextPlayer());
    }

}