package com.poliscrypts.tictactoekata.dto;

import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BoardDto {
    private UUID id;
    private String nextPlayer;
    private boolean endBoard;
}
