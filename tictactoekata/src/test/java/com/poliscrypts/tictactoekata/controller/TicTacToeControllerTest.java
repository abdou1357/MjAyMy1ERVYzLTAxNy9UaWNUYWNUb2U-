package com.poliscrypts.tictactoekata.controller;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.service.TicTacToeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TicTacToeControllerTest {
    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @MockBean
    private TicTacToeService ticTacToeService;

    @Test
    void shouldCallNewGameEndpointSuccessfully() {

        // Given
        String url = "http://localhost:" + port + "/api/tictactoe/create";
        UUID uuid = UUID.randomUUID();
        BoardDto game = BoardDto.builder()
                .id(uuid)
                .nextPlayer("O or X can start the game")
                .endBoard(false)
                .build();

        // When
        when(ticTacToeService.createNewGame()).thenReturn(game);
        ResponseEntity<BoardDto> response = this.restTemplate.exchange(url, HttpMethod.GET, null, BoardDto.class);

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(uuid, response.getBody().getId());
        assertEquals("O or X can start the game", response.getBody().getNextPlayer());
        assertFalse(response.getBody().isEndBoard());
    }
}