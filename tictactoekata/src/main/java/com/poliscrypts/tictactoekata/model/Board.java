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
}
