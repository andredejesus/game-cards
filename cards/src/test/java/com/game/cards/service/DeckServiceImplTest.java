package com.game.cards.service;

import com.game.cards.client.ApiDeckFeignClient;
import com.game.cards.dto.CardDTO;
import com.game.cards.dto.DeckResponseDTO;
import com.game.cards.dto.DrawResponseDTO;
import com.game.cards.dto.ImageDTO;
import com.game.cards.exception.FeignClientException;
import com.game.cards.service.impl.DeckServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

class DeckServiceImplTest {

    @InjectMocks
    private DeckServiceImpl deckServiceImpl;

    private ApiDeckFeignClient apiDeckFeignClient;

    @BeforeEach
    public void setUp() {
        apiDeckFeignClient = Mockito.mock(ApiDeckFeignClient.class);
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deve_gerar_uma_excecao_caso_o_retorno_seja_nulo() {

        when(apiDeckFeignClient.createOrder(1))
                .thenReturn(ResponseEntity.ok(null));


        FeignClientException exception = assertThrows(
                FeignClientException.class,
                () -> deckServiceImpl.createNewDeck(),
                "Ocorreu um erro ao obter dados do deck."
        );

        assertEquals("Ocorreu um erro ao obter dados do deck.", exception.getMessage());
    }

    @Test
    void deve_gerar_um_id_para_o_deck_com_sucesso() {
        String deckId = "3p40paa87x90";

        when(apiDeckFeignClient.createOrder(1))
                .thenReturn(ResponseEntity
                        .of(Optional.of(new DeckResponseDTO(true, deckId, true, 52))));

        String deckIdRetornado = deckServiceImpl.createNewDeck();

        assertEquals(deckId, deckIdRetornado);

    }

    @Test
    void deve_gerar_uma_excecao_caso_nao_retorne_nenhuma_carta() {

        when(apiDeckFeignClient.drawCards(1, "deckId"))
                .thenReturn(ResponseEntity.ok(null));

        FeignClientException exception = assertThrows(
                FeignClientException.class,
                () -> deckServiceImpl.selectCards("deckId", 1),
                "Ocorreu um erro ao obter as cartas."
        );

        assertEquals("Ocorreu um erro ao obter as cartas.", exception.getMessage());

    }

    @Test
    void deve_gerar_uma_excecao_caso_deckId_e_count_sejam_nulos() {

        IllegalArgumentException exception = assertThrows(
                IllegalArgumentException.class,
                () -> deckServiceImpl.selectCards(null, 0),
                "DeckId e count são obrigatórios."
        );

        assertEquals("DeckId e count são obrigatórios.", exception.getMessage());

    }

    @Test
    void deve_retornar_cartas_com_sucesso() {
        String deckId = "kxozasf3edqu";
        String cardSuit = "SPADES";
        String cardImage = "https://deckofcardsapi.com/static/img/6H.png";
        int cardValue = 6;
        String cardCode = "6H";

        List<CardDTO> cardsMock = List
                .of(new CardDTO(cardCode, cardImage, String.valueOf(cardValue), cardSuit));

        when(apiDeckFeignClient.drawCards(1, deckId))
                .thenReturn(ResponseEntity
                        .of(Optional.of(new DrawResponseDTO(true, deckId, cardsMock, 50))));

        List<CardDTO> cards = deckServiceImpl.selectCards(deckId, 1);

        assertEquals(1, cards.size());
        assertEquals(cardCode, cards.get(0).getCode());
        assertEquals(cardImage, cards.get(0).getImage());
        assertEquals(cardValue, Integer.parseInt(cards.get(0).getValue()));
        assertEquals(cardSuit, cards.get(0).getSuit());


    }

}
