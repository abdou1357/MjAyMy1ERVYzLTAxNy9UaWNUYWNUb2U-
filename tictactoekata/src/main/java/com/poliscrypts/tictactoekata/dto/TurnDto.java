package com.poliscrypts.tictactoekata.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TurnDto {
    private String id;
    private String player;
    private int row;
    private int col;
}
