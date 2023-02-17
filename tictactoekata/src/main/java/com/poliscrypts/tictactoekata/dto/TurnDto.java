package com.poliscrypts.tictactoekata.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class TurnDto {
    @Schema(description = "Turn ID", example = "e33aea50-1efa-4559-9198-c4dae525268a")
    private String id;
    @Schema(description = "player name", example = "X")
    private String player;
    @Schema(description = "row number", example = "0")
    private int row;
    @Schema(description = "column number", example = "2")
    private int col;
}
