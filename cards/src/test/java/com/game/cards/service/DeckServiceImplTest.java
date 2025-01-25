package com.game.cards.service;

import com.game.cards.client.ApiDeckFeignClient;
import com.game.cards.dto.DeckResponseDTO;
import com.game.cards.exception.FeignClientException;
import com.game.cards.service.impl.DeckServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

class DeckServiceImplTest {

    @InjectMocks
    private DeckServiceImpl deckServiceImpl;

    private ApiDeckFeignClient apiDeckFeignClient;

    @BeforeEach
    public void setUp() {
        apiDeckFeignClient = Mockito.mock(ApiDeckFeignClient.class);
        deckServiceImpl = new DeckServiceImpl(apiDeckFeignClient);
    }

    @Test
    void deve_gerar_uma_excecao_caso_o_retorno_seja_nulo() {

        Mockito.when(apiDeckFeignClient.createOrder(1))
                .thenReturn(ResponseEntity.ok(null));


        FeignClientException exception = Assertions.assertThrows(
                FeignClientException.class,
                () -> deckServiceImpl.createNewDeck(),
                "Ocorreu um erro ao obter dados do deck."
        );

        Assertions.assertEquals("Ocorreu um erro ao obter dados do deck.", exception.getMessage());
    }

    @Test
    void deve_gerar_um_id_para_o_deck_com_sucesso() {
        String deckId = "3p40paa87x90";

        Mockito.when(apiDeckFeignClient.createOrder(1))
                .thenReturn(ResponseEntity
                        .of(Optional.of(new DeckResponseDTO(true, deckId, true, 52))));

        String deckIdRetornado = deckServiceImpl.createNewDeck();

        Assertions.assertEquals(deckId, deckIdRetornado);

    }

}
