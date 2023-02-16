package com.poliscrypts.tictactoekata.service.impl;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.model.Board;
import com.poliscrypts.tictactoekata.model.enumeration.Square;
import com.poliscrypts.tictactoekata.repository.TicTacToeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TicTacToeServiceImplTest {
    @Mock
    private TicTacToeRepository ticTacToeRepository;
    @InjectMocks
    private TicTacToeServiceImpl ticTacToeServiceImpl;

    @Test
    public void should_create_new_game_successfully() {

        // Given
        Board board = new Board().toBuilder()
                .id(UUID.randomUUID())
                .nextPlayer(Square.BLANK)
                .endBoard(false)
                .build();

        // When,
        when(ticTacToeRepository.save(any())).thenReturn(board);
        BoardDto boardDto = ticTacToeServiceImpl.createNewGame();

        // Then
        assertNotNull(boardDto);
        assertNotNull(boardDto.getId());
        assertFalse(boardDto.isEndBoard());
        assertEquals("X can start the game", boardDto.getNextPlayer());
    }
}