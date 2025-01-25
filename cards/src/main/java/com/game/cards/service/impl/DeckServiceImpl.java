package com.game.cards.service.impl;

import com.game.cards.client.ApiDeckFeignClient;
import com.game.cards.dto.CardDTO;
import com.game.cards.dto.DeckResponseDTO;
import com.game.cards.exception.FeignClientException;
import com.game.cards.service.DeckService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DeckServiceImpl implements DeckService {

    private final ApiDeckFeignClient apiDeckFeignClient;

    public DeckServiceImpl(ApiDeckFeignClient apiDeckFeignClient) {
        this.apiDeckFeignClient = apiDeckFeignClient;
    }

    @Override
    public String createNewDeck() {
        Integer deckCount = 1;
        DeckResponseDTO response = apiDeckFeignClient.createOrder(deckCount).getBody();

        if (response == null) {
            throw new FeignClientException("Ocorreu um erro ao obter dados do deck.");
        }

        return response.getDeckId();

    }

    @Override
    public List<CardDTO> selectCards(String deckId, int count) {
        return null;
    }
}
