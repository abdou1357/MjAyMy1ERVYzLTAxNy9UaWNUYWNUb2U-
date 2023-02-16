package com.poliscrypts.tictactoekata.repository;

import com.poliscrypts.tictactoekata.model.Board;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TicTacToeRepository extends JpaRepository<Board, UUID> {
}
