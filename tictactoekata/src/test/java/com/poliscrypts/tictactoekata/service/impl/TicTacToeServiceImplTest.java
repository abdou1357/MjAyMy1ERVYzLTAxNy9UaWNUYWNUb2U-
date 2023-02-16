package com.poliscrypts.tictactoekata.service.impl;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.dto.TurnDto;
import com.poliscrypts.tictactoekata.model.Board;
import com.poliscrypts.tictactoekata.model.enumeration.Square;
import com.poliscrypts.tictactoekata.repository.TicTacToeRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
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
    public void shouldCreateNewGameSuccessfully() {

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

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenFirstPlayerIsNot_X() {

        // Given
        UUID uuid = UUID.randomUUID();
        TurnDto turnDto = TurnDto.builder()
                .id(uuid.toString())
                .player("O")
                .build();

        Optional<Board> gameById = Optional.of(new Board().toBuilder()
                .id(uuid)
                .nextPlayer(Square.O)
                .topLeft(Square.BLANK)
                .build());

        // When
        when(ticTacToeRepository.findById(any())).thenReturn(gameById);

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticTacToeServiceImpl.play(turnDto)
        );

        // Then
        assertEquals("The first player should be: X", exception.getMessage());
    }
}