package com.game.cards.service;

import com.game.cards.dto.CardDTO;

import java.util.List;

public interface DeckService {

    String createNewDeck();

    List<CardDTO> selectCards(String deckId, int count);


}
