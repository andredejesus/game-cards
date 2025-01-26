package com.game.cards.rest.controller;

import com.game.cards.rest.dto.PlayerDataResponseDTO;
import com.game.cards.rest.mapper.PlayerDataMapper;
import com.game.cards.core.model.PlayerDataEntity;
import com.game.cards.core.service.PlayCardGameService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
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

    @Operation(
            summary = "Jogo de cartas",
            description = "Jogar um jogo de cartas com a quantidade de jogadores e cartas informadas.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sucesso"),
            @ApiResponse(responseCode = "400", description = "Campos obrigatórios não informados"),
            @ApiResponse(responseCode = "500", description = "Erro interno do servidor")
    })
    @GetMapping("/play")
    public ResponseEntity<List<PlayerDataResponseDTO>> play(
                                                @Parameter(description = "Quantidade de jogadores", required = true)
                                                @RequestParam Integer qtdPlayers,
                                                @Parameter(description = "Quantidade de cartas", required = true)
                                                @RequestParam Integer countCards) {

        List<PlayerDataEntity> playerDataEntityList =  playCardGameService.play(qtdPlayers, countCards);

        List<PlayerDataResponseDTO> playerDataResponseDTO = PlayerDataMapper
                .toDTOList(playerDataEntityList);

        return ResponseEntity
                .ok(playerDataResponseDTO);

    }
}
