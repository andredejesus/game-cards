package com.game.cards.service;

import com.game.cards.dto.CardDTO;

import java.util.List;

public interface CalculateScoreService {

    int calculate(List<CardDTO> cards);

}
