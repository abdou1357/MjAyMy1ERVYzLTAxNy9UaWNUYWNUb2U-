package com.poliscrypts.tictactoekata.service.impl;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.dto.TurnDto;
import com.poliscrypts.tictactoekata.mapper.BoardMapper;
import com.poliscrypts.tictactoekata.model.Board;
import com.poliscrypts.tictactoekata.model.enumeration.Square;
import com.poliscrypts.tictactoekata.repository.TicTacToeRepository;
import com.poliscrypts.tictactoekata.service.TicTacToeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TicTacToeServiceImpl implements TicTacToeService {
    private TicTacToeRepository ticTacToeRepository;

    private static final Logger logger = LoggerFactory.getLogger(TicTacToeServiceImpl.class);
    private static final BoardMapper boardMapper = new BoardMapper();

    /**
     * All possible combinations to win.
     */
    private static final List<List<String>> winnerCombinations = Arrays.asList(
            Arrays.asList("00", "01", "02"),
            Arrays.asList("10", "11", "12"),
            Arrays.asList("20", "21", "22"),
            Arrays.asList("00", "10", "20"),
            Arrays.asList("01", "11", "21"),
            Arrays.asList("02", "12", "22"),
            Arrays.asList("00", "11", "22"),
            Arrays.asList("02", "11", "20")
    );

    public TicTacToeServiceImpl(TicTacToeRepository ticTacToeRepository) {
        this.ticTacToeRepository = ticTacToeRepository;
    }

    @Override
    public BoardDto createNewGame() {
        var board = ticTacToeRepository.save(new Board());

        logger.info("\nWelcome to 3*3 Tic Tac Toe Game: {}\n", board.drawBoard()!= null ? board.drawBoard(): "");

        return boardMapper.apply(board);
    }

    @Override
    public BoardDto play(TurnDto turnDto) {
        Optional<Board> boardById = ticTacToeRepository.findById(UUID.fromString(turnDto.getId()));

        if (boardById.isPresent()) {
            var board = boardById.get();
        // Check if the first player is X.
        if (firstPlayerIsNotX(board,turnDto)) {
            throw new IllegalArgumentException("The first player should be: " + Square.X);
        }
            // Check if the next player is correct.
            if (isNotCorrectPlayer(board,turnDto)) {
                throw new IllegalArgumentException("The next player should be: " + board.getNextPlayer());
            }
            // Check if the game is end.
            if (board.isEndBoard()) {
                throw new IllegalArgumentException(String.format("The game is end and the winner was: %s", getWinner(board)));
            }
        }
        return null;
    }
    private boolean isBoardEmpty(Board board) {
        List<Square> squares = List.of(
                board.getTopLeft(), board.getTopCenter(), board.getTopRight(),
                board.getCenterLeft(), board.getCenter(), board.getCenterRight(),
                board.getBottomLeft(), board.getBottomCenter(), board.getBottomRight()
        );
        return squares.stream().allMatch(b -> b == Square.BLANK);
    }

    private boolean isNotCorrectPlayer(Board board,TurnDto turnDto){
        return board.getNextPlayer() != Square.BLANK && !board.getNextPlayer().getValue().equals(turnDto.getPlayer());
    }

    private boolean firstPlayerIsNotX(Board board,TurnDto turnDto){
        return !turnDto.getPlayer().equals("X") && isBoardEmpty(board);
    }

    private String getWinner(Board board) {
        if (isThereWinnerByName(board, Square.O)) {
            return Square.O.getValue();
        }
        if (isThereWinnerByName(board, Square.X)) {
            return Square.X.getValue();
        }
        return "draw";
    }

    /**
     *
     * @param board
     * @param player X or O
     * @return true if there are a winner
     */
    private boolean isThereWinnerByName(Board board, Square player) {
        Map<String, Square> squareMap = Map.of(
                "00", board.getTopLeft(), "01", board.getTopCenter(), "02", board.getTopRight(),
                "10", board.getCenterLeft(), "11", board.getCenter(), "12", board.getCenterRight(),
                "20", board.getBottomLeft(), "21", board.getBottomCenter(), "22", board.getBottomRight()
        );
        return winnerCombinations.stream()
                .anyMatch(combination -> combination.stream().allMatch(b -> squareMap.get(b) == player));
    }


}

