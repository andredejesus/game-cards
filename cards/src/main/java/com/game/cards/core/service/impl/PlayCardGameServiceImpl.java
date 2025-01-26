package com.game.cards.core.service.impl;

import com.game.cards.core.repository.PlayerDataRepository;
import com.game.cards.core.service.CalculateScoreService;
import com.game.cards.core.service.DeckService;
import com.game.cards.core.service.PlayCardGameService;
import com.game.cards.rest.dto.CardDTO;
import com.game.cards.core.exception.MandatoryFieldsException;
import com.game.cards.core.model.PlayerDataEntity;
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

        if (qtdPlayers == 0 && countCards == 0) {
            throw new MandatoryFieldsException("Quantidade de jogadores e cartas são obrigatórios.");
        }

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

    private List<PlayerDataEntity> verifyWinner(List<PlayerDataEntity> players) {

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
