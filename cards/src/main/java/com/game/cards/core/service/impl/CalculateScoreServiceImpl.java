package com.game.cards.core.service.impl;

import com.game.cards.core.service.CalculateScoreService;
import com.game.cards.rest.dto.CardDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalculateScoreServiceImpl implements CalculateScoreService {
    @Override
    public int calculate(List<CardDTO> cards) {

        int totalScore = 0;

        for (CardDTO card : cards) {

            String value = card.getValue().toUpperCase();

            int score = switch (value) {
                case "ACE" -> 1;
                case "KING" -> 13;
                case "QUEEN" -> 12;
                case "JACK" -> 11;
                default -> Integer.parseInt(value);
            };

            totalScore += score;

        }
        return totalScore;
    }
}
