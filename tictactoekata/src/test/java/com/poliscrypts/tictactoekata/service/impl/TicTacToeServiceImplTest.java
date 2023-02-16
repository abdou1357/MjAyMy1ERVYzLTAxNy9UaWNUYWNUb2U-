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
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;
import java.util.UUID;

import static org.junit.Assert.assertThrows;
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

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTurnPlayerNameNotEqualNextPlayer() {

        // Given
        UUID uuid = UUID.randomUUID();
        TurnDto turnDto = TurnDto.builder()
                .id(uuid.toString())
                .player("X")
                .build();

        Board boardById = Board.builder()
                .id(uuid)
                .nextPlayer(Square.O)
                .build();

        // When
        when(ticTacToeRepository.findById(any())).thenReturn(Optional.of(boardById));

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticTacToeServiceImpl.play(turnDto)
        );

        // Then
        assertEquals("The next player should be: O", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTheGameIsEnd() {

        // Given
        UUID uuid = UUID.randomUUID();
        TurnDto turnDto = TurnDto.builder()
                .id(uuid.toString())
                .player("X")
                .build();

        Board gameById = new Board().toBuilder()
                .id(uuid)
                .nextPlayer(Square.X)
                .endBoard(true)
                .build();

        // When
        when(ticTacToeRepository.findById(any())).thenReturn(Optional.of(gameById));
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticTacToeServiceImpl.play(turnDto)
        );

        // Then
        assertEquals("The game is end and the winner was: draw", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenSquareIsOccupied() {

        // Given
        UUID uuid = UUID.randomUUID();
        TurnDto turnDto = TurnDto.builder()
                .id(uuid.toString())
                .player("O")
                .col(0)
                .row(0)
                .build();

        Board gameById = new Board().toBuilder()
                .id(uuid)
                .topLeft(Square.O)
                .nextPlayer(Square.O)
                .endBoard(false)
                .build();

        // When
        when(ticTacToeRepository.findById(any())).thenReturn(Optional.of(gameById));
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticTacToeServiceImpl.play(turnDto)
        );

        // Then
        assertEquals("The asked square is occupied row = 0, col = 0 ", exception.getMessage());
    }
    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTheGameIsEndAndWinners_O_withAnyCase() {

        // Given
        UUID uuid = UUID.randomUUID();
        TurnDto turnDto = TurnDto.builder()
                .id(uuid.toString())
                .player("O")
                .build();

        Board gameById = new Board().toBuilder()
                .id(uuid)
                .nextPlayer(Square.O)
                .topLeft(Square.O)
                .topCenter(Square.O)
                .topRight(Square.O)
                .endBoard(true)
                .build();

        // When
        when(ticTacToeRepository.findById(any())).thenReturn(Optional.of(gameById));
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticTacToeServiceImpl.play(turnDto)
        );

        // Then
        assertEquals("The game is end and the winner was: O", exception.getMessage());
    }

    @Test
    public void shouldThrowIllegalArgumentExceptionWhenTheGameIsEndAndWinners_X_withAnyCase() {

        // Given
        UUID uuid = UUID.randomUUID();
        TurnDto turnDto = TurnDto.builder()
                .id(uuid.toString())
                .player("X")
                .build();

        Board gameById = new Board().toBuilder()
                .id(uuid)
                .nextPlayer(Square.X)
                .topLeft(Square.X)
                .topCenter(Square.X)
                .topRight(Square.X)
                .endBoard(true)
                .build();

        // When
        when(ticTacToeRepository.findById(any())).thenReturn(Optional.of(gameById));
        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> ticTacToeServiceImpl.play(turnDto)
        );

        // Then
        assertEquals("The game is end and the winner was: X", exception.getMessage());
    }
    @Test
    public void shouldReturnBoardDtoWithIsWinnerTrue() {

        // Given
        UUID uuid = UUID.randomUUID();
        TurnDto turnDto = TurnDto.builder()
                .id(uuid.toString())
                .player("O")
                .row(0)
                .col(2)
                .build();

        Optional<Board> gameById = Optional.of(new Board().toBuilder()
                .id(uuid)
                .nextPlayer(Square.O)
                .topLeft(Square.O)
                .topCenter(Square.O)
                .build());

        // When
        when(ticTacToeRepository.findById(any())).thenReturn(gameById);
        when(ticTacToeRepository.save(any())).thenReturn(Mockito.mock(Board.class));
        BoardDto response = ticTacToeServiceImpl.play(turnDto);

        // Then
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("X Player", response.getNextPlayer());
        assertTrue(response.isEndBoard());
    }

    @Test
    public void shouldReturnBoardDtoWithIsBoardDTOTieTrue() {

        // Given
        UUID uuid = UUID.randomUUID();
        TurnDto turnDto = TurnDto.builder()
                .id(uuid.toString())
                .player("O")
                .row(1)
                .col(1)
                .build();

        Optional<Board> gameById = Optional.of(new Board().toBuilder()
                .id(uuid)
                .nextPlayer(Square.O)
                .topLeft(Square.O)
                .topCenter(Square.X)
                .topRight(Square.O)
                .centerLeft(Square.O)
                .centerRight(Square.X)
                .bottomLeft(Square.X)
                .bottomCenter(Square.O)
                .bottomRight(Square.X)
                .build());

        // When
        when(ticTacToeRepository.findById(any())).thenReturn(gameById);
        when(ticTacToeRepository.save(any())).thenReturn(Mockito.mock(Board.class));
        BoardDto response = ticTacToeServiceImpl.play(turnDto);

        // Then
        assertNotNull(response);
        assertNotNull(response.getId());
        assertEquals("X Player", response.getNextPlayer());
        assertTrue(response.isEndBoard());
    }
}