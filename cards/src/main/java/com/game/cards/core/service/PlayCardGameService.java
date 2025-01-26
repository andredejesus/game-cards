package com.game.cards.core.service;

import com.game.cards.core.model.PlayerDataEntity;

import java.util.List;

public interface PlayCardGameService {

    List<PlayerDataEntity> play(Integer qtdPlayers, Integer countCards);

}
