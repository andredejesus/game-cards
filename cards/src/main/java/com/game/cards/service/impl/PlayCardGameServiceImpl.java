package com.game.cards.service.impl;

import com.game.cards.dto.CardDTO;
import com.game.cards.model.PlayerDataEntity;
import com.game.cards.repository.PlayerDataRepository;
import com.game.cards.service.CalculateScoreService;
import com.game.cards.service.DeckService;
import com.game.cards.service.PlayCardGameService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class PlayCardGameServiceImpl implements PlayCardGameService {

    private final DeckService deckService;
    private final CalculateScoreService calculateScoreService;
    private final PlayerDataRepository playerDataRepository;

    public PlayCardGameServiceImpl(DeckService deckService,
                                   CalculateScoreService calculateScoreService,
                                   PlayerDataRepository playerDataRepository) {
        this.deckService = deckService;
        this.calculateScoreService = calculateScoreService;
        this.playerDataRepository = playerDataRepository;
    }

    @Override
    public List<PlayerDataEntity> play(Integer qtdPlayers, Integer countCards) {

        String deckId = deckService.createNewDeck();

        List<PlayerDataEntity> players = new ArrayList<>();

        for (int i = 0; i < qtdPlayers; i++) {

            PlayerDataEntity player = new PlayerDataEntity();

            List<CardDTO> cards =  deckService.selectCards(deckId, countCards);

            int score = calculateScoreService.calculate(cards);

            player.setNumberPlayer(i);
            player.setScore(score);

            players.add(player);

        }

        return this.verifyWinner(players);

    }

    List<PlayerDataEntity> verifyWinner(List<PlayerDataEntity> players) {

        List<PlayerDataEntity> winners = new ArrayList<>();

        int maxScore = 0;

        for (PlayerDataEntity player : players) {
            if (player.getScore() > maxScore) {

                maxScore = player.getScore();
                winners.clear();
                winners.add(player);

            } else if (player.getScore() == maxScore) {
                winners.add(player);
            }
            player.setCreatedAt(LocalDateTime.now());
        }

        playerDataRepository.saveAll(winners);

        return winners;
    }
}
