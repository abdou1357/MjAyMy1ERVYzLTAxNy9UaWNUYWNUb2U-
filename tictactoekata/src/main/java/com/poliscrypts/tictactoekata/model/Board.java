package com.poliscrypts.tictactoekata.model;

import com.poliscrypts.tictactoekata.model.enumeration.Square;
import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Board {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false)
    private UUID id;
    private Square nextPlayer = Square.BLANK;
    private boolean endBoard;
    private Square topLeft = Square.BLANK;
    private Square topCenter = Square.BLANK;
    private Square topRight = Square.BLANK;
    private Square centerLeft = Square.BLANK;
    private Square center = Square.BLANK;
    private Square centerRight = Square.BLANK;
    private Square bottomLeft = Square.BLANK;
    private Square bottomCenter = Square.BLANK;
    private Square bottomRight = Square.BLANK;

    public String drawBoard() {
        String lineSeparatorLabel = "line.separator";
        String lineSeparatorLine = "+---+---+---+";
        String placeholder = "| %s | %s | %s |";

        String board;
        board = System.getProperty(lineSeparatorLabel) +
                lineSeparatorLine +
                System.getProperty(lineSeparatorLabel) +
                String.format(placeholder, topLeft.getValue(), topCenter.getValue(), topRight.getValue()) +
                System.getProperty(lineSeparatorLabel) +
                lineSeparatorLine +
                System.getProperty(lineSeparatorLabel) +
                String.format(placeholder, centerLeft.getValue(), center.getValue(), centerRight.getValue()) +
                System.getProperty(lineSeparatorLabel) +
                lineSeparatorLine +
                System.getProperty(lineSeparatorLabel) +
                String.format(placeholder, bottomLeft.getValue(), bottomCenter.getValue(), bottomRight.getValue()) +
                System.getProperty(lineSeparatorLabel) +
                lineSeparatorLine;
        return board;
    }
}
