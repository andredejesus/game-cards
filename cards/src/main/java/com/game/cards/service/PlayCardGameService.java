package com.game.cards.service;

import com.game.cards.model.PlayerDataEntity;

import java.util.List;

public interface PlayCardGameService {

    List<PlayerDataEntity> play(Integer qtdPlayers, Integer countCards);

}
