package com.game.cards.service;

import com.game.cards.core.service.CalculateScoreService;
import com.game.cards.core.service.DeckService;
import com.game.cards.rest.dto.CardDTO;
import com.game.cards.core.model.PlayerDataEntity;

import com.game.cards.core.repository.PlayerDataRepository;
import com.game.cards.core.service.impl.PlayCardGameServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.Mockito.when;

class PlayCardGameServiceImplTest {

    @Mock
    private DeckService deckService;

    @Mock
    private CalculateScoreService calculateScoreService;

    @Mock
    private PlayerDataRepository playerDataRepository;

    @InjectMocks
    private PlayCardGameServiceImpl playCardGameServiceImpl;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void deve_retornar_o_numero_correto_de_jogadores() {

        when(deckService.createNewDeck()).thenReturn("deckId");
        when(deckService.selectCards("deckId", 5))
                .thenReturn(List.of(new CardDTO("1", "image", "2", "suit"),
                        new CardDTO("1", "image", "3", "suit")));

        when(calculateScoreService.calculate(List.of(new CardDTO("1", "image", "2", "suit"),
                new CardDTO("1", "image", "2", "suit")))).thenReturn(5);

        List<PlayerDataEntity> players = playCardGameServiceImpl.play(3, 5);

        assertEquals(3, players.size());
    }

    @Test
    void deve_identificar_um_unico_vencedor() {

        when(deckService.createNewDeck()).thenReturn("deckId");

        when(deckService.selectCards("deckId", 2))
                .thenReturn(List.of(
                        new CardDTO("1", "image", "2", "suit"),
                        new CardDTO("2", "image", "3", "suit")));

        when(calculateScoreService.calculate(anyList()))
                .thenReturn(5, 7);

        List<PlayerDataEntity> players = playCardGameServiceImpl.play(2, 2);

        assertEquals(1, players.size());

    }

    @Test
    void deve_identificar_multiplos_vencedores() {
        when(deckService.createNewDeck()).thenReturn("deckId");
        when(deckService.selectCards("deckId", 5))
                .thenReturn(List.of(new CardDTO("1", "image", "2", "suit"),
                        new CardDTO("1", "image", "3", "suit")));
        when(calculateScoreService.calculate(List.of(new CardDTO("1", "image", "2", "suit"),
                new CardDTO("1", "image", "3", "suit")))).thenReturn(5);

        List<PlayerDataEntity> players = playCardGameServiceImpl.play(2, 5);
        players.get(1).setScore(5);

        assertEquals(2, players.size());
    }

    @Test
    void deve_lidar_com_zero_jogadores() {
        List<PlayerDataEntity> players = playCardGameServiceImpl.play(0, 5);

        assertEquals(0, players.size());
    }
}