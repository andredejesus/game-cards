package com.game.cards.client;

import com.game.cards.dto.DeckResponseDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@FeignClient(
        name = "ApiDeck",
        url = "https://deckofcardsapi.com/api")
public interface ApiDeckFeignClient {

    @GetMapping(value = "/deck/new/shuffle/", produces = "application/json")
    ResponseEntity<DeckResponseDTO> createOrder(@RequestParam(name = "deck_count",
            required = false, defaultValue = "1") Integer deckCount);

}
