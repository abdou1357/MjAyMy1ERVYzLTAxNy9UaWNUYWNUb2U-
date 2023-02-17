package com.poliscrypts.tictactoekata.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import java.util.UUID;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder(toBuilder = true)
public class BoardDto {
    @Schema(description = "Board ID", example = "e33aea50-1efa-4559-9198-c4dae525268a")
    private UUID id;
    @Schema(description = "The next player name", example = "X or O")
    private String nextPlayer;
    @Schema(description = "is the board full", example = "true or false")
    private boolean endBoard;
}
