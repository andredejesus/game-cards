package com.game.cards.controller;

import com.game.cards.dto.PlayerDataResponseDTO;
import com.game.cards.mapper.PlayerDataMapper;
import com.game.cards.model.PlayerDataEntity;
import com.game.cards.service.PlayCardGameService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class PlayCardGameController {

    private final PlayCardGameService playCardGameService;

    public PlayCardGameController(PlayCardGameService playCardGameService) {
        this.playCardGameService = playCardGameService;
    }

    @GetMapping("/play")
    public ResponseEntity<List<PlayerDataResponseDTO>> play(@RequestParam Integer qtdPlayers,
                                                      @RequestParam Integer countCards) {

        List<PlayerDataEntity> playerDataEntityList =  playCardGameService.play(qtdPlayers, countCards);

        List<PlayerDataResponseDTO> playerDataResponseDTO = PlayerDataMapper
                .toDTOList(playerDataEntityList);

        return ResponseEntity
                .ok(playerDataResponseDTO);

    }
}
