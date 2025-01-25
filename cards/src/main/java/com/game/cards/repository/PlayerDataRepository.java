package com.game.cards.repository;

import com.game.cards.model.PlayerDataEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PlayerDataRepository extends JpaRepository<PlayerDataEntity, Long> {
}
