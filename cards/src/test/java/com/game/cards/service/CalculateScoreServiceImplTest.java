package com.game.cards.service;

import com.game.cards.dto.CardDTO;
import com.game.cards.service.impl.CalculateScoreServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculateScoreServiceImplTest {

    @InjectMocks
    private CalculateScoreServiceImpl calculateScoreServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deve_retornar_a_pontuacao_correta_para_cartas_numericas() {

        List<CardDTO> cards = List.of(new CardDTO("1", "image", "1", "suit"),
                new CardDTO("1", "image", "2", "suit"),
                new CardDTO("1", "image", "3", "suit"));
        int score = calculateScoreServiceImpl.calculate(cards);
        assertEquals(6, score);
    }

    @Test
    void deve_retornar_a_pontuacao_correta_para_cartas_de_figura() {

        List<CardDTO> cards = List.of(new CardDTO("1", "image", "JACK", "suit"),
                new CardDTO("1", "image", "QUEEN", "suit"),
                new CardDTO("1", "image", "KING", "suit"));
        int score = calculateScoreServiceImpl.calculate(cards);
        assertEquals(36, score);
    }

    @Test
    void deve_retornar_a_pontuacao_correta_para_aces() {

        List<CardDTO> cards = List.of(new CardDTO("1", "image", "ACE", "suit"));
        int score = calculateScoreServiceImpl.calculate(cards);
        assertEquals(1, score);
    }

    @Test
    void deve_retornar_a_pontuacao_correta_para_cartas_misturadas() {

        List<CardDTO> cards = List.of(new CardDTO("1", "image", "2", "suit"),
                new CardDTO("1", "image", "JACK", "suit"),
                new CardDTO("1", "image", "ACE", "suit"));
        int score = calculateScoreServiceImpl.calculate(cards);
        assertEquals(14, score);
    }

    @Test
    void deve_retornar_zero_para_lista_vazia() {
        List<CardDTO> cards = List.of();
        int score = calculateScoreServiceImpl.calculate(cards);
        assertEquals(0, score);
    }

    @Test
    void deve_lancar_excecao_para_valor_invalido_de_carta() {
        List<CardDTO> cards = List.of(new CardDTO("1", "image", "INVALID", "suit"));
        assertThrows(NumberFormatException.class, () -> calculateScoreServiceImpl.calculate(cards));
    }
}
