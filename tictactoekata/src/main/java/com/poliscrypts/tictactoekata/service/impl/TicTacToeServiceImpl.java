package com.poliscrypts.tictactoekata.service.impl;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.dto.TurnDto;
import com.poliscrypts.tictactoekata.mapper.BoardMapper;
import com.poliscrypts.tictactoekata.model.Board;
import com.poliscrypts.tictactoekata.repository.TicTacToeRepository;
import com.poliscrypts.tictactoekata.service.TicTacToeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
        return null;
    }


}

