package com.poliscrypts.tictactoekata.controller;

import com.poliscrypts.tictactoekata.dto.BoardDto;
import com.poliscrypts.tictactoekata.dto.TurnDto;
import com.poliscrypts.tictactoekata.exception.Error;
import com.poliscrypts.tictactoekata.service.TicTacToeService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

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

    @Test
    @DisplayName("The row should be between or equals 0 and 2.")
    void shouldThrowIllegalArgumentExceptionWhenRowOutsideTheBox() {

        // Given
        String url = "http://localhost:" + port + "/api/tictactoe/play";
        TurnDto turnDto = TurnDto.builder()
                .col(0)
                .row(-1)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");

        // When
        ResponseEntity<Error> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity(turnDto, headers), Error.class);

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Wrong row information, they should be between 0 and 2!", response.getBody().getMessage());

    }
    @Test
    @DisplayName("The column should be between or equals 0 and 2.")
    void shouldThrowIllegalArgumentExceptionWhenColumnOutsideTheBox() {

        // Given
        String url = "http://localhost:" + port + "/api/tictactoe/play";
        TurnDto turnDto = TurnDto.builder()
                .col(3)
                .row(1)
                .build();
        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");

        // When
        ResponseEntity<Error> response = this.restTemplate.exchange(
                url, HttpMethod.POST, new HttpEntity(turnDto, headers), Error.class);

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Wrong column information, they should be between 0 and 2!", response.getBody().getMessage());

    }

    @Test
    @DisplayName("The player should be X or O.")
    void shouldThrowIllegalArgumentExceptionWhenPlayerIsNot_X_or_O() {

        // Given
        String url = "http://localhost:" + port + "/api/tictactoe/play";
        TurnDto turnDto = TurnDto.builder()
                .col(2)
                .row(0)
                .player("#")
                .build();

        MultiValueMap<String, String> headers = new LinkedMultiValueMap<>();
        headers.add("Content-Type", "application/json");


        // When
        ResponseEntity<Error> response = this.restTemplate.exchange(

                url,
                HttpMethod.POST,
                new HttpEntity(turnDto, headers),
                Error.class
        );

        //Then
        assertNotNull(response);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Wrong player name, it should be X or O.", response.getBody().getMessage());
    }
}