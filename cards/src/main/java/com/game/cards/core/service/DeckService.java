package com.game.cards.core.service;

import com.game.cards.rest.dto.CardDTO;

import java.util.List;

public interface DeckService {

    String createNewDeck();

    List<CardDTO> selectCards(String deckId, int countCards);


}
