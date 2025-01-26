package com.game.cards.rest.mapper;

import com.game.cards.rest.dto.PlayerDataResponseDTO;
import com.game.cards.core.model.PlayerDataEntity;

import java.util.List;
import java.util.stream.Collectors;

public class PlayerDataMapper {

    private PlayerDataMapper() {
    }

    public static PlayerDataResponseDTO toDTO(PlayerDataEntity entity) {
        if (entity == null) {
            return null;
        }

        PlayerDataResponseDTO dto = new PlayerDataResponseDTO();
        dto.setId(entity.getId());
        dto.setNumberPlayer(entity.getNumberPlayer());
        dto.setScore(entity.getScore());
        dto.setCreatedAt(entity.getCreatedAt());

        return dto;
    }

    public static List<PlayerDataResponseDTO> toDTOList(List<PlayerDataEntity> entities) {
        if (entities == null || entities.isEmpty()) {
            return List.of();
        }

        return entities.stream()
                .map(PlayerDataMapper::toDTO)
                .collect(Collectors.toList());
    }
}

