package com.game.cards.core.service;

import com.game.cards.rest.dto.CardDTO;

import java.util.List;

public interface CalculateScoreService {

    int calculate(List<CardDTO> cards);

}
